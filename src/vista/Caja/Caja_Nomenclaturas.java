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
import javax.swing.BorderFactory;
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
byte tge;
byte tga;
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
        cargareliminar.setVisible(false);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        b.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        txtnom2.setVisible(false);
        jTabbedPane1.setSelectedIndex(1);
         jTabbedPane1.setEnabledAt(0,false);
         jTabbedPane1.setEnabledAt(1, false);
         btnLista.setVisible(false);
    
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
             String titulos[]={"Área","","Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec listaruinidad";
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
    tb_Grupo1.setRowHeight(45);
    
}
    public void formatoventanas(){
    tb_Grupos.getColumnModel().getColumn(0).setPreferredWidth(70);
    tb_Grupos.getColumnModel().getColumn(1).setPreferredWidth(200);
    tb_Grupos.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupos.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Grupos.setRowHeight(30);
    tb_Grupos2.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Grupos2.getColumnModel().getColumn(1).setPreferredWidth(250);
    tb_Grupos2.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupos2.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Grupos2.setRowHeight(45);
    
}
    public void formatoventanas1(){
    tb_Grupos1.getColumnModel().getColumn(0).setPreferredWidth(200);
    tb_Grupos1.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Grupos1.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Grupos1.getColumnModel().getColumn(2).setPreferredWidth(200);
    tb_Grupos1.setRowHeight(45);
}
    
    
    
    public void Modificar(){
 
                Caja_Nomenclatura cno1 = new Caja_Nomenclatura();
                cno1.setCod_nomen_caja(txtcod.getText());
                cno1.setCod_grupo_nomen_aten(grupo.getText());
                cno1.setAr_id(Integer.parseInt(unior.getText()));
                cno1.setId_cuenta(ct6.getText());
                cno1.setNomen_caja(txtnom2.getText());
                cno1.setDescripcion_nomen_tipo(txtdes.getText());
                cno1.setNom_usu(lblusu.getText());//
                
                if (visible.getSelectedIndex()==0){
                    cno1.setVis_admi("S");
                     } else if(visible.getSelectedIndex()==1){
                    cno1.setVis_admi("N");  
                }

       
                //cno1.setTipo_nomen(visible1.getSelectedItem().toString());
        
                cno1.setVis_aten("");
                
                       if(cno1.modificarNomenclatura()==true){
                        
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Actualizados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                txtnom2.setEditable(false);
                                txtdes.setEditable(false);
                                tge=9;
                           LISTAR();
                           
                          
                       } else {
                           cargareliminar.setVisible(true);
                           cargareliminar.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Ocurrió un error, Verifique");
                           eli.setVisible(false);
                           noeli.setVisible(false);
                       }
                  
            

                  
    }
    public void eliminar(){
         try{
            if(tge==6 ){
                Caja_Nomenclatura hCEl = new Caja_Nomenclatura();
                hCEl.setCod_nomen_caja(txtcod.getText());
                if(hCEl.eliminarCajaNomenclatura()){
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
            } 
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
    }
    public void Guardar(){
        
        if(txtnom1.getText().equals("")|| txtdes.getText().equals("")){
            cargareliminar.setVisible(true);
                           cargareliminar.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Asegurese de completar todos los campos");
                           eli.setVisible(false);
                           noeli.setVisible(false);
        } else {

            //////////////////////////////////////////
             Caja_Nomenclatura cn = new Caja_Nomenclatura();
            /////////////////////////////////////////
            if(cn.CPT_verif(txtnomenclatura.getText()+txtnom1.getText())>0){
                 cargareliminar.setVisible(true);
                 cargareliminar.setBackground(new Color(255,91,70)); 
                 Mensaje.setText("El CPT ingresado ya existe, Verifique ");
                 eli.setVisible(false);
                 noeli.setVisible(false);
                 txtnom1.setText("");
                 txtnom1.requestFocus();
                 txtnom1.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }else{
                Caja_Nomenclatura cno1 = new Caja_Nomenclatura();
                //cno1.setCod_nomen_caja(cnn.idNomen());
                cno1.setCod_grupo_nomen_aten(grupo.getText());
                cno1.setAr_id(Integer.parseInt(unior.getText()));
                cno1.setId_cuenta(ct6.getText());
                
                
                
//                    JOptionPane.showMessageDialog(this, (txtnomenclatura.getText()+txtnom1.getText()));
                cno1.setNomen_caja(txtnomenclatura.getText()+txtnom1.getText());   
               
                cno1.setDescripcion_nomen_tipo(txtdes.getText().toUpperCase());
                cno1.setNom_usu(lblusu.getText());
                
                if (visible.getSelectedIndex()==0){
                    cno1.setVis_admi("S");
                     } else if(visible.getSelectedIndex()==1){
                    cno1.setVis_admi("N");  
                }
         
                //cno1.setCod_nomen_caja("1");
   

                cno1.setVis_aten("");
       
                    if(cno1.nuevaNomenclatura()==true){
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Guardados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                txtnom1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                                btnguardar.setEnabled(false);
                                btneditar.setEnabled(true);
                                btneliminar.setEnabled(true);
                                tge=1;
                           LISTAR();
                       } else {
                          cargareliminar.setVisible(true);
                           cargareliminar.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Ocurrió un error, Verifique");
                           eli.setVisible(false);
                           noeli.setVisible(false);

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
        jPanel28 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscarPaciente3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            Unidad = new javax.swing.JDialog();
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
                Cta6 = new javax.swing.JDialog();
                jPanel8 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                jPanel30 = new javax.swing.JPanel();
                txtBuscar2 = new javax.swing.JTextField();
                btnBuscarPaciente2 = new javax.swing.JButton();
                jScrollPane5 = new javax.swing.JScrollPane();
                tb_Grupos2 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jPanel1 = new javax.swing.JPanel();
                    btnNuevo = new javax.swing.JButton();
                    btneditar = new javax.swing.JButton();
                    btnguardar = new javax.swing.JButton();
                    btneliminar = new javax.swing.JButton();
                    lblusu = new javax.swing.JLabel();
                    jLabel57 = new javax.swing.JLabel();
                    jPanel23 = new javax.swing.JPanel();
                    buscartodo = new javax.swing.JTextField();
                    btnBuscarPaciente = new javax.swing.JButton();
                    lbldetalle = new javax.swing.JLabel();
                    btnLista = new javax.swing.JButton();
                    jTabbedPane1 = new javax.swing.JTabbedPane();
                    jPanel4 = new javax.swing.JPanel();
                    txtnomenclatura = new javax.swing.JTextField();
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
                    txtnom1 = new javax.swing.JTextField();
                    nm = new javax.swing.JLabel();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    txtdes = new javax.swing.JEditorPane();
                    txtnom2 = new javax.swing.JTextField();
                    panelCPT = new javax.swing.JPanel();
                    txtgrupo = new javax.swing.JTextField();
                    b = new javax.swing.JButton();
                    panelCPT1 = new javax.swing.JPanel();
                    txtct6 = new javax.swing.JTextField();
                    b1 = new javax.swing.JButton();
                    panelCPT2 = new javax.swing.JPanel();
                    txtuo = new javax.swing.JTextField();
                    b2 = new javax.swing.JButton();
                    jPanel2 = new javax.swing.JPanel();
                    jScrollPane3 = new javax.swing.JScrollPane();
                    tb_Grupo1 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        cargareliminar = new javax.swing.JPanel();
                        Mensaje = new javax.swing.JLabel();
                        eli = new javax.swing.JButton();
                        noeli = new javax.swing.JButton();
                        jPanel5 = new javax.swing.JPanel();
                        jLabel33 = new javax.swing.JLabel();

                        Grupos.setAlwaysOnTop(true);
                        Grupos.setMinimumSize(new java.awt.Dimension(310, 441));
                        Grupos.setModal(true);
                        Grupos.setResizable(false);

                        jPanel6.setBackground(new java.awt.Color(41, 127, 184));

                        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel2.setText("Grupo");

                        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

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
                        });

                        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                        jPanel28.setLayout(jPanel28Layout);
                        jPanel28Layout.setHorizontalGroup(
                            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 5, Short.MAX_VALUE))
                        );
                        jPanel28Layout.setVerticalGroup(
                            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
                        );

                        btnBuscarPaciente3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                        btnBuscarPaciente3.setContentAreaFilled(false);
                        btnBuscarPaciente3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarPaciente3.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPaciente3ActionPerformed(evt);
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
                                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(24, Short.MAX_VALUE))
                        );
                        jPanel6Layout.setVerticalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(415, 415, 415))
                        );

                        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                        tb_Grupos.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tb_Grupos.getTableHeader().setReorderingAllowed(false);
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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                        );
                        GruposLayout.setVerticalGroup(
                            GruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GruposLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
                        );

                        Unidad.setAlwaysOnTop(true);
                        Unidad.setMinimumSize(new java.awt.Dimension(591, 419));
                        Unidad.setResizable(false);

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

                        javax.swing.GroupLayout UnidadLayout = new javax.swing.GroupLayout(Unidad.getContentPane());
                        Unidad.getContentPane().setLayout(UnidadLayout);
                        UnidadLayout.setHorizontalGroup(
                            UnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                        );
                        UnidadLayout.setVerticalGroup(
                            UnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UnidadLayout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
                        );

                        Cta6.setAlwaysOnTop(true);
                        Cta6.setMinimumSize(new java.awt.Dimension(700, 422));
                        Cta6.setResizable(false);

                        jPanel8.setBackground(new java.awt.Color(41, 127, 184));

                        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel4.setText("Cuenta 6");

                        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

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

                        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                        jPanel30.setLayout(jPanel30Layout);
                        jPanel30Layout.setHorizontalGroup(
                            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 5, Short.MAX_VALUE))
                        );
                        jPanel30Layout.setVerticalGroup(
                            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
                        );

                        btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                        btnBuscarPaciente2.setContentAreaFilled(false);
                        btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPaciente2ActionPerformed(evt);
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
                                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(387, Short.MAX_VALUE))
                        );
                        jPanel8Layout.setVerticalGroup(
                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(410, 410, 410))
                        );

                        jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                        tb_Grupos2.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tb_Grupos2.getTableHeader().setReorderingAllowed(false);
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
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                        );
                        Cta6Layout.setVerticalGroup(
                            Cta6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Cta6Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                .addContainerGap())
                        );

                        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        setMinimumSize(new java.awt.Dimension(1054, 550));

                        jPanel1.setBackground(new java.awt.Color(41, 127, 184));
                        jPanel1.setPreferredSize(new java.awt.Dimension(292, 437));

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

                        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel57.setText("<html>CPT<span style=\"font-size:'14px'\"><br>Nomenclaturas</br></span></html>");

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

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(15, 15, 15)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lbldetalle)))
                                        .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

                        jTabbedPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
                        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                        txtnomenclatura.setEditable(false);
                        txtnomenclatura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtnomenclatura.setForeground(new java.awt.Color(51, 51, 51));
                        txtnomenclatura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                        txtnomenclatura.setEnabled(false);

                        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel7.setText("Grupo");

                        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel9.setText("Cuenta contable");

                        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel10.setText("Área");

                        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel11.setText("CPT");

                        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel12.setText("Descripción");

                        txtcod.setEditable(false);
                        txtcod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtcod.setForeground(new java.awt.Color(255, 255, 255));
                        txtcod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                        txtcod.setSelectionColor(new java.awt.Color(255, 255, 255));

                        lblvisa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        lblvisa.setText("Enviar H.C. al modulo de admisión");

                        visible.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        visible.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));

                        grupo.setForeground(new java.awt.Color(255, 255, 255));
                        grupo.setText("jLabel3");

                        ct6.setForeground(new java.awt.Color(255, 255, 255));
                        ct6.setText("jLabel4");

                        unior.setForeground(new java.awt.Color(255, 255, 255));
                        unior.setText("jLabel5");

                        txtnom1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtnom1.setForeground(new java.awt.Color(51, 51, 51));
                        txtnom1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(109, 109, 109)));
                        txtnom1.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtnom1KeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtnom1KeyReleased(evt);
                            }
                        });

                        nm.setForeground(new java.awt.Color(255, 255, 255));
                        nm.setText("jLabel5");

                        txtdes.setForeground(new java.awt.Color(51, 51, 51));
                        txtdes.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtdesKeyReleased(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtdesKeyTyped(evt);
                            }
                        });
                        jScrollPane1.setViewportView(txtdes);

                        txtnom2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtnom2.setForeground(new java.awt.Color(51, 51, 51));
                        txtnom2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(109, 109, 109)));
                        txtnom2.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtnom2KeyPressed(evt);
                            }
                        });

                        panelCPT.setBackground(new java.awt.Color(255, 255, 255));
                        panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                        txtgrupo.setEditable(false);
                        txtgrupo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txtgrupo.setForeground(new java.awt.Color(51, 51, 51));
                        txtgrupo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                        txtgrupo.setBorder(null);
                        txtgrupo.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtgrupoCaretUpdate(evt);
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
                                .addGap(5, 5, 5)
                                .addComponent(txtgrupo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                        );
                        panelCPTLayout.setVerticalGroup(
                            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCPTLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtgrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                        panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                        txtct6.setEditable(false);
                        txtct6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txtct6.setForeground(new java.awt.Color(51, 51, 51));
                        txtct6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                        txtct6.setBorder(null);
                        txtct6.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtct6CaretUpdate(evt);
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
                                .addGap(5, 5, 5)
                                .addComponent(txtct6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                        );
                        panelCPT1Layout.setVerticalGroup(
                            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCPT1Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtct6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
                        panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                        txtuo.setEditable(false);
                        txtuo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txtuo.setForeground(new java.awt.Color(51, 51, 51));
                        txtuo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                        txtuo.setBorder(null);
                        txtuo.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtuoCaretUpdate(evt);
                            }
                        });

                        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                        b2.setContentAreaFilled(false);
                        b2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        b2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                b2ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
                        panelCPT2.setLayout(panelCPT2Layout);
                        panelCPT2Layout.setHorizontalGroup(
                            panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCPT2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtuo, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                        );
                        panelCPT2Layout.setVerticalGroup(
                            panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCPT2Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtuo, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                        jPanel4.setLayout(jPanel4Layout);
                        jPanel4Layout.setHorizontalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addGap(0, 758, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(136, 136, 136)
                                        .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel12))
                                        .addGap(144, 144, 144)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(txtnomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(txtnom1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtnom2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(panelCPT2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelCPT1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelCPT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblvisa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(visible, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(nm)
                                        .addGap(18, 18, 18)
                                        .addComponent(ct6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(unior)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(grupo)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel4Layout.setVerticalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtnomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(129, 129, 129)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(ct6)
                                                .addComponent(nm)
                                                .addComponent(unior)
                                                .addComponent(grupo))
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblvisa)
                                                .addComponent(visible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        jTabbedPane1.addTab("...", jPanel4);

                        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                        jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                        tb_Grupo1.setSelectionBackground(new java.awt.Color(50, 151, 219));
                        tb_Grupo1.getTableHeader().setReorderingAllowed(false);
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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                        );

                        jTabbedPane1.addTab("...", jPanel2);

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
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                                    .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTabbedPane1)))
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
      //  txtcod.setText(cnn.idNomen());
      
        tg=1;
         txtnom1.setEditable(false);
         txtgrupo.setEditable(false);
         txtct6.setEditable(false);
         txtuo.setEditable(false);
         txtdes.setEditable(true);
         
         txtnom1.setEditable(true);
         txtnomenclatura.setVisible(true);
         txtnom1.setVisible(true);
         txtnom2.setVisible(false);
         txtnom1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
         txtdes.setEditable(true);
    
         visible.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         btneliminar.setEnabled(false);
         b.setVisible(true);

         txtnomenclatura.setText("");
         txtdes.setText("");
         txtnom1.setText("");
         txtuo.setText("");
         txtct6.setText("");
         txtgrupo.setText("");
        
        jTabbedPane1.setSelectedIndex(0);
        Grupos.setVisible(true);
        jLabel33.setText("Edición"); 
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
jTabbedPane1.setSelectedIndex(0);
        txtnomenclatura.setEditable(true);
         txtdes.setEditable(true);
          txtnom2.setEditable(true);
         txtdes.setEditable(true);
 
         visible.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         
         b.setVisible(true);
         b1.setVisible(true);
         b2.setVisible(true);
         tg=2;
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
            Guardar();
            b.setVisible(false);
            b1.setVisible(false);
            b2.setVisible(false);
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

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        BuscarGrupo();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
    int fila=tb_Grupo1.getSelectedRow();
     if(evt.getClickCount()==1){

       txtcod.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));  
       txtgrupo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4))); 
       txtct6.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
       txtuo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
       txtdes.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
       
       grupo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 9)));
       ct6.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 11)));  
       unior.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 10))); 
       txtnom2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1))); 
       if(tb_Grupo1.getValueAt(fila, 6).equals("N")){
           visible.setSelectedIndex(1);
           
       }else if(tb_Grupo1.getValueAt(fila, 6).equals("S")){
           visible.setSelectedIndex(0);
           
       }
