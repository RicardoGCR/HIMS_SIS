/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
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
import java.awt.event.KeyEvent;
import javax.swing.table.JTableHeader;
import tablas.FormatoTablaCajaConsultorio;
import tablas.FormatoTablaReporteDiarioCaja;
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
         Empresa.setLocationRelativeTo(null);//en el centro
         Empresa.getContentPane().setBackground(Color.WHITE);
         MedicosConsultorios.setLocationRelativeTo(null);//en el centro
         MedicosConsultorios.getContentPane().setBackground(Color.WHITE);
         
         AsistenciaSocial.setLocationRelativeTo(null);//en el centro
         AsistenciaSocial.getContentPane().setBackground(Color.WHITE);
         
         Medicos.setLocationRelativeTo(null);//en el centro
         Medicos.getContentPane().setBackground(Color.WHITE);
         
         
         Anular.setLocationRelativeTo(null);//en el centro
         Anular.getContentPane().setBackground(Color.WHITE);

         panelMensaje.setVisible(false);
         panelCPT.setVisible(false);
         ///////////////////
         //txtfp.addKeyListener(manejador);
         //////////////////
         
         ///NO MOSTRAR
         //////////////////////////
          //preventa.setVisible(false);

          jTabbedPane1.setEnabledAt(0,false);
          jTabbedPane1.setEnabledAt(1, false);
          jTabbedPane1.setEnabledAt(2, false);
         
          panelAbonos.setVisible(false);

//          b4.setVisible(false);
          nuevoanulacion.setVisible(false);
          panelPreventa.setVisible(false);
          resumen.setVisible(false);
          jScrollPane3.setVisible(false);
          tb_BusquedaCabecera.setVisible(false);
          jScrollPane7.setVisible(false);
          tb_ReporteDetalle.setVisible(false);
          
          panelTablaCPT.setVisible(false);
           
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
        jPanel41.setBackground(new Color(41,127,184));
        jPanel43.setBackground(new Color(41,127,184));
        
        lblCPT.setVisible(false);
        panelCPT.setVisible(false);
        panelTablaCPT.setVisible(false);
         
        
        lForma.setVisible(false);
        panelFormaPago.setVisible(false);
        lDoc.setVisible(false);
        lblNumeroDoc.setVisible(false);
        lTipoDoc.setVisible(false);
        cbxTipoDocumento.setVisible(false);
        panelAnular.setVisible(false);
        panelDatosGenerales.setVisible(false);
        panelActoMedico.setVisible(false);
        panelEliminar.setVisible(false);
        
        jScrollPane8.setBackground(new Color(255,255,255));
        jScrollPane4.setBackground(new Color(255,255,255));
        tb_Asistente.setBackground(new Color(255,255,255));
        tb_CPT.setBackground(new Color(255,255,255));
        
        tb_Asistente.getTableHeader().setVisible(false);
        tb_CPT.getTableHeader().setVisible(false);
        tb_Asistente.setTableHeader(null);
        
        tb_CPT.setTableHeader(null);
        
        tb_CPTBUSCAR.setTableHeader(null);
        tbPacientes.setTableHeader(null);
        tb_medicos1.setTableHeader(null); 
        tb_consultorios.setTableHeader(null);
        tb_Empresa.setTableHeader(null);
        

        panelAsignacion.setVisible(false);
        panelDeatelleC.setVisible(false);
        btnReservar.setVisible(false);
        panelSinConsultas.setVisible(false);
        cbxTipoDocumento.setBackground(Color.WHITE);
        panelExoneracion.setVisible(false);
        panelPorcentajes.setVisible(false);
        jPanel1.setVisible(false);
