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
import modelos.Caja.Caja_Documento_Detalle;
import modelos.Caja.Caja_MotivoAnulacion;
import modelos.Caja.Caja_NuevaVenta;
import modelos.Caja.Caja_Preventa;
import modelos.Caja.Caja_SIS_Cabecera;
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
byte tge;
byte tgm;
byte tgp;
byte tga;
byte tgg;

byte tgH;
byte tgDetalle;
byte tgnuevoEliminar;
ResultSet r;
Conexion c=new Conexion();
Connection conexion=c.conectar();
Caja_NuevaVenta cnn = new Caja_NuevaVenta();
Caja_MotivoAnulacion cnnA = new Caja_MotivoAnulacion();
Caja_Documento_Detalle cnnD = new Caja_Documento_Detalle();
Caja_SIS_Cabecera cnn1 = new Caja_SIS_Cabecera();
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
         Medicos.setLocationRelativeTo(null);//en el centro
         Medicos.getContentPane().setBackground(new Color(0,153,153)); 
         
         
         
         Anular.setLocationRelativeTo(null);//en el centro
         Anular.getContentPane().setBackground(Color.WHITE);

         panelMensaje.setVisible(false);
         panelEliminarDetalle.setVisible(false);
         panelCPT.setVisible(false);
         ///////////////////
         //txtfp.addKeyListener(manejador);
         //////////////////
         
         ///NO MOSTRAR
         //////////////////////////
          //preventa.setVisible(false);

          jTabbedPane1.setEnabledAt(0,false);
          jTabbedPane1.setEnabledAt(1, false);
         
          panelAbonos.setVisible(false);

