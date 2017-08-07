/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.admisionEmergencia;

import modelos.admisionCentral.HistoriaClinica;
import modelos.admisionCentral.MovimientoHistoriaClinica;
import Atxy2k.CustomTextField.RestrictedTextField;
import campos.LimitadorDeDocumento;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import modelos.*;
import modelos.Caja.Caja_DetallePreventa;
import modelos.Caja.Caja_Nomenclatura;
import modelos.Caja.Caja_Preventa;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.admisionEmergencia.AdmisionEmergenciaTopico;
import modelos.admisionEmergencia.AdmisionEmergenciaTopicoDetalleDiagFinal;
import modelos.admisionEmergencia.AdmisionEmergenciaTopicoDetalleDiagPresun;
import modelos.admisionEmergencia.AdmisionEmergenciaTopicoDetalleExamen;
import modelos.admisionEmergencia.AdmisionEmergenciaTriaje;
import tablas.FormatoTablaMovHC;
import vista.admisionCentral.FrmAdmision;
import vista.admisionCentral.FrmNuevaHistoriaC;
import vista.Principal;
import static vista.admisionCentral.FrmNuevaHistoriaC.txtDni;
import static vista.admisionCentral.FrmNuevaHistoriaC.txtID;
import static vista.admisionCentral.FrmNuevaHistoriaC.txtNombre1;
import static vista.Principal.fechaActual;
import vista.PrincipalMDI;
/**
 *
 * @author PC02
 */
public class FrmFormatoEmergenciaCabecera extends javax.swing.JFrame implements Runnable{
    static DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    static MovimientoHistoriaClinica movHC = new MovimientoHistoriaClinica();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
    ResultSet r;
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    AdmisionEmergenciaTriaje adEmerTr = new AdmisionEmergenciaTriaje();
    AdmisionEmergenciaTopico adEmerTo = new AdmisionEmergenciaTopico();
    Caja_Preventa cp = new Caja_Preventa();
    byte tg;
    byte tge;
    byte tga;
    /**
     * Creates new form FrmemergenciaCabecera
     */
    public FrmFormatoEmergenciaCabecera() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);//fondo blanco
        setLocationRelativeTo(null);//en el centro
        this.setExtendedState(MAXIMIZED_BOTH);
        btnBuscar.setMnemonic(KeyEvent.VK_F3);
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
        conexion = c.conectar();
        
        
            cp.listarDatosEmergencia(txtBuscar.getText(), "", "", tbCabecera);
            tbCabecera.getSelectionModel().setSelectionInterval(0, 0);
            tbCabecera.requestFocus();
            
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
        cargareliminar.setVisible(false);
//        tb_Asistente.getTableHeader().setVisible(false);
        tbPacientes.setTableHeader(null);
        tb_Grupo4.setTableHeader(null);
        tbPaneles.setEnabledAt(0,false);
        tbPaneles.setEnabledAt(1, false);
        BuscarJ();
        tbPaneles.setSelectedIndex(1);
        pnlB.setEnabled(false);
        limpiar();
        restringirCampos(8, txtNHC);
        pnlDatosCabecera.setVisible(false);
        cbxFormaLlegada.setModel(formaDeLlegada());
        mostrarDatosModif(false);
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 

        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        formatotbExamenAux(tbExamenes);
        lblEFP.setText("C");
        formatotbImpdx(tbDiagFinal);
        formatotbImpdx(tbDiagPresun);
        //tbPaciente.setDefaultRenderer(Object.class,new tablas.AdmisionEmergenciaTopico());
        //limitadores
        LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(100);
        pnlEObservación.setDocument(limitObservacion);
        lblNewMod.setVisible(false);
        lblCabpT.setVisible(false);