//        panelTurnos.setVisible(false);
        
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                if(lblOk.getText()=="okk" && tb_CPT.getRowCount()==0){ 
                    panelEliminar.setVisible(true);   
                    panelMensaje.setVisible(false);  
                    tgnuevoEliminar=1;
                    txtEnterEscapeEnter1.requestFocus();
                    
                }  
  
                if(lblOk.getText()=="okk" && tb_CPT.getRowCount()!=0  ){
                    panelAnular.setVisible(true);
                    panelMensaje.setVisible(false);  
                    txtEnterEscapeEnter.requestFocus();
                   
                }
                if(lblOk.getText()=="ok" && tb_CPT.getRowCount()==0){ 
                   dispose();
                }
            }
        });
        //////////////////////////////
               
         LISTAR();
         formatoj();
         cnn.ListarAsistentaSocial(tb_Asistente);
         if (txtBuscarPaciente.getText() == " "){
             panelBuscarHC.setVisible(true);
             paneltablaHC.setVisible(false);
             panelSinHC.setVisible(false);
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
            cmd.setString(1, txtHC.getText());
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
            cmd.setString(1, txtHC.getText());
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
            cmd.setString(1, txtBuscarFormaPago.getText());
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
             String titulos[]={"Nº H.C.","DNI","Paciente","Direccion","Sexo","Fecha","Edad",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec CAJA_BUSCAR_HISTORIAS ?";      
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
    tbPacientes.getColumnModel().getColumn(0).setPreferredWidth(150);
    tbPacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
    tbPacientes.getColumnModel().getColumn(2).setPreferredWidth(700);
    tbPacientes.getColumnModel().getColumn(3).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(3).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(4).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(4).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(6).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(6).setMaxWidth(0);

    tbPacientes.getColumnModel().getColumn(5).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(5).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(7).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(7).setMaxWidth(0);
    tbPacientes.setRowHeight(38);
    }
    
    public void formatoj(){
    tb_Grupo3.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Grupo3.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Grupo3.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo3.getColumnModel().getColumn(2).setPreferredWidth(500);
    tb_Grupo3.setRowHeight(30);

    }
   

    
    
    public void formatoA(){
    anulacion.getColumnModel().getColumn(0).setPreferredWidth(220);
    anulacion.getColumnModel().getColumn(1).setMinWidth(0);
    anulacion.getColumnModel().getColumn(1).setMaxWidth(0);
    }
     
     public void ActualizarVenta(){
  
        Caja_NuevaVenta CNVVAC = new Caja_NuevaVenta();
        CNVVAC.setId_documento(lblcodigo.getText());
        CNVVAC.setDESCUENTO(Double.parseDouble(txtDescuento.getText()));
        CNVVAC.setTOTAL_DOCUUMENTO(Double.parseDouble(txtTotal.getText()));
        CNVVAC.setUsu_Exoneracion(lblUsuPorcentaje.getText());
        CNVVAC.setPorcentaje_Exoneracion(lblPorcentaje.getText());

        if(CNVVAC.ActualizarVenta()==true){
                   System.out.println("CABECERA ACTUALIZADA");

        } else {

                System.out.println("ERROR ACTUALIZAR CAMA");

        }
                       
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
                cno1.setCod_jerar_forma_pago(lblFP.getText());
                cno1.setId_hc(lblHc.getText());
                cno1.setCod_motiv_anu(lblSinAnulacion.getText());
                cno1.setSerie_documento(lblSerie.getText());/////////falta
                cno1.setNum_documento(lblNumDoc.getText());
                cno1.setDependencia("CAJA CONTADO");//
                
                if (txtFormaPago.getText().equals("CONTADO")){
                    cno1.setESTADOP("C");
                     } else if(!txtFormaPago.getText().equals("CONTADO")){
                    cno1.setESTADOP("P");  
                }
                
                cno1.setCod_usu(cno1.codUsuario(lblusu.getText()));
                cno1.setId_liquidacion(" ");/////////falta
                cno1.setCorrelativo(2);
                cno1.setId_ActoMedico(Integer.parseInt(lblActoMedico.getText() ));
                cno1.setId_Cta_Abono(0);
                cno1.setCod_empre_jerar(lblId_EmpresaFP.getText());
                cno1.setUsu_Exoneracion(lblUsuPorcentaje.getText());
                cno1.setPorcentaje_Exoneracion(lblPorcentaje.getText());
                
                    if(cno1.Nuevo()==true){
                           System.out.println("Guardado cabecera");
                           tg=2;
                           btnguardar.setEnabled(false);
                           cbxTipoDocumento.setEnabled(false);
                           btnBuscarFormaPago.setEnabled(false);
                           lblActoMedicoNuevo.setText(lblActoMedico.getText());
                           if ( lblPreventa.getText().equals("SI")){
                               panelPreventa.setVisible(true);
                               panelPreventa.requestFocus();
                           }else if ( lblPreventa.getText().equals("NO")){
                                panelPreventa.setVisible(false);
                                lblCPT.setVisible(true);
                                panelCPT.setVisible(true);
                        
                                panelTablaCPT.setVisible(true);

                                 
                               
                           }
  
                       } else {
                             System.out.println("erorr cabecera");
                       }     
 }
    public void GuardarDetalle(){
        
        if(!lblVisAdmi.getText().equals("N")&&lblMant.getText().equals("H")&&(!lblContador.getText().equals(lblNumeroTotal.getText()))){  //&&(Integer.parseInt(lblNumero.getText())<=0)
                                ///////////////////////////CITAS
                                    Caja_NuevaVenta CNVAC = new Caja_NuevaVenta();
                                    CNVAC.setCA_ID(Integer.parseInt(lblCAid.getText()));
                                    int c=0,d=0;
                                    c=Integer.parseInt(lblContador.getText());
                                    d=c+1;
                                    CNVAC.setCONTADOR_CITAS(d); 
                                    CNVAC.setCITAS_CAJA(Integer.parseInt(lblNumero.getText())-1); 
                                    if(CNVAC.ActualizarCitas()==true){
                                      System.out.println("Actualizado N Cita PARA HOY");  

                                    }else {
                                        System.out.println("Error actualizar N CITAS HOY");  
                                    }
                                
                            }else if(!lblVisAdmi.getText().equals("N")&&lblMant.getText().equals("M")&&(!lblContadorF.getText().equals(lblNumeroFT.getText()))){//&&(Integer.parseInt(lblNumeroFuturo.getText())<=0)
                                ///////////////////////////CITAS
                                    Caja_NuevaVenta CNVACF = new Caja_NuevaVenta();
                                    CNVACF.setCA_ID(Integer.parseInt(lblCAid.getText()));
                                    int c=0,d=0;
                                    c=Integer.parseInt(lblContadorF.getText());
                                    d=c+1;
                                    CNVACF.setCONTADOR_CITAS(d); 
                                    
                   
                                       CNVACF.setCITAS_CAJA(Integer.parseInt(lblNumeroFuturo.getText())-1);   
                                    
                                   
                                    if(CNVACF.ActualizarCitasFuturas()==true){
                                      System.out.println("Actualizado N Cita A FUTURO");  

                                    }else {
                                        System.out.println("Error actualizar N CITAS A FUTURO");  
                                    }
                                
                            }else
                if(!lblVisAdmi.getText().equals("N")&&lblMant.getText().equals("H")&&(!lblContador.getText().equals(lblNumeroTotal.getText()))){ //&&(Integer.parseInt(lblNumero.getText())<=0)
                                   Caja_NuevaVenta CNVACA = new Caja_NuevaVenta();
                                    CNVACA.setCA_ID(Integer.parseInt(lblCAid.getText()));
                                    int h=0,i=0;
                                    h=Integer.parseInt(lblContadorA.getText());
                                    i=h+1;
                                    CNVACA.setCONTADOR_CITAS(i); 
                                    CNVACA.setCITAS_CAJA(Integer.parseInt(lblNumerosAdicional.getText())-1); 
                                    if(CNVACA.ActualizarCitasAdicionales()==true){
                                      System.out.println("Actualizado N Cita ADICIONALES");  

                                    }else {
                                        System.out.println("Error actualizar N CITAS ADICIONALES");  
                                    } 
                }
        

                Caja_Documento_Detalle cnvd = new Caja_Documento_Detalle();
                Caja_Documento_Detalle cnvd1 = new Caja_Documento_Detalle();
               // cnvd.setId_cod_doc_det(Integer.parseInt(CodDet.getText() ));
                cnvd.setId_documento(lblcodigo.getText());     
                cnvd.setCod_precio(cnvd.CodPrecio(lblCajaNomenclaturaPrecio.getText()));
                cnvd.setNom_consultorio_citas(lblServicioArea.getText());
                cnvd.setCantidad_detalle(Integer.parseInt("1"));
                cnvd.setPrecio_detalle(Double.parseDouble(cnvd.CodPrecio1(lblCajaNomenclaturaPrecio.getText())));
                cnvd.setTotal_detalle(Double.parseDouble(cnvd.CodPrecio1(lblCajaNomenclaturaPrecio.getText())));
                cnvd.setDescu_exo_detalle(Double.parseDouble("0"));
                cnvd.setPersonal_aten(lblIdMedico.getText()); 
                if(!lblVisAdmi.getText().equals("N")&&lblMant.getText().equals("H")&&(!lblContador.getText().equals(lblNumeroTotal.getText()))){
                    int a=0,b=0;
                    a=Integer.parseInt(lblContador.getText());
                    b=a+1; 
                    cnvd.setNum_aten(String.valueOf(b));
                }else  if(!lblVisAdmi.getText().equals("N")&&lblMant.getText().equals("M")){
                    int e=0,f=0;
                    e=Integer.parseInt(lblContadorF.getText());
                    f=e+1; 
                    cnvd.setNum_aten(String.valueOf(f));
                }else if(!lblVisAdmi.getText().equals("N")&&lblMant.getText().equals("H")&&(lblContador.getText().equals(lblNumeroTotal.getText()))){
                    int p=0,q=0;
                    p=Integer.parseInt(lblContadorA.getText());
                    q=p+1; 
                    cnvd.setNum_aten(String.valueOf(q)); 
                }

                cnvd.setTurno_cita("Mañana");
                cnvd.setCod_usu(cnvd.codUsuario(lblusu.getText()));
                
                
                    if(cnvd.DetalleVenta()==true){
                           
                               
                                cnvd1.Detalle(lblcodigo.getText(),tb_CPT);
                           /////////////////////////////////////////////////////
                           
                           /////////////////////////////////////////////////////
                           PreventaHOSDET();
                           panelMensaje.setVisible(true);   
                           panelMensaje.setBackground(new Color(0,153,102)); 
                           Mensaje4.setText("Datos Guardados de forma correcta");
                           btnCorrectoSi.setVisible(true);
                           btnCorrectoSi.setText("OK");
                           btnCorrectoNo.setVisible(false);
                           
                           btnCorrectoSi.requestFocus();
                           
                            lblCPT.setVisible(true);
                            panelCPT.setVisible(true);
                            
                            panelTablaCPT.setVisible(true);

                             
                         
                           
                          
                           System.out.println("Guardado detalle");
                           tgDetalle=0;
                           Caja_Documento_Detalle IDHC = new Caja_Documento_Detalle();
                           IDHC.DetalleID(lblcodigo.getText());
                           
                           if(!lblVisAdmi.getText().equals("N")){
                               Caja_Documento_Detalle MHC = new Caja_Documento_Detalle();
                               MHC.setCod_usu(MHC.codUsuario(lblusu.getText()));
                               MHC.setId_documento(lblIdDetalle.getText()); 
                                    if(MHC.MovimientoHC()==true){
                                      System.out.println("Guardado Movimiento HC");  

                                    }else {
                                        System.out.println("Error guaradar Movimiento HC");  
                                    }  
                            }

                            panelTablaCPT.setVisible(true);

                            btnguardar.setEnabled(true);

                       } else {
                                panelMensaje.setVisible(true);
                                panelMensaje.setBackground(new Color(255,91,70)); 
                                Mensaje4.setText("Ocurrió un error verifique");
                                btnCorrectoSi.setVisible(false);
                                btnCorrectoNo.setVisible(false);
                             System.out.println("erorr detalle");
                       }     
 }
    
        public void GuardarSIS(){
        
                Caja_SIS_Cabecera cnoS = new Caja_SIS_Cabecera();
//                cnoS.setID_FUA(fua.getText());
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
                        btnCorrectoNo.setText("Ok");
                        btnCorrectoSi.setVisible(false);
                        btnCorrectoNo.setVisible(true); 
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
                        cnop.setCod_jerar_forma_pago(lblFP.getText());
                        cnop.setCod_medico(lblIdMedico.getText());
                        
                        if(cnop.modificarPreventaCEX()==true){
                                   System.out.println("PreventaModificada");
                                   panelPreventa.setVisible(false);
                        } else {
                           
                                panelEliminarEME.setVisible(true);
                                panelEliminarEME.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                System.out.println("error modificar preventa");
                         
                        }
                       
    }
     
     public void ModificarPreventa(){

                        Caja_Preventa cnop = new Caja_Preventa();
                        cnop.setId_preventa(Integer.parseInt(lblIdPreventas.getText()));
                        cnop.setACTO_MEDICO(Integer.parseInt(lblActoMedico.getText()));  
                        cnop.setCod_jerar_forma_pago(lblFP.getText());
                        cnop.setCod_medico(lblMedicoId.getText());
                        
                        if(cnop.modificarPreventa()==true){
                                   System.out.println("PreventaModificada ");
                                   panelPreventa.setVisible(false);
                        } else {
                           
                                panelEliminarEME.setVisible(true);
                                panelEliminarEME.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique  ");
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

        DefaultTableModel modelo1 = (DefaultTableModel)tb_CPT.getModel(); 
        int b=tb_CPT.getRowCount();
        for(int j=0;j<b;j++){
                    modelo1.removeRow(0);
        }
        

        
        lblcodigo.setText(cnn.id());
        lblSinAnulacion.setText(cnn.sinanulacion());
        lblActoMedico.setText(cnn.ActoMedico());
        cnn.Caja_Correlativo();
        tg=1;
    
        btnguardar.setEnabled(true);
        btneliminar.setEnabled(false);
        cbxTipoDocumento.setEnabled(true);

        btnBuscarFormaPago.setEnabled(true);
        btnBuscarCPT.setEnabled(true);
        txtHC.setEnabled(true);
        txtCPT.setEnabled(true);
        txtCPT.setEditable(false);
        txtFormaPago.setEnabled(true);
        txtFormaPago.setEditable(false);
        txtTotal.setEnabled(true);
        txtTotal.setEditable(false);
    
     
        jTabbedPane1.setSelectedIndex(1); 
        
       

        
                 ///NO MOSTRAR
         //////////////////////////
          //preventa.setVisible(false);
//          b4.setVisible(false);
          nuevoanulacion.setVisible(false);
          panelPreventa.setVisible(false);
          
          panelTablaCPT.setVisible(false);
           
          
          panelEliminacion.setVisible(false);
          panelNumeros.setVisible(false);
          btnNuevo.setEnabled(true);
        
          btneliminar.setEnabled(false);
         
          btnCargarEME.setVisible(false);
          btnEliminarEME.setVisible(false);
          
          


          txtFormaPago.setText("");
          txtHC.setText("");
  
          lblDireccion.setText("");
          lblDNI.setText("");
          txtedad.setText("");
          sexo.setText("");
          txtape.setText("Paciente");

          lblHC.setText("");
           panelPreventa.setVisible(false);
           BHC.setVisible(true);
           txtBuscarPaciente.setText("");
          
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
        bus3 = new javax.swing.JLabel();
        btnBuscarPaciente2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        ABONOS = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            lblIdPreventa = new javax.swing.JLabel();
            jScrollPane9 = new javax.swing.JScrollPane();
            tbpreventas = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                panelBuscarHC = new javax.swing.JPanel();
                jLabel9 = new javax.swing.JLabel();
                paneltablaHC = new javax.swing.JPanel();
                jScrollPane2 = new javax.swing.JScrollPane();
                tbPacientes = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jPanel35 = new javax.swing.JPanel();
                    jLabel40 = new javax.swing.JLabel();
                    jLabel47 = new javax.swing.JLabel();
                    jLabel50 = new javax.swing.JLabel();
                    panelSinHC = new javax.swing.JPanel();
                    jLabel16 = new javax.swing.JLabel();
                    jLabel17 = new javax.swing.JLabel();
                    jPanel9 = new javax.swing.JPanel();
                    btnNuevo1 = new javax.swing.JButton();
                    jLabel18 = new javax.swing.JLabel();
                    Jerarquias = new javax.swing.JDialog();
                    jPanel10 = new javax.swing.JPanel();
                    jLabel20 = new javax.swing.JLabel();
                    jPanel29 = new javax.swing.JPanel();
                    txtBuscarFormaPago = new javax.swing.JTextField();
                    btnBuscarPaciente4 = new javax.swing.JButton();
                    jScrollPane5 = new javax.swing.JScrollPane();
                    tb_Grupo3 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        nomenclaturas = new javax.swing.JDialog();
                        jPanel11 = new javax.swing.JPanel();
                        jLabel21 = new javax.swing.JLabel();
                        jPanel28 = new javax.swing.JPanel();
                        txtBuscarCPT = new javax.swing.JTextField();
                        btnBuscarPaciente3 = new javax.swing.JButton();
                        panelBuscar = new javax.swing.JPanel();
                        jLabel22 = new javax.swing.JLabel();
                        panelCargarCPT = new javax.swing.JPanel();
                        jScrollPane6 = new javax.swing.JScrollPane();
                        tb_CPTBUSCAR = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jLabel3 = new javax.swing.JLabel();
                            jLabel8 = new javax.swing.JLabel();
                            jLabel10 = new javax.swing.JLabel();
                            jPanel33 = new javax.swing.JPanel();
                            panelSinCPT = new javax.swing.JPanel();
                            jLabel23 = new javax.swing.JLabel();
                            jLabel24 = new javax.swing.JLabel();
                            jPanel16 = new javax.swing.JPanel();
                            btnNuevo2 = new javax.swing.JButton();
                            jLabel25 = new javax.swing.JLabel();
                            preventas = new javax.swing.JDialog();
                            jPanel18 = new javax.swing.JPanel();
                            jLabel29 = new javax.swing.JLabel();
                            jLabel34 = new javax.swing.JLabel();
                            elimdp = new javax.swing.JLabel();
                            jPanel24 = new javax.swing.JPanel();
                            jLabel35 = new javax.swing.JLabel();
                            jPanel25 = new javax.swing.JPanel();
                            btnbuscar4 = new javax.swing.JButton();
                            jPanel31 = new javax.swing.JPanel();
                            jPanel41 = new javax.swing.JPanel();
                            jLabel37 = new javax.swing.JLabel();
                            btnbuscar6 = new javax.swing.JButton();
                            jPanel42 = new javax.swing.JPanel();
                            jPanel43 = new javax.swing.JPanel();
                            jLabel41 = new javax.swing.JLabel();
                            btnbuscar10 = new javax.swing.JButton();
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
                                                    Empresa = new javax.swing.JDialog();
                                                    jPanel46 = new javax.swing.JPanel();
                                                    jLabel62 = new javax.swing.JLabel();
                                                    jPanel47 = new javax.swing.JPanel();
                                                    txtBuscarEmpresa = new javax.swing.JTextField();
                                                    jLabel56 = new javax.swing.JLabel();
                                                    btnBuscarPaciente5 = new javax.swing.JButton();
                                                    jScrollPane16 = new javax.swing.JScrollPane();
                                                    tb_Empresa = new javax.swing.JTable(){
                                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                                            return false; //Disallow the editing of any cell
                                                        }};
                                                        jPanel51 = new javax.swing.JPanel();
                                                        jLabel65 = new javax.swing.JLabel();
                                                        jLabel66 = new javax.swing.JLabel();
                                                        jPanel12 = new javax.swing.JPanel();
                                                        jLabel78 = new javax.swing.JLabel();
                                                        jPanel45 = new javax.swing.JPanel();
                                                        jLabel79 = new javax.swing.JLabel();
                                                        jLabel80 = new javax.swing.JLabel();
                                                        jLabel81 = new javax.swing.JLabel();
                                                        jLabel82 = new javax.swing.JLabel();
                                                        jLabel83 = new javax.swing.JLabel();
                                                        AsistenciaSocial = new javax.swing.JDialog();
                                                        jPanel20 = new javax.swing.JPanel();
                                                        jPanel32 = new javax.swing.JPanel();
                                                        jLabel43 = new javax.swing.JLabel();
                                                        jLabel1 = new javax.swing.JLabel();
                                                        jScrollPane8 = new javax.swing.JScrollPane();
                                                        tb_Asistente = new javax.swing.JTable(){
                                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                                return false; //Disallow the editing of any cell
                                                            }};
                                                            jPanel44 = new javax.swing.JPanel();
                                                            panelExoneracion = new javax.swing.JPanel();
                                                            btnNuevo3 = new javax.swing.JButton();
                                                            panelPorcentajes = new javax.swing.JPanel();
                                                            jLabel7 = new javax.swing.JLabel();
                                                            txtT2 = new javax.swing.JTextField();
                                                            jLabel4 = new javax.swing.JLabel();
                                                            jLabel6 = new javax.swing.JLabel();
                                                            txtT3 = new javax.swing.JTextField();
                                                            jLabel5 = new javax.swing.JLabel();
                                                            txtT4 = new javax.swing.JTextField();
                                                            jLabel2 = new javax.swing.JLabel();
                                                            txtT1 = new javax.swing.JTextField();
                                                            MedicosConsultorios = new javax.swing.JDialog();
                                                            jPanelCabecera = new javax.swing.JPanel();
                                                            jLabel63 = new javax.swing.JLabel();
                                                            jPanel49 = new javax.swing.JPanel();
                                                            txtBuscarMedicos = new javax.swing.JTextField();
                                                            jLabel58 = new javax.swing.JLabel();
                                                            btnBuscarPaciente1 = new javax.swing.JButton();
                                                            lblMedicoApeNom = new javax.swing.JLabel();
                                                            panelTurnos = new javax.swing.JPanel();
                                                            jScrollPane17 = new javax.swing.JScrollPane();
                                                            tb_medicos1 = new javax.swing.JTable(){
                                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                                    return false; //Disallow the editing of any cell
                                                                }};
                                                                jLabel51 = new javax.swing.JLabel();
                                                                jPanel37 = new javax.swing.JPanel();
                                                                panelDeatelleC = new javax.swing.JPanel();
                                                                jScrollPane18 = new javax.swing.JScrollPane();
                                                                tb_consultorios = new javax.swing.JTable(){
                                                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                                                        return false; //Disallow the editing of any cell
                                                                    }};
                                                                    jPanel38 = new javax.swing.JPanel();
                                                                    jLabel52 = new javax.swing.JLabel();
                                                                    jLabel53 = new javax.swing.JLabel();
                                                                    jLabel54 = new javax.swing.JLabel();
                                                                    jLabel55 = new javax.swing.JLabel();
                                                                    jLabel59 = new javax.swing.JLabel();
                                                                    panelSinMedico = new javax.swing.JPanel();
                                                                    jLabel67 = new javax.swing.JLabel();
                                                                    jLabel68 = new javax.swing.JLabel();
                                                                    jPanel4 = new javax.swing.JPanel();
                                                                    panelAsignacion = new javax.swing.JPanel();
                                                                    jLabel60 = new javax.swing.JLabel();
                                                                    txtT5 = new javax.swing.JTextField();
                                                                    txtT6 = new javax.swing.JTextField();
                                                                    jLabel61 = new javax.swing.JLabel();
                                                                    jLabel64 = new javax.swing.JLabel();
                                                                    btnReservar = new javax.swing.JButton();
                                                                    lblMant = new javax.swing.JLabel();
                                                                    panelSinConsultas = new javax.swing.JPanel();
                                                                    jLabel69 = new javax.swing.JLabel();
                                                                    jLabel70 = new javax.swing.JLabel();
                                                                    lblCitaFutura = new javax.swing.JLabel();
                                                                    Medicos = new javax.swing.JDialog();
                                                                    jPanel48 = new javax.swing.JPanel();
                                                                    jLabel89 = new javax.swing.JLabel();
                                                                    jPanel50 = new javax.swing.JPanel();
                                                                    BMedicos = new javax.swing.JTextField();
                                                                    jLabel90 = new javax.swing.JLabel();
                                                                    btnBuscarPaciente6 = new javax.swing.JButton();
                                                                    jTabbedPane7 = new javax.swing.JTabbedPane();
                                                                    jPanel52 = new javax.swing.JPanel();
                                                                    jScrollPane19 = new javax.swing.JScrollPane();
                                                                    tb_medicos = new javax.swing.JTable(){
                                                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                                                            return false; //Disallow the editing of any cell
                                                                        }};
                                                                        jPanel53 = new javax.swing.JPanel();
                                                                        jLabel91 = new javax.swing.JLabel();
                                                                        jLabel92 = new javax.swing.JLabel();
                                                                        jTabbedPane1 = new javax.swing.JTabbedPane();
                                                                        jPanel2 = new javax.swing.JPanel();
                                                                        jScrollPane3 = new javax.swing.JScrollPane();
                                                                        tb_BusquedaCabecera = new javax.swing.JTable(){
                                                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                                                return false; //Disallow the editing of any cell
                                                                            }};
                                                                            jScrollPane7 = new javax.swing.JScrollPane();
                                                                            tb_ReporteDetalle = new javax.swing.JTable(){
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
                                                                                cbxTipoDocumento = new javax.swing.JComboBox();
                                                                                lblCPT = new javax.swing.JLabel();
                                                                                panelNumeros = new javax.swing.JPanel();
                                                                                jLabel26 = new javax.swing.JLabel();
                                                                                txtTotal = new javax.swing.JTextField();
                                                                                txtDescuento = new javax.swing.JTextField();
                                                                                jLabel27 = new javax.swing.JLabel();
                                                                                txtSubTotal = new javax.swing.JTextField();
                                                                                jLabel28 = new javax.swing.JLabel();
                                                                                txtIGV = new javax.swing.JTextField();
                                                                                jLabel39 = new javax.swing.JLabel();
                                                                                panelPreventa = new javax.swing.JPanel();
                                                                                jLabel15 = new javax.swing.JLabel();
                                                                                btnbuscar5 = new javax.swing.JButton();
                                                                                jLabel36 = new javax.swing.JLabel();
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
                                                                                btnCorrectoSi = new javax.swing.JButton();
                                                                                btnCorrectoNo = new javax.swing.JButton();
                                                                                panelFormaPago = new javax.swing.JPanel();
                                                                                txtFormaPago = new javax.swing.JTextField();
                                                                                btnBuscarFormaPago = new javax.swing.JButton();
                                                                                panelColorFp = new javax.swing.JPanel();
                                                                                lblNumeroDoc = new javax.swing.JLabel();
                                                                                panelActoMedico = new javax.swing.JPanel();
                                                                                jLabel30 = new javax.swing.JLabel();
                                                                                lblActoMedico = new javax.swing.JLabel();
                                                                                lblDescripcion = new javax.swing.JLabel();
                                                                                panelCPT = new javax.swing.JPanel();
                                                                                txtCPT = new javax.swing.JTextField();
                                                                                btnBuscarCPT = new javax.swing.JButton();
                                                                                panelEliminacion = new javax.swing.JPanel();
                                                                                btnEliminarDetalle = new javax.swing.JButton();
                                                                                noeli5 = new javax.swing.JButton();
                                                                                eli5 = new javax.swing.JButton();
                                                                                panelAnular = new javax.swing.JPanel();
                                                                                btnbuscar9 = new javax.swing.JButton();
                                                                                jLabel38 = new javax.swing.JLabel();
                                                                                btnTerminiarVenta = new javax.swing.JButton();
                                                                                jPanel17 = new javax.swing.JPanel();
                                                                                btnAnularVenta = new javax.swing.JButton();
                                                                                txtEnterEscapeEnter = new javax.swing.JTextField();
                                                                                panelEliminar = new javax.swing.JPanel();
                                                                                Mensaje5 = new javax.swing.JLabel();
                                                                                btnEliminarSi = new javax.swing.JButton();
                                                                                btnEliminarNo = new javax.swing.JButton();
                                                                                txtEnterEscapeEnter1 = new javax.swing.JTextField();
                                                                                jPanel1 = new javax.swing.JPanel();
                                                                                lblFua = new javax.swing.JLabel();
                                                                                lblServicio = new javax.swing.JLabel();
                                                                                panelAbonos = new javax.swing.JPanel();
                                                                                lblArea = new javax.swing.JLabel();
                                                                                lblAbonos = new javax.swing.JLabel();
                                                                                lblFormaPago = new javax.swing.JLabel();
                                                                                lblIdPreventas = new javax.swing.JLabel();
                                                                                nom = new javax.swing.JLabel();
                                                                                lblSinAnulacion = new javax.swing.JLabel();
                                                                                lblModuloHos = new javax.swing.JLabel();
                                                                                lblActoMedicoNuevo = new javax.swing.JLabel();
                                                                                lblHc = new javax.swing.JLabel();
                                                                                lblIdMedico = new javax.swing.JLabel();
                                                                                sexo = new javax.swing.JTextField();
                                                                                lblCajaNomenclaturaPrecio = new javax.swing.JLabel();
                                                                                lblFP = new javax.swing.JLabel();
                                                                                lblOk = new javax.swing.JLabel();
                                                                                lblModulo = new javax.swing.JLabel();
                                                                                lblcodigo = new javax.swing.JLabel();
                                                                                txtedad = new javax.swing.JTextField();
                                                                                txtHC = new javax.swing.JLabel();
                                                                                ca_id = new javax.swing.JLabel();
                                                                                AMA = new javax.swing.JLabel();
                                                                                lblVisAdmi = new javax.swing.JLabel();
                                                                                lblIdDetalle = new javax.swing.JLabel();
                                                                                lblPreventa = new javax.swing.JLabel();
                                                                                lblServicioArea = new javax.swing.JLabel();
                                                                                lblNumero = new javax.swing.JLabel();
                                                                                lblNumerosAdicional = new javax.swing.JLabel();
                                                                                lblNumeroFuturo = new javax.swing.JLabel();
                                                                                lblCAid = new javax.swing.JLabel();
                                                                                lblContador = new javax.swing.JLabel();
                                                                                lblContadorF = new javax.swing.JLabel();
                                                                                lblNumeroTotal = new javax.swing.JLabel();
                                                                                lblContadorA = new javax.swing.JLabel();
                                                                                lblNumeroAF = new javax.swing.JLabel();
                                                                                lblNumeroFT = new javax.swing.JLabel();
                                                                                lblMedicoId = new javax.swing.JLabel();
                                                                                lblMantP = new javax.swing.JLabel();
                                                                                lblId_EmpresaFP = new javax.swing.JLabel();
                                                                                lblPorcentaje = new javax.swing.JLabel();
                                                                                lblUsuPorcentaje = new javax.swing.JLabel();
                                                                                lblSerie = new javax.swing.JLabel();
                                                                                lblNumDoc = new javax.swing.JLabel();
                                                                                panelTablaCPT = new javax.swing.JPanel();
                                                                                jLabel45 = new javax.swing.JLabel();
                                                                                jLabel77 = new javax.swing.JLabel();
                                                                                jLabel75 = new javax.swing.JLabel();
                                                                                jScrollPane4 = new javax.swing.JScrollPane();
                                                                                tb_CPT = new javax.swing.JTable(){
                                                                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                                                                        return false; //Disallow the editing of any cell
                                                                                    }};
                                                                                    jPanel36 = new javax.swing.JPanel();
                                                                                    jLabel74 = new javax.swing.JLabel();
                                                                                    jLabel76 = new javax.swing.JLabel();
                                                                                    jPanel5 = new javax.swing.JPanel();
                                                                                    jScrollPane11 = new javax.swing.JScrollPane();
                                                                                    tb_ReporteDiario = new javax.swing.JTable(){
                                                                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                                                                            return false; //Disallow the editing of any cell
                                                                                        }};
                                                                                        resumen1 = new javax.swing.JPanel();
                                                                                        lblTotalPendiente = new javax.swing.JLabel();
                                                                                        lblTotalContado = new javax.swing.JLabel();
                                                                                        jLabel71 = new javax.swing.JLabel();
                                                                                        lblTotalDiario = new javax.swing.JLabel();
                                                                                        APENOM1 = new javax.swing.JLabel();
                                                                                        jLabel72 = new javax.swing.JLabel();
                                                                                        jLabel73 = new javax.swing.JLabel();
                                                                                        jLabel84 = new javax.swing.JLabel();
                                                                                        lblTotalAnulado = new javax.swing.JLabel();
                                                                                        jPanel6 = new javax.swing.JPanel();
                                                                                        ChkAnalf1 = new javax.swing.JTextField();
                                                                                        ChkEdad1 = new javax.swing.JTextField();
                                                                                        jLabel85 = new javax.swing.JLabel();
                                                                                        jLabel86 = new javax.swing.JLabel();
                                                                                        jLabel87 = new javax.swing.JLabel();
                                                                                        ChkEdad2 = new javax.swing.JTextField();
                                                                                        jLabel88 = new javax.swing.JLabel();
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
                                                                                        btnLista = new javax.swing.JButton();

                                                                                        BHC.setAlwaysOnTop(true);
                                                                                        BHC.setMinimumSize(new java.awt.Dimension(749, 350));
                                                                                        BHC.setResizable(false);

                                                                                        jPanel7.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                                        jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                                        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel19.setText("Paciente");

                                                                                        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel13.setText("Busqueda por DNI, H.C. y Apellidos");

                                                                                        bus.setForeground(new java.awt.Color(41, 127, 184));
                                                                                        bus.setText("jLabel37");

                                                                                        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        txtBuscarPaciente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        txtBuscarPaciente.setForeground(new java.awt.Color(98, 98, 98));
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

                                                                                        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                                                                                        jPanel27.setLayout(jPanel27Layout);
                                                                                        jPanel27Layout.setHorizontalGroup(
                                                                                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel27Layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap())
                                                                                        );
                                                                                        jPanel27Layout.setVerticalGroup(
                                                                                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        bus3.setForeground(new java.awt.Color(41, 127, 184));
                                                                                        bus3.setText("jLabel37");

                                                                                        btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                                                                        btnBuscarPaciente2.setContentAreaFilled(false);
                                                                                        btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnBuscarPaciente2ActionPerformed(evt);
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
                                                                                                        .addComponent(jLabel13)
                                                                                                        .addGap(39, 39, 39)
                                                                                                        .addComponent(bus)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(bus3))
                                                                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(2, 2, 2)
                                                                                                        .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel7Layout.setVerticalGroup(
                                                                                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                                                        .addComponent(jLabel19)
                                                                                                        .addGap(10, 10, 10)
                                                                                                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                        .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(bus)
                                                                                                            .addComponent(bus3))))
                                                                                                .addGap(331, 331, 331))
                                                                                        );

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

                                                                                        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                                                                        jPanel8.setLayout(jPanel8Layout);
                                                                                        jPanel8Layout.setHorizontalGroup(
                                                                                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(28, 28, 28)
                                                                                                .addComponent(lblIdPreventa)
                                                                                                .addGap(145, 145, 145)
                                                                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel8Layout.setVerticalGroup(
                                                                                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                                                                                        .addGap(17, 17, 17)
                                                                                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addComponent(lblIdPreventa)
                                                                                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                                                                                        .addGap(32, 32, 32)
                                                                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );

                                                                                        panelBuscarHC.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 34)); // NOI18N
                                                                                        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Find User Male-80.png"))); // NOI18N
                                                                                        jLabel9.setText("Busqueda de Pacientes ");

                                                                                        javax.swing.GroupLayout panelBuscarHCLayout = new javax.swing.GroupLayout(panelBuscarHC);
                                                                                        panelBuscarHC.setLayout(panelBuscarHCLayout);
                                                                                        panelBuscarHCLayout.setHorizontalGroup(
                                                                                            panelBuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelBuscarHCLayout.createSequentialGroup()
                                                                                                .addGap(134, 134, 134)
                                                                                                .addComponent(jLabel9)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelBuscarHCLayout.setVerticalGroup(
                                                                                            panelBuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelBuscarHCLayout.createSequentialGroup()
                                                                                                .addGap(62, 62, 62)
                                                                                                .addComponent(jLabel9)
                                                                                                .addContainerGap(50, Short.MAX_VALUE))
                                                                                        );

                                                                                        paneltablaHC.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                                                        tbPacientes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        tbPacientes.setForeground(new java.awt.Color(51, 51, 51));
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
                                                                                        tbPacientes.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                                                        tbPacientes.getTableHeader().setReorderingAllowed(false);
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

                                                                                        jPanel35.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel35.setPreferredSize(new java.awt.Dimension(0, 2));

                                                                                        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
                                                                                        jPanel35.setLayout(jPanel35Layout);
                                                                                        jPanel35Layout.setHorizontalGroup(
                                                                                            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel35Layout.setVerticalGroup(
                                                                                            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                                                        );

                                                                                        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel40.setText("Nº H.C.");

                                                                                        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel47.setText(" DNI");

                                                                                        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel50.setText("Paciente");

                                                                                        javax.swing.GroupLayout paneltablaHCLayout = new javax.swing.GroupLayout(paneltablaHC);
                                                                                        paneltablaHC.setLayout(paneltablaHCLayout);
                                                                                        paneltablaHCLayout.setHorizontalGroup(
                                                                                            paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                                                                                            .addGroup(paneltablaHCLayout.createSequentialGroup()
                                                                                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(31, 31, 31)
                                                                                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(30, 30, 30)
                                                                                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                            .addComponent(jScrollPane2)
                                                                                        );
                                                                                        paneltablaHCLayout.setVerticalGroup(
                                                                                            paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneltablaHCLayout.createSequentialGroup()
                                                                                                .addGroup(paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        panelSinHC.setBackground(new java.awt.Color(255, 255, 255));

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
                                                                                                .addContainerGap(47, Short.MAX_VALUE))
                                                                                        );

                                                                                        javax.swing.GroupLayout panelSinHCLayout = new javax.swing.GroupLayout(panelSinHC);
                                                                                        panelSinHC.setLayout(panelSinHCLayout);
                                                                                        panelSinHCLayout.setHorizontalGroup(
                                                                                            panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel17)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jLabel16)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                                                                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );
                                                                                        panelSinHCLayout.setVerticalGroup(
                                                                                            panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                                                                                .addGroup(panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(panelSinHCLayout.createSequentialGroup()
                                                                                                        .addGap(32, 32, 32)
                                                                                                        .addComponent(jLabel17))
                                                                                                    .addGroup(panelSinHCLayout.createSequentialGroup()
                                                                                                        .addGap(87, 87, 87)
                                                                                                        .addComponent(jLabel16)))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        );

                                                                                        javax.swing.GroupLayout BHCLayout = new javax.swing.GroupLayout(BHC.getContentPane());
                                                                                        BHC.getContentPane().setLayout(BHCLayout);
                                                                                        BHCLayout.setHorizontalGroup(
                                                                                            BHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelBuscarHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(paneltablaHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelSinHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        );
                                                                                        BHCLayout.setVerticalGroup(
                                                                                            BHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(BHCLayout.createSequentialGroup()
                                                                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelBuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(paneltablaHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelSinHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                        );

                                                                                        Jerarquias.setAlwaysOnTop(true);
                                                                                        Jerarquias.setMinimumSize(new java.awt.Dimension(628, 300));
                                                                                        Jerarquias.setResizable(false);

                                                                                        jPanel10.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel10.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                                        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                                        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel20.setText("Forma de Pago");

                                                                                        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        txtBuscarFormaPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        txtBuscarFormaPago.setBorder(null);
                                                                                        txtBuscarFormaPago.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtBuscarFormaPagoCaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarFormaPago.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                txtBuscarFormaPagoMouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarFormaPago.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                txtBuscarFormaPagoActionPerformed(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarFormaPago.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                txtBuscarFormaPagoKeyPressed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                                                                                        jPanel29.setLayout(jPanel29Layout);
                                                                                        jPanel29Layout.setHorizontalGroup(
                                                                                            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel29Layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(txtBuscarFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                                                                                .addContainerGap())
                                                                                        );
                                                                                        jPanel29Layout.setVerticalGroup(
                                                                                            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(txtBuscarFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        btnBuscarPaciente4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                                                                        btnBuscarPaciente4.setContentAreaFilled(false);
                                                                                        btnBuscarPaciente4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnBuscarPaciente4.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnBuscarPaciente4ActionPerformed(evt);
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
                                                                                                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel10Layout.setVerticalGroup(
                                                                                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel20)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                                                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addGap(352, 352, 352))
                                                                                        );

                                                                                        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                                                                                        tb_Grupo3.setSelectionBackground(new java.awt.Color(102, 102, 102));
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

                                                                                        jPanel11.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                                        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                                        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel21.setText("CPT");

                                                                                        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        txtBuscarCPT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        txtBuscarCPT.setForeground(new java.awt.Color(98, 98, 98));
                                                                                        txtBuscarCPT.setBorder(null);
                                                                                        txtBuscarCPT.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtBuscarCPTCaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                txtBuscarCPTActionPerformed(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarCPT.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                txtBuscarCPTKeyPressed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                                                                                        jPanel28.setLayout(jPanel28Layout);
                                                                                        jPanel28Layout.setHorizontalGroup(
                                                                                            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel28Layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(txtBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel28Layout.setVerticalGroup(
                                                                                            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(txtBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        btnBuscarPaciente3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                                                                        btnBuscarPaciente3.setContentAreaFilled(false);
                                                                                        btnBuscarPaciente3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnBuscarPaciente3.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnBuscarPaciente3ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                                                                                        jPanel11.setLayout(jPanel11Layout);
                                                                                        jPanel11Layout.setHorizontalGroup(
                                                                                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel21)
                                                                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                                                                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel11Layout.setVerticalGroup(
                                                                                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel21)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                                                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addGap(353, 353, 353))
                                                                                        );

                                                                                        panelBuscar.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                                        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-80.png"))); // NOI18N
                                                                                        jLabel22.setText("Busqueda de CPT ");

                                                                                        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
                                                                                        panelBuscar.setLayout(panelBuscarLayout);
                                                                                        panelBuscarLayout.setHorizontalGroup(
                                                                                            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelBuscarLayout.createSequentialGroup()
                                                                                                .addGap(160, 160, 160)
                                                                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelBuscarLayout.setVerticalGroup(
                                                                                            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelBuscarLayout.createSequentialGroup()
                                                                                                .addGap(59, 59, 59)
                                                                                                .addComponent(jLabel22)
                                                                                                .addContainerGap(73, Short.MAX_VALUE))
                                                                                        );

                                                                                        panelCargarCPT.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane6.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                                                                                        tb_CPTBUSCAR.setRowMargin(0);
                                                                                        tb_CPTBUSCAR.setSelectionBackground(new java.awt.Color(102, 102, 102));
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

                                                                                        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel3.setText("  CPT");

                                                                                        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel8.setText("Descripción");

                                                                                        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel10.setText("Precio");

                                                                                        jPanel33.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel33.setPreferredSize(new java.awt.Dimension(0, 2));

                                                                                        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
                                                                                        jPanel33.setLayout(jPanel33Layout);
                                                                                        jPanel33Layout.setHorizontalGroup(
                                                                                            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel33Layout.setVerticalGroup(
                                                                                            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                                                        );

                                                                                        javax.swing.GroupLayout panelCargarCPTLayout = new javax.swing.GroupLayout(panelCargarCPT);
                                                                                        panelCargarCPT.setLayout(panelCargarCPTLayout);
                                                                                        panelCargarCPTLayout.setHorizontalGroup(
                                                                                            panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelCargarCPTLayout.createSequentialGroup()
                                                                                                .addGroup(panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(panelCargarCPTLayout.createSequentialGroup()
                                                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(64, 64, 64)
                                                                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
                                                                                                .addContainerGap())
                                                                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                                                                                        );
                                                                                        panelCargarCPTLayout.setVerticalGroup(
                                                                                            panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCargarCPTLayout.createSequentialGroup()
                                                                                                .addGroup(panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        panelSinCPT.setBackground(new java.awt.Color(255, 255, 255));

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

                                                                                        javax.swing.GroupLayout panelSinCPTLayout = new javax.swing.GroupLayout(panelSinCPT);
                                                                                        panelSinCPT.setLayout(panelSinCPTLayout);
                                                                                        panelSinCPTLayout.setHorizontalGroup(
                                                                                            panelSinCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelSinCPTLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel24)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel23)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                                                                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );
                                                                                        panelSinCPTLayout.setVerticalGroup(
                                                                                            panelSinCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelSinCPTLayout.createSequentialGroup()
                                                                                                .addGap(39, 39, 39)
                                                                                                .addComponent(jLabel24)
                                                                                                .addContainerGap(39, Short.MAX_VALUE))
                                                                                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSinCPTLayout.createSequentialGroup()
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel23)
                                                                                                .addGap(73, 73, 73))
                                                                                        );

                                                                                        javax.swing.GroupLayout nomenclaturasLayout = new javax.swing.GroupLayout(nomenclaturas.getContentPane());
                                                                                        nomenclaturas.getContentPane().setLayout(nomenclaturasLayout);
                                                                                        nomenclaturasLayout.setHorizontalGroup(
                                                                                            nomenclaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelSinCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelCargarCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        );
                                                                                        nomenclaturasLayout.setVerticalGroup(
                                                                                            nomenclaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(nomenclaturasLayout.createSequentialGroup()
                                                                                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelCargarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelSinCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                        );

                                                                                        preventas.setAlwaysOnTop(true);
                                                                                        preventas.setMinimumSize(new java.awt.Dimension(770, 422));
                                                                                        preventas.setResizable(false);

                                                                                        jPanel18.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel18.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                                        jLabel29.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                                        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel29.setText("Preventa");

                                                                                        jLabel34.setForeground(new java.awt.Color(41, 127, 184));
                                                                                        jLabel34.setText("jLabel34");

                                                                                        elimdp.setForeground(new java.awt.Color(41, 127, 184));
                                                                                        elimdp.setText("jLabel40");

                                                                                        jPanel24.setBackground(new java.awt.Color(41, 127, 184));

                                                                                        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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
                                                                                            .addGap(0, 127, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel25Layout.setVerticalGroup(
                                                                                            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 4, Short.MAX_VALUE)
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

                                                                                        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
                                                                                        jPanel24.setLayout(jPanel24Layout);
                                                                                        jPanel24Layout.setHorizontalGroup(
                                                                                            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel24Layout.createSequentialGroup()
                                                                                                .addGap(19, 19, 19)
                                                                                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                                                                                        .addComponent(jLabel35)
                                                                                                        .addGap(0, 0, 0)
                                                                                                        .addComponent(btnbuscar4)))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel24Layout.setVerticalGroup(
                                                                                            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel24Layout.createSequentialGroup()
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel35)
                                                                                                    .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        jPanel31.setBackground(new java.awt.Color(41, 127, 184));

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

                                                                                        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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

                                                                                        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                                                                                        jPanel31.setLayout(jPanel31Layout);
                                                                                        jPanel31Layout.setHorizontalGroup(
                                                                                            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                                                                                .addContainerGap(26, Short.MAX_VALUE)
                                                                                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                                                                                        .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                                                                                        .addContainerGap())
                                                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                                                                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(btnbuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                        );
                                                                                        jPanel31Layout.setVerticalGroup(
                                                                                            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel31Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel37)
                                                                                                    .addComponent(btnbuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        jPanel42.setBackground(new java.awt.Color(41, 127, 184));

                                                                                        jPanel43.setPreferredSize(new java.awt.Dimension(117, 4));

                                                                                        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
                                                                                        jPanel43.setLayout(jPanel43Layout);
                                                                                        jPanel43Layout.setHorizontalGroup(
                                                                                            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 211, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel43Layout.setVerticalGroup(
                                                                                            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 4, Short.MAX_VALUE)
                                                                                        );

                                                                                        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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

                                                                                        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
                                                                                        jPanel42.setLayout(jPanel42Layout);
                                                                                        jPanel42Layout.setHorizontalGroup(
                                                                                            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                                                                                                .addContainerGap(38, Short.MAX_VALUE)
                                                                                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addGroup(jPanel42Layout.createSequentialGroup()
                                                                                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                        .addComponent(btnbuscar10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGap(35, 35, 35))
                                                                                        );
                                                                                        jPanel42Layout.setVerticalGroup(
                                                                                            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel42Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel41)
                                                                                                    .addComponent(btnbuscar10))
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
                                                                                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel29))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                                        .addComponent(jLabel34)
                                                                                                        .addGap(178, 178, 178)
                                                                                                        .addComponent(elimdp))
                                                                                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(18, 18, 18)
                                                                                                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGap(0, 189, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel18Layout.setVerticalGroup(
                                                                                            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addComponent(elimdp)
                                                                                                            .addComponent(jLabel29))
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
                                                                                        jScrollPane13.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                                                                                        jScrollPane14.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                                                                                        jScrollPane22.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                                                                                        jScrollPane23.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                                                                                        jScrollPane15.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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

                                                                                        Empresa.setAlwaysOnTop(true);
                                                                                        Empresa.setMinimumSize(new java.awt.Dimension(754, 420));
                                                                                        Empresa.setResizable(false);

                                                                                        jPanel46.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel46.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                                        jLabel62.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                                        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel62.setText("<html>Empresa<span style=\"font-size:'14px'\"> Forma de pago</span></html>");

                                                                                        jPanel47.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        txtBuscarEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        txtBuscarEmpresa.setBorder(null);
                                                                                        txtBuscarEmpresa.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtBuscarEmpresaCaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarEmpresa.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                txtBuscarEmpresaActionPerformed(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                txtBuscarEmpresaKeyPressed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
                                                                                        jPanel47.setLayout(jPanel47Layout);
                                                                                        jPanel47Layout.setHorizontalGroup(
                                                                                            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel47Layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(txtBuscarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 1, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel47Layout.setVerticalGroup(
                                                                                            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(txtBuscarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel56.setText("Representante o Razón social");

                                                                                        btnBuscarPaciente5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                                                                        btnBuscarPaciente5.setContentAreaFilled(false);
                                                                                        btnBuscarPaciente5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnBuscarPaciente5.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnBuscarPaciente5ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
                                                                                        jPanel46.setLayout(jPanel46Layout);
                                                                                        jPanel46Layout.setHorizontalGroup(
                                                                                            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel46Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel56)
                                                                                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addGroup(jPanel46Layout.createSequentialGroup()
                                                                                                        .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(btnBuscarPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addContainerGap(470, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel46Layout.setVerticalGroup(
                                                                                            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addComponent(btnBuscarPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                                                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel56)
                                                                                                .addGap(326, 326, 326))
                                                                                        );

                                                                                        jScrollPane16.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane16.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                                                        tb_Empresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        tb_Empresa.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                                        tb_Empresa.setGridColor(new java.awt.Color(255, 255, 255));
                                                                                        tb_Empresa.setRowHeight(25);
                                                                                        tb_Empresa.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                                                        tb_Empresa.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                tb_EmpresaMouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        tb_Empresa.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                tb_EmpresaKeyPressed(evt);
                                                                                            }
                                                                                        });
                                                                                        jScrollPane16.setViewportView(tb_Empresa);

                                                                                        jPanel51.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jLabel65.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                                        jLabel65.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        jLabel65.setText("No lo encuentra?");

                                                                                        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 80)); // NOI18N
                                                                                        jLabel66.setForeground(new java.awt.Color(41, 127, 184));
                                                                                        jLabel66.setText(":(");
                                                                                        jLabel66.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                                                                                        jPanel12.setBackground(new java.awt.Color(50, 151, 219));

                                                                                        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                                                                        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Añadir contacto a empresa-50.png"))); // NOI18N
                                                                                        jLabel78.setText("Registrar");
                                                                                        jLabel78.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                jLabel78MouseClicked(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                                                                        jPanel12.setLayout(jPanel12Layout);
                                                                                        jPanel12Layout.setHorizontalGroup(
                                                                                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel12Layout.setVerticalGroup(
                                                                                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                                                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 39, Short.MAX_VALUE))
                                                                                        );

                                                                                        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
                                                                                        jPanel51.setLayout(jPanel51Layout);
                                                                                        jPanel51Layout.setHorizontalGroup(
                                                                                            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel51Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel66)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel65)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );
                                                                                        jPanel51Layout.setVerticalGroup(
                                                                                            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel51Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel51Layout.createSequentialGroup()
                                                                                                        .addGap(23, 23, 23)
                                                                                                        .addComponent(jLabel65))
                                                                                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );

                                                                                        jPanel45.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel45.setPreferredSize(new java.awt.Dimension(0, 2));

                                                                                        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
                                                                                        jPanel45.setLayout(jPanel45Layout);
                                                                                        jPanel45Layout.setHorizontalGroup(
                                                                                            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel45Layout.setVerticalGroup(
                                                                                            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                                                        );

                                                                                        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel79.setText("RUC");

                                                                                        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel80.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel80.setText("Razón social");

                                                                                        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel81.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel81.setText(" Empresa");

                                                                                        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel82.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel82.setText("Dirección");

                                                                                        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel83.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel83.setText("Teléfono");

                                                                                        javax.swing.GroupLayout EmpresaLayout = new javax.swing.GroupLayout(Empresa.getContentPane());
                                                                                        Empresa.getContentPane().setLayout(EmpresaLayout);
                                                                                        EmpresaLayout.setHorizontalGroup(
                                                                                            EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmpresaLayout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addGroup(EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                                                                                                    .addGroup(EmpresaLayout.createSequentialGroup()
                                                                                                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                                                                                                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(65, 65, 65)
                                                                                                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(18, 18, 18))
                                                                                                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)))
                                                                                        );
                                                                                        EmpresaLayout.setVerticalGroup(
                                                                                            EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(EmpresaLayout.createSequentialGroup()
                                                                                                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        AsistenciaSocial.setMinimumSize(new java.awt.Dimension(630, 351));
                                                                                        AsistenciaSocial.setResizable(false);

                                                                                        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jPanel32.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel32.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                                        jLabel43.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                                        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel43.setText("Exoneraciones ");

                                                                                        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
                                                                                        jPanel32.setLayout(jPanel32Layout);
                                                                                        jPanel32Layout.setHorizontalGroup(
                                                                                            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel32Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel43)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel32Layout.setVerticalGroup(
                                                                                            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel32Layout.createSequentialGroup()
                                                                                                .addGap(20, 20, 20)
                                                                                                .addComponent(jLabel43)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );

                                                                                        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel1.setText("Asistente Social");

                                                                                        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane8.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                                                                                        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                                                                                        tb_Asistente.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        tb_Asistente.setModel(new javax.swing.table.DefaultTableModel(
                                                                                            new Object [][] {
                                                                                                {},
                                                                                                {},
                                                                                                {},
                                                                                                {}
                                                                                            },
                                                                                            new String [] {

                                                                                            }
                                                                                        ));
                                                                                        tb_Asistente.setGridColor(new java.awt.Color(255, 255, 255));
                                                                                        tb_Asistente.setRowHeight(25);
                                                                                        tb_Asistente.setRowMargin(0);
                                                                                        tb_Asistente.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                                                        tb_Asistente.getTableHeader().setReorderingAllowed(false);
                                                                                        tb_Asistente.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                tb_AsistenteMouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        tb_Asistente.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                tb_AsistenteKeyPressed(evt);
                                                                                            }
                                                                                        });
                                                                                        jScrollPane8.setViewportView(tb_Asistente);

                                                                                        jPanel44.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel44.setPreferredSize(new java.awt.Dimension(0, 2));

                                                                                        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
                                                                                        jPanel44.setLayout(jPanel44Layout);
                                                                                        jPanel44Layout.setHorizontalGroup(
                                                                                            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 350, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel44Layout.setVerticalGroup(
                                                                                            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                                                        );

                                                                                        panelExoneracion.setBackground(new java.awt.Color(43, 43, 43));

                                                                                        btnNuevo3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        btnNuevo3.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        btnNuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Introducir-40.png"))); // NOI18N
                                                                                        btnNuevo3.setText("Seleccionar");
                                                                                        btnNuevo3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        btnNuevo3.setContentAreaFilled(false);
                                                                                        btnNuevo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnNuevo3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                                                        btnNuevo3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                                        btnNuevo3.setIconTextGap(30);
                                                                                        btnNuevo3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                                        btnNuevo3.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnNuevo3ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout panelExoneracionLayout = new javax.swing.GroupLayout(panelExoneracion);
                                                                                        panelExoneracion.setLayout(panelExoneracionLayout);
                                                                                        panelExoneracionLayout.setHorizontalGroup(
                                                                                            panelExoneracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelExoneracionLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(btnNuevo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelExoneracionLayout.setVerticalGroup(
                                                                                            panelExoneracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelExoneracionLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(btnNuevo3)
                                                                                                .addContainerGap(29, Short.MAX_VALUE))
                                                                                        );

                                                                                        panelPorcentajes.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel7.setText("75 %");

                                                                                        txtT2.setEditable(false);
                                                                                        txtT2.setBackground(new java.awt.Color(255, 204, 51));
                                                                                        txtT2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                                                                                        txtT2.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        txtT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                                                                                        txtT2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                                                                                        txtT2.setPreferredSize(new java.awt.Dimension(18, 18));
                                                                                        txtT2.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtT2CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT2.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                txtT2MouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT2.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                txtT2ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel4.setText("25 %");

                                                                                        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel6.setText("100 %");

                                                                                        txtT3.setEditable(false);
                                                                                        txtT3.setBackground(new java.awt.Color(243, 156, 17));
                                                                                        txtT3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                                                                                        txtT3.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        txtT3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                                                                                        txtT3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                                                                                        txtT3.setPreferredSize(new java.awt.Dimension(18, 18));
                                                                                        txtT3.setSelectionColor(new java.awt.Color(243, 156, 17));
                                                                                        txtT3.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtT3CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT3.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                txtT3MouseClicked(evt);
                                                                                            }
                                                                                        });

                                                                                        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel5.setText("50 %");

                                                                                        txtT4.setEditable(false);
                                                                                        txtT4.setBackground(new java.awt.Color(255, 51, 51));
                                                                                        txtT4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                                                                                        txtT4.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        txtT4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                                                                                        txtT4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                                                                                        txtT4.setPreferredSize(new java.awt.Dimension(18, 18));
                                                                                        txtT4.setSelectionColor(new java.awt.Color(255, 51, 51));
                                                                                        txtT4.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtT4CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT4.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                txtT4MouseClicked(evt);
                                                                                            }
                                                                                        });

                                                                                        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel2.setText("Porcentaje de Descuento");

                                                                                        txtT1.setEditable(false);
                                                                                        txtT1.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        txtT1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                                                                                        txtT1.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        txtT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                                                                                        txtT1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                                                                                        txtT1.setPreferredSize(new java.awt.Dimension(18, 18));
                                                                                        txtT1.setSelectionColor(new java.awt.Color(255, 204, 51));
                                                                                        txtT1.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtT1CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                txtT1MouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT1.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                txtT1ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout panelPorcentajesLayout = new javax.swing.GroupLayout(panelPorcentajes);
                                                                                        panelPorcentajes.setLayout(panelPorcentajesLayout);
                                                                                        panelPorcentajesLayout.setHorizontalGroup(
                                                                                            panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelPorcentajesLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel2)
                                                                                                    .addGroup(panelPorcentajesLayout.createSequentialGroup()
                                                                                                        .addGroup(panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addComponent(txtT2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(txtT1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(txtT3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(txtT4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                        .addGroup(panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addComponent(jLabel4)
                                                                                                            .addComponent(jLabel5)
                                                                                                            .addComponent(jLabel6)
                                                                                                            .addComponent(jLabel7))))
                                                                                                .addContainerGap())
                                                                                        );
                                                                                        panelPorcentajesLayout.setVerticalGroup(
                                                                                            panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelPorcentajesLayout.createSequentialGroup()
                                                                                                .addGap(0, 0, 0)
                                                                                                .addGroup(panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(panelPorcentajesLayout.createSequentialGroup()
                                                                                                        .addGap(31, 31, 31)
                                                                                                        .addGroup(panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(txtT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(jLabel4))
                                                                                                        .addGap(18, 18, 18)
                                                                                                        .addGroup(panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(txtT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(jLabel5))
                                                                                                        .addGap(21, 21, 21)
                                                                                                        .addGroup(panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(txtT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(jLabel7))
                                                                                                        .addGap(18, 18, 18)
                                                                                                        .addGroup(panelPorcentajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(txtT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(jLabel6)))
                                                                                                    .addComponent(jLabel2))
                                                                                                .addContainerGap())
                                                                                        );

                                                                                        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                                                                                        jPanel20.setLayout(jPanel20Layout);
                                                                                        jPanel20Layout.setHorizontalGroup(
                                                                                            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addGroup(jPanel20Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                                                    .addComponent(jLabel1)
                                                                                                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                                                                                .addComponent(panelPorcentajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(34, 34, 34))
                                                                                            .addComponent(panelExoneracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel20Layout.setVerticalGroup(
                                                                                            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel20Layout.createSequentialGroup()
                                                                                                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(7, 7, 7)
                                                                                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                                                                                        .addComponent(jLabel1)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(0, 0, 0)
                                                                                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                    .addComponent(panelPorcentajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelExoneracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );

                                                                                        javax.swing.GroupLayout AsistenciaSocialLayout = new javax.swing.GroupLayout(AsistenciaSocial.getContentPane());
                                                                                        AsistenciaSocial.getContentPane().setLayout(AsistenciaSocialLayout);
                                                                                        AsistenciaSocialLayout.setHorizontalGroup(
                                                                                            AsistenciaSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        );
                                                                                        AsistenciaSocialLayout.setVerticalGroup(
                                                                                            AsistenciaSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        );

                                                                                        MedicosConsultorios.setAlwaysOnTop(true);
                                                                                        MedicosConsultorios.setMinimumSize(new java.awt.Dimension(1060, 495));
                                                                                        MedicosConsultorios.setResizable(false);

                                                                                        jPanelCabecera.setBackground(new java.awt.Color(0, 153, 102));
                                                                                        jPanelCabecera.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                                        jLabel63.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                                        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel63.setText("Consultorios Externos");

                                                                                        jPanel49.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        txtBuscarMedicos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        txtBuscarMedicos.setBorder(null);
                                                                                        txtBuscarMedicos.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtBuscarMedicosCaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarMedicos.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                txtBuscarMedicosActionPerformed(evt);
                                                                                            }
                                                                                        });
                                                                                        txtBuscarMedicos.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                txtBuscarMedicosKeyPressed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
                                                                                        jPanel49.setLayout(jPanel49Layout);
                                                                                        jPanel49Layout.setHorizontalGroup(
                                                                                            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel49Layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(txtBuscarMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel49Layout.setVerticalGroup(
                                                                                            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(txtBuscarMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel58.setText("Búsqueda por nombres y apellidos");

                                                                                        btnBuscarPaciente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                                                                        btnBuscarPaciente1.setContentAreaFilled(false);
                                                                                        btnBuscarPaciente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnBuscarPaciente1.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnBuscarPaciente1ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        lblMedicoApeNom.setForeground(new java.awt.Color(0, 153, 102));
                                                                                        lblMedicoApeNom.setText("jLabel59");

                                                                                        javax.swing.GroupLayout jPanelCabeceraLayout = new javax.swing.GroupLayout(jPanelCabecera);
                                                                                        jPanelCabecera.setLayout(jPanelCabeceraLayout);
                                                                                        jPanelCabeceraLayout.setHorizontalGroup(
                                                                                            jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanelCabeceraLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanelCabeceraLayout.createSequentialGroup()
                                                                                                        .addComponent(jLabel58)
                                                                                                        .addGap(116, 116, 116)
                                                                                                        .addComponent(lblMedicoApeNom))
                                                                                                    .addComponent(jLabel63)
                                                                                                    .addGroup(jPanelCabeceraLayout.createSequentialGroup()
                                                                                                        .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(3, 3, 3)
                                                                                                        .addComponent(btnBuscarPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanelCabeceraLayout.setVerticalGroup(
                                                                                            jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCabeceraLayout.createSequentialGroup()
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel63)
                                                                                                .addGap(12, 12, 12)
                                                                                                .addGroup(jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(btnBuscarPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel58)
                                                                                                    .addComponent(lblMedicoApeNom))
                                                                                                .addGap(333, 333, 333))
                                                                                        );

                                                                                        panelTurnos.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jScrollPane17.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane17.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                                                        tb_medicos1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        tb_medicos1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        tb_medicos1.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                                        tb_medicos1.setGridColor(new java.awt.Color(255, 255, 255));
                                                                                        tb_medicos1.setRowHeight(25);
                                                                                        tb_medicos1.setSelectionBackground(new java.awt.Color(39, 174, 97));
                                                                                        tb_medicos1.getTableHeader().setReorderingAllowed(false);
                                                                                        tb_medicos1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                tb_medicos1MouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        tb_medicos1.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                tb_medicos1KeyPressed(evt);
                                                                                            }
                                                                                        });
                                                                                        jScrollPane17.setViewportView(tb_medicos1);

                                                                                        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel51.setText("  Médico");

                                                                                        jPanel37.setBackground(new java.awt.Color(0, 153, 102));
                                                                                        jPanel37.setPreferredSize(new java.awt.Dimension(0, 2));

                                                                                        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
                                                                                        jPanel37.setLayout(jPanel37Layout);
                                                                                        jPanel37Layout.setHorizontalGroup(
                                                                                            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel37Layout.setVerticalGroup(
                                                                                            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                                                        );

                                                                                        panelDeatelleC.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jScrollPane18.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane18.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                                                        tb_consultorios.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        tb_consultorios.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        tb_consultorios.setModel(new javax.swing.table.DefaultTableModel(
                                                                                            new Object [][] {
                                                                                                {},
                                                                                                {},
                                                                                                {},
                                                                                                {}
                                                                                            },
                                                                                            new String [] {

                                                                                            }
                                                                                        ));
                                                                                        tb_consultorios.setGridColor(new java.awt.Color(255, 255, 255));
                                                                                        tb_consultorios.setRowHeight(25);
                                                                                        tb_consultorios.setSelectionBackground(new java.awt.Color(142, 142, 142));
                                                                                        tb_consultorios.getTableHeader().setReorderingAllowed(false);
                                                                                        tb_consultorios.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                tb_consultoriosMouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        tb_consultorios.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                tb_consultoriosKeyPressed(evt);
                                                                                            }
                                                                                        });
                                                                                        jScrollPane18.setViewportView(tb_consultorios);

                                                                                        jPanel38.setBackground(new java.awt.Color(0, 153, 102));
                                                                                        jPanel38.setPreferredSize(new java.awt.Dimension(0, 2));

                                                                                        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
                                                                                        jPanel38.setLayout(jPanel38Layout);
                                                                                        jPanel38Layout.setHorizontalGroup(
                                                                                            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel38Layout.setVerticalGroup(
                                                                                            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                                                        );

                                                                                        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel52.setText("  Turno");

                                                                                        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel53.setText("Citas Disponibles");

                                                                                        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel54.setText("Citas Adicionales");

                                                                                        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel55.setText("Citas Futuras");

                                                                                        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel59.setText("Consultorio");

                                                                                        javax.swing.GroupLayout panelDeatelleCLayout = new javax.swing.GroupLayout(panelDeatelleC);
                                                                                        panelDeatelleC.setLayout(panelDeatelleCLayout);
                                                                                        panelDeatelleCLayout.setHorizontalGroup(
                                                                                            panelDeatelleCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelDeatelleCLayout.createSequentialGroup()
                                                                                                .addGap(0, 0, 0)
                                                                                                .addGroup(panelDeatelleCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                                                                                                    .addGroup(panelDeatelleCLayout.createSequentialGroup()
                                                                                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(117, 117, 117)
                                                                                                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(33, 33, 33)
                                                                                                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(27, 27, 27)
                                                                                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(27, 27, 27)
                                                                                                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                    .addComponent(jScrollPane18)))
                                                                                        );
                                                                                        panelDeatelleCLayout.setVerticalGroup(
                                                                                            panelDeatelleCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelDeatelleCLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(panelDeatelleCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                                                                                        );

                                                                                        javax.swing.GroupLayout panelTurnosLayout = new javax.swing.GroupLayout(panelTurnos);
                                                                                        panelTurnos.setLayout(panelTurnosLayout);
                                                                                        panelTurnosLayout.setHorizontalGroup(
                                                                                            panelTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelTurnosLayout.createSequentialGroup()
                                                                                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                            .addComponent(jScrollPane17)
                                                                                            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                                                                                            .addGroup(panelTurnosLayout.createSequentialGroup()
                                                                                                .addComponent(panelDeatelleC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelTurnosLayout.setVerticalGroup(
                                                                                            panelTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelTurnosLayout.createSequentialGroup()
                                                                                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelDeatelleC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        panelSinMedico.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jLabel67.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                                        jLabel67.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        jLabel67.setText("No se halló ningún medico de turno");

                                                                                        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                                                                                        jLabel68.setForeground(new java.awt.Color(0, 153, 102));
                                                                                        jLabel68.setText(":(");

                                                                                        javax.swing.GroupLayout panelSinMedicoLayout = new javax.swing.GroupLayout(panelSinMedico);
                                                                                        panelSinMedico.setLayout(panelSinMedicoLayout);
                                                                                        panelSinMedicoLayout.setHorizontalGroup(
                                                                                            panelSinMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelSinMedicoLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel68)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel67)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelSinMedicoLayout.setVerticalGroup(
                                                                                            panelSinMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelSinMedicoLayout.createSequentialGroup()
                                                                                                .addGroup(panelSinMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel68)
                                                                                                    .addGroup(panelSinMedicoLayout.createSequentialGroup()
                                                                                                        .addGap(53, 53, 53)
                                                                                                        .addComponent(jLabel67)))
                                                                                                .addContainerGap(105, Short.MAX_VALUE))
                                                                                        );

                                                                                        jPanel4.setBackground(new java.awt.Color(43, 43, 43));

                                                                                        panelAsignacion.setBackground(new java.awt.Color(43, 43, 43));

                                                                                        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel60.setText("Asignación");

                                                                                        txtT5.setEditable(false);
                                                                                        txtT5.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        txtT5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                                                                                        txtT5.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        txtT5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtT5.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        txtT5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                                                                                        txtT5.setPreferredSize(new java.awt.Dimension(30, 30));
                                                                                        txtT5.setSelectionColor(new java.awt.Color(255, 204, 51));
                                                                                        txtT5.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtT5CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT5.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                txtT5MouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT5.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                txtT5ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        txtT6.setEditable(false);
                                                                                        txtT6.setBackground(new java.awt.Color(255, 204, 51));
                                                                                        txtT6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                                                                                        txtT6.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        txtT6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtT6.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        txtT6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                                                                                        txtT6.setPreferredSize(new java.awt.Dimension(30, 30));
                                                                                        txtT6.setSelectedTextColor(new java.awt.Color(109, 109, 109));
                                                                                        txtT6.setSelectionColor(new java.awt.Color(255, 204, 51));
                                                                                        txtT6.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                txtT6CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        txtT6.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                txtT6MouseClicked(evt);
                                                                                            }
                                                                                        });

                                                                                        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        jLabel61.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel61.setText("Reservar para hoy");

                                                                                        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        jLabel64.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel64.setText("Reservar a futuro");

                                                                                        btnReservar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        btnReservar.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnReservar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hoy-32 (1).png"))); // NOI18N
                                                                                        btnReservar.setText("Reservar");
                                                                                        btnReservar.setContentAreaFilled(false);
                                                                                        btnReservar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnReservar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                                                        btnReservar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                                        btnReservar.setIconTextGap(30);
                                                                                        btnReservar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                                                        btnReservar.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                btnReservarMouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        btnReservar.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnReservarActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        lblMant.setForeground(new java.awt.Color(43, 43, 43));
                                                                                        lblMant.setText("jLabel69");

                                                                                        panelSinConsultas.setBackground(new java.awt.Color(232, 76, 61));

                                                                                        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel69.setText("Se agotaron las consultas");

                                                                                        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel70.setText("Se utilizaran los adicionales,");

                                                                                        lblCitaFutura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        lblCitaFutura.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        lblCitaFutura.setText("también puede seleccionar citas futuras");

                                                                                        javax.swing.GroupLayout panelSinConsultasLayout = new javax.swing.GroupLayout(panelSinConsultas);
                                                                                        panelSinConsultas.setLayout(panelSinConsultasLayout);
                                                                                        panelSinConsultasLayout.setHorizontalGroup(
                                                                                            panelSinConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelSinConsultasLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(panelSinConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel69)
                                                                                                    .addComponent(jLabel70)
                                                                                                    .addComponent(lblCitaFutura))
                                                                                                .addContainerGap(182, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelSinConsultasLayout.setVerticalGroup(
                                                                                            panelSinConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelSinConsultasLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel69)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel70)
                                                                                                .addGap(3, 3, 3)
                                                                                                .addComponent(lblCitaFutura)
                                                                                                .addContainerGap(25, Short.MAX_VALUE))
                                                                                        );

                                                                                        javax.swing.GroupLayout panelAsignacionLayout = new javax.swing.GroupLayout(panelAsignacion);
                                                                                        panelAsignacion.setLayout(panelAsignacionLayout);
                                                                                        panelAsignacionLayout.setHorizontalGroup(
                                                                                            panelAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelAsignacionLayout.createSequentialGroup()
                                                                                                .addGroup(panelAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(panelAsignacionLayout.createSequentialGroup()
                                                                                                        .addContainerGap()
                                                                                                        .addGroup(panelAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addGroup(panelAsignacionLayout.createSequentialGroup()
                                                                                                                .addGap(11, 11, 11)
                                                                                                                .addGroup(panelAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                    .addGroup(panelAsignacionLayout.createSequentialGroup()
                                                                                                                        .addComponent(txtT6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addGap(18, 18, 18)
                                                                                                                        .addComponent(jLabel64))
                                                                                                                    .addGroup(panelAsignacionLayout.createSequentialGroup()
                                                                                                                        .addComponent(txtT5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addGap(18, 18, 18)
                                                                                                                        .addComponent(jLabel61)))
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblMant))))
                                                                                                    .addComponent(btnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(panelSinConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelAsignacionLayout.setVerticalGroup(
                                                                                            panelAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelAsignacionLayout.createSequentialGroup()
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(31, 31, 31)
                                                                                                .addGroup(panelAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addComponent(lblMant)
                                                                                                    .addGroup(panelAsignacionLayout.createSequentialGroup()
                                                                                                        .addGroup(panelAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(txtT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(jLabel61))
                                                                                                        .addGap(18, 18, 18)
                                                                                                        .addGroup(panelAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(txtT6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(jLabel64))))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(btnReservar)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(panelSinConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0))
                                                                                        );

                                                                                        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                                                                        jPanel4.setLayout(jPanel4Layout);
                                                                                        jPanel4Layout.setHorizontalGroup(
                                                                                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addGap(0, 0, 0))
                                                                                        );
                                                                                        jPanel4Layout.setVerticalGroup(
                                                                                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(panelAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addGap(0, 0, 0))
                                                                                        );

                                                                                        javax.swing.GroupLayout MedicosConsultoriosLayout = new javax.swing.GroupLayout(MedicosConsultorios.getContentPane());
                                                                                        MedicosConsultorios.getContentPane().setLayout(MedicosConsultoriosLayout);
                                                                                        MedicosConsultoriosLayout.setHorizontalGroup(
                                                                                            MedicosConsultoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jPanelCabecera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addGroup(MedicosConsultoriosLayout.createSequentialGroup()
                                                                                                .addComponent(panelTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                            .addGroup(MedicosConsultoriosLayout.createSequentialGroup()
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(panelSinMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addContainerGap())
                                                                                        );
                                                                                        MedicosConsultoriosLayout.setVerticalGroup(
                                                                                            MedicosConsultoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(MedicosConsultoriosLayout.createSequentialGroup()
                                                                                                .addComponent(jPanelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(MedicosConsultoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addComponent(panelTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(panelSinMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap())
                                                                                        );

                                                                                        Medicos.setAlwaysOnTop(true);
                                                                                        Medicos.setMinimumSize(new java.awt.Dimension(749, 398));
                                                                                        Medicos.setResizable(false);
                                                                                        Medicos.getContentPane().setLayout(null);

                                                                                        jPanel48.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel48.setMinimumSize(new java.awt.Dimension(310, 441));

                                                                                        jLabel89.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                                                        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel89.setText("Médicos de turno");

                                                                                        jPanel50.setBackground(new java.awt.Color(255, 255, 255));

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

                                                                                        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
                                                                                        jPanel50.setLayout(jPanel50Layout);
                                                                                        jPanel50Layout.setHorizontalGroup(
                                                                                            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel50Layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(BMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel50Layout.setVerticalGroup(
                                                                                            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(BMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel90.setText("Búsqueda por nombres y apellidos");

                                                                                        btnBuscarPaciente6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                                                                        btnBuscarPaciente6.setContentAreaFilled(false);
                                                                                        btnBuscarPaciente6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnBuscarPaciente6.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnBuscarPaciente6ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
                                                                                        jPanel48.setLayout(jPanel48Layout);
                                                                                        jPanel48Layout.setHorizontalGroup(
                                                                                            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel48Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jLabel90)
                                                                                                    .addComponent(jLabel89)
                                                                                                    .addGroup(jPanel48Layout.createSequentialGroup()
                                                                                                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(2, 2, 2)
                                                                                                        .addComponent(btnBuscarPaciente6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addContainerGap(502, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel48Layout.setVerticalGroup(
                                                                                            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel89)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(btnBuscarPaciente6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(jLabel90)
                                                                                                .addGap(333, 333, 333))
                                                                                        );

                                                                                        Medicos.getContentPane().add(jPanel48);
                                                                                        jPanel48.setBounds(0, 0, 780, 120);

                                                                                        jTabbedPane7.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                                                                                        jPanel52.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jScrollPane19.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane19.setBorder(null);

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
                                                                                        jScrollPane19.setViewportView(tb_medicos);

                                                                                        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
                                                                                        jPanel52.setLayout(jPanel52Layout);
                                                                                        jPanel52Layout.setHorizontalGroup(
                                                                                            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );
                                                                                        jPanel52Layout.setVerticalGroup(
                                                                                            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                                                                        );

                                                                                        jTabbedPane7.addTab("tab2", jPanel52);

                                                                                        jPanel53.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jLabel91.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                                        jLabel91.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        jLabel91.setText("No se halló ningún medico de turno");

                                                                                        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                                                                                        jLabel92.setForeground(new java.awt.Color(0, 153, 153));
                                                                                        jLabel92.setText(":(");

                                                                                        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
                                                                                        jPanel53.setLayout(jPanel53Layout);
                                                                                        jPanel53Layout.setHorizontalGroup(
                                                                                            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel53Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel92)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel91)
                                                                                                .addContainerGap(122, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel53Layout.setVerticalGroup(
                                                                                            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel53Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel53Layout.createSequentialGroup()
                                                                                                        .addGap(39, 39, 39)
                                                                                                        .addComponent(jLabel92))
                                                                                                    .addGroup(jPanel53Layout.createSequentialGroup()
                                                                                                        .addGap(98, 98, 98)
                                                                                                        .addComponent(jLabel91)))
                                                                                                .addContainerGap(109, Short.MAX_VALUE))
                                                                                        );

                                                                                        jTabbedPane7.addTab("tab3", jPanel53);

                                                                                        Medicos.getContentPane().add(jTabbedPane7);
                                                                                        jTabbedPane7.setBounds(0, 118, 749, 310);

                                                                                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                                                                        setIconImage(getIconImage());

                                                                                        jTabbedPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                                                                                        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                                                                                        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                                                                                        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                                                        tb_BusquedaCabecera.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                                        tb_BusquedaCabecera.setGridColor(new java.awt.Color(255, 255, 255));
                                                                                        tb_BusquedaCabecera.setRowHeight(25);
                                                                                        tb_BusquedaCabecera.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                                                                        tb_BusquedaCabecera.getTableHeader().setReorderingAllowed(false);
                                                                                        tb_BusquedaCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                tb_BusquedaCabeceraMouseClicked(evt);
                                                                                            }
                                                                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                                                                tb_BusquedaCabeceraMousePressed(evt);
                                                                                            }
                                                                                        });
                                                                                        tb_BusquedaCabecera.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                tb_BusquedaCabeceraKeyPressed(evt);
                                                                                            }
                                                                                        });
                                                                                        jScrollPane3.setViewportView(tb_BusquedaCabecera);

                                                                                        jScrollPane7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                                                        tb_ReporteDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                                        tb_ReporteDetalle.setGridColor(new java.awt.Color(255, 255, 255));
                                                                                        tb_ReporteDetalle.setRowHeight(25);
                                                                                        tb_ReporteDetalle.setSelectionBackground(new java.awt.Color(23, 160, 134));
                                                                                        tb_ReporteDetalle.getTableHeader().setReorderingAllowed(false);
                                                                                        tb_ReporteDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                tb_ReporteDetalleMouseClicked(evt);
                                                                                            }
                                                                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                                                                tb_ReporteDetalleMousePressed(evt);
                                                                                            }
                                                                                        });
                                                                                        tb_ReporteDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                tb_ReporteDetalleKeyPressed(evt);
                                                                                            }
                                                                                        });
                                                                                        jScrollPane7.setViewportView(tb_ReporteDetalle);

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

                                                                                        bus1.setForeground(new java.awt.Color(43, 43, 43));
                                                                                        bus1.setText("jLabel42");

                                                                                        javax.swing.GroupLayout resumenLayout = new javax.swing.GroupLayout(resumen);
                                                                                        resumen.setLayout(resumenLayout);
                                                                                        resumenLayout.setHorizontalGroup(
                                                                                            resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(resumenLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(APENOM, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                                                                .addContainerGap(139, Short.MAX_VALUE))
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
                                                                                            .addComponent(resumen, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
                                                                                            .addComponent(jScrollPane3)
                                                                                        );
                                                                                        jPanel2Layout.setVerticalGroup(
                                                                                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                                                                .addComponent(resumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                                                                                                .addGap(0, 0, 0))
                                                                                        );

                                                                                        jTabbedPane1.addTab("...", jPanel2);

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

                                                                                        cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        cbxTipoDocumento.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                                                                                        cbxTipoDocumento.setEnabled(false);
                                                                                        cbxTipoDocumento.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseReleased(java.awt.event.MouseEvent evt) {
                                                                                                cbxTipoDocumentoMouseReleased(evt);
                                                                                            }
                                                                                        });
                                                                                        cbxTipoDocumento.addItemListener(new java.awt.event.ItemListener() {
                                                                                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                                                                                cbxTipoDocumentoItemStateChanged(evt);
                                                                                            }
                                                                                        });
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

                                                                                        panelNumeros.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        panelNumeros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                                                                                        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel26.setText("Total");

                                                                                        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
                                                                                        txtTotal.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtTotal.setBorder(null);
                                                                                        txtTotal.setEnabled(false);

                                                                                        txtDescuento.setEditable(false);
                                                                                        txtDescuento.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
                                                                                        txtDescuento.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtDescuento.setBorder(null);

                                                                                        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel27.setText("Descuento");

                                                                                        txtSubTotal.setEditable(false);
                                                                                        txtSubTotal.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
                                                                                        txtSubTotal.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtSubTotal.setBorder(null);

                                                                                        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel28.setText("Subtotal");

                                                                                        txtIGV.setEditable(false);
                                                                                        txtIGV.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
                                                                                        txtIGV.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        txtIGV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        txtIGV.setBorder(null);

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
                                                                                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(60, 60, 60)
                                                                                                .addComponent(jLabel28)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(60, 60, 60)
                                                                                                .addComponent(jLabel39)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(60, 60, 60)
                                                                                                .addComponent(jLabel26)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelNumerosLayout.setVerticalGroup(
                                                                                            panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelNumerosLayout.createSequentialGroup()
                                                                                                .addContainerGap(12, Short.MAX_VALUE)
                                                                                                .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(jLabel28)
                                                                                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                    .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(jLabel27)
                                                                                                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                    .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(jLabel39)
                                                                                                        .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                    .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(jLabel26)
                                                                                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

                                                                                        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel15.setText("Preventas pendiente de pago, cargar?");

                                                                                        btnbuscar5.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnbuscar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Recordatorios de citas-32.png"))); // NOI18N
                                                                                        btnbuscar5.setContentAreaFilled(false);
                                                                                        btnbuscar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnbuscar5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                                        btnbuscar5.setIconTextGap(30);
                                                                                        btnbuscar5.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnbuscar5ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                                                                                        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel36.setText("00");

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
                                                                                                .addGap(14, 14, 14)
                                                                                                .addGroup(panelPreventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel15)
                                                                                                    .addComponent(jLabel36))
                                                                                                .addContainerGap(15, Short.MAX_VALUE))
                                                                                            .addComponent(btnbuscar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        );

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
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

                                                                                        Mensaje4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        Mensaje4.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        Mensaje4.setText("Cancelar la venta ?");

                                                                                        btnCorrectoSi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        btnCorrectoSi.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnCorrectoSi.setText("Si");
                                                                                        btnCorrectoSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                                        btnCorrectoSi.setContentAreaFilled(false);
                                                                                        btnCorrectoSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnCorrectoSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                                        btnCorrectoSi.setIconTextGap(30);
                                                                                        btnCorrectoSi.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnCorrectoSiActionPerformed(evt);
                                                                                            }
                                                                                        });
                                                                                        btnCorrectoSi.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                btnCorrectoSiKeyPressed(evt);
                                                                                            }
                                                                                        });

                                                                                        btnCorrectoNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        btnCorrectoNo.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnCorrectoNo.setText("No");
                                                                                        btnCorrectoNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                                        btnCorrectoNo.setContentAreaFilled(false);
                                                                                        btnCorrectoNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnCorrectoNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                                        btnCorrectoNo.setIconTextGap(30);
                                                                                        btnCorrectoNo.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnCorrectoNoActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout panelMensajeLayout = new javax.swing.GroupLayout(panelMensaje);
                                                                                        panelMensaje.setLayout(panelMensajeLayout);
                                                                                        panelMensajeLayout.setHorizontalGroup(
                                                                                            panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelMensajeLayout.createSequentialGroup()
                                                                                                .addGap(16, 16, 16)
                                                                                                .addComponent(Mensaje4)
                                                                                                .addGap(43, 43, 43)
                                                                                                .addComponent(btnCorrectoSi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(btnCorrectoNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelMensajeLayout.setVerticalGroup(
                                                                                            panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMensajeLayout.createSequentialGroup()
                                                                                                .addContainerGap(14, Short.MAX_VALUE)
                                                                                                .addGroup(panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(Mensaje4)
                                                                                                    .addComponent(btnCorrectoSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(btnCorrectoNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

                                                                                        panelColorFp.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        javax.swing.GroupLayout panelColorFpLayout = new javax.swing.GroupLayout(panelColorFp);
                                                                                        panelColorFp.setLayout(panelColorFpLayout);
                                                                                        panelColorFpLayout.setHorizontalGroup(
                                                                                            panelColorFpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 5, Short.MAX_VALUE)
                                                                                        );
                                                                                        panelColorFpLayout.setVerticalGroup(
                                                                                            panelColorFpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                                        );

                                                                                        javax.swing.GroupLayout panelFormaPagoLayout = new javax.swing.GroupLayout(panelFormaPago);
                                                                                        panelFormaPago.setLayout(panelFormaPagoLayout);
                                                                                        panelFormaPagoLayout.setHorizontalGroup(
                                                                                            panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                                                                                .addComponent(panelColorFp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(txtFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(btnBuscarFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(3, 3, 3))
                                                                                        );
                                                                                        panelFormaPagoLayout.setVerticalGroup(
                                                                                            panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                                                                                .addGap(0, 0, 0)
                                                                                                .addGroup(panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addComponent(panelColorFp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

                                                                                        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        lblDescripcion.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        lblDescripcion.setText("Nueva Venta");

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
                                                                                                .addContainerGap()
                                                                                                .addComponent(txtCPT)
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

                                                                                        btnEliminarDetalle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        btnEliminarDetalle.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnEliminarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                                                                                        btnEliminarDetalle.setText("Eliminar Este Registro ?");
                                                                                        btnEliminarDetalle.setContentAreaFilled(false);
                                                                                        btnEliminarDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnEliminarDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                                                        btnEliminarDetalle.setIconTextGap(30);
                                                                                        btnEliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnEliminarDetalleActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        noeli5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

                                                                                        eli5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

                                                                                        javax.swing.GroupLayout panelEliminacionLayout = new javax.swing.GroupLayout(panelEliminacion);
                                                                                        panelEliminacion.setLayout(panelEliminacionLayout);
                                                                                        panelEliminacionLayout.setHorizontalGroup(
                                                                                            panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelEliminacionLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(btnEliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(eli5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(noeli5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelEliminacionLayout.setVerticalGroup(
                                                                                            panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelEliminacionLayout.createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addGroup(panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(btnEliminarDetalle)
                                                                                                    .addComponent(eli5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(noeli5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(6, 6, 6))
                                                                                        );

                                                                                        panelAnular.setBackground(new java.awt.Color(241, 197, 14));
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
                                                                                        btnbuscar9.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        btnbuscar9.setContentAreaFilled(false);
                                                                                        btnbuscar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnbuscar9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                                        btnbuscar9.setIconTextGap(30);
                                                                                        btnbuscar9.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnbuscar9ActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        jLabel38.setText("Esta venta esta tiene varios detalles");

                                                                                        btnTerminiarVenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                                        btnTerminiarVenta.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        btnTerminiarVenta.setText("Terminar y empezar una nueva venta");
                                                                                        btnTerminiarVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
                                                                                        btnTerminiarVenta.setContentAreaFilled(false);
                                                                                        btnTerminiarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnTerminiarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                                        btnTerminiarVenta.setIconTextGap(30);
                                                                                        btnTerminiarVenta.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnTerminiarVentaActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        jPanel17.setBackground(new java.awt.Color(255, 91, 70));
                                                                                        jPanel17.setPreferredSize(new java.awt.Dimension(125, 25));

                                                                                        btnAnularVenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                                        btnAnularVenta.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnAnularVenta.setText("Anular");
                                                                                        btnAnularVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
                                                                                        btnAnularVenta.setContentAreaFilled(false);
                                                                                        btnAnularVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnAnularVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                                        btnAnularVenta.setIconTextGap(30);
                                                                                        btnAnularVenta.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnAnularVentaActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                                                                                        jPanel17.setLayout(jPanel17Layout);
                                                                                        jPanel17Layout.setHorizontalGroup(
                                                                                            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(btnAnularVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );
                                                                                        jPanel17Layout.setVerticalGroup(
                                                                                            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(btnAnularVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                                                                        );

                                                                                        txtEnterEscapeEnter.setEditable(false);
                                                                                        txtEnterEscapeEnter.setBackground(new java.awt.Color(241, 197, 14));
                                                                                        txtEnterEscapeEnter.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
                                                                                        txtEnterEscapeEnter.setForeground(new java.awt.Color(241, 197, 14));
                                                                                        txtEnterEscapeEnter.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        txtEnterEscapeEnter.setCaretColor(new java.awt.Color(102, 102, 102));
                                                                                        txtEnterEscapeEnter.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                txtEnterEscapeEnterKeyPressed(evt);
                                                                                            }
                                                                                        });

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
                                                                                                .addComponent(btnTerminiarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(26, 26, 26)
                                                                                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(txtEnterEscapeEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        );
                                                                                        panelAnularLayout.setVerticalGroup(
                                                                                            panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(btnbuscar9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addGroup(panelAnularLayout.createSequentialGroup()
                                                                                                .addGap(14, 14, 14)
                                                                                                .addGroup(panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                                                                                    .addGroup(panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(jLabel38)
                                                                                                        .addComponent(btnTerminiarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                    .addComponent(txtEnterEscapeEnter))
                                                                                                .addContainerGap(12, Short.MAX_VALUE))
                                                                                        );

                                                                                        panelEliminar.setBackground(new java.awt.Color(255, 91, 70));

                                                                                        Mensaje5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        Mensaje5.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        Mensaje5.setText("Cancelar la venta ?");

                                                                                        btnEliminarSi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        btnEliminarSi.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnEliminarSi.setText("Si");
                                                                                        btnEliminarSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                                        btnEliminarSi.setContentAreaFilled(false);
                                                                                        btnEliminarSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnEliminarSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                                        btnEliminarSi.setIconTextGap(30);
                                                                                        btnEliminarSi.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnEliminarSiActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        btnEliminarNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        btnEliminarNo.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnEliminarNo.setText("No");
                                                                                        btnEliminarNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                                                        btnEliminarNo.setContentAreaFilled(false);
                                                                                        btnEliminarNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                                                        btnEliminarNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                                                        btnEliminarNo.setIconTextGap(30);
                                                                                        btnEliminarNo.addActionListener(new java.awt.event.ActionListener() {
                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                btnEliminarNoActionPerformed(evt);
                                                                                            }
                                                                                        });

                                                                                        txtEnterEscapeEnter1.setEditable(false);
                                                                                        txtEnterEscapeEnter1.setBackground(new java.awt.Color(255, 91, 70));
                                                                                        txtEnterEscapeEnter1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
                                                                                        txtEnterEscapeEnter1.setForeground(new java.awt.Color(255, 91, 70));
                                                                                        txtEnterEscapeEnter1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        txtEnterEscapeEnter1.setCaretColor(new java.awt.Color(102, 102, 102));
                                                                                        txtEnterEscapeEnter1.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                txtEnterEscapeEnter1KeyPressed(evt);
                                                                                            }
                                                                                        });

                                                                                        javax.swing.GroupLayout panelEliminarLayout = new javax.swing.GroupLayout(panelEliminar);
                                                                                        panelEliminar.setLayout(panelEliminarLayout);
                                                                                        panelEliminarLayout.setHorizontalGroup(
                                                                                            panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelEliminarLayout.createSequentialGroup()
                                                                                                .addGap(16, 16, 16)
                                                                                                .addComponent(Mensaje5)
                                                                                                .addGap(43, 43, 43)
                                                                                                .addComponent(btnEliminarSi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(btnEliminarNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(txtEnterEscapeEnter1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(98, 98, 98))
                                                                                        );
                                                                                        panelEliminarLayout.setVerticalGroup(
                                                                                            panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelEliminarLayout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEliminarLayout.createSequentialGroup()
                                                                                                        .addGap(0, 3, Short.MAX_VALUE)
                                                                                                        .addGroup(panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(Mensaje5)
                                                                                                            .addComponent(btnEliminarSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(btnEliminarNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                    .addComponent(txtEnterEscapeEnter1, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                                                .addContainerGap())
                                                                                        );

                                                                                        lblFua.setText("jLabel42");

                                                                                        lblServicio.setBackground(new java.awt.Color(255, 102, 102));
                                                                                        lblServicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                                        lblServicio.setForeground(new java.awt.Color(255, 51, 153));
                                                                                        lblServicio.setText("8");

                                                                                        panelAbonos.setBackground(new java.awt.Color(51, 51, 0));

                                                                                        javax.swing.GroupLayout panelAbonosLayout = new javax.swing.GroupLayout(panelAbonos);
                                                                                        panelAbonos.setLayout(panelAbonosLayout);
                                                                                        panelAbonosLayout.setHorizontalGroup(
                                                                                            panelAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 14, Short.MAX_VALUE)
                                                                                        );
                                                                                        panelAbonosLayout.setVerticalGroup(
                                                                                            panelAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 8, Short.MAX_VALUE)
                                                                                        );

                                                                                        lblArea.setForeground(new java.awt.Color(204, 204, 0));
                                                                                        lblArea.setText("jLabel57");

                                                                                        lblAbonos.setText("jLabel56");

                                                                                        lblFormaPago.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        lblFormaPago.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        lblFormaPago.setText("jLabel8");

                                                                                        lblIdPreventas.setText("000");

                                                                                        nom.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        nom.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        nom.setText("jLabel8");

                                                                                        lblSinAnulacion.setText("jLabel41");

                                                                                        lblModuloHos.setText("MHOS");

                                                                                        lblActoMedicoNuevo.setText("n acto m");

                                                                                        lblHc.setText("Numero de Documento");

                                                                                        lblIdMedico.setText("   ");

                                                                                        sexo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                                        sexo.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        sexo.setBorder(null);
                                                                                        sexo.setEnabled(false);

                                                                                        lblCajaNomenclaturaPrecio.setForeground(new java.awt.Color(204, 0, 204));
                                                                                        lblCajaNomenclaturaPrecio.setText("nomenclatura pre");

                                                                                        lblFP.setText("jLabel30");

                                                                                        lblOk.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        lblOk.setText("ok");

                                                                                        lblModulo.setText("modulo");

                                                                                        lblcodigo.setText("Numero de Documento");

                                                                                        txtedad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                                                        txtedad.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        txtedad.setBorder(null);
                                                                                        txtedad.setEnabled(false);

                                                                                        txtHC.setText("jLabel1");

                                                                                        ca_id.setText("0");

                                                                                        AMA.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        AMA.setText("AMA");

                                                                                        lblVisAdmi.setText("jLabel1");

                                                                                        lblIdDetalle.setText("jLabel1");

                                                                                        lblPreventa.setText("Preventa");

                                                                                        lblServicioArea.setText("jLabel40");

                                                                                        lblNumero.setForeground(new java.awt.Color(255, 51, 51));
                                                                                        lblNumero.setText("jLabel60");

                                                                                        lblNumerosAdicional.setForeground(new java.awt.Color(153, 0, 153));
                                                                                        lblNumerosAdicional.setText("jLabel69");

                                                                                        lblNumeroFuturo.setForeground(new java.awt.Color(0, 102, 102));
                                                                                        lblNumeroFuturo.setText("jLabel69");

                                                                                        lblCAid.setText("jLabel69");

                                                                                        lblContador.setForeground(new java.awt.Color(255, 51, 51));
                                                                                        lblContador.setText("jLabel69");

                                                                                        lblContadorF.setForeground(new java.awt.Color(0, 102, 102));
                                                                                        lblContadorF.setText("jLabel69");

                                                                                        lblNumeroTotal.setForeground(new java.awt.Color(255, 51, 51));
                                                                                        lblNumeroTotal.setText("jLabel69");

                                                                                        lblContadorA.setForeground(new java.awt.Color(153, 0, 153));
                                                                                        lblContadorA.setText("jLabel69");

                                                                                        lblNumeroAF.setForeground(new java.awt.Color(153, 0, 153));
                                                                                        lblNumeroAF.setText("jLabel70");

                                                                                        lblNumeroFT.setForeground(new java.awt.Color(0, 102, 102));
                                                                                        lblNumeroFT.setText("jLabel71");

                                                                                        lblMedicoId.setText("jLabel45");

                                                                                        lblMantP.setText("PD?");

                                                                                        lblId_EmpresaFP.setText("CJ0001");

                                                                                        lblPorcentaje.setText("0%");

                                                                                        lblUsuPorcentaje.setText("Sin Exoneracion");

                                                                                        lblSerie.setText("SERIE");

                                                                                        lblNumDoc.setText("jLabel93");

                                                                                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                                                                        jPanel1.setLayout(jPanel1Layout);
                                                                                        jPanel1Layout.setHorizontalGroup(
                                                                                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblPreventa)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblVisAdmi)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(lblId_EmpresaFP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblIdDetalle)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                .addComponent(lblServicioArea)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(txtHC)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblArea)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblCajaNomenclaturaPrecio))
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(ca_id)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(lblCAid)
                                                                                                                .addGap(2, 2, 2)
                                                                                                                .addComponent(lblNumero)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(lblNumerosAdicional)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(lblNumDoc)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(lblSerie)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(lblNumeroFuturo)
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblIdPreventas)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblIdMedico))
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblOk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(lblMantP)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(lblMedicoId)))
                                                                                                        .addGap(18, 18, 18)
                                                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblModuloHos)
                                                                                                                .addGap(5, 5, 5)
                                                                                                                .addComponent(lblModulo)
                                                                                                                .addGap(34, 34, 34)
                                                                                                                .addComponent(lblActoMedicoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(lblAbonos)
                                                                                                                .addGap(10, 10, 10)
                                                                                                                .addComponent(lblServicio)
                                                                                                                .addGap(32, 32, 32)
                                                                                                                .addComponent(lblSinAnulacion)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblFP)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(lblFua))
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblcodigo)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(lblHc)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblFormaPago)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(nom)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(panelAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                .addComponent(AMA))))
                                                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblContador)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(lblContadorF)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblNumeroTotal)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(lblContadorA)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblNumeroAF)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(lblNumeroFT))
                                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblPorcentaje)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(lblUsuPorcentaje)))
                                                                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                                                                .addContainerGap())
                                                                                        );
                                                                                        jPanel1Layout.setVerticalGroup(
                                                                                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, 0)
                                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(lblPreventa))
                                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(ca_id)
                                                                                                                .addComponent(lblOk)
                                                                                                                .addComponent(lblcodigo)
                                                                                                                .addComponent(lblHc)
                                                                                                                .addComponent(lblFormaPago)
                                                                                                                .addComponent(nom)
                                                                                                                .addComponent(lblNumero)
                                                                                                                .addComponent(lblNumerosAdicional)
                                                                                                                .addComponent(lblNumeroFuturo)
                                                                                                                .addComponent(lblCAid)
                                                                                                                .addComponent(lblMedicoId)
                                                                                                                .addComponent(lblMantP)
                                                                                                                .addComponent(lblSerie)
                                                                                                                .addComponent(lblNumDoc)))
                                                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                                                            .addGap(9, 9, 9)
                                                                                                            .addComponent(panelAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                    .addComponent(AMA))
                                                                                                .addGap(5, 5, 5)
                                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(lblContador)
                                                                                                    .addComponent(lblContadorF)
                                                                                                    .addComponent(lblNumeroTotal)
                                                                                                    .addComponent(lblContadorA)
                                                                                                    .addComponent(lblNumeroAF)
                                                                                                    .addComponent(lblNumeroFT))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                            .addComponent(lblArea)
                                                                                                            .addComponent(lblCajaNomenclaturaPrecio)
                                                                                                            .addComponent(lblIdPreventas)
                                                                                                            .addComponent(lblIdMedico)
                                                                                                            .addComponent(lblModuloHos)
                                                                                                            .addComponent(lblModulo)
                                                                                                            .addComponent(lblActoMedicoNuevo)
                                                                                                            .addComponent(lblAbonos)
                                                                                                            .addComponent(txtHC)
                                                                                                            .addComponent(lblSinAnulacion)
                                                                                                            .addComponent(lblFP)
                                                                                                            .addComponent(lblFua)
                                                                                                            .addComponent(lblServicio)
                                                                                                            .addComponent(lblVisAdmi)
                                                                                                            .addComponent(lblIdDetalle)
                                                                                                            .addComponent(lblServicioArea))
                                                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                                                    .addComponent(lblId_EmpresaFP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(lblPorcentaje)
                                                                                                    .addComponent(lblUsuPorcentaje)))
                                                                                        );

                                                                                        panelTablaCPT.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel45.setText("Precio");

                                                                                        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel77.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel77.setText("Nº Atención");

                                                                                        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel75.setText("Departamento / Área");

                                                                                        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                                                                                        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                                                                                        tb_CPT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        tb_CPT.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        tb_CPT.setModel(new javax.swing.table.DefaultTableModel(
                                                                                            new Object [][] {
                                                                                                {null, null, null, null},
                                                                                                {null, null, null, null},
                                                                                                {null, null, null, null},
                                                                                                {null, null, null, null}
                                                                                            },
                                                                                            new String [] {
                                                                                                "Título 1", "Título 2", "Título 3", "Título 4"
                                                                                            }
                                                                                        ));
                                                                                        tb_CPT.setGridColor(new java.awt.Color(255, 255, 255));
                                                                                        tb_CPT.setRowHeight(25);
                                                                                        tb_CPT.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                                                        tb_CPT.getTableHeader().setReorderingAllowed(false);
                                                                                        tb_CPT.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                tb_CPTMouseClicked(evt);
                                                                                            }
                                                                                        });
                                                                                        tb_CPT.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                tb_CPTKeyPressed(evt);
                                                                                            }
                                                                                        });
                                                                                        jScrollPane4.setViewportView(tb_CPT);

                                                                                        jPanel36.setBackground(new java.awt.Color(41, 127, 184));
                                                                                        jPanel36.setPreferredSize(new java.awt.Dimension(0, 2));

                                                                                        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
                                                                                        jPanel36.setLayout(jPanel36Layout);
                                                                                        jPanel36Layout.setHorizontalGroup(
                                                                                            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel36Layout.setVerticalGroup(
                                                                                            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                                                        );

                                                                                        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel74.setText(" CPT");

                                                                                        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
                                                                                        jLabel76.setText("Personal");

                                                                                        javax.swing.GroupLayout panelTablaCPTLayout = new javax.swing.GroupLayout(panelTablaCPT);
                                                                                        panelTablaCPT.setLayout(panelTablaCPTLayout);
                                                                                        panelTablaCPTLayout.setHorizontalGroup(
                                                                                            panelTablaCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelTablaCPTLayout.createSequentialGroup()
                                                                                                .addGap(0, 0, 0)
                                                                                                .addGroup(panelTablaCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
                                                                                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addGroup(panelTablaCPTLayout.createSequentialGroup()
                                                                                                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(363, 363, 363)
                                                                                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(29, 29, 29)
                                                                                                        .addComponent(jLabel75)
                                                                                                        .addGap(73, 73, 73)
                                                                                                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(27, 27, 27)
                                                                                                        .addComponent(jLabel77)))
                                                                                                .addContainerGap())
                                                                                        );
                                                                                        panelTablaCPTLayout.setVerticalGroup(
                                                                                            panelTablaCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelTablaCPTLayout.createSequentialGroup()
                                                                                                .addGap(0, 0, 0)
                                                                                                .addGroup(panelTablaCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addGroup(panelTablaCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                                                                                        );

                                                                                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                                                                        jPanel3.setLayout(jPanel3Layout);
                                                                                        jPanel3Layout.setHorizontalGroup(
                                                                                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(panelEliminacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelPreventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelDatosGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(panelActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                                .addGap(19, 19, 19)
                                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                    .addComponent(lTipoDoc)
                                                                                                                    .addComponent(lForma)
                                                                                                                    .addComponent(lblCPT)
                                                                                                                    .addComponent(lDoc))
                                                                                                                .addGap(24, 24, 24)
                                                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                    .addComponent(lblNumeroDoc)
                                                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                                        .addComponent(panelCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                        .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addComponent(panelFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                                                .addGap(16, 16, 16)
                                                                                                                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                                                    .addComponent(panelNumeros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                    .addComponent(panelTablaCPT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addContainerGap())
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
                                                                                                .addComponent(panelPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(lblDescripcion)
                                                                                                .addGap(8, 8, 8)
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
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(panelTablaCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(panelEliminacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(panelNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap())
                                                                                        );

                                                                                        jTabbedPane1.addTab("...", jPanel3);

                                                                                        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                                                                                        jScrollPane11.setBackground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane11.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                                                        jScrollPane11.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jScrollPane11.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                                                                                        tb_ReporteDiario.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        tb_ReporteDiario.setModel(new javax.swing.table.DefaultTableModel(
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
                                                                                        tb_ReporteDiario.setGridColor(new java.awt.Color(255, 255, 255));
                                                                                        tb_ReporteDiario.setRowHeight(25);
                                                                                        tb_ReporteDiario.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                                                        tb_ReporteDiario.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                tb_ReporteDiarioMouseClicked(evt);
                                                                                            }
                                                                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                                                                tb_ReporteDiarioMousePressed(evt);
                                                                                            }
                                                                                        });
                                                                                        tb_ReporteDiario.addKeyListener(new java.awt.event.KeyAdapter() {
                                                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                                                tb_ReporteDiarioKeyPressed(evt);
                                                                                            }
                                                                                        });
                                                                                        jScrollPane11.setViewportView(tb_ReporteDiario);

                                                                                        resumen1.setBackground(new java.awt.Color(43, 43, 43));
                                                                                        resumen1.setForeground(new java.awt.Color(43, 43, 43));
                                                                                        resumen1.setPreferredSize(new java.awt.Dimension(610, 113));

                                                                                        lblTotalPendiente.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        lblTotalPendiente.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        lblTotalPendiente.setText("S./ 608.70");

                                                                                        lblTotalContado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        lblTotalContado.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        lblTotalContado.setText("S./ 500.00");

                                                                                        jLabel71.setBackground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        jLabel71.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel71.setText("Total de Ventas");

                                                                                        lblTotalDiario.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        lblTotalDiario.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        lblTotalDiario.setText("25");

                                                                                        APENOM1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        APENOM1.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        APENOM1.setText("Ventas de Hoy");

                                                                                        jLabel72.setBackground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        jLabel72.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel72.setText("Ventas por Contado");

                                                                                        jLabel73.setBackground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        jLabel73.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel73.setText("Ventas Pendientes de Pago");

                                                                                        jLabel84.setBackground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        jLabel84.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel84.setText("Total Anulado");

                                                                                        lblTotalAnulado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                                                        lblTotalAnulado.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        lblTotalAnulado.setText("25");

                                                                                        jPanel6.setBackground(new java.awt.Color(43, 43, 43));

                                                                                        ChkAnalf1.setEditable(false);
                                                                                        ChkAnalf1.setBackground(new java.awt.Color(50, 151, 219));
                                                                                        ChkAnalf1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        ChkAnalf1.setForeground(new java.awt.Color(102, 102, 102));
                                                                                        ChkAnalf1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        ChkAnalf1.setBorder(null);
                                                                                        ChkAnalf1.setPreferredSize(new java.awt.Dimension(20, 20));
                                                                                        ChkAnalf1.setSelectionColor(new java.awt.Color(255, 204, 51));
                                                                                        ChkAnalf1.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                ChkAnalf1CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        ChkAnalf1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                ChkAnalf1MouseClicked(evt);
                                                                                            }
                                                                                        });

                                                                                        ChkEdad1.setEditable(false);
                                                                                        ChkEdad1.setBackground(new java.awt.Color(0, 153, 187));
                                                                                        ChkEdad1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        ChkEdad1.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        ChkEdad1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        ChkEdad1.setBorder(null);
                                                                                        ChkEdad1.setPreferredSize(new java.awt.Dimension(20, 20));
                                                                                        ChkEdad1.setSelectionColor(new java.awt.Color(255, 51, 51));
                                                                                        ChkEdad1.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                ChkEdad1CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        ChkEdad1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                ChkEdad1MouseClicked(evt);
                                                                                            }
                                                                                        });

                                                                                        jLabel85.setBackground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        jLabel85.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel85.setText("Otras Forma de pago");

                                                                                        jLabel86.setBackground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        jLabel86.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel86.setText("Ventas por Contado");

                                                                                        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                                                        jLabel87.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel87.setText("Leyenda");

                                                                                        ChkEdad2.setEditable(false);
                                                                                        ChkEdad2.setBackground(new java.awt.Color(255, 51, 51));
                                                                                        ChkEdad2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        ChkEdad2.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        ChkEdad2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                                                        ChkEdad2.setBorder(null);
                                                                                        ChkEdad2.setPreferredSize(new java.awt.Dimension(20, 20));
                                                                                        ChkEdad2.setSelectionColor(new java.awt.Color(255, 51, 51));
                                                                                        ChkEdad2.addCaretListener(new javax.swing.event.CaretListener() {
                                                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                                                ChkEdad2CaretUpdate(evt);
                                                                                            }
                                                                                        });
                                                                                        ChkEdad2.addMouseListener(new java.awt.event.MouseAdapter() {
                                                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                                                ChkEdad2MouseClicked(evt);
                                                                                            }
                                                                                        });

                                                                                        jLabel88.setBackground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                                                        jLabel88.setForeground(new java.awt.Color(204, 204, 204));
                                                                                        jLabel88.setText("Ventas Anuladas");

                                                                                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                                                                                        jPanel6.setLayout(jPanel6Layout);
                                                                                        jPanel6Layout.setHorizontalGroup(
                                                                                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                                                .addGap(28, 28, 28)
                                                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                                                                        .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(jLabel88))
                                                                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                                                                        .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(jLabel86))
                                                                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                                                                        .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(jLabel85))
                                                                                                    .addComponent(jLabel87))
                                                                                                .addContainerGap(16, Short.MAX_VALUE))
                                                                                        );
                                                                                        jPanel6Layout.setVerticalGroup(
                                                                                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                                                .addGap(4, 4, 4)
                                                                                                .addComponent(jLabel87)
                                                                                                .addGap(4, 4, 4)
                                                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel86))
                                                                                                .addGap(4, 4, 4)
                                                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(jLabel85)
                                                                                                    .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(4, 4, 4)
                                                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(jLabel88)))
                                                                                        );

                                                                                        javax.swing.GroupLayout resumen1Layout = new javax.swing.GroupLayout(resumen1);
                                                                                        resumen1.setLayout(resumen1Layout);
                                                                                        resumen1Layout.setHorizontalGroup(
                                                                                            resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(resumen1Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(APENOM1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addGroup(resumen1Layout.createSequentialGroup()
                                                                                                        .addGroup(resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addComponent(jLabel72)
                                                                                                            .addComponent(jLabel71)
                                                                                                            .addComponent(jLabel73))
                                                                                                        .addGap(24, 24, 24)
                                                                                                        .addGroup(resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addGroup(resumen1Layout.createSequentialGroup()
                                                                                                                .addComponent(lblTotalDiario)
                                                                                                                .addGap(179, 179, 179)
                                                                                                                .addComponent(jLabel84)
                                                                                                                .addGap(24, 24, 24)
                                                                                                                .addComponent(lblTotalAnulado))
                                                                                                            .addComponent(lblTotalContado)
                                                                                                            .addComponent(lblTotalPendiente))))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap())
                                                                                        );
                                                                                        resumen1Layout.setVerticalGroup(
                                                                                            resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resumen1Layout.createSequentialGroup()
                                                                                                .addGap(11, 11, 11)
                                                                                                .addComponent(APENOM1)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(jLabel84)
                                                                                                        .addComponent(lblTotalAnulado))
                                                                                                    .addGroup(resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(jLabel71)
                                                                                                        .addComponent(lblTotalDiario)))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(lblTotalContado)
                                                                                                    .addComponent(jLabel72))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(lblTotalPendiente)
                                                                                                    .addComponent(jLabel73))
                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        );

                                                                                        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                                                                        jPanel5.setLayout(jPanel5Layout);
                                                                                        jPanel5Layout.setHorizontalGroup(
                                                                                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(resumen1, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
                                                                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
                                                                                        );
                                                                                        jPanel5Layout.setVerticalGroup(
                                                                                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                                                                .addComponent(resumen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE))
                                                                                        );

                                                                                        jTabbedPane1.addTab("tab3", jPanel5);

                                                                                        jPanel21.setBackground(new java.awt.Color(41, 127, 184));

                                                                                        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                                                        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                                                                                        jLabel57.setText("<html>Ventas<span style=\"font-size:'14px'\"><br>Caja Central</br></span></html>");

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
                                                                                                .addComponent(txtBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                                                        lbldetalle.setText("DNI, Acto Meédico");

                                                                                        btnLista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                                                        btnLista.setForeground(new java.awt.Color(240, 240, 240));
                                                                                        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Orden de compra-32.png"))); // NOI18N
                                                                                        btnLista.setText("Ventas de Hoy");
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

                                                                                        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                                                                                        jPanel21.setLayout(jPanel21Layout);
                                                                                        jPanel21Layout.setHorizontalGroup(
                                                                                            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                                                                                            .addComponent(lbldetalle)))
                                                                                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                                                                                        .addContainerGap()
                                                                                                        .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addContainerGap(19, Short.MAX_VALUE))
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
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnLista)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        );

                                                                                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                                                                        getContentPane().setLayout(layout);
                                                                                        layout.setHorizontalGroup(
                                                                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, 0)
                                                                                                .addComponent(jTabbedPane1)
                                                                                                .addGap(0, 0, 0))
                                                                                        );
                                                                                        layout.setVerticalGroup(
                                                                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                                                                                        );

                                                                                        pack();
                                                                                    }// </editor-fold>//GEN-END:initComponents

    private void tb_BusquedaCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_BusquedaCabeceraMouseClicked
        int fila = tb_BusquedaCabecera.getSelectedRow(); 
        if(evt.getClickCount()==2){
            
            ACTM.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 11)));
            APENOM.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 5)));
            DNII.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 3)));
            HCI.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 4)));
            
            bus1.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 12)));
            Caja_NuevaVenta CNVRCCD = new  Caja_NuevaVenta();
            CNVRCCD.ReporteDiariocajaDetalleCC(bus1.getText(),tb_ReporteDetalle); 

         }


    }//GEN-LAST:event_tb_BusquedaCabeceraMouseClicked

    private void tb_BusquedaCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_BusquedaCabeceraMousePressed

    }//GEN-LAST:event_tb_BusquedaCabeceraMousePressed

    private void tb_BusquedaCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_BusquedaCabeceraKeyPressed

    }//GEN-LAST:event_tb_BusquedaCabeceraKeyPressed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        panelBuscarHC.setVisible(false);
             paneltablaHC.setVisible(true);
             panelSinHC.setVisible(false);
                  BuscarHC();
        

        if (tbPacientes.getRowCount() == 0){
            panelBuscarHC.setVisible(false);
             paneltablaHC.setVisible(false);
             panelSinHC.setVisible(true);
            }
          if (txtBuscarPaciente.getText().length()==0){
             panelBuscarHC.setVisible(true);
             paneltablaHC.setVisible(false);
             panelSinHC.setVisible(false);
        }

        
           
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void txtBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPacienteActionPerformed

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
       Caja_NuevaVenta CP= new Caja_NuevaVenta();
       Caja_NuevaVenta CP2= new Caja_NuevaVenta();
       Caja_NuevaVenta CP3= new Caja_NuevaVenta();
       Caja_NuevaVenta CPAM= new Caja_NuevaVenta();
       Caja_NuevaVenta PM= new Caja_NuevaVenta();
        int fila=tbPacientes.getSelectedRow();
        
       bus.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
       bus3.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
           PM.Preventamostrar(bus.getText(),tbpreventas);
           CPAM.Caja_Id_Preventa(bus.getText());
           CP.ConsultoriosExtPREVENTAListar(lblIdPreventa.getText());
           CP2.ConsultoriosExtPREVENTAListarCEX(lblIdPreventa.getText());
           CP3.ConsultoriosACTOMEDICO_EMERGENCIA(lblIdPreventa.getText());
           
            Abonnos();
        if(evt.getClickCount()==2){
            BHC.dispose();
            panelDatosGenerales.setVisible(true);
            panelActoMedico.setVisible(true);

            txtHC.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            lblHC.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            txtape.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));
            lblDireccion.setText(String.valueOf(tbPacientes.getValueAt(fila, 3)));    
            lblDNI.setText(String.valueOf(tbPacientes.getValueAt(fila, 0)));
            sexo.setText(String.valueOf(tbPacientes.getValueAt(fila, 4)));
            txtedad.setText(String.valueOf(tbPacientes.getValueAt(fila, 6)));
            lblHc.setText(String.valueOf(tbPacientes.getValueAt(fila, 7))); 
           
            
          ////////////////////////

	     jLabel36.setText(String.valueOf(tbpreventas.getRowCount()));
             

             if(this.tbpreventas.getRowCount()!=0  ){
//              panelPreventa.setVisible(true);
//              panelPreventa.requestFocus();
                lblPreventa.setText("SI");
             }
              if(this.tbpreventas.getRowCount()==0  ){
//              panelPreventa.setVisible(false);
                  lblPreventa.setText("NO");
             }
             
        if(this.ABONOS.getRowCount()!=0  ){
              panelAbonos.setVisible(true);
              lblAbonos.setText("Si");
          }
            if(this.ABONOS.getRowCount()==0  ){
             panelAbonos.setVisible(false);
             lblAbonos.setText("No");
             txtBuscarCPT.setText("");
             }