//          b4.setVisible(false);
          nuevoanulacion.setVisible(false);
          panelPreventa.setVisible(false);
          resumen.setVisible(false);
          jScrollPane3.setVisible(false);
          tb_Grupo1.setVisible(false);
          jScrollPane7.setVisible(false);
          tb_Grupo6.setVisible(false);
          
          jScrollPane4.setVisible(false);
          panelEliminacion.setVisible(false);
          panelNumeros.setVisible(false);
          btnNuevo.setEnabled(true);
          btnguardar.setEnabled(false);
          btneliminar.setEnabled(false);
          
          btnCargarEME.setVisible(false);
          btnEliminarEME.setVisible(false);
          
          ////NO SE MUESTRA PRE VENTA
          btnbuscar4.setVisible(false);
          btnbuscar6.setVisible(false);
          jScrollPane14.setVisible(false);
          panelEliminarEME.setVisible(false);
          btnEliminarHOS.setVisible(false);
          panelEliminarHOS.setVisible(false);
          //////////////////////////
          elimma1.setVisible(false);
          cargareliminarma.setVisible(false);
          jPanel39.setVisible(false);
          sep1.setVisible(false);
          btnbuscar1.setVisible(false);
          btnbuscar8.setVisible(false);
          /////////////////////////
          
        jPanel25.setBackground(new Color(240,240,240)); 
        jPanel41.setBackground(new Color(0,153,255));
        jPanel43.setBackground(new Color(0,153,255));
        
        lblCPT.setVisible(false);
        panelCPT.setVisible(false);
        jScrollPane4.setVisible(false);
        lForma.setVisible(false);
        panelFormaPago.setVisible(false);
        lDoc.setVisible(false);
        lblNumeroDoc.setVisible(false);
        lTipoDoc.setVisible(false);
        cbxTipoDocumento.setVisible(false);
        panelAnular.setVisible(false);
        panelDatosGenerales.setVisible(false);
        panelActoMedico.setVisible(false);
               
         LISTAR();
         LISTARN();
         formatoj();
         if (txtBuscarPaciente.getText() == " "){
             jTabbedPane2.setSelectedIndex(0);
        }
        
 
   
       this.cbxTipoDocumento.setModel(tipo());
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
    private void sumaAbono()
    {
        double total = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for( int i=0 ; i<ABONOS.getRowCount() ; i++)
        {
            double numero =0,st=0;
            try{
                //capturamos valor de celda
                numero = Double.parseDouble(ABONOS.getValueAt(i, 2).toString() );
                
  
            }
            catch (NumberFormatException nfe){ //si existe un error se coloca 0 a la celda
                numero = 0;
                ABONOS.setValueAt(0, i, 2);
                System.out.println("error" + nfe.getMessage());
            }
            //se suma al total
          total += numero;
        }
        //muestra en el componente
        this.abonod.setText( String.valueOf(total) );
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
        
    public void listarMedicos(){
    String consulta="";
        try {
            tb_medicos.setModel(new DefaultTableModel());
            String titulos[]={"Código","Médico","Día","Mes","Año","Turno",};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
            consulta="EXEC CAJA_LISTA_MEDICO_TURNO ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, BMedicos.getText());
            cmd.setString(2, SE.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // nhc
                fila[3]=r.getString(4); //
                fila[4]=r.getString(5); // 
                fila[5]=r.getString(6); // id
                    m.addRow(fila);
                    c++;
            }
            tb_medicos.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_medicos.setRowSorter(elQueOrdena);
            tb_medicos.setModel(m);
            formatoListarMedicos();
        } catch (Exception e) {
            System.out.println("ErrorMEDICOS " + e.getMessage());
        }
    }
          
    public void listarMedicos1(){
    String consulta="";
        try {
            tb_medicos.setModel(new DefaultTableModel());
            String titulos[]={"Código","Médico","Día","Mes","Año","Turno",};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
            consulta="EXEC CAJA_LISTA_MEDICO_TURNO_TODO ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, SE.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // nhc
                fila[3]=r.getString(4); //
                fila[4]=r.getString(5); // 
                fila[5]=r.getString(6); // id
                    m.addRow(fila);
                    c++;
            }
            tb_medicos.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_medicos.setRowSorter(elQueOrdena);
            tb_medicos.setModel(m);
            formatoListarMedicos();
        } catch (Exception e) {
            System.out.println("Error: LISTAR MEDICOS: " + e.getMessage());
        }
    }
    public void formatoListarMedicos(){
        tb_medicos.getColumnModel().getColumn(0).setMinWidth(0);
        tb_medicos.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_medicos.getColumnModel().getColumn(1).setPreferredWidth(345);
        tb_medicos.getColumnModel().getColumn(2).setPreferredWidth(50);
        tb_medicos.getColumnModel().getColumn(3).setPreferredWidth(50);
        tb_medicos.getColumnModel().getColumn(4).setPreferredWidth(50);
        tb_medicos.getColumnModel().getColumn(5).setPreferredWidth(150);
        tb_medicos.setRowHeight(30);
    }
          
    public void Detalle(){
        String consulta="";
        try {
            tb_CPT.setModel(new DefaultTableModel());
         
             String titulos[]={"CPT","Cantidad","Precio","Dsct.","SubTotal","Consultorio","Atencion","Medico/Personal","Nº Atencion","Turno","cpt","idd","id"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Listar_Detalle_preventa ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, lblcodigo.getText());
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
            tb_CPT.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_CPT.setRowSorter(elQueOrdena);
            this.tb_CPT.setModel(m);
     
        } catch (Exception e) {
            System.out.println("Error doc: " + e.getMessage());
        }
      } 
    public void BusquedaGeneral(){
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
         
             String titulos[]={"Documento","Nº Documento","Forma de Pago","DNI","HC","C","Estado","Descuento","SubTotal","IGV","Total","Fecha","Hora","Am","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec CAJA_CONSULTAR_ACTOMEDICODNI ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar4.getText());
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
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            formatoCabecera();
        } catch (Exception e) {
            System.out.println("Error doc: " + e.getMessage());
        }
      }
       
    public void BusquedaDet(){
        String consulta="";
        try {
            tb_Grupo6.setModel(new DefaultTableModel());
         
             String titulos[]={"CPT","Precio","Servicio/Area/Dpto","Cantidad","Precio","Descuento","Total","Medico/Personal","Nº Atencion","Turno","doc"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec CAJA_CONSULTAR_ACTOMEDICODNI_DET ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, bus1.getText());
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
            tb_Grupo6.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo6.setRowSorter(elQueOrdena);
            this.tb_Grupo6.setModel(m);
            formatoCuerpo();
        } catch (Exception e) {
            System.out.println("Error doc: " + e.getMessage());
        }
      }
    public void LISTARMA(){
    try {
             String titulos[]={"Descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Anulacion_listar";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2); 
                    m.addRow(fila);
                    c++;
            }
            anulacion.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            anulacion.setRowSorter(elQueOrdena);
            this.anulacion.setModel(m);
            formatoA();
            
    } catch (Exception e) {
    }
}  
    public void LISTAR(){
    try {
             String titulos[]={"Codigo","Forma de Pago","Descripcion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Jerarquia_LISTAR";
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
             String titulos[]={"Nomeclatura","Descripcion","Precio","Forma de Pago","Decripcion Forma Pago","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

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
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
      
                    m.addRow(fila);
                    c++;
            }
            tb_CPTBUSCAR.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_CPTBUSCAR.setRowSorter(elQueOrdena);
            this.tb_CPTBUSCAR.setModel(m);
            formatocpt();
            
    } catch (Exception e) {
    }
}  
    public void Abonnos(){
        String consulta="";
        try {
            ABONOS.setModel(new DefaultTableModel());
         
             String titulos[]={"Documento","Cliente","monto","fecha","hora","id"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec CAJA_VENTA_ABOBO ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, bus3.getText());
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
            ABONOS.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            ABONOS.setRowSorter(elQueOrdena);
            this.ABONOS.setModel(m);
            
        } catch (Exception e) {
            System.out.println("Error preventa: " + e.getMessage());
        }
      }
    public void Preventamostrar(){
        String consulta="";
        try {
            tbpreventas.setModel(new DefaultTableModel());
         
             String titulos[]={"Nº Preventa","ID_H.C.","Modulo","CodNomen","Nomenclatura","Descripcion","Medico","Fecha","Hora"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_Verificar_PreVenta ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, bus.getText());
            ResultSet r = cmd.executeQuery();
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
            tbpreventas.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbpreventas.setRowSorter(elQueOrdena);
            this.tbpreventas.setModel(m);
            
        } catch (Exception e) {
            System.out.println("Error preventa: " + e.getMessage());
        }
      }
        
    public void PreventaEME(){
        String consulta="";
        try {
            tEME.setModel(new DefaultTableModel());
         
             String titulos[]={"Nº","DNI","H.C.","Cliente","Modulo","Medico","Fecha","Hora","id","estado","codm","conom","ARID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];
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
            fila[10]=r.getString(11);
            fila[11]=r.getString(12);
            fila[12]=r.getString(13);
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
    public void PreventaHOS(){
        String consulta="";
        try {
            tHOS.setModel(new DefaultTableModel());
         
             String titulos[]={"Nº","DNI","H.C.","Cliente","Modulo","Indicaciones","Procedencia","Medico","Fecha","Hora","id","estado","COD_MED","CPT","caid","PROC","ACT","ARID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[18];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_Mostrar_Preventas_HOS ?";      
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
            fila[10]=r.getString(11);
            fila[11]=r.getString(12);
            fila[12]=r.getString(13);
            fila[13]=r.getString(14);
            fila[14]=r.getString(15);
            fila[15]=r.getString(16);
            fila[16]=r.getString(17);
            fila[17]=r.getString(18);
                m.addRow(fila);
                c++;
            }
            tHOS.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tHOS.setRowSorter(elQueOrdena);
            this.tHOS.setModel(m);
            formatoHOS();
        } catch (Exception e) {
            System.out.println("Error preventa HOS: " + e.getMessage());
        }
      }
        
    public void PreventaEMEDET(){
        String consulta="";
        try {
            tEMEDET.setModel(new DefaultTableModel());
         
             String titulos[]={"CPT","Descripcion","Precio","Fecha","Hora","cod","idDp","ESTADO","IDP","ARID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_Mostrar_DETALLE_Preventas ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, lblIdPreventas.getText());
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
            tEMEDET.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tEMEDET.setRowSorter(elQueOrdena);
            this.tEMEDET.setModel(m);
            formatoEMEDET();
        } catch (Exception e) {
            System.out.println("Error preventa detalleeeeeeeeeeeeeeeeee: " + e.getMessage());
        }
      }
    
    public void PreventaHOSDET(){
        String consulta="";
        try {
            tHOSDET.setModel(new DefaultTableModel());
         
             String titulos[]={"CPT","Descripcion","","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_Mostrar_DETALLE_Preventas_HOS ?";      
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
            
                m.addRow(fila);
                c++;
            }
            tHOSDET.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tHOSDET.setRowSorter(elQueOrdena);
            this.tHOSDET.setModel(m);
            
        } catch (Exception e) {
            System.out.println("Error PREVENTA HOS DETALLE: " + e.getMessage());
        }
      }
    
    public void BuscarJ(){
        String consulta="";
        try {
            tb_Grupo3.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","Forma de Pago","Descripcion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_Jerarquia_BUSCAR ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar3.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
                m.addRow(fila);
                c++;
            }
            tb_Grupo3.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo3.setRowSorter(elQueOrdena);
            this.tb_Grupo3.setModel(m);
            formatoj();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
     
    public void BuscarHC(){
        String consulta="";
        try {
            tbPacientes.setModel(new DefaultTableModel());
             String titulos[]={"Nº H.C.","Paciente","Direccion","DNI","Sexo","Fecha","Edad",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_BuscarHC ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarPaciente.getText());
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
            tbPacientes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPacientes.setRowSorter(elQueOrdena);
            this.tbPacientes.setModel(m);
            formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
     
    public void BuscarN(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_CPTBUSCAR.setModel(new DefaultTableModel());
             String titulos[]={"Nomeclatura","Descripcion","Precio","Forma de Pago","Decripcion Forma Pago","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_NomenclaturaVentaBUSCAR ?,?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar2.getText());
            cmd.setString(2, txtFormaPago.getText());
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
            tb_CPTBUSCAR.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_CPTBUSCAR.setRowSorter(elQueOrdena);
            this.tb_CPTBUSCAR.setModel(m);

           formatocpt();
         
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
      public void formatocpt(){
    tb_CPTBUSCAR.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_CPTBUSCAR.getColumnModel().getColumn(1).setPreferredWidth(500);
    tb_CPTBUSCAR.getColumnModel().getColumn(2).setPreferredWidth(80);
    tb_CPTBUSCAR.getColumnModel().getColumn(3).setPreferredWidth(100);
    tb_CPTBUSCAR.getColumnModel().getColumn(4).setPreferredWidth(100);

    
    tb_CPTBUSCAR.getColumnModel().getColumn(5).setMinWidth(0);
    tb_CPTBUSCAR.getColumnModel().getColumn(5).setMaxWidth(0);
    tb_CPTBUSCAR.getColumnModel().getColumn(6).setMinWidth(0);
    tb_CPTBUSCAR.getColumnModel().getColumn(6).setMaxWidth(0);
    tb_CPTBUSCAR.getColumnModel().getColumn(7).setMinWidth(0);
    tb_CPTBUSCAR.getColumnModel().getColumn(7).setMaxWidth(0);
    tb_CPTBUSCAR.getColumnModel().getColumn(8).setMinWidth(0);
    tb_CPTBUSCAR.getColumnModel().getColumn(8).setMaxWidth(0);
    tb_CPTBUSCAR.setRowHeight(30);

    }
    
    public void formatoEME(){
    tEME.getColumnModel().getColumn(0).setMinWidth(0);
    tEME.getColumnModel().getColumn(0).setMaxWidth(0);
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
    tEME.getColumnModel().getColumn(10).setMinWidth(0);
    tEME.getColumnModel().getColumn(10).setMaxWidth(0);
    tEME.getColumnModel().getColumn(11).setMinWidth(0);
    tEME.getColumnModel().getColumn(11).setMaxWidth(0);
    tEME.getColumnModel().getColumn(12).setMinWidth(0);
    tEME.getColumnModel().getColumn(12).setMaxWidth(0);
    }
    
    public void formatoHOS(){
    tHOS.getColumnModel().getColumn(0).setMinWidth(0);
    tHOS.getColumnModel().getColumn(0).setMaxWidth(0);
    tHOS.getColumnModel().getColumn(1).setPreferredWidth(80);
    tHOS.getColumnModel().getColumn(2).setMinWidth(0);
    tHOS.getColumnModel().getColumn(2).setMaxWidth(0);
    tHOS.getColumnModel().getColumn(3).setPreferredWidth(300);
    tHOS.getColumnModel().getColumn(4).setPreferredWidth(60);
    tHOS.getColumnModel().getColumn(5).setPreferredWidth(100);
    tHOS.getColumnModel().getColumn(6).setPreferredWidth(90);
    tHOS.getColumnModel().getColumn(7).setPreferredWidth(100);
    tHOS.getColumnModel().getColumn(8).setPreferredWidth(80);

    tHOS.getColumnModel().getColumn(9).setMinWidth(0);
    tHOS.getColumnModel().getColumn(9).setMaxWidth(0);
    tHOS.getColumnModel().getColumn(10).setMinWidth(0);
    tHOS.getColumnModel().getColumn(10).setMaxWidth(0);
    tHOS.getColumnModel().getColumn(11).setMinWidth(0);
    tHOS.getColumnModel().getColumn(11).setMaxWidth(0);
    tHOS.getColumnModel().getColumn(12).setMinWidth(0);
    tHOS.getColumnModel().getColumn(12).setMaxWidth(0);
    tHOS.getColumnModel().getColumn(13).setMinWidth(0);
    tHOS.getColumnModel().getColumn(13).setMaxWidth(0);
    tHOS.getColumnModel().getColumn(14).setMinWidth(0);
    tHOS.getColumnModel().getColumn(14).setMaxWidth(0);
    tHOS.getColumnModel().getColumn(15).setMinWidth(0);
    tHOS.getColumnModel().getColumn(15).setMaxWidth(0);
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
    tbPacientes.getColumnModel().getColumn(0).setPreferredWidth(220);
    tbPacientes.getColumnModel().getColumn(1).setPreferredWidth(480);
    tbPacientes.getColumnModel().getColumn(2).setPreferredWidth(400);
    tbPacientes.getColumnModel().getColumn(3).setPreferredWidth(150);
    tbPacientes.getColumnModel().getColumn(4).setPreferredWidth(70);
    tbPacientes.getColumnModel().getColumn(6).setPreferredWidth(70);

    tbPacientes.getColumnModel().getColumn(5).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(5).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(7).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(7).setMaxWidth(0);
    tbPacientes.setRowHeight(30);
    }
    
    public void formatoj(){
    tb_Grupo3.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Grupo3.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Grupo3.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo3.getColumnModel().getColumn(2).setPreferredWidth(500);
    tb_Grupo3.setRowHeight(30);

    }
     public void formatoCabecera(){
    
    tb_Grupo1.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(200);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(4).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(4).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(5).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(5).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(6).setPreferredWidth(200);
    tb_Grupo1.getColumnModel().getColumn(7).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(8).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(10).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(11).setPreferredWidth(200);
    tb_Grupo1.getColumnModel().getColumn(12).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(13).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(13).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(14).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(14).setMaxWidth(0);

    }
    public void formatoCuerpo(){
    
    tb_Grupo6.getColumnModel().getColumn(0).setPreferredWidth(430);
    tb_Grupo6.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Grupo6.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Grupo6.getColumnModel().getColumn(2).setPreferredWidth(150);
    tb_Grupo6.getColumnModel().getColumn(3).setPreferredWidth(80);
    tb_Grupo6.getColumnModel().getColumn(4).setPreferredWidth(80);
    tb_Grupo6.getColumnModel().getColumn(5).setPreferredWidth(80);
    tb_Grupo6.getColumnModel().getColumn(6).setPreferredWidth(100);
    tb_Grupo6.getColumnModel().getColumn(7).setPreferredWidth(220);
    tb_Grupo6.getColumnModel().getColumn(8).setPreferredWidth(80);
    tb_Grupo6.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Grupo6.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Grupo6.getColumnModel().getColumn(10).setMaxWidth(0);

    }
    
     public void formatoA(){
    anulacion.getColumnModel().getColumn(0).setPreferredWidth(220);
    anulacion.getColumnModel().getColumn(1).setMinWidth(0);
    anulacion.getColumnModel().getColumn(1).setMaxWidth(0);
    }
    public void Guardar(){
 
                Caja_NuevaVenta AME = new Caja_NuevaVenta();
                AME.setID_ACTOMEDICO1(Integer.parseInt(lblActoMedico.getText() ));
                AME.setNUM_ACTOMEDICO(Integer.parseInt(lblActoMedico.getText() ));
                AME.setFECHA_TERMINO(" ");
                AME.setDURACION(" ");
                if(AME.NuevoActoMedico()==true){
                           System.out.println("Guardado actoo medico");
                           btnguardar.setEnabled(false);
                           btneliminar.setEnabled(true);
  
                       } else {
                             System.out.println("erorr");
                       }                    
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setId_documento(lblcodigo.getText());
                cno1.setCod_tipo_documento(cno1.codTipo(cbxTipoDocumento.getSelectedItem().toString()));      
                cno1.setCod_jerar_forma_pago(formap.getText());
                cno1.setId_hc(lblhc.getText());
                cno1.setCod_motiv_anu(sinanular.getText());
                cno1.setSerie_documento(lblNumeroDoc.getText());/////////falta
                cno1.setNum_documento(lblNumeroDoc.getText());
                cno1.setDependencia("CAJA CONTA");//
                cno1.setCod_usu(cno1.codUsuario(lblusu.getText()));
                cno1.setId_liquidacion(" ");/////////falta
                cno1.setCorrelativo(2);
                cno1.setId_ActoMedico(Integer.parseInt(lblActoMedico.getText() ));
                cno1.setId_Cta_Abono(0);
                
                    if(cno1.Nuevo()==true){
                           System.out.println("Guardado cabecera");
                           tg=2;
                           btnguardar.setEnabled(false);
                           AMN.setText(lblActoMedico.getText());
  
                       } else {
                             System.out.println("erorr cabecera");
                       }     
 }
    public void GuardarDetalle(){
        
                Caja_Documento_Detalle cnvd = new Caja_Documento_Detalle();
               // cnvd.setId_cod_doc_det(Integer.parseInt(CodDet.getText() ));
                cnvd.setId_documento(lblcodigo.getText());     
                cnvd.setCod_precio(cnvd.CodPrecio(NCP.getText()));
                cnvd.setNom_consultorio_citas("Consultorio");
                cnvd.setCantidad_detalle(Double.parseDouble("1"));
                cnvd.setPrecio_detalle(Double.parseDouble(cnvd.CodPrecio1(NCP.getText())));
                cnvd.setTotal_detalle(Double.parseDouble(cnvd.CodPrecio1(NCP.getText())));
                cnvd.setDescu_exo_detalle(Double.parseDouble("0"));
                cnvd.setPersonal_aten(lblIdMedico.getText());  
                cnvd.setNum_aten("2");
                cnvd.setTurno_cita("Mañana");
                cnvd.setCod_usu(cnvd.codUsuario(lblusu.getText()));
                
                    if(cnvd.DetalleVenta()==true){
                         Detalle();
                         PreventaHOSDET();
                           panelMensaje.setVisible(true);   
                           panelMensaje.setBackground(new Color(0,153,102)); 
                           Mensaje4.setText("Datos Guardados de forma correcta");
                           eli4.setVisible(true);
                           eli4.setText("OK");
                           noeli4.setVisible(false);
                           System.out.println("Guardado detalle");
                           tgDetalle=0;
 
                           
                            jScrollPane4.setVisible(true);
                            btnguardar.setEnabled(true);
           
                         
  
                       } else {
                                panelMensaje.setVisible(true);
                                panelMensaje.setBackground(new Color(255,91,70)); 
                                Mensaje4.setText("Ocurrio un error verifique");
                                eli4.setVisible(false);
                                noeli4.setVisible(false);
                             System.out.println("erorr detalle");
                       }     
 }
    
        public void GuardarSIS(){
        
                Caja_SIS_Cabecera cnoS = new Caja_SIS_Cabecera();
                cnoS.setID_FUA(fua.getText());
                cnoS.setID_DOCUMENTO(lblcodigo.getText());
                cnoS.setID_ESTABLECIMIENTO(Integer.parseInt("1" ));
                cnoS.setATENCION("Ambulatoria");

                    if(cnoS.nuevoSIS()==true){
                           System.out.println("Guardado sis");
                           tg=2;
                           btnguardar.setEnabled(false);
  
                       } else {
                             System.out.println("erorr sis");
                       }     
 }
        
 public void GuardarAnulacion(){
        
                Caja_MotivoAnulacion cnoa = new Caja_MotivoAnulacion();
                cnoa.setCod_motiv_anu(lblcodanu.getText());
                cnoa.setDescripcion_anulacion(txtanular.getText());
                cnoa.setNom_usu(cnoa.codUsuario(lblusu.getText()));
     

                    if(cnoa.NuevoAnulacion()==true){
                        codelim.setText(lblcodanu.getText());
                        M.setText(txtanular.getText());
                        cargareliminarma.setBackground(new Color(0,153,102)); 
                        Mensaje2.setText("Guardado! Anular venta con este motivo?");
                        tge=9;
                        cargareliminarma.setVisible(true);
                        eli2.setVisible(true);
                        noeli2.setVisible(true); 
                        elimma1.setVisible(false);
                        elimma.setVisible(false);
                        btnbuscar8.setEnabled(false);
                        txtanular.setText("");
                           //btneditar.setEnabled(true);
  
                       } else {
                             System.out.println("erorr motivo anulacion");
                       }     
 }
                
 public void AnularDocumento(){
    
        
                Caja_NuevaVenta cno2 = new Caja_NuevaVenta();
                cno2.setId_documento(lblcod.getText());
                cno2.setCod_motiv_anu(codelim.getText());
                cno2.setDevolucion_doc(Double.parseDouble("0"));///////////////////FALTA PÀRAMETRO
                cno2.setCod_usu_anu(cno2.codUsuario(lblusu.getText()));
     

                    if(cno2.modificarAnulacion()==true){
                        tge=4;
                        System.out.println("anulado");
                        panelMensaje.setBackground(new Color(0,153,102)); 
                        Mensaje4.setText("Venta Anulada Correctamente");
                        lblcodigo.setText("");
                        panelMensaje.setVisible(true);
                        noeli4.setText("Ok");
                        eli4.setVisible(false);
                        noeli4.setVisible(true); 
                        elimma1.setVisible(false);
                        elimma.setVisible(false);

                           //btneditar.setEnabled(true);
  
                       } else {
                             System.out.println("erorr motivo anulacion");
                       }     
 }
     public void ModificarPreventaCEX(){

                        Caja_Preventa cnop = new Caja_Preventa();
                        cnop.setId_preventa(Integer.parseInt(lblIdPreventas.getText()));
                        cnop.setACTO_MEDICO(Integer.parseInt(lblActoMedico.getText()));  
                        cnop.setCod_jerar_forma_pago(formap.getText());
                        cnop.setCod_medico(lblIdMedico.getText());
                        
                        if(cnop.modificarPreventaCEX()==true){
                                   System.out.println("PreventaModificada");
                                   panelPreventa.setVisible(false);
                        } else {
                           
                                panelEliminarEME.setVisible(true);
                                panelEliminarEME.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrio un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                System.out.println("error modificar preventa");
                         
                        }
                       
    }
     
     public void ModificarPreventa(){

                        Caja_Preventa cnop = new Caja_Preventa();
                        cnop.setId_preventa(Integer.parseInt(lblIdPreventas.getText()));
                        cnop.setACTO_MEDICO(Integer.parseInt(lblActoMedico.getText()));  
                        cnop.setCod_jerar_forma_pago(formap.getText());
                        
                        if(cnop.modificarPreventa()==true){
                                   System.out.println("PreventaModificada ");
                                   panelPreventa.setVisible(false);
                        } else {
                           
                                panelEliminarEME.setVisible(true);
                                panelEliminarEME.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrio un error, Verifique  ");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                System.out.println("error modificar preventa ");
                         
                        }
                       
    }
     
         public void AsignarCamas(){
  
                        Caja_Preventa cnoac = new Caja_Preventa();
                        cnoac.setCA_ID(Integer.parseInt(ca_id.getText()));
                      
                        if(cnoac.camas()==true){
                                   System.out.println("cama asignada");
                                 
                        } else {
                           
                                System.out.println("cama NO asignada");
                         
                        }
                       
    }
 /////////////////////////////ANULAR PREVENTA CAMA Y ASIGNACION CAMA
 public void AnularCamas(){
  
                        Caja_Preventa cnoac = new Caja_Preventa();
                        cnoac.setCA_ID(Integer.parseInt(ca_id.getText()));
                      
                        if(cnoac.anularcamas()==true){
                                   System.out.println("cama ANULADA");
                                 
                        } else {
                           
                                System.out.println("cama NO ANULADA");
                         
                        }
                       
    }
 
  public void AnularPreventa(){
  
                        Caja_Preventa cnoac = new Caja_Preventa();
                        cnoac.setId_preventa(Integer.parseInt(lblIdPreventas.getText()));
                      
                        if(cnoac.anularpreventa()==true){
                                   System.out.println("PREVENTA ANULADA");
                                 
                        } else {
                           
                                System.out.println("PREVENTA  NO ANULADA");
                         
                        }
                       
    }
     public void AnularAsignacionCamas(){
  
                        Caja_Preventa cnoac = new Caja_Preventa();
                        cnoac.setId_preventa(Integer.parseInt(lblIdPreventas.getText()));
                      
                        if(cnoac.anularasigcamas()==true){
                                   System.out.println("ASIGNACION DE CAMA ANULADA");
                                 
                        } else {
                           
                                System.out.println("ASIGNACION DE CAMA  NO ANULADA");
                         
                        }
                       
    }     
         
////////////////////////////////////////////////////////////////////////
 
 public void NuevaV(){
        lblcodigo.setText(cnn.id());
        sinanular.setText(cnn.sinanulacion());
        lblActoMedico.setText(cnn.ActoMedico());
        tg=1;
    
        btnguardar.setEnabled(true);
        btneliminar.setEnabled(false);
        cbxTipoDocumento.setEnabled(true);

        btnBuscarFormaPago.setEnabled(true);
        btnBuscarCPT.setEnabled(true);
        txthc.setEnabled(true);
        txtCPT.setEnabled(true);
        txtCPT.setEditable(false);
        txtFormaPago.setEnabled(true);
        txtFormaPago.setEditable(false);
        total.setEnabled(true);
        total.setEditable(false);
    
     
        jTabbedPane1.setSelectedIndex(1); 
        
        panelPreventa.setVisible(false);
        BHC.setVisible(true);
        txtBuscarPaciente.setText("");
        jTabbedPane2.setSelectedIndex(0);
        
        
        
        
                 ///NO MOSTRAR
         //////////////////////////
          //preventa.setVisible(false);
//          b4.setVisible(false);
          nuevoanulacion.setVisible(false);
          panelPreventa.setVisible(false);
          
          jScrollPane4.setVisible(false);
          panelEliminacion.setVisible(false);
          panelNumeros.setVisible(false);
          btnNuevo.setEnabled(true);
        
          btneliminar.setEnabled(false);
         
          btnCargarEME.setVisible(false);
          btnEliminarEME.setVisible(false);
          
          

          lblDireccion.setEnabled(false);
          lblDNI.setEnabled(false);
          txtedad.setEnabled(false);
          sexo.setEnabled(false);
          txtFormaPago.setText("");
          txthc.setText("");
  
          lblDireccion.setText("");
          lblDNI.setText("");
          txtedad.setText("");
          sexo.setText("");
          
          ////NO SE MUESTRA PRE VENTA
          btnbuscar4.setVisible(false);
          btnbuscar6.setVisible(false);
         
          jScrollPane14.setVisible(false);
          panelEliminarEME.setVisible(false);
          //////////////////////////
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
        txtBuscarPaciente = new javax.swing.JTextField();
        T3 = new javax.swing.JLabel();
        bus3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        ABONOS = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jTabbedPane2 = new javax.swing.JTabbedPane();
            jPanel4 = new javax.swing.JPanel();
            jLabel9 = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            jScrollPane2 = new javax.swing.JScrollPane();
            tbPacientes = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                lblIdPreventa = new javax.swing.JLabel();
                jScrollPane9 = new javax.swing.JScrollPane();
                tbpreventas = new javax.swing.JTable(){
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
                    jPanel29 = new javax.swing.JPanel();
                    txtBuscar3 = new javax.swing.JTextField();
                    T5 = new javax.swing.JLabel();
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
                        tb_CPTBUSCAR = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jPanel15 = new javax.swing.JPanel();
                            jLabel23 = new javax.swing.JLabel();
                            jLabel24 = new javax.swing.JLabel();
                            jPanel16 = new javax.swing.JPanel();
                            btnNuevo2 = new javax.swing.JButton();
                            jLabel25 = new javax.swing.JLabel();
                            preventas = new javax.swing.JDialog();
                            jPanel18 = new javax.swing.JPanel();
                            jLabel29 = new javax.swing.JLabel();
                            btnbuscar6 = new javax.swing.JButton();
                            jLabel34 = new javax.swing.JLabel();
                            elimdp = new javax.swing.JLabel();
                            jPanel24 = new javax.swing.JPanel();
                            jLabel35 = new javax.swing.JLabel();
                            jPanel25 = new javax.swing.JPanel();
                            btnbuscar4 = new javax.swing.JButton();
                            jPanel31 = new javax.swing.JPanel();
                            jPanel41 = new javax.swing.JPanel();
                            jLabel37 = new javax.swing.JLabel();
                            jPanel42 = new javax.swing.JPanel();
                            jPanel43 = new javax.swing.JPanel();
                            jLabel41 = new javax.swing.JLabel();
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
                                    btnCargarEME = new javax.swing.JButton();
                                    btnEliminarEME = new javax.swing.JButton();
                                    panelEliminarEME = new javax.swing.JPanel();
                                    Mensaje = new javax.swing.JLabel();
                                    eli = new javax.swing.JButton();
                                    noeli = new javax.swing.JButton();
                                    jPanel40 = new javax.swing.JPanel();
                                    jScrollPane22 = new javax.swing.JScrollPane();
                                    tHOS = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};
                                        jScrollPane23 = new javax.swing.JScrollPane();
                                        tHOSDET = new javax.swing.JTable(){
                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                return false; //Disallow the editing of any cell
                                            }};
                                            btnCargarHOS = new javax.swing.JButton();
                                            btnEliminarHOS = new javax.swing.JButton();
                                            panelEliminarHOS = new javax.swing.JPanel();
                                            Mensaje3 = new javax.swing.JLabel();
                                            eli3 = new javax.swing.JButton();
                                            noeli3 = new javax.swing.JButton();
                                            jPanel26 = new javax.swing.JPanel();
                                            jScrollPane15 = new javax.swing.JScrollPane();
                                            tb_CEX = new javax.swing.JTable(){
                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                    return false; //Disallow the editing of any cell
                                                }};
                                                btnCargarCEX = new javax.swing.JButton();
                                                btnEliminarCEX = new javax.swing.JButton();
                                                panelEliminarCEX = new javax.swing.JPanel();
                                                Mensaje6 = new javax.swing.JLabel();
                                                eli7 = new javax.swing.JButton();
                                                noeli7 = new javax.swing.JButton();
                                                Anular = new javax.swing.JDialog();
                                                jPanel19 = new javax.swing.JPanel();
                                                jLabel31 = new javax.swing.JLabel();
                                                btnbuscar7 = new javax.swing.JButton();
                                                btnbuscar8 = new javax.swing.JButton();
                                                btnbuscar1 = new javax.swing.JButton();
                                                lblcod = new javax.swing.JLabel();
                                                codelim = new javax.swing.JLabel();
                                                btnbuscar2 = new javax.swing.JButton();
                                                nuevoanulacion = new javax.swing.JPanel();
                                                jLabel33 = new javax.swing.JLabel();
                                                txtanular = new javax.swing.JTextField();
                                                lblcodanu = new javax.swing.JLabel();
                                                M = new javax.swing.JLabel();
                                                elimma = new javax.swing.JButton();
                                                cargareliminarma = new javax.swing.JPanel();
                                                Mensaje2 = new javax.swing.JLabel();
                                                eli2 = new javax.swing.JButton();
                                                noeli2 = new javax.swing.JButton();
                                                jPanel39 = new javax.swing.JPanel();
                                                jScrollPane21 = new javax.swing.JScrollPane();
                                                anulacion = new javax.swing.JTable(){
                                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                                        return false; //Disallow the editing of any cell
                                                    }};
                                                    sep1 = new javax.swing.JPanel();
                                                    jLabel32 = new javax.swing.JLabel();
                                                    CBXANULAR = new javax.swing.JComboBox();
                                                    elimma1 = new javax.swing.JButton();
                                                    abono = new javax.swing.JDialog();
                                                    jPanel30 = new javax.swing.JPanel();
                                                    jLabel42 = new javax.swing.JLabel();
                                                    Cliente = new javax.swing.JLabel();
                                                    jLabel46 = new javax.swing.JLabel();
                                                    cargareliminar1 = new javax.swing.JPanel();
                                                    Mensaje1 = new javax.swing.JLabel();
                                                    eli1 = new javax.swing.JButton();
                                                    noeli1 = new javax.swing.JButton();
                                                    abonod = new javax.swing.JLabel();
                                                    adni = new javax.swing.JLabel();
                                                    adni1 = new javax.swing.JLabel();
                                                    Medicos = new javax.swing.JDialog();
                                                    jPanel46 = new javax.swing.JPanel();
                                                    jLabel62 = new javax.swing.JLabel();
                                                    jPanel47 = new javax.swing.JPanel();
                                                    BMedicos = new javax.swing.JTextField();
                                                    T8 = new javax.swing.JLabel();
                                                    jLabel56 = new javax.swing.JLabel();
                                                    jTabbedPane7 = new javax.swing.JTabbedPane();
                                                    jPanel50 = new javax.swing.JPanel();
                                                    jScrollPane16 = new javax.swing.JScrollPane();
                                                    tb_medicos = new javax.swing.JTable(){
                                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                                            return false; //Disallow the editing of any cell
                                                        }};
                                                        jPanel51 = new javax.swing.JPanel();
                                                        jLabel65 = new javax.swing.JLabel();
                                                        jLabel66 = new javax.swing.JLabel();
                                                        jTabbedPane1 = new javax.swing.JTabbedPane();
                                                        jPanel2 = new javax.swing.JPanel();
                                                        jScrollPane3 = new javax.swing.JScrollPane();
                                                        tb_Grupo1 = new javax.swing.JTable(){
                                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                                return false; //Disallow the editing of any cell
                                                            }};
                                                            jScrollPane7 = new javax.swing.JScrollPane();
                                                            tb_Grupo6 = new javax.swing.JTable(){
                                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                                    return false; //Disallow the editing of any cell
                                                                }};
                                                                resumen = new javax.swing.JPanel();
                                                                HCI = new javax.swing.JLabel();
                                                                DNII = new javax.swing.JLabel();
                                                                jLabel44 = new javax.swing.JLabel();
                                                                ACTM = new javax.swing.JLabel();
                                                                APENOM = new javax.swing.JLabel();
                                                                jLabel48 = new javax.swing.JLabel();
                                                                jLabel49 = new javax.swing.JLabel();
                                                                bus1 = new javax.swing.JLabel();
                                                                jPanel3 = new javax.swing.JPanel();
                                                                lForma = new javax.swing.JLabel();
                                                                lTipoDoc = new javax.swing.JLabel();
                                                                lDoc = new javax.swing.JLabel();
                                                                nom = new javax.swing.JLabel();
                                                                lblcodigo = new javax.swing.JLabel();
                                                                lblhc = new javax.swing.JLabel();
                                                                cbxTipoDocumento = new javax.swing.JComboBox();
                                                                lblCPT = new javax.swing.JLabel();
                                                                jScrollPane4 = new javax.swing.JScrollPane();
                                                                tb_CPT = new javax.swing.JTable(){
                                                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                                                        return false; //Disallow the editing of any cell
                                                                    }};
                                                                    panelNumeros = new javax.swing.JPanel();
                                                                    jLabel26 = new javax.swing.JLabel();
                                                                    total = new javax.swing.JTextField();
                                                                    total1 = new javax.swing.JTextField();
                                                                    jLabel27 = new javax.swing.JLabel();
                                                                    total2 = new javax.swing.JTextField();
                                                                    jLabel28 = new javax.swing.JLabel();
                                                                    total3 = new javax.swing.JTextField();
                                                                    jLabel39 = new javax.swing.JLabel();
                                                                    panelPreventa = new javax.swing.JPanel();
                                                                    jLabel15 = new javax.swing.JLabel();
                                                                    btnbuscar5 = new javax.swing.JButton();
                                                                    jLabel36 = new javax.swing.JLabel();
                                                                    sinanular = new javax.swing.JLabel();
                                                                    formap = new javax.swing.JLabel();
                                                                    ok = new javax.swing.JLabel();
                                                                    fua = new javax.swing.JLabel();
                                                                    panelDatosGenerales = new javax.swing.JPanel();
                                                                    txtape = new javax.swing.JLabel();
                                                                    jLabel11 = new javax.swing.JLabel();
                                                                    lblDNI = new javax.swing.JLabel();
                                                                    jLabel12 = new javax.swing.JLabel();
                                                                    lblDireccion = new javax.swing.JLabel();
                                                                    jLabel14 = new javax.swing.JLabel();
                                                                    lblHC = new javax.swing.JLabel();
                                                                    panelMensaje = new javax.swing.JPanel();
                                                                    Mensaje4 = new javax.swing.JLabel();
                                                                    eli4 = new javax.swing.JButton();
                                                                    noeli4 = new javax.swing.JButton();
                                                                    panelFormaPago = new javax.swing.JPanel();
                                                                    txtFormaPago = new javax.swing.JTextField();
                                                                    btnBuscarFormaPago = new javax.swing.JButton();
                                                                    lblNumeroDoc = new javax.swing.JLabel();
                                                                    panelActoMedico = new javax.swing.JPanel();
                                                                    jLabel30 = new javax.swing.JLabel();
                                                                    lblActoMedico = new javax.swing.JLabel();
                                                                    lblDescripcion = new javax.swing.JLabel();
                                                                    txthc = new javax.swing.JLabel();
                                                                    lblAREA = new javax.swing.JLabel();
                                                                    SE = new javax.swing.JLabel();
                                                                    NCP = new javax.swing.JLabel();
                                                                    lblIdPreventas = new javax.swing.JLabel();
                                                                    lblIdMedico = new javax.swing.JLabel();
                                                                    MHOS = new javax.swing.JLabel();
                                                                    MODULO = new javax.swing.JLabel();
                                                                    AMA = new javax.swing.JLabel();
                                                                    AMN = new javax.swing.JLabel();
                                                                    abonos = new javax.swing.JLabel();
                                                                    txtedad = new javax.swing.JTextField();
                                                                    sexo = new javax.swing.JTextField();
                                                                    fp = new javax.swing.JLabel();
                                                                    panelAbonos = new javax.swing.JPanel();
                                                                    ca_id = new javax.swing.JLabel();
                                                                    panelCPT = new javax.swing.JPanel();
                                                                    txtCPT = new javax.swing.JTextField();
                                                                    btnBuscarCPT = new javax.swing.JButton();
                                                                    panelEliminacion = new javax.swing.JPanel();
                                                                    btnEliminarDetalle = new javax.swing.JButton();
                                                                    panelEliminarDetalle = new javax.swing.JPanel();
                                                                    Mensaje5 = new javax.swing.JLabel();
                                                                    eli5 = new javax.swing.JButton();
                                                                    noeli5 = new javax.swing.JButton();
                                                                    panelAnular = new javax.swing.JPanel();
                                                                    btnbuscar9 = new javax.swing.JButton();
                                                                    jLabel38 = new javax.swing.JLabel();
                                                                    eli6 = new javax.swing.JButton();
                                                                    jPanel17 = new javax.swing.JPanel();
                                                                    noeli6 = new javax.swing.JButton();
                                                                    jPanel21 = new javax.swing.JPanel();
                                                                    jLabel57 = new javax.swing.JLabel();
                                                                    lblusu = new javax.swing.JLabel();
                                                                    btnNuevo = new javax.swing.JButton();
                                                                    jPanel23 = new javax.swing.JPanel();
                                                                    txtBuscar4 = new javax.swing.JTextField();
                                                                    btnguardar = new javax.swing.JButton();
                                                                    btnBuscarPaciente = new javax.swing.JButton();
                                                                    btneliminar = new javax.swing.JButton();
                                                                    lbldetalle = new javax.swing.JLabel();

                                                                    BHC.setAlwaysOnTop(true);
                                                                    BHC.setMinimumSize(new java.awt.Dimension(749, 338));
                                                                    BHC.setResizable(false);
                                                                    BHC.getContentPane().setLayout(null);

                                                                    jPanel7.setBackground(new java.awt.Color(43, 43, 43));
                                                                    jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                    jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                    jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel19.setText("Paciente");

                                                                    jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    jLabel13.setForeground(new java.awt.Color(204, 204, 204));
                                                                    jLabel13.setText("Busqueda por DNI, H.C. y Apellidos");

                                                                    bus.setForeground(new java.awt.Color(43, 43, 43));
                                                                    bus.setText("jLabel37");

                                                                    jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                                                                    txtBuscarPaciente.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                                    txtBuscarPaciente.setForeground(new java.awt.Color(51, 51, 51));
                                                                    txtBuscarPaciente.setBorder(null);
                                                                    txtBuscarPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                            txtBuscarPacienteCaretUpdate(evt);
                                                                        }
                                                                    });
                                                                    txtBuscarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            txtBuscarPacienteMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    txtBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            txtBuscarPacienteActionPerformed(evt);
                                                                        }
                                                                    });
                                                                    txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            txtBuscarPacienteKeyPressed(evt);
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
                                                                            .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                                                .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    );

                                                                    bus3.setForeground(new java.awt.Color(43, 43, 43));
                                                                    bus3.setText("jLabel37");

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
                                                                                    .addComponent(bus)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(bus3)))
                                                                            .addContainerGap(379, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel7Layout.setVerticalGroup(
                                                                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(bus)
                                                                                        .addComponent(bus3)))
                                                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                                                    .addComponent(jLabel19)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addGap(0, 0, 0)
                                                                                    .addComponent(jLabel13)))
                                                                            .addGap(331, 331, 331))
                                                                    );

                                                                    BHC.getContentPane().add(jPanel7);
                                                                    jPanel7.setBounds(0, 0, 780, 120);

                                                                    jPanel8.setBackground(new java.awt.Color(255, 255, 255));

                                                                    ABONOS.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    ABONOS.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    ABONOS.setRowHeight(25);
                                                                    ABONOS.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                                    ABONOS.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            ABONOSMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    ABONOS.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            ABONOSKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane10.setViewportView(ABONOS);

                                                                    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                                                    jPanel8.setLayout(jPanel8Layout);
                                                                    jPanel8Layout.setHorizontalGroup(
                                                                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                                                                            .addContainerGap())
                                                                    );
                                                                    jPanel8Layout.setVerticalGroup(
                                                                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                            .addGap(17, 17, 17)
                                                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(24, Short.MAX_VALUE))
                                                                    );

                                                                    BHC.getContentPane().add(jPanel8);
                                                                    jPanel8.setBounds(0, 310, 750, 70);

                                                                    jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                                                                    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 34)); // NOI18N
                                                                    jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                                                                    jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Find User Male-80.png"))); // NOI18N
                                                                    jLabel9.setText("Busqueda de Pacientes ");

                                                                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                                                    jPanel4.setLayout(jPanel4Layout);
                                                                    jPanel4Layout.setHorizontalGroup(
                                                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                            .addGap(134, 134, 134)
                                                                            .addComponent(jLabel9)
                                                                            .addContainerGap(201, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel4Layout.setVerticalGroup(
                                                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                            .addGap(62, 62, 62)
                                                                            .addComponent(jLabel9)
                                                                            .addContainerGap(50, Short.MAX_VALUE))
                                                                    );

                                                                    jTabbedPane2.addTab("tab2", jPanel4);

                                                                    jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
                                                                    jScrollPane2.setBorder(null);

                                                                    tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    tbPacientes.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tbPacientes.setRowHeight(25);
                                                                    tbPacientes.setSelectionBackground(new java.awt.Color(41, 127, 184));
                                                                    tbPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tbPacientesMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    tbPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tbPacientesKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane2.setViewportView(tbPacientes);

                                                                    lblIdPreventa.setText("jLabel57");

                                                                    tbpreventas.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    tbpreventas.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tbpreventas.setRowHeight(25);
                                                                    tbpreventas.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                                    tbpreventas.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tbpreventasMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    tbpreventas.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tbpreventasKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane9.setViewportView(tbpreventas);

                                                                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                                                    jPanel5.setLayout(jPanel5Layout);
                                                                    jPanel5Layout.setHorizontalGroup(
                                                                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(34, 34, 34)
                                                                            .addComponent(lblIdPreventa)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                                                                            .addContainerGap())
                                                                    );
                                                                    jPanel5Layout.setVerticalGroup(
                                                                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                            .addGap(32, 32, 32)
                                                                            .addComponent(lblIdPreventa)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                            .addContainerGap())
                                                                    );

                                                                    jTabbedPane2.addTab("tab2", jPanel5);

                                                                    jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                    jLabel16.setForeground(new java.awt.Color(102, 102, 102));
                                                                    jLabel16.setText("No se hallaron coincidencias");

                                                                    jLabel17.setFont(new java.awt.Font("Segoe UI Light", 0, 100)); // NOI18N
                                                                    jLabel17.setForeground(new java.awt.Color(50, 151, 219));
                                                                    jLabel17.setText(":(");

                                                                    jPanel9.setBackground(new java.awt.Color(50, 151, 219));

                                                                    btnNuevo1.setBackground(new java.awt.Color(204, 204, 204));
                                                                    btnNuevo1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                                                    btnNuevo1.setForeground(new java.awt.Color(102, 102, 102));
                                                                    btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar usuario Grupo Mujer Hombre-50.png"))); // NOI18N
                                                                    btnNuevo1.setContentAreaFilled(false);
                                                                    btnNuevo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnNuevo1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnNuevo1.setIconTextGap(30);
                                                                    btnNuevo1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnNuevo1ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                    jLabel18.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                                                    jLabel18.setText("Nueva Historia Clinica");

                                                                    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                                                    jPanel9.setLayout(jPanel9Layout);
                                                                    jPanel9Layout.setHorizontalGroup(
                                                                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                                                                .addComponent(btnNuevo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                            .addGap(0, 23, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel9Layout.setVerticalGroup(
                                                                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                                            .addGap(23, 23, 23)
                                                                            .addComponent(btnNuevo1)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(13, Short.MAX_VALUE))
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
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );
                                                                    jPanel6Layout.setVerticalGroup(
                                                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                                    .addGap(32, 32, 32)
                                                                                    .addComponent(jLabel17))
                                                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                                    .addGap(87, 87, 87)
                                                                                    .addComponent(jLabel16)))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    );

                                                                    jTabbedPane2.addTab("tab3", jPanel6);

                                                                    BHC.getContentPane().add(jTabbedPane2);
                                                                    jTabbedPane2.setBounds(0, 120, 760, 220);

                                                                    Jerarquias.setAlwaysOnTop(true);
                                                                    Jerarquias.setMinimumSize(new java.awt.Dimension(628, 300));
                                                                    Jerarquias.setResizable(false);

                                                                    jPanel10.setBackground(new java.awt.Color(43, 43, 43));
                                                                    jPanel10.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                    jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                    jLabel20.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel20.setText("Forma de Pago");

                                                                    jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                                                                    txtBuscar3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    txtBuscar3.setBorder(null);
                                                                    txtBuscar3.addCaretListener(new javax.swing.event.CaretListener() {
                                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                            txtBuscar3CaretUpdate(evt);
                                                                        }
                                                                    });
                                                                    txtBuscar3.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            txtBuscar3MouseClicked(evt);
                                                                        }
                                                                    });
                                                                    txtBuscar3.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            txtBuscar3ActionPerformed(evt);
                                                                        }
                                                                    });
                                                                    txtBuscar3.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            txtBuscar3KeyPressed(evt);
                                                                        }
                                                                    });

                                                                    T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                                    T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    T5.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            T5MouseClicked(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                                                                    jPanel29.setLayout(jPanel29Layout);
                                                                    jPanel29Layout.setHorizontalGroup(
                                                                        jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                                                            .addGap(2, 2, 2)
                                                                            .addComponent(txtBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel29Layout.setVerticalGroup(
                                                                        jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                                                            .addGap(2, 2, 2)
                                                                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(T5)
                                                                                .addComponent(txtBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    );

                                                                    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                                                                    jPanel10.setLayout(jPanel10Layout);
                                                                    jPanel10Layout.setHorizontalGroup(
                                                                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel20)
                                                                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(262, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel10Layout.setVerticalGroup(
                                                                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jLabel20)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(352, 352, 352))
                                                                    );

                                                                    jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
                                                                    jScrollPane5.setBorder(null);

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
                                                                    tb_Grupo3.setSelectionBackground(new java.awt.Color(50, 151, 219));
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
                                                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                                                                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    );
                                                                    JerarquiasLayout.setVerticalGroup(
                                                                        JerarquiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(JerarquiasLayout.createSequentialGroup()
                                                                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                                            .addGap(0, 0, 0))
                                                                    );

                                                                    nomenclaturas.setAlwaysOnTop(true);
                                                                    nomenclaturas.setMinimumSize(new java.awt.Dimension(749, 338));
                                                                    nomenclaturas.setResizable(false);
                                                                    nomenclaturas.getContentPane().setLayout(null);

                                                                    jPanel11.setBackground(new java.awt.Color(43, 43, 43));
                                                                    jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                    jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                    jLabel21.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel21.setText("CPT");

                                                                    jPanel28.setBackground(new java.awt.Color(255, 255, 255));

                                                                    txtBuscar2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                                    txtBuscar2.setForeground(new java.awt.Color(51, 51, 51));
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
                                                                    jPanel11.setBounds(0, 0, 770, 100);

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
                                                                    jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-80.png"))); // NOI18N
                                                                    jLabel22.setText("Busqueda de CPT ");

                                                                    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                                                                    jPanel13.setLayout(jPanel13Layout);
                                                                    jPanel13Layout.setHorizontalGroup(
                                                                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                                            .addGap(160, 160, 160)
                                                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(188, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel13Layout.setVerticalGroup(
                                                                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                                            .addGap(59, 59, 59)
                                                                            .addComponent(jLabel22)
                                                                            .addContainerGap(73, Short.MAX_VALUE))
                                                                    );

                                                                    jTabbedPane3.addTab("tab2", jPanel13);

                                                                    jPanel14.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
                                                                    jScrollPane6.setBorder(null);

                                                                    tb_CPTBUSCAR.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    tb_CPTBUSCAR.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tb_CPTBUSCAR.setRowHeight(25);
                                                                    tb_CPTBUSCAR.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                                                    tb_CPTBUSCAR.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tb_CPTBUSCARMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    tb_CPTBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tb_CPTBUSCARKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane6.setViewportView(tb_CPTBUSCAR);

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
                                                                        .addGap(0, 212, Short.MAX_VALUE)
                                                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                                                                    );

                                                                    jTabbedPane3.addTab("tab2", jPanel14);

                                                                    jPanel15.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jLabel23.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                    jLabel23.setForeground(new java.awt.Color(102, 102, 102));
                                                                    jLabel23.setText("No se hallaron coincidencias");

                                                                    jLabel24.setFont(new java.awt.Font("Segoe UI Light", 0, 100)); // NOI18N
                                                                    jLabel24.setForeground(new java.awt.Color(50, 151, 219));
                                                                    jLabel24.setText(":(");

                                                                    jPanel16.setBackground(new java.awt.Color(50, 151, 219));

                                                                    btnNuevo2.setBackground(new java.awt.Color(204, 204, 204));
                                                                    btnNuevo2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                                                    btnNuevo2.setForeground(new java.awt.Color(102, 102, 102));
                                                                    btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-50.png"))); // NOI18N
                                                                    btnNuevo2.setContentAreaFilled(false);
                                                                    btnNuevo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnNuevo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnNuevo2.setIconTextGap(30);
                                                                    btnNuevo2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnNuevo2ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                    jLabel25.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                                                    jLabel25.setText("Nuevo CPT");
                                                                    jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                                                                    javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                                                                    jPanel16.setLayout(jPanel16Layout);
                                                                    jPanel16Layout.setHorizontalGroup(
                                                                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btnNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                                                    );
                                                                    jPanel16Layout.setVerticalGroup(
                                                                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                                            .addGap(52, 52, 52)
                                                                            .addComponent(btnNuevo2)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                                                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );
                                                                    jPanel15Layout.setVerticalGroup(
                                                                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                                                            .addGap(39, 39, 39)
                                                                            .addComponent(jLabel24)
                                                                            .addContainerGap(39, Short.MAX_VALUE))
                                                                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jLabel23)
                                                                            .addGap(73, 73, 73))
                                                                    );

                                                                    jTabbedPane3.addTab("tab3", jPanel15);

                                                                    nomenclaturas.getContentPane().add(jTabbedPane3);
                                                                    jTabbedPane3.setBounds(0, 98, 749, 240);

                                                                    preventas.setAlwaysOnTop(true);
                                                                    preventas.setMinimumSize(new java.awt.Dimension(770, 422));
                                                                    preventas.setResizable(false);

                                                                    jPanel18.setBackground(new java.awt.Color(0, 153, 255));
                                                                    jPanel18.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                    jLabel29.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                    jLabel29.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel29.setText("Preventa");

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

                                                                    jLabel34.setForeground(new java.awt.Color(0, 153, 255));
                                                                    jLabel34.setText("jLabel34");

                                                                    elimdp.setForeground(new java.awt.Color(0, 153, 255));
                                                                    elimdp.setText("jLabel40");

                                                                    jPanel24.setBackground(new java.awt.Color(0, 153, 255));

                                                                    jLabel35.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                                                    jLabel35.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel35.setText("Emergencia");
                                                                    jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            jLabel35MouseClicked(evt);
                                                                        }
                                                                    });

                                                                    jPanel25.setPreferredSize(new java.awt.Dimension(117, 4));

                                                                    javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
                                                                    jPanel25.setLayout(jPanel25Layout);
                                                                    jPanel25Layout.setHorizontalGroup(
                                                                        jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                    );
                                                                    jPanel25Layout.setVerticalGroup(
                                                                        jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 4, Short.MAX_VALUE)
                                                                    );

                                                                    javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
                                                                    jPanel24.setLayout(jPanel24Layout);
                                                                    jPanel24Layout.setHorizontalGroup(
                                                                        jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel24Layout.createSequentialGroup()
                                                                            .addGap(19, 19, 19)
                                                                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                                                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel24Layout.setVerticalGroup(
                                                                        jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel24Layout.createSequentialGroup()
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jLabel35)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );

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

                                                                    jPanel31.setBackground(new java.awt.Color(0, 153, 255));

                                                                    jPanel41.setPreferredSize(new java.awt.Dimension(117, 4));

                                                                    javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
                                                                    jPanel41.setLayout(jPanel41Layout);
                                                                    jPanel41Layout.setHorizontalGroup(
                                                                        jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                    );
                                                                    jPanel41Layout.setVerticalGroup(
                                                                        jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 4, Short.MAX_VALUE)
                                                                    );

                                                                    jLabel37.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                                                    jLabel37.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel37.setText("Hospitalización");
                                                                    jLabel37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            jLabel37MouseClicked(evt);
                                                                        }
                                                                    });
                                                                    jLabel37.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            jLabel37KeyPressed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                                                                    jPanel31.setLayout(jPanel31Layout);
                                                                    jPanel31Layout.setHorizontalGroup(
                                                                        jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                                                            .addContainerGap(26, Short.MAX_VALUE)
                                                                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                            .addContainerGap())
                                                                    );
                                                                    jPanel31Layout.setVerticalGroup(
                                                                        jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jLabel37)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );

                                                                    jPanel42.setBackground(new java.awt.Color(0, 153, 255));

                                                                    jPanel43.setPreferredSize(new java.awt.Dimension(117, 4));

                                                                    javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
                                                                    jPanel43.setLayout(jPanel43Layout);
                                                                    jPanel43Layout.setHorizontalGroup(
                                                                        jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 217, Short.MAX_VALUE)
                                                                    );
                                                                    jPanel43Layout.setVerticalGroup(
                                                                        jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 4, Short.MAX_VALUE)
                                                                    );

                                                                    jLabel41.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                                                    jLabel41.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel41.setText("Consultorios Externos");
                                                                    jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            jLabel41MouseClicked(evt);
                                                                        }
                                                                    });
                                                                    jLabel41.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            jLabel41KeyPressed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
                                                                    jPanel42.setLayout(jPanel42Layout);
                                                                    jPanel42Layout.setHorizontalGroup(
                                                                        jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                                                                            .addContainerGap(14, Short.MAX_VALUE)
                                                                            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap())
                                                                    );
                                                                    jPanel42Layout.setVerticalGroup(
                                                                        jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel42Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jLabel41)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );

                                                                    javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                                                                    jPanel18.setLayout(jPanel18Layout);
                                                                    jPanel18Layout.setHorizontalGroup(
                                                                        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                    .addComponent(jLabel29)
                                                                                    .addGap(44, 44, 44)
                                                                                    .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(btnbuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                    .addComponent(jLabel34)
                                                                                    .addGap(146, 146, 146)
                                                                                    .addComponent(elimdp))
                                                                                .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addGap(18, 18, 18)
                                                                                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addGap(0, 268, Short.MAX_VALUE))))
                                                                    );
                                                                    jPanel18Layout.setVerticalGroup(
                                                                        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(elimdp)
                                                                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                            .addComponent(jLabel29)
                                                                                            .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                            .addComponent(btnbuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addGap(8, 8, 8))
                                                                                .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                    .addGap(21, 21, 21)
                                                                                    .addComponent(jLabel34)
                                                                                    .addGap(0, 0, Short.MAX_VALUE)))
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
                                                                    tEME.setSelectionBackground(new java.awt.Color(50, 151, 219));
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
                                                                    tEMEDET.setSelectionBackground(new java.awt.Color(50, 151, 219));
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

                                                                    btnCargarEME.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    btnCargarEME.setForeground(new java.awt.Color(0, 153, 51));
                                                                    btnCargarEME.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Exportar-30.png"))); // NOI18N
                                                                    btnCargarEME.setText("Cargar para venta");
                                                                    btnCargarEME.setContentAreaFilled(false);
                                                                    btnCargarEME.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnCargarEME.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnCargarEME.setIconTextGap(30);
                                                                    btnCargarEME.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnCargarEME.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnCargarEMEActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    btnEliminarEME.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    btnEliminarEME.setForeground(new java.awt.Color(255, 91, 70));
                                                                    btnEliminarEME.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Delete Property Filled-32.png"))); // NOI18N
                                                                    btnEliminarEME.setText("Eliminar Registro");
                                                                    btnEliminarEME.setContentAreaFilled(false);
                                                                    btnEliminarEME.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnEliminarEME.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnEliminarEME.setIconTextGap(30);
                                                                    btnEliminarEME.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnEliminarEME.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnEliminarEMEActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    panelEliminarEME.setBackground(new java.awt.Color(255, 91, 70));

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

                                                                    javax.swing.GroupLayout panelEliminarEMELayout = new javax.swing.GroupLayout(panelEliminarEME);
                                                                    panelEliminarEME.setLayout(panelEliminarEMELayout);
                                                                    panelEliminarEMELayout.setHorizontalGroup(
                                                                        panelEliminarEMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminarEMELayout.createSequentialGroup()
                                                                            .addGap(19, 19, 19)
                                                                            .addComponent(Mensaje)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(19, 19, 19))
                                                                    );
                                                                    panelEliminarEMELayout.setVerticalGroup(
                                                                        panelEliminarEMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminarEMELayout.createSequentialGroup()
                                                                            .addGap(17, 17, 17)
                                                                            .addGroup(panelEliminarEMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                                                                            .addComponent(btnCargarEME)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(btnEliminarEME)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(panelEliminarEME, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                                                                        .addComponent(btnCargarEME)
                                                                                        .addComponent(btnEliminarEME)))
                                                                                .addComponent(panelEliminarEME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    jTabbedPane5.addTab("EME", jPanel22);

                                                                    jPanel40.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jScrollPane22.setBackground(new java.awt.Color(255, 255, 255));
                                                                    jScrollPane22.setBorder(null);

                                                                    tHOS.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    tHOS.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tHOS.setRowHeight(25);
                                                                    tHOS.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                                                    tHOS.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tHOSMouseClicked(evt);
                                                                        }
                                                                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                                                            tHOSMouseEntered(evt);
                                                                        }
                                                                    });
                                                                    tHOS.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tHOSKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane22.setViewportView(tHOS);

                                                                    jScrollPane23.setBackground(new java.awt.Color(255, 255, 255));
                                                                    jScrollPane23.setBorder(null);

                                                                    tHOSDET.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    tHOSDET.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
                                                                    tHOSDET.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tHOSDET.setRowHeight(25);
                                                                    tHOSDET.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                                                    tHOSDET.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tHOSDETMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    tHOSDET.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tHOSDETKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane23.setViewportView(tHOSDET);

                                                                    btnCargarHOS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    btnCargarHOS.setForeground(new java.awt.Color(0, 153, 51));
                                                                    btnCargarHOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Exportar-30.png"))); // NOI18N
                                                                    btnCargarHOS.setText("Cargar para venta");
                                                                    btnCargarHOS.setContentAreaFilled(false);
                                                                    btnCargarHOS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnCargarHOS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnCargarHOS.setIconTextGap(30);
                                                                    btnCargarHOS.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnCargarHOS.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnCargarHOSActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    btnEliminarHOS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    btnEliminarHOS.setForeground(new java.awt.Color(255, 91, 70));
                                                                    btnEliminarHOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Delete Property Filled-32.png"))); // NOI18N
                                                                    btnEliminarHOS.setText("Eliminar Registro");
                                                                    btnEliminarHOS.setContentAreaFilled(false);
                                                                    btnEliminarHOS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnEliminarHOS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnEliminarHOS.setIconTextGap(30);
                                                                    btnEliminarHOS.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnEliminarHOS.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnEliminarHOSActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    panelEliminarHOS.setBackground(new java.awt.Color(255, 91, 70));

                                                                    Mensaje3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    Mensaje3.setForeground(new java.awt.Color(255, 255, 255));
                                                                    Mensaje3.setText("Desea Eliminar el Registro ?");

                                                                    eli3.setForeground(new java.awt.Color(240, 240, 240));
                                                                    eli3.setText("Si");
                                                                    eli3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    eli3.setContentAreaFilled(false);
                                                                    eli3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    eli3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    eli3.setIconTextGap(30);
                                                                    eli3.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            eli3ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    noeli3.setForeground(new java.awt.Color(240, 240, 240));
                                                                    noeli3.setText("No");
                                                                    noeli3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    noeli3.setContentAreaFilled(false);
                                                                    noeli3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    noeli3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    noeli3.setIconTextGap(30);
                                                                    noeli3.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            noeli3ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout panelEliminarHOSLayout = new javax.swing.GroupLayout(panelEliminarHOS);
                                                                    panelEliminarHOS.setLayout(panelEliminarHOSLayout);
                                                                    panelEliminarHOSLayout.setHorizontalGroup(
                                                                        panelEliminarHOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminarHOSLayout.createSequentialGroup()
                                                                            .addGap(19, 19, 19)
                                                                            .addComponent(Mensaje3)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(eli3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(noeli3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(19, 19, 19))
                                                                    );
                                                                    panelEliminarHOSLayout.setVerticalGroup(
                                                                        panelEliminarHOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminarHOSLayout.createSequentialGroup()
                                                                            .addGap(17, 17, 17)
                                                                            .addGroup(panelEliminarHOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Mensaje3)
                                                                                .addComponent(eli3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(noeli3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                                    );

                                                                    javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
                                                                    jPanel40.setLayout(jPanel40Layout);
                                                                    jPanel40Layout.setHorizontalGroup(
                                                                        jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane22)
                                                                        .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                                                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                                                            .addComponent(btnCargarHOS)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(btnEliminarHOS)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(panelEliminarHOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel40Layout.setVerticalGroup(
                                                                        jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                                                            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel40Layout.createSequentialGroup()
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(btnCargarHOS)
                                                                                        .addComponent(btnEliminarHOS)))
                                                                                .addComponent(panelEliminarHOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    jTabbedPane5.addTab("HOS", jPanel40);

                                                                    jPanel26.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jScrollPane15.setBackground(new java.awt.Color(255, 255, 255));
                                                                    jScrollPane15.setBorder(null);

                                                                    tb_CEX.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    tb_CEX.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tb_CEX.setRowHeight(25);
                                                                    tb_CEX.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                                                    tb_CEX.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tb_CEXMouseClicked(evt);
                                                                        }
                                                                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                                                            tb_CEXMouseEntered(evt);
                                                                        }
                                                                    });
                                                                    tb_CEX.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tb_CEXKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane15.setViewportView(tb_CEX);

                                                                    btnCargarCEX.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    btnCargarCEX.setForeground(new java.awt.Color(0, 153, 51));
                                                                    btnCargarCEX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Exportar-30.png"))); // NOI18N
                                                                    btnCargarCEX.setText("Cargar para venta");
                                                                    btnCargarCEX.setContentAreaFilled(false);
                                                                    btnCargarCEX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnCargarCEX.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnCargarCEX.setIconTextGap(30);
                                                                    btnCargarCEX.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnCargarCEX.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnCargarCEXActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    btnEliminarCEX.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    btnEliminarCEX.setForeground(new java.awt.Color(255, 91, 70));
                                                                    btnEliminarCEX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Delete Property Filled-32.png"))); // NOI18N
                                                                    btnEliminarCEX.setText("Eliminar Registro");
                                                                    btnEliminarCEX.setContentAreaFilled(false);
                                                                    btnEliminarCEX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnEliminarCEX.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnEliminarCEX.setIconTextGap(30);
                                                                    btnEliminarCEX.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnEliminarCEX.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnEliminarCEXActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    panelEliminarCEX.setBackground(new java.awt.Color(255, 91, 70));

                                                                    Mensaje6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    Mensaje6.setForeground(new java.awt.Color(255, 255, 255));
                                                                    Mensaje6.setText("Desea Eliminar el Registro ?");

                                                                    eli7.setForeground(new java.awt.Color(240, 240, 240));
                                                                    eli7.setText("Si");
                                                                    eli7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    eli7.setContentAreaFilled(false);
                                                                    eli7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    eli7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    eli7.setIconTextGap(30);
                                                                    eli7.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            eli7ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    noeli7.setForeground(new java.awt.Color(240, 240, 240));
                                                                    noeli7.setText("No");
                                                                    noeli7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    noeli7.setContentAreaFilled(false);
                                                                    noeli7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    noeli7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    noeli7.setIconTextGap(30);
                                                                    noeli7.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            noeli7ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout panelEliminarCEXLayout = new javax.swing.GroupLayout(panelEliminarCEX);
                                                                    panelEliminarCEX.setLayout(panelEliminarCEXLayout);
                                                                    panelEliminarCEXLayout.setHorizontalGroup(
                                                                        panelEliminarCEXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminarCEXLayout.createSequentialGroup()
                                                                            .addGap(19, 19, 19)
                                                                            .addComponent(Mensaje6)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                                                            .addComponent(eli7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(noeli7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(19, 19, 19))
                                                                    );
                                                                    panelEliminarCEXLayout.setVerticalGroup(
                                                                        panelEliminarCEXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminarCEXLayout.createSequentialGroup()
                                                                            .addGap(17, 17, 17)
                                                                            .addGroup(panelEliminarCEXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Mensaje6)
                                                                                .addComponent(eli7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(noeli7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                                    );

                                                                    javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
                                                                    jPanel26.setLayout(jPanel26Layout);
                                                                    jPanel26Layout.setHorizontalGroup(
                                                                        jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane15)
                                                                        .addGroup(jPanel26Layout.createSequentialGroup()
                                                                            .addComponent(btnCargarCEX)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(btnEliminarCEX)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(panelEliminarCEX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel26Layout.setVerticalGroup(
                                                                        jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel26Layout.createSequentialGroup()
                                                                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(4, 4, 4)
                                                                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(btnCargarCEX)
                                                                                    .addComponent(btnEliminarCEX))
                                                                                .addComponent(panelEliminarCEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    jTabbedPane5.addTab("CEX", jPanel26);

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
                                                                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );

                                                                    Anular.setAlwaysOnTop(true);
                                                                    Anular.setMinimumSize(new java.awt.Dimension(562, 359));
                                                                    Anular.setResizable(false);

                                                                    jPanel19.setBackground(new java.awt.Color(255, 91, 70));
                                                                    jPanel19.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                    jLabel31.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                    jLabel31.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel31.setText("Anular Venta");

                                                                    btnbuscar7.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btnbuscar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
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

                                                                    btnbuscar1.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btnbuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-32.png"))); // NOI18N
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

                                                                    lblcod.setForeground(new java.awt.Color(255, 91, 70));
                                                                    lblcod.setText("jLabel56");

                                                                    codelim.setForeground(new java.awt.Color(204, 255, 255));
                                                                    codelim.setText("jLabel56");

                                                                    btnbuscar2.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btnbuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Volver-32.png"))); // NOI18N
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

                                                                    javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                                                                    jPanel19.setLayout(jPanel19Layout);
                                                                    jPanel19Layout.setHorizontalGroup(
                                                                        jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel19Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel19Layout.createSequentialGroup()
                                                                                    .addComponent(jLabel31)
                                                                                    .addGap(29, 29, 29)
                                                                                    .addComponent(lblcod)
                                                                                    .addGap(110, 110, 110)
                                                                                    .addComponent(codelim))
                                                                                .addGroup(jPanel19Layout.createSequentialGroup()
                                                                                    .addComponent(btnbuscar7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                    .addComponent(btnbuscar8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                    .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                    .addComponent(btnbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                            .addContainerGap(352, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel19Layout.setVerticalGroup(
                                                                        jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel31)
                                                                                .addComponent(lblcod)
                                                                                .addComponent(codelim))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(btnbuscar7)
                                                                                .addComponent(btnbuscar8)
                                                                                .addComponent(btnbuscar1)
                                                                                .addComponent(btnbuscar2))
                                                                            .addGap(345, 345, 345))
                                                                    );

                                                                    nuevoanulacion.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    jLabel33.setForeground(new java.awt.Color(102, 102, 102));
                                                                    jLabel33.setText("Nuevo motivo de  anulacion");

                                                                    lblcodanu.setForeground(new java.awt.Color(255, 255, 255));
                                                                    lblcodanu.setText("jLabel34");

                                                                    M.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    M.setForeground(new java.awt.Color(102, 102, 102));

                                                                    javax.swing.GroupLayout nuevoanulacionLayout = new javax.swing.GroupLayout(nuevoanulacion);
                                                                    nuevoanulacion.setLayout(nuevoanulacionLayout);
                                                                    nuevoanulacionLayout.setHorizontalGroup(
                                                                        nuevoanulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(nuevoanulacionLayout.createSequentialGroup()
                                                                            .addGap(0, 0, 0)
                                                                            .addGroup(nuevoanulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(nuevoanulacionLayout.createSequentialGroup()
                                                                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                    .addComponent(lblcodanu)
                                                                                    .addGap(79, 79, 79))
                                                                                .addGroup(nuevoanulacionLayout.createSequentialGroup()
                                                                                    .addGroup(nuevoanulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(txtanular, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(M)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    elimma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    elimma.setForeground(new java.awt.Color(255, 102, 0));
                                                                    elimma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Eliminar propiedad Filled-30.png"))); // NOI18N
                                                                    elimma.setText("Anular venta");
                                                                    elimma.setContentAreaFilled(false);
                                                                    elimma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    elimma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    elimma.setIconTextGap(30);
                                                                    elimma.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    elimma.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            elimmaActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    cargareliminarma.setBackground(new java.awt.Color(255, 91, 70));

                                                                    Mensaje2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    Mensaje2.setForeground(new java.awt.Color(255, 255, 255));
                                                                    Mensaje2.setText("Desea Eliminar el Registro ?");

                                                                    eli2.setForeground(new java.awt.Color(240, 240, 240));
                                                                    eli2.setText("Si");
                                                                    eli2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    eli2.setContentAreaFilled(false);
                                                                    eli2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    eli2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    eli2.setIconTextGap(30);
                                                                    eli2.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            eli2ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    noeli2.setForeground(new java.awt.Color(240, 240, 240));
                                                                    noeli2.setText("No");
                                                                    noeli2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    noeli2.setContentAreaFilled(false);
                                                                    noeli2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    noeli2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    noeli2.setIconTextGap(30);
                                                                    noeli2.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            noeli2ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout cargareliminarmaLayout = new javax.swing.GroupLayout(cargareliminarma);
                                                                    cargareliminarma.setLayout(cargareliminarmaLayout);
                                                                    cargareliminarmaLayout.setHorizontalGroup(
                                                                        cargareliminarmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(cargareliminarmaLayout.createSequentialGroup()
                                                                            .addGap(19, 19, 19)
                                                                            .addComponent(Mensaje2)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(eli2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(noeli2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(19, 19, 19))
                                                                    );
                                                                    cargareliminarmaLayout.setVerticalGroup(
                                                                        cargareliminarmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(cargareliminarmaLayout.createSequentialGroup()
                                                                            .addGap(17, 17, 17)
                                                                            .addGroup(cargareliminarmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Mensaje2)
                                                                                .addComponent(eli2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(noeli2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    jPanel39.setBackground(new java.awt.Color(255, 102, 204));

                                                                    jScrollPane21.setBorder(null);

                                                                    anulacion.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    anulacion.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    anulacion.setRowHeight(25);
                                                                    anulacion.setSelectionBackground(new java.awt.Color(255, 153, 51));
                                                                    anulacion.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            anulacionMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    anulacion.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            anulacionKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane21.setViewportView(anulacion);

                                                                    javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
                                                                    jPanel39.setLayout(jPanel39Layout);
                                                                    jPanel39Layout.setHorizontalGroup(
                                                                        jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel39Layout.createSequentialGroup()
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                                                                            .addGap(0, 0, 0))
                                                                    );
                                                                    jPanel39Layout.setVerticalGroup(
                                                                        jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel39Layout.createSequentialGroup()
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                            .addGap(0, 0, 0))
                                                                    );

                                                                    sep1.setBackground(new java.awt.Color(204, 204, 204));
                                                                    sep1.setPreferredSize(new java.awt.Dimension(1, 0));

                                                                    javax.swing.GroupLayout sep1Layout = new javax.swing.GroupLayout(sep1);
                                                                    sep1.setLayout(sep1Layout);
                                                                    sep1Layout.setHorizontalGroup(
                                                                        sep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 1, Short.MAX_VALUE)
                                                                    );
                                                                    sep1Layout.setVerticalGroup(
                                                                        sep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                    );

                                                                    jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    jLabel32.setForeground(new java.awt.Color(102, 102, 102));
                                                                    jLabel32.setText("Seleccione Motivo de anulacion");

                                                                    CBXANULAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                                    CBXANULAR.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                                                                    CBXANULAR.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            CBXANULARMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    CBXANULAR.addItemListener(new java.awt.event.ItemListener() {
                                                                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                                                            CBXANULARItemStateChanged(evt);
                                                                        }
                                                                    });

                                                                    elimma1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    elimma1.setForeground(new java.awt.Color(255, 91, 70));
                                                                    elimma1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Delete Property Filled-32.png"))); // NOI18N
                                                                    elimma1.setText("Eliminar Registro");
                                                                    elimma1.setContentAreaFilled(false);
                                                                    elimma1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    elimma1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    elimma1.setIconTextGap(30);
                                                                    elimma1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    elimma1.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            elimma1ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout AnularLayout = new javax.swing.GroupLayout(Anular.getContentPane());
                                                                    Anular.getContentPane().setLayout(AnularLayout);
                                                                    AnularLayout.setHorizontalGroup(
                                                                        AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(AnularLayout.createSequentialGroup()
                                                                            .addComponent(elimma)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(elimma1)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(cargareliminarma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AnularLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(AnularLayout.createSequentialGroup()
                                                                                    .addGroup(AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(CBXANULAR, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(nuevoanulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addGap(0, 33, Short.MAX_VALUE)))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );
                                                                    AnularLayout.setVerticalGroup(
                                                                        AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(AnularLayout.createSequentialGroup()
                                                                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addGroup(AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(AnularLayout.createSequentialGroup()
                                                                                    .addComponent(nuevoanulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addGap(13, 13, 13)
                                                                                    .addComponent(jLabel32)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                    .addComponent(CBXANULAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addGap(0, 55, Short.MAX_VALUE))
                                                                                .addComponent(sep1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                                                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(cargareliminarma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(elimma1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                    .addComponent(elimma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                                    );

                                                                    abono.setAlwaysOnTop(true);
                                                                    abono.setMinimumSize(new java.awt.Dimension(537, 300));
                                                                    abono.setResizable(false);

                                                                    jPanel30.setBackground(new java.awt.Color(255, 153, 51));
                                                                    jPanel30.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                    jLabel42.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                    jLabel42.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel42.setText("Abono");

                                                                    javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                                                                    jPanel30.setLayout(jPanel30Layout);
                                                                    jPanel30Layout.setHorizontalGroup(
                                                                        jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel30Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jLabel42)
                                                                            .addContainerGap(441, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel30Layout.setVerticalGroup(
                                                                        jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jLabel42)
                                                                            .addGap(389, 389, 389))
                                                                    );

                                                                    Cliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    Cliente.setForeground(new java.awt.Color(102, 102, 102));
                                                                    Cliente.setText("jLabel43");

                                                                    jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    jLabel46.setForeground(new java.awt.Color(102, 102, 102));
                                                                    jLabel46.setText("Registra un abono de ");

                                                                    cargareliminar1.setBackground(new java.awt.Color(255, 102, 51));

                                                                    Mensaje1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    Mensaje1.setForeground(new java.awt.Color(255, 255, 255));
                                                                    Mensaje1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Descuento-30.png"))); // NOI18N
                                                                    Mensaje1.setText("   Desea Descontar el Abono ?");

                                                                    eli1.setForeground(new java.awt.Color(240, 240, 240));
                                                                    eli1.setText("Si");
                                                                    eli1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    eli1.setContentAreaFilled(false);
                                                                    eli1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    eli1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    eli1.setIconTextGap(30);
                                                                    eli1.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            eli1ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    noeli1.setForeground(new java.awt.Color(240, 240, 240));
                                                                    noeli1.setText("No");
                                                                    noeli1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    noeli1.setContentAreaFilled(false);
                                                                    noeli1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    noeli1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    noeli1.setIconTextGap(30);
                                                                    noeli1.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            noeli1ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout cargareliminar1Layout = new javax.swing.GroupLayout(cargareliminar1);
                                                                    cargareliminar1.setLayout(cargareliminar1Layout);
                                                                    cargareliminar1Layout.setHorizontalGroup(
                                                                        cargareliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(cargareliminar1Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(Mensaje1)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(eli1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(noeli1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(19, 19, 19))
                                                                    );
                                                                    cargareliminar1Layout.setVerticalGroup(
                                                                        cargareliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(cargareliminar1Layout.createSequentialGroup()
                                                                            .addGap(17, 17, 17)
                                                                            .addGroup(cargareliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Mensaje1)
                                                                                .addComponent(eli1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(noeli1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(31, Short.MAX_VALUE))
                                                                    );

                                                                    abonod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    abonod.setForeground(new java.awt.Color(102, 102, 102));
                                                                    abonod.setText("00");

                                                                    adni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    adni.setForeground(new java.awt.Color(102, 102, 102));
                                                                    adni.setText("jLabel43");

                                                                    adni1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    adni1.setForeground(new java.awt.Color(102, 102, 102));
                                                                    adni1.setText("S/");

                                                                    javax.swing.GroupLayout abonoLayout = new javax.swing.GroupLayout(abono.getContentPane());
                                                                    abono.getContentPane().setLayout(abonoLayout);
                                                                    abonoLayout.setHorizontalGroup(
                                                                        abonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(abonoLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(abonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(Cliente)
                                                                                .addGroup(abonoLayout.createSequentialGroup()
                                                                                    .addComponent(jLabel46)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(adni1)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(abonod))
                                                                                .addComponent(adni))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addComponent(cargareliminar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    );
                                                                    abonoLayout.setVerticalGroup(
                                                                        abonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(abonoLayout.createSequentialGroup()
                                                                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(25, 25, 25)
                                                                            .addComponent(Cliente)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(adni)
                                                                            .addGap(18, 18, 18)
                                                                            .addGroup(abonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel46)
                                                                                .addComponent(abonod)
                                                                                .addComponent(adni1))
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(cargareliminar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    Medicos.setAlwaysOnTop(true);
                                                                    Medicos.setMinimumSize(new java.awt.Dimension(749, 398));
                                                                    Medicos.setResizable(false);
                                                                    Medicos.getContentPane().setLayout(null);

                                                                    jPanel46.setBackground(new java.awt.Color(43, 43, 43));
                                                                    jPanel46.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                    jLabel62.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                    jLabel62.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel62.setText("Médicos de turno");

                                                                    jPanel47.setBackground(new java.awt.Color(255, 255, 255));

                                                                    BMedicos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    BMedicos.setBorder(null);
                                                                    BMedicos.addCaretListener(new javax.swing.event.CaretListener() {
                                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                            BMedicosCaretUpdate(evt);
                                                                        }
                                                                    });
                                                                    BMedicos.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            BMedicosActionPerformed(evt);
                                                                        }
                                                                    });
                                                                    BMedicos.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            BMedicosKeyPressed(evt);
                                                                        }
                                                                    });

                                                                    T8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                                    T8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    T8.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            T8MouseClicked(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
                                                                    jPanel47.setLayout(jPanel47Layout);
                                                                    jPanel47Layout.setHorizontalGroup(
                                                                        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel47Layout.createSequentialGroup()
                                                                            .addGap(2, 2, 2)
                                                                            .addComponent(BMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(T8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel47Layout.setVerticalGroup(
                                                                        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(BMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(T8)))
                                                                    );

                                                                    jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    jLabel56.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel56.setText("Búsqueda por nombres y apellidos");

                                                                    javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
                                                                    jPanel46.setLayout(jPanel46Layout);
                                                                    jPanel46Layout.setHorizontalGroup(
                                                                        jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel46Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel56)
                                                                                .addComponent(jLabel62)
                                                                                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(504, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel46Layout.setVerticalGroup(
                                                                        jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jLabel62)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(2, 2, 2)
                                                                            .addComponent(jLabel56)
                                                                            .addGap(333, 333, 333))
                                                                    );

                                                                    Medicos.getContentPane().add(jPanel46);
                                                                    jPanel46.setBounds(0, 0, 780, 120);

                                                                    jTabbedPane7.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                                                                    jPanel50.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jScrollPane16.setBackground(new java.awt.Color(255, 255, 255));
                                                                    jScrollPane16.setBorder(null);

                                                                    tb_medicos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    tb_medicos.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    tb_medicos.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tb_medicos.setRowHeight(25);
                                                                    tb_medicos.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                                    tb_medicos.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tb_medicosMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    tb_medicos.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tb_medicosKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane16.setViewportView(tb_medicos);

                                                                    javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
                                                                    jPanel50.setLayout(jPanel50Layout);
                                                                    jPanel50Layout.setHorizontalGroup(
                                                                        jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );
                                                                    jPanel50Layout.setVerticalGroup(
                                                                        jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                                                    );

                                                                    jTabbedPane7.addTab("tab2", jPanel50);

                                                                    jPanel51.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jLabel65.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                    jLabel65.setForeground(new java.awt.Color(102, 102, 102));
                                                                    jLabel65.setText("No se halló ningún medico de turno");

                                                                    jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                                                                    jLabel66.setForeground(new java.awt.Color(0, 153, 153));
                                                                    jLabel66.setText(":(");

                                                                    javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
                                                                    jPanel51.setLayout(jPanel51Layout);
                                                                    jPanel51Layout.setHorizontalGroup(
                                                                        jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel51Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jLabel66)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(jLabel65)
                                                                            .addContainerGap(122, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel51Layout.setVerticalGroup(
                                                                        jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel51Layout.createSequentialGroup()
                                                                            .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel51Layout.createSequentialGroup()
                                                                                    .addGap(39, 39, 39)
                                                                                    .addComponent(jLabel66))
                                                                                .addGroup(jPanel51Layout.createSequentialGroup()
                                                                                    .addGap(98, 98, 98)
                                                                                    .addComponent(jLabel65)))
                                                                            .addContainerGap(109, Short.MAX_VALUE))
                                                                    );

                                                                    jTabbedPane7.addTab("tab3", jPanel51);

                                                                    Medicos.getContentPane().add(jTabbedPane7);
                                                                    jTabbedPane7.setBounds(0, 118, 749, 310);

                                                                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                                                    setIconImage(getIconImage());

                                                                    jTabbedPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                    jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                                                                    jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                                                                    jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                                                                    jScrollPane3.setBorder(null);

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

                                                                    jScrollPane7.setBorder(null);

                                                                    tb_Grupo6.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                    tb_Grupo6.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tb_Grupo6.setRowHeight(25);
                                                                    tb_Grupo6.setSelectionBackground(new java.awt.Color(204, 204, 204));
                                                                    tb_Grupo6.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tb_Grupo6MouseClicked(evt);
                                                                        }
                                                                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                                                            tb_Grupo6MousePressed(evt);
                                                                        }
                                                                    });
                                                                    tb_Grupo6.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tb_Grupo6KeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane7.setViewportView(tb_Grupo6);

                                                                    resumen.setBackground(new java.awt.Color(43, 43, 43));
                                                                    resumen.setForeground(new java.awt.Color(43, 43, 43));
                                                                    resumen.setPreferredSize(new java.awt.Dimension(610, 113));

                                                                    HCI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    HCI.setForeground(new java.awt.Color(204, 204, 204));
                                                                    HCI.setText("DNI");

                                                                    DNII.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    DNII.setForeground(new java.awt.Color(204, 204, 204));
                                                                    DNII.setText("Dirección ");

                                                                    jLabel44.setBackground(new java.awt.Color(204, 204, 204));
                                                                    jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    jLabel44.setForeground(new java.awt.Color(204, 204, 204));
                                                                    jLabel44.setText("Acto Medico");

                                                                    ACTM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                                                                    ACTM.setForeground(new java.awt.Color(255, 255, 255));
                                                                    ACTM.setText("DNI / H.C.");

                                                                    APENOM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                    APENOM.setForeground(new java.awt.Color(255, 255, 255));
                                                                    APENOM.setText("Apellidos y Nombres");

                                                                    jLabel48.setBackground(new java.awt.Color(204, 204, 204));
                                                                    jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    jLabel48.setForeground(new java.awt.Color(204, 204, 204));
                                                                    jLabel48.setText("DNI");

                                                                    jLabel49.setBackground(new java.awt.Color(204, 204, 204));
                                                                    jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    jLabel49.setForeground(new java.awt.Color(204, 204, 204));
                                                                    jLabel49.setText("Nº H. C.");

                                                                    bus1.setText("jLabel42");

                                                                    javax.swing.GroupLayout resumenLayout = new javax.swing.GroupLayout(resumen);
                                                                    resumen.setLayout(resumenLayout);
                                                                    resumenLayout.setHorizontalGroup(
                                                                        resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(resumenLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(APENOM, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(resumenLayout.createSequentialGroup()
                                                                                    .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel48)
                                                                                        .addComponent(jLabel44)
                                                                                        .addComponent(jLabel49))
                                                                                    .addGap(24, 24, 24)
                                                                                    .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(resumenLayout.createSequentialGroup()
                                                                                            .addComponent(ACTM)
                                                                                            .addGap(645, 645, 645)
                                                                                            .addComponent(bus1))
                                                                                        .addComponent(DNII)
                                                                                        .addComponent(HCI))))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    resumenLayout.setVerticalGroup(
                                                                        resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resumenLayout.createSequentialGroup()
                                                                            .addGap(11, 11, 11)
                                                                            .addComponent(APENOM)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel44)
                                                                                .addComponent(bus1)
                                                                                .addComponent(ACTM))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(DNII)
                                                                                .addComponent(jLabel48))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(HCI)
                                                                                .addComponent(jLabel49))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                                                    jPanel2.setLayout(jPanel2Layout);
                                                                    jPanel2Layout.setHorizontalGroup(
                                                                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane7)
                                                                        .addComponent(resumen, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                                                                        .addComponent(jScrollPane3)
                                                                    );
                                                                    jPanel2Layout.setVerticalGroup(
                                                                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                                            .addComponent(resumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                                                                            .addContainerGap())
                                                                    );

                                                                    jTabbedPane1.addTab("  ", jPanel2);

                                                                    jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                                                                    jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                                                                    lForma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    lForma.setForeground(new java.awt.Color(51, 51, 51));
                                                                    lForma.setText("Forma de Pago");

                                                                    lTipoDoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    lTipoDoc.setForeground(new java.awt.Color(51, 51, 51));
                                                                    lTipoDoc.setText("Tipo de Documento");

                                                                    lDoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    lDoc.setForeground(new java.awt.Color(51, 51, 51));
                                                                    lDoc.setText("Numero de Documento");

                                                                    nom.setBackground(new java.awt.Color(255, 255, 255));
                                                                    nom.setForeground(new java.awt.Color(255, 255, 255));
                                                                    nom.setText("jLabel8");

                                                                    lblcodigo.setText("Numero de Documento");

                                                                    lblhc.setText("Numero de Documento");

                                                                    cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    cbxTipoDocumento.setForeground(new java.awt.Color(51, 51, 51));
                                                                    cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                                                                    cbxTipoDocumento.setEnabled(false);
                                                                    cbxTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            cbxTipoDocumentoActionPerformed(evt);
                                                                        }
                                                                    });
                                                                    cbxTipoDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            cbxTipoDocumentoKeyPressed(evt);
                                                                        }
                                                                        public void keyTyped(java.awt.event.KeyEvent evt) {
                                                                            cbxTipoDocumentoKeyTyped(evt);
                                                                        }
                                                                    });

                                                                    lblCPT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    lblCPT.setForeground(new java.awt.Color(51, 51, 51));
                                                                    lblCPT.setText("CPT");

                                                                    jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                                    tb_CPT.setModel(new javax.swing.table.DefaultTableModel(
                                                                        new Object [][] {

                                                                        },
                                                                        new String [] {
                                                                            "Nomenclatura", "Descripcion", "Precio", "Forma de Pago"
                                                                        }
                                                                    ));
                                                                    tb_CPT.setGridColor(new java.awt.Color(255, 255, 255));
                                                                    tb_CPT.setRowHeight(25);
                                                                    tb_CPT.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                                    tb_CPT.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            tb_CPTMouseClicked(evt);
                                                                        }
                                                                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                                                            tb_CPTMousePressed(evt);
                                                                        }
                                                                    });
                                                                    tb_CPT.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            tb_CPTKeyPressed(evt);
                                                                        }
                                                                    });
                                                                    jScrollPane4.setViewportView(tb_CPT);

                                                                    panelNumeros.setBackground(new java.awt.Color(255, 255, 255));
                                                                    panelNumeros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                                                                    jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                                                                    jLabel26.setText("Total");

                                                                    total.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
                                                                    total.setForeground(new java.awt.Color(51, 51, 51));
                                                                    total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                    total.setBorder(null);
                                                                    total.setEnabled(false);

                                                                    total1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
                                                                    total1.setForeground(new java.awt.Color(51, 51, 51));
                                                                    total1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                    total1.setBorder(null);
                                                                    total1.setEnabled(false);

                                                                    jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                                                                    jLabel27.setText("Descuento");

                                                                    total2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
                                                                    total2.setForeground(new java.awt.Color(51, 51, 51));
                                                                    total2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                    total2.setBorder(null);
                                                                    total2.setEnabled(false);

                                                                    jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                                                                    jLabel28.setText("Subtotal");

                                                                    total3.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
                                                                    total3.setForeground(new java.awt.Color(51, 51, 51));
                                                                    total3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                    total3.setBorder(null);
                                                                    total3.setEnabled(false);

                                                                    jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    jLabel39.setForeground(new java.awt.Color(51, 51, 51));
                                                                    jLabel39.setText("IGV");

                                                                    javax.swing.GroupLayout panelNumerosLayout = new javax.swing.GroupLayout(panelNumeros);
                                                                    panelNumeros.setLayout(panelNumerosLayout);
                                                                    panelNumerosLayout.setHorizontalGroup(
                                                                        panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelNumerosLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jLabel27)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(total1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(60, 60, 60)
                                                                            .addComponent(jLabel28)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(60, 60, 60)
                                                                            .addComponent(jLabel39)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(total3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(60, 60, 60)
                                                                            .addComponent(jLabel26)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    panelNumerosLayout.setVerticalGroup(
                                                                        panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelNumerosLayout.createSequentialGroup()
                                                                            .addContainerGap(12, Short.MAX_VALUE)
                                                                            .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel26)
                                                                                .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(jLabel27)
                                                                                    .addComponent(total1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(jLabel28)
                                                                                        .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(jLabel39)
                                                                                    .addComponent(total3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    panelPreventa.setBackground(new java.awt.Color(25, 188, 157));
                                                                    panelPreventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    panelPreventa.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            panelPreventaMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    panelPreventa.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            panelPreventaKeyPressed(evt);
                                                                        }
                                                                    });

                                                                    jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                                    jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel15.setText("Preventas pendiente de pago, cargar?");

                                                                    btnbuscar5.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btnbuscar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Recordatorios de citas-32.png"))); // NOI18N
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

                                                                    javax.swing.GroupLayout panelPreventaLayout = new javax.swing.GroupLayout(panelPreventa);
                                                                    panelPreventa.setLayout(panelPreventaLayout);
                                                                    panelPreventaLayout.setHorizontalGroup(
                                                                        panelPreventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelPreventaLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(btnbuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(jLabel36)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jLabel15)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    panelPreventaLayout.setVerticalGroup(
                                                                        panelPreventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelPreventaLayout.createSequentialGroup()
                                                                            .addGroup(panelPreventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panelPreventaLayout.createSequentialGroup()
                                                                                    .addContainerGap()
                                                                                    .addComponent(btnbuscar5))
                                                                                .addGroup(panelPreventaLayout.createSequentialGroup()
                                                                                    .addGap(14, 14, 14)
                                                                                    .addGroup(panelPreventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(jLabel15)
                                                                                        .addComponent(jLabel36))))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    sinanular.setText("jLabel41");

                                                                    formap.setText("jLabel30");

                                                                    ok.setForeground(new java.awt.Color(51, 51, 51));
                                                                    ok.setText("ok");

                                                                    fua.setText("jLabel42");

                                                                    panelDatosGenerales.setBackground(new java.awt.Color(43, 43, 43));

                                                                    txtape.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                    txtape.setForeground(new java.awt.Color(255, 255, 255));
                                                                    txtape.setText("Paciente");

                                                                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    jLabel11.setForeground(new java.awt.Color(204, 204, 204));
                                                                    jLabel11.setText("DNI");

                                                                    lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    lblDNI.setForeground(new java.awt.Color(204, 204, 204));
                                                                    lblDNI.setText(" ");

                                                                    jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    jLabel12.setForeground(new java.awt.Color(204, 204, 204));
                                                                    jLabel12.setText("Historia Clínica");

                                                                    lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    lblDireccion.setForeground(new java.awt.Color(204, 204, 204));
                                                                    lblDireccion.setText(" ");

                                                                    jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    jLabel14.setForeground(new java.awt.Color(204, 204, 204));
                                                                    jLabel14.setText("Dirección ");

                                                                    lblHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                    lblHC.setForeground(new java.awt.Color(204, 204, 204));
                                                                    lblHC.setText(" ");

                                                                    javax.swing.GroupLayout panelDatosGeneralesLayout = new javax.swing.GroupLayout(panelDatosGenerales);
                                                                    panelDatosGenerales.setLayout(panelDatosGeneralesLayout);
                                                                    panelDatosGeneralesLayout.setHorizontalGroup(
                                                                        panelDatosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelDatosGeneralesLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(panelDatosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(panelDatosGeneralesLayout.createSequentialGroup()
                                                                                    .addGroup(panelDatosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel12)
                                                                                        .addComponent(jLabel11)
                                                                                        .addComponent(jLabel14))
                                                                                    .addGap(62, 62, 62)
                                                                                    .addGroup(panelDatosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(lblDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(lblHC, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                            .addContainerGap(580, Short.MAX_VALUE))
                                                                    );
                                                                    panelDatosGeneralesLayout.setVerticalGroup(
                                                                        panelDatosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelDatosGeneralesLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(txtape)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(panelDatosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel11)
                                                                                .addComponent(lblDNI))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(panelDatosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel12)
                                                                                .addComponent(lblHC))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(panelDatosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel14)
                                                                                .addComponent(lblDireccion))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    panelMensaje.setBackground(new java.awt.Color(255, 91, 70));

                                                                    Mensaje4.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                                    Mensaje4.setForeground(new java.awt.Color(255, 255, 255));
                                                                    Mensaje4.setText("Cancelar la venta ?");

                                                                    eli4.setForeground(new java.awt.Color(240, 240, 240));
                                                                    eli4.setText("Si");
                                                                    eli4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    eli4.setContentAreaFilled(false);
                                                                    eli4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    eli4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    eli4.setIconTextGap(30);
                                                                    eli4.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            eli4ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    noeli4.setForeground(new java.awt.Color(240, 240, 240));
                                                                    noeli4.setText("No");
                                                                    noeli4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    noeli4.setContentAreaFilled(false);
                                                                    noeli4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    noeli4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    noeli4.setIconTextGap(30);
                                                                    noeli4.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            noeli4ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout panelMensajeLayout = new javax.swing.GroupLayout(panelMensaje);
                                                                    panelMensaje.setLayout(panelMensajeLayout);
                                                                    panelMensajeLayout.setHorizontalGroup(
                                                                        panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelMensajeLayout.createSequentialGroup()
                                                                            .addGap(22, 22, 22)
                                                                            .addComponent(Mensaje4)
                                                                            .addGap(43, 43, 43)
                                                                            .addComponent(eli4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(noeli4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    panelMensajeLayout.setVerticalGroup(
                                                                        panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMensajeLayout.createSequentialGroup()
                                                                            .addContainerGap(17, Short.MAX_VALUE)
                                                                            .addGroup(panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Mensaje4)
                                                                                .addComponent(eli4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(noeli4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap())
                                                                    );

                                                                    panelFormaPago.setBackground(new java.awt.Color(255, 255, 255));
                                                                    panelFormaPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                                    txtFormaPago.setEditable(false);
                                                                    txtFormaPago.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                                    txtFormaPago.setForeground(new java.awt.Color(51, 51, 51));
                                                                    txtFormaPago.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                                    txtFormaPago.setBorder(null);
                                                                    txtFormaPago.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                                                                    txtFormaPago.setSelectionColor(new java.awt.Color(255, 255, 255));
                                                                    txtFormaPago.addCaretListener(new javax.swing.event.CaretListener() {
                                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                            txtFormaPagoCaretUpdate(evt);
                                                                        }
                                                                    });

                                                                    btnBuscarFormaPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                                    btnBuscarFormaPago.setContentAreaFilled(false);
                                                                    btnBuscarFormaPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnBuscarFormaPago.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnBuscarFormaPagoActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout panelFormaPagoLayout = new javax.swing.GroupLayout(panelFormaPago);
                                                                    panelFormaPago.setLayout(panelFormaPagoLayout);
                                                                    panelFormaPagoLayout.setHorizontalGroup(
                                                                        panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                                                            .addGap(2, 2, 2)
                                                                            .addComponent(txtFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(btnBuscarFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(3, 3, 3))
                                                                    );
                                                                    panelFormaPagoLayout.setVerticalGroup(
                                                                        panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                                                            .addGap(0, 0, 0)
                                                                            .addGroup(panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(txtFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                                .addComponent(btnBuscarFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    lblNumeroDoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    lblNumeroDoc.setForeground(new java.awt.Color(51, 51, 51));
                                                                    lblNumeroDoc.setText("000000000000-00562");

                                                                    panelActoMedico.setBackground(new java.awt.Color(22, 22, 22));
                                                                    panelActoMedico.setForeground(new java.awt.Color(51, 51, 51));

                                                                    jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                    jLabel30.setForeground(new java.awt.Color(204, 204, 204));
                                                                    jLabel30.setText("Acto Medico");

                                                                    lblActoMedico.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                                                                    lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));
                                                                    lblActoMedico.setText("   ");

                                                                    javax.swing.GroupLayout panelActoMedicoLayout = new javax.swing.GroupLayout(panelActoMedico);
                                                                    panelActoMedico.setLayout(panelActoMedicoLayout);
                                                                    panelActoMedicoLayout.setHorizontalGroup(
                                                                        panelActoMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelActoMedicoLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jLabel30)
                                                                            .addGap(65, 65, 65)
                                                                            .addComponent(lblActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    panelActoMedicoLayout.setVerticalGroup(
                                                                        panelActoMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelActoMedicoLayout.createSequentialGroup()
                                                                            .addGap(6, 6, 6)
                                                                            .addGroup(panelActoMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel30)
                                                                                .addComponent(lblActoMedico))
                                                                            .addGap(6, 6, 6))
                                                                    );

                                                                    lblDescripcion.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                                    lblDescripcion.setForeground(new java.awt.Color(51, 51, 51));
                                                                    lblDescripcion.setText("Nueva Venta");

                                                                    txthc.setText("jLabel1");

                                                                    lblAREA.setForeground(new java.awt.Color(204, 204, 0));
                                                                    lblAREA.setText("jLabel57");

                                                                    SE.setBackground(new java.awt.Color(255, 102, 102));
                                                                    SE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    SE.setForeground(new java.awt.Color(255, 51, 153));
                                                                    SE.setText("8");

                                                                    NCP.setForeground(new java.awt.Color(204, 0, 204));
                                                                    NCP.setText("nomenclatura pre");

                                                                    lblIdPreventas.setText("000");

                                                                    lblIdMedico.setText("medico");

                                                                    MHOS.setText("MHOS");

                                                                    MODULO.setText("modulo");

                                                                    AMA.setForeground(new java.awt.Color(255, 255, 255));
                                                                    AMA.setText("AMA");

                                                                    AMN.setText("n acto m");

                                                                    abonos.setText("jLabel56");

                                                                    txtedad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    txtedad.setForeground(new java.awt.Color(102, 102, 102));
                                                                    txtedad.setBorder(null);
                                                                    txtedad.setEnabled(false);

                                                                    sexo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    sexo.setForeground(new java.awt.Color(102, 102, 102));
                                                                    sexo.setBorder(null);
                                                                    sexo.setEnabled(false);

                                                                    fp.setBackground(new java.awt.Color(255, 255, 255));
                                                                    fp.setForeground(new java.awt.Color(255, 255, 255));
                                                                    fp.setText("jLabel8");

                                                                    panelAbonos.setBackground(new java.awt.Color(51, 51, 0));

                                                                    javax.swing.GroupLayout panelAbonosLayout = new javax.swing.GroupLayout(panelAbonos);
                                                                    panelAbonos.setLayout(panelAbonosLayout);
                                                                    panelAbonosLayout.setHorizontalGroup(
                                                                        panelAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 100, Short.MAX_VALUE)
                                                                    );
                                                                    panelAbonosLayout.setVerticalGroup(
                                                                        panelAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(0, 31, Short.MAX_VALUE)
                                                                    );

                                                                    ca_id.setText("0");

                                                                    panelCPT.setBackground(new java.awt.Color(255, 255, 255));
                                                                    panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                                    txtCPT.setEditable(false);
                                                                    txtCPT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                                    txtCPT.setForeground(new java.awt.Color(51, 51, 51));
                                                                    txtCPT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                                    txtCPT.setBorder(null);
                                                                    txtCPT.addCaretListener(new javax.swing.event.CaretListener() {
                                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                            txtCPTCaretUpdate(evt);
                                                                        }
                                                                    });

                                                                    btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                                    btnBuscarCPT.setContentAreaFilled(false);
                                                                    btnBuscarCPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnBuscarCPTActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
                                                                    panelCPT.setLayout(panelCPTLayout);
                                                                    panelCPTLayout.setHorizontalGroup(
                                                                        panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelCPTLayout.createSequentialGroup()
                                                                            .addGap(2, 2, 2)
                                                                            .addComponent(txtCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(3, 3, 3))
                                                                    );
                                                                    panelCPTLayout.setVerticalGroup(
                                                                        panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelCPTLayout.createSequentialGroup()
                                                                            .addGap(0, 0, 0)
                                                                            .addGroup(panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(txtCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                                .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    panelEliminacion.setBackground(new java.awt.Color(255, 91, 70));

                                                                    btnEliminarDetalle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    btnEliminarDetalle.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btnEliminarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                                                                    btnEliminarDetalle.setText("Eliminar ");
                                                                    btnEliminarDetalle.setContentAreaFilled(false);
                                                                    btnEliminarDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnEliminarDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                    btnEliminarDetalle.setIconTextGap(30);
                                                                    btnEliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnEliminarDetalleActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    panelEliminarDetalle.setBackground(new java.awt.Color(255, 91, 70));

                                                                    Mensaje5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    Mensaje5.setForeground(new java.awt.Color(255, 255, 255));
                                                                    Mensaje5.setText("Desea Eliminar el Registro ?");

                                                                    eli5.setForeground(new java.awt.Color(240, 240, 240));
                                                                    eli5.setText("Si");
                                                                    eli5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    eli5.setContentAreaFilled(false);
                                                                    eli5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    eli5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    eli5.setIconTextGap(30);
                                                                    eli5.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            eli5ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    noeli5.setForeground(new java.awt.Color(240, 240, 240));
                                                                    noeli5.setText("No");
                                                                    noeli5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    noeli5.setContentAreaFilled(false);
                                                                    noeli5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    noeli5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    noeli5.setIconTextGap(30);
                                                                    noeli5.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            noeli5ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout panelEliminarDetalleLayout = new javax.swing.GroupLayout(panelEliminarDetalle);
                                                                    panelEliminarDetalle.setLayout(panelEliminarDetalleLayout);
                                                                    panelEliminarDetalleLayout.setHorizontalGroup(
                                                                        panelEliminarDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminarDetalleLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(Mensaje5)
                                                                            .addGap(82, 82, 82)
                                                                            .addComponent(eli5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(noeli5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap())
                                                                    );
                                                                    panelEliminarDetalleLayout.setVerticalGroup(
                                                                        panelEliminarDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminarDetalleLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(panelEliminarDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Mensaje5)
                                                                                .addComponent(eli5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(noeli5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap())
                                                                    );

                                                                    javax.swing.GroupLayout panelEliminacionLayout = new javax.swing.GroupLayout(panelEliminacion);
                                                                    panelEliminacion.setLayout(panelEliminacionLayout);
                                                                    panelEliminacionLayout.setHorizontalGroup(
                                                                        panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminacionLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(btnEliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(103, 103, 103)
                                                                            .addComponent(panelEliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    panelEliminacionLayout.setVerticalGroup(
                                                                        panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelEliminacionLayout.createSequentialGroup()
                                                                            .addGap(6, 6, 6)
                                                                            .addGroup(panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(panelEliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnEliminarDetalle))
                                                                            .addGap(6, 6, 6))
                                                                    );

                                                                    panelAnular.setBackground(new java.awt.Color(243, 156, 18));
                                                                    panelAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    panelAnular.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                            panelAnularMouseClicked(evt);
                                                                        }
                                                                    });
                                                                    panelAnular.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                            panelAnularKeyPressed(evt);
                                                                        }
                                                                    });

                                                                    btnbuscar9.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btnbuscar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Error-32.png"))); // NOI18N
                                                                    btnbuscar9.setContentAreaFilled(false);
                                                                    btnbuscar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnbuscar9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    btnbuscar9.setIconTextGap(30);
                                                                    btnbuscar9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                    btnbuscar9.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnbuscar9ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    jLabel38.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                                    jLabel38.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel38.setText("Esta venta esta tiene varios detalles");

                                                                    eli6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    eli6.setForeground(new java.awt.Color(240, 240, 240));
                                                                    eli6.setText("Terminar y empezar una nueva venta");
                                                                    eli6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    eli6.setContentAreaFilled(false);
                                                                    eli6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    eli6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    eli6.setIconTextGap(30);
                                                                    eli6.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            eli6ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    jPanel17.setBackground(new java.awt.Color(255, 91, 70));
                                                                    jPanel17.setPreferredSize(new java.awt.Dimension(125, 25));

                                                                    noeli6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                    noeli6.setForeground(new java.awt.Color(240, 240, 240));
                                                                    noeli6.setText("Anular");
                                                                    noeli6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                    noeli6.setContentAreaFilled(false);
                                                                    noeli6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    noeli6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                    noeli6.setIconTextGap(30);
                                                                    noeli6.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            noeli6ActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                                                                    jPanel17.setLayout(jPanel17Layout);
                                                                    jPanel17Layout.setHorizontalGroup(
                                                                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                            .addComponent(noeli6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    );
                                                                    jPanel17Layout.setVerticalGroup(
                                                                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(noeli6, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                                                    );

                                                                    javax.swing.GroupLayout panelAnularLayout = new javax.swing.GroupLayout(panelAnular);
                                                                    panelAnular.setLayout(panelAnularLayout);
                                                                    panelAnularLayout.setHorizontalGroup(
                                                                        panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelAnularLayout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(btnbuscar9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(jLabel38)
                                                                            .addGap(73, 73, 73)
                                                                            .addComponent(eli6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(26, 26, 26)
                                                                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    panelAnularLayout.setVerticalGroup(
                                                                        panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelAnularLayout.createSequentialGroup()
                                                                            .addGroup(panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panelAnularLayout.createSequentialGroup()
                                                                                    .addContainerGap()
                                                                                    .addComponent(btnbuscar9))
                                                                                .addGroup(panelAnularLayout.createSequentialGroup()
                                                                                    .addGap(14, 14, 14)
                                                                                    .addGroup(panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                            .addComponent(jLabel38)
                                                                                            .addComponent(eli6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                                                    jPanel3.setLayout(jPanel3Layout);
                                                                    jPanel3Layout.setHorizontalGroup(
                                                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(panelPreventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(panelDatosGenerales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(panelMensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(panelActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                            .addComponent(fp)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                            .addComponent(nom))
                                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                            .addComponent(txthc)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                            .addComponent(lblAREA)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                            .addComponent(NCP)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                            .addComponent(lblIdPreventas)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                            .addComponent(lblIdMedico)
                                                                                            .addGap(18, 18, 18)
                                                                                            .addComponent(MHOS)
                                                                                            .addGap(5, 5, 5)
                                                                                            .addComponent(MODULO)
                                                                                            .addGap(5, 5, 5)
                                                                                            .addComponent(AMA)
                                                                                            .addGap(7, 7, 7)
                                                                                            .addComponent(AMN, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                            .addComponent(abonos)
                                                                                            .addGap(10, 10, 10)
                                                                                            .addComponent(SE)
                                                                                            .addGap(18, 18, 18)
                                                                                            .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                            .addGap(18, 18, 18)
                                                                                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                            .addGap(18, 18, 18)
                                                                                            .addComponent(ca_id)
                                                                                            .addGap(18, 18, 18)
                                                                                            .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                            .addComponent(lblcodigo)
                                                                                            .addGap(18, 18, 18)
                                                                                            .addComponent(lblhc)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                            .addComponent(sinanular)
                                                                                            .addGap(18, 18, 18)
                                                                                            .addComponent(formap)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                            .addComponent(fua)))
                                                                                    .addGap(10, 10, 10)
                                                                                    .addComponent(panelAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                    .addComponent(lDoc)
                                                                                    .addGap(18, 18, 18)
                                                                                    .addComponent(lblNumeroDoc))
                                                                                .addComponent(lblDescripcion)
                                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(lTipoDoc)
                                                                                        .addComponent(lForma)
                                                                                        .addComponent(lblCPT))
                                                                                    .addGap(42, 42, 42)
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(panelFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(panelEliminacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(panelNumeros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(panelAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    );
                                                                    jPanel3Layout.setVerticalGroup(
                                                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addComponent(panelDatosGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(panelActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(panelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(panelPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(panelAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(lblDescripcion)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(lTipoDoc)
                                                                                .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(lDoc)
                                                                                .addComponent(lblNumeroDoc))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(lForma)
                                                                                .addComponent(panelFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(lblCPT)
                                                                                .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(3, 3, 3)
                                                                            .addComponent(panelEliminacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(panelNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(10, 10, 10)
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(nom)
                                                                                        .addComponent(fp))
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(lblAREA)
                                                                                        .addComponent(NCP)
                                                                                        .addComponent(lblIdPreventas)
                                                                                        .addComponent(lblIdMedico)
                                                                                        .addComponent(MHOS)
                                                                                        .addComponent(MODULO)
                                                                                        .addComponent(AMA)
                                                                                        .addComponent(AMN)
                                                                                        .addComponent(abonos)
                                                                                        .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(txthc)
                                                                                        .addComponent(ca_id)
                                                                                        .addComponent(ok)
                                                                                        .addComponent(lblcodigo)
                                                                                        .addComponent(lblhc)
                                                                                        .addComponent(sinanular)
                                                                                        .addComponent(formap)
                                                                                        .addComponent(fua)
                                                                                        .addComponent(SE)))
                                                                                .addComponent(panelAbonos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    jTabbedPane1.addTab(" ", jPanel3);

                                                                    jPanel21.setBackground(new java.awt.Color(50, 151, 219));

                                                                    jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                    jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                                                                    jLabel57.setText("<html>Ventas<span style=\"font-size:'15px'\"><br>Caja Central</br></span></html>");

                                                                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                                                                    lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                                                                    lblusu.setText("Silvana");
                                                                    lblusu.setFocusable(false);
                                                                    lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                                                    btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                    btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
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

                                                                    jPanel23.setBackground(new java.awt.Color(255, 255, 255));

                                                                    txtBuscar4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                                    txtBuscar4.setForeground(new java.awt.Color(51, 51, 51));
                                                                    txtBuscar4.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                                    txtBuscar4.setToolTipText("");
                                                                    txtBuscar4.setBorder(null);
                                                                    txtBuscar4.addCaretListener(new javax.swing.event.CaretListener() {
                                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                            txtBuscar4CaretUpdate(evt);
                                                                        }
                                                                    });

                                                                    javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                                                                    jPanel23.setLayout(jPanel23Layout);
                                                                    jPanel23Layout.setHorizontalGroup(
                                                                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                                                            .addGap(2, 2, 2)
                                                                            .addComponent(txtBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );
                                                                    jPanel23Layout.setVerticalGroup(
                                                                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(txtBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    );

                                                                    btnguardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                    btnguardar.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imprimir-32.png"))); // NOI18N
                                                                    btnguardar.setText("Imprimir");
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

                                                                    btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                                                    btnBuscarPaciente.setToolTipText("");
                                                                    btnBuscarPaciente.setContentAreaFilled(false);
                                                                    btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                    btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                            btnBuscarPacienteActionPerformed(evt);
                                                                        }
                                                                    });

                                                                    btneliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                    btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                                                                    btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
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

                                                                    lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                                                                    lbldetalle.setText("DNI, Apellidos, Nombres");

                                                                    javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                                                                    jPanel21.setLayout(jPanel21Layout);
                                                                    jPanel21Layout.setHorizontalGroup(
                                                                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel21Layout.createSequentialGroup()
                                                                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                                                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel21Layout.createSequentialGroup()
                                                                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(jPanel21Layout.createSequentialGroup()
                                                                                            .addContainerGap()
                                                                                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(jPanel21Layout.createSequentialGroup()
                                                                                            .addContainerGap()
                                                                                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(jPanel21Layout.createSequentialGroup()
                                                                                            .addContainerGap()
                                                                                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                                                                                                    .addGap(227, 227, 227)
                                                                                                    .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                        .addGroup(jPanel21Layout.createSequentialGroup()
                                                                                            .addGap(24, 24, 24)
                                                                                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(lbldetalle))))
                                                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                                                            .addContainerGap())
                                                                    );
                                                                    jPanel21Layout.setVerticalGroup(
                                                                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(34, 34, 34)
                                                                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(lbldetalle)
                                                                            .addGap(21, 21, 21)
                                                                            .addComponent(btnNuevo)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(btnguardar)
                                                                            .addGap(18, 18, 18)
                                                                            .addComponent(btneliminar)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(lblusu))
                                                                    );

                                                                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                                                    getContentPane().setLayout(layout);
                                                                    layout.setHorizontalGroup(
                                                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(0, 0, 0)
                                                                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE))
                                                                    );
                                                                    layout.setVerticalGroup(
                                                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTabbedPane1)
                                                                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    );

                                                                    pack();
                                                                }// </editor-fold>//GEN-END:initComponents

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
            int fila = tb_Grupo1.getSelectedRow();
            ACTM.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 13)));
            APENOM.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            DNII.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            HCI.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
            bus1.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 14)));
            BusquedaDet();

    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed

    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed

    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        jTabbedPane2.setSelectedIndex(1);
        BuscarHC();

        if (tbPacientes.getRowCount() == 0){
            jTabbedPane2.setSelectedIndex(2);
            }
          if (txtBuscarPaciente.getText().length()==0){
             jTabbedPane2.setSelectedIndex(0);
        }

        
           
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void txtBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPacienteActionPerformed

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
       Caja_NuevaVenta CP= new Caja_NuevaVenta();
       Caja_NuevaVenta CP2= new Caja_NuevaVenta();
       Caja_NuevaVenta CPAM= new Caja_NuevaVenta();
        int fila=tbPacientes.getSelectedRow();
        
       bus.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
       bus3.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
           Preventamostrar();
           CPAM.Caja_Id_Preventa(bus.getText());
           CP.ConsultoriosExtPREVENTAListar(lblIdPreventa.getText());
           CP2.ConsultoriosExtPREVENTAListarCEX(lblIdPreventa.getText());
           
            Abonnos();
        if(evt.getClickCount()==2){
            BHC.dispose();
            panelDatosGenerales.setVisible(true);
            panelActoMedico.setVisible(true);

            txthc.setText(String.valueOf(tbPacientes.getValueAt(fila, 0)));
            lblHC.setText(String.valueOf(tbPacientes.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            lblDireccion.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));    
            lblDNI.setText(String.valueOf(tbPacientes.getValueAt(fila, 3)));
            sexo.setText(String.valueOf(tbPacientes.getValueAt(fila, 4)));
            txtedad.setText(String.valueOf(tbPacientes.getValueAt(fila, 6)));
            lblhc.setText(String.valueOf(tbPacientes.getValueAt(fila, 7))); 
           
            
          ////////////////////////

	     jLabel36.setText(String.valueOf(tbpreventas.getRowCount()));
             

             if(this.tbpreventas.getRowCount()!=0  ){
              panelPreventa.setVisible(true);
              panelPreventa.requestFocus();
             }
              if(this.tbpreventas.getRowCount()==0  ){
              panelPreventa.setVisible(false);
             }
             
        if(this.ABONOS.getRowCount()!=0  ){
              panelAbonos.setVisible(true);
              abonos.setText("Si");
          }
            if(this.ABONOS.getRowCount()==0  ){
             panelAbonos.setVisible(false);
             abonos.setText("No");
             txtBuscar2.setText("");
             }
            fua.setText(cnn1.idSIS());
            
            
            
            
            lTipoDoc.setVisible(true);
            cbxTipoDocumento.setVisible(true);    
            cbxTipoDocumento.showPopup();
            cbxTipoDocumento.requestFocus(true);
            
            
            
         
        }
    }//GEN-LAST:event_tbPacientesMouseClicked

    private void tbPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyPressed
         
        char teclaPresionada = evt.getKeyChar();
        
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbPacientes.getSelectedRow();
             BHC.dispose();
            bus.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
            bus3.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
            Preventamostrar();
            Abonnos();
           
         
            
            txthc.setText(String.valueOf(tbPacientes.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            lblDireccion.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));    
            lblDNI.setText(String.valueOf(tbPacientes.getValueAt(fila, 3)));
            sexo.setText(String.valueOf(tbPacientes.getValueAt(fila, 4)));
            txtedad.setText(String.valueOf(tbPacientes.getValueAt(fila, 6)));
            lblhc.setText(String.valueOf(tbPacientes.getValueAt(fila, 7))); 
          
            jLabel36.setText(String.valueOf(tbpreventas.getRowCount()));
            
            
          ////////////////////////
            
           if(this.tbpreventas.getRowCount()!=0  ){
              panelPreventa.setVisible(true);
              panelPreventa.requestFocus();

              lblCPT.setVisible(false);
              txtCPT.setVisible(false);
              btnBuscarCPT.setVisible(false);
              jScrollPane4.setVisible(false);

          }
            if(this.tbpreventas.getRowCount()==0  ){
              panelPreventa.setVisible(false);

              lblCPT.setVisible(true);
              txtCPT.setVisible(true);
              btnBuscarCPT.setVisible(true);
              jScrollPane4.setVisible(true);
              nomenclaturas.setVisible(true);
              txtBuscar2.setText("");
             }
            
            if(this.ABONOS.getRowCount()!=0  ){
              panelAbonos.setVisible(true);
              abonos.setText("Si");
   
          }
            if(this.ABONOS.getRowCount()==0  ){
             panelAbonos.setVisible(false);
             abonos.setText("No");
             txtBuscar2.setText("");
             }
            fua.setText(cnn1.idSIS());
            Guardar();
            ok.setText("okk");
            if(txtFormaPago.getText().equalsIgnoreCase("SIS" ) || txtFormaPago.getText().equalsIgnoreCase("SIS CREDITOS"  )){
             GuardarSIS();
             } 
            
        }
        
        
    }//GEN-LAST:event_tbPacientesKeyPressed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
       BHC.dispose();
        FrmNuevaHistoriaC frmEmerList = new FrmNuevaHistoriaC();
        frmEmerList.setVisible(true);
        
//        String u=PrincipalMDI.lblUsu.getText();
//        frmEmerList.lblUsuUsuario.setText(u);
//        FrmListFormatoEmergencia.tbpReporteEmergencia.setSelectedIndex(1);       
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void tb_Grupo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo3MouseClicked
         int fila=tb_Grupo3.getSelectedRow();
        if(evt.getClickCount()==2){
            Jerarquias.dispose();
            txtFormaPago.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
            formap.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
            

            lblCPT.setVisible(true);
            panelCPT.setVisible(true);
            jScrollPane4.setVisible(true);
            
            
            
            
            Guardar();
           
            ok.setText("okk");
             if(txtFormaPago.getText().equalsIgnoreCase("SIS" ) || txtFormaPago.getText().equalsIgnoreCase("SIS CREDITOS"  )){
             GuardarSIS();
             } 
            }  
           
    }//GEN-LAST:event_tb_Grupo3MouseClicked

    private void tb_Grupo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo3KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo3.getSelectedRow();
            Jerarquias.dispose();
//            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtFormaPago.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
            formap.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
           
            } 
            
         
             
 
       
    }//GEN-LAST:event_tb_Grupo3KeyPressed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
          jTabbedPane3.setSelectedIndex(1);
        BuscarN();
        
        if (tb_CPTBUSCAR.getRowCount() == 0){
            jTabbedPane3.setSelectedIndex(2);
            }
          if (txtBuscar2.getText().length()==0){
             jTabbedPane3.setSelectedIndex(0);
        }
          
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void tb_CPTBUSCARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTBUSCARMouseClicked
        int fila=tb_CPTBUSCAR.getSelectedRow();
        if(evt.getClickCount()==2){
        nomenclaturas.dispose();
            SE.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 7)));
            NCP.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 5)));
            GuardarDetalle();
            panelNumeros.setVisible(true);
            suma();
            }
    }//GEN-LAST:event_tb_CPTBUSCARMouseClicked

    private void tb_CPTBUSCARKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTBUSCARKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_CPTBUSCAR.getSelectedRow();
            nomenclaturas.dispose();
            SE.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 7)));
            NCP.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 5)));
            GuardarDetalle();
            panelNumeros.setVisible(true);
            suma();
            }
    }//GEN-LAST:event_tb_CPTBUSCARKeyPressed
  private void suma()
    {
        double total = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for( int i=0 ; i<tb_CPT.getRowCount() ; i++)
        {
            double numero =0,st=0;
            try{
                //capturamos valor de celda
                numero = Double.parseDouble(tb_CPT.getValueAt(i, 2).toString() );
                
  
            }
            catch (NumberFormatException nfe){ //si existe un error se coloca 0 a la celda
                numero = 0;
                tb_CPT.setValueAt(0, i, 2);
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

    private void btnbuscar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar7ActionPerformed
           nuevoanulacion.setVisible(true);
            lblcodanu.setText(cnnA.idanu());
           txtanular.requestFocus();
          jLabel32.setVisible(false);
          CBXANULAR.setVisible(false);
          elimma.setVisible(false);
          cargareliminarma.setVisible(false);
          btnbuscar1.setVisible(true);
          eli2.setVisible(true);
          noeli2.setVisible(true); 
          btnbuscar8.setEnabled(true);
          btnbuscar8.setVisible(true); 
          btnbuscar2.setVisible(true);
          jPanel39.setVisible(false);
          txtanular.setText("");
          txtanular.requestFocus();
          M.setText("");
    }//GEN-LAST:event_btnbuscar7ActionPerformed

    private void btnbuscar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar8ActionPerformed
       tge=3;
       eli.setVisible(true);
       noeli.setVisible(true);
        GuardarAnulacion();
    }//GEN-LAST:event_btnbuscar8ActionPerformed

    private void tbpreventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpreventasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasMouseClicked

    private void tbpreventasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpreventasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasKeyPressed

    private void btnbuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar4ActionPerformed
        jTabbedPane5.setSelectedIndex(0);
      
    }//GEN-LAST:event_btnbuscar4ActionPerformed

    private void btnbuscar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar6ActionPerformed
          jTabbedPane5.setSelectedIndex(3);
    }//GEN-LAST:event_btnbuscar6ActionPerformed

    private void tEMEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tEMEMouseClicked
       ///////////////////////////////////////////////////////////////////////////
        /////////////////////VENDER CABECERA PREVENTA
       Caja_Preventa cpam = new Caja_Preventa(); 
        int fila=tEME.getSelectedRow();
     if(evt.getClickCount()==1){
       NCP.setText(String.valueOf(tEME.getValueAt(fila, 11)));  
       lblAREA.setText(String.valueOf(tEME.getValueAt(fila, 12))); 
       lblIdPreventas.setText(String.valueOf(tEME.getValueAt(fila, 0))); 
       //////////////////////ANULAR
       MHOS.setText(String.valueOf(tEME.getValueAt(fila, 4))); 
       ////////////////////////////////////////////////////////
      
     } 
 
        lblActoMedico.setText((cpam.Ultima_CEX(lblhc.getText())));
        btnCargarEME.setVisible(true);
        btnEliminarEME.setVisible(true);
        tgp=1;
        ////////////////////////////////////////
             if(lblActoMedico.getText()==""){
       lblActoMedico.setText(AMN.getText());    
       }
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
                    btnCargarEME.setVisible(true);
                    btnEliminarEME.setVisible(true);
                    tgp=2;
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
                    btnCargarEME.setVisible(true);
                    btnEliminarEME.setVisible(true);
                    tgp=2;
                }
            } catch (Exception e) {
                System.out.println("Error seleccionar: " + e.getMessage());
              }
        }
        int fila=tEMEDET.getSelectedRow();         
        elimdp.setText(String.valueOf(tEMEDET.getValueAt(fila, 6)));
    }//GEN-LAST:event_tEMEDETKeyPressed

    private void btnCargarEMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarEMEActionPerformed

        if(tgp==1){
          Caja_Preventa CSE = new Caja_Preventa();
        SE.setText(CSE.CodSE(lblAREA.getText()));
       
//        Medicos.setVisible(true);
//        listarMedicos1();
        
         Medicos.setVisible(true);
        listarMedicos1();
        //////////////////

         preventas.dispose();   
        }
       

       
    
    }//GEN-LAST:event_btnCargarEMEActionPerformed

    private void tEMEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tEMEMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tEMEMouseEntered

    private void btnEliminarEMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEMEActionPerformed
 
        panelEliminarEME.setBackground(new Color(255,91,70)); 
                    Mensaje.setText("Desea Eliminar el Registro ?");
                    eli.setVisible(true);
                    noeli.setVisible(true);
        
        panelEliminarEME.setVisible(true);

    }//GEN-LAST:event_btnEliminarEMEActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
