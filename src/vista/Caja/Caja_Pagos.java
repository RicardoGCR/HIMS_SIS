/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import java.awt.AWTException;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import modelos.Caja.Caja_DetallePreventas;
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
//ManejadorTecla manejador = new ManejadorTecla();

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
         
         preventas.setLocationRelativeTo(null);//en el centro
         preventas.getContentPane().setBackground(Color.WHITE);
         
         Anular.setLocationRelativeTo(null);//en el centro
         Anular.getContentPane().setBackground(Color.WHITE);

         
         ///////////////////
         //txtfp.addKeyListener(manejador);
         //////////////////
          
        
             
             quitar.setUndecorated(true);
             quitar.setLocationRelativeTo(null);//en el centro
         
         
         
         ///NO MOSTRAR
         //////////////////////////
          //preventa.setVisible(false);
          separar.setVisible(false);
          bu.setVisible(false);
          Nomen2.setVisible(false);
          b4.setVisible(false);
          nuevoanulacion.setVisible(false);
          preventa.setVisible(false);
          
          jScrollPane4.setVisible(false);
          b5.setVisible(false);
          jPanel20.setVisible(false);
          btnNuevo.setEnabled(true);
          btnguardar.setEnabled(false);
          btneditar.setEnabled(false);
          btneliminar.setEnabled(false);
          btnbuscar.setEnabled(true);
          btnimprimir.setEnabled(false);
          CARGAR.setVisible(false);
          CARGAR1.setVisible(false);
          
          ////NO SE MUESTRA PRE VENTA
          btnbuscar4.setVisible(false);
          btnbuscar6.setVisible(false);
          btnbuscar9.setVisible(false);
          btnbuscar10.setVisible(false);
          jScrollPane14.setVisible(false);
          cargareliminar.setVisible(false);
          //////////////////////////
           
               
         LISTAR();
         LISTARN();
         formatoj();
         if (txtBuscar.getText() == " "){
             jTabbedPane2.setSelectedIndex(0);
        }
        
 
   
       this.cbxtipo.setModel(tipo());
       this.CBXANULAR.setModel(anular());
      

    }
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/iconos/Caja fuerte-96.png"));


        return retValue;
    }
    