//            fua.setText(cnn1.idSIS());

            
            lTipoDoc.setVisible(true);
            cbxTipoDocumento.setVisible(true);    
            cbxTipoDocumento.showPopup();
            cbxTipoDocumento.requestFocus(true);

        }
    }//GEN-LAST:event_tbPacientesMouseClicked

    private void tbPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyPressed
        Caja_NuevaVenta CP= new Caja_NuevaVenta();
        Caja_NuevaVenta CP2= new Caja_NuevaVenta();
        Caja_NuevaVenta CPAM= new Caja_NuevaVenta();
        Caja_NuevaVenta PM= new Caja_NuevaVenta();
        int fila = tbPacientes.getSelectedRow();
        bus.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
        bus3.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
           PM.Preventamostrar(bus.getText(),tbpreventas);
           CPAM.Caja_Id_Preventa(bus.getText());
           CP.ConsultoriosExtPREVENTAListar(lblIdPreventa.getText());
           CP2.ConsultoriosExtPREVENTAListarCEX(lblIdPreventa.getText());
           
            Abonnos(); 
        char teclaPresionada = evt.getKeyChar();
        
        if(teclaPresionada==KeyEvent.VK_ENTER){
            
            
            BHC.dispose();
            panelDatosGenerales.setVisible(true);
            panelActoMedico.setVisible(true);

            txtHC.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            lblHC.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            txtape.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));
            lblDireccion.setText(String.valueOf(tbPacientes.getValueAt(fila, 3)));    
            lblDNI.setText(String.valueOf(tbPacientes.getValueAt(fila, 0)));
            sexo.setText(String.valueOf(tbPacientes.getValueAt(fila, 4)));
            txtedad.setText(String.valueOf(tbPacientes.getValueAt(fila, 6)));
            lblHc.setText(String.valueOf(tbPacientes.getValueAt(fila, 7))); 
           
            
          ////////////////////////

	     jLabel36.setText(String.valueOf(tbpreventas.getRowCount()));
             
            if(this.tbpreventas.getRowCount()!=0  ){
//              panelPreventa.setVisible(true);
//              panelPreventa.requestFocus();
                lblPreventa.setText("SI");
             }
              if(this.tbpreventas.getRowCount()==0  ){
//              panelPreventa.setVisible(false);
                  lblPreventa.setText("NO");
             }
             
        if(this.ABONOS.getRowCount()!=0  ){
              panelAbonos.setVisible(true);
              lblAbonos.setText("Si");
          }
            if(this.ABONOS.getRowCount()==0  ){
             panelAbonos.setVisible(false);
             lblAbonos.setText("No");
             txtBuscarCPT.setText("");
             txtBuscarCPT.requestFocus();
             }