if(tgp==1){
   //////////////////////CABECERA       
            try{
           
                Caja_Preventa objp=new Caja_Preventa();
                objp.setId_preventa(Integer.parseInt(lblIdPreventas.getText()));
                if(objp.eliminarP())
                {
                    panelEliminarEME.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro Eliminado");
                    eli.setVisible(false);
                    noeli.setVisible(false);
                    btnEliminarEME.setVisible(false);
                }
            
        }catch(Exception e){
           
        }
          PreventaEME();    
          PreventaEMEDET(); 
}else if(tgp==2){ 
       ///////////////////////DETALLE
        try{
           
                Caja_DetallePreventas obj=new Caja_DetallePreventas();
                obj.setIdDetalle_Preventa(Integer.parseInt(elimdp.getText()));
                if(obj.eliminarDP())
                {
                    panelEliminarEME.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro Eliminado");
                    eli.setVisible(false);
                    noeli.setVisible(false);
                    btnEliminarEME.setVisible(false);
                }
            
        }catch(Exception e){
           
        }
          PreventaEMEDET();
}
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
     panelEliminarEME.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void T3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T3MouseClicked
      
    }//GEN-LAST:event_T3MouseClicked

    private void txtBuscarPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarPacienteMouseClicked

    }//GEN-LAST:event_txtBuscarPacienteMouseClicked

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tbPacientes.getSelectionModel().setSelectionInterval (0,0) ;
          tbPacientes.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        char teclaPresionada = evt.getKeyChar();
    
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_CPTBUSCAR.getSelectionModel().setSelectionInterval (0,0) ;
          tb_CPTBUSCAR.requestFocus();
        
         
           
          } 
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void T4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T4MouseClicked

    private void txtBuscar3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar3CaretUpdate
       BuscarJ();
    }//GEN-LAST:event_txtBuscar3CaretUpdate

    private void txtBuscar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscar3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar3MouseClicked

    private void txtBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar3ActionPerformed

    private void txtBuscar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar3KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo3.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo3.requestFocus();

          } 
    }//GEN-LAST:event_txtBuscar3KeyPressed

    private void T5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T5MouseClicked

    private void tb_Grupo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo6MouseClicked

    private void tb_Grupo6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo6MousePressed

    private void tb_Grupo6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo6KeyPressed

    private void eli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eli1ActionPerformed

    private void noeli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noeli1ActionPerformed

    private void ABONOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ABONOSMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ABONOSMouseClicked

    private void ABONOSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ABONOSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ABONOSKeyPressed

    private void elimmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elimmaActionPerformed

      
        cargareliminarma.setBackground(new Color(255,153,51));               
        Mensaje2.setText("Desea Anular la venta ?");
        cargareliminarma.setVisible(true);
          tge=1;
        
    }//GEN-LAST:event_elimmaActionPerformed

    private void eli2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli2ActionPerformed

        if((tge==1||tge==9) ){ //&& MHOS.getText()=="HOS"
          
           AnularDocumento();
           AnularPreventa();
           AnularCamas();
           AnularAsignacionCamas();
            }