//private class ManejadorTecla implements KeyListener{
//public void keyPressed(KeyEvent e) {
//if(e.getKeyCode()==KeyEvent.VK_ENTER){
//Robot Tecla = null;
//try {
//Tecla = new Robot();
//} catch (AWTException e1) {
//e1.printStackTrace();
//}
//Tecla.keyPress(KeyEvent.VK_TAB);
//}
//}
//public void keyReleased(KeyEvent arg0) {}
//public void keyTyped(KeyEvent arg0) {}
//
//}//Fin de la clase privada ManejadorTecla()
    
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
    
    
        public DefaultComboBoxModel anular(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("Caja_Anulacion_Des"); 
//              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "descripcion_anulacion" ) );                
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
            formatocpt();
            
    } catch (Exception e) {
    }
}  
   
        public void Preventamostrar(){
        String consulta="";
        try {
            tb_Grupo5.setModel(new DefaultTableModel());
         
             String titulos[]={"Nº Preventa","ID_H.C.","Modulo","CodNomen","Nomenclatura","Descripcion","Medico","Fecha","Hora"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_Verificar_PreVenta ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, bus.getText());
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
            tb_Grupo5.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo5.setRowSorter(elQueOrdena);
            this.tb_Grupo5.setModel(m);
            
        } catch (Exception e) {
            System.out.println("Error preventa: " + e.getMessage());
        }
      }
        
    public void PreventaEME(){
        String consulta="";
        try {
            tEME.setModel(new DefaultTableModel());
         
             String titulos[]={"Nº","DNI","H.C.","Cliente","Modulo","Medico","Fecha","Hora","id","estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_Mostrar_Preventas ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txthc.getText());
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
            tEME.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tEME.setRowSorter(elQueOrdena);
            this.tEME.setModel(m);
            formatoEME();
        } catch (Exception e) {
            System.out.println("Error preventa: " + e.getMessage());
        }
      }
    
    public void PreventaEMEDET(){
        String consulta="";
        try {
            tEMEDET.setModel(new DefaultTableModel());
         
             String titulos[]={"CPT","Descripcion","Precio","Fecha","Hora","cod","idDp","ESTADO","IDP"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_Mostrar_DETALLE_Preventas ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, jLabel34.getText());
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
            tEMEDET.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tEMEDET.setRowSorter(elQueOrdena);
            this.tEMEDET.setModel(m);
            formatoEMEDET();
        } catch (Exception e) {
            System.out.println("Error preventa detalleeeeeeeeeeeeeeeeee: " + e.getMessage());
        }
      }
    
    public void BuscarHC(){
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
                    consulta="exec Caja_NomenclaturaVentaBUSCAR ?,?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar2.getText());
            cmd.setString(2, txtfp.getText());
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


    }
    
    public void formatoEME(){
    tEME.getColumnModel().getColumn(0).setPreferredWidth(50);
    tEME.getColumnModel().getColumn(1).setPreferredWidth(100);
    tEME.getColumnModel().getColumn(2).setPreferredWidth(80);
    tEME.getColumnModel().getColumn(3).setPreferredWidth(350);
    tEME.getColumnModel().getColumn(4).setPreferredWidth(60);
    tEME.getColumnModel().getColumn(6).setPreferredWidth(90);
    tEME.getColumnModel().getColumn(7).setPreferredWidth(80);

    tEME.getColumnModel().getColumn(8).setMinWidth(0);
    tEME.getColumnModel().getColumn(8).setMaxWidth(0);
    tEME.getColumnModel().getColumn(9).setMinWidth(0);
    tEME.getColumnModel().getColumn(9).setMaxWidth(0);
    }
    
    public void formatoEMEDET(){
    tEMEDET.getColumnModel().getColumn(0).setPreferredWidth(100);
    tEMEDET.getColumnModel().getColumn(1).setPreferredWidth(300);
    tEMEDET.getColumnModel().getColumn(2).setPreferredWidth(80);
    tEMEDET.getColumnModel().getColumn(3).setPreferredWidth(80);
    tEMEDET.getColumnModel().getColumn(4).setPreferredWidth(80);
    
    tEMEDET.getColumnModel().getColumn(5).setMinWidth(0);
    tEMEDET.getColumnModel().getColumn(5).setMaxWidth(0);
    tEMEDET.getColumnModel().getColumn(6).setMinWidth(0);
    tEMEDET.getColumnModel().getColumn(6).setMaxWidth(0);
    tEMEDET.getColumnModel().getColumn(7).setMinWidth(0);
    tEMEDET.getColumnModel().getColumn(7).setMaxWidth(0);
    tEMEDET.getColumnModel().getColumn(8).setMinWidth(0);
    tEMEDET.getColumnModel().getColumn(8).setMaxWidth(0);
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
        jLabel13 = new javax.swing.JLabel();
        bus = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        T3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_Grupo5 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
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
                    jPanel28 = new javax.swing.JPanel();
                    txtBuscar2 = new javax.swing.JTextField();
                    T4 = new javax.swing.JLabel();
                    jLabel45 = new javax.swing.JLabel();
                    jComboBox1 = new javax.swing.JComboBox();
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
                        preventas = new javax.swing.JDialog();
                        jPanel18 = new javax.swing.JPanel();
                        jLabel29 = new javax.swing.JLabel();
                        jLabel35 = new javax.swing.JLabel();
                        jLabel37 = new javax.swing.JLabel();
                        jLabel38 = new javax.swing.JLabel();
                        jLabel39 = new javax.swing.JLabel();
                        btnbuscar4 = new javax.swing.JButton();
                        btnbuscar6 = new javax.swing.JButton();
                        btnbuscar9 = new javax.swing.JButton();
                        btnbuscar10 = new javax.swing.JButton();
                        jLabel34 = new javax.swing.JLabel();
                        elimdp = new javax.swing.JLabel();
                        jTabbedPane5 = new javax.swing.JTabbedPane();
                        jPanel22 = new javax.swing.JPanel();
                        jScrollPane13 = new javax.swing.JScrollPane();
                        tEME = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jScrollPane14 = new javax.swing.JScrollPane();
                            tEMEDET = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                CARGAR = new javax.swing.JButton();
                                CARGAR1 = new javax.swing.JButton();
                                cargareliminar = new javax.swing.JPanel();
                                Mensaje = new javax.swing.JLabel();
                                eli = new javax.swing.JButton();
                                noeli = new javax.swing.JButton();
                                jPanel23 = new javax.swing.JPanel();
                                jScrollPane15 = new javax.swing.JScrollPane();
                                tpreventa7 = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    jScrollPane16 = new javax.swing.JScrollPane();
                                    tpreventa8 = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};
                                        btnbuscar13 = new javax.swing.JButton();
                                        jPanel24 = new javax.swing.JPanel();
                                        jScrollPane17 = new javax.swing.JScrollPane();
                                        tpreventa9 = new javax.swing.JTable(){
                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                return false; //Disallow the editing of any cell
                                            }};
                                            jScrollPane18 = new javax.swing.JScrollPane();
                                            tpreventa10 = new javax.swing.JTable(){
                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                    return false; //Disallow the editing of any cell
                                                }};
                                                btnbuscar14 = new javax.swing.JButton();
                                                jPanel25 = new javax.swing.JPanel();
                                                jScrollPane19 = new javax.swing.JScrollPane();
                                                tpreventa11 = new javax.swing.JTable(){
                                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                                        return false; //Disallow the editing of any cell
                                                    }};
                                                    jScrollPane20 = new javax.swing.JScrollPane();
                                                    tpreventa12 = new javax.swing.JTable(){
                                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                                            return false; //Disallow the editing of any cell
                                                        }};
                                                        btnbuscar15 = new javax.swing.JButton();
                                                        Anular = new javax.swing.JDialog();
                                                        jPanel19 = new javax.swing.JPanel();
                                                        jLabel31 = new javax.swing.JLabel();
                                                        btnbuscar7 = new javax.swing.JButton();
                                                        btnbuscar8 = new javax.swing.JButton();
                                                        nuevoanulacion = new javax.swing.JPanel();
                                                        jLabel33 = new javax.swing.JLabel();
                                                        txtanular = new javax.swing.JTextField();
                                                        lblcodanu = new javax.swing.JLabel();
                                                        jPanel21 = new javax.swing.JPanel();
                                                        jLabel32 = new javax.swing.JLabel();
                                                        CBXANULAR = new javax.swing.JComboBox();
                                                        jDialog1 = new javax.swing.JDialog();
                                                        jPanel26 = new javax.swing.JPanel();
                                                        jLabel40 = new javax.swing.JLabel();
                                                        btnbuscar11 = new javax.swing.JButton();
                                                        btnbuscar12 = new javax.swing.JButton();
                                                        jPanel1 = new javax.swing.JPanel();
                                                        jLabel1 = new javax.swing.JLabel();
                                                        btnNuevo = new javax.swing.JButton();
                                                        btneditar = new javax.swing.JButton();
                                                        btnguardar = new javax.swing.JButton();
                                                        btneliminar = new javax.swing.JButton();
                                                        btnbuscar = new javax.swing.JButton();
                                                        lblusu = new javax.swing.JLabel();
                                                        btneliminar1 = new javax.swing.JButton();
                                                        btnimprimir = new javax.swing.JButton();
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
                                                            fp = new javax.swing.JLabel();
                                                            jLabel51 = new javax.swing.JLabel();
                                                            nom = new javax.swing.JLabel();
                                                            jLabel6 = new javax.swing.JLabel();
                                                            jLabel7 = new javax.swing.JLabel();
                                                            txtape = new javax.swing.JTextField();
                                                            jLabel10 = new javax.swing.JLabel();
                                                            jLabel11 = new javax.swing.JLabel();
                                                            jLabel12 = new javax.swing.JLabel();
                                                            txtdir = new javax.swing.JTextField();
                                                            txtdni = new javax.swing.JTextField();
                                                            txtedad = new javax.swing.JTextField();
                                                            jLabel14 = new javax.swing.JLabel();
                                                            sexo = new javax.swing.JTextField();
                                                            lblcodigo = new javax.swing.JLabel();
                                                            lblhc = new javax.swing.JLabel();
                                                            cbxtipo = new javax.swing.JComboBox();
                                                            b = new javax.swing.JButton();
                                                            b2 = new javax.swing.JButton();
                                                            separar = new javax.swing.JLabel();
                                                            bu = new javax.swing.JLabel();
                                                            Nomen2 = new javax.swing.JTextField();
                                                            b4 = new javax.swing.JButton();
                                                            jScrollPane4 = new javax.swing.JScrollPane();
                                                            tb_Grupo2 = new javax.swing.JTable(){
                                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                                    return false; //Disallow the editing of any cell
                                                                }};
                                                                b5 = new javax.swing.JButton();
                                                                jPanel20 = new javax.swing.JPanel();
                                                                jLabel26 = new javax.swing.JLabel();
                                                                total = new javax.swing.JTextField();
                                                                preventa = new javax.swing.JPanel();
                                                                jLabel15 = new javax.swing.JLabel();
                                                                btnbuscar5 = new javax.swing.JButton();
                                                                jLabel36 = new javax.swing.JLabel();
                                                                jLabel30 = new javax.swing.JLabel();

                                                                BHC.setAlwaysOnTop(true);
                                                                BHC.setMinimumSize(new java.awt.Dimension(749, 338));
                                                                BHC.setResizable(false);
                                                                BHC.getContentPane().setLayout(null);

                                                                jPanel7.setBackground(new java.awt.Color(0, 153, 153));
                                                                jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel19.setText("Paciente");

                                                                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                jLabel13.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel13.setText("Busqueda por DNI, H.C. y Apellidos");

                                                                bus.setForeground(new java.awt.Color(0, 153, 153));
                                                                bus.setText("jLabel37");

                                                                jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                                                                txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                txtBuscar.setBorder(null);
                                                                txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                                                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                        txtBuscarCaretUpdate(evt);
                                                                    }
                                                                });
                                                                txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        txtBuscarMouseClicked(evt);
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
                                                                        .addGap(2, 2, 2)
                                                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, 0)
                                                                        .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, 0))
                                                                );
                                                                jPanel27Layout.setVerticalGroup(
                                                                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                                                        .addGap(2, 2, 2)
                                                                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(T3)
                                                                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                );

                                                                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                                                                jPanel7.setLayout(jPanel7Layout);
                                                                jPanel7Layout.setHorizontalGroup(
                                                                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel19)
                                                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addComponent(jLabel13)
                                                                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(39, 39, 39)
                                                                                .addComponent(bus)))
                                                                        .addContainerGap(425, Short.MAX_VALUE))
                                                                );
                                                                jPanel7Layout.setVerticalGroup(
                                                                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(bus))
                                                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(jLabel19)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(8, 8, 8)
                                                                                .addComponent(jLabel13)))
                                                                        .addGap(331, 331, 331))
                                                                );

                                                                BHC.getContentPane().add(jPanel7);
                                                                jPanel7.setBounds(0, 0, 780, 120);

                                                                jPanel8.setBackground(new java.awt.Color(255, 255, 255));

                                                                tb_Grupo5.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tb_Grupo5.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tb_Grupo5.setRowHeight(25);
                                                                tb_Grupo5.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                                tb_Grupo5.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tb_Grupo5MouseClicked(evt);
                                                                    }
                                                                });
                                                                tb_Grupo5.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tb_Grupo5KeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane9.setViewportView(tb_Grupo5);

                                                                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                                                jPanel8.setLayout(jPanel8Layout);
                                                                jPanel8Layout.setHorizontalGroup(
                                                                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                                                        .addGap(35, 35, 35)
                                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addContainerGap(332, Short.MAX_VALUE))
                                                                );
                                                                jPanel8Layout.setVerticalGroup(
                                                                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                                        .addContainerGap(30, Short.MAX_VALUE)
                                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addContainerGap())
                                                                );

                                                                BHC.getContentPane().add(jPanel8);
                                                                jPanel8.setBounds(0, 310, 750, 70);

                                                                jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                                                                jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                                                jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                                                                jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Silla de ruedas-100 (2).png"))); // NOI18N
                                                                jLabel9.setText("Busqueda de Pacientes ");

                                                                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                                                jPanel4.setLayout(jPanel4Layout);
                                                                jPanel4Layout.setHorizontalGroup(
                                                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                                        .addGap(134, 134, 134)
                                                                        .addComponent(jLabel9)
                                                                        .addContainerGap(160, Short.MAX_VALUE))
                                                                );
                                                                jPanel4Layout.setVerticalGroup(
                                                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                                        .addGap(62, 62, 62)
                                                                        .addComponent(jLabel9)
                                                                        .addContainerGap(30, Short.MAX_VALUE))
                                                                );

                                                                jTabbedPane2.addTab("tab2", jPanel4);

                                                                jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                                                                jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
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

                                                                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                                                jPanel5.setLayout(jPanel5Layout);
                                                                jPanel5Layout.setHorizontalGroup(
                                                                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                                                                        .addContainerGap())
                                                                );
                                                                jPanel5Layout.setVerticalGroup(
                                                                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
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
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
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
                                                                jTabbedPane2.setBounds(0, 120, 760, 220);

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
                                                                txtBuscar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
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

                                                                jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

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
                                                                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(T4)
                                                                            .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                );

                                                                jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                jLabel45.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel45.setText("Buscar por otra forma de pago");

                                                                jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                                                                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                                                                jPanel11.setLayout(jPanel11Layout);
                                                                jPanel11Layout.setHorizontalGroup(
                                                                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel21)
                                                                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel45)
                                                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addContainerGap(183, Short.MAX_VALUE))
                                                                );
                                                                jPanel11Layout.setVerticalGroup(
                                                                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                                                .addComponent(jLabel45)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                                                .addComponent(jLabel21)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addGap(353, 353, 353))
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
                                                                        .addContainerGap(76, Short.MAX_VALUE))
                                                                );

                                                                jTabbedPane3.addTab("tab2", jPanel13);

                                                                jPanel14.setBackground(new java.awt.Color(255, 255, 255));

                                                                jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));

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
                                                                    .addGap(0, 235, Short.MAX_VALUE)
                                                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
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
                                                                        .addContainerGap(46, Short.MAX_VALUE))
                                                                );

                                                                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                                                                jPanel15.setLayout(jPanel15Layout);
                                                                jPanel15Layout.setHorizontalGroup(
                                                                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(jLabel24)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jLabel23)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                );
                                                                jPanel15Layout.setVerticalGroup(
                                                                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                                                .addGap(86, 86, 86)
                                                                                .addComponent(jLabel23))
                                                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                                                .addGap(39, 39, 39)
                                                                                .addComponent(jLabel24)))
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                );

                                                                jTabbedPane3.addTab("tab3", jPanel15);

                                                                nomenclaturas.getContentPane().add(jTabbedPane3);
                                                                jTabbedPane3.setBounds(0, 98, 749, 240);

                                                                quitar.setAlwaysOnTop(true);
                                                                quitar.setIconImage(null);
                                                                quitar.setMinimumSize(new java.awt.Dimension(357, 156));
                                                                quitar.setModal(true);
                                                                quitar.setResizable(false);

                                                                jPanel17.setBackground(new java.awt.Color(255, 91, 70));

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

                                                                preventas.setAlwaysOnTop(true);
                                                                preventas.setMinimumSize(new java.awt.Dimension(770, 422));
                                                                preventas.setResizable(false);

                                                                jPanel18.setBackground(new java.awt.Color(0, 153, 102));
                                                                jPanel18.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                jLabel29.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                jLabel29.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel29.setText("Preventa");

                                                                jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                jLabel35.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel35.setText("Emergencia");
                                                                jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                                                                jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                jLabel37.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel37.setText("Hospitalicacion");
                                                                jLabel37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                                                                jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                jLabel38.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel38.setText("SIS");
                                                                jLabel38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                                                                jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                jLabel39.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel39.setText("Sala de Operaciones");
                                                                jLabel39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                                                                btnbuscar4.setForeground(new java.awt.Color(102, 102, 102));
                                                                btnbuscar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Luna nueva Filled-20.png"))); // NOI18N
                                                                btnbuscar4.setText("00");
                                                                btnbuscar4.setContentAreaFilled(false);
                                                                btnbuscar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                                btnbuscar4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar4.setIconTextGap(30);
                                                                btnbuscar4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar4.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar4ActionPerformed(evt);
                                                                    }
                                                                });

                                                                btnbuscar6.setForeground(new java.awt.Color(102, 102, 102));
                                                                btnbuscar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Luna nueva Filled-20.png"))); // NOI18N
                                                                btnbuscar6.setText("00");
                                                                btnbuscar6.setContentAreaFilled(false);
                                                                btnbuscar6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                                btnbuscar6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar6.setIconTextGap(30);
                                                                btnbuscar6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar6.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar6ActionPerformed(evt);
                                                                    }
                                                                });

                                                                btnbuscar9.setForeground(new java.awt.Color(102, 102, 102));
                                                                btnbuscar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Luna nueva Filled-20.png"))); // NOI18N
                                                                btnbuscar9.setText("00");
                                                                btnbuscar9.setContentAreaFilled(false);
                                                                btnbuscar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                                btnbuscar9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar9.setIconTextGap(30);
                                                                btnbuscar9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar9.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar9ActionPerformed(evt);
                                                                    }
                                                                });

                                                                btnbuscar10.setForeground(new java.awt.Color(102, 102, 102));
                                                                btnbuscar10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Luna nueva Filled-20.png"))); // NOI18N
                                                                btnbuscar10.setText("00");
                                                                btnbuscar10.setContentAreaFilled(false);
                                                                btnbuscar10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                                btnbuscar10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar10.setIconTextGap(30);
                                                                btnbuscar10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar10.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar10ActionPerformed(evt);
                                                                    }
                                                                });

                                                                jLabel34.setForeground(new java.awt.Color(0, 153, 102));
                                                                jLabel34.setText("jLabel34");

                                                                elimdp.setForeground(new java.awt.Color(0, 153, 102));
                                                                elimdp.setText("jLabel40");

                                                                javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                                                                jPanel18.setLayout(jPanel18Layout);
                                                                jPanel18Layout.setHorizontalGroup(
                                                                    jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addComponent(jLabel29))
                                                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, 0)
                                                                                .addComponent(jLabel35)
                                                                                .addGap(30, 30, 30)
                                                                                .addComponent(btnbuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, 0)
                                                                                .addComponent(jLabel37)))
                                                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                                                                        .addComponent(jLabel34)
                                                                                        .addGap(263, 263, 263))
                                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                                                                        .addComponent(elimdp)
                                                                                        .addGap(152, 152, 152))))
                                                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(btnbuscar9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, 0)
                                                                                .addComponent(jLabel38)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(btnbuscar10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, 0)
                                                                                .addComponent(jLabel39)
                                                                                .addContainerGap())))
                                                                );
                                                                jPanel18Layout.setVerticalGroup(
                                                                    jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                .addGap(21, 21, 21)
                                                                                .addComponent(jLabel34)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                    .addComponent(elimdp)
                                                                                    .addComponent(jLabel29))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel35)
                                                                            .addComponent(btnbuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel37)
                                                                            .addComponent(btnbuscar9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel38)
                                                                            .addComponent(btnbuscar10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel39))
                                                                        .addGap(348, 348, 348))
                                                                );

                                                                jTabbedPane5.setBackground(new java.awt.Color(255, 255, 255));
                                                                jTabbedPane5.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                                                                jPanel22.setBackground(new java.awt.Color(255, 255, 255));

                                                                jScrollPane13.setBackground(new java.awt.Color(255, 255, 255));
                                                                jScrollPane13.setBorder(null);

                                                                tEME.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tEME.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tEME.setRowHeight(25);
                                                                tEME.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                                tEME.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tEMEMouseClicked(evt);
                                                                    }
                                                                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                                                                        tEMEMouseEntered(evt);
                                                                    }
                                                                });
                                                                tEME.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tEMEKeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane13.setViewportView(tEME);

                                                                jScrollPane14.setBackground(new java.awt.Color(255, 255, 255));
                                                                jScrollPane14.setBorder(null);

                                                                tEMEDET.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tEMEDET.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
                                                                tEMEDET.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tEMEDET.setRowHeight(25);
                                                                tEMEDET.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                                tEMEDET.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tEMEDETMouseClicked(evt);
                                                                    }
                                                                });
                                                                tEMEDET.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tEMEDETKeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane14.setViewportView(tEMEDET);

                                                                CARGAR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                CARGAR.setForeground(new java.awt.Color(0, 153, 51));
                                                                CARGAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Exportar-30.png"))); // NOI18N
                                                                CARGAR.setText("Cargar para venta");
                                                                CARGAR.setContentAreaFilled(false);
                                                                CARGAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                CARGAR.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                CARGAR.setIconTextGap(30);
                                                                CARGAR.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                CARGAR.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        CARGARActionPerformed(evt);
                                                                    }
                                                                });

                                                                CARGAR1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                CARGAR1.setForeground(new java.awt.Color(255, 91, 70));
                                                                CARGAR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Delete Property Filled-32.png"))); // NOI18N
                                                                CARGAR1.setText("Eliminar Registro");
                                                                CARGAR1.setContentAreaFilled(false);
                                                                CARGAR1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                CARGAR1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                CARGAR1.setIconTextGap(30);
                                                                CARGAR1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                CARGAR1.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        CARGAR1ActionPerformed(evt);
                                                                    }
                                                                });

                                                                cargareliminar.setBackground(new java.awt.Color(255, 91, 70));

                                                                Mensaje.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                Mensaje.setForeground(new java.awt.Color(255, 255, 255));
                                                                Mensaje.setText("Desea Eliminar el Registro ?");

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
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(19, 19, 19))
                                                                );
                                                                cargareliminarLayout.setVerticalGroup(
                                                                    cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(cargareliminarLayout.createSequentialGroup()
                                                                        .addGap(17, 17, 17)
                                                                        .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(Mensaje)
                                                                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addContainerGap(30, Short.MAX_VALUE))
                                                                );

                                                                javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
                                                                jPanel22.setLayout(jPanel22Layout);
                                                                jPanel22Layout.setHorizontalGroup(
                                                                    jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jScrollPane13)
                                                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                                                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                                                        .addComponent(CARGAR)
                                                                        .addGap(0, 0, 0)
                                                                        .addComponent(CARGAR1)
                                                                        .addGap(0, 0, 0)
                                                                        .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                );
                                                                jPanel22Layout.setVerticalGroup(
                                                                    jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, 0)
                                                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(jPanel22Layout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(CARGAR)
                                                                                    .addComponent(CARGAR1))
                                                                                .addGap(70, 70, 70))
                                                                            .addGroup(jPanel22Layout.createSequentialGroup()
                                                                                .addGap(0, 0, 0)
                                                                                .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                                );

                                                                jTabbedPane5.addTab("EME", jPanel22);

                                                                tpreventa7.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tpreventa7.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tpreventa7.setRowHeight(25);
                                                                tpreventa7.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                                tpreventa7.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tpreventa7MouseClicked(evt);
                                                                    }
                                                                });
                                                                tpreventa7.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tpreventa7KeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane15.setViewportView(tpreventa7);

                                                                tpreventa8.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tpreventa8.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tpreventa8.setRowHeight(25);
                                                                tpreventa8.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                                tpreventa8.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tpreventa8MouseClicked(evt);
                                                                    }
                                                                });
                                                                tpreventa8.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tpreventa8KeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane16.setViewportView(tpreventa8);

                                                                btnbuscar13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                btnbuscar13.setForeground(new java.awt.Color(0, 153, 51));
                                                                btnbuscar13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Exportar-30.png"))); // NOI18N
                                                                btnbuscar13.setText("Cargar para venta");
                                                                btnbuscar13.setContentAreaFilled(false);
                                                                btnbuscar13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                btnbuscar13.setIconTextGap(30);
                                                                btnbuscar13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar13.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar13ActionPerformed(evt);
                                                                    }
                                                                });

                                                                javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                                                                jPanel23.setLayout(jPanel23Layout);
                                                                jPanel23Layout.setHorizontalGroup(
                                                                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane16)
                                                                    .addGroup(jPanel23Layout.createSequentialGroup()
                                                                        .addComponent(btnbuscar13)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                );
                                                                jPanel23Layout.setVerticalGroup(
                                                                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel23Layout.createSequentialGroup()
                                                                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnbuscar13)
                                                                        .addGap(29, 29, 29))
                                                                );

                                                                jTabbedPane5.addTab("HOS", jPanel23);

                                                                tpreventa9.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tpreventa9.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tpreventa9.setRowHeight(25);
                                                                tpreventa9.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                                tpreventa9.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tpreventa9MouseClicked(evt);
                                                                    }
                                                                });
                                                                tpreventa9.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tpreventa9KeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane17.setViewportView(tpreventa9);

                                                                tpreventa10.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tpreventa10.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tpreventa10.setRowHeight(25);
                                                                tpreventa10.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                                tpreventa10.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tpreventa10MouseClicked(evt);
                                                                    }
                                                                });
                                                                tpreventa10.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tpreventa10KeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane18.setViewportView(tpreventa10);

                                                                btnbuscar14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                btnbuscar14.setForeground(new java.awt.Color(0, 153, 51));
                                                                btnbuscar14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Exportar-30.png"))); // NOI18N
                                                                btnbuscar14.setText("Cargar para venta");
                                                                btnbuscar14.setContentAreaFilled(false);
                                                                btnbuscar14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                btnbuscar14.setIconTextGap(30);
                                                                btnbuscar14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar14.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar14ActionPerformed(evt);
                                                                    }
                                                                });

                                                                javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
                                                                jPanel24.setLayout(jPanel24Layout);
                                                                jPanel24Layout.setHorizontalGroup(
                                                                    jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane18)
                                                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                                                        .addComponent(btnbuscar14)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                );
                                                                jPanel24Layout.setVerticalGroup(
                                                                    jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                                                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnbuscar14)
                                                                        .addGap(29, 29, 29))
                                                                );

                                                                jTabbedPane5.addTab("SIS", jPanel24);

                                                                tpreventa11.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tpreventa11.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tpreventa11.setRowHeight(25);
                                                                tpreventa11.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                                tpreventa11.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tpreventa11MouseClicked(evt);
                                                                    }
                                                                });
                                                                tpreventa11.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tpreventa11KeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane19.setViewportView(tpreventa11);

                                                                tpreventa12.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                tpreventa12.setGridColor(new java.awt.Color(255, 255, 255));
                                                                tpreventa12.setRowHeight(25);
                                                                tpreventa12.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                                tpreventa12.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        tpreventa12MouseClicked(evt);
                                                                    }
                                                                });
                                                                tpreventa12.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        tpreventa12KeyPressed(evt);
                                                                    }
                                                                });
                                                                jScrollPane20.setViewportView(tpreventa12);

                                                                btnbuscar15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                btnbuscar15.setForeground(new java.awt.Color(0, 153, 51));
                                                                btnbuscar15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Exportar-30.png"))); // NOI18N
                                                                btnbuscar15.setText("Cargar para venta");
                                                                btnbuscar15.setContentAreaFilled(false);
                                                                btnbuscar15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                btnbuscar15.setIconTextGap(30);
                                                                btnbuscar15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar15.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar15ActionPerformed(evt);
                                                                    }
                                                                });

                                                                javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
                                                                jPanel25.setLayout(jPanel25Layout);
                                                                jPanel25Layout.setHorizontalGroup(
                                                                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane20)
                                                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                                                        .addComponent(btnbuscar15)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                );
                                                                jPanel25Layout.setVerticalGroup(
                                                                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                                                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnbuscar15)
                                                                        .addGap(29, 29, 29))
                                                                );

                                                                jTabbedPane5.addTab("SAH", jPanel25);

                                                                javax.swing.GroupLayout preventasLayout = new javax.swing.GroupLayout(preventas.getContentPane());
                                                                preventas.getContentPane().setLayout(preventasLayout);
                                                                preventasLayout.setHorizontalGroup(
                                                                    preventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                );
                                                                preventasLayout.setVerticalGroup(
                                                                    preventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(preventasLayout.createSequentialGroup()
                                                                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, 0)
                                                                        .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                );

                                                                Anular.setAlwaysOnTop(true);
                                                                Anular.setMinimumSize(new java.awt.Dimension(517, 300));

                                                                jPanel19.setBackground(new java.awt.Color(255, 91, 70));
                                                                jPanel19.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                jLabel31.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                jLabel31.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel31.setText("Anular Venta");

                                                                btnbuscar7.setForeground(new java.awt.Color(240, 240, 240));
                                                                btnbuscar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                                                btnbuscar7.setMnemonic('N');
                                                                btnbuscar7.setToolTipText("");
                                                                btnbuscar7.setContentAreaFilled(false);
                                                                btnbuscar7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar7.setIconTextGap(30);
                                                                btnbuscar7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar7.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar7ActionPerformed(evt);
                                                                    }
                                                                });

                                                                btnbuscar8.setForeground(new java.awt.Color(240, 240, 240));
                                                                btnbuscar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                                                                btnbuscar8.setMnemonic('N');
                                                                btnbuscar8.setToolTipText("");
                                                                btnbuscar8.setContentAreaFilled(false);
                                                                btnbuscar8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar8.setIconTextGap(30);
                                                                btnbuscar8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar8.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar8ActionPerformed(evt);
                                                                    }
                                                                });

                                                                javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                                                                jPanel19.setLayout(jPanel19Layout);
                                                                jPanel19Layout.setHorizontalGroup(
                                                                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel31)
                                                                            .addGroup(jPanel19Layout.createSequentialGroup()
                                                                                .addComponent(btnbuscar7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(btnbuscar8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addContainerGap(344, Short.MAX_VALUE))
                                                                );
                                                                jPanel19Layout.setVerticalGroup(
                                                                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel31)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addComponent(btnbuscar7)
                                                                            .addComponent(btnbuscar8))
                                                                        .addGap(345, 345, 345))
                                                                );

                                                                nuevoanulacion.setBackground(new java.awt.Color(255, 255, 255));

                                                                jLabel33.setText("Nuevo motivo de  anulacion");

                                                                lblcodanu.setText("jLabel34");

                                                                javax.swing.GroupLayout nuevoanulacionLayout = new javax.swing.GroupLayout(nuevoanulacion);
                                                                nuevoanulacion.setLayout(nuevoanulacionLayout);
                                                                nuevoanulacionLayout.setHorizontalGroup(
                                                                    nuevoanulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(nuevoanulacionLayout.createSequentialGroup()
                                                                        .addGap(0, 0, 0)
                                                                        .addGroup(nuevoanulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(nuevoanulacionLayout.createSequentialGroup()
                                                                                .addComponent(txtanular, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap(172, Short.MAX_VALUE))
                                                                            .addGroup(nuevoanulacionLayout.createSequentialGroup()
                                                                                .addComponent(jLabel33)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(lblcodanu)
                                                                                .addGap(79, 79, 79))))
                                                                );
                                                                nuevoanulacionLayout.setVerticalGroup(
                                                                    nuevoanulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(nuevoanulacionLayout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(nuevoanulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(jLabel33)
                                                                            .addComponent(lblcodanu))
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(txtanular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                );

                                                                jPanel21.setBackground(new java.awt.Color(255, 255, 255));

                                                                jLabel32.setText("Seleccione Motivo de anulacion");

                                                                CBXANULAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                                CBXANULAR.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                                                                javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                                                                jPanel21.setLayout(jPanel21Layout);
                                                                jPanel21Layout.setHorizontalGroup(
                                                                    jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                                                        .addGap(0, 0, 0)
                                                                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel32)
                                                                            .addComponent(CBXANULAR, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                );
                                                                jPanel21Layout.setVerticalGroup(
                                                                    jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(jLabel32)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(CBXANULAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addContainerGap(48, Short.MAX_VALUE))
                                                                );

                                                                javax.swing.GroupLayout AnularLayout = new javax.swing.GroupLayout(Anular.getContentPane());
                                                                Anular.getContentPane().setLayout(AnularLayout);
                                                                AnularLayout.setHorizontalGroup(
                                                                    AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addGroup(AnularLayout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(nuevoanulacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addContainerGap(61, Short.MAX_VALUE))
                                                                );
                                                                AnularLayout.setVerticalGroup(
                                                                    AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(AnularLayout.createSequentialGroup()
                                                                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(nuevoanulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                );

                                                                jPanel26.setBackground(new java.awt.Color(255, 91, 70));
                                                                jPanel26.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                jLabel40.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                jLabel40.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel40.setText("Anular Venta");

                                                                btnbuscar11.setForeground(new java.awt.Color(240, 240, 240));
                                                                btnbuscar11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                                                btnbuscar11.setMnemonic('N');
                                                                btnbuscar11.setToolTipText("");
                                                                btnbuscar11.setContentAreaFilled(false);
                                                                btnbuscar11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar11.setIconTextGap(30);
                                                                btnbuscar11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar11.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar11ActionPerformed(evt);
                                                                    }
                                                                });

                                                                btnbuscar12.setForeground(new java.awt.Color(240, 240, 240));
                                                                btnbuscar12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                                                                btnbuscar12.setMnemonic('N');
                                                                btnbuscar12.setToolTipText("");
                                                                btnbuscar12.setContentAreaFilled(false);
                                                                btnbuscar12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar12.setIconTextGap(30);
                                                                btnbuscar12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar12.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar12ActionPerformed(evt);
                                                                    }
                                                                });

                                                                javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
                                                                jPanel26.setLayout(jPanel26Layout);
                                                                jPanel26Layout.setHorizontalGroup(
                                                                    jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel26Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel40)
                                                                            .addGroup(jPanel26Layout.createSequentialGroup()
                                                                                .addComponent(btnbuscar11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(btnbuscar12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addContainerGap(354, Short.MAX_VALUE))
                                                                );
                                                                jPanel26Layout.setVerticalGroup(
                                                                    jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel40)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addComponent(btnbuscar11)
                                                                            .addComponent(btnbuscar12))
                                                                        .addGap(345, 345, 345))
                                                                );

                                                                javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
                                                                jDialog1.getContentPane().setLayout(jDialog1Layout);
                                                                jDialog1Layout.setHorizontalGroup(
                                                                    jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                );
                                                                jDialog1Layout.setVerticalGroup(
                                                                    jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jDialog1Layout.createSequentialGroup()
                                                                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 174, Short.MAX_VALUE))
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

                                                                btnimprimir.setForeground(new java.awt.Color(240, 240, 240));
                                                                btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Print-32 (2).png"))); // NOI18N
                                                                btnimprimir.setMnemonic('N');
                                                                btnimprimir.setContentAreaFilled(false);
                                                                btnimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnimprimir.setIconTextGap(30);
                                                                btnimprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnimprimir.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnimprimirActionPerformed(evt);
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
                                                                                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                                            .addComponent(btnimprimir))
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
                                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
                                                                );

                                                                jTabbedPane1.addTab("Ventas de Hoy", jPanel2);

                                                                jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                                                                jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                                                                jLabel2.setText("Forma de Pago");

                                                                jLabel3.setText("Tipo de Documento");

                                                                jLabel4.setText("DNI / H.C.");

                                                                jLabel5.setText("Numero de Documento");

                                                                txtcodigo.setEnabled(false);

                                                                txthc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
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

                                                                txtfp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                                                                txtfp.setEnabled(false);
                                                                txtfp.setMinimumSize(new java.awt.Dimension(2, 22));
                                                                txtfp.setPreferredSize(new java.awt.Dimension(2, 22));
                                                                txtfp.addCaretListener(new javax.swing.event.CaretListener() {
                                                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                        txtfpCaretUpdate(evt);
                                                                    }
                                                                });
                                                                txtfp.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        txtfpMouseClicked(evt);
                                                                    }
                                                                });
                                                                txtfp.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        txtfpActionPerformed(evt);
                                                                    }
                                                                });
                                                                txtfp.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        txtfpKeyPressed(evt);
                                                                    }
                                                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                                                        txtfpKeyTyped(evt);
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

                                                                jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                                jLabel6.setForeground(new java.awt.Color(153, 153, 153));
                                                                jLabel6.setText("PACIENTE __________________________________________________________________________________________________________________________________________________");

                                                                jLabel7.setText("Apellidos y Nombres");

                                                                txtape.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                txtape.setForeground(new java.awt.Color(102, 102, 102));
                                                                txtape.setBorder(null);
                                                                txtape.setEnabled(false);
                                                                txtape.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        txtapeActionPerformed(evt);
                                                                    }
                                                                });

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

                                                                jLabel14.setText("Sexo ");

                                                                sexo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                sexo.setForeground(new java.awt.Color(102, 102, 102));
                                                                sexo.setBorder(null);
                                                                sexo.setEnabled(false);

                                                                lblcodigo.setText("Numero de Documento");

                                                                lblhc.setText("Numero de Documento");

                                                                cbxtipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                                                                cbxtipo.setEnabled(false);
                                                                cbxtipo.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        cbxtipoActionPerformed(evt);
                                                                    }
                                                                });
                                                                cbxtipo.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        cbxtipoKeyPressed(evt);
                                                                    }
                                                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                                                        cbxtipoKeyTyped(evt);
                                                                    }
                                                                });

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

                                                                separar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                                separar.setForeground(new java.awt.Color(153, 153, 153));
                                                                separar.setText("CPT_________________________________________________________________________________________________________________________________________________________");

                                                                bu.setText("Buscar");

                                                                Nomen2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                                                                Nomen2.setEnabled(false);
                                                                Nomen2.setMinimumSize(new java.awt.Dimension(2, 22));
                                                                Nomen2.setPreferredSize(new java.awt.Dimension(2, 22));
                                                                Nomen2.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        Nomen2ActionPerformed(evt);
                                                                    }
                                                                });

                                                                b4.setForeground(new java.awt.Color(240, 240, 240));
                                                                b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                                                                b4.setMnemonic('N');
                                                                b4.setContentAreaFilled(false);
                                                                b4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                b4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                                                                b4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                b4.setIconTextGap(30);
                                                                b4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                b4.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        b4ActionPerformed(evt);
                                                                    }
                                                                });
                                                                b4.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        b4KeyPressed(evt);
                                                                    }
                                                                });

                                                                jScrollPane4.setBorder(null);

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
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                                                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addContainerGap())
                                                                );

                                                                preventa.setBackground(new java.awt.Color(0, 153, 102));
                                                                preventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                preventa.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                        preventaMouseClicked(evt);
                                                                    }
                                                                });
                                                                preventa.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                        preventaKeyPressed(evt);
                                                                    }
                                                                });

                                                                jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel15.setText("Preventas pendiente de pago, cargar?");

                                                                btnbuscar5.setForeground(new java.awt.Color(240, 240, 240));
                                                                btnbuscar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Camisas venta por mayor-30.png"))); // NOI18N
                                                                btnbuscar5.setMnemonic('N');
                                                                btnbuscar5.setContentAreaFilled(false);
                                                                btnbuscar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                btnbuscar5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                btnbuscar5.setIconTextGap(30);
                                                                btnbuscar5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                btnbuscar5.addActionListener(new java.awt.event.ActionListener() {
                                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                        btnbuscar5ActionPerformed(evt);
                                                                    }
                                                                });

                                                                jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                                                                jLabel36.setForeground(new java.awt.Color(255, 255, 255));
                                                                jLabel36.setText("10");

                                                                javax.swing.GroupLayout preventaLayout = new javax.swing.GroupLayout(preventa);
                                                                preventa.setLayout(preventaLayout);
                                                                preventaLayout.setHorizontalGroup(
                                                                    preventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(preventaLayout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(btnbuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(jLabel36)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jLabel15)
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                );
                                                                preventaLayout.setVerticalGroup(
                                                                    preventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, preventaLayout.createSequentialGroup()
                                                                        .addGap(3, 3, 3)
                                                                        .addComponent(btnbuscar5)
                                                                        .addGap(4, 4, 4))
                                                                    .addGroup(preventaLayout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(preventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(jLabel15)
                                                                            .addComponent(jLabel36))
                                                                        .addContainerGap())
                                                                );

                                                                jLabel30.setText("jLabel30");

                                                                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                                                jPanel3.setLayout(jPanel3Layout);
                                                                jPanel3Layout.setHorizontalGroup(
                                                                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(jScrollPane4)
                                                                                .addGap(0, 0, 0)
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                    .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                .addGap(202, 202, 202))
                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addComponent(jLabel51)
                                                                                    .addComponent(jLabel6)
                                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel2)
                                                                                                    .addComponent(jLabel3)
                                                                                                    .addComponent(jLabel4))
                                                                                                .addGap(14, 14, 14))
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                                                .addComponent(jLabel7)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                    .addComponent(cbxtipo, javax.swing.GroupLayout.Alignment.LEADING, 0, 160, Short.MAX_VALUE)
                                                                                                    .addComponent(txtfp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(67, 67, 67)
                                                                                                .addComponent(jLabel5)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(lblcodigo)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(lblhc)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel30))
                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                        .addComponent(txthc, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(0, 0, 0)
                                                                                                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                                                                .addComponent(nom))))
                                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                        .addComponent(bu)
                                                                                        .addGap(72, 72, 72)
                                                                                        .addComponent(Nomen2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(0, 0, 0)
                                                                                        .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addContainerGap(205, Short.MAX_VALUE))
                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(separar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addContainerGap())))
                                                                    .addComponent(preventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                );
                                                                jPanel3Layout.setVerticalGroup(
                                                                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(jLabel51)
                                                                                .addGap(16, 16, 16)
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(jLabel2)
                                                                                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(txtfp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(jLabel5)
                                                                                    .addComponent(lblcodigo)
                                                                                    .addComponent(lblhc)
                                                                                    .addComponent(jLabel30)))
                                                                            .addComponent(b))
                                                                        .addGap(11, 11, 11)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(cbxtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel3))
                                                                        .addGap(11, 11, 11)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(jLabel6)
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                        .addGap(20, 20, 20)
                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                            .addComponent(jLabel4)
                                                                                            .addComponent(txthc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                            .addComponent(jLabel12)
                                                                                            .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                            .addComponent(b2))
                                                                        .addGap(14, 14, 14)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                                            .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(preventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(separar)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(Nomen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(bu)))
                                                                            .addComponent(b4, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                );

                                                                jTabbedPane1.addTab("Nueva Venta", jPanel3);

                                                                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                                                getContentPane().setLayout(layout);
                                                                layout.setHorizontalGroup(
                                                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(jTabbedPane1)
                                                                        .addContainerGap())
                                                                );
                                                                layout.setVerticalGroup(
                                                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
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
           Jerarquias.setVisible(true);
        txtBuscar1.setText("");
        
                 ///NO MOSTRAR
         //////////////////////////
          //preventa.setVisible(false);
          separar.setVisible(false);
          bu.setVisible(false);
          Nomen2.setVisible(false);
          b4.setVisible(false);
          nuevoanulacion.setVisible(false);
          preventa.setVisible(false);
          
          jScrollPane4.setVisible(false);
          b5.setVisible(false);
          jPanel20.setVisible(false);
          btnNuevo.setEnabled(true);
        
          btneditar.setEnabled(false);
          btneliminar.setEnabled(false);
          btnbuscar.setEnabled(true);
          btnimprimir.setEnabled(false);
          CARGAR.setVisible(false);
          CARGAR1.setVisible(false);
          
          
          txtape.setEnabled(false);
          txtdir.setEnabled(false);
          txtdni.setEnabled(false);
          txtedad.setEnabled(false);
          sexo.setEnabled(false);
          txtfp.setText("");
          txthc.setText("");
          txtape.setText("");
          txtdir.setText("");
          txtdni.setText("");
          txtedad.setText("");
          sexo.setText("");
          
          ////NO SE MUESTRA PRE VENTA
          btnbuscar4.setVisible(false);
          btnbuscar6.setVisible(false);
          btnbuscar9.setVisible(false);
          btnbuscar10.setVisible(false);
          jScrollPane14.setVisible(false);
          cargareliminar.setVisible(false);
          //////////////////////////
           
               
         LISTAR();
         LISTARN();
         formatoj();
         if (txtBuscar.getText() == " "){
             jTabbedPane2.setSelectedIndex(0);
        }
        
 
   
       this.cbxtipo.setModel(tipo());
       this.CBXANULAR.setModel(anular());
      
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
      
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        Guardar();    

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        Anular.setVisible(true);
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

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        jTabbedPane2.setSelectedIndex(1);
        BuscarHC();

        if (tb_Grupo.getRowCount() == 0){
            jTabbedPane2.setSelectedIndex(2);
            }
          if (txtBuscar.getText() == ""){
             jTabbedPane2.setSelectedIndex(0);
        }

        
           
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
       int fila=tb_Grupo.getSelectedRow();
       bus.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
        Preventamostrar();
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
            
            txthc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            txtdir.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));    
            txtdni.setText(String.valueOf(tb_Grupo.getValueAt(fila, 3)));
            sexo.setText(String.valueOf(tb_Grupo.getValueAt(fila, 4)));
            txtedad.setText(String.valueOf(tb_Grupo.getValueAt(fila, 6)));
            lblhc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 7))); 
           
            
          ////////////////////////

	     jLabel36.setText(String.valueOf(tb_Grupo5.getRowCount()));

             if(this.tb_Grupo5.getRowCount()!=0  ){
              preventa.setVisible(true);
                preventa.requestFocus();
              separar.setVisible(false);
              bu.setVisible(false);
              Nomen2.setVisible(false);
              b4.setVisible(false);
               jScrollPane4.setVisible(false);

             }
              if(this.tb_Grupo5.getRowCount()==0  ){
              preventa.setVisible(false);
              separar.setVisible(true);
              bu.setVisible(true);
              Nomen2.setVisible(true);
              b4.setVisible(true);
              jScrollPane4.setVisible(true);
              nomenclaturas.setVisible(true);
              txtBuscar2.setText("");
             }
        
        }
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
         
        char teclaPresionada = evt.getKeyChar();
        
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            bus.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            Preventamostrar();
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
          
            jLabel36.setText(String.valueOf(tb_Grupo5.getRowCount()));
            
            
          ////////////////////////
            
           if(this.tb_Grupo5.getRowCount()!=0  ){
              preventa.setVisible(true);
                preventa.requestFocus();
              separar.setVisible(false);
              bu.setVisible(false);
              Nomen2.setVisible(false);
              b4.setVisible(false);
              jScrollPane4.setVisible(false);

          }
            if(this.tb_Grupo5.getRowCount()==0  ){
              preventa.setVisible(false);
              separar.setVisible(true);
              bu.setVisible(true);
              Nomen2.setVisible(true);
              b4.setVisible(true);
              jScrollPane4.setVisible(true);
              nomenclaturas.setVisible(true);
              txtBuscar2.setText("");
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
 
         
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void tb_Grupo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo3MouseClicked
         int fila=tb_Grupo3.getSelectedRow();
        if(evt.getClickCount()==2){
            Jerarquias.dispose();
//            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtfp.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
            jLabel30.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
            if (txtfp.getText()=="SIS"){
                jLabel4.setText("DNI");
        }else{
            if (txtfp.getText()=="CONTADO"){
            jLabel4.setText("Historia Clinica");
            } 
            }    
        
         
        

               }
    }//GEN-LAST:event_tb_Grupo3MouseClicked

    private void tb_Grupo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo3KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo3.getSelectedRow();
            Jerarquias.dispose();