//        lblIDHCTo.setVisible(false);

        lblIdFP.setVisible(false);
        txtNroRegistro.setVisible(false);
        lblIDHCTo.setVisible(false);
    }
    
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
        });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Actualizar_ID_PREVENTA(){
                Caja_Preventa AME = new Caja_Preventa();
                AME.setId_Preventa(Integer.parseInt(lblId_Preventa.getText()));
                AME.setTriaje_Id(lblTriajeId.getText() );

                if(AME.ACTUALIZAR_TRIAJE_ID_PREVENTA()==true){
                           System.out.println("SE ACTUALIZO EL ID DE LA PREVENTA");
                       } else {
                             System.out.println("erorr ID_PREVENTA ");
                       }  
    }

     public void imprimirCabecera(){
        int fila = tbCabecera.getSelectedRow();
        adEmerCab.reporteCabecera(Integer.parseInt(String.valueOf(tbCabecera.getValueAt(fila, 0))));
    }
      public String determinarFecha(JDateChooser calendario){
        String fecha = "";
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = calendario.getCalendar().get(Calendar.MONTH)+1;
        int anio = calendario.getCalendar().get(Calendar.YEAR); 
        if(dia < 10 && mes < 10){
            fecha = String.valueOf("0" + dia + "/" + "0" + mes + "/" + anio);
        }else 
            if(dia < 10 || mes < 10){
                if(dia < 10 && mes >=10){
                    fecha = String.valueOf("0" + dia + "/" + mes + "/" + anio);
                } else 
                    if(dia >= 10 && mes < 10){
                        fecha = String.valueOf(dia + "/" + "0" + mes + "/" + anio);
                    } 
            } else 
                fecha = String.valueOf(dia + "/" + mes + "/" + anio);
        return fecha;
    }
      public void BuscarJ(){
        String consulta="";
        try {
            tb_FP.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","Forma de Pago","Descripcion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            AdmisionEmergenciaCabecera obj=new AdmisionEmergenciaCabecera();
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
            tb_FP.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_FP.setRowSorter(elQueOrdena);
            this.tb_FP.setModel(m);
            formatoj();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
      public void formatoj(){
    tb_FP.getColumnModel().getColumn(0).setMinWidth(0);
    tb_FP.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_FP.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_FP.getColumnModel().getColumn(2).setMinWidth(0);
    tb_FP.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_FP.setRowHeight(30);

    }
    
     public void BuscarHC(){
        String consulta="";
        try {
            tbPacientes.setModel(new DefaultTableModel());
             String titulos[]={"Nº H.C.","DNI","Paciente","Direccion","Sexo","Fecha","Edad","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            AdmisionEmergenciaCabecera obj=new AdmisionEmergenciaCabecera();
                    consulta="exec CAJA_BUSCAR_HISTORIAS_TOPICOS_TRIAJE ?";      
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
                fila[8]=r.getString(9);
                m.addRow(fila);
                c++;
            }
            tbPacientes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPacientes.setRowSorter(elQueOrdena);
            this.tbPacientes.setModel(m);
            formato();
        } catch (Exception e) {
            System.out.println("Error BHC: " + e.getMessage());
        }
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
    tbPacientes.getColumnModel().getColumn(8).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(8).setMaxWidth(0);
    tbPacientes.setRowHeight(38);
    }
    

    
    public DefaultComboBoxModel formaDeLlegada(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("EXEC ADMISION_EMERGENCIA_FORMADE_LLEGADA_LISTAR"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "EMER_FORMA_LLEGADA_NOMBRE" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public void restringirCampos(int limite,javax.swing.JTextField campo){
        RestrictedTextField restricted = new RestrictedTextField(campo);
        restricted.setLimit(limite);
    }
    
    
    public void filtrarDatos(){
        BHC.setVisible(true);
        BHC.setLocationRelativeTo(null);//en el centro
        BHC.setResizable(false);
        BHC.getContentPane().setBackground(Color.WHITE);
    }
    
    public void limpiar(){
        lblDni.setText("");
        lblApNom.setText("");
        lblGenero.setText("");
        lblSector.setText("");
        lblEdad.setText("");
        lblEstcivil.setText("");
        lblDistrito.setText("");
        lblDireccion.setText("");
        lblProvincia.setText("");
        lblDepartamento.setText("");
        lblFechaNac.setText("");
        txtTraidopor.setText("");
        txtParentesco.setText("");
        pnlEObservación.setText("");
        txtCPT.setText("");
        txtCPTDES.setText("");
        txtFormaPago.setText("");
        cbxFormaLlegada.setSelectedIndex(0);
    }

   
    
    public static void formatoTablaBuscar(){
        tbPaciente.getColumnModel().getColumn(0).setPreferredWidth(70);//nhc
        tbPaciente.getColumnModel().getColumn(1).setPreferredWidth(130);//apellidos
        tbPaciente.getColumnModel().getColumn(2).setPreferredWidth(130);//nombres
        tbPaciente.getColumnModel().getColumn(3).setPreferredWidth(80);//dni
        tbPaciente.setRowHeight(25);
    }
    
    public static void buscar_HC(int index, String opcion, String descripcion){
    String consulta="";
        try {
            tbPaciente.setModel(new DefaultTableModel());
            String titulos[]={"N°","Apellidos","Nombres","DNI"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC SP_HC_METODO_BUSQUEDA ?,?,?";
            PreparedStatement cmd = movHC.getCn().prepareStatement(consulta);
            cmd.setInt(1, index);
            cmd.setString(2, descripcion);
            cmd.setString(3, opcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(3); // nhc
                fila[1]=r.getString(4) + " " + r.getString(5);
                fila[2]=r.getString(6) + " " + 
                        r.getString(7) + " " + r.getString(8); // apellidos y nombres
                fila[3]=r.getString(2); // dni
                //fila[4]=r.getString(1); // codigo de hc
                    m.addRow(fila);
                    c++;
            }
            tbPaciente.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPaciente.setRowSorter(elQueOrdena);
            tbPaciente.setModel(m);
            formatoTablaBuscar();
        } catch (Exception e) {
            System.out.println("Error_buscar_HC: " + e.getMessage());
        }
    }
    
    public void buscarNHC(){
        int index = cbxTipoBusqueda.getSelectedIndex();
        if(cbxTipoBusqueda.getSelectedIndex() > 0)
            buscar_HC(index,"A",txtBusqueda.getText());
        else {
            JOptionPane.showMessageDialog(dlgBuscarPac, "Seleccione un tipo de búsqueda");
            pnlB.setEnabled(true);
            txtBusqueda.setText("");
        }
    }
    
    public void mostrarHCCabecera(String nhc){
        String consulta="";
        try {
            consulta="EXEC SP_ADMISION_HISTORIACLINICA_BUSXNHC ?";
            PreparedStatement cmd = movHC.getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblDni.setText(r.getString(1)); 
                lblApNom.setText(r.getString(2) + " " + r.getString(3) + " " + 
                                 r.getString(4) + " " + r.getString(5) + " " +
                                 r.getString(6)); 
                lblFechaNac.setText(r.getString(7)); 
                lblGenero.setText(r.getString(8)); 
                lblEstcivil.setText(r.getString(9)); 
                lblDistrito.setText(r.getString(10)); 
                lblProvincia.setText(r.getString(11)); 
                lblDepartamento.setText(r.getString(12)); 
                lblDireccion.setText(r.getString(13) + " " + r.getString(14) + r.getString(15)); 
                lblSector.setText(r.getString(16)); 
                lblEstado.setText(r.getString(17));
                lblIDHC.setText(r.getString(18));
                lblEdad.setText(r.getString(19)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error_mostrar_MovHC: " + e.getMessage());
        }
    }
    
    
    
   
    public String formatoNHC(String nhc){
        String codigo = String.valueOf(nhc.charAt(0)) + 
                        String.valueOf(nhc.charAt(1)) + 
                        String.valueOf(nhc.charAt(2)) + 
                        String.valueOf(nhc.charAt(3)) + 
                        String.valueOf(nhc.charAt(4)) +
                        String.valueOf(nhc.charAt(6)) +
                        String.valueOf(nhc.charAt(7));
        return codigo;
    }
    
    public void mostrarDatosModif(boolean opcion){
        lblFechaIM.setVisible(opcion);
        lblFechaIng.setVisible(opcion);
        lblHoraIM.setVisible(opcion);
        lblHoraIng.setVisible(opcion);
    }
    
    public void enviarDatosTbPaciente(){
        int fila = tbPacientes.getSelectedRow();
//        txtBuscarPaciente.setText("");
        BHC.dispose();

            txtNHC.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            lblTriajeId.setText(String.valueOf(tbPacientes.getValueAt(fila, 8)));
            txtTraidopor.requestFocus();
             btnGuardar.setEnabled(true);

    }
    public void Actualizar_ESTADO_PREVENTA(){
                Caja_Preventa AME = new Caja_Preventa();
                AME.setTriaje_Id(lblTriajeId.getText() );

                if(AME.ACTUALIZAR_ESTADO_TRIAJE()==true){
                           System.out.println("SE ACTUALIZO EL ESTADO DEL TRIAJE");
                       } else {
                             System.out.println("eror ESTADO PREVENTA");
                       }  
    }

        
   public void enviarDatosTbFormatEmergencia(){

       
        int fila = tbCabecera.getSelectedRow();
//        dlgModemergencia.dispose();
        txtNHC.setText(String.valueOf(tbCabecera.getValueAt(fila, 2)));
        tbPaneles.setSelectedIndex(0);
        txtNroRegistro.setText(String.valueOf(tbCabecera.getValueAt(fila, 0)));
        txtTraidopor.setText(String.valueOf(tbCabecera.getValueAt(fila, 5)));
        txtParentesco.setText(String.valueOf(tbCabecera.getValueAt(fila, 6)));
        cbxFormaLlegada.setSelectedItem(String.valueOf(tbCabecera.getValueAt(fila, 11)));
        pnlEObservación.setText(String.valueOf(tbCabecera.getValueAt(fila, 12)));
        lblFechaIng.setText(String.valueOf(tbCabecera.getValueAt(fila, 13)));
        lblHoraIng.setText(String.valueOf(tbCabecera.getValueAt(fila, 14)));
        
        txtFormaPago.setText(String.valueOf(tbCabecera.getValueAt(fila, 15)));
        lblFP.setText(String.valueOf(tbCabecera.getValueAt(fila, 16)));
        txtCPT.setText(String.valueOf(tbCabecera.getValueAt(fila, 17)));
        txtCPTDES.setText(String.valueOf(tbCabecera.getValueAt(fila, 18)));
        mostrarDatosModif(true);
        txtTraidopor.requestFocus();
//        habilitarPestanas(1, false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        
        
        
    }
 
    
    public void enviarNomenclatura(){
        int fila = tb_Grupo4.getSelectedRow();
        dlgBuscarCPT.dispose();
        txtCPT.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 2)));
        txtCPTDES.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 1)));
    }   
    

    
   
    
   
    
    public void habilitarDatos(){
        txtNHC.requestFocus();
        btnFiltrar.setEnabled(true);
        txtNHC.setEnabled(true);
        pnlDatosCabecera.setVisible(false);
        txtNHC.setText("");
        limpiar();
    }
    
   
    
    public void formatotbExamenAux(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(200);//nombre
        tabla.getColumnModel().getColumn(1).setPreferredWidth(140);//CODIGO
        tabla.getColumnModel().getColumn(2).setPreferredWidth(140);//CODIGO
        tabla.setRowHeight(25);
    }

    
    public void formatotbImpdx(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);//clasificacion
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);//clasificacion
        tabla.setRowHeight(25);
    }
    

    
 
   
    //FIN DIALOGO TOPICO
    public boolean repitedlgExamen(){
        int filaselec=tbDatosLab.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbExamenes.getRowCount(); i++){    
            if(tbExamenes.getValueAt(i, 2).toString().equalsIgnoreCase(tbDatosLab.getValueAt(filaselec, 3).toString())){
                    c=true;
            }
        }
               return c;
     }
    
    public boolean repitedlgExamenDiagImp(){
        int filaselec=tbDiagnosticos.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbDiagPresun.getRowCount(); i++){    
            if(tbDiagPresun.getValueAt(i, 0).toString().equalsIgnoreCase(tbDiagnosticos.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
    
    public boolean repitedlgExamenDiagAlta(){
        int filaselec=tbDiagnosticos.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbDiagFinal.getRowCount(); i++){    
            if(tbDiagFinal.getValueAt(i, 0).toString().equalsIgnoreCase(tbDiagnosticos.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
     //FIN DIALOGO TOPICO

    
    // ELIMINAR DATOS TOPICOS DETALLE EXAMEN
    
    public void eliminarDetalleTopicoExamen(){
        if(tbExamenes.getSelectedRowCount()!=0){
            Caja_DetallePreventa cp = new Caja_DetallePreventa();
            int fila = tbExamenes.getSelectedRow();
            int filaDatosT = tbDatosTopico.getSelectedRow();
            cp.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filaDatosT, 0)));
            cp.setCod_precio(String.valueOf(tbExamenes.getValueAt(fila, 2)));
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar.png")); 
            int eliminar = JOptionPane.showConfirmDialog(dlgMostrarDatosTopico, "¿Está seguro que desea ELIMINAR este registro?",
                                    "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
            if(eliminar == 0){
                if(cp.cajaDetallePreventaModificar()==true){
                    m= (DefaultTableModel) tbExamenes.getModel(); //TableProducto es el nombre de mi tabla ;) 
                    m.removeRow(tbExamenes.getSelectedRow()); 
                    JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Examen eliminado");
                }
            } else
                JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "No ha hecho ninguna modificación");
        } else 
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
    }
    
    public void eliminarDetalleTopicoDiagPresun(){
        if(tbDiagPresun.getSelectedRowCount()!=0){
            AdmisionEmergenciaTopicoDetalleDiagPresun adEmertop = new AdmisionEmergenciaTopicoDetalleDiagPresun();
            int fila = tbDiagPresun.getSelectedRow();
            int filaDatosT = tbDatosTopico.getSelectedRow();
            adEmertop.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filaDatosT, 0)));
            adEmertop.setId_cie10(Integer.parseInt(String.valueOf(tbDiagPresun.getValueAt(fila, 0))));
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar.png")); 
            int eliminar = JOptionPane.showConfirmDialog(dlgMostrarDatosTopico, "¿Está seguro que desea ELIMINAR este registro?",
                                    "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
            if(eliminar == 0){
                if(adEmertop.modificarDetalleDiagPresun()==true){
                    m= (DefaultTableModel) tbDiagPresun.getModel(); //TableProducto es el nombre de mi tabla ;) 
                    m.removeRow(tbDiagPresun.getSelectedRow()); 
                    JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Examen eliminado");
                }
            } else
                JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "No ha hecho ninguna modificación");
        } else 
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
    }
    
    public void eliminarDetalleTopicoDiagFinal(){
        if(tbDiagFinal.getSelectedRowCount()!=0){
            AdmisionEmergenciaTopicoDetalleDiagFinal adEmertop = new AdmisionEmergenciaTopicoDetalleDiagFinal();
            int fila = tbDiagFinal.getSelectedRow();
            int filaDatosT = tbDatosTopico.getSelectedRow();
            adEmertop.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filaDatosT, 0)));
            adEmertop.setId_cie10(Integer.parseInt(String.valueOf(tbDiagFinal.getValueAt(fila, 0))));
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar.png")); 
            int eliminar = JOptionPane.showConfirmDialog(dlgMostrarDatosTopico, "¿Está seguro que desea ELIMINAR este registro?",
                                    "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
            if(eliminar == 0){
                if(adEmertop.modificarDetalleDiagFinal()==true){
                    m= (DefaultTableModel) tbDiagFinal.getModel(); //TableProducto es el nombre de mi tabla ;) 
                    m.removeRow(tbDiagFinal.getSelectedRow()); 
                    JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Examen eliminado");
                }
            } else
                JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "No ha hecho ninguna modificación");
        } else 
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        dlgBuscarPac = new javax.swing.JDialog();
        pnlB = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        btnFiltrarTri1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPaciente = new javax.swing.JTable();
        cbxTipoBusqueda = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        dlgModemergencia = new javax.swing.JDialog();
        jLabel39 = new javax.swing.JLabel();
        jdcBusquedaFecha = new com.toedter.calendar.JDateChooser();
        BTNB = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        lblPestanaMod = new javax.swing.JLabel();
        EDITAR = new javax.swing.JLabel();
        dlgModTriaje = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbModifTriaje = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jdtBuscarTriaje = new com.toedter.calendar.JDateChooser();
        btnBuscarMTriaje = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        dlgListaImpDx = new javax.swing.JDialog();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbDiagnosticos = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtBuscarDiagnostico = new javax.swing.JTextField();
        btnBuscarDiagnostico = new javax.swing.JButton();
        lblTipoDiag = new javax.swing.JLabel();
        dlgListaExamnAux = new javax.swing.JDialog();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbDatosLab = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtBusquedaTo = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        dlgMostrarDatosTriajeT = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbMostrarTriajepT = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        jdtBuscarTriTop = new com.toedter.calendar.JDateChooser();
        btnBuscarTriTop = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        dlgMostrarDatosTopico = new javax.swing.JDialog();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbDatosTopico = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbExamenes = new javax.swing.JTable();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbDiagPresun = new javax.swing.JTable();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbDiagFinal = new javax.swing.JTable();
        btnAddExam = new javax.swing.JButton();
        btnDelExam = new javax.swing.JButton();
        btnAddDiagP = new javax.swing.JButton();
        btnDelDiagP = new javax.swing.JButton();
        btnAddDiagF = new javax.swing.JButton();
        btnDelDiagF = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jdtFechaTop = new com.toedter.calendar.JDateChooser();
        btnBuscarTo = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        dlgBuscarCPT = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        txtBuscar2 = new javax.swing.JTextField();
        btnBuscarPaciente3 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tb_Grupo4 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            BHC = new javax.swing.JDialog();
            jPanel15 = new javax.swing.JPanel();
            jLabel19 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            bus = new javax.swing.JLabel();
            jPanel27 = new javax.swing.JPanel();
            txtBuscarPaciente = new javax.swing.JTextField();
            bus3 = new javax.swing.JLabel();
            btnBuscarPaciente2 = new javax.swing.JButton();
            jPanel26 = new javax.swing.JPanel();
            jScrollPane22 = new javax.swing.JScrollPane();
            ABONOS = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                lblIdPreventa = new javax.swing.JLabel();
                jScrollPane23 = new javax.swing.JScrollPane();
                tbpreventas = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jScrollPane27 = new javax.swing.JScrollPane();
                    tbpreventasFR = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        panelBuscarHC = new javax.swing.JPanel();
                        jLabel15 = new javax.swing.JLabel();
                        paneltablaHC = new javax.swing.JPanel();
                        jScrollPane24 = new javax.swing.JScrollPane();
                        tbPacientes = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jPanel35 = new javax.swing.JPanel();
                            jLabel45 = new javax.swing.JLabel();
                            jLabel51 = new javax.swing.JLabel();
                            jLabel86 = new javax.swing.JLabel();
                            panelSinHC = new javax.swing.JPanel();
                            jLabel46 = new javax.swing.JLabel();
                            jLabel87 = new javax.swing.JLabel();
                            jPanel28 = new javax.swing.JPanel();
                            btnNuevo1 = new javax.swing.JButton();
                            jLabel88 = new javax.swing.JLabel();
                            jpmCabecera = new javax.swing.JPopupMenu();
                            jMenuItem4 = new javax.swing.JMenuItem();
                            jSeparator9 = new javax.swing.JPopupMenu.Separator();
                            jMenuItem5 = new javax.swing.JMenuItem();
                            Jerarquias = new javax.swing.JDialog();
                            jPanel11 = new javax.swing.JPanel();
                            jLabel22 = new javax.swing.JLabel();
                            jPanel30 = new javax.swing.JPanel();
                            txtBuscarFormaPago = new javax.swing.JTextField();
                            btnBuscarPaciente4 = new javax.swing.JButton();
                            jScrollPane6 = new javax.swing.JScrollPane();
                            tb_FP = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                jPanel8 = new javax.swing.JPanel();
                                btnNuevo = new javax.swing.JButton();
                                btnModificar = new javax.swing.JButton();
                                btnGuardar = new javax.swing.JButton();
                                btnEliminar = new javax.swing.JButton();
                                btnBuscar = new javax.swing.JButton();
                                lblNewMod = new javax.swing.JLabel();
                                lblCabpT = new javax.swing.JLabel();
                                lblIDHCTo = new javax.swing.JLabel();
                                lblIdFP = new javax.swing.JLabel();
                                lblusu = new javax.swing.JLabel();
                                jLabel84 = new javax.swing.JLabel();
                                lblId_Preventa = new javax.swing.JLabel();
                                lblTriajeId = new javax.swing.JLabel();
                                tbPaneles = new javax.swing.JTabbedPane();
                                jPanel1 = new javax.swing.JPanel();
                                jLabel4 = new javax.swing.JLabel();
                                jPanel6 = new javax.swing.JPanel();
                                txtNHC = new javax.swing.JTextField();
                                btnFiltrar = new javax.swing.JButton();
                                pnlDatosCabecera = new javax.swing.JPanel();
                                jLabel5 = new javax.swing.JLabel();
                                lblApNom = new javax.swing.JLabel();
                                jLabel6 = new javax.swing.JLabel();
                                lblGenero = new javax.swing.JLabel();
                                jLabel7 = new javax.swing.JLabel();
                                lblEdad = new javax.swing.JLabel();
                                jLabel8 = new javax.swing.JLabel();
                                lblFechaNac = new javax.swing.JLabel();
                                jLabel9 = new javax.swing.JLabel();
                                lblEstcivil = new javax.swing.JLabel();
                                lblDni = new javax.swing.JLabel();
                                jLabel17 = new javax.swing.JLabel();
                                lblDistrito = new javax.swing.JLabel();
                                jLabel18 = new javax.swing.JLabel();
                                lblProvincia = new javax.swing.JLabel();
                                jLabel20 = new javax.swing.JLabel();
                                lblDepartamento = new javax.swing.JLabel();
                                jSeparator2 = new javax.swing.JSeparator();
                                jLabel10 = new javax.swing.JLabel();
                                lblDireccion = new javax.swing.JLabel();
                                jLabel11 = new javax.swing.JLabel();
                                lblSector = new javax.swing.JLabel();
                                lblEstado = new javax.swing.JLabel();
                                lblIDHC = new javax.swing.JLabel();
                                lblHoraIM = new javax.swing.JLabel();
                                lblHoraIng = new javax.swing.JLabel();
                                lblFechaIng = new javax.swing.JLabel();
                                jSeparator8 = new javax.swing.JSeparator();
                                jLabel31 = new javax.swing.JLabel();
                                jLabel32 = new javax.swing.JLabel();
                                jLabel41 = new javax.swing.JLabel();
                                cbxFormaLlegada = new javax.swing.JComboBox();
                                jLabel3 = new javax.swing.JLabel();
                                lblFechaIM = new javax.swing.JLabel();
                                panelFormaPago = new javax.swing.JPanel();
                                btnBuscarCPT = new javax.swing.JButton();
                                txtCPTDES = new javax.swing.JTextField();
                                jLabel2 = new javax.swing.JLabel();
                                panelFormaPago1 = new javax.swing.JPanel();
                                txtFormaPago = new javax.swing.JTextField();
                                btnBuscarFormaPago = new javax.swing.JButton();
                                jLabel33 = new javax.swing.JLabel();
                                lblFP = new javax.swing.JLabel();
                                panelFormaPago2 = new javax.swing.JPanel();
                                txtParentesco = new javax.swing.JTextField();
                                panelFormaPago3 = new javax.swing.JPanel();
                                txtTraidopor = new javax.swing.JTextField();
                                panelFormaPago4 = new javax.swing.JPanel();
                                jScrollPane4 = new javax.swing.JScrollPane();
                                pnlEObservación = new javax.swing.JEditorPane();
                                txtCPT = new javax.swing.JLabel();
                                jLabel16 = new javax.swing.JLabel();
                                jLabel21 = new javax.swing.JLabel();
                                txtNroRegistro = new javax.swing.JLabel();
                                jPanel29 = new javax.swing.JPanel();
                                jScrollPane25 = new javax.swing.JScrollPane();
                                tbCabecera = new javax.swing.JTable();
                                panelBusqueda = new javax.swing.JPanel();
                                btnBuscar1 = new javax.swing.JButton();
                                txtBuscar = new javax.swing.JTextField();
                                fechai = new com.toedter.calendar.JDateChooser();
                                fechaf = new com.toedter.calendar.JDateChooser();
                                jPanel10 = new javax.swing.JPanel();
                                jLabel36 = new javax.swing.JLabel();
                                jLabel42 = new javax.swing.JLabel();
                                jLabel43 = new javax.swing.JLabel();
                                lblEFP = new javax.swing.JLabel();
                                cargareliminar = new javax.swing.JPanel();
                                Mensaje = new javax.swing.JLabel();
                                eli = new javax.swing.JButton();
                                noeli = new javax.swing.JButton();

                                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                jPanel4.setLayout(jPanel4Layout);
                                jPanel4Layout.setHorizontalGroup(
                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 100, Short.MAX_VALUE)
                                );
                                jPanel4Layout.setVerticalGroup(
                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 100, Short.MAX_VALUE)
                                );

                                dlgBuscarPac.setAlwaysOnTop(true);
                                dlgBuscarPac.setMinimumSize(new java.awt.Dimension(400, 550));

                                pnlB.setBackground(new java.awt.Color(255, 255, 255));
                                pnlB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtBusqueda.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtBusqueda.setBorder(null);
                                txtBusqueda.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtBusquedaCaretUpdate(evt);
                                    }
                                });
                                txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        txtBusquedaKeyPressed(evt);
                                    }
                                });

                                btnFiltrarTri1.setBackground(new java.awt.Color(0, 153, 153));
                                btnFiltrarTri1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                btnFiltrarTri1.setMnemonic('B');
                                btnFiltrarTri1.setToolTipText("Buscar Nª H.C. (Alt + B)");
                                btnFiltrarTri1.setBorderPainted(false);
                                btnFiltrarTri1.setContentAreaFilled(false);
                                btnFiltrarTri1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnFiltrarTri1.setDefaultCapable(false);
                                btnFiltrarTri1.setFocusPainted(false);
                                btnFiltrarTri1.setFocusable(false);
                                btnFiltrarTri1.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnFiltrarTri1ActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout pnlBLayout = new javax.swing.GroupLayout(pnlB);
                                pnlB.setLayout(pnlBLayout);
                                pnlBLayout.setHorizontalGroup(
                                    pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBLayout.createSequentialGroup()
                                        .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnFiltrarTri1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0))
                                );
                                pnlBLayout.setVerticalGroup(
                                    pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBLayout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addGroup(pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnFiltrarTri1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                tbPaciente = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbPaciente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                tbPaciente.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {
                                        {null, null, null, null},
                                        {null, null, null, null}
                                    },
                                    new String [] {
                                        "Nº H.C.", "Apellidos", "Nombres", "DNI"
                                    }
                                ) {
                                    boolean[] canEdit = new boolean [] {
                                        false, false, false, false
                                    };

                                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return canEdit [columnIndex];
                                    }
                                });
                                tbPaciente.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbPaciente.setSelectionBackground(new java.awt.Color(0, 118, 168));
                                tbPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbPacienteMouseClicked(evt);
                                    }
                                });
                                tbPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbPacienteKeyPressed(evt);
                                    }
                                });
                                jScrollPane1.setViewportView(tbPaciente);

                                cbxTipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Nº H.C.", "DNI", "Apellidos", "Nombres" }));
                                cbxTipoBusqueda.addItemListener(new java.awt.event.ItemListener() {
                                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                        cbxTipoBusquedaItemStateChanged(evt);
                                    }
                                });

                                lblPestana.setForeground(new java.awt.Color(255, 255, 255));
                                lblPestana.setText("jLabel3");

                                jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                jLabel78.setText("Buscar (Alt + B)");

                                javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                                jPanel19.setLayout(jPanel19Layout);
                                jPanel19Layout.setHorizontalGroup(
                                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel78)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel19Layout.setVerticalGroup(
                                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel78)
                                        .addContainerGap())
                                );

                                javax.swing.GroupLayout dlgBuscarPacLayout = new javax.swing.GroupLayout(dlgBuscarPac.getContentPane());
                                dlgBuscarPac.getContentPane().setLayout(dlgBuscarPacLayout);
                                dlgBuscarPacLayout.setHorizontalGroup(
                                    dlgBuscarPacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgBuscarPacLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(dlgBuscarPacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(dlgBuscarPacLayout.createSequentialGroup()
                                                .addGroup(dlgBuscarPacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblPestana)
                                                    .addGroup(dlgBuscarPacLayout.createSequentialGroup()
                                                        .addComponent(cbxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(pnlB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap())
                                );
                                dlgBuscarPacLayout.setVerticalGroup(
                                    dlgBuscarPacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgBuscarPacLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblPestana)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(dlgBuscarPacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pnlB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26))
                                );

                                dlgModemergencia.setAlwaysOnTop(true);
                                dlgModemergencia.setMinimumSize(new java.awt.Dimension(400, 550));

                                jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                jLabel39.setText("Buscar por fecha");

                                jdcBusquedaFecha.setBackground(new java.awt.Color(255, 255, 255));
                                jdcBusquedaFecha.setDateFormatString("dd/MM/yyyy");

                                BTNB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                BTNB.setMnemonic('B');
                                BTNB.setToolTipText("Buscar (Alt + B)");
                                BTNB.setContentAreaFilled(false);
                                BTNB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                BTNB.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        BTNBActionPerformed(evt);
                                    }
                                });

                                jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                jLabel40.setText("Buscar (Alt + B)");

                                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                                jPanel14.setLayout(jPanel14Layout);
                                jPanel14Layout.setHorizontalGroup(
                                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel40)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel14Layout.setVerticalGroup(
                                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel40)
                                        .addContainerGap())
                                );

                                lblPestanaMod.setForeground(new java.awt.Color(255, 255, 255));
                                lblPestanaMod.setText("jLabel13");

                                EDITAR.setText("jLabel1");

                                javax.swing.GroupLayout dlgModemergenciaLayout = new javax.swing.GroupLayout(dlgModemergencia.getContentPane());
                                dlgModemergencia.getContentPane().setLayout(dlgModemergenciaLayout);
                                dlgModemergenciaLayout.setHorizontalGroup(
                                    dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgModemergenciaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(dlgModemergenciaLayout.createSequentialGroup()
                                                .addGroup(dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(dlgModemergenciaLayout.createSequentialGroup()
                                                        .addComponent(jLabel39)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jdcBusquedaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BTNB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(EDITAR)
                                                        .addComponent(lblPestanaMod)))
                                                .addGap(0, 107, Short.MAX_VALUE))
                                            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addContainerGap())
                                );
                                dlgModemergenciaLayout.setVerticalGroup(
                                    dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgModemergenciaLayout.createSequentialGroup()
                                        .addComponent(EDITAR)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPestanaMod)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jdcBusquedaFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(BTNB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGap(424, 424, 424)
                                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36))
                                );

                                dlgModTriaje.setAlwaysOnTop(true);
                                dlgModTriaje.setMinimumSize(new java.awt.Dimension(400, 550));

                                tbModifTriaje = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbModifTriaje.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbModifTriaje.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbModifTriaje.setSelectionBackground(new java.awt.Color(0, 118, 168));
                                tbModifTriaje.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbModifTriajeMouseClicked(evt);
                                    }
                                });
                                tbModifTriaje.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbModifTriajeKeyPressed(evt);
                                    }
                                });
                                jScrollPane3.setViewportView(tbModifTriaje);

                                jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                jLabel35.setText("Fecha de Ingreso:");

                                jdtBuscarTriaje.setBackground(new java.awt.Color(255, 255, 255));
                                jdtBuscarTriaje.setDateFormatString("dd/MM/yyyy");

                                btnBuscarMTriaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                btnBuscarMTriaje.setMnemonic('B');
                                btnBuscarMTriaje.setToolTipText("Buscar (Alt + B)");
                                btnBuscarMTriaje.setContentAreaFilled(false);
                                btnBuscarMTriaje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscarMTriaje.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscarMTriajeActionPerformed(evt);
                                    }
                                });

                                jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                jLabel47.setText("Buscar (Alt + B)");

                                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                                jPanel16.setLayout(jPanel16Layout);
                                jPanel16Layout.setHorizontalGroup(
                                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel47)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel16Layout.setVerticalGroup(
                                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel47)
                                        .addContainerGap())
                                );

                                javax.swing.GroupLayout dlgModTriajeLayout = new javax.swing.GroupLayout(dlgModTriaje.getContentPane());
                                dlgModTriaje.getContentPane().setLayout(dlgModTriajeLayout);
                                dlgModTriajeLayout.setHorizontalGroup(
                                    dlgModTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgModTriajeLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(dlgModTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addGroup(dlgModTriajeLayout.createSequentialGroup()
                                                .addComponent(jLabel35)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jdtBuscarTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1)
                                                .addComponent(btnBuscarMTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 113, Short.MAX_VALUE))
                                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addContainerGap())
                                );
                                dlgModTriajeLayout.setVerticalGroup(
                                    dlgModTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgModTriajeLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(dlgModTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel35)
                                            .addComponent(jdtBuscarTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnBuscarMTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                );

                                dlgListaImpDx.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                                dlgListaImpDx.setMinimumSize(new java.awt.Dimension(400, 550));

                                tbDiagnosticos = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbDiagnosticos.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbDiagnosticos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbDiagnosticos.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbDiagnosticosMouseClicked(evt);
                                    }
                                });
                                tbDiagnosticos.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbDiagnosticosKeyPressed(evt);
                                    }
                                });
                                jScrollPane16.setViewportView(tbDiagnosticos);

                                jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                jLabel77.setText("Buscar (Alt + B)");

                                javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                                jPanel20.setLayout(jPanel20Layout);
                                jPanel20Layout.setHorizontalGroup(
                                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel77)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel20Layout.setVerticalGroup(
                                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel77)
                                        .addContainerGap())
                                );

                                jLabel75.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                jLabel75.setText("Buscar diagnóstico:");

                                txtBuscarDiagnostico.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                txtBuscarDiagnostico.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtBuscarDiagnosticoCaretUpdate(evt);
                                    }
                                });
                                txtBuscarDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        txtBuscarDiagnosticoKeyPressed(evt);
                                    }
                                });

                                btnBuscarDiagnostico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                btnBuscarDiagnostico.setMnemonic('B');
                                btnBuscarDiagnostico.setToolTipText("Buscar (Alt + B)");
                                btnBuscarDiagnostico.setContentAreaFilled(false);

                                lblTipoDiag.setForeground(new java.awt.Color(255, 255, 255));
                                lblTipoDiag.setText("jLabel78");

                                javax.swing.GroupLayout dlgListaImpDxLayout = new javax.swing.GroupLayout(dlgListaImpDx.getContentPane());
                                dlgListaImpDx.getContentPane().setLayout(dlgListaImpDxLayout);
                                dlgListaImpDxLayout.setHorizontalGroup(
                                    dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgListaImpDxLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(dlgListaImpDxLayout.createSequentialGroup()
                                                .addComponent(jLabel75)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtBuscarDiagnostico)
                                                .addGap(1, 1, 1)
                                                .addComponent(btnBuscarDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26))
                                            .addGroup(dlgListaImpDxLayout.createSequentialGroup()
                                                .addGroup(dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblTipoDiag))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap())
                                );
                                dlgListaImpDxLayout.setVerticalGroup(
                                    dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgListaImpDxLayout.createSequentialGroup()
                                        .addComponent(lblTipoDiag)
                                        .addGap(9, 9, 9)
                                        .addGroup(dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnBuscarDiagnostico)
                                            .addGroup(dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel75)
                                                .addComponent(txtBuscarDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                );

                                dlgListaExamnAux.setAlwaysOnTop(true);
                                dlgListaExamnAux.setMinimumSize(new java.awt.Dimension(681, 405));

                                tbDatosLab = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbDatosLab.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                tbDatosLab.setForeground(new java.awt.Color(102, 102, 102));
                                tbDatosLab.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Title 1", "Title 2", "Title 3", "Title 4"
                                    }
                                ) {
                                    boolean[] canEdit = new boolean [] {
                                        false, false, false, false
                                    };

                                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return canEdit [columnIndex];
                                    }
                                });
                                tbDatosLab.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbDatosLabMouseClicked(evt);
                                    }
                                });
                                tbDatosLab.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbDatosLabKeyPressed(evt);
                                    }
                                });
                                jScrollPane15.setViewportView(tbDatosLab);

                                jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                jLabel73.setText("Buscar (Alt + B)");

                                lblTipo.setForeground(new java.awt.Color(255, 255, 255));
                                lblTipo.setText("jLabel71");

                                javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                                jPanel17.setLayout(jPanel17Layout);
                                jPanel17Layout.setHorizontalGroup(
                                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel73)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTipo)
                                        .addGap(132, 132, 132))
                                );
                                jPanel17Layout.setVerticalGroup(
                                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel73)
                                            .addComponent(lblTipo))
                                        .addGap(15, 15, 15))
                                );

                                jPanel2.setBackground(new java.awt.Color(0, 118, 168));

                                jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
                                jLabel12.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel12.setText("Exámenes Auxiliares");

                                txtBusquedaTo.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtBusquedaToCaretUpdate(evt);
                                    }
                                });
                                txtBusquedaTo.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        txtBusquedaToKeyPressed(evt);
                                    }
                                });

                                jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                                jButton2.setMnemonic('B');
                                jButton2.setToolTipText("");
                                jButton2.setContentAreaFilled(false);

                                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                jPanel2.setLayout(jPanel2Layout);
                                jPanel2Layout.setHorizontalGroup(
                                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtBusquedaTo))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel2Layout.setVerticalGroup(
                                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(txtBusquedaTo, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                                        .addContainerGap())
                                );

                                javax.swing.GroupLayout dlgListaExamnAuxLayout = new javax.swing.GroupLayout(dlgListaExamnAux.getContentPane());
                                dlgListaExamnAux.getContentPane().setLayout(dlgListaExamnAuxLayout);
                                dlgListaExamnAuxLayout.setHorizontalGroup(
                                    dlgListaExamnAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                );
                                dlgListaExamnAuxLayout.setVerticalGroup(
                                    dlgListaExamnAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgListaExamnAuxLayout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27))
                                );

                                dlgMostrarDatosTriajeT.setAlwaysOnTop(true);
                                dlgMostrarDatosTriajeT.setMinimumSize(new java.awt.Dimension(400, 550));

                                tbMostrarTriajepT = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbMostrarTriajepT.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbMostrarTriajepT.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbMostrarTriajepT.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbMostrarTriajepTMouseClicked(evt);
                                    }
                                });
                                tbMostrarTriajepT.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbMostrarTriajepTKeyPressed(evt);
                                    }
                                });
                                jScrollPane5.setViewportView(tbMostrarTriajepT);

                                jLabel65.setText("Buscar por fecha:");

                                jdtBuscarTriTop.setBackground(new java.awt.Color(255, 255, 255));
                                jdtBuscarTriTop.setDateFormatString("dd/MM/yyyy");
                                jdtBuscarTriTop.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        jdtBuscarTriTopKeyPressed(evt);
                                    }
                                });

                                btnBuscarTriTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                btnBuscarTriTop.setMnemonic('B');
                                btnBuscarTriTop.setContentAreaFilled(false);
                                btnBuscarTriTop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscarTriTop.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscarTriTopActionPerformed(evt);
                                    }
                                });

                                jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                jLabel80.setText("Buscar (Alt + B)");

                                javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
                                jPanel22.setLayout(jPanel22Layout);
                                jPanel22Layout.setHorizontalGroup(
                                    jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel80)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel22Layout.setVerticalGroup(
                                    jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel80)
                                        .addContainerGap())
                                );

                                javax.swing.GroupLayout dlgMostrarDatosTriajeTLayout = new javax.swing.GroupLayout(dlgMostrarDatosTriajeT.getContentPane());
                                dlgMostrarDatosTriajeT.getContentPane().setLayout(dlgMostrarDatosTriajeTLayout);
                                dlgMostrarDatosTriajeTLayout.setHorizontalGroup(
                                    dlgMostrarDatosTriajeTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgMostrarDatosTriajeTLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(dlgMostrarDatosTriajeTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(dlgMostrarDatosTriajeTLayout.createSequentialGroup()
                                                .addGroup(dlgMostrarDatosTriajeTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(dlgMostrarDatosTriajeTLayout.createSequentialGroup()
                                                        .addComponent(jLabel65)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jdtBuscarTriTop, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnBuscarTriTop, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 5, Short.MAX_VALUE)))
                                        .addContainerGap())
                                );
                                dlgMostrarDatosTriajeTLayout.setVerticalGroup(
                                    dlgMostrarDatosTriajeTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgMostrarDatosTriajeTLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(dlgMostrarDatosTriajeTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel65)
                                            .addComponent(jdtBuscarTriTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnBuscarTriTop, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                );

                                dlgMostrarDatosTopico.setMinimumSize(new java.awt.Dimension(730, 650));

                                tbDatosTopico = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbDatosTopico.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbDatosTopico.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbDatosTopico.setSelectionBackground(new java.awt.Color(0, 118, 168));
                                tbDatosTopico.getTableHeader().setReorderingAllowed(false);
                                tbDatosTopico.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbDatosTopicoMouseClicked(evt);
                                    }
                                });
                                tbDatosTopico.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbDatosTopicoKeyPressed(evt);
                                    }
                                });
                                jScrollPane17.setViewportView(tbDatosTopico);

                                tbExamenes = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbExamenes.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Exámenes", "Código", "Precio"
                                    }
                                ) {
                                    boolean[] canEdit = new boolean [] {
                                        false, false, false
                                    };

                                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return canEdit [columnIndex];
                                    }
                                });
                                tbExamenes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbExamenes.setSelectionBackground(new java.awt.Color(0, 118, 168));
                                tbExamenes.getTableHeader().setReorderingAllowed(false);
                                tbExamenes.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbExamenesKeyPressed(evt);
                                    }
                                });
                                jScrollPane18.setViewportView(tbExamenes);

                                tbDiagPresun = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbDiagPresun.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Número", "Código", "Diagnósticos"
                                    }
                                ) {
                                    boolean[] canEdit = new boolean [] {
                                        false, false, false
                                    };

                                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return canEdit [columnIndex];
                                    }
                                });
                                tbDiagPresun.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbDiagPresun.setSelectionBackground(new java.awt.Color(0, 118, 168));
                                tbDiagPresun.getTableHeader().setReorderingAllowed(false);
                                tbDiagPresun.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbDiagPresunKeyPressed(evt);
                                    }
                                });
                                jScrollPane19.setViewportView(tbDiagPresun);

                                tbDiagFinal = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbDiagFinal.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Nro", "Código", "Diagnósticos"
                                    }
                                ) {
                                    boolean[] canEdit = new boolean [] {
                                        false, false, true
                                    };

                                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return canEdit [columnIndex];
                                    }
                                });
                                tbDiagFinal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbDiagFinal.setSelectionBackground(new java.awt.Color(0, 118, 168));
                                tbDiagFinal.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbDiagFinalKeyPressed(evt);
                                    }
                                });
                                jScrollPane20.setViewportView(tbDiagFinal);

                                btnAddExam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                                btnAddExam.setMnemonic('E');
                                btnAddExam.setToolTipText("Alt + E");
                                btnAddExam.setContentAreaFilled(false);
                                btnAddExam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnAddExam.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnAddExamActionPerformed(evt);
                                    }
                                });

                                btnDelExam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                                btnDelExam.setContentAreaFilled(false);
                                btnDelExam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnDelExam.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnDelExamActionPerformed(evt);
                                    }
                                });
                                btnDelExam.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        btnDelExamKeyPressed(evt);
                                    }
                                });

                                btnAddDiagP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                                btnAddDiagP.setMnemonic('P');
                                btnAddDiagP.setToolTipText("Alt + P");
                                btnAddDiagP.setContentAreaFilled(false);
                                btnAddDiagP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnAddDiagP.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnAddDiagPActionPerformed(evt);
                                    }
                                });

                                btnDelDiagP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                                btnDelDiagP.setContentAreaFilled(false);
                                btnDelDiagP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnDelDiagP.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnDelDiagPActionPerformed(evt);
                                    }
                                });
                                btnDelDiagP.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        btnDelDiagPKeyPressed(evt);
                                    }
                                });

                                btnAddDiagF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                                btnAddDiagF.setMnemonic('D');
                                btnAddDiagF.setToolTipText("Alt + D");
                                btnAddDiagF.setContentAreaFilled(false);
                                btnAddDiagF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnAddDiagF.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnAddDiagFActionPerformed(evt);
                                    }
                                });

                                btnDelDiagF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                                btnDelDiagF.setContentAreaFilled(false);
                                btnDelDiagF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnDelDiagF.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnDelDiagFActionPerformed(evt);
                                    }
                                });

                                lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
                                lblUsuario.setText("jLabel71");

                                jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                jLabel71.setText("Buscar por fecha de ingreso:");

                                jdtFechaTop.setBackground(new java.awt.Color(255, 255, 255));
                                jdtFechaTop.setDateFormatString("dd/MM/yyyy");
                                jdtFechaTop.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        jdtFechaTopKeyPressed(evt);
                                    }
                                });

                                btnBuscarTo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                btnBuscarTo.setMnemonic('B');
                                btnBuscarTo.setToolTipText("Buscar (Alt + B)");
                                btnBuscarTo.setContentAreaFilled(false);
                                btnBuscarTo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscarTo.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscarToActionPerformed(evt);
                                    }
                                });

                                jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                                jLabel79.setText("Buscar (Alt + B)");

                                javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                                jPanel21.setLayout(jPanel21Layout);
                                jPanel21Layout.setHorizontalGroup(
                                    jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel79)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel21Layout.setVerticalGroup(
                                    jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel79)
                                        .addContainerGap())
                                );

                                javax.swing.GroupLayout dlgMostrarDatosTopicoLayout = new javax.swing.GroupLayout(dlgMostrarDatosTopico.getContentPane());
                                dlgMostrarDatosTopico.getContentPane().setLayout(dlgMostrarDatosTopicoLayout);
                                dlgMostrarDatosTopicoLayout.setHorizontalGroup(
                                    dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jdtFechaTop, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(btnBuscarTo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblUsuario)
                                                .addGap(78, 78, 78))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                                                    .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                        .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(btnAddExam, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(btnDelExam, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                                                            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btnAddDiagP, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btnDelDiagP, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btnAddDiagF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btnDelDiagF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(19, 19, 19)))
                                                .addContainerGap())))
                                );
                                dlgMostrarDatosTopicoLayout.setVerticalGroup(
                                    dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblUsuario)
                                                .addComponent(jLabel71))
                                            .addComponent(jdtFechaTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnBuscarTo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                            .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(btnAddDiagP)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDelDiagP))
                                            .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(btnAddExam)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDelExam)))
                                        .addGroup(dlgMostrarDatosTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(dlgMostrarDatosTopicoLayout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(btnAddDiagF)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDelDiagF)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(20, Short.MAX_VALUE))
                                );

                                dlgBuscarCPT.setAlwaysOnTop(true);
                                dlgBuscarCPT.setMinimumSize(new java.awt.Dimension(749, 338));
                                dlgBuscarCPT.setResizable(false);

                                jPanel12.setBackground(new java.awt.Color(23, 160, 134));
                                jPanel12.setMinimumSize(new java.awt.Dimension(310, 441));

                                jLabel74.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                jLabel74.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel74.setText("Prioridad");

                                jPanel31.setBackground(new java.awt.Color(255, 255, 255));

                                txtBuscar2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                txtBuscar2.setForeground(new java.awt.Color(98, 98, 98));
                                txtBuscar2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
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

                                javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                                jPanel31.setLayout(jPanel31Layout);
                                jPanel31Layout.setHorizontalGroup(
                                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                );
                                jPanel31Layout.setVerticalGroup(
                                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                );

                                btnBuscarPaciente3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                btnBuscarPaciente3.setContentAreaFilled(false);
                                btnBuscarPaciente3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscarPaciente3.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscarPaciente3ActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                jPanel12.setLayout(jPanel12Layout);
                                jPanel12Layout.setHorizontalGroup(
                                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel74)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(488, Short.MAX_VALUE))
                                );
                                jPanel12Layout.setVerticalGroup(
                                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel74)
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(356, 356, 356))
                                );

                                jPanel36.setBackground(new java.awt.Color(23, 160, 134));
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

                                jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                jLabel52.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel52.setText(" DESCRIPCIÓN");

                                jScrollPane21.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tb_Grupo4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
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
                                tb_Grupo4.setSelectionBackground(new java.awt.Color(102, 102, 102));
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
                                jScrollPane21.setViewportView(tb_Grupo4);

                                javax.swing.GroupLayout dlgBuscarCPTLayout = new javax.swing.GroupLayout(dlgBuscarCPT.getContentPane());
                                dlgBuscarCPT.getContentPane().setLayout(dlgBuscarCPTLayout);
                                dlgBuscarCPTLayout.setHorizontalGroup(
                                    dlgBuscarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                                    .addGroup(dlgBuscarCPTLayout.createSequentialGroup()
                                        .addGroup(dlgBuscarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane21)
                                );
                                dlgBuscarCPTLayout.setVerticalGroup(
                                    dlgBuscarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgBuscarCPTLayout.createSequentialGroup()
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                                );

                                BHC.setAlwaysOnTop(true);
                                BHC.setMinimumSize(new java.awt.Dimension(749, 350));
                                BHC.setResizable(false);

                                jPanel15.setBackground(new java.awt.Color(23, 160, 134));
                                jPanel15.setMinimumSize(new java.awt.Dimension(310, 441));

                                jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel19.setText("Paciente");

                                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel14.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel14.setText("Busqueda por DNI, H.C. y Apellidos");

                                bus.setForeground(new java.awt.Color(23, 160, 134));
                                bus.setText("jLabel37");

                                jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                                txtBuscarPaciente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                txtBuscarPaciente.setForeground(new java.awt.Color(98, 98, 98));
                                txtBuscarPaciente.setBorder(javax.swing.BorderFactory.createCompoundBorder());
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

                                bus3.setForeground(new java.awt.Color(23, 160, 134));
                                bus3.setText("jLabel37");

                                btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                btnBuscarPaciente2.setContentAreaFilled(false);
                                btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscarPaciente2ActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                                jPanel15.setLayout(jPanel15Layout);
                                jPanel15Layout.setHorizontalGroup(
                                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(39, 39, 39)
                                                .addComponent(bus)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bus3))
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel15Layout.setVerticalGroup(
                                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel19)
                                                .addGap(10, 10, 10)
                                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(bus)
                                                    .addComponent(bus3))))
                                        .addGap(331, 331, 331))
                                );

                                jPanel26.setBackground(new java.awt.Color(255, 255, 255));

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
                                jScrollPane22.setViewportView(ABONOS);

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
                                jScrollPane23.setViewportView(tbpreventas);

                                tbpreventasFR.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbpreventasFR.setGridColor(new java.awt.Color(255, 255, 255));
                                tbpreventasFR.setRowHeight(25);
                                tbpreventasFR.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                tbpreventasFR.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbpreventasFRMouseClicked(evt);
                                    }
                                });
                                tbpreventasFR.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbpreventasFRKeyPressed(evt);
                                    }
                                });
                                jScrollPane27.setViewportView(tbpreventasFR);

                                javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
                                jPanel26.setLayout(jPanel26Layout);
                                jPanel26Layout.setHorizontalGroup(
                                    jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel26Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel26Layout.createSequentialGroup()
                                                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblIdPreventa))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel26Layout.setVerticalGroup(
                                    jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel26Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                            .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblIdPreventa)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                panelBuscarHC.setBackground(new java.awt.Color(255, 255, 255));

                                jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 34)); // NOI18N
                                jLabel15.setForeground(new java.awt.Color(102, 102, 102));
                                jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Encuentra Hombre Usuario-80.png"))); // NOI18N
                                jLabel15.setText("Busqueda de Pacientes ");

                                javax.swing.GroupLayout panelBuscarHCLayout = new javax.swing.GroupLayout(panelBuscarHC);
                                panelBuscarHC.setLayout(panelBuscarHCLayout);
                                panelBuscarHCLayout.setHorizontalGroup(
                                    panelBuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBuscarHCLayout.createSequentialGroup()
                                        .addGap(134, 134, 134)
                                        .addComponent(jLabel15)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                panelBuscarHCLayout.setVerticalGroup(
                                    panelBuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBuscarHCLayout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jLabel15)
                                        .addContainerGap(50, Short.MAX_VALUE))
                                );

                                paneltablaHC.setBackground(new java.awt.Color(255, 255, 255));

                                jScrollPane24.setBackground(new java.awt.Color(255, 255, 255));
                                jScrollPane24.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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
                                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                                        tbPacientesMouseEntered(evt);
                                    }
                                });
                                tbPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbPacientesKeyPressed(evt);
                                    }
                                });
                                jScrollPane24.setViewportView(tbPacientes);

                                jPanel35.setBackground(new java.awt.Color(23, 160, 134));
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

                                jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                jLabel45.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel45.setText("Nº H.C.");

                                jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                jLabel51.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel51.setText(" DNI");

                                jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                jLabel86.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel86.setText("Paciente");

                                javax.swing.GroupLayout paneltablaHCLayout = new javax.swing.GroupLayout(paneltablaHC);
                                paneltablaHC.setLayout(paneltablaHCLayout);
                                paneltablaHCLayout.setHorizontalGroup(
                                    paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                                    .addGroup(paneltablaHCLayout.createSequentialGroup()
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(372, Short.MAX_VALUE))
                                    .addComponent(jScrollPane24)
                                );
                                paneltablaHCLayout.setVerticalGroup(
                                    paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paneltablaHCLayout.createSequentialGroup()
                                        .addGroup(paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                );

                                panelSinHC.setBackground(new java.awt.Color(255, 255, 255));

                                jLabel46.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                jLabel46.setForeground(new java.awt.Color(102, 102, 102));
                                jLabel46.setText("No se hallaron coincidencias");

                                jLabel87.setFont(new java.awt.Font("Segoe UI Light", 0, 100)); // NOI18N
                                jLabel87.setForeground(new java.awt.Color(23, 160, 134));
                                jLabel87.setText(":(");

                                jPanel28.setBackground(new java.awt.Color(25, 188, 157));

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

                                jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                jLabel88.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                jLabel88.setText("Nueva Historia Clinica");

                                javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                                jPanel28.setLayout(jPanel28Layout);
                                jPanel28Layout.setHorizontalGroup(
                                    jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                            .addComponent(btnNuevo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 23, Short.MAX_VALUE))
                                );
                                jPanel28Layout.setVerticalGroup(
                                    jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(btnNuevo1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(47, Short.MAX_VALUE))
                                );

                                javax.swing.GroupLayout panelSinHCLayout = new javax.swing.GroupLayout(panelSinHC);
                                panelSinHC.setLayout(panelSinHCLayout);
                                panelSinHCLayout.setHorizontalGroup(
                                    panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelSinHCLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel87)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel46)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                );
                                panelSinHCLayout.setVerticalGroup(
                                    panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelSinHCLayout.createSequentialGroup()
                                        .addGroup(panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel87))
                                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                                .addGap(87, 87, 87)
                                                .addComponent(jLabel46)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                );

                                javax.swing.GroupLayout BHCLayout = new javax.swing.GroupLayout(BHC.getContentPane());
                                BHC.getContentPane().setLayout(BHCLayout);
                                BHCLayout.setHorizontalGroup(
                                    BHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelBuscarHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(paneltablaHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelSinHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                );
                                BHCLayout.setVerticalGroup(
                                    BHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(BHCLayout.createSequentialGroup()
                                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(panelBuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(paneltablaHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(panelSinHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                );

                                jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones.png"))); // NOI18N
                                jMenuItem4.setText("Opciones");
                                jpmCabecera.add(jMenuItem4);
                                jpmCabecera.add(jSeparator9);

                                jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/imprimir16x16.png"))); // NOI18N
                                jMenuItem5.setMnemonic('P');
                                jMenuItem5.setText("Imprimir");
                                jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        jMenuItem5ActionPerformed(evt);
                                    }
                                });
                                jpmCabecera.add(jMenuItem5);

                                Jerarquias.setAlwaysOnTop(true);
                                Jerarquias.setMinimumSize(new java.awt.Dimension(628, 300));
                                Jerarquias.setResizable(false);

                                jPanel11.setBackground(new java.awt.Color(23, 160, 134));
                                jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                                jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                jLabel22.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel22.setText("Forma de Pago");

                                jPanel30.setBackground(new java.awt.Color(255, 255, 255));

                                txtBuscarFormaPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                txtBuscarFormaPago.setBorder(javax.swing.BorderFactory.createCompoundBorder());
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

                                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                                jPanel30.setLayout(jPanel30Layout);
                                jPanel30Layout.setHorizontalGroup(
                                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel30Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtBuscarFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                        .addContainerGap())
                                );
                                jPanel30Layout.setVerticalGroup(
                                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
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

                                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                                jPanel11.setLayout(jPanel11Layout);
                                jPanel11Layout.setHorizontalGroup(
                                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel11Layout.setVerticalGroup(
                                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(352, 352, 352))
                                );

                                jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
                                jScrollPane6.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tb_FP.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                tb_FP.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_FP.setGridColor(new java.awt.Color(255, 255, 255));
                                tb_FP.setRowHeight(25);
                                tb_FP.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                tb_FP.getTableHeader().setReorderingAllowed(false);
                                tb_FP.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tb_FPMouseClicked(evt);
                                    }
                                });
                                tb_FP.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tb_FPKeyPressed(evt);
                                    }
                                });
                                jScrollPane6.setViewportView(tb_FP);

                                javax.swing.GroupLayout JerarquiasLayout = new javax.swing.GroupLayout(Jerarquias.getContentPane());
                                Jerarquias.getContentPane().setLayout(JerarquiasLayout);
                                JerarquiasLayout.setHorizontalGroup(
                                    JerarquiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                );
                                JerarquiasLayout.setVerticalGroup(
                                    JerarquiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JerarquiasLayout.createSequentialGroup()
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                        .addGap(0, 0, 0))
                                );

                                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                setTitle("Admisión emergencia");
                                addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        formKeyPressed(evt);
                                    }
                                });

                                jPanel8.setBackground(new java.awt.Color(232, 76, 61));
                                jPanel8.setPreferredSize(new java.awt.Dimension(285, 415));

                                btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
                                btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
                                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
                                btnNuevo.setText("Nuevo");
                                btnNuevo.setContentAreaFilled(false);
                                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnNuevo.setFocusable(false);
                                btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnNuevo.setIconTextGap(30);
                                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnNuevoActionPerformed(evt);
                                    }
                                });

                                btnModificar.setBackground(new java.awt.Color(204, 204, 204));
                                btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnModificar.setForeground(new java.awt.Color(255, 255, 255));
                                btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
                                btnModificar.setText("Editar");
                                btnModificar.setContentAreaFilled(false);
                                btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnModificar.setFocusable(false);
                                btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnModificar.setIconTextGap(30);
                                btnModificar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnModificarActionPerformed(evt);
                                    }
                                });

                                btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
                                btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
                                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                                btnGuardar.setText("Guardar");
                                btnGuardar.setContentAreaFilled(false);
                                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnGuardar.setEnabled(false);
                                btnGuardar.setFocusable(false);
                                btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnGuardar.setIconTextGap(30);
                                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnGuardarActionPerformed(evt);
                                    }
                                });

                                btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
                                btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
                                btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
                                btnEliminar.setText("Eliminar");
                                btnEliminar.setContentAreaFilled(false);
                                btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnEliminar.setEnabled(false);
                                btnEliminar.setFocusable(false);
                                btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnEliminar.setIconTextGap(30);
                                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnEliminarActionPerformed(evt);
                                    }
                                });

                                btnBuscar.setBackground(new java.awt.Color(204, 204, 204));
                                btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
                                btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Orden de compra-32.png"))); // NOI18N
                                btnBuscar.setText("Listado");
                                btnBuscar.setContentAreaFilled(false);
                                btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscar.setFocusable(false);
                                btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnBuscar.setIconTextGap(30);
                                btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscarActionPerformed(evt);
                                    }
                                });

                                lblNewMod.setText("jLabel39");

                                lblCabpT.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
                                lblCabpT.setText("jLabel71");

                                lblIDHCTo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
                                lblIDHCTo.setForeground(new java.awt.Color(255, 255, 255));
                                lblIDHCTo.setText("jLabel65");

                                lblIdFP.setText("jLabel51");

                                lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                                lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                                lblusu.setText("Silvana");
                                lblusu.setFocusable(false);
                                lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                jLabel84.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                jLabel84.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel84.setText("<html>Admisión<span style=\"font-size:'14px'\"><br>Emergencia</br></span></html>");

                                lblId_Preventa.setForeground(new java.awt.Color(232, 76, 61));
                                lblId_Preventa.setText("jLabel1");

                                lblTriajeId.setForeground(new java.awt.Color(232, 76, 61));
                                lblTriajeId.setText("jLabel1");

                                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                jPanel8.setLayout(jPanel8Layout);
                                jPanel8Layout.setHorizontalGroup(
                                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 35, Short.MAX_VALUE)))
                                        .addContainerGap())
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTriajeId)
                                            .addComponent(lblId_Preventa)
                                            .addComponent(lblNewMod)
                                            .addComponent(lblIDHCTo)
                                            .addComponent(lblIdFP)
                                            .addComponent(lblCabpT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel8Layout.setVerticalGroup(
                                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(btnNuevo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnGuardar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscar)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblCabpT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblIdFP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblIDHCTo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblNewMod)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblId_Preventa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTriajeId)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                );

                                tbPaneles.setForeground(new java.awt.Color(255, 255, 255));
                                tbPaneles.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                                tbPaneles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                tbPaneles.setFont(new java.awt.Font("Segoe UI Light", 0, 1)); // NOI18N
                                tbPaneles.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbPanelesMouseClicked(evt);
                                    }
                                });

                                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                                jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        jPanel1MouseClicked(evt);
                                    }
                                });

                                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel4.setText("Nº H.C.");

                                jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                                jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtNHC.setEditable(false);
                                txtNHC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                txtNHC.setBorder(null);
                                txtNHC.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtNHCCaretUpdate(evt);
                                    }
                                });
                                txtNHC.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        txtNHCKeyPressed(evt);
                                    }
                                });

                                btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                btnFiltrar.setToolTipText("Buscar Nº H.C. (Alt + B)");
                                btnFiltrar.setContentAreaFilled(false);
                                btnFiltrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnFiltrar.setEnabled(false);
                                btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnFiltrarActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                                jPanel6.setLayout(jPanel6Layout);
                                jPanel6Layout.setHorizontalGroup(
                                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(txtNHC, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0))
                                );
                                jPanel6Layout.setVerticalGroup(
                                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNHC, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                            .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                pnlDatosCabecera.setBackground(new java.awt.Color(255, 255, 255));

                                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel5.setText("Apellidos y Nombres");

                                lblApNom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblApNom.setText("jLabel6");

                                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel6.setText("Género");

                                lblGenero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblGenero.setText("jLabel7");

                                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel7.setText("Edad");

                                lblEdad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblEdad.setText("jLabel8");

                                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel8.setText("Fecha de Nacimiento");

                                lblFechaNac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblFechaNac.setText("jLabel9");

                                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel9.setText("Estado Civil");

                                lblEstcivil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblEstcivil.setText("jLabel10");

                                lblDni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblDni.setText("jLabel17");

                                jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel17.setText("Distrito");

                                lblDistrito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblDistrito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                lblDistrito.setText("jLabel18");

                                jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel18.setText("Provincia");

                                lblProvincia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblProvincia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                lblProvincia.setText("jLabel20");

                                jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel20.setText("Departamento");

                                lblDepartamento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblDepartamento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                lblDepartamento.setText("jLabel21");

                                jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel10.setText("Dirección");

                                lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblDireccion.setText("jLabel11");

                                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel11.setText("Sector");

                                lblSector.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblSector.setText("W");

                                lblEstado.setForeground(new java.awt.Color(255, 255, 255));
                                lblEstado.setText("A");

                                lblIDHC.setForeground(new java.awt.Color(255, 255, 255));
                                lblIDHC.setText("jLabel12");

                                lblHoraIM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblHoraIM.setText("Hora de Ingreso");

                                lblHoraIng.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblHoraIng.setText("jLabel43");

                                lblFechaIng.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblFechaIng.setText("jLabel43");

                                jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel31.setText("Traído Por");

                                jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel32.setText("Parentesco");

                                jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel41.setText("Forma de LLegada");

                                cbxFormaLlegada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                cbxFormaLlegada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Caminando" }));

                                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel3.setText("Observación");

                                lblFechaIM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblFechaIM.setText("Fecha de Ingreso");

                                panelFormaPago.setBackground(new java.awt.Color(255, 255, 255));
                                panelFormaPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                btnBuscarCPT.setContentAreaFilled(false);
                                btnBuscarCPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscarCPTActionPerformed(evt);
                                    }
                                });

                                txtCPTDES.setEditable(false);
                                txtCPTDES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                txtCPTDES.setForeground(new java.awt.Color(51, 51, 51));
                                txtCPTDES.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txtCPTDES.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                txtCPTDES.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                                txtCPTDES.setSelectionColor(new java.awt.Color(255, 255, 255));
                                txtCPTDES.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtCPTDESCaretUpdate(evt);
                                    }
                                });

                                javax.swing.GroupLayout panelFormaPagoLayout = new javax.swing.GroupLayout(panelFormaPago);
                                panelFormaPago.setLayout(panelFormaPagoLayout);
                                panelFormaPagoLayout.setHorizontalGroup(
                                    panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txtCPTDES)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                );
                                panelFormaPagoLayout.setVerticalGroup(
                                    panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addGroup(panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCPTDES)
                                            .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                                .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap())
                                );

                                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel2.setText("Prioridad");

                                panelFormaPago1.setBackground(new java.awt.Color(255, 255, 255));
                                panelFormaPago1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtFormaPago.setEditable(false);
                                txtFormaPago.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtFormaPago.setForeground(new java.awt.Color(51, 51, 51));
                                txtFormaPago.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txtFormaPago.setBorder(javax.swing.BorderFactory.createCompoundBorder());
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

                                javax.swing.GroupLayout panelFormaPago1Layout = new javax.swing.GroupLayout(panelFormaPago1);
                                panelFormaPago1.setLayout(panelFormaPago1Layout);
                                panelFormaPago1Layout.setHorizontalGroup(
                                    panelFormaPago1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormaPago1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txtFormaPago)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                );
                                panelFormaPago1Layout.setVerticalGroup(
                                    panelFormaPago1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormaPago1Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addGroup(panelFormaPago1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                            .addComponent(btnBuscarFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel33.setText("Forma de Pago");

                                lblFP.setForeground(new java.awt.Color(255, 255, 255));
                                lblFP.setText("jLabel1");

                                panelFormaPago2.setBackground(new java.awt.Color(255, 255, 255));
                                panelFormaPago2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtParentesco.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtParentesco.setForeground(new java.awt.Color(51, 51, 51));
                                txtParentesco.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txtParentesco.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                txtParentesco.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                                txtParentesco.setSelectionColor(new java.awt.Color(255, 255, 255));
                                txtParentesco.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtParentescoCaretUpdate(evt);
                                    }
                                });
                                txtParentesco.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtParentescoKeyReleased(evt);
                                    }
                                });

                                javax.swing.GroupLayout panelFormaPago2Layout = new javax.swing.GroupLayout(panelFormaPago2);
                                panelFormaPago2.setLayout(panelFormaPago2Layout);
                                panelFormaPago2Layout.setHorizontalGroup(
                                    panelFormaPago2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormaPago2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txtParentesco, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addContainerGap())
                                );
                                panelFormaPago2Layout.setVerticalGroup(
                                    panelFormaPago2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormaPago2Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                panelFormaPago3.setBackground(new java.awt.Color(255, 255, 255));
                                panelFormaPago3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtTraidopor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtTraidopor.setForeground(new java.awt.Color(51, 51, 51));
                                txtTraidopor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txtTraidopor.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                txtTraidopor.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                                txtTraidopor.setSelectionColor(new java.awt.Color(255, 255, 255));
                                txtTraidopor.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtTraidoporCaretUpdate(evt);
                                    }
                                });
                                txtTraidopor.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtTraidoporKeyReleased(evt);
                                    }
                                });

                                javax.swing.GroupLayout panelFormaPago3Layout = new javax.swing.GroupLayout(panelFormaPago3);
                                panelFormaPago3.setLayout(panelFormaPago3Layout);
                                panelFormaPago3Layout.setHorizontalGroup(
                                    panelFormaPago3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormaPago3Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txtTraidopor)
                                        .addContainerGap())
                                );
                                panelFormaPago3Layout.setVerticalGroup(
                                    panelFormaPago3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormaPago3Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtTraidopor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                panelFormaPago4.setBackground(new java.awt.Color(255, 255, 255));
                                panelFormaPago4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                pnlEObservación.setBorder(null);
                                pnlEObservación.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                pnlEObservación.setForeground(new java.awt.Color(51, 51, 51));
                                pnlEObservación.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        pnlEObservaciónKeyReleased(evt);
                                    }
                                });
                                jScrollPane4.setViewportView(pnlEObservación);

                                javax.swing.GroupLayout panelFormaPago4Layout = new javax.swing.GroupLayout(panelFormaPago4);
                                panelFormaPago4.setLayout(panelFormaPago4Layout);
                                panelFormaPago4Layout.setHorizontalGroup(
                                    panelFormaPago4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4)
                                );
                                panelFormaPago4Layout.setVerticalGroup(
                                    panelFormaPago4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                );

                                txtCPT.setForeground(new java.awt.Color(255, 255, 255));
                                txtCPT.setText("jLabel1");

                                jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel16.setText("DNI");

                                javax.swing.GroupLayout pnlDatosCabeceraLayout = new javax.swing.GroupLayout(pnlDatosCabecera);
                                pnlDatosCabecera.setLayout(pnlDatosCabeceraLayout);
                                pnlDatosCabeceraLayout.setHorizontalGroup(
                                    pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(15, 15, 15)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                .addComponent(lblFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(60, 60, 60)
                                                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblApNom, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(61, 61, 61)
                                                .addComponent(lblIDHC)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEstcivil, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(121, 121, 121))
                                    .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(jLabel20)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel17))
                                                .addGap(53, 53, 53)
                                                .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                        .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel11)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblSector, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblFechaIM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(29, 29, 29)
                                                .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                        .addComponent(lblFechaIng)
                                                        .addGap(26, 26, 26)
                                                        .addComponent(lblHoraIM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(lblHoraIng)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblFP)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtCPT))
                                                    .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(cbxFormaLlegada, 0, 506, Short.MAX_VALUE)
                                                            .addComponent(panelFormaPago3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(panelFormaPago1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelFormaPago2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(panelFormaPago4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(panelFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addContainerGap(26, Short.MAX_VALUE))
                                    .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                        .addComponent(jSeparator8)
                                        .addContainerGap())
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                                );
                                pnlDatosCabeceraLayout.setVerticalGroup(
                                    pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6)
                                                .addComponent(lblGenero))
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblDni)
                                                .addComponent(lblIDHC)
                                                .addComponent(jLabel16)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(lblEstcivil)
                                            .addComponent(lblApNom)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(lblEdad)
                                            .addComponent(jLabel8)
                                            .addComponent(lblFechaNac)
                                            .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(lblDistrito))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel18)
                                            .addComponent(lblProvincia))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel20)
                                            .addComponent(lblDepartamento))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(lblDireccion)
                                            .addComponent(jLabel11)
                                            .addComponent(lblSector))
                                        .addGap(5, 5, 5)
                                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(panelFormaPago2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panelFormaPago3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(panelFormaPago1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbxFormaLlegada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2))
                                            .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                .addComponent(panelFormaPago4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(panelFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblFechaIM)
                                            .addComponent(lblFechaIng)
                                            .addComponent(lblHoraIM)
                                            .addComponent(lblHoraIng)
                                            .addComponent(lblFP)
                                            .addComponent(txtCPT))
                                        .addGap(21, 21, 21))
                                );

                                jLabel21.setForeground(new java.awt.Color(102, 102, 102));
                                jLabel21.setText("Alt + B");

                                txtNroRegistro.setForeground(new java.awt.Color(255, 255, 255));
                                txtNroRegistro.setText("21");

                                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                jPanel1.setLayout(jPanel1Layout);
                                jPanel1Layout.setHorizontalGroup(
                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pnlDatosCabecera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(99, 99, 99)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtNroRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(90, 90, 90)))
                                        .addContainerGap())
                                );
                                jPanel1Layout.setVerticalGroup(
                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNroRegistro, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, 0)
                                        .addComponent(pnlDatosCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(5, 5, 5))
                                );

                                tbPaneles.addTab("Cabecera", jPanel1);

                                jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                                jScrollPane25.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tbCabecera = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false;
                                    }
                                };
                                tbCabecera.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                tbCabecera.setForeground(new java.awt.Color(51, 51, 51));
                                tbCabecera.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbCabecera.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbCabecera.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                tbCabecera.getTableHeader().setReorderingAllowed(false);
                                tbCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbCabeceraMouseClicked(evt);
                                    }
                                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                                        tbCabeceraMouseReleased(evt);
                                    }
                                });
                                tbCabecera.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbCabeceraKeyPressed(evt);
                                    }
                                });
                                jScrollPane25.setViewportView(tbCabecera);

                                panelBusqueda.setBackground(new java.awt.Color(22, 22, 22));

                                btnBuscar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                btnBuscar1.setForeground(new java.awt.Color(255, 255, 255));
                                btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/continuar.png"))); // NOI18N
                                btnBuscar1.setMnemonic('B');
                                btnBuscar1.setText("Iniciar");
                                btnBuscar1.setToolTipText("Buscar (Alt + B)");
                                btnBuscar1.setContentAreaFilled(false);
                                btnBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscar1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                                btnBuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscar1ActionPerformed(evt);
                                    }
                                });

                                txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtBuscar.setForeground(new java.awt.Color(102, 102, 102));
                                txtBuscar.setEnabled(false);
                                txtBuscar.setSelectionColor(new java.awt.Color(204, 204, 204));
                                txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtBuscarCaretUpdate(evt);
                                    }
                                });
                                txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        txtBuscarKeyPressed(evt);
                                    }
                                });

                                fechai.setBackground(new java.awt.Color(22, 22, 22));
                                fechai.setForeground(new java.awt.Color(102, 102, 102));
                                fechai.setDateFormatString("dd/MM/yyyy");
                                fechai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                                fechaf.setBackground(new java.awt.Color(22, 22, 22));
                                fechaf.setForeground(new java.awt.Color(102, 102, 102));
                                fechaf.setDateFormatString("dd/MM/yyyy");
                                fechaf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                                javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
                                panelBusqueda.setLayout(panelBusquedaLayout);
                                panelBusquedaLayout.setHorizontalGroup(
                                    panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBusquedaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(fechai, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar1)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                panelBusquedaLayout.setVerticalGroup(
                                    panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBusquedaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(fechai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap())
                                );

                                javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                                jPanel29.setLayout(jPanel29Layout);
                                jPanel29Layout.setHorizontalGroup(
                                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                                    .addComponent(panelBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                );
                                jPanel29Layout.setVerticalGroup(
                                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(panelBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                                );

                                tbPaneles.addTab("tab4", jPanel29);

                                jPanel10.setBackground(new java.awt.Color(43, 43, 43));
                                jPanel10.setPreferredSize(new java.awt.Dimension(929, 115));

                                jLabel36.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                jLabel36.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel36.setText("Nota de Emergencia");

                                jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                jLabel42.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel42.setText("Pago Confirmado");
                                jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        jLabel42MouseClicked(evt);
                                    }
                                });

                                jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                jLabel43.setForeground(new java.awt.Color(153, 153, 153));
                                jLabel43.setText("Pendientes de Pago");
                                jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        jLabel43MouseClicked(evt);
                                    }
                                });

                                lblEFP.setForeground(new java.awt.Color(43, 43, 43));
                                lblEFP.setText("jLabel1");

                                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                                jPanel10.setLayout(jPanel10Layout);
                                jPanel10Layout.setHorizontalGroup(
                                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblEFP)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel10Layout.setVerticalGroup(
                                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEFP))
                                        .addContainerGap())
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
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 1039, Short.MAX_VALUE)
                                            .addComponent(tbPaneles)
                                            .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                );
                                layout.setVerticalGroup(
                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tbPaneles))
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                                );

                                pack();
                            }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        filtrarDatos();
        cbxTipoBusqueda.requestFocus();
        lblPestana.setText("C");
        tbPaciente.getSelectionModel().setSelectionInterval(0,0);
        //tbPaciente.getSelectionModel().setSelectionInterval(0,0);
        //tbPaciente.requestFocus();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
            if(tbPaneles.getSelectedIndex()==0){
                if(lblNewMod.getText().equals("N")){//NUEVO REGISTRO CAJA_PREVENTA
                    if(txtTraidopor.getText().equals("") || txtParentesco.getText().equals("") 
                            || cbxFormaLlegada.getSelectedIndex()==0 || txtCPT.getText().equals("")){
                        JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
                        txtTraidopor.setBackground(new Color(153,204,205));
                        txtParentesco.setBackground(new Color(153,204,205));
                        cbxFormaLlegada.setForeground(Color.red);
                        txtCPT.setBackground(new Color(153,204,205));
  
                    } else {//INSERTAR CAJA_PREVENTA COMO EMERGENCIA
             
                            Caja_Preventa caja1 = new Caja_Preventa();
                            caja1.setId_hc(lblIDHC.getText());
                            caja1.setEMER_OBSERVACION(pnlEObservación.getText());
                            caja1.setEMER_TRAIDOPOR(txtTraidopor.getText());
                            caja1.setEMER_PARENTESCO(txtParentesco.getText());
                            caja1.setEMER_FORMA_LLEGADA_ID(caja1.codFormaLlegada(cbxFormaLlegada.getSelectedItem().toString()));
                            caja1.setCod_usu(caja1.codUsuario(lblusu.getText()));
                            caja1.setCod_jerar_forma_pago(lblFP.getText());
                            caja1.setCod_nomen(txtCPT.getText());
                            if(caja1.mantanimientoCajaPreventaEmergencia("I")==true){
                                Caja_Preventa adEmer = new Caja_Preventa();
                                adEmer.PreventaID();
                                Actualizar_ID_PREVENTA();
                                //VISUALIZAR EL REPORTE
//                                String ruta = "/reportes/admisionEmergencia/formatoEmergencia-Cabecera.jasper";
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Guardados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=1;
                                lblEFP.setText("P");
                                Caja_Preventa CPL = new Caja_Preventa(); 
                                CPL.listarDatosEmergenciaP(lblusu.getText(), tbCabecera);
                                tbPaneles.setSelectedIndex(1);
                                Actualizar_ESTADO_PREVENTA();
//                              adEmerCab.reporteCabecera(ruta, Integer.parseInt(txtNroRegistro.getText()));
                                AdmisionEmergenciaCabecera cab1 = new AdmisionEmergenciaCabecera();
                                cab1.reporteCabecera(Integer.parseInt(lblId_Preventa.getText()));
                                pnlDatosCabecera.setVisible(false);
                                limpiar();
                                txtNHC.setText("");
                                txtNHC.setEnabled(false);
                                txtNroRegistro.setText("");
                                jLabel42.setVisible(true);
                                jLabel43.setVisible(true);
                                panelBusqueda.setVisible(false);
                                jLabel42.setForeground(new Color(153,153,153));
                                jLabel43.setForeground(new Color(255,255,255));
                                
                                

                            } else {
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                            }
                      
                    }
                } else { //MODIFICAR CAJA_PREVENTA COMO EMERGENCIA
                    
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(255,153,51)); 
                    Mensaje.setText("Desea Actualizar el Registro ?");
                    eli.setText("Si");
                    eli.setVisible(true);
                    noeli.setVisible(true);
                    tge=2;
                }
            } 
            
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
            tg=1; 
            cargareliminar.setVisible(false);
            jLabel43.setVisible(false);
            jLabel42.setVisible(false);            
            btnGuardar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnBuscarFormaPago.setVisible(true);
            btnBuscarCPT.setVisible(true);
            habilitarDatos();
            lblNewMod.setText("N");
            mostrarDatosModif(false);
            tbPaneles.setSelectedIndex(0);
           
   
            //JOptionPane.showMessageDialog(this, txtaMotivo.getText());
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
            jLabel43.setVisible(false);
            jLabel42.setVisible(false);
            lblNewMod.setText("M");
            enviarDatosTbFormatEmergencia();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        cargareliminar.setVisible(true);
              cargareliminar.setBackground(new Color(255,91,70)); 
              Mensaje.setText("Desea Eliminar este registro?");
              eli.setText("Si");
              eli.setVisible(true);
              noeli.setText("No");
              noeli.setVisible(true);
              tge=6;
        
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        jLabel43.setVisible(true);
        jLabel42.setVisible(true);
        tbPaneles.setSelectedIndex(1);
        lblNewMod.setText("M");