//        if((tge==1||tge==9) && (MHOS.getText()=="EME" ||MHOS.getText()=="MHOS")){
//           AnularDocumento();
//            AnularPreventa();
//            }
        
        if(tge==2){
            
        try{
                Caja_MotivoAnulacion cnoa = new Caja_MotivoAnulacion();
                cnoa.setCod_motiv_anu(lblcodanu.getText());
                if(cnoa.eliminarA()){
                        cargareliminarma.setBackground(new Color(0,153,102)); 
                        Mensaje2.setText("Motivo eliminado");
                        cargareliminarma.setVisible(true);
                        eli2.setVisible(false);
                        noeli2.setVisible(false); 
                        elimma1.setVisible(false);
                        elimma.setVisible(false);
                        btnbuscar8.setEnabled(false);
                    LISTARMA();
                }
   
        }catch(Exception e){
             cargareliminarma.setBackground(new Color(255,153,51));               
             Mensaje2.setText("En uso no se puede eliminar");
             eli2.setVisible(true);
             noeli2.setVisible(false); 
             cargareliminarma.setVisible(true);
        }
        }else if(tge==4){
            NuevaV();
            Anular.dispose();
            cargareliminarma.setVisible(false);
            elimma.setVisible(true);
            noeli2.setVisible(true);
            CBXANULAR.setSelectedIndex(0);
            jLabel32.setVisible(true);
            CBXANULAR.setVisible(true);
            
        }  
           
           
    }//GEN-LAST:event_eli2ActionPerformed

    private void noeli2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli2ActionPerformed
   if(tge==1){
       cargareliminarma.setVisible(false);
       elimma.setVisible(true); 
        }else if(tge==2){
        cargareliminarma.setVisible(false);
       elimma1.setVisible(true);  
        }else if(tge==3){
        cargareliminarma.setVisible(false);
       elimma1.setVisible(false);  
       elimma1.setVisible(false);
        }else if(tge==9){
            nuevoanulacion.setVisible(false);
            cargareliminarma.setVisible(false);
            elimma.setVisible(true);
            noeli2.setVisible(true);
            CBXANULAR.setSelectedIndex(0);
            jLabel32.setVisible(true);
            CBXANULAR.setVisible(true);
        }            

    }//GEN-LAST:event_noeli2ActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        LISTARMA();
        sep1.setVisible(true);
        jPanel39.setVisible(true);
        nuevoanulacion.setVisible(false);
        btnbuscar8.setVisible(false);
        btnbuscar2.setVisible(true);
        elimma1.setVisible(false);
        jLabel32.setVisible(false);
        CBXANULAR.setVisible(false); 
        elimma.setVisible(false);   
        eli2.setVisible(true);
        noeli2.setVisible(true);
        btnbuscar1.setVisible(false);   
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void elimma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elimma1ActionPerformed
                      tge=2;
        cargareliminarma.setBackground(new Color(255,91,70)); 
                        Mensaje2.setText("Desea Eliminar el Registro ?");
                        cargareliminarma.setVisible(true);
                        eli2.setVisible(true);
                        noeli2.setVisible(true); 
    }//GEN-LAST:event_elimma1ActionPerformed

    private void anulacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anulacionKeyPressed
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = anulacion.getSelectedRow();
            
            txtanular.setText(String.valueOf(anulacion.getValueAt(fila, 0)));  
            lblcodanu.setText(String.valueOf(anulacion.getValueAt(fila, 1)));  
       }
           elimma1.setVisible(true);
           cargareliminarma.setVisible(false);
    }//GEN-LAST:event_anulacionKeyPressed

    private void anulacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anulacionMouseClicked
     int fila=anulacion.getSelectedRow();
     if(evt.getClickCount()==1){
       txtanular.setText(String.valueOf(anulacion.getValueAt(fila, 0)));  
       lblcodanu.setText(String.valueOf(anulacion.getValueAt(fila, 1))); 
     } 
        cargareliminarma.setVisible(false);
        elimma1.setVisible(true);
        eli2.setVisible(true);
        noeli2.setVisible(true);

    }//GEN-LAST:event_anulacionMouseClicked

    private void tHOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tHOSMouseClicked
    ///////////////////////////////////////////////////////////////////////////
        /////////////////////VENDER CABECERA PREVENTA
       tgH=0;
       Caja_Preventa cpam = new Caja_Preventa();
        int fila=tHOS.getSelectedRow();
     if(evt.getClickCount()==1){
       NCP.setText(String.valueOf(tHOS.getValueAt(fila, 13)));  
       lblAREA.setText(String.valueOf(tHOS.getValueAt(fila, 17)));
       lblIdPreventas.setText(String.valueOf(tHOS.getValueAt(fila, 0)));  
       ca_id.setText(String.valueOf(tHOS.getValueAt(fila, 14))); 
       MODULO.setText(String.valueOf(tHOS.getValueAt(fila, 6))); 
       //////////////////////ANULAR
       MHOS.setText(String.valueOf(tHOS.getValueAt(fila, 4))); 
       ////////////////////////////////////////////////////////
       
     } 