//            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtfp.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
            jLabel30.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
             if (txtfp.getText()=="SIS"){
                jLabel4.setText("DNI");
        }else{
            if (txtfp.getText()=="CONTADO"){
            jLabel4.setText("Historia Clinica");
            } 
            }  
            cbxtipo.showPopup();
            cbxtipo.requestFocus(true);
        
              
           
          } 
       
    }//GEN-LAST:event_tb_Grupo3KeyPressed

    private void txthcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthcActionPerformed

    private void txtfpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfpMouseClicked
        Jerarquias.setVisible(true);
        txtBuscar1.setText("");
    }//GEN-LAST:event_txtfpMouseClicked

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
                        b5.setVisible(true);
                        b5.setEnabled(false);
                        jPanel20.setVisible(true);
                        b4.requestFocus();
                    
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

                        b5.setVisible(true);
                        b5.setEnabled(false);
                        jPanel20.setVisible(true);
                        b4.requestFocus();
                    
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

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnimprimirActionPerformed

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

    private void txtfpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfpKeyPressed

    private void txtfpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfpActionPerformed
     
          
    }//GEN-LAST:event_txtfpActionPerformed

    private void cbxtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxtipoActionPerformed
        //        evt.setSource((char) KeyEvent.VK_CLEAR);
        //        jTextField2.requestFocus();// al presionar enter mandas el cursor a jTextField2
    }//GEN-LAST:event_cbxtipoActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        Jerarquias.setVisible(true);
        txtBuscar1.setText("");
           
    }//GEN-LAST:event_bActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        preventa.setVisible(false);
        BHC.setVisible(true);
        txtBuscar.setText("");
         jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_b2ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
          nomenclaturas.setVisible(true);
          txtBuscar2.setText("");
    }//GEN-LAST:event_b4ActionPerformed

    private void Nomen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nomen2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nomen2ActionPerformed

    private void txtapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapeActionPerformed

    private void btnbuscar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar5ActionPerformed

    private void preventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preventaMouseClicked
       preventas.setVisible(true);
       PreventaEME();
       
        btnbuscar4.setText(String.valueOf(tEME.getRowCount()));
        btnbuscar4.setVisible(true);
     
    }//GEN-LAST:event_preventaMouseClicked

    private void txtfpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtfpCaretUpdate
 
    }//GEN-LAST:event_txtfpCaretUpdate

    private void btnbuscar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar7ActionPerformed
           nuevoanulacion.setVisible(true);
            lblcodanu.setText(cnn.idanu());
           txtanular.requestFocus();
    }//GEN-LAST:event_btnbuscar7ActionPerformed

    private void btnbuscar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar8ActionPerformed

    private void tb_Grupo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo5MouseClicked

    private void tb_Grupo5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo5KeyPressed

    private void preventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preventaKeyPressed
    char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
         preventas.setVisible(true);
         PreventaEME();
       
        btnbuscar4.setText(String.valueOf(tEME.getRowCount()));
        btnbuscar4.setVisible(true);

          } 
    }//GEN-LAST:event_preventaKeyPressed

    private void btnbuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar4ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
    }//GEN-LAST:event_btnbuscar4ActionPerformed

    private void btnbuscar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar6ActionPerformed

    private void btnbuscar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar9ActionPerformed

    private void btnbuscar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar10ActionPerformed

    private void tEMEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tEMEMouseClicked
      
        int fila=tEME.getSelectedRow();         
        jLabel34.setText(String.valueOf(tEME.getValueAt(fila, 0)));
        PreventaEMEDET();
        
        if (jLabel34.getText() != "jLabel34"){
            jScrollPane14.setVisible(true);
        }

    }//GEN-LAST:event_tEMEMouseClicked

    private void tEMEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tEMEKeyPressed
        int fila=tEME.getSelectedRow();         
        jLabel34.setText(String.valueOf(tEME.getValueAt(fila, 0)));
        PreventaEMEDET();
        
        if (jLabel34.getText() != "jLabel34"){
            jScrollPane14.setVisible(true);
        }
    }//GEN-LAST:event_tEMEKeyPressed

    private void tEMEDETMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tEMEDETMouseClicked
          
        if(evt.getClickCount()==1){
                int filaseleccionada;
            try{
                filaseleccionada= tEMEDET.getSelectedRow();
                if (filaseleccionada!=-1){
                    CARGAR.setVisible(true);
                    CARGAR1.setVisible(true);
                }
            } catch (Exception e) {
                System.out.println("Error seleccionar: " + e.getMessage());
              }
        }
        int fila=tEMEDET.getSelectedRow();         
        elimdp.setText(String.valueOf(tEMEDET.getValueAt(fila, 6)));
   
     
    }//GEN-LAST:event_tEMEDETMouseClicked

    private void tEMEDETKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tEMEDETKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){          
                int filaseleccionada;
            try{
                filaseleccionada= tEMEDET.getSelectedRow();
                if (filaseleccionada!=-1){
                    CARGAR.setVisible(true);
                    CARGAR1.setVisible(true);
                }
            } catch (Exception e) {
                System.out.println("Error seleccionar: " + e.getMessage());
              }
        }
        int fila=tEMEDET.getSelectedRow();         
        elimdp.setText(String.valueOf(tEMEDET.getValueAt(fila, 6)));
    }//GEN-LAST:event_tEMEDETKeyPressed

    private void CARGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CARGARActionPerformed
 
    
    }//GEN-LAST:event_CARGARActionPerformed

    private void tpreventa7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpreventa7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa7MouseClicked

    private void tpreventa7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpreventa7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa7KeyPressed

    private void tpreventa8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpreventa8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa8MouseClicked

    private void tpreventa8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpreventa8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa8KeyPressed

    private void btnbuscar13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar13ActionPerformed

    private void tpreventa9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpreventa9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa9MouseClicked

    private void tpreventa9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpreventa9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa9KeyPressed

    private void tpreventa10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpreventa10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa10MouseClicked

    private void tpreventa10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpreventa10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa10KeyPressed

    private void btnbuscar14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar14ActionPerformed

    private void tpreventa11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpreventa11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa11MouseClicked

    private void tpreventa11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpreventa11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa11KeyPressed

    private void tpreventa12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpreventa12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa12MouseClicked

    private void tpreventa12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpreventa12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpreventa12KeyPressed

    private void btnbuscar15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar15ActionPerformed

    private void tEMEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tEMEMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tEMEMouseEntered

    private void CARGAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CARGAR1ActionPerformed
 
        cargareliminar.setBackground(new Color(255,91,70)); 
                    Mensaje.setText("Desea Eliminar el Registro ?");
                    eli.setVisible(true);
                    noeli.setVisible(true);
        
        cargareliminar.setVisible(true);

    }//GEN-LAST:event_CARGAR1ActionPerformed

    private void btnbuscar11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar11ActionPerformed

    private void btnbuscar12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar12ActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed

       
        try{
           
                Caja_DetallePreventas obj=new Caja_DetallePreventas();
                obj.setIdDetalle_Preventa(Integer.parseInt(elimdp.getText()));
                if(obj.eliminarDP())
                {
                    cargareliminar.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro Eliminado");
                    eli.setVisible(false);
                    noeli.setVisible(false);
                    CARGAR1.setVisible(false);
                }
            
        }catch(Exception e){
           
        }
          PreventaEMEDET();
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
     cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void T3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T3MouseClicked
      
    }//GEN-LAST:event_T3MouseClicked

    private void cbxtipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxtipoKeyTyped
      
    }//GEN-LAST:event_cbxtipoKeyTyped

    private void txtfpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfpKeyTyped
      
    }//GEN-LAST:event_txtfpKeyTyped

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
               char teclaPresionada = evt.getKeyChar();
    
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo3.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo3.requestFocus();
        
            
           
          } 
 
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked

    }//GEN-LAST:event_txtBuscarMouseClicked

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
                         char teclaPresionada = evt.getKeyChar();
    
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo.requestFocus();
        
         
           
          } 
        
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        char teclaPresionada = evt.getKeyChar();
    
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo4.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo4.requestFocus();
        
         
           
          } 
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void cbxtipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxtipoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
              preventa.setVisible(false);
        BHC.setVisible(true);
        txtBuscar.setText("");
         jTabbedPane2.setSelectedIndex(0);

          } 
       
    }//GEN-LAST:event_cbxtipoKeyPressed

    private void b4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b4KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            nomenclaturas.setVisible(true);
            txtBuscar2.setText("");
        } 
    }//GEN-LAST:event_b4KeyPressed

    private void T4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T4MouseClicked

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
    private javax.swing.JDialog Anular;
    private javax.swing.JDialog BHC;
    private javax.swing.JButton CARGAR;
    private javax.swing.JButton CARGAR1;
    private javax.swing.JComboBox CBXANULAR;
    private javax.swing.JDialog Jerarquias;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JTextField Nomen2;
    private javax.swing.JLabel T3;
    private javax.swing.JLabel T4;
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
    private javax.swing.JButton btnbuscar10;
    private javax.swing.JButton btnbuscar11;
    private javax.swing.JButton btnbuscar12;
    private javax.swing.JButton btnbuscar13;
    private javax.swing.JButton btnbuscar14;
    private javax.swing.JButton btnbuscar15;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btnbuscar4;
    private javax.swing.JButton btnbuscar5;
    private javax.swing.JButton btnbuscar6;
    private javax.swing.JButton btnbuscar7;
    private javax.swing.JButton btnbuscar8;
    private javax.swing.JButton btnbuscar9;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JLabel bu;
    private javax.swing.JLabel bus;
    private javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxtipo;
    private javax.swing.JButton eli;
    private javax.swing.JLabel elimdp;
    private javax.swing.JLabel fp;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel45;
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
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JLabel lblcodanu;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JLabel lblhc;
    private javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JLabel nom;
    private javax.swing.JDialog nomenclaturas;
    private javax.swing.JPanel nuevoanulacion;
    private javax.swing.JPanel preventa;
    private javax.swing.JDialog preventas;
    private javax.swing.JDialog quitar;
    private javax.swing.JLabel separar;
    private javax.swing.JTextField sexo;
    private javax.swing.JTable tEME;
    private javax.swing.JTable tEMEDET;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo2;
    private javax.swing.JTable tb_Grupo3;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JTable tb_Grupo5;
    private javax.swing.JTextField total;
    private javax.swing.JTable tpreventa10;
    private javax.swing.JTable tpreventa11;
    private javax.swing.JTable tpreventa12;
    private javax.swing.JTable tpreventa7;
    private javax.swing.JTable tpreventa8;
    private javax.swing.JTable tpreventa9;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtanular;
    private javax.swing.JTextField txtape;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdir;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtedad;
    private javax.swing.JTextField txtfp;
    private javax.swing.JTextField txthc;
    // End of variables declaration//GEN-END:variables
}