//            fua.setText(cnn1.idSIS());

            
            lTipoDoc.setVisible(true);
            cbxTipoDocumento.setVisible(true);    
            cbxTipoDocumento.showPopup();
            cbxTipoDocumento.requestFocus(true);
            
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
            lblFP.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
           
            
            if(!txtFormaPago.getText().equals("EXONERACION")){
                Guardar();
                
                lblOk.setText("okk");
             if(txtFormaPago.getText().equalsIgnoreCase("SIS" ) || txtFormaPago.getText().equalsIgnoreCase("SIS CREDITOS"  )){
                 panelColorFp.setBackground(new Color(232,76,61)); 
             GuardarSIS();
             } 
             
             if(txtFormaPago.getText().equalsIgnoreCase("CONTADO" )){
                 panelColorFp.setBackground(new Color(41,127,184)); 
             } 
             
              if(!txtFormaPago.getText().equalsIgnoreCase("CONTADO" )&&!txtFormaPago.getText().equalsIgnoreCase("SIS" ) && !txtFormaPago.getText().equalsIgnoreCase("SIS CREDITOS"  )){
                 panelColorFp.setBackground(new Color(243,156,17)); 
             } 
            }  
            
            }else if(txtFormaPago.getText().equals("EXONERACION")){
                AsistenciaSocial.setVisible(true);

            }
            
           
            
            
           
    }//GEN-LAST:event_tb_Grupo3MouseClicked

    private void tb_Grupo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo3KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo3.getSelectedRow();
            
            Jerarquias.dispose();
            txtFormaPago.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
            lblFP.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
            
            if(!txtFormaPago.getText().equals("EXONERACION")){
                Guardar();
                
                lblOk.setText("okk");
             if(txtFormaPago.getText().equalsIgnoreCase("SIS" ) || txtFormaPago.getText().equalsIgnoreCase("SIS CREDITOS"  )){
                 panelColorFp.setBackground(new Color(232,76,61)); 
             GuardarSIS();
             } 
             
             if(txtFormaPago.getText().equalsIgnoreCase("CONTADO" )){
                 panelColorFp.setBackground(new Color(41,127,184)); 
             } 
             
              if(!txtFormaPago.getText().equalsIgnoreCase("CONTADO" )&&!txtFormaPago.getText().equalsIgnoreCase("SIS" ) && !txtFormaPago.getText().equalsIgnoreCase("SIS CREDITOS"  )){
                 panelColorFp.setBackground(new Color(243,156,17)); 
             }
            if(lblPreventa.getText().equals("NO")){
               nomenclaturas.setVisible(true);
               txtBuscarCPT.setText("");
               txtBuscarCPT.requestFocus();  
            } 
       
            
            }else if(txtFormaPago.getText().equals("EXONERACION")){
                AsistenciaSocial.setVisible(true);

            }  
           
            }  
    }//GEN-LAST:event_tb_Grupo3KeyPressed

    private void txtBuscarCPTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCPTCaretUpdate
          panelCargarCPT.setVisible(true);
          panelBuscar.setVisible(false);
          panelSinCPT.setVisible(false);
          Caja_NuevaVenta CNVCPT = new Caja_NuevaVenta();
          CNVCPT.BuscarCPT(txtBuscarCPT.getText(),txtFormaPago.getText(),tb_CPTBUSCAR);
          

        if (tb_CPTBUSCAR.getRowCount() == 0){
            panelSinCPT.setVisible(true);
            panelCargarCPT.setVisible(false);
            panelBuscar.setVisible(false);
            }
          if (txtBuscarCPT.getText().length()==0){
             panelBuscar.setVisible(true);
             panelCargarCPT.setVisible(false);
             panelSinCPT.setVisible(false);
        }