//       AM.setText((cpam.Ultima_Emergencia(lblhc.getText())));
//
//         if(AM.getText()==""){
//       AM.setText(AMN.getText());    
//       }
        jLabel34.setText(String.valueOf(tHOS.getValueAt(fila, 0)));
        PreventaHOSDET();
        
        btnCargarHOS.setVisible(true);
        btnEliminarHOS.setVisible(true);
        
       
        
        
    }//GEN-LAST:event_tHOSMouseClicked

    private void tHOSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tHOSMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tHOSMouseEntered

    private void tHOSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tHOSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tHOSKeyPressed

    private void tHOSDETMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tHOSDETMouseClicked
       ///////////////////////////////////////////////////////////////////////////
        /////////////////////VENDER CABECERA PREVENTA
        tgH=1;

       
        if(evt.getClickCount()==1){
            int filaD=tHOSDET.getSelectedRow();
            NCP.setText(String.valueOf(tHOSDET.getValueAt(filaD, 6)));  //NOMENCLATURA

            lblIdMedico.setText("");
        } 
//       AM.setText((cpam.Ultima_Emergencia(lblhc.getText())));

        btnCargarHOS.setVisible(true);
        btnEliminarHOS.setVisible(true);
        
    }//GEN-LAST:event_tHOSDETMouseClicked

    private void tHOSDETKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tHOSDETKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tHOSDETKeyPressed

    private void btnCargarHOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarHOSActionPerformed

    if(tgH==0){
        Caja_Preventa CSE = new Caja_Preventa();
        SE.setText(CSE.CodSE(lblAREA.getText()));
        AsignarCamas();
        Medicos.setVisible(true);
        listarMedicos1();
        //////////////////
         preventas.dispose(); 
    }
    if(tgH==1){
        GuardarDetalle();
        panelNumeros.setVisible(true);
        suma();
        
        
     
    }
    }//GEN-LAST:event_btnCargarHOSActionPerformed

    private void btnEliminarHOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarHOSActionPerformed

    private void eli3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli3ActionPerformed
      if(tgp==3){
   //////////////////////CABECERA       
            try{
           
                Caja_Preventa objp=new Caja_Preventa();
                objp.setId_preventa(Integer.parseInt(lblIdPreventas.getText()));
                if(objp.eliminarP())
                {
                    panelEliminarEME.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro Eliminado");
                    eli.setVisible(false);
                    noeli.setVisible(false);
                    btnEliminarEME.setVisible(false);
                }
            
        }catch(Exception e){
           
        }
          PreventaHOS();    
          PreventaHOSDET(); 
}else if(tgp==4){ 
       ///////////////////////DETALLE
        try{
           
                Caja_DetallePreventas obj=new Caja_DetallePreventas();
                obj.setIdDetalle_Preventa(Integer.parseInt(elimdp.getText()));
                if(obj.eliminarDP())
                {
                    panelEliminarEME.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro Eliminado");
                    eli.setVisible(false);
                    noeli.setVisible(false);
                    btnEliminarEME.setVisible(false);
                }
            
        }catch(Exception e){
           
        }
          PreventaHOSDET();
}
    }//GEN-LAST:event_eli3ActionPerformed

    private void noeli3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli3ActionPerformed

         
    }//GEN-LAST:event_noeli3ActionPerformed

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
         jTabbedPane5.setSelectedIndex(0);
        jPanel25.setBackground(new Color(240,240,240)); 
        jPanel41.setBackground(new Color(0,153,255));
        jPanel43.setBackground(new Color(0,153,255));
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel37KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel37KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37KeyPressed

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        jTabbedPane5.setSelectedIndex(1);
        
        jPanel41.setBackground(new Color(240,240,240)); 
        jPanel25.setBackground(new Color(0,153,255));
        jPanel43.setBackground(new Color(0,153,255));

    }//GEN-LAST:event_jLabel37MouseClicked

    private void CBXANULARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBXANULARMouseClicked
        
    }//GEN-LAST:event_CBXANULARMouseClicked

    private void CBXANULARItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBXANULARItemStateChanged
         Caja_NuevaVenta cno2 = new Caja_NuevaVenta();
        codelim.setText((cno2.anular(CBXANULAR.getSelectedItem().toString())));
    }//GEN-LAST:event_CBXANULARItemStateChanged

    private void btnbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar2ActionPerformed
       nuevoanulacion.setVisible(false);
       jPanel39.setVisible(false);
       jLabel32.setVisible(true);
       CBXANULAR.setVisible(true);
       cargareliminarma.setVisible(false);
       elimma1.setVisible(false);
       elimma.setVisible(true);
       btnbuscar2.setVisible(false);
       btnbuscar1.setVisible(false);
        btnbuscar8.setVisible(false);
    }//GEN-LAST:event_btnbuscar2ActionPerformed

    private void BMedicosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_BMedicosCaretUpdate
           
        listarMedicos();

        if (tb_medicos.getRowCount() == 0){
            jTabbedPane7.setSelectedIndex(1);
            }
          if (BMedicos.getText().length()==0){
             jTabbedPane7.setSelectedIndex(0);
        }
    }//GEN-LAST:event_BMedicosCaretUpdate

    private void BMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMedicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BMedicosActionPerformed

    private void BMedicosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BMedicosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BMedicosKeyPressed

    private void T8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T8MouseClicked

    private void tb_medicosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_medicosKeyPressed

        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            Medicos.dispose();
            int fila = tb_medicos.getSelectedRow();
            lblIdMedico.setText(String.valueOf(tb_medicos.getValueAt(fila, 0)));
            GuardarDetalle();
            ModificarPreventa();
            
        }
        
        
        
    }//GEN-LAST:event_tb_medicosKeyPressed

    private void tb_medicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_medicosMouseClicked

        int fila=tb_medicos.getSelectedRow();
        if(evt.getClickCount()==2){
            Medicos.dispose();
            lblIdMedico.setText(String.valueOf(tb_medicos.getValueAt(fila, 0)));
            GuardarDetalle();
            ModificarPreventa();
            panelNumeros.setVisible(true);
            suma();
        }
    }//GEN-LAST:event_tb_medicosMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
             panelMensaje.setVisible(false);
        if(ok.getText()=="okk" && this.tb_CPT.getRowCount()==0){ 
              panelMensaje.setBackground(new Color(255,91,70)); 
              panelMensaje.setVisible(true);
              eli4.setText("Si");
              noeli4.setVisible(true);
              Mensaje4.setText("Cancelar la venta ?");
              tgnuevoEliminar=1;
         }  
   
   if(ok.getText()=="okk" && this.tb_CPT.getRowCount()!=0  ){

              panelAnular.setVisible(true);

   }else  if(ok.getText()=="ok")
   {
 

        NuevaV();
           
               
        LISTAR();
        LISTARN();
        formatoj();
        
       this.cbxTipoDocumento.setModel(tipo());
       this.CBXANULAR.setModel(anular());
   }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscar4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar4CaretUpdate
        
         BusquedaGeneral();
        if (txtBuscar4.getText()!=""){
            resumen.setVisible(true);
            jScrollPane3.setVisible(true);
            tb_Grupo1.setVisible(true);
            jScrollPane7.setVisible(true);
            tb_Grupo6.setVisible(true);
            jTabbedPane1.setSelectedIndex(0); 
   
            tb_Grupo1.getSelectionModel().setSelectionInterval (0,0) ;
            
            
            /////////////////////////CABECERA 
            int fila = tb_Grupo1.getSelectedRow();
   
            ACTM.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 13)));
            APENOM.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            DNII.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            HCI.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
            
            bus1.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 14)));
            BusquedaDet();
            ////////////////////////////////////////
          
            
        }if (txtBuscar4.getText().length()==0){
            resumen.setVisible(false);
  
            jScrollPane3.setVisible(false);
            tb_Grupo1.setVisible(false);
            jScrollPane7.setVisible(false);
            tb_Grupo6.setVisible(false);
            
        }           

    }//GEN-LAST:event_txtBuscar4CaretUpdate

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if(this.tb_CPT.getRowCount()==0  ){
       
             panelMensaje.setVisible(true);
             eli4.setText("Si");
             noeli4.setVisible(true);
             Mensaje4.setText("Cancelar la venta ?");
             tgm=1;
   }else if(this.tb_CPT.getRowCount()!=0  ){
    
             Anular.setVisible(true);
             eli4.setText("Si");
             noeli4.setVisible(true);
            lblcod.setText(lblcodigo.getText()); 
   }   
    }//GEN-LAST:event_btneliminarActionPerformed

    private void noeli4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli4ActionPerformed

        if(tge==4){
            panelMensaje.setVisible(false);
        }
    }//GEN-LAST:event_noeli4ActionPerformed

    private void eli4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli4ActionPerformed
        if (tgm==1 ){
            try{
                Caja_NuevaVenta ELIM = new Caja_NuevaVenta();
                ELIM.setId_documento(lblcodigo.getText());
                if(ELIM.eliminar()){
                    System.out.println("ELIMINADO CABECERA");
                }

            }catch(Exception e){
                System.out.println("Error Eliminar" + e.toString());
            }

            panelMensaje.setVisible(false);
            NuevaV();
            LISTAR();
            LISTARN();
            formatoj();
            abono.setVisible(false);
        }

