/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import groovyjarjarasm.asm.tree.TryCatchBlockNode;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_Cta_Abono;
import servicios.Conexion;
import vista.admisionCentral.FrmNuevaHistoriaC;

/**
 *
 * @author MYS1
 */
public class Caja_Abono extends javax.swing.JFrame {
DefaultTableModel m;
ResultSet r;
Conexion c=new Conexion();
byte tg;
byte tge;
Connection conexion=c.conectar();
Caja_Cta_Abono cnn = new Caja_Cta_Abono();
    /**
     * Creates new form Caja_Abobo
     */
    public Caja_Abono() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
         jTabbedPane1.setSelectedIndex(1);
         BHC.setLocationRelativeTo(null);//en el centro
         BHC.getContentPane().setBackground(Color.WHITE);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        //abonodet.setVisible(false);
        cargareliminar.setVisible(false);
         nid.setEnabled(false); 
        docu.setEnabled(false); 
        des.setEnabled(false); 
        
        ///////
        resumen.setVisible(false);
        jScrollPane4.setVisible(false);
        resumenes.setVisible(false);
        
        jScrollPane3.setVisible(false);
        tb_Grupo1.setVisible(false);
        jScrollPane10.setVisible(false);
        verif.setVisible(false);        
    }
    
    
    public void Mresumen(){
        String consulta="";
        try {
            resumenes.setModel(new DefaultTableModel());
         
             String titulos[]={"Documento","Monto","Fecha","Hora","CPT","DNI","NOM","IDHC","HU","IDCTAC","IDCT","E1","E2","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            Caja_Cta_Abono obj=new Caja_Cta_Abono();
                    consulta="exec CAJA_CTA_ABONO_RESUMEN ?";      
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
            fila[14]=r.getString(15);
                m.addRow(fila);
                c++;
            }
            resumenes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            resumenes.setRowSorter(elQueOrdena);
            this.resumenes.setModel(m);
             formatoR();
        } catch (Exception e) {
            System.out.println("Error abono: " + e.getMessage());
        }
      }
    
       public void Verificar(){
        String consulta="";
        try {
            verif.setModel(new DefaultTableModel());
         
             String titulos[]={"id_cta","id_hc","cod_nomen_caja","huella","estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];
            Caja_Cta_Abono obj=new Caja_Cta_Abono();
                    consulta="exec CAJA_CTA_ABONO_VERIFICAR_REGISTRO ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, lblhc.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);
            fila[4]=r.getString(5);
                m.addRow(fila);
                c++;
            }
            verif.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            verif.setRowSorter(elQueOrdena);
            this.verif.setModel(m);
            
        } catch (Exception e) {
            System.out.println("Error abono: " + e.getMessage());
        }
      }
       
             
       
     public void formato1(){
        tb_Grupo1.getColumnModel().getColumn(0).setPreferredWidth(220);
        tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(480);
        tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(400);
        tb_Grupo1.getColumnModel().getColumn(3).setPreferredWidth(150);
        tb_Grupo1.getColumnModel().getColumn(4).setPreferredWidth(70);


//        tb_Grupo.getColumnModel().getColumn(5).setMaxWidth(0);
//        tb_Grupo.getColumnModel().getColumn(7).setMinWidth(0);
//        tb_Grupo.getColumnModel().getColumn(7).setMaxWidth(0);
    }
     
      public void formatoR(){
        resumenes.getColumnModel().getColumn(0).setPreferredWidth(220);
        resumenes.getColumnModel().getColumn(1).setPreferredWidth(100);
        resumenes.getColumnModel().getColumn(2).setPreferredWidth(150);
        resumenes.getColumnModel().getColumn(3).setPreferredWidth(100);
        resumenes.getColumnModel().getColumn(4).setPreferredWidth(200);


        resumenes.getColumnModel().getColumn(5).setMinWidth(0);
        resumenes.getColumnModel().getColumn(5).setMaxWidth(0);
        resumenes.getColumnModel().getColumn(6).setMinWidth(0);
        resumenes.getColumnModel().getColumn(6).setMaxWidth(0);
        resumenes.getColumnModel().getColumn(7).setMinWidth(0);
        resumenes.getColumnModel().getColumn(7).setMaxWidth(0);
        resumenes.getColumnModel().getColumn(8).setMinWidth(0);
        resumenes.getColumnModel().getColumn(8).setMaxWidth(0);
        resumenes.getColumnModel().getColumn(9).setMinWidth(0);
        resumenes.getColumnModel().getColumn(9).setMaxWidth(0);
        resumenes.getColumnModel().getColumn(10).setMinWidth(0);
        resumenes.getColumnModel().getColumn(10).setMaxWidth(0);
        resumenes.getColumnModel().getColumn(11).setMinWidth(0);
        resumenes.getColumnModel().getColumn(11).setMaxWidth(0);
        resumenes.getColumnModel().getColumn(12).setMinWidth(0);
        resumenes.getColumnModel().getColumn(12).setMaxWidth(0);
        

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

            Caja_Cta_Abono obj=new Caja_Cta_Abono();
                    consulta="exec Caja_BuscarHCAbono ?";
                    
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
    
    public void TotalAbonos(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
             String titulos[]={"id","monto","id_detalle"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            Caja_Cta_Abono obj=new Caja_Cta_Abono();
                    consulta="exec Caja_Total_Abonos ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, idac.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
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
    

      private void suma(){
        double total = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for( int i=0 ; i<tb_Grupo1.getRowCount() ; i++)
        {
            double numero =0,st=0;
            try{
                //capturamos valor de celda
                numero = Double.parseDouble(tb_Grupo1.getValueAt(i, 1).toString() );
                
  
            }
            catch (NumberFormatException nfe){ //si existe un error se coloca 0 a la celda
                numero = 0;
                tb_Grupo1.setValueAt(0, i, 2);
                System.out.println("error" + nfe.getMessage());
            }
            //se suma al total
          total += numero;
        }
        //muestra en el componente
        this.totales.setText( String.valueOf(total) );
    }
      
      private void sumar(){
        double total = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for( int i=0 ; i<resumenes.getRowCount() ; i++)
        {
            double numero =0,st=0;
            try{
                //capturamos valor de celda
                numero = Double.parseDouble(resumenes.getValueAt(i, 1).toString() );
                
  
            }
            catch (NumberFormatException nfe){ //si existe un error se coloca 0 a la celda
                numero = 0;
                resumenes.setValueAt(0, i, 2);
                System.out.println("error" + nfe.getMessage());
            }
            //se suma al total
          total += numero;
        }
        //muestra en el componente
        this.tta.setText( String.valueOf(total) );
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
        public void GuardarC(){
try {
  
                Caja_Cta_Abono cno1 = new Caja_Cta_Abono();
                cno1.setId_hc(lblhc.getText());
                cno1.setCod_nomen_caja(nomenc.getText());          
                cno1.setHuella(Byte.parseByte("0"));
                    if(cno1.nuevoC()==true){
                           System.out.println("Abono cabecera guardado");
                             
                             Verificar();
                             cargar();
                       
                            

                       } else {
                           System.out.println("error guardar abono cabecera");
        } } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
        } 
     
    public void Guardar(){
          if((idac.getText().equals(""))  || (abono.getText().equals(""))){
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrio un error");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                btnguardar.setEnabled(false);
                                abono.setEnabled(false);
        } else {
                        
                
                Caja_Cta_Abono cno1 = new Caja_Cta_Abono();
                cno1.setId_Cta_Abonoc(Integer.parseInt(idac.getText()));
                cno1.setMonto(Double.parseDouble(abono.getText() ));
                cno1.setHuella(Byte.parseByte("0"));
                cno1.setDocumento(docu.getText());
                cno1.setUsuario(lblusu.getText());
                    if(cno1.nuevo()==true){
                        
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Guardados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=1;
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(false);
                           btneliminar.setEnabled(false);
                           abono.setEnabled(false);      
  
                       } else {
                           cargareliminar.setVisible(true);
                           cargareliminar.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Ocurrio un error, Verifique");
                           eli.setVisible(false);
                           noeli.setVisible(false);
    }}}
    
    
     public void Modificar(){
           
                        Caja_Cta_Abono cno = new Caja_Cta_Abono();
                        cno.setId_Cta_Abono(Integer.parseInt(nid.getText() ));
                        cno.setMonto(Double.parseDouble(abono.getText() ));
                        cno.setUsuario(lblusu.getText());
                        if(cno.modificar()==true){
                            /////////////////mensaje
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Actualizados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=1;
                                Mresumen();
                                suma();
                                sumar();
                            /////////////////////////
                            
                            
                            
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             abono.setEnabled(false);   
                     
                        } else {
                           
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrio un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                        }
                   
 }
     
     public void Eliminar(){
      
        try{
                Caja_Cta_Abono cnE = new Caja_Cta_Abono();
                cnE.setId_Cta_Abono(Integer.parseInt(nid.getText()));
                if(cnE.eliminar()){
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro Eliminado");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=9;
                                 Mresumen();
                                suma();
                                sumar();
 
                }
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
     }
     public void cargar(){
         
            int fila = tb_Grupo.getSelectedRow();
            Verificar();
            idac.setText(String.valueOf(verif.getValueAt(fila, 0)));
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
        jLabel13 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        T3 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel11 = new javax.swing.JPanel();
            jLabel16 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            jPanel12 = new javax.swing.JPanel();
            btnNuevo1 = new javax.swing.JButton();
            jLabel18 = new javax.swing.JLabel();
            jTabbedPane1 = new javax.swing.JTabbedPane();
            jPanel2 = new javax.swing.JPanel();
            buscartodo = new javax.swing.JTextField();
            jLabel8 = new javax.swing.JLabel();
            jLabel50 = new javax.swing.JLabel();
            resumen = new javax.swing.JPanel();
            jLabel41 = new javax.swing.JLabel();
            jLabel44 = new javax.swing.JLabel();
            ACTM = new javax.swing.JLabel();
            APENOM = new javax.swing.JLabel();
            jLabel45 = new javax.swing.JLabel();
            tta = new javax.swing.JLabel();
            jScrollPane4 = new javax.swing.JScrollPane();
            resumenes = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel3 = new javax.swing.JPanel();
                codpago = new javax.swing.JLabel();
                jLabel51 = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                txtdni = new javax.swing.JTextField();
                txtape = new javax.swing.JTextField();
                txtdir = new javax.swing.JTextField();
                txthc = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jLabel12 = new javax.swing.JLabel();
                lblhc = new javax.swing.JLabel();
                idac = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                jPanel6 = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                nomenc = new javax.swing.JLabel();
                abonodet = new javax.swing.JPanel();
                jLabel52 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                nid = new javax.swing.JTextField();
                jLabel10 = new javax.swing.JLabel();
                docu = new javax.swing.JTextField();
                jLabel4 = new javax.swing.JLabel();
                des = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                abono = new javax.swing.JTextField();
                Total = new javax.swing.JLabel();
                totales = new javax.swing.JLabel();
                jScrollPane3 = new javax.swing.JScrollPane();
                tb_Grupo1 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jScrollPane10 = new javax.swing.JScrollPane();
                    verif = new javax.swing.JTable(){
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

                        BHC.setAlwaysOnTop(true);
                        BHC.setMinimumSize(new java.awt.Dimension(749, 338));
                        BHC.setResizable(false);
                        BHC.getContentPane().setLayout(null);

                        jPanel7.setBackground(new java.awt.Color(0, 153, 153));
                        jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                        jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel19.setText("Cliente");

                        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
                        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel13.setText("Busqueda por DNI y H.C.");

                        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                        T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                        T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        T3.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                T3MouseClicked(evt);
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
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscarKeyTyped(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                        jPanel27.setLayout(jPanel27Layout);
                        jPanel27Layout.setHorizontalGroup(
                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
                        );
                        jPanel27Layout.setVerticalGroup(
                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(T3))
                                .addGap(4, 4, 4))
                        );

                        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                        jPanel7.setLayout(jPanel7Layout);
                        jPanel7Layout.setHorizontalGroup(
                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel13)
                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(501, Short.MAX_VALUE))
                        );
                        jPanel7Layout.setVerticalGroup(
                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addGap(5, 5, 5)
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel13)
                                .addGap(335, 335, 335))
                        );

                        BHC.getContentPane().add(jPanel7);
                        jPanel7.setBounds(0, 0, 780, 120);

                        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

                        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                        jPanel8.setLayout(jPanel8Layout);
                        jPanel8Layout.setHorizontalGroup(
                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 750, Short.MAX_VALUE)
                        );
                        jPanel8Layout.setVerticalGroup(
                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 70, Short.MAX_VALUE)
                        );

                        BHC.getContentPane().add(jPanel8);
                        jPanel8.setBounds(0, 310, 750, 70);

                        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-100.png"))); // NOI18N
                        jLabel9.setText("Busqueda de Historias ");

                        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                        jPanel9.setLayout(jPanel9Layout);
                        jPanel9Layout.setHorizontalGroup(
                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel9)
                                .addContainerGap(174, Short.MAX_VALUE))
                        );
                        jPanel9Layout.setVerticalGroup(
                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel9)
                                .addContainerGap(30, Short.MAX_VALUE))
                        );

                        jTabbedPane2.addTab("tab2", jPanel9);

                        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

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

                        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                        jPanel10.setLayout(jPanel10Layout);
                        jPanel10Layout.setHorizontalGroup(
                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                        );
                        jPanel10Layout.setVerticalGroup(
                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                        );

                        jTabbedPane2.addTab("tab2", jPanel10);

                        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

                        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel16.setText("No se hallaron coincidencias");

                        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
                        jLabel17.setText(":(");

                        jPanel12.setBackground(new java.awt.Color(153, 153, 153));

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

                        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                        jPanel12.setLayout(jPanel12Layout);
                        jPanel12Layout.setHorizontalGroup(
                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(btnNuevo1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addContainerGap(45, Short.MAX_VALUE)
                                .addComponent(jLabel18)
                                .addGap(44, 44, 44))
                        );
                        jPanel12Layout.setVerticalGroup(
                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(btnNuevo1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                        jPanel11.setLayout(jPanel11Layout);
                        jPanel11Layout.setHorizontalGroup(
                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );
                        jPanel11Layout.setVerticalGroup(
                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel17))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel16))
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        jTabbedPane2.addTab("tab3", jPanel11);

                        BHC.getContentPane().add(jTabbedPane2);
                        jTabbedPane2.setBounds(0, 120, 760, 220);

                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                        buscartodo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

                        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel8.setText("DNI");

                        jLabel50.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                        jLabel50.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-96.png"))); // NOI18N
                        jLabel50.setText("Busqueda de historial por clientes ");

                        resumen.setBackground(new java.awt.Color(255, 255, 255));
                        resumen.setForeground(new java.awt.Color(102, 102, 102));

                        jLabel41.setBackground(new java.awt.Color(204, 204, 204));
                        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel41.setText("Apellidos y Nombres");

                        jLabel44.setBackground(new java.awt.Color(204, 204, 204));
                        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel44.setText("DNI");

                        ACTM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                        ACTM.setForeground(new java.awt.Color(102, 102, 102));

                        APENOM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        APENOM.setForeground(new java.awt.Color(102, 102, 102));

                        jLabel45.setBackground(new java.awt.Color(204, 204, 204));
                        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel45.setText("Total de Abonos");

                        tta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                        tta.setForeground(new java.awt.Color(102, 102, 102));

                        javax.swing.GroupLayout resumenLayout = new javax.swing.GroupLayout(resumen);
                        resumen.setLayout(resumenLayout);
                        resumenLayout.setHorizontalGroup(
                            resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(resumenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel41))
                                .addGap(28, 28, 28)
                                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(APENOM)
                                    .addGroup(resumenLayout.createSequentialGroup()
                                        .addComponent(ACTM)
                                        .addGap(232, 232, 232)
                                        .addComponent(jLabel45)
                                        .addGap(28, 28, 28)
                                        .addComponent(tta)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        resumenLayout.setVerticalGroup(
                            resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resumenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(resumenLayout.createSequentialGroup()
                                        .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ACTM)
                                            .addComponent(tta)
                                            .addComponent(jLabel45))
                                        .addGap(14, 14, 14)
                                        .addComponent(APENOM))
                                    .addGroup(resumenLayout.createSequentialGroup()
                                        .addComponent(jLabel44)
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel41)))
                                .addContainerGap())
                        );

                        jScrollPane4.setBorder(null);

                        resumenes.setModel(new javax.swing.table.DefaultTableModel(
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
                        resumenes.setGridColor(new java.awt.Color(255, 255, 255));
                        resumenes.setRowHeight(25);
                        resumenes.setSelectionBackground(new java.awt.Color(0, 153, 153));
                        resumenes.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                resumenesMouseClicked(evt);
                            }
                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                resumenesMousePressed(evt);
                            }
                        });
                        resumenes.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                resumenesKeyPressed(evt);
                            }
                        });
                        jScrollPane4.setViewportView(resumenes);

                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                        jPanel2.setLayout(jPanel2Layout);
                        jPanel2Layout.setHorizontalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel8)
                                                .addGap(26, 26, 26)
                                                .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 264, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(resumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel50)
                                .addGap(18, 18, 18)
                                .addComponent(resumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                        );

                        jTabbedPane1.addTab("Listado", jPanel2);

                        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                        codpago.setBackground(new java.awt.Color(255, 255, 255));
                        codpago.setForeground(new java.awt.Color(255, 255, 255));
                        codpago.setText("jLabel8");

                        jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel51.setText("Datos del Cliente_________________________________________________________________________________________________________");

                        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                        txtdni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        txtdni.setForeground(new java.awt.Color(102, 102, 102));
                        txtdni.setBorder(null);
                        txtdni.setEnabled(false);

                        txtape.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        txtape.setForeground(new java.awt.Color(102, 102, 102));
                        txtape.setBorder(null);
                        txtape.setEnabled(false);
                        txtape.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtapeActionPerformed(evt);
                            }
                        });

                        txtdir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        txtdir.setForeground(new java.awt.Color(102, 102, 102));
                        txtdir.setBorder(null);
                        txtdir.setEnabled(false);

                        txthc.setEditable(false);
                        txthc.setBorder(null);
                        txthc.setEnabled(false);
                        txthc.setMinimumSize(new java.awt.Dimension(2, 22));
                        txthc.setPreferredSize(new java.awt.Dimension(2, 22));
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

                        jLabel3.setText("H. C.");

                        jLabel11.setText("DNI");

                        jLabel7.setText("Apellidos y Nombres");

                        jLabel12.setText("Dirección ");

                        lblhc.setForeground(new java.awt.Color(255, 255, 255));
                        lblhc.setText("jLabel10");

                        idac.setForeground(new java.awt.Color(255, 255, 255));
                        idac.setText("jLabel14");

                        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                        jPanel5.setLayout(jPanel5Layout);
                        jPanel5Layout.setHorizontalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txthc, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(lblhc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idac)))
                                .addContainerGap(20, Short.MAX_VALUE))
                        );
                        jPanel5Layout.setVerticalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblhc)
                                            .addComponent(idac))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txthc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addGap(34, 34, 34))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                        jPanel6.setLayout(jPanel6Layout);
                        jPanel6Layout.setHorizontalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 86, Short.MAX_VALUE)
                        );
                        jPanel6Layout.setVerticalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
                        );

                        jLabel6.setText("Huella");

                        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                        jPanel4.setLayout(jPanel4Layout);
                        jPanel4Layout.setHorizontalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel4Layout.setVerticalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );

                        nomenc.setForeground(new java.awt.Color(255, 255, 255));
                        nomenc.setText("jLabel14");

                        abonodet.setBackground(new java.awt.Color(255, 255, 255));

                        jLabel52.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                        jLabel52.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel52.setText("Datos del Abono_________________________________________________________________________________________________________");

                        jLabel2.setText("Nª Abono");

                        nid.setEditable(false);
                        nid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        nid.setForeground(new java.awt.Color(102, 102, 102));
                        nid.setBorder(null);

                        jLabel10.setText("Nª Documento");

                        docu.setEditable(false);
                        docu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        docu.setForeground(new java.awt.Color(102, 102, 102));
                        docu.setBorder(null);

                        jLabel4.setText("Nomenclatura");

                        des.setEditable(false);
                        des.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        des.setForeground(new java.awt.Color(102, 102, 102));
                        des.setBorder(null);
                        des.setEnabled(false);

                        jLabel5.setText("Monto");

                        abono.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
                        abono.setEnabled(false);
                        abono.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                abonoCaretUpdate(evt);
                            }
                        });
                        abono.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                abonoActionPerformed(evt);
                            }
                        });
                        abono.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                abonoKeyPressed(evt);
                            }
                        });

                        Total.setText("Total");

                        totales.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        totales.setForeground(new java.awt.Color(51, 51, 51));

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

                        verif.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {
                                {},
                                {},
                                {},
                                {}
                            },
                            new String [] {

                            }
                        ));
                        verif.setGridColor(new java.awt.Color(255, 255, 255));
                        verif.setRowHeight(25);
                        verif.setSelectionBackground(new java.awt.Color(0, 153, 153));
                        verif.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                verifMouseClicked(evt);
                            }
                        });
                        verif.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                verifKeyPressed(evt);
                            }
                        });
                        jScrollPane10.setViewportView(verif);

                        javax.swing.GroupLayout abonodetLayout = new javax.swing.GroupLayout(abonodet);
                        abonodet.setLayout(abonodetLayout);
                        abonodetLayout.setHorizontalGroup(
                            abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abonodetLayout.createSequentialGroup()
                                .addComponent(Total)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(abonodetLayout.createSequentialGroup()
                                .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addGroup(abonodetLayout.createSequentialGroup()
                                        .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel2))
                                        .addGap(54, 54, 54)
                                        .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(abonodetLayout.createSequentialGroup()
                                                    .addComponent(nid, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel10)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(docu, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(des, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(abono, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totales, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abonodetLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(abonodetLayout.createSequentialGroup()
                                    .addGap(820, 820, 820)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(34, Short.MAX_VALUE)))
                        );
                        abonodetLayout.setVerticalGroup(
                            abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abonodetLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel52)
                                .addGap(27, 27, 27)
                                .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(nid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(docu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(des, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(abono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Total)
                                    .addComponent(totales))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(abonodetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(abonodetLayout.createSequentialGroup()
                                    .addGap(61, 61, 61)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(31, Short.MAX_VALUE)))
                        );

                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                        jPanel3.setLayout(jPanel3Layout);
                        jPanel3Layout.setHorizontalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(868, 868, 868)
                                        .addComponent(codpago))
                                    .addComponent(jLabel51)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(576, 576, 576)
                                        .addComponent(nomenc))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(abonodet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(12, Short.MAX_VALUE))
                        );
                        jPanel3Layout.setVerticalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel51)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addComponent(abonodet, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(codpago)
                                .addGap(78, 78, 78)
                                .addComponent(nomenc)
                                .addGap(49, 49, 49))
                        );

                        jTabbedPane1.addTab("Edicion", jPanel3);

                        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

                        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel1.setText("Abono");

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
                                        .addGap(10, 10, 10)
                                        .addComponent(lblusu))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
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
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btneliminar)
                                        .addComponent(btnbuscar))
                                    .addComponent(btnNuevo)
                                    .addComponent(btnguardar))
                                .addGap(552, 552, 552))
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
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTabbedPane1)
                                .addContainerGap())
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
            txthc.setEnabled(true);
            txtape.setEnabled(true);
            txtdir.setEnabled(true);
            txtdni.setEnabled(true);


            txthc.setEditable(false);
            txtape.setEditable(false);
            txtdir.setEditable(false);
            txtdni.setEditable(false);
            
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==2){
            jTabbedPane1.setSelectedIndex(1);
            
            jTabbedPane1.setSelectedIndex(1);
            docu.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            abono.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
        }
        tge=2;
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true); 
        
        txthc.setEnabled(true); 
        abono.setEnabled(true);      
    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed
      
    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed
      txthc.setEnabled(true);
            txtape.setEnabled(true);
            txtdir.setEnabled(true);
            txtdni.setEnabled(true);


            txthc.setEditable(false);
            txtape.setEditable(false);
            txtdir.setEditable(false);
            txtdni.setEditable(false);
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();
            
            jTabbedPane1.setSelectedIndex(1);
         
            docu.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            abono.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            
          
        }
        tge=2;
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true); 
        
        txthc.setEnabled(true); 
        abono.setEnabled(true);      
     
        
    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
            Mresumen();
            sumar();
        if (buscartodo.getText()!=""){
            resumen.setVisible(true);
            jScrollPane4.setVisible(true);
            resumenes.setVisible(true);
            jLabel50.setVisible(false);
            resumenes.getSelectionModel().setSelectionInterval (0,0) ;
            
            
            
            
            /////////////////////////CABECERA 
            
                if(this.resumenes.getRowCount()!=0  ){
                    int fila = resumenes.getSelectedRow();
                    ACTM.setText(String.valueOf(resumenes.getValueAt(fila, 5)));
                    APENOM.setText(String.valueOf(resumenes.getValueAt(fila, 6)));
                    Mresumen();
                }     
                if(this.resumenes.getRowCount()==0  ){ 
                
                }
            

           
            ////////////////////////////////////////
          
            
        }if (buscartodo.getText().length()==0){
            resumen.setVisible(false);
            jLabel50.setVisible(true);
            jScrollPane4.setVisible(false);
            resumenes.setVisible(false);
            
        }  

    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        jTabbedPane1.setSelectedIndex(1);
        tg=1;
        
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true); 
        txthc.setText("");
        txtdni.setText("");
        txtape.setText("");
        txtdir.setText("");
        nid.setText("");
        docu.setText("");
        des.setText("");
        abono.setText("");
        idac.setText("");
        lblhc.setText("");
        totales.setText("");
        
        
        txtdni.setEnabled(false); 
        txtape.setEnabled(false); 
        txtdir.setEnabled(false); 
        nid.setEnabled(true); 
        docu.setEnabled(true); 
        des.setEnabled(true); 
 
        txthc.setEnabled(true); 
        abono.setEnabled(true); 
        BHC.setVisible(true);
        txtBuscar.setText(""); 
        nid.setText(cnn.id());
        nomenc.setText(cnn.cpt());
        des.setText(cnn.nomen(nomenc.getText()));
        docu.setText(cnn.doc());
        cargareliminar.setVisible(false);
           if(docu.getText().equalsIgnoreCase("")&& nid.getText().equalsIgnoreCase("")){
             docu.setText("AC000000000000000000"); 
             nid.setText("0");
             } 

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
             tg=2;
             btnNuevo.setEnabled(true);
             btnguardar.setEnabled(true);
             btneditar.setEnabled(false);
             btneliminar.setEnabled(true);
             btnbuscar.setEnabled(true);
             abono.setEnabled(true);
              abono.setEditable(true);
             abono.requestFocus();
             jTabbedPane1.setSelectedIndex(1);
             abono.requestFocus();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
             Guardar();  
             TotalAbonos();
             suma();
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
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        cargareliminar.setVisible(true);
        cargareliminar.setBackground(new Color(255,91,70)); 
        Mensaje.setText("Desea Eliminar este registro?");
          eli.setText("Si");
          eli.setVisible(true);
          noeli.setVisible(true);
          tge=8;
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        cargareliminar.setVisible(false);       
        jTabbedPane1.setSelectedIndex(0);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        buscartodo.requestFocus();
   
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void txthcCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txthcCaretUpdate
 
    }//GEN-LAST:event_txthcCaretUpdate

    private void txthcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthcMouseClicked
               
    }//GEN-LAST:event_txthcMouseClicked

    private void txthcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthcActionPerformed
       
    }//GEN-LAST:event_txthcActionPerformed

    private void txtapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapeActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        jTabbedPane2.setSelectedIndex(1);
        BuscarHC();
         if (tb_Grupo.getRowCount() == 0){
            jTabbedPane2.setSelectedIndex(2);
            }
          if (txtBuscar.getText().length()==0){
             jTabbedPane2.setSelectedIndex(0);
        }

    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked

        int fila=tb_Grupo.getSelectedRow();
   
      
        if(evt.getClickCount()==2){
            BHC.dispose();
            btnguardar.setEnabled(true);
            txthc.setEnabled(true);
            txtape.setEnabled(true);
            txtdir.setEnabled(true);
            txtdni.setEnabled(true);
            
            txthc.setEditable(false);
            txtape.setEditable(false);
            txtdir.setEditable(false);
            txtdni.setEditable(false);

            txthc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            txtdir.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
            txtdni.setText(String.valueOf(tb_Grupo.getValueAt(fila, 3)));

            lblhc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 7)));
            
            nid.setEnabled(true); 
            docu.setEnabled(true); 
            des.setEnabled(true); 

            //////////////////////////////////CARGAR - DETALLE  
           try{
            Verificar();
            if(this.verif.getRowCount()!=0  ){
                Verificar();
                cargar();
                abono.requestFocus();
          }     
            if(this.verif.getRowCount()==0  ){ 
                GuardarC();  
                abono.requestFocus();
             }
            } catch (Exception e) {
            }
                    
 
            ////////////////////////////////////////////