//          
    }//GEN-LAST:event_txtBuscarCPTCaretUpdate

    private void txtBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCPTActionPerformed

    private void tb_CPTBUSCARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTBUSCARMouseClicked
        int fila=tb_CPTBUSCAR.getSelectedRow();
        if(evt.getClickCount()==2){
            nomenclaturas.dispose();
            lblIdMedico.setText("");
            lblServicio.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 7)));
            lblCajaNomenclaturaPrecio.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 5)));
            lblServicioArea.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 9)));
            
            Caja_Documento_Detalle cnvd = new Caja_Documento_Detalle();
            lblVisAdmi.setText(cnvd.VisibleAdmin(lblCajaNomenclaturaPrecio.getText()));
            
            if(!lblVisAdmi.getText().equals("N")){
//                tb_medicos2.setDefaultRenderer(Object.class,new FormatoTablaCajaConsultorio());
                Caja_NuevaVenta CNV = new Caja_NuevaVenta();
                MedicosConsultorios.setVisible(true);
                CNV.listarMedicos1(lblServicio.getText(),tb_medicos1);
                txtBuscarMedicos.setText(null);
                txtBuscarMedicos.requestFocus();
            }else if(lblVisAdmi.getText().equals("N")){
                GuardarDetalle();
                panelNumeros.setVisible(true);
                suma();
            }
                    
            lblMantP.setText("CP");                   
    
           
            }
    }//GEN-LAST:event_tb_CPTBUSCARMouseClicked

    private void tb_CPTBUSCARKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTBUSCARKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_CPTBUSCAR.getSelectedRow();
            nomenclaturas.dispose();
            lblIdMedico.setText("");
            lblServicio.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 7)));
            lblCajaNomenclaturaPrecio.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 5)));
            lblServicioArea.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 9)));
            
            Caja_Documento_Detalle cnvd = new Caja_Documento_Detalle();
            lblVisAdmi.setText(cnvd.VisibleAdmin(lblCajaNomenclaturaPrecio.getText()));
            
            if(!lblVisAdmi.getText().equals("N")){
//                tb_medicos2.setDefaultRenderer(Object.class,new FormatoTablaCajaConsultorio());
                Caja_NuevaVenta CNV = new Caja_NuevaVenta();
                MedicosConsultorios.setVisible(true);
                CNV.listarMedicos1(lblServicio.getText(),tb_medicos1);
            }else if(lblVisAdmi.getText().equals("N")){
                GuardarDetalle();
                panelNumeros.setVisible(true);
                suma();
            }
            lblMantP.setText("CP");        
            }
    }//GEN-LAST:event_tb_CPTBUSCARKeyPressed
  private void suma()
    {
        double total = 0;
        double IGV=0;
        double subtotal=0;
        double descuento =0;
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
        IGV=total*0.18;
        subtotal=total-IGV;
        descuento=0;
        //muestra en el componente
        this.txtTotal.setText( String.valueOf(total) );
        this.txtIGV.setText(String.valueOf(IGV));
        this.txtSubTotal.setText(String.valueOf((subtotal)));
        this.txtDescuento.setText(String.valueOf(descuento));
        ActualizarVenta();
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
       lblCajaNomenclaturaPrecio.setText(String.valueOf(tEME.getValueAt(fila, 11)));  
       lblArea.setText(String.valueOf(tEME.getValueAt(fila, 12))); 
       lblIdPreventas.setText(String.valueOf(tEME.getValueAt(fila, 0))); 
       //////////////////////ANULAR
       lblModuloHos.setText(String.valueOf(tEME.getValueAt(fila, 4))); 
       ////////////////////////////////////////////////////////
      
     } 
 
        lblActoMedico.setText((cpam.Ultima_CEX(lblHc.getText())));
        btnCargarEME.setVisible(true);
        btnEliminarEME.setVisible(true);
        tgp=1;
        ////////////////////////////////////////
             if(lblActoMedico.getText()==""){
       lblActoMedico.setText(lblActoMedicoNuevo.getText());    
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
          Caja_NuevaVenta CNV = new Caja_NuevaVenta();
          lblServicio.setText(CSE.CodSE(lblArea.getText()));
       
//        Medicos.setVisible(true);
//        listarMedicos1();
        
//        MedicosConsultorios.setVisible(true);
//        txtBuscarMedicos.setText(null);
//        txtBuscarMedicos.requestFocus();
//        CNV.listarMedicos1(lblServicio.getText(),tb_medicos1);
        lblMantP.setText("Pr");
        
        
        Medicos.setVisible(true);
        CNV.listarMedicos1(lblServicio.getText(),tb_medicos);
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

    private void txtBuscarCPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCPTKeyPressed
        char teclaPresionada = evt.getKeyChar();
    
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_CPTBUSCAR.getSelectionModel().setSelectionInterval (0,0) ;
          tb_CPTBUSCAR.requestFocus();
        
         
           
          } 
    }//GEN-LAST:event_txtBuscarCPTKeyPressed

    private void txtBuscarFormaPagoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarFormaPagoCaretUpdate
       BuscarJ();
    }//GEN-LAST:event_txtBuscarFormaPagoCaretUpdate

    private void txtBuscarFormaPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarFormaPagoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFormaPagoMouseClicked

    private void txtBuscarFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarFormaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFormaPagoActionPerformed

    private void txtBuscarFormaPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarFormaPagoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo3.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo3.requestFocus();

          } 
    }//GEN-LAST:event_txtBuscarFormaPagoKeyPressed

    private void tb_ReporteDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDetalleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDetalleMouseClicked

    private void tb_ReporteDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDetalleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDetalleMousePressed

    private void tb_ReporteDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ReporteDetalleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDetalleKeyPressed

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
       lblCajaNomenclaturaPrecio.setText(String.valueOf(tHOS.getValueAt(fila, 13)));  
       lblArea.setText(String.valueOf(tHOS.getValueAt(fila, 17)));
       lblIdPreventas.setText(String.valueOf(tHOS.getValueAt(fila, 0)));  
       ca_id.setText(String.valueOf(tHOS.getValueAt(fila, 14))); 
       lblModulo.setText(String.valueOf(tHOS.getValueAt(fila, 6))); 
       //////////////////////ANULAR
       lblModuloHos.setText(String.valueOf(tHOS.getValueAt(fila, 4))); 
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
            lblCajaNomenclaturaPrecio.setText(String.valueOf(tHOSDET.getValueAt(filaD, 6)));  //NOMENCLATURA

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
        Caja_NuevaVenta CNV1 = new Caja_NuevaVenta();
        lblServicio.setText(CSE.CodSE(lblArea.getText()));
        AsignarCamas();