//        if(tga==0 && abonos.getText()=="Si"){
//            Cliente.setText(String.valueOf(ABONOS.getValueAt(0, 5)));
//            adni.setText(String.valueOf(ABONOS.getValueAt(0, 1)));
//            sumaAbono();
//            abono.setVisible(true);
//            abono.setLocationRelativeTo(null);//en el centro
//            abono.getContentPane().setBackground(Color.WHITE);
//        }
//        if(tga==1 && abonos.getText()=="No"){
//            abono.setVisible(false);
//        }
        if(tgDetalle==0){
            panelMensaje.setVisible(false);
            
        }

    }//GEN-LAST:event_eli4ActionPerformed

    private void panelPreventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelPreventaKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            preventas.setVisible(true);
            PreventaEME();

            btnbuscar4.setText(String.valueOf(tEME.getRowCount()));
            btnbuscar4.setVisible(true);

        }
    }//GEN-LAST:event_panelPreventaKeyPressed

    private void panelPreventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPreventaMouseClicked
        preventas.setVisible(true);
        
        
        Caja_Preventa CPCEX= new Caja_Preventa();
        CPCEX.ListarPreventasCEX(lblhc.getText(),tb_CEX);
        PreventaHOS();
        PreventaEME();
        

        btnbuscar4.setText(String.valueOf(tEME.getRowCount()));
        btnbuscar4.setVisible(true);

        btnbuscar6.setText(String.valueOf(tHOS.getRowCount()));
        btnbuscar6.setVisible(true);

    }//GEN-LAST:event_panelPreventaMouseClicked

    private void btnbuscar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar5ActionPerformed

    private void tb_CPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTKeyPressed

    }//GEN-LAST:event_tb_CPTKeyPressed

    private void tb_CPTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTMousePressed

    }//GEN-LAST:event_tb_CPTMousePressed

    private void tb_CPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTMouseClicked

        int fila=tb_CPT.getSelectedRow();
        if(evt.getClickCount()==1){

            int filaseleccionada;
            try{

                filaseleccionada= tb_CPT.getSelectedRow();
                if (filaseleccionada!=-1){
                    panelEliminacion.setVisible(true);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            //            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));

        }
    }//GEN-LAST:event_tb_CPTMouseClicked

    private void cbxTipoDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyTyped

    }//GEN-LAST:event_cbxTipoDocumentoKeyTyped

    private void cbxTipoDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            lDoc.setVisible(true);
            lblNumeroDoc.setVisible(true);
            lForma.setVisible(true);
            panelFormaPago.setVisible(true);
        }

    }//GEN-LAST:event_cbxTipoDocumentoKeyPressed

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
            lDoc.setVisible(true);
            lblNumeroDoc.setVisible(true);
            lForma.setVisible(true);
            panelFormaPago.setVisible(true);
            
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        jTabbedPane5.setSelectedIndex(2);
        jPanel43.setBackground(new Color(240,240,240)); 
        jPanel41.setBackground(new Color(0,153,255));
        jPanel25.setBackground(new Color(0,153,255));

    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel41KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel41KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel41KeyPressed

    private void txtFormaPagoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFormaPagoCaretUpdate
    
    }//GEN-LAST:event_txtFormaPagoCaretUpdate

    private void btnBuscarFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFormaPagoActionPerformed
        Jerarquias.setVisible(true);
        txtBuscar3.setText("");
    }//GEN-LAST:event_btnBuscarFormaPagoActionPerformed

    private void txtCPTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPTCaretUpdate
    
    }//GEN-LAST:event_txtCPTCaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