//            Verificar();
//             try{
//            if(this.verificar.getRowCount()!=0  ){
//                Verificar();
//                idac.setText(String.valueOf(verificar.getValueAt(fila, 0)));
//          }
//            if(this.verificar.getRowCount()==0  ){ 
//                
//                GuardarC();
//                Verificar();
//                idac.setText(String.valueOf(verificar.getValueAt(fila, 0)));
//             }
//            
//            abono.setEnabled(true);
//            abono.requestFocus();
//            abonodet.setVisible(true);
//            }catch(Exception e){
//            System.out.println("Error: " + e.toString());
//        }
//           
        }
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
                  int fila=tb_Grupo.getSelectedRow();
   
            BHC.dispose();
            btnguardar.setEnabled(true);
            txthc.setEnabled(true);
            txtape.setEnabled(true);
            txtdir.setEnabled(true);
            txtdni.setEnabled(true);
            
            txthc.setEditable(false);
            txtape.setEditable(false);
            txtdir.setEditable(false);
            txtdni.setEditable(false);

            txthc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            txtdir.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
            txtdni.setText(String.valueOf(tb_Grupo.getValueAt(fila, 3)));

            lblhc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 7)));
            
            nid.setEnabled(true); 
            docu.setEnabled(true); 
            des.setEnabled(true); 

            //////////////////////////////////CARGAR - DETALLE  
            try{
            Verificar();
            if(this.verif.getRowCount()!=0  ){
                Verificar();
                cargar();
                abono.requestFocus();
          }     
            if(this.verif.getRowCount()==0  ){ 
                GuardarC(); 
                abono.requestFocus();
             }
            } catch (Exception e) {
            }
                    
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

    private void verifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verifMouseClicked
      
    }//GEN-LAST:event_verifMouseClicked

    private void verifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verifKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_verifKeyPressed

    private void T3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T3MouseClicked

    }//GEN-LAST:event_T3MouseClicked

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
          char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
   if (tge==3 || tge==1 || tge==9){
   cargareliminar.setVisible(false);  
   buscartodo.setText(txtdni.getText());
   jTabbedPane1.setSelectedIndex(0);
   
    
   }
   if (tge==2){
     Modificar();
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true); 
        
        txthc.setEnabled(true); 
        abono.setEnabled(false);  
        
    
   }     
    if (tge==8){
        Eliminar();
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true); 
        
        
        abono.setEnabled(false);  
        
    
   }   
      
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void abonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_abonoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
             if(tg==1){
             Guardar();  
             TotalAbonos();
             suma();
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
    }//GEN-LAST:event_abonoKeyPressed

    private void resumenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resumenesMouseClicked
       int fila=resumenes.getSelectedRow();
   if(evt.getClickCount()==2){
       jTabbedPane1.setSelectedIndex(1);
       txthc.setText(String.valueOf(resumenes.getValueAt(fila, 13)));  
       txtdni.setText(String.valueOf(resumenes.getValueAt(fila, 5))); 
       txtape.setText(String.valueOf(resumenes.getValueAt(fila, 6)));
       txtdir.setText(String.valueOf(resumenes.getValueAt(fila, 14)));  
       nid.setText(String.valueOf(resumenes.getValueAt(fila, 10))); 
       docu.setText(String.valueOf(resumenes.getValueAt(fila, 0)));
       des.setText(String.valueOf(resumenes.getValueAt(fila, 4)));  
       abono.setText(String.valueOf(resumenes.getValueAt(fila, 1))); 
       totales.setText(tta.getText());

   } 
    if(evt.getClickCount()==1){
       txthc.setText(String.valueOf(resumenes.getValueAt(fila, 13)));  
       txtdni.setText(String.valueOf(resumenes.getValueAt(fila, 5))); 
       txtape.setText(String.valueOf(resumenes.getValueAt(fila, 6)));
       txtdir.setText(String.valueOf(resumenes.getValueAt(fila, 14)));  
       nid.setText(String.valueOf(resumenes.getValueAt(fila, 10))); 
       docu.setText(String.valueOf(resumenes.getValueAt(fila, 0)));
       des.setText(String.valueOf(resumenes.getValueAt(fila, 4)));  
       abono.setText(String.valueOf(resumenes.getValueAt(fila, 1))); 
       totales.setText(tta.getText());

   } 
 
            txthc.setEnabled(true);
            txtape.setEnabled(true);
            txtdir.setEnabled(true);
            txtdni.setEnabled(true);
            nid.setEnabled(true);
            docu.setEnabled(true);
            des.setEnabled(true);
            txthc.setEditable(false);
            txtape.setEditable(false);
            txtdir.setEditable(false);
            txtdni.setEditable(false);
            nid.setEditable(false);
            docu.setEditable(false);
            des.setEditable(false);
            abono.setEditable(false);
    btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        

    }//GEN-LAST:event_resumenesMouseClicked

    private void resumenesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resumenesMousePressed

    }//GEN-LAST:event_resumenesMousePressed

    private void resumenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resumenesKeyPressed

    }//GEN-LAST:event_resumenesKeyPressed

    private void buscartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscartodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscartodoActionPerformed

    private void abonoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_abonoCaretUpdate
           
    }//GEN-LAST:event_abonoCaretUpdate

    private void abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abonoActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
           char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }    
    }//GEN-LAST:event_txtBuscarKeyTyped

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
            java.util.logging.Logger.getLogger(Caja_Abono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Abono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Abono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Abono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Abono().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ACTM;
    private javax.swing.JLabel APENOM;
    private javax.swing.JDialog BHC;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JLabel T3;
    private javax.swing.JLabel Total;
    private javax.swing.JTextField abono;
    private javax.swing.JPanel abonodet;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JLabel codpago;
    private javax.swing.JTextField des;
    private javax.swing.JTextField docu;
    private javax.swing.JButton eli;
    private javax.swing.JLabel idac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblhc;
    private javax.swing.JLabel lblusu;
    private javax.swing.JTextField nid;
    private javax.swing.JButton noeli;
    private javax.swing.JLabel nomenc;
    private javax.swing.JPanel resumen;
    private javax.swing.JTable resumenes;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JLabel totales;
    private javax.swing.JLabel tta;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtape;
    private javax.swing.JTextField txtdir;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txthc;
    private javax.swing.JTable verif;
    // End of variables declaration//GEN-END:variables
}