//        MedicosConsultorios.setVisible(true);
//        txtBuscarMedicos.setText(null);
//        txtBuscarMedicos.requestFocus();
//        CNV1.listarMedicos1(lblServicio.getText(),tb_medicos1);
        Medicos.setVisible(true);
        CNV1.listarMedicos1(lblServicio.getText(),tb_medicos);
        lblMantP.setText("Pr");
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
        jPanel41.setBackground(new Color(41,127,184));
        jPanel43.setBackground(new Color(41,127,184));
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel37KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel37KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37KeyPressed

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        jTabbedPane5.setSelectedIndex(1);
        
        jPanel41.setBackground(new Color(240,240,240)); 
        jPanel25.setBackground(new Color(41,127,184));
        jPanel43.setBackground(new Color(41,127,184));

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

    private void txtBuscarEmpresaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaCaretUpdate
        Caja_NuevaVenta CNVE = new Caja_NuevaVenta();  
//        CNV.listarMedicos(txtBuscarEmpresa.getText(),lblServicio.getText(),tb_Empresa);
        CNVE.listarEmpresa(txtBuscarEmpresa.getText(),tb_Empresa);

        if (tb_Empresa.getRowCount() == 0){
            jPanel51.setVisible(true);
            }
          if (txtBuscarEmpresa.getText().length()==0){
             jPanel51.setVisible(true);
        }
          
          
          
         
    }//GEN-LAST:event_txtBuscarEmpresaCaretUpdate

    private void txtBuscarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpresaActionPerformed

    private void txtBuscarEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpresaKeyPressed

    private void tb_EmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_EmpresaKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            Empresa.dispose();
            int fila = tb_Empresa.getSelectedRow();
            lblId_EmpresaFP.setText(String.valueOf(tb_Empresa.getValueAt(fila, 0)));
            
            lDoc.setVisible(true);
            lblNumeroDoc.setVisible(true);
            lForma.setVisible(true);
            panelFormaPago.setVisible(true);
            Jerarquias.setVisible(true);
            txtBuscarFormaPago.requestFocus();
          
        } 
        
    }//GEN-LAST:event_tb_EmpresaKeyPressed

    private void tb_EmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EmpresaMouseClicked
        int fila=tb_Empresa.getSelectedRow();
        if(evt.getClickCount()==2){
            Empresa.dispose();
            lblId_EmpresaFP.setText(String.valueOf(tb_Empresa.getValueAt(fila, 0)));
        }

      
    }//GEN-LAST:event_tb_EmpresaMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