//         char teclaPresionada = evt.getKeyChar();
//        if(teclaPresionada==KeyEvent.VK_ENTER){
            nomenclaturas.setVisible(true);
            txtBuscar2.setText("");
//        }
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

    private void btnEliminarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDetalleActionPerformed
       panelEliminarDetalle.setVisible(true);
    }//GEN-LAST:event_btnEliminarDetalleActionPerformed

    private void eli5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli5ActionPerformed
  

    }//GEN-LAST:event_eli5ActionPerformed

    private void noeli5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli5ActionPerformed
       panelEliminacion.setVisible(false);
    }//GEN-LAST:event_noeli5ActionPerformed

    private void btnbuscar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar9ActionPerformed

    private void panelAnularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAnularMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnularMouseClicked

    private void panelAnularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelAnularKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnularKeyPressed

    private void eli6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli6ActionPerformed
        lblCPT.setVisible(false);
        panelCPT.setVisible(false);
        jScrollPane4.setVisible(false);
        lForma.setVisible(false);
        panelFormaPago.setVisible(false);
        lDoc.setVisible(false);
        lblNumeroDoc.setVisible(false);
        lTipoDoc.setVisible(false);
        cbxTipoDocumento.setVisible(false);
        panelAnular.setVisible(false);
        NuevaV();
    }//GEN-LAST:event_eli6ActionPerformed

    private void noeli6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli6ActionPerformed
        Anular.setVisible(true);
    }//GEN-LAST:event_noeli6ActionPerformed

    private void tb_CEXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CEXMouseClicked
       Caja_Preventa cpam = new Caja_Preventa(); 
       int fila=tb_CEX.getSelectedRow();
       if(evt.getClickCount()==1){
       NCP.setText(String.valueOf(tb_CEX.getValueAt(fila, 9)));  
       lblAREA.setText(String.valueOf(tb_CEX.getValueAt(fila, 11))); 
       lblIdPreventas.setText(String.valueOf(tb_CEX.getValueAt(fila, 0))); 
       //////////////////////ANULAR
       MHOS.setText(String.valueOf(tb_CEX.getValueAt(fila, 4))); 
       ////////////////////////////////////////////////////////
      
     } 
    }//GEN-LAST:event_tb_CEXMouseClicked

    private void tb_CEXMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CEXMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_CEXMouseEntered

    private void tb_CEXKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CEXKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_CEXKeyPressed

    private void btnCargarCEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCEXActionPerformed

        Caja_Preventa CSE = new Caja_Preventa();
        SE.setText(CSE.CodSE(lblAREA.getText()));
        GuardarDetalle();
        ModificarPreventaCEX();
         
        panelNumeros.setVisible(true);
        suma();
        preventas.dispose();
    }//GEN-LAST:event_btnCargarCEXActionPerformed

    private void btnEliminarCEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCEXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarCEXActionPerformed

    private void eli7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eli7ActionPerformed

    private void noeli7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noeli7ActionPerformed

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
    private javax.swing.JTable ABONOS;
    private javax.swing.JLabel ACTM;
    private javax.swing.JLabel AMA;
    private javax.swing.JLabel AMN;
    private javax.swing.JLabel APENOM;
    private javax.swing.JDialog Anular;
    private javax.swing.JDialog BHC;
    private javax.swing.JTextField BMedicos;
    private javax.swing.JComboBox CBXANULAR;
    private javax.swing.JLabel Cliente;
    private javax.swing.JLabel DNII;
    private javax.swing.JLabel HCI;
    private javax.swing.JDialog Jerarquias;
    private javax.swing.JLabel M;
    private javax.swing.JLabel MHOS;
    private javax.swing.JLabel MODULO;
    private javax.swing.JDialog Medicos;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JLabel Mensaje1;
    private javax.swing.JLabel Mensaje2;
    private javax.swing.JLabel Mensaje3;
    private javax.swing.JLabel Mensaje4;
    private javax.swing.JLabel Mensaje5;
    private javax.swing.JLabel Mensaje6;
    private javax.swing.JLabel NCP;
    private javax.swing.JLabel SE;
    private javax.swing.JLabel T3;
    private javax.swing.JLabel T4;
    private javax.swing.JLabel T5;
    private javax.swing.JLabel T8;
    private javax.swing.JDialog abono;
    private javax.swing.JLabel abonod;
    private javax.swing.JLabel abonos;
    private javax.swing.JLabel adni;
    private javax.swing.JLabel adni1;
    private javax.swing.JTable anulacion;
    private javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarFormaPago;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnCargarCEX;
    private javax.swing.JButton btnCargarEME;
    private javax.swing.JButton btnCargarHOS;
    private javax.swing.JButton btnEliminarCEX;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnEliminarEME;
    private javax.swing.JButton btnEliminarHOS;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btnbuscar4;
    private javax.swing.JButton btnbuscar5;
    private javax.swing.JButton btnbuscar6;
    private javax.swing.JButton btnbuscar7;
    private javax.swing.JButton btnbuscar8;
    private javax.swing.JButton btnbuscar9;
    public static javax.swing.JButton btneliminar;
    public static javax.swing.JButton btnguardar;
    private javax.swing.JLabel bus;
    private javax.swing.JLabel bus1;
    private javax.swing.JLabel bus3;
    private javax.swing.JLabel ca_id;
    private javax.swing.JPanel cargareliminar1;
    private javax.swing.JPanel cargareliminarma;
    private javax.swing.JComboBox cbxTipoDocumento;
    private javax.swing.JLabel codelim;
    private javax.swing.JButton eli;
    private javax.swing.JButton eli1;
    private javax.swing.JButton eli2;
    private javax.swing.JButton eli3;
    private javax.swing.JButton eli4;
    private javax.swing.JButton eli5;
    private javax.swing.JButton eli6;
    private javax.swing.JButton eli7;
    private javax.swing.JLabel elimdp;
    private javax.swing.JButton elimma;
    private javax.swing.JButton elimma1;
    private javax.swing.JLabel formap;
    private javax.swing.JLabel fp;
    private javax.swing.JLabel fua;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JLabel lDoc;
    private javax.swing.JLabel lForma;
    private javax.swing.JLabel lTipoDoc;
    private javax.swing.JLabel lblAREA;
    public static javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblCPT;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblHC;
    private javax.swing.JLabel lblIdMedico;
    public static javax.swing.JLabel lblIdPreventa;
    private javax.swing.JLabel lblIdPreventas;
    private javax.swing.JLabel lblNumeroDoc;
    private javax.swing.JLabel lblcod;
    private javax.swing.JLabel lblcodanu;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JLabel lbldetalle;
    private javax.swing.JLabel lblhc;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JButton noeli1;
    private javax.swing.JButton noeli2;
    private javax.swing.JButton noeli3;
    private javax.swing.JButton noeli4;
    private javax.swing.JButton noeli5;
    private javax.swing.JButton noeli6;
    private javax.swing.JButton noeli7;
    private javax.swing.JLabel nom;
    private javax.swing.JDialog nomenclaturas;
    private javax.swing.JPanel nuevoanulacion;
    private javax.swing.JLabel ok;
    private javax.swing.JPanel panelAbonos;
    private javax.swing.JPanel panelActoMedico;
    private javax.swing.JPanel panelAnular;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelDatosGenerales;
    private javax.swing.JPanel panelEliminacion;
    private javax.swing.JPanel panelEliminarCEX;
    private javax.swing.JPanel panelEliminarDetalle;
    private javax.swing.JPanel panelEliminarEME;
    private javax.swing.JPanel panelEliminarHOS;
    private javax.swing.JPanel panelFormaPago;
    private javax.swing.JPanel panelMensaje;
    private javax.swing.JPanel panelNumeros;
    private javax.swing.JPanel panelPreventa;
    private javax.swing.JDialog preventas;
    private javax.swing.JPanel resumen;
    private javax.swing.JPanel sep1;
    private javax.swing.JTextField sexo;
    private javax.swing.JLabel sinanular;
    private javax.swing.JTable tEME;
    private javax.swing.JTable tEMEDET;
    private javax.swing.JTable tHOS;
    private javax.swing.JTable tHOSDET;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTable tb_CEX;
    private javax.swing.JTable tb_CPT;
    private javax.swing.JTable tb_CPTBUSCAR;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo3;
    private javax.swing.JTable tb_Grupo6;
    private javax.swing.JTable tb_medicos;
    private javax.swing.JTable tbpreventas;
    private javax.swing.JTextField total;
    private javax.swing.JTextField total1;
    private javax.swing.JTextField total2;
    private javax.swing.JTextField total3;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtBuscar3;
    public static javax.swing.JTextField txtBuscar4;
    private javax.swing.JTextField txtBuscarPaciente;
    public static javax.swing.JTextField txtCPT;
    public static javax.swing.JTextField txtFormaPago;
    private javax.swing.JTextField txtanular;
    private javax.swing.JLabel txtape;
    private javax.swing.JTextField txtedad;
    private javax.swing.JLabel txthc;
    // End of variables declaration//GEN-END:variables
}