//       visible.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
      
   }
     if(evt.getClickCount()==2){
      jLabel33.setText("Edición");
      btnLista.setVisible(true);
      jTabbedPane1.setSelectedIndex(0);

      }
//      try {
//            String cuenta5=cpt.getText();
//            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1));
//            txtnomenclatura.setText(palabra5);
//            txtnomenclatura.setEnabled(true);
//            
//            String cj=CPTE.getText();
//            String cja=String.valueOf(cj.charAt(2)+""+cj.charAt(3)+""+cj.charAt(4)+""+cj.charAt(5)+""+cj.charAt(6)+""+cj.charAt(7)+""+cj.charAt(8)+""+cj.charAt(9));
//            txtnom1.setText(cja);
//            txtnom1.setEnabled(true);
//            
//        } catch (Exception e) {
//        }
        tg=2;
         txtnomenclatura.setEditable(true);
         txtdes.setEditable(true);
     
         visible.setEnabled(true);
         btnguardar.setEnabled(false);
         btneditar.setEnabled(true);
         btneliminar.setEnabled(true);
         b.setEnabled(true);
         b1.setEnabled(true);
         b2.setEnabled(true);
         b.setVisible(false);
         b1.setVisible(false);
         b2.setVisible(false);
          txtnom1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
         txtnomenclatura.setVisible(false);
         txtnom1.setVisible(false);
         txtnom2.setVisible(true);
         txtcod.setEditable(true);
         txtnomenclatura.setEditable(true);
         txtgrupo.setEditable(true);
         txtct6.setEditable(true);
         txtuo.setEditable(true);
         txtdes.setEditable(true);
         txtcod.setEditable(false);
         txtnomenclatura.setEditable(false);
         txtgrupo.setEditable(false);
         txtct6.setEditable(false);
         txtuo.setEditable(false);
         txtnom2.setEditable(false);
         txtdes.setEditable(false);
         

    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed

    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed

          char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();

       txtcod.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));  
       txtgrupo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4))); 
       txtct6.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
       txtuo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
       txtdes.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
       
       grupo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 9)));
       ct6.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 11)));  
       unior.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 10))); 
       txtnom2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1))); 
       if(tb_Grupo1.getValueAt(fila, 6).equals("N")){
           visible.setSelectedIndex(1);
           
       }else if(tb_Grupo1.getValueAt(fila, 6).equals("S")){
           visible.setSelectedIndex(0);
           
       }