jTabbedPane1.setSelectedIndex(1);
        panelMensaje.setVisible(false);
        if(lblOk.getText()=="okk" && this.tb_CPT.getRowCount()==0){ 
              panelEliminar.setVisible(true);

              tgnuevoEliminar=1;
         }  
   
   if(lblOk.getText()=="okk" && this.tb_CPT.getRowCount()!=0  ){

              panelAnular.setVisible(true);

   }else  if(lblOk.getText()=="ok")
   {
 

        NuevaV();
           
               
        LISTAR();
        formatoj();
        
       this.cbxTipoDocumento.setModel(tipo());
       this.CBXANULAR.setModel(anular());
   }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscar4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar4CaretUpdate
        
         
        
        if (txtBuscar4.getText()!=""){
            resumen.setVisible(true);
            jScrollPane3.setVisible(true);
            tb_BusquedaCabecera.setVisible(true);
            jScrollPane7.setVisible(true);
            tb_ReporteDetalle.setVisible(true);
            jTabbedPane1.setSelectedIndex(0); 
            
            JTableHeader th; 
            th = tb_BusquedaCabecera.getTableHeader(); 
            Font fuente = new Font("Segoe UI", Font.CENTER_BASELINE, 14); 
            th.setFont(fuente); 
            th.setForeground(new java.awt.Color(102,102,102));
        
        
            Caja_NuevaVenta CNVRCC = new  Caja_NuevaVenta();
            CNVRCC.ReporteDiariocajaCabeceraCC(txtBuscar4.getText(),tb_BusquedaCabecera);
   
            tb_BusquedaCabecera.getSelectionModel().setSelectionInterval (0,0) ;
// 
//            
//            /////////////////////////CABECERA 
            try {
            int fila = tb_BusquedaCabecera.getSelectedRow();
   
            ACTM.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 11)));
            APENOM.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 5)));
            DNII.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 3)));
            HCI.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 4)));
            
            bus1.setText(String.valueOf(tb_BusquedaCabecera.getValueAt(fila, 12)));
                
            } catch (Exception e) {
            }
            
            JTableHeader th1; 
            th1 = tb_ReporteDetalle.getTableHeader(); 
            Font fuente1 = new Font("Segoe UI", Font.CENTER_BASELINE, 14); 
            th1.setFont(fuente1); 
            th1.setForeground(new java.awt.Color(102,102,102));
            
            Caja_NuevaVenta CNVRCCD = new  Caja_NuevaVenta();
            CNVRCCD.ReporteDiariocajaDetalleCC(bus1.getText(),tb_ReporteDetalle); 
            ////////////////////////////////////////
          
            
        }if (txtBuscar4.getText().length()==0){
            resumen.setVisible(false);
  
            jScrollPane3.setVisible(false);
            tb_BusquedaCabecera.setVisible(false);
            jScrollPane7.setVisible(false);
            tb_ReporteDetalle.setVisible(false);
            
        }           

    }//GEN-LAST:event_txtBuscar4CaretUpdate

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if(this.tb_CPT.getRowCount()==0  ){
       
             panelMensaje.setVisible(true);
             btnCorrectoSi.setText("Si");
             btnCorrectoNo.setVisible(true);
             Mensaje4.setText("Cancelar la venta ?");
             tgm=1;
   }else if(this.tb_CPT.getRowCount()!=0  ){
    
             Anular.setVisible(true);
             btnCorrectoSi.setText("Si");
             btnCorrectoNo.setVisible(true);
            lblcod.setText(lblcodigo.getText()); 
   }   
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnCorrectoNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorrectoNoActionPerformed

        if(tge==4){
            panelMensaje.setVisible(false);
        }
    }//GEN-LAST:event_btnCorrectoNoActionPerformed

    private void btnCorrectoSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorrectoSiActionPerformed

         

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

    }//GEN-LAST:event_btnCorrectoSiActionPerformed

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
        CPCEX.ListarPreventasCEX(lblHc.getText(),tb_CEX);
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

    private void cbxTipoDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyTyped

    }//GEN-LAST:event_cbxTipoDocumentoKeyTyped

    private void cbxTipoDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyPressed
       if(!cbxTipoDocumento.getSelectedItem().toString().equalsIgnoreCase("FACTURA")){
           char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            lDoc.setVisible(true);
            lblNumeroDoc.setVisible(true);
            lForma.setVisible(true);
            panelFormaPago.setVisible(true);
            Jerarquias.setVisible(true);
            txtBuscarFormaPago.requestFocus();
        }       
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
        jPanel41.setBackground(new Color(41,127,184));
        jPanel25.setBackground(new Color(41,127,184));

    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel41KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel41KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel41KeyPressed

    private void txtFormaPagoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFormaPagoCaretUpdate
    
    }//GEN-LAST:event_txtFormaPagoCaretUpdate

    private void btnBuscarFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFormaPagoActionPerformed
        Jerarquias.setVisible(true);
        txtBuscarFormaPago.setText("");
    }//GEN-LAST:event_btnBuscarFormaPagoActionPerformed

    private void txtCPTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPTCaretUpdate
    
    }//GEN-LAST:event_txtCPTCaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
//         char teclaPresionada = evt.getKeyChar();
//        if(teclaPresionada==KeyEvent.VK_ENTER){
            nomenclaturas.setVisible(true);
            txtBuscarCPT.setText("");
            txtBuscarCPT.requestFocus();