//        adEmerCab.cargarFormatEmer("", "", tbFormatosEmer);
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnFiltrarTri1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarTri1ActionPerformed
        buscarNHC();
    }//GEN-LAST:event_btnFiltrarTri1ActionPerformed

    private void txtBusquedaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedaCaretUpdate
        
    }//GEN-LAST:event_txtBusquedaCaretUpdate

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            tbPaciente.requestFocus();
        }
        if(evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            buscarNHC();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void tbPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacienteKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPaciente.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBusqueda.requestFocus();
            tbPaciente.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosTbPaciente();
        }
    }//GEN-LAST:event_tbPacienteKeyPressed

    private void cbxTipoBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaItemStateChanged

    }//GEN-LAST:event_cbxTipoBusquedaItemStateChanged

    private void txtNHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNHCKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            mostrarHCCabecera(formatoNHC(txtNHC.getText()));
        }
    }//GEN-LAST:event_txtNHCKeyPressed

    private void txtNHCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNHCCaretUpdate
  
          if(lblNewMod.getText().equals("N")){
            if(txtNHC.getText().length()==8){
                mostrarHCCabecera(formatoNHC(txtNHC.getText()));
                if(lblEstado.getText().equals("A")){
                    if(lblDni.getText().equals("")){
                        JOptionPane.showMessageDialog(this, "Nº H.C. " + txtNHC.getText() + " no existe");
                        pnlDatosCabecera.setVisible(false);
                        txtNroRegistro.setText("");
                        btnGuardar.setEnabled(false);
                        //txtNHC.setText("");
                    } else {
                        pnlDatosCabecera.setVisible(true);
                        //if(txtNroRegistro.getText().equals(""))
                        //    txtNroRegistro.setText("EC000000000000000001");
                        //else
                        Caja_Preventa cp = new Caja_Preventa();
                        int id = cp.idCajaPreventa();
                        txtNroRegistro.setText(String.valueOf(id));
                    }
                } else {
                    ImageIcon iT=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
                    int pregunta = JOptionPane.showConfirmDialog(this, "Nº H.C. " + txtNHC.getText() + " elimada, \ndesea restaurarla?",
                          "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,iT);
                    if(pregunta==0){
                        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
                        int restaurar = JOptionPane.showConfirmDialog(this, "Seguro que desea restaurar? \n Nº H.C. " + txtNHC.getText() + "\nPaciente: " + lblApNom.getText(),
                              "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                        if(restaurar==0){
                            HistoriaClinica hC = new HistoriaClinica();
                            hC.setId_hc(lblIDHC.getText());
                            if(hC.restaurarHistoriaClinica()){
                                JOptionPane.showMessageDialog(this, "Nº H.C. " + txtNHC.getText() + "\n\t\t\trestaurada");
                                pnlDatosCabecera.setVisible(true);
                                
                                //if(txtNroRegistro.getText().equals(""))
                                //    txtNroRegistro.setText("EC000000000000000001");
                                //else
                                Caja_Preventa cp = new Caja_Preventa();
                                int id = cp.idCajaPreventa();
                                txtNroRegistro.setText(String.valueOf(id));
                                buscar_HC(1,"A","");
                            }
                        }
                    }
                }
                btnEliminar.setEnabled(false);
            }else{
                limpiar();
                pnlDatosCabecera.setVisible(false);
                txtNroRegistro.setText("");
            }
        } else {
            if(txtNHC.getText().length()==8){ //MODIFICAR
             
                mostrarHCCabecera(formatoNHC(txtNHC.getText()));
                pnlDatosCabecera.setVisible(true);
//                adEmerCab.cargarFormatEmer(lblIDHC.getText(),"",tbFormatosEmer);
//                if(tbFormatosEmer.getRowCount()!=0){
//                    if(lblPestana.getText().equals("C"))
                        lblPestanaMod.setText("C");
//                        tbPaneles.setSelectedIndex(0);
//                } else {
//                    habilitarPestanas(1, true);
//                    JOptionPane.showMessageDialog(this,"No hay registros");
//                    //campo en blanco, para verificar si existe registro o no
//                    txtTraidopor.setText("");
//                }
            }
        }
        
        if(txtTraidopor.getText().equals("")){
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
        }else{ 
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_txtNHCCaretUpdate

    private void BTNBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBActionPerformed
//        if(lblPestanaMod.getText().equals("C")){
//            if(jdcBusquedaFecha.getCalendar()!= null){
//                String fecha = adEmerCab.determinarFecha(jdcBusquedaFecha);
//                adEmerCab.cargarFormatEmer(lblIDHC.getText(), fecha, tbFormatosEmer);
//            } else {
//                adEmerCab.cargarFormatEmer(lblIDHC.getText(), "", tbFormatosEmer);
//            }
//        } 
//        
//        tbFormatosEmer.getSelectionModel().setSelectionInterval(0, 0);
//        tbFormatosEmer.requestFocus();
 
    }//GEN-LAST:event_BTNBActionPerformed

    private void tbPanelesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPanelesMouseClicked
        
    }//GEN-LAST:event_tbPanelesMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        
    }//GEN-LAST:event_jPanel1MouseClicked

    private void tbPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacienteMouseClicked
      if(evt.getClickCount()==2){
         enviarDatosTbPaciente();
       }
    }//GEN-LAST:event_tbPacienteMouseClicked

    private void btnBuscarMTriajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMTriajeActionPerformed
      
    }//GEN-LAST:event_btnBuscarMTriajeActionPerformed

    private void tbModifTriajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbModifTriajeKeyPressed
      
    }//GEN-LAST:event_tbModifTriajeKeyPressed

    private void tbModifTriajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbModifTriajeMouseClicked
        
    }//GEN-LAST:event_tbModifTriajeMouseClicked

    private void txtBusquedaToCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedaToCaretUpdate
        adEmerTo.cargarFormatLaboratorio(txtBusquedaTo.getText(), tbDatosLab,lblIdFP.getText());
    }//GEN-LAST:event_txtBusquedaToCaretUpdate

    private void tbDatosLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDatosLabKeyPressed
      
    }//GEN-LAST:event_tbDatosLabKeyPressed

    private void tbDatosLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosLabMouseClicked
        
    }//GEN-LAST:event_tbDatosLabMouseClicked

    private void txtBuscarDiagnosticoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDiagnosticoCaretUpdate
        adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
    }//GEN-LAST:event_txtBuscarDiagnosticoCaretUpdate

    private void tbDiagnosticosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosticosKeyPressed
        
    }//GEN-LAST:event_tbDiagnosticosKeyPressed

    private void tbDiagnosticosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticosMouseClicked
       
    }//GEN-LAST:event_tbDiagnosticosMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void txtBusquedaToKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaToKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbDatosLab.getSelectionModel().setSelectionInterval(0, 0);
            tbDatosLab.requestFocus();
        }
    }//GEN-LAST:event_txtBusquedaToKeyPressed

    private void txtBuscarDiagnosticoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDiagnosticoKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbDiagnosticos.getSelectionModel().setSelectionInterval(0, 0);
            tbDiagnosticos.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarDiagnosticoKeyPressed

    private void btnBuscarTriTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTriTopActionPerformed
        if(jdtBuscarTriTop.getCalendar()!=null)
            adEmerTo.cargarFormatTriajeparaTopico(lblIDHCTo.getText(),adEmerCab.determinarFecha(jdtBuscarTriTop),tbMostrarTriajepT);
        else
            adEmerTo.cargarFormatTriajeparaTopico(lblIDHCTo.getText(),"",tbMostrarTriajepT);
    }//GEN-LAST:event_btnBuscarTriTopActionPerformed

    private void jdtBuscarTriTopKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdtBuscarTriTopKeyPressed
       
    }//GEN-LAST:event_jdtBuscarTriTopKeyPressed

    private void tbMostrarTriajepTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMostrarTriajepTKeyPressed
      
    }//GEN-LAST:event_tbMostrarTriajepTKeyPressed

    private void tbMostrarTriajepTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMostrarTriajepTMouseClicked
       
    }//GEN-LAST:event_tbMostrarTriajepTMouseClicked

    private void tbDatosTopicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDatosTopicoKeyPressed
        
    }//GEN-LAST:event_tbDatosTopicoKeyPressed

    private void tbDatosTopicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosTopicoMouseClicked
      
    
    }//GEN-LAST:event_tbDatosTopicoMouseClicked

    private void btnAddExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddExamActionPerformed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
            int fila = tbDatosTopico.getSelectedRow();
            adEmerTo.cargarFormatLaboratorio(txtBusquedaTo.getText(), tbDatosLab,String.valueOf(tbDatosTopico.getValueAt(fila, 24)));
            dlgListaExamnAux.setVisible(true);
            dlgListaExamnAux.setLocationRelativeTo(null);//en el centro
            dlgListaExamnAux.setResizable(false);
            dlgListaExamnAux.getContentPane().setBackground(Color.WHITE);
            lblTipo.setText("modex");
        }
    }//GEN-LAST:event_btnAddExamActionPerformed

    private void btnAddDiagPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiagPActionPerformed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
            if(tbDiagPresun.getRowCount()<2){
                adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
                dlgListaImpDx.setVisible(true);
                dlgListaImpDx.setLocationRelativeTo(null);//en el centro
                dlgListaImpDx.setResizable(false);
                dlgListaImpDx.getContentPane().setBackground(Color.WHITE);
                lblTipoDiag.setText("p");
            } else {
                JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Sólo se permiten dos tipos de diagnósticos");
                tbDatosTopico.getSelectionModel().setSelectionInterval(0, 0);
                tbDatosTopico.requestFocus();
            }
        }
    }//GEN-LAST:event_btnAddDiagPActionPerformed

    private void btnAddDiagFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiagFActionPerformed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
            if(tbDiagFinal.getRowCount()<2){
                adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
                dlgListaImpDx.setVisible(true);
                dlgListaImpDx.setLocationRelativeTo(null);//en el centro
                dlgListaImpDx.setResizable(false);
                dlgListaImpDx.getContentPane().setBackground(Color.WHITE);
                lblTipoDiag.setText("f");
            } else {
                JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Sólo se permiten dos tipos de diagnósticos");
                tbDatosTopico.getSelectionModel().setSelectionInterval(0, 0);
                tbDatosTopico.requestFocus();
            }
        }
    }//GEN-LAST:event_btnAddDiagFActionPerformed

    private void btnDelExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelExamActionPerformed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
            eliminarDetalleTopicoExamen();
        }
    }//GEN-LAST:event_btnDelExamActionPerformed

    private void btnDelExamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDelExamKeyPressed
        
    }//GEN-LAST:event_btnDelExamKeyPressed

    private void btnDelDiagPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDiagPActionPerformed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
            eliminarDetalleTopicoDiagPresun();
        }
    }//GEN-LAST:event_btnDelDiagPActionPerformed

    private void btnDelDiagPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDelDiagPKeyPressed
        
    }//GEN-LAST:event_btnDelDiagPKeyPressed

    private void tbDiagPresunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagPresunKeyPressed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
            char teclaPresionada = evt.getKeyChar(); 
            if(teclaPresionada==KeyEvent.VK_DELETE)
                eliminarDetalleTopicoDiagPresun();
        }
    }//GEN-LAST:event_tbDiagPresunKeyPressed

    private void tbExamenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbExamenesKeyPressed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_DELETE)
            eliminarDetalleTopicoExamen();
        }
    }//GEN-LAST:event_tbExamenesKeyPressed

    private void btnDelDiagFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDiagFActionPerformed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
            eliminarDetalleTopicoDiagFinal();
        }
    }//GEN-LAST:event_btnDelDiagFActionPerformed

    private void tbDiagFinalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagFinalKeyPressed
        if(tbDatosTopico.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Seleccione un registro");
        else {
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_DELETE)
            eliminarDetalleTopicoDiagFinal();
        }
    }//GEN-LAST:event_tbDiagFinalKeyPressed

    private void btnBuscarToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarToActionPerformed
        
        if(jdtFechaTop.getDate() != null)
            adEmerTo.cargarDatosTopico(tbDatosTopico, lblIDHCTo.getText(), adEmerCab.determinarFecha(jdtFechaTop));
        else{
            JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Ingrese una fecha");
            adEmerTo.cargarDatosTopico(tbDatosTopico, lblIDHCTo.getText(), "");
        }
    }//GEN-LAST:event_btnBuscarToActionPerformed

    private void jdtFechaTopKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdtFechaTopKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbDatosTopico.getSelectionModel().setSelectionInterval(0, 0);
            tbDatosTopico.requestFocus();
        }
    }//GEN-LAST:event_jdtFechaTopKeyPressed

    private void tb_Grupo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo4MouseClicked
        if(evt.getClickCount()==2)
            enviarNomenclatura();
    }//GEN-LAST:event_tb_Grupo4MouseClicked

    private void tb_Grupo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo4KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tb_Grupo4.getSelectedRow()==0){
            txtBuscar2.requestFocus();
            tb_Grupo4.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER){ 
            enviarNomenclatura();
        }
    }//GEN-LAST:event_tb_Grupo4KeyPressed

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

    private void txtBuscarPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarPacienteMouseClicked

    }//GEN-LAST:event_txtBuscarPacienteMouseClicked

    private void txtBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPacienteActionPerformed

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tbPacientes.getSelectionModel().setSelectionInterval (0,0) ;
            tbPacientes.requestFocus();

        }
    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void ABONOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ABONOSMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ABONOSMouseClicked

    private void ABONOSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ABONOSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ABONOSKeyPressed

    private void tbpreventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpreventasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasMouseClicked

    private void tbpreventasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpreventasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasKeyPressed

    private void tbpreventasFRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpreventasFRMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasFRMouseClicked

    private void tbpreventasFRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpreventasFRKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasFRKeyPressed

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked

        if(evt.getClickCount()==2){
         enviarDatosTbPaciente();
       }

    }//GEN-LAST:event_tbPacientesMouseClicked

    private void tbPacientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPacientesMouseEntered

    private void tbPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyPressed

        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientes.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBusqueda.requestFocus();
            tbPacientes.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosTbPaciente();
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

    private void pnlEObservaciónKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlEObservaciónKeyReleased
       pnlEObservación.setText(pnlEObservación.getText().toUpperCase());
    }//GEN-LAST:event_pnlEObservaciónKeyReleased

    private void tbCabeceraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMouseReleased
        int fila = tbCabecera.getSelectedRow();
        if(fila == -1)
        JOptionPane.showMessageDialog(this, "Seleccione una fila");
        else{
            if(evt.isPopupTrigger()){
                jpmCabecera.show(evt.getComponent(),evt.getX(),evt.getY());
            }
        }
    }//GEN-LAST:event_tbCabeceraMouseReleased

    private void tbCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabeceraKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCabecera.getSelectedRow()==0){
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_tbCabeceraKeyPressed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate

        cp.listarDatosEmergencia(txtBuscar.getText(), determinarFecha(fechai),determinarFecha(fechaf), tbCabecera);

    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        
            if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
                tbCabecera.getSelectionModel().setSelectionInterval(0, 0);
                tbCabecera.requestFocus();
            }
     
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        ImageIcon continuar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/continuar.png"));
        ImageIcon detener=new ImageIcon(this.getClass().getResource("/imagenes/iconos/detener.png"));
       
            if(btnBuscar1.getText().equals("Iniciar")){
                if(fechai.getDate()==null || fechai.getDate()==null){
                    JOptionPane.showMessageDialog(this, "Ingrese un rango de fechas");
                }
                cp.listarDatosEmergencia(txtBuscar.getText(), determinarFecha(fechai),determinarFecha(fechaf), tbCabecera);
                btnBuscar1.setText("Detener");
                txtBuscar.setVisible(true);
                txtBuscar.requestFocus();
                btnBuscar1.setIcon(detener);
                txtBuscar.setEnabled(true);
               
            } else {
                cp.listarDatosEmergencia(txtBuscar.getText(), "","", tbCabecera);
                btnBuscar1.setText("Iniciar");
                txtBuscar.setVisible(false);
                btnBuscar1.setIcon(continuar);
                txtBuscar.setEnabled(false);
  
            }
        
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        imprimirCabecera();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed

        
        dlgBuscarCPT.setLocationRelativeTo(null);//en el centro
        dlgBuscarCPT.setResizable(false);
        dlgBuscarCPT.getContentPane().setBackground(Color.WHITE);
        dlgBuscarCPT.setVisible(true);
        Caja_Nomenclatura cajaNomen = new Caja_Nomenclatura();
        cajaNomen.cajaNomenclaturaListarEmergencia("", tb_Grupo4);
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

    private void tbCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMouseClicked
            if(evt.getClickCount()==2){
                lblNewMod.setText("M");
                jLabel43.setVisible(false);
                jLabel42.setVisible(false);
                if(lblEFP.getText().equals("P")){
                    btnBuscarFormaPago.setVisible(true); 
                    btnBuscarCPT.setVisible(true); 
                }else if(lblEFP.getText().equals("C")){
                    btnBuscarFormaPago.setVisible(false); 
                    btnBuscarCPT.setVisible(false); 
                }
             enviarDatosTbFormatEmergencia();
            }
            if(evt.getClickCount()==1){
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }

    }//GEN-LAST:event_tbCabeceraMouseClicked

    private void txtFormaPagoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFormaPagoCaretUpdate

    }//GEN-LAST:event_txtFormaPagoCaretUpdate

    private void btnBuscarFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFormaPagoActionPerformed
        Jerarquias.setLocationRelativeTo(null);//en el centro
        Jerarquias.getContentPane().setBackground(Color.WHITE);
        txtBuscarFormaPago.setText("");
        Jerarquias.setVisible(true);
        
    }//GEN-LAST:event_btnBuscarFormaPagoActionPerformed

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
            tb_FP.getSelectionModel().setSelectionInterval (0,0) ;
            tb_FP.requestFocus();

        }
    }//GEN-LAST:event_txtBuscarFormaPagoKeyPressed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

    private void tb_FPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_FPMouseClicked
        int fila=tb_FP.getSelectedRow();
        if(evt.getClickCount()==2){
            Jerarquias.dispose();
            txtFormaPago.setText(String.valueOf(tb_FP.getValueAt(fila, 1)));
            lblFP.setText(String.valueOf(tb_FP.getValueAt(fila, 0)));

            }
    }//GEN-LAST:event_tb_FPMouseClicked

    private void tb_FPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_FPKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_FP.getSelectedRow();

            Jerarquias.dispose();
            txtFormaPago.setText(String.valueOf(tb_FP.getValueAt(fila, 1)));
            lblFP.setText(String.valueOf(tb_FP.getValueAt(fila, 0)));
        }
    }//GEN-LAST:event_tb_FPKeyPressed

    private void txtParentescoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtParentescoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtParentescoCaretUpdate

    private void txtTraidoporCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTraidoporCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTraidoporCaretUpdate

    private void txtCPTDESCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPTDESCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPTDESCaretUpdate

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        panelBusqueda.setVisible(false);
        lblEFP.setText("P");
        jLabel42.setForeground(new Color(153,153,153));
        jLabel43.setForeground(new Color(255,255,255));
        Caja_Preventa CPL = new Caja_Preventa(); 
        CPL.listarDatosEmergenciaP(lblusu.getText(), tbCabecera);
    }//GEN-LAST:event_jLabel43MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        panelBusqueda.setVisible(true);
        lblEFP.setText("C");
        jLabel43.setForeground(new Color(153,153,153));
        jLabel42.setForeground(new Color(255,255,255));
        cp.listarDatosEmergencia(txtBuscar.getText(), "", "", tbCabecera);
        tbCabecera.getSelectionModel().setSelectionInterval(0, 0);
        tbCabecera.requestFocus();
        
    }//GEN-LAST:event_jLabel42MouseClicked

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
        Caja_Nomenclatura cajaNomen = new Caja_Nomenclatura();
        cajaNomen.cajaNomenclaturaListarEmergencia(txtBuscar2.getText(), tb_Grupo4);
        
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
 
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void btnBuscarPaciente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente3ActionPerformed

    private void txtTraidoporKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTraidoporKeyReleased
        txtTraidopor.setText(txtTraidopor.getText().toUpperCase());
    }//GEN-LAST:event_txtTraidoporKeyReleased

    private void txtParentescoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParentescoKeyReleased
        txtParentesco.setText(txtParentesco.getText().toUpperCase());
    }//GEN-LAST:event_txtParentescoKeyReleased

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==3 || tge==1 ||tge==9 ||tge==7||tge==9){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            tga=5;
            if(txtTraidopor.getText().equals("") || txtParentesco.getText().equals("")){ //VALIDAR CAJAS VACIAS
                        JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
                        txtTraidopor.setBackground(new Color(153,204,205));
                        txtParentesco.setBackground(new Color(153,204,205));
                    } else {
         
                            Caja_Preventa caja1 = new Caja_Preventa();
                            caja1.setId_preventa(Integer.parseInt(txtNroRegistro.getText()));
                            caja1.setEMER_OBSERVACION(pnlEObservación.getText());
                            caja1.setEMER_TRAIDOPOR(txtTraidopor.getText());
                            caja1.setEMER_PARENTESCO(txtParentesco.getText());
                            caja1.setEMER_FORMA_LLEGADA_ID(caja1.codFormaLlegada(cbxFormaLlegada.getSelectedItem().toString()));
                            caja1.setCod_jerar_forma_pago(lblFP.getText());
                            if(caja1.mantanimientoCajaPreventaEmergencia("U")==true){
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Actualizados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=9;
                                pnlDatosCabecera.setVisible(false);
                                limpiar();
                                txtNHC.setText("");
                                txtNHC.setEnabled(false);
                                mostrarDatosModif(false);
                                txtNroRegistro.setText("");
                                btnGuardar.setEnabled(false);
                                btnEliminar.setEnabled(false);
                                cbxFormaLlegada.setSelectedIndex(0);
                                
                                if (lblEFP.getText().equals("P")){
                                    jLabel42.setVisible(true);
                                    jLabel43.setVisible(true);
                                    panelBusqueda.setVisible(false);
                                    jLabel42.setForeground(new Color(153,153,153));
                                    jLabel43.setForeground(new Color(255,255,255));
                                    Caja_Preventa CPL = new Caja_Preventa(); 
                                    CPL.listarDatosEmergenciaP(lblusu.getText(), tbCabecera);  
                                    tbPaneles.setSelectedIndex(1);
                                }else if (lblEFP.getText().equals("C")){
                                    jLabel42.setVisible(true);
                                    jLabel43.setVisible(true);
                                    panelBusqueda.setVisible(true);
                                    jLabel43.setForeground(new Color(153,153,153));
                                    jLabel42.setForeground(new Color(255,255,255));
                                    cp.listarDatosEmergencia(txtBuscar.getText(), "", "", tbCabecera);
                                    tbCabecera.getSelectionModel().setSelectionInterval(0, 0);
                                    tbCabecera.requestFocus();
                                    tbPaneles.setSelectedIndex(1);
                                }
                                    
                                
                            } else {
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                            }
                        
                    }

        }
        if (tge==6){
        try { 
                Caja_Preventa cp = new Caja_Preventa();
                cp.setId_preventa(Integer.parseInt(txtNroRegistro.getText()));
                if(cp.mantanimientoCajaPreventaEmergencia("E")){
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro eliminado de forma correcta");
                    eli.setText("OK");
                    eli.setVisible(true);
                    noeli.setVisible(false);
                    tge=7;
                    limpiar();
                    pnlDatosCabecera.setVisible(false);
                    txtNHC.setEnabled(false);
                    txtNHC.setText("");
                    btnGuardar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                } else 
                    cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);

        btnGuardar.setEnabled(false);
        } catch (Exception e) {
            System.out.println("Error_btnEliminar" + e.getMessage());
        }

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
    
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
 
      
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
    }
    
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
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaCabecera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaCabecera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaCabecera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaCabecera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFormatoEmergenciaCabecera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ABONOS;
    private javax.swing.JDialog BHC;
    private javax.swing.JButton BTNB;
    private javax.swing.JLabel EDITAR;
    private javax.swing.JDialog Jerarquias;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JButton btnAddDiagF;
    private javax.swing.JButton btnAddDiagP;
    private javax.swing.JButton btnAddExam;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarDiagnostico;
    private javax.swing.JButton btnBuscarFormaPago;
    private javax.swing.JButton btnBuscarMTriaje;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBuscarPaciente3;
    private javax.swing.JButton btnBuscarPaciente4;
    private javax.swing.JButton btnBuscarTo;
    private javax.swing.JButton btnBuscarTriTop;
    private javax.swing.JButton btnDelDiagF;
    private javax.swing.JButton btnDelDiagP;
    private javax.swing.JButton btnDelExam;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnFiltrarTri1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JLabel bus;
    private javax.swing.JLabel bus3;
    private javax.swing.JPanel cargareliminar;
    public static javax.swing.JComboBox cbxFormaLlegada;
    private javax.swing.JComboBox cbxTipoBusqueda;
    private javax.swing.JDialog dlgBuscarCPT;
    private javax.swing.JDialog dlgBuscarPac;
    private javax.swing.JDialog dlgListaExamnAux;
    private javax.swing.JDialog dlgListaImpDx;
    private javax.swing.JDialog dlgModTriaje;
    private javax.swing.JDialog dlgModemergencia;
    private javax.swing.JDialog dlgMostrarDatosTopico;
    private javax.swing.JDialog dlgMostrarDatosTriajeT;
    private javax.swing.JButton eli;
    private com.toedter.calendar.JDateChooser fechaf;
    private com.toedter.calendar.JDateChooser fechai;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private com.toedter.calendar.JDateChooser jdcBusquedaFecha;
    private com.toedter.calendar.JDateChooser jdtBuscarTriTop;
    private com.toedter.calendar.JDateChooser jdtBuscarTriaje;
    private com.toedter.calendar.JDateChooser jdtFechaTop;
    private javax.swing.JPopupMenu jpmCabecera;
    public static javax.swing.JLabel lblApNom;
    public static javax.swing.JLabel lblCabpT;
    public static javax.swing.JLabel lblDepartamento;
    public static javax.swing.JLabel lblDireccion;
    public static javax.swing.JLabel lblDistrito;
    public static javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblEFP;
    public static javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEstado;
    public static javax.swing.JLabel lblEstcivil;
    private javax.swing.JLabel lblFP;
    private javax.swing.JLabel lblFechaIM;
    public static javax.swing.JLabel lblFechaIng;
    public static javax.swing.JLabel lblFechaNac;
    public static javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblHoraIM;
    public static javax.swing.JLabel lblHoraIng;
    private javax.swing.JLabel lblIDHC;
    public static javax.swing.JLabel lblIDHCTo;
    public static javax.swing.JLabel lblIdFP;
    public static javax.swing.JLabel lblIdPreventa;
    public static javax.swing.JLabel lblId_Preventa;
    private javax.swing.JLabel lblNewMod;
    public static final javax.swing.JLabel lblPestana = new javax.swing.JLabel();
    public static javax.swing.JLabel lblPestanaMod;
    public static javax.swing.JLabel lblProvincia;
    public static javax.swing.JLabel lblSector;
    private javax.swing.JLabel lblTipo;
    public static javax.swing.JLabel lblTipoDiag;
    private javax.swing.JLabel lblTriajeId;
    public static javax.swing.JLabel lblUsuario;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelBuscarHC;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelFormaPago;
    private javax.swing.JPanel panelFormaPago1;
    private javax.swing.JPanel panelFormaPago2;
    private javax.swing.JPanel panelFormaPago3;
    private javax.swing.JPanel panelFormaPago4;
    private javax.swing.JPanel panelSinHC;
    private javax.swing.JPanel paneltablaHC;
    private javax.swing.JPanel pnlB;
    private javax.swing.JPanel pnlDatosCabecera;
    public static javax.swing.JEditorPane pnlEObservación;
    private javax.swing.JTable tbCabecera;
    private javax.swing.JTable tbDatosLab;
    private javax.swing.JTable tbDatosTopico;
    private javax.swing.JTable tbDiagFinal;
    private javax.swing.JTable tbDiagPresun;
    private javax.swing.JTable tbDiagnosticos;
    private javax.swing.JTable tbExamenes;
    private javax.swing.JTable tbModifTriaje;
    private javax.swing.JTable tbMostrarTriajepT;
    private static javax.swing.JTable tbPaciente;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTabbedPane tbPaneles;
    private javax.swing.JTable tb_FP;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JTable tbpreventas;
    private javax.swing.JTable tbpreventasFR;
    public static javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtBuscarDiagnostico;
    private javax.swing.JTextField txtBuscarFormaPago;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtBusquedaTo;
    private javax.swing.JLabel txtCPT;
    public static javax.swing.JTextField txtCPTDES;
    public static javax.swing.JTextField txtFormaPago;
    public static javax.swing.JTextField txtNHC;
    public static javax.swing.JLabel txtNroRegistro;
    public static javax.swing.JTextField txtParentesco;
    public static javax.swing.JTextField txtTraidopor;
    // End of variables declaration//GEN-END:variables
}