//       visible.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
       jLabel33.setText("Edición");
      
   }
   
//      try {
//            String cuenta5=cpt.getText();
//            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1));
//            txtnomenclatura.setText(palabra5);
//            txtnomenclatura.setEnabled(true);
//            
//            String cj=CPTE.getText();
//            String cja=String.valueOf(cj.charAt(2)+""+cj.charAt(3)+""+cj.charAt(4)+""+cj.charAt(5)+""+cj.charAt(6)+""+cj.charAt(7)+""+cj.charAt(8)+""+cj.charAt(9));
//            txtnom1.setText(cja);
//            txtnom1.setEnabled(true);
//            
//        } catch (Exception e) {
//        }
        tg=2;
         txtnomenclatura.setEditable(true);
         txtdes.setEditable(true);
     
         visible.setEnabled(true);
         btnguardar.setEnabled(false);
         btneditar.setEnabled(true);
         btneliminar.setEnabled(true);
         b.setEnabled(true);
         b1.setEnabled(true);
         b2.setEnabled(true);
         b.setVisible(false);
         b1.setVisible(false);
         b2.setVisible(false);
          txtnom1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
         txtnomenclatura.setVisible(false);
         txtnom1.setVisible(false);
         txtnom2.setVisible(true);
         txtcod.setEditable(true);
         txtnomenclatura.setEditable(true);
         txtgrupo.setEditable(true);
         txtct6.setEditable(true);
         txtuo.setEditable(true);
         txtdes.setEditable(true);
         txtcod.setEditable(false);
         txtnomenclatura.setEditable(false);
         txtgrupo.setEditable(false);
         txtct6.setEditable(false);
         txtuo.setEditable(false);
         txtnom2.setEditable(false);
         txtdes.setEditable(false);
         btnLista.setVisible(true);

       

    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void tb_GruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GruposMouseClicked
   
        int fila=tb_Grupos.getSelectedRow();
        if(evt.getClickCount()==2){
            Grupos.dispose();
            nm.setText(String.valueOf(tb_Grupos.getValueAt(fila, 0)));
            txtgrupo.setText(String.valueOf(tb_Grupos.getValueAt(fila, 0))+"\t"+(tb_Grupos.getValueAt(fila, 1)));
            grupo.setText(String.valueOf(tb_Grupos.getValueAt(fila, 2)));
            
        }
         try {
            String cuenta5=nm.getText();
            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1));
            txtnomenclatura.setText(palabra5);
            txtnomenclatura.setEditable(true);
        } catch (Exception e) {
        } 
        
          txtgrupo.setEditable(true);
            txtgrupo.setEditable(false);
           
   b1.setVisible(true);
    }//GEN-LAST:event_tb_GruposMouseClicked

    private void tb_GruposKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GruposKeyPressed
 
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos.getSelectedRow();
            Grupos.dispose();
    
            txtgrupo.setText(String.valueOf(tb_Grupos.getValueAt(fila, 0))+"\t"+(tb_Grupos.getValueAt(fila, 1)));
            grupo.setText(String.valueOf(tb_Grupos.getValueAt(fila, 2)));
            nm.setText(String.valueOf(tb_Grupos.getValueAt(fila, 0)));
            txtgrupo.setEditable(true);
            txtgrupo.setEditable(false);
           
        }
         try {
            String cuenta5=nm.getText();
            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1));
            txtnomenclatura.setText(palabra5);
            txtnomenclatura.setEditable(true);
        } catch (Exception e) {
        } 
         txtgrupo.setEditable(true);
            txtgrupo.setEditable(false);
   Cta6.setVisible(true);
   b1.setVisible(true);
               
    }//GEN-LAST:event_tb_GruposKeyPressed

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
            txtuo.setEditable(true);
            txtuo.setEditable(false);
            txtnom1.requestFocus();
            txtnom1.setEditable(true);
            } 
    }//GEN-LAST:event_tb_Grupos1MouseClicked

    private void tb_Grupos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos1KeyPressed
      
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos1.getSelectedRow();
            Unidad.dispose();
            txtuo.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            unior.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));
            txtuo.setEditable(true);
            txtuo.setEditable(false);
            txtnom1.requestFocus();
            txtnom1.setEditable(true);
  
           
        }
    }//GEN-LAST:event_tb_Grupos1KeyPressed

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
            txtct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 0))+"\t"+(tb_Grupos2.getValueAt(fila, 1)));
            ct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 2)));
            txtct6.setEditable(true);
            txtct6.setEditable(false);
            txtnomenclatura.setRequestFocusEnabled(true);
           
        }
         b2.setVisible(true);
    }//GEN-LAST:event_tb_Grupos2MouseClicked

    private void tb_Grupos2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos2KeyPressed
            char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos2.getSelectedRow();
            Cta6.dispose();
            txtct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 0))+"\t"+(tb_Grupos2.getValueAt(fila, 1)));
            ct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 2)));
            txtct6.setEditable(true);
            txtct6.setEditable(false);
            txtnomenclatura.setRequestFocusEnabled(true);
            Unidad.setVisible(true);
        }
        b2.setVisible(true);
                
    }//GEN-LAST:event_tb_Grupos2KeyPressed

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

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==3 || tge==1 ||tge==9 ||tge==7||tge==9){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            Modificar();
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            btneliminar.setEnabled(true);
               b.setVisible(false);
         b1.setVisible(false);
         b2.setVisible(false);


        }
          if (tge==6){
            eliminar();

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

    private void txtnom2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnom2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnom2KeyPressed

    private void txtdesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesKeyReleased
        txtdes.setText(txtdes.getText().toUpperCase());
         
    }//GEN-LAST:event_txtdesKeyReleased

    private void txtdesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesKeyTyped
             
    }//GEN-LAST:event_txtdesKeyTyped

    private void txtnom1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnom1KeyReleased
        txtnom1.setText(txtnom1.getText().toUpperCase());
    }//GEN-LAST:event_txtnom1KeyReleased

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        jLabel33.setText("Listado"); 
        jTabbedPane1.setSelectedIndex(1);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        
        ///////////////
         txtnom1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
         cargareliminar.setVisible(false);
         txtnom1.setEditable(false);
         txtgrupo.setEditable(false);
         txtct6.setEditable(false);
         txtuo.setEditable(false);
         txtdes.setEditable(true);
         
         txtnom1.setEditable(true);
         txtnomenclatura.setVisible(true);
         txtnom1.setVisible(true);
         txtnom2.setVisible(false);
        
        
         txtdes.setEditable(true);
    
         visible.setEnabled(true);
         btneditar.setEnabled(false);
         b.setVisible(true);

         txtnomenclatura.setText("");
         txtdes.setText("");
         txtnom1.setText("");
         txtuo.setText("");
         txtct6.setText("");
         txtgrupo.setText("");
         btnLista.setVisible(false);
        Buscar();  

    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void txtgrupoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtgrupoCaretUpdate

    }//GEN-LAST:event_txtgrupoCaretUpdate

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
       Grupos.setVisible(true);
    }//GEN-LAST:event_bActionPerformed

    private void txtct6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtct6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtct6CaretUpdate

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        Cta6.setVisible(true);
    }//GEN-LAST:event_b1ActionPerformed

    private void txtuoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtuoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuoCaretUpdate

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
       Unidad.setVisible(true);
    }//GEN-LAST:event_b2ActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
         jLabel33.setText("Listado"); 
        jTabbedPane1.setSelectedIndex(1);
         btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        
        ///////////////
         txtnom1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
         cargareliminar.setVisible(false);
         txtnom1.setEditable(false);
         txtgrupo.setEditable(false);
         txtct6.setEditable(false);
         txtuo.setEditable(false);
         txtdes.setEditable(true);
         
         txtnom1.setEditable(true);
         txtnomenclatura.setVisible(true);
         txtnom1.setVisible(true);
         txtnom2.setVisible(false);
        
        
         txtdes.setEditable(true);
    
         visible.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         b.setVisible(true);

         txtnomenclatura.setText("");
         txtdes.setText("");
         txtnom1.setText("");
         txtuo.setText("");
         txtct6.setText("");
         txtgrupo.setText("");
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void btnBuscarPaciente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente3ActionPerformed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

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
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog Unidad;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBuscarPaciente3;
    private javax.swing.JButton btnBuscarPaciente4;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JLabel ct6;
    private javax.swing.JButton eli;
    private javax.swing.JLabel grupo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel lblvisa;
    private javax.swing.JLabel nm;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupos;
    private javax.swing.JTable tb_Grupos1;
    private javax.swing.JTable tb_Grupos2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtcod;
    public static javax.swing.JTextField txtct6;
    private javax.swing.JEditorPane txtdes;
    public static javax.swing.JTextField txtgrupo;
    private javax.swing.JTextField txtnom1;
    private javax.swing.JTextField txtnom2;
    private javax.swing.JTextField txtnomenclatura;
    public static javax.swing.JTextField txtuo;
    private javax.swing.JLabel unior;
    private javax.swing.JComboBox visible;
    // End of variables declaration//GEN-END:variables
}