//        }
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

    private void btnEliminarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDetalleActionPerformed

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

    private void btnTerminiarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminiarVentaActionPerformed
        lblCPT.setVisible(false);
        panelCPT.setVisible(false);
        panelTablaCPT.setVisible(false);
         
        lForma.setVisible(false);
        panelFormaPago.setVisible(false);
        lDoc.setVisible(false);
        lblNumeroDoc.setVisible(false);
        lTipoDoc.setVisible(false);
        cbxTipoDocumento.setVisible(false);
        panelAnular.setVisible(false);
        panelColorFp.setBackground(new Color(255,255,255)); 
        
        NuevaV();
    }//GEN-LAST:event_btnTerminiarVentaActionPerformed

    private void btnAnularVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularVentaActionPerformed
        Anular.setVisible(true);
    }//GEN-LAST:event_btnAnularVentaActionPerformed

    private void tb_CEXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CEXMouseClicked
       Caja_Preventa cpam = new Caja_Preventa(); 
       int fila=tb_CEX.getSelectedRow();
       if(evt.getClickCount()==1){
       lblCajaNomenclaturaPrecio.setText(String.valueOf(tb_CEX.getValueAt(fila, 9)));  
       lblArea.setText(String.valueOf(tb_CEX.getValueAt(fila, 11))); 
       lblIdPreventas.setText(String.valueOf(tb_CEX.getValueAt(fila, 0))); 
       //////////////////////ANULAR
       lblModuloHos.setText(String.valueOf(tb_CEX.getValueAt(fila, 4))); 
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
        lblServicio.setText(CSE.CodSE(lblArea.getText()));
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

    private void btnEliminarSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSiActionPerformed
           try{
                Caja_NuevaVenta ELIM = new Caja_NuevaVenta();
                ELIM.setId_documento(lblcodigo.getText());
                if(ELIM.eliminar()){
                    panelColorFp.setBackground(new Color(255,255,255)); 
                    System.out.println("ELIMINADO CABECERA");
                }

            }catch(Exception e){
                System.out.println("Error Eliminar" + e.toString());
            }

            panelEliminar.setVisible(false);
            NuevaV();
            LISTAR();
            formatoj();
            abono.setVisible(false);

    }//GEN-LAST:event_btnEliminarSiActionPerformed

    private void btnEliminarNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNoActionPerformed
        panelEliminar.setVisible(false);
    }//GEN-LAST:event_btnEliminarNoActionPerformed

    private void btnbuscar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar10ActionPerformed

    private void btnCorrectoSiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCorrectoSiKeyPressed
         char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            nomenclaturas.setVisible(true);
            txtBuscarCPT.setText("");
            txtBuscarCPT.requestFocus();
        }
    }//GEN-LAST:event_btnCorrectoSiKeyPressed

    private void txtEnterEscapeEnterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterEscapeEnterKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
             btnTerminiarVenta.doClick();
        }
    }//GEN-LAST:event_txtEnterEscapeEnterKeyPressed

    private void txtEnterEscapeEnter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterEscapeEnter1KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
             btnEliminarSi.doClick();
        }
    }//GEN-LAST:event_txtEnterEscapeEnter1KeyPressed

    private void txtT1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT1CaretUpdate

    private void txtT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT1MouseClicked
  
        if(txtT1.getText().equals("") && evt.getClickCount()==1){
           txtT1.setText("X");
           txtT2.setText("");
           txtT3.setText("");
           txtT4.setText("");
           panelExoneracion.setVisible(true);


        }else
        if(txtT1.getText().equals("X") && evt.getClickCount()==1){
           txtT1.setText(""); 
           txtT2.setText("");
           txtT3.setText("");
           txtT4.setText("");
           panelExoneracion.setVisible(false);

        }

    }//GEN-LAST:event_txtT1MouseClicked

    private void txtT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT1ActionPerformed

    private void txtT2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT2CaretUpdate

    private void txtT2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT2MouseClicked
         if(txtT2.getText().equals("") && evt.getClickCount()==1){
           txtT2.setText("X");
           txtT1.setText("");
           txtT3.setText("");
           txtT4.setText("");
           panelExoneracion.setVisible(true);
   

        }else
        if(txtT2.getText().equals("X") && evt.getClickCount()==1){
           txtT2.setText(""); 
           txtT1.setText("");
           txtT3.setText("");
           txtT4.setText("");
           panelExoneracion.setVisible(false);
       
        }
    }//GEN-LAST:event_txtT2MouseClicked

    private void txtT3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT3CaretUpdate

    private void txtT3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT3MouseClicked
         if(txtT3.getText().equals("") && evt.getClickCount()==1){
           txtT3.setText("X");
           txtT2.setText("");
           txtT1.setText("");
           txtT4.setText("");
           panelExoneracion.setVisible(true);
    

        }else
        if(txtT3.getText().equals("X") && evt.getClickCount()==1){
           txtT3.setText(""); 
           txtT2.setText("");
           txtT1.setText("");
           txtT4.setText("");
           panelExoneracion.setVisible(false);
 
        }
    }//GEN-LAST:event_txtT3MouseClicked

    private void txtT4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT4CaretUpdate

    private void txtT4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT4MouseClicked
         if(txtT4.getText().equals("") && evt.getClickCount()==1){
           txtT4.setText("X");
           txtT2.setText("");
           txtT3.setText("");
           txtT1.setText("");
           panelExoneracion.setVisible(true);

        }else
        if(txtT4.getText().equals("X") && evt.getClickCount()==1){
           txtT4.setText(""); 
           txtT2.setText("");
           txtT3.setText("");
           txtT1.setText("");
           panelExoneracion.setVisible(false);
        }
    }//GEN-LAST:event_txtT4MouseClicked

    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
        if(txtT1.getText().equals("X")){
           lblPorcentaje.setText("25 %");
        }else if(txtT2.getText().equals("X")){
           lblPorcentaje.setText("50 %");
        }else if(txtT3.getText().equals("X")){
           lblPorcentaje.setText("75 %");
        }else if(txtT4.getText().equals("X")){
           lblPorcentaje.setText("100 %");
        }
         AsistenciaSocial.dispose();
         Guardar();
         if(lblPreventa.getText().equals("NO")){
               nomenclaturas.setVisible(true);
               txtBuscarCPT.setText("");
               txtBuscarCPT.requestFocus();  
            }
        
            
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void tb_AsistenteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_AsistenteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_AsistenteKeyPressed

    private void tb_AsistenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_AsistenteMouseClicked
        int fila=tb_Asistente.getSelectedRow();
        if(evt.getClickCount()==2){
            lblUsuPorcentaje.setText(String.valueOf(tb_Asistente.getValueAt(fila, 0)));
            panelPorcentajes.setVisible(true);
            }
         if(evt.getClickCount()==1){
            lblUsuPorcentaje.setText(String.valueOf(tb_Asistente.getValueAt(fila, 0)));
            }
    }//GEN-LAST:event_tb_AsistenteMouseClicked

    private void txtBuscarMedicosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarMedicosCaretUpdate
        Caja_NuevaVenta CNV = new Caja_NuevaVenta();  
        CNV.listarMedicos(txtBuscarMedicos.getText(),lblServicio.getText(),tb_medicos1);


        if (tb_medicos1.getRowCount() == 0){
            panelSinMedico.setVisible(true);
            }
          if (txtBuscarEmpresa.getText().length()==0){
             panelSinMedico.setVisible(false);
        }
          
    }//GEN-LAST:event_txtBuscarMedicosCaretUpdate

    private void txtBuscarMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMedicosActionPerformed

    private void txtBuscarMedicosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedicosKeyPressed
         char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_medicos1.getSelectionModel().setSelectionInterval (0,0) ;
          tb_medicos1.requestFocus();
       
          } 
    }//GEN-LAST:event_txtBuscarMedicosKeyPressed

    private void tb_medicos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_medicos1MouseClicked
        int fila=tb_medicos1.getSelectedRow();
        if(evt.getClickCount()==1){
           Caja_NuevaVenta CNVLC = new Caja_NuevaVenta();
           lblMedicoApeNom.setText(String.valueOf(tb_medicos1.getValueAt(fila, 1)));
           lblIdMedico.setText(String.valueOf(tb_medicos1.getValueAt(fila, 1)));
           lblMedicoId.setText(String.valueOf(tb_medicos1.getValueAt(fila, 0)));
           CNVLC.ListarConsultorios(lblMedicoApeNom.getText(),tb_consultorios);
           panelTurnos.setVisible(true);
           panelDeatelleC.setVisible(true);
           panelSinConsultas.setVisible(false);

       }
        
    }//GEN-LAST:event_tb_medicos1MouseClicked

    private void tb_medicos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_medicos1KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
           int fila=tb_medicos1.getSelectedRow();
           Caja_NuevaVenta CNVLC = new Caja_NuevaVenta();
           lblMedicoApeNom.setText(String.valueOf(tb_medicos1.getValueAt(fila, 1)));
           lblIdMedico.setText(String.valueOf(tb_medicos1.getValueAt(fila, 1)));
           lblMedicoId.setText(String.valueOf(tb_medicos1.getValueAt(fila, 0)));
           CNVLC.ListarConsultorios(lblMedicoApeNom.getText(),tb_consultorios);
           panelTurnos.setVisible(true);
           panelDeatelleC.setVisible(true);
           panelSinConsultas.setVisible(false);
            
            
        }
    }//GEN-LAST:event_tb_medicos1KeyPressed

    private void tb_consultoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_consultoriosMouseClicked
 
        if(evt.getClickCount()==1){

            int fila = tb_consultorios.getSelectedRow();
            lblCAid.setText(String.valueOf(tb_consultorios.getValueAt(fila, 0)));
            
              lblContador.setText(String.valueOf(tb_consultorios.getValueAt(fila, 12)));
              lblNumero.setText(String.valueOf(tb_consultorios.getValueAt(fila, 15)));  
              lblNumeroTotal.setText(String.valueOf(tb_consultorios.getValueAt(fila, 18))); 
           
              lblContadorF.setText(String.valueOf(tb_consultorios.getValueAt(fila, 14)));
              lblNumeroFuturo.setText(String.valueOf(tb_consultorios.getValueAt(fila, 16))); 
              lblNumeroFT.setText(String.valueOf(tb_consultorios.getValueAt(fila, 20))); 
          
            
            lblNumerosAdicional.setText(String.valueOf(tb_consultorios.getValueAt(fila, 17)));
            lblNumeroAF.setText(String.valueOf(tb_consultorios.getValueAt(fila, 19)));
            lblContadorA.setText(String.valueOf(tb_consultorios.getValueAt(fila, 13)));
            
            
                    
            panelAsignacion.setVisible(true);

            if((lblNumeroFuturo.getText().equals("0"))&&(!lblNumero.getText().equals(lblNumeroTotal.getText()))){
                panelSinConsultas.setVisible(true);
                ///////////////////////////////
                txtT6.setVisible(false);
                jLabel64.setVisible(false);
                /////////////////////////////////
                
                lblCitaFutura.setText("Aun quedan citas para hoy");
                jLabel69.setText("Se agotaron las citas futuras");
            }else if((lblNumero.getText().equals("0"))&&((!lblNumeroFuturo.getText().equals(lblNumeroFT.getText()))||(lblNumeroFuturo.getText().equals(lblNumeroFT.getText())))&&(lblNumeroFuturo .getText().equals("0"))){
                panelSinConsultas.setVisible(true);
                ///////////////////////////////
                txtT5.setVisible(false);
                jLabel61.setVisible(false);
                /////////////////////////////////
                
                lblCitaFutura.setText("Aun quedan citas futuras");
                jLabel69.setText("Se agotaron las citas ");
            }else if((lblNumero.getText().equals("0"))&&((!lblNumeroFuturo.getText().equals(lblNumeroFT.getText()))||(lblNumeroFuturo.getText().equals(lblNumeroFT.getText())))&&(!lblNumeroFuturo .getText().equals("0"))){
                panelSinConsultas.setVisible(true);
                ///////////////////////////////
              
                
                /////////////////////////////////
                
                lblCitaFutura.setText("Aun quedan citas futuras");
                jLabel69.setText("Se agotaron las citas ");
            }
//            else if((lblNumero.getText().equals("0"))&&(lblNumeroFuturo.getText().equals("0"))){
//                panelSinConsultas.setVisible(true);
//                ///////////////////////////////
//                txtT6.setVisible(false);
//                jLabel64.setVisible(false);
//                /////////////////////////////////
//                
//                lblCitaFutura.setText("");
//                jLabel69.setText("Se agotaron las consultas");
            if((lblNumero.getText().equals("0"))&&(lblNumeroFuturo.getText().equals("0"))&&(!lblNumerosAdicional.getText().equals("0"))){
                panelSinConsultas.setVisible(true);
                ///////////////////////////////
                txtT6.setVisible(false);
                jLabel64.setVisible(false);
                /////////////////////////////////
                
                lblCitaFutura.setText("");
                jLabel69.setText("Se agotaron las consultas");
            }else if((lblNumero.getText().equals("0"))&&(lblNumeroFuturo.getText().equals("0"))&&(lblNumerosAdicional.getText().equals("0"))){
                panelSinConsultas.setVisible(true);
                ///////////////////////////////
                txtT6.setVisible(false);
                jLabel64.setVisible(false);
                /////////////////////////////////
                
                lblCitaFutura.setText("");
                jLabel70.setText("No hay adicionales");
                jLabel69.setText("Se agotaron las consultas");
            }
            else if((lblNumero.getText().equals("0"))&&(lblNumerosAdicional.getText().equals("0"))&&(!lblNumeroFuturo .getText().equals("0"))){
                panelSinConsultas.setVisible(true);
                ///////////////////////////////
                txtT5.setVisible(false);
                jLabel61.setVisible(false);
                /////////////////////////////////
                
                lblCitaFutura.setText("también puede seleccionar citas futuras");
                jLabel70.setText("No hay adicionales");
                jLabel69.setText("Se agotaron las consultas");
            }
        }
    }//GEN-LAST:event_tb_consultoriosMouseClicked

    private void tb_consultoriosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_consultoriosKeyPressed
//        char teclaPresionada = evt.getKeyChar();
//        if(teclaPresionada==KeyEvent.VK_ENTER){    
//        MedicosConsultorios.dispose();
//            int fila = tb_medicos1.getSelectedRow();
//            lblIdMedico.setText(String.valueOf(tb_medicos1.getValueAt(fila, 12)));
//            GuardarDetalle();
//            panelNumeros.setVisible(true);
//            suma();
//        }
    }//GEN-LAST:event_tb_consultoriosKeyPressed

    private void btnBuscarPaciente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente1ActionPerformed

    }//GEN-LAST:event_btnBuscarPaciente1ActionPerformed

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void txtT5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT5CaretUpdate

    private void txtT5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT5MouseClicked
  
        if(txtT5.getText().equals("") && evt.getClickCount()==1){
           txtT5.setText("X");
           txtT6.setText("");
           lblMant.setText("H");
           btnReservar.setVisible(true);
           

        }else
        if(txtT5.getText().equals("X") && evt.getClickCount()==1){
           txtT5.setText(""); 
           txtT6.setText("");
           lblMant.setText("");
           btnReservar.setVisible(false);
            

        }
    }//GEN-LAST:event_txtT5MouseClicked

    private void txtT5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtT5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT5ActionPerformed

    private void txtT6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT6CaretUpdate

    private void txtT6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT6MouseClicked
  
        if(txtT6.getText().equals("") && evt.getClickCount()==1){
           txtT6.setText("X");
           txtT5.setText("");
           lblMant.setText("M");
    
           btnReservar.setVisible(true);

        }else
        if(txtT6.getText().equals("X") && evt.getClickCount()==1){
           txtT6.setText(""); 
           txtT5.setText("");
           lblMant.setText("");
           btnReservar.setVisible(false);

        }
    }//GEN-LAST:event_txtT6MouseClicked

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
 
            MedicosConsultorios.dispose();
            GuardarDetalle();
            panelNumeros.setVisible(true);
            panelAsignacion.setVisible(false);
            panelDeatelleC.setVisible(false);
            txtT5.setText("");
            txtT6.setText("");
            suma();  

    }//GEN-LAST:event_btnReservarActionPerformed

    private void btnReservarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservarMouseClicked

    private void txtT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtT2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT2ActionPerformed

    private void tb_ReporteDiarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiarioMouseClicked

    private void tb_ReporteDiarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiarioMousePressed

    private void tb_ReporteDiarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiarioKeyPressed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        JTableHeader th; 
        th = tb_ReporteDiario.getTableHeader(); 
        Font fuente = new Font("Segoe UI", Font.CENTER_BASELINE, 14); 
        th.setFont(fuente); 
        th.setForeground(new java.awt.Color(102,102,102));
        th.setBackground(new java.awt.Color(255,255,255));
        
        Caja_NuevaVenta CNVRCC = new  Caja_NuevaVenta();
        CNVRCC.ReporteDiariocajaCabecera(lblusu.getText(),tb_ReporteDiario);
        
        CNVRCC.SumaTotalReporte(lblusu.getText());
        CNVRCC.SumaCONTADOReporte(lblusu.getText());
        CNVRCC.SumaPENDIENTEReporte(lblusu.getText());
        CNVRCC.SumaANULADOReporte(lblusu.getText());
        tb_ReporteDiario.setDefaultRenderer(Object.class,new FormatoTablaReporteDiarioCaja());

        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnBuscarPaciente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente3ActionPerformed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

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

    private void tb_CPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_CPTKeyPressed

    private void cbxTipoDocumentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoMouseReleased
        
    }//GEN-LAST:event_cbxTipoDocumentoMouseReleased

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
//        txtTipoDoc.setText(cbxTipoDocumento.getSelectedItem().toString());
        Caja_NuevaVenta CNVE = new Caja_NuevaVenta();
        if(cbxTipoDocumento.getSelectedItem().toString().equalsIgnoreCase("FACTURA")){
                CNVE.listarEmpresa(txtBuscarEmpresa.getText(),tb_Empresa);
                Empresa.setVisible(true); 
        }
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    private void btnBuscarPaciente5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente5ActionPerformed

    }//GEN-LAST:event_btnBuscarPaciente5ActionPerformed

    private void jLabel78MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MouseClicked
        Empresa.dispose();
        Caja_Empresa_jerarquia CEJ = new Caja_Empresa_jerarquia();
        CEJ.setVisible(true);
    }//GEN-LAST:event_jLabel78MouseClicked

    private void ChkAnalf1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnalf1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf1CaretUpdate

    private void ChkAnalf1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnalf1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf1MouseClicked

    private void ChkEdad1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdad1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad1CaretUpdate

    private void ChkEdad1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdad1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad1MouseClicked

    private void ChkEdad2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdad2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad2CaretUpdate

    private void ChkEdad2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdad2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad2MouseClicked

    private void BMedicosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_BMedicosCaretUpdate
        Caja_NuevaVenta CNV = new Caja_NuevaVenta();
        CNV.listarMedicos(BMedicos.getText(),lblServicio.getText(),tb_medicos);

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

    private void tb_medicosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_medicosKeyPressed

        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            Medicos.dispose();
            int fila = tb_medicos.getSelectedRow();
            Medicos.dispose();
            lblIdMedico.setText(String.valueOf(tb_medicos.getValueAt(fila, 0)));
            GuardarDetalle();
            ModificarPreventa();
            panelNumeros.setVisible(true);
            suma();

        }

    }//GEN-LAST:event_tb_medicosKeyPressed

    private void btnBuscarPaciente6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente6ActionPerformed

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
    private javax.swing.JLabel APENOM;
    private javax.swing.JLabel APENOM1;
    private javax.swing.JDialog Anular;
    private javax.swing.JDialog AsistenciaSocial;
    private javax.swing.JDialog BHC;
    private javax.swing.JTextField BMedicos;
    private javax.swing.JComboBox CBXANULAR;
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    public static javax.swing.JTextField ChkEdad2;
    private javax.swing.JLabel Cliente;
    private javax.swing.JLabel DNII;
    private javax.swing.JDialog Empresa;
    private javax.swing.JLabel HCI;
    private javax.swing.JDialog Jerarquias;
    private javax.swing.JLabel M;
    private javax.swing.JDialog Medicos;
    private javax.swing.JDialog MedicosConsultorios;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JLabel Mensaje1;
    private javax.swing.JLabel Mensaje2;
    private javax.swing.JLabel Mensaje3;
    private javax.swing.JLabel Mensaje4;
    private javax.swing.JLabel Mensaje5;
    private javax.swing.JLabel Mensaje6;
    private javax.swing.JDialog abono;
    private javax.swing.JLabel abonod;
    private javax.swing.JLabel adni;
    private javax.swing.JLabel adni1;
    private javax.swing.JTable anulacion;
    private javax.swing.JButton btnAnularVenta;
    private javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarFormaPago;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente1;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBuscarPaciente3;
    private javax.swing.JButton btnBuscarPaciente4;
    private javax.swing.JButton btnBuscarPaciente5;
    private javax.swing.JButton btnBuscarPaciente6;
    private javax.swing.JButton btnCargarCEX;
    private javax.swing.JButton btnCargarEME;
    private javax.swing.JButton btnCargarHOS;
    private javax.swing.JButton btnCorrectoNo;
    private javax.swing.JButton btnCorrectoSi;
    private javax.swing.JButton btnEliminarCEX;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnEliminarEME;
    private javax.swing.JButton btnEliminarHOS;
    private javax.swing.JButton btnEliminarNo;
    private javax.swing.JButton btnEliminarSi;
    public static javax.swing.JButton btnLista;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnNuevo3;
    private javax.swing.JButton btnReservar;
    private javax.swing.JButton btnTerminiarVenta;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar10;
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
    private javax.swing.JButton eli5;
    private javax.swing.JButton eli7;
    private javax.swing.JLabel elimdp;
    private javax.swing.JButton elimma;
    private javax.swing.JButton elimma1;
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
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
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
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JLabel lDoc;
    private javax.swing.JLabel lForma;
    private javax.swing.JLabel lTipoDoc;
    private javax.swing.JLabel lblAbonos;
    public static javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblActoMedicoNuevo;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCAid;
    private javax.swing.JLabel lblCPT;
    private javax.swing.JLabel lblCajaNomenclaturaPrecio;
    private javax.swing.JLabel lblCitaFutura;
    private javax.swing.JLabel lblContador;
    private javax.swing.JLabel lblContadorA;
    private javax.swing.JLabel lblContadorF;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFP;
    private javax.swing.JLabel lblFormaPago;
    private javax.swing.JLabel lblFua;
    private javax.swing.JLabel lblHC;
    private javax.swing.JLabel lblHc;
    public static javax.swing.JLabel lblIdDetalle;
    private javax.swing.JLabel lblIdMedico;
    public static javax.swing.JLabel lblIdPreventa;
    private javax.swing.JLabel lblIdPreventas;
    private javax.swing.JLabel lblId_EmpresaFP;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMantP;
    private javax.swing.JLabel lblMedicoApeNom;
    private javax.swing.JLabel lblMedicoId;
    private javax.swing.JLabel lblModulo;
    private javax.swing.JLabel lblModuloHos;
    public static javax.swing.JLabel lblNumDoc;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblNumeroAF;
    public static javax.swing.JLabel lblNumeroDoc;
    private javax.swing.JLabel lblNumeroFT;
    private javax.swing.JLabel lblNumeroFuturo;
    private javax.swing.JLabel lblNumeroTotal;
    private javax.swing.JLabel lblNumerosAdicional;
    private javax.swing.JLabel lblOk;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblPreventa;
    public static javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblServicioArea;
    private javax.swing.JLabel lblSinAnulacion;
    public static javax.swing.JLabel lblTotalAnulado;
    public static javax.swing.JLabel lblTotalContado;
    public static javax.swing.JLabel lblTotalDiario;
    public static javax.swing.JLabel lblTotalPendiente;
    private javax.swing.JLabel lblUsuPorcentaje;
    private javax.swing.JLabel lblVisAdmi;
    private javax.swing.JLabel lblcod;
    private javax.swing.JLabel lblcodanu;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JButton noeli1;
    private javax.swing.JButton noeli2;
    private javax.swing.JButton noeli3;
    private javax.swing.JButton noeli5;
    private javax.swing.JButton noeli7;
    private javax.swing.JLabel nom;
    private javax.swing.JDialog nomenclaturas;
    private javax.swing.JPanel nuevoanulacion;
    private javax.swing.JPanel panelAbonos;
    private javax.swing.JPanel panelActoMedico;
    private javax.swing.JPanel panelAnular;
    private javax.swing.JPanel panelAsignacion;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelBuscarHC;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCargarCPT;
    private javax.swing.JPanel panelColorFp;
    private javax.swing.JPanel panelDatosGenerales;
    private javax.swing.JPanel panelDeatelleC;
    private javax.swing.JPanel panelEliminacion;
    private javax.swing.JPanel panelEliminar;
    private javax.swing.JPanel panelEliminarCEX;
    private javax.swing.JPanel panelEliminarEME;
    private javax.swing.JPanel panelEliminarHOS;
    private javax.swing.JPanel panelExoneracion;
    private javax.swing.JPanel panelFormaPago;
    private javax.swing.JPanel panelMensaje;
    private javax.swing.JPanel panelNumeros;
    private javax.swing.JPanel panelPorcentajes;
    private javax.swing.JPanel panelPreventa;
    private javax.swing.JPanel panelSinCPT;
    private javax.swing.JPanel panelSinConsultas;
    private javax.swing.JPanel panelSinHC;
    private javax.swing.JPanel panelSinMedico;
    private javax.swing.JPanel panelTablaCPT;
    private javax.swing.JPanel panelTurnos;
    private javax.swing.JPanel paneltablaHC;
    private javax.swing.JDialog preventas;
    private javax.swing.JPanel resumen;
    private javax.swing.JPanel resumen1;
    private javax.swing.JPanel sep1;
    private javax.swing.JTextField sexo;
    private javax.swing.JTable tEME;
    private javax.swing.JTable tEMEDET;
    private javax.swing.JTable tHOS;
    private javax.swing.JTable tHOSDET;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTable tb_Asistente;
    private javax.swing.JTable tb_BusquedaCabecera;
    private javax.swing.JTable tb_CEX;
    private javax.swing.JTable tb_CPT;
    private javax.swing.JTable tb_CPTBUSCAR;
    private javax.swing.JTable tb_Empresa;
    private javax.swing.JTable tb_Grupo3;
    private javax.swing.JTable tb_ReporteDetalle;
    private javax.swing.JTable tb_ReporteDiario;
    private javax.swing.JTable tb_consultorios;
    private javax.swing.JTable tb_medicos;
    private javax.swing.JTable tb_medicos1;
    private javax.swing.JTable tbpreventas;
    public static javax.swing.JTextField txtBuscar4;
    private javax.swing.JTextField txtBuscarCPT;
    private javax.swing.JTextField txtBuscarEmpresa;
    private javax.swing.JTextField txtBuscarFormaPago;
    private javax.swing.JTextField txtBuscarMedicos;
    private javax.swing.JTextField txtBuscarPaciente;
    public static javax.swing.JTextField txtCPT;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtEnterEscapeEnter;
    private javax.swing.JTextField txtEnterEscapeEnter1;
    public static javax.swing.JTextField txtFormaPago;
    private javax.swing.JLabel txtHC;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtSubTotal;
    public static javax.swing.JTextField txtT1;
    public static javax.swing.JTextField txtT2;
    public static javax.swing.JTextField txtT3;
    public static javax.swing.JTextField txtT4;
    public static javax.swing.JTextField txtT5;
    public static javax.swing.JTextField txtT6;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtanular;
    private javax.swing.JLabel txtape;
    private javax.swing.JTextField txtedad;
    // End of variables declaration//GEN-END:variables
}
