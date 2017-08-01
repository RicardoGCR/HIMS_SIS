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
public class FrmFormatoEmergenciaTopico extends javax.swing.JFrame implements Runnable{
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
    public FrmFormatoEmergenciaTopico() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);//fondo blanco
        setLocationRelativeTo(null);//en el centro
        this.setExtendedState(MAXIMIZED_BOTH);
        btnListado.setMnemonic(KeyEvent.VK_F3);
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
        conexion = c.conectar();
        
            
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
        
        adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, "","");
//        tbTopico.getSelectionModel().setSelectionInterval(0, 0);

        cerrar();
//        buscar_HC(1,"A","");
        tbPacientes.setTableHeader(null);
//        tbExamAux.setTableHeader(null);
        restringirCampos(8, txtNHCTo);
        jPanel9.setVisible(false);
        cargareliminar.setVisible(false);
    
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
        tbPaneles.setSelectedIndex(0);
        tbPaneles.setEnabledAt(0,false);
        tbPaneles.setEnabledAt(1, false);
        ErrorMas2.setLocationRelativeTo(null);//en el centro
        ErrorExistente.setLocationRelativeTo(null);//en el centro
        ElimDXP.setLocationRelativeTo(null);//en el centro
        SelReg.setLocationRelativeTo(null);//en el centro
        ElimEx.setLocationRelativeTo(null);//en el centro
        ElimDXF.setLocationRelativeTo(null);//en el centro
        txtaMotivo.setLineWrap(true); 
        txtaMotivo.setWrapStyleWord(true);
        txtaRelato.setLineWrap(true); 
        txtaRelato.setWrapStyleWord(true);
        txtaPlanTrabajo.setLineWrap(true); 
        txtaPlanTrabajo.setWrapStyleWord(true);
        txtaExamenFisico.setLineWrap(true); 
        txtaExamenFisico.setWrapStyleWord(true);
        txtaAnotMed.setLineWrap(true); 
        txtaAnotMed.setWrapStyleWord(true);
        txtaAnotacionesEnf.setLineWrap(true); 
        txtaAnotacionesEnf.setWrapStyleWord(true);
//        btnQuitarDiag.setEnabled(false);
//        btnQuitarExam.setEnabled(false);
//        btnQuitarDiagFinal.setEnabled(false);
        formatotbImpdx(tbImpDx);
        formatotbExamenAux(tbExamAux);
//        formatotbExamenAux(tbExamenes);----EDITAR
        formatotbDxAlta();
//        formatotbImpdx(tbDiagFinal);----EDITAR
//        formatotbImpdx(tbDiagPresun);---EDITAR
        //tbPaciente.setDefaultRenderer(Object.class,new tablas.AdmisionEmergenciaTopico());
        //limitadores
        LimitadorDeDocumento limitMotivo = new LimitadorDeDocumento(200);
        txtaMotivo.setDocument(limitMotivo);
        LimitadorDeDocumento limitRelato = new LimitadorDeDocumento(200);
        txtaRelato.setDocument(limitRelato);
        LimitadorDeDocumento limitExFisico = new LimitadorDeDocumento(950);
        txtaExamenFisico.setDocument(limitExFisico);
        LimitadorDeDocumento limitPlanTrabajo = new LimitadorDeDocumento(400);
        txtaPlanTrabajo.setDocument(limitPlanTrabajo);
        LimitadorDeDocumento limitAnotacionesMed = new LimitadorDeDocumento(2000);
        txtaAnotMed.setDocument(limitAnotacionesMed);
        LimitadorDeDocumento limitAnotacionesEnf = new LimitadorDeDocumento(2000);
        txtaAnotacionesEnf.setDocument(limitAnotacionesEnf);
        lblNewMod.setVisible(false);
        lblCabpT.setVisible(false);
        lbl1.setVisible(false);
        lbl2.setVisible(false);
   
        lblFP.setVisible(false);
//        lblIDHCTo.setVisible(false);
        txtIDTriaje.setVisible(false);
        lblIdFP.setVisible(false);
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
    public void imprimirTopico(){
        int fila = tbTopico.getSelectedRow();
        adEmerTo.reporteTopico(String.valueOf(tbTopico.getValueAt(fila, 0)));
    }
    public void imprimirFormatoCompleto(){
        int fila = tbTopico.getSelectedRow();
        adEmerTo.reporteTopicoCompleto(String.valueOf(tbTopico.getValueAt(fila, 0)));
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
    
    
    public void Actualizar_ESTADO_PREVENTA(){
                AdmisionEmergenciaTopico AME = new AdmisionEmergenciaTopico();
                AME.setTriaje_id(lbl_ID_TRIAJE.getText() );

                if(AME.ACTUALIZAR_ESTADO_PREVENTA()==true){
                           System.out.println("SE ACTUALIZO EL ESTADO DE LA PREVENTA");
                       } else {
                             System.out.println("eror ESTADO PREVENTA");
                       }  
    }
    
     public void BuscarHC(){
        String consulta="";
        try {
            tbPacientes.setModel(new DefaultTableModel());
             String titulos[]={"Nº H.C.","DNI","Paciente","Direccion","Sexo","Fecha","Edad","","","","","","","","","","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[20];
            AdmisionEmergenciaCabecera obj=new AdmisionEmergenciaCabecera();
                    consulta="exec CAJA_BUSCAR_HISTORIAS_TOPICOS ?";      
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
                fila[9]=r.getString(10);
                fila[10]=r.getString(11);
                fila[11]=r.getString(12);
                fila[12]=r.getString(13);
                fila[13]=r.getString(14);
                fila[14]=r.getString(15);
                fila[15]=r.getString(16);
                fila[16]=r.getString(17);
                fila[17]=r.getString(18);
                fila[18]=r.getString(19);
                fila[19]=r.getString(20);
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
    tbPacientes.getColumnModel().getColumn(9).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(9).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(10).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(10).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(11).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(11).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(12).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(12).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(13).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(13).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(14).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(14).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(15).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(15).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(16).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(16).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(17).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(17).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(18).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(18).setMaxWidth(0);
    tbPacientes.getColumnModel().getColumn(19).setMinWidth(0);
    tbPacientes.getColumnModel().getColumn(19).setMaxWidth(0);
    
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


    public void limpiarDatosTopico(){
        lbl2.setText("");
        txtNHCTo.setText("");
        txtApetito.setText("");
        txtSed.setText("");
        txtSueno.setText("");
        txtOrinas.setText("");
        txtDeposiciones.setText("");
        txtaMotivo.setText("");
        txtaRelato.setText("");
        txtConciencia.setText("");
        txtHidratacion.setText("");
        txtNutricion.setText("");
        txtaExamenFisico.setText("");
        txtaPlanTrabajo.setText("");
        cbxEvaluacionPaciente.setSelectedIndex(0);
        cbxUbicacionEgreso.setSelectedIndex(0);
        txtaAnotMed.setText("");
        txtaAnotacionesEnf.setText("");
        for (int i = 0; i < tbExamAux.getRowCount(); i++) {
           m= (DefaultTableModel) tbExamAux.getModel(); 
           m.removeRow(i);
           i-=1;
       }
        for (int i = 0; i < tbImpDx.getRowCount(); i++) {
           m= (DefaultTableModel) tbImpDx.getModel(); 
           m.removeRow(i);
           i-=1;
       }
        for (int i = 0; i < tbDxAlta.getRowCount(); i++) {
           m= (DefaultTableModel) tbDxAlta.getModel(); 
           m.removeRow(i);
           i-=1;
       }
        lblIDHCTo.setText("");
        lblCabpT.setText("");
        lblIDTriajepTop.setText("");
    }

    
//    public void mostrarHCTopico(String nhc){
//        String consulta="";
//        try {
//            consulta="EXEC SP_ADMISION_HISTORIACLINICA_BUSXNHC ?";
//            PreparedStatement cmd = movHC.getCn().prepareStatement(consulta);
//            cmd.setString(1, nhc);
//            ResultSet r= cmd.executeQuery();
//            int c=1;
//            while(r.next()){
//                lblPaciente.setText(r.getString(2) + " " + r.getString(3) + " " + 
//                                 r.getString(4) + " " + r.getString(5) + " " +
//                                 r.getString(6)); 
//            }
//            //
//        } catch (Exception e) {
//            System.out.println("Error_mostrar_MovHC: " + e.getMessage());
//        }
//    }
    
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

    
    public void enviarDatosTbPaciente(){
        int fila = tbPacientes.getSelectedRow();
        BHC.dispose();
            //////////////////ACTUALIZAR TRIAJE/////////////////////////////////
            lbl_ID_TRIAJE.setText(String.valueOf(tbPacientes.getValueAt(fila, 8)));
            lblID_PREVENTA.setText(String.valueOf(tbPacientes.getValueAt(fila, 19)));
            txtNHCTo.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            lblPacienteTo.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));
            lblTriaje.setText("TRIAJE |    P.A. "+String.valueOf(tbPacientes.getValueAt(fila, 9))+
                    "      F.C. "+String.valueOf(tbPacientes.getValueAt(fila, 10))+
                    "      F.R. "+String.valueOf(tbPacientes.getValueAt(fila, 11))+
                    "      Tº. "+String.valueOf(tbPacientes.getValueAt(fila, 12))+
                    "      PESO "+String.valueOf(tbPacientes.getValueAt(fila, 13))+
                    "      TALLA "+String.valueOf(tbPacientes.getValueAt(fila, 14))+
                    "      IDM "+String.valueOf(tbPacientes.getValueAt(fila, 15)));
            lblFP.setText(String.valueOf(tbPacientes.getValueAt(fila, 17)));
            lblIdFP.setText(String.valueOf(tbPacientes.getValueAt(fila, 18)));
            lblIDHCTo.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
            lblCabpT.setText(String.valueOf(tbPacientes.getValueAt(fila, 19)));
            lblIDTriajepTop.setText(String.valueOf(tbPacientes.getValueAt(fila, 8)));
            
            
            lblFormaP.setVisible(true);
            lblFP.setVisible(true);
            btnGuardar.setEnabled(true);
       
    }
    
    
    
    
    public void enviarDatosdeTopico(){
        int fila = tbTopico.getSelectedRow();
        tbPaneles.setSelectedIndex(1);
        pnlTopicoP.setVisible(true);
        lbl2.setText(String.valueOf(tbTopico.getValueAt(fila, 0)));
        txtApetito.setText(String.valueOf(tbTopico.getValueAt(fila, 5)));
        txtSed.setText(String.valueOf(tbTopico.getValueAt(fila, 6)));
        txtDeposiciones.setText(String.valueOf(tbTopico.getValueAt(fila, 7)));
        txtSueno.setText(String.valueOf(tbTopico.getValueAt(fila, 8)));
        txtOrinas.setText(String.valueOf(tbTopico.getValueAt(fila, 9)));
        txtaMotivo.setText(String.valueOf(tbTopico.getValueAt(fila, 10)));
        txtaRelato.setText(String.valueOf(tbTopico.getValueAt(fila, 11)));
        txtConciencia.setText(String.valueOf(tbTopico.getValueAt(fila, 12)));
        txtHidratacion.setText(String.valueOf(tbTopico.getValueAt(fila, 13)));
        txtNutricion.setText(String.valueOf(tbTopico.getValueAt(fila, 14)));
        txtaExamenFisico.setText(String.valueOf(tbTopico.getValueAt(fila, 15)));
        txtaPlanTrabajo.setText(String.valueOf(tbTopico.getValueAt(fila, 16)));
        txtaAnotMed.setText(String.valueOf(tbTopico.getValueAt(fila, 17)));
        txtaAnotacionesEnf.setText(String.valueOf(tbTopico.getValueAt(fila, 18)));
        cbxEvaluacionPaciente.setSelectedItem(String.valueOf(tbTopico.getValueAt(fila, 19)));
        cbxUbicacionEgreso.setSelectedItem(String.valueOf(tbTopico.getValueAt(fila, 20)));
        cbxPrioridad.setSelectedItem(String.valueOf(tbTopico.getValueAt(fila, 26)));
        /*FrmFormatoEmergencia.lblIDTriajepTop.setText(String.valueOf(tbMostrarTriajepT.getValueAt(fila, 5)));
        txtIDTopico.setText(adEmerTo.idAdmisionEmergenciaTopico());*/
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        //inhabilitar botones
        habilitaBotonesJTable(false);

        lblNewMod.setText("M");
        habilitaBotonesJTable(true);
    }
    
    public void habilitaBotonesJTable(boolean opcion){
        btnbuscarExamAux.setEnabled(opcion);
        btnBuscarImpDx.setEnabled(opcion);
        btnAgregarDiagfinal.setEnabled(opcion);
    }


    public void habilitarDatosTopico(){
        txtNHCTo.requestFocus();
        txtNHCTo.setEnabled(true);
        txtNHCTo.setText("");
    }
    
    public void formatotbExamenAux(JTable tabla){

        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(140);//CODIGO
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.setRowHeight(25);
    }
    
    public void enviarDatosExamAux(){
        m = (DefaultTableModel) tbExamAux.getModel();
        int fila = tbDatosLab.getSelectedRow();
        if(tbExamAux.getColumnCount()==0){
            dlgListaExamnAux.dispose();
            //PASAR DATOS A TABLA
            String examenes[] = {
            String.valueOf(tbDatosLab.getValueAt(fila, 2)),
            String.valueOf(tbDatosLab.getValueAt(fila, 0)),
            String.valueOf(tbDatosLab.getValueAt(fila, 3))};
            m.addRow(examenes);
            formatotbExamenAux(tbExamAux);
            btnQuitarExam.setEnabled(true);
        } else 
        if(repiteExamen()==true)
            ErrorExistente.setVisible(true);
        else{
            dlgListaExamnAux.dispose();
            //PASAR DATOS A TABLA
            String examenes[] = {
            String.valueOf(tbDatosLab.getValueAt(fila, 2)),
            String.valueOf(tbDatosLab.getValueAt(fila, 0)),
            String.valueOf(tbDatosLab.getValueAt(fila, 3))};
            m.addRow(examenes);
            formatotbExamenAux(tbExamAux);
            btnQuitarExam.setEnabled(true);
        }
    }
    
    public void enviarDatosExamenes(){
        m = (DefaultTableModel) tbExamAux.getModel();
        int fila = tbDatosLab.getSelectedRow();
        if(tbExamAux.getColumnCount()==0){
            dlgListaExamnAux.dispose();
            //PASAR DATOS A TABLA
            String examenes[] = {
            String.valueOf(tbDatosLab.getValueAt(fila, 0)),
            String.valueOf(tbDatosLab.getValueAt(fila, 2)),
            String.valueOf(tbDatosLab.getValueAt(fila, 3))};
            m.addRow(examenes);
            //formatotbExamenAux();
            btnQuitarExam.setEnabled(true);
            int filas = tbTopico.getSelectedRow();
            for (int i = 0; i < tbExamAux.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    Caja_DetallePreventa cp = new Caja_DetallePreventa();
                    cp.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                    cp.setCod_precio(tbExamAux.getValueAt(i, 2).toString());
                    cp.setCod_usu(adEmer2.codUsuario(lblusu.getText()));
                    cp.setId_Preventa(Integer.parseInt(String.valueOf(tbTopico.getValueAt(filas, 28))));//////
                    cp.cajaDetallePreventaInsertar();
            }
        } else 
        if(repitedlgExamen()==true)
            ErrorExistente.setVisible(true);
        else{
            dlgListaExamnAux.dispose();
            //PASAR DATOS A TABLA
            String examenes[] = {
            String.valueOf(tbDatosLab.getValueAt(fila, 0)),
            String.valueOf(tbDatosLab.getValueAt(fila, 2)),
            String.valueOf(tbDatosLab.getValueAt(fila, 3))};
            m.addRow(examenes);
            btnQuitarExam.setEnabled(true);
            int filas = tbTopico.getSelectedRow();
            boolean eliminar = false;
            boolean insertar = false;
            for (int i = 0; i < tbExamAux.getRowCount(); i++){      
                AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                Caja_DetallePreventa cp = new Caja_DetallePreventa();
                cp.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                cp.cajaDetallePreventaModificar();//{
                eliminar = true;
            }
            if(eliminar == true){
                for (int i = 0; i < tbExamAux.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    Caja_DetallePreventa cp = new Caja_DetallePreventa();
                    cp.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                    cp.setCod_precio(tbExamAux.getValueAt(i, 2).toString());
                    cp.setCod_usu(adEmer2.codUsuario(lblusu.getText()));
                    cp.setId_Preventa(Integer.parseInt(String.valueOf(tbTopico.getValueAt(filas, 28))));//////
                    cp.cajaDetallePreventaInsertar();
                    insertar = true;
                }
            }
            if(insertar == true && eliminar == true){
                System.out.println("Examen GURDADO");
            }
        }
    }
    
    public void formatotbImpdx(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);//clasificacion
        tabla.setRowHeight(25);
    }
    
    public void enviarDatosDiagnostico(){
        m = (DefaultTableModel) tbImpDx.getModel();
        int fila = tbDiagnosticos.getSelectedRow();
        if(tbImpDx.getColumnCount()==0){
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbImpDx);
            btnQuitarDiag.setEnabled(true);
        } else
        if(repiteDiagImp()==true)
        ErrorExistenteDX.setVisible(true);
        else {
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbImpDx);
            btnQuitarDiag.setEnabled(true);
        }
    }
    
    public void enviarDatosDiagnosticoP(){
        m = (DefaultTableModel) tbImpDx.getModel();
        int fila = tbDiagnosticos.getSelectedRow();
        if(tbImpDx.getColumnCount()==0){
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbImpDx);
            btnQuitarDiag.setEnabled(true);
            int filas = tbTopico.getSelectedRow();
            for (int i = 0; i < tbImpDx.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    AdmisionEmergenciaTopicoDetalleDiagPresun adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagPresun();
                    adEmerTop2.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                    adEmerTop2.setId_cie10(Integer.parseInt(String.valueOf(tbImpDx.getValueAt(i, 0))));
                    adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblusu.getText()));
                    adEmerTop2.modificarDetalleDiagPresun();//{
            }
        } else
        if(repitedlgExamenDiagImp()==true)
            ErrorExistenteDX.setVisible(true);
        else {
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbImpDx);
            btnQuitarDiag.setEnabled(true);
            boolean eliminar = false;
            boolean insertar = false;
            int filas = tbTopico.getSelectedRow();
            for (int i = 0; i < tbImpDx.getRowCount(); i++){      
                AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                AdmisionEmergenciaTopicoDetalleDiagPresun adEmerTop1=new AdmisionEmergenciaTopicoDetalleDiagPresun();
                adEmerTop1.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                adEmerTop1.setId_cie10(0);
                adEmerTop1.modificarDetalleDiagPresun();//{
                eliminar = true;
            }
            if(eliminar == true){
                for (int i = 0; i < tbImpDx.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    AdmisionEmergenciaTopicoDetalleDiagPresun adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagPresun();
                    adEmerTop2.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                    adEmerTop2.setId_cie10(Integer.parseInt(String.valueOf(tbImpDx.getValueAt(i, 0))));
                    adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblusu.getText()));
                    adEmerTop2.insertarDetalleDiagPresuntivo();
                    insertar = true;
            }
            }
            if(insertar == true && eliminar == true){
                System.out.println("DX Guardado");
            }
        }
    }
    
    public void formatotbDxAlta(){
        tbDxAlta.getColumnModel().getColumn(0).setMinWidth(0);
        tbDxAlta.getColumnModel().getColumn(0).setMaxWidth(0);
        tbDxAlta.getColumnModel().getColumn(1).setMinWidth(0);
        tbDxAlta.getColumnModel().getColumn(1).setMaxWidth(0);
        tbDxAlta.getColumnModel().getColumn(2).setPreferredWidth(550);//clasificacion
        tbDxAlta.setRowHeight(25);
    }
    
    public void enviarDatosDiagnosticoFinal(){
        m = (DefaultTableModel) tbDxAlta.getModel();
        int fila = tbDiagnosticos.getSelectedRow();
        if(tbDxAlta.getColumnCount()==0){
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbDxAlta();
            btnQuitarDiagFinal.setEnabled(true);
        }else
        if(repiteDiagAlta()==true)
        ErrorExistenteDX.setVisible(true);
        else {
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbDxAlta();
            btnQuitarDiagFinal.setEnabled(true);
        }
    }
    
    public void enviarDatosDiagnosticoF(){
        m = (DefaultTableModel) tbDxAlta.getModel();
        int fila = tbDiagnosticos.getSelectedRow();
        if(tbDxAlta.getColumnCount()==0){
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbDxAlta);
            btnQuitarDiag.setEnabled(true);
            int filas = tbTopico.getSelectedRow();
                for (int i = 0; i < tbDxAlta.getRowCount(); i++){      
                        AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                        AdmisionEmergenciaTopicoDetalleDiagFinal adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagFinal();
                        adEmerTop2.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                        adEmerTop2.setId_cie10(Integer.parseInt(String.valueOf(tbDxAlta.getValueAt(i, 0))));
                        adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblusu.getText()));
                        adEmerTop2.modificarDetalleDiagFinal();//{
                }
        } else
        if(repitedlgExamenDiagAlta()==true)
            ErrorExistenteDX.setVisible(true);
        else {
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbDxAlta);
            btnQuitarDiag.setEnabled(true);
            boolean eliminar = false;
            boolean insertar = false;
            int filas = tbTopico.getSelectedRow();
                for (int i = 0; i < tbDxAlta.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    AdmisionEmergenciaTopicoDetalleDiagFinal adEmerTop1=new AdmisionEmergenciaTopicoDetalleDiagFinal();
                    adEmerTop1.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                    adEmerTop1.setId_cie10(0);
                    adEmerTop1.modificarDetalleDiagFinal();//{
                    eliminar = true;
                }
                if(eliminar == true){
                    for (int i = 0; i < tbDxAlta.getRowCount(); i++){      
                        AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                        AdmisionEmergenciaTopicoDetalleDiagFinal adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagFinal();
                        adEmerTop2.setId_topico(String.valueOf(tbTopico.getValueAt(filas, 0)));
                        adEmerTop2.setId_cie10(Integer.parseInt(String.valueOf(tbDxAlta.getValueAt(i, 0))));
                        adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblusu.getText()));
                        adEmerTop2.insertarDetalleDiagFinal();
                        insertar = true;
                }
                }
                if(insertar == true && eliminar == true){
                    System.out.println("DX Guardado");
                }
        }
    }
    
    public boolean repiteExamen(){
        int filaselec=tbDatosLab.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbExamAux.getRowCount(); i++){    
            if(tbExamAux.getValueAt(i, 1).toString().equalsIgnoreCase(tbDatosLab.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
               return c;
     }
    
    public boolean repiteDiagImp(){
        int filaselec=tbDiagnosticos.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbImpDx.getRowCount(); i++){    
            if(tbImpDx.getValueAt(i, 0).toString().equalsIgnoreCase(tbDiagnosticos.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
    
    public boolean repiteDiagAlta(){
        int filaselec=tbDiagnosticos.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbDxAlta.getRowCount(); i++){    
            if(tbDxAlta.getValueAt(i, 0).toString().equalsIgnoreCase(tbDiagnosticos.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
    //FIN DIALOGO TOPICO
    public boolean repitedlgExamen(){
        int filaselec=tbDatosLab.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbExamAux.getRowCount(); i++){    
            if(tbExamAux.getValueAt(i, 2).toString().equalsIgnoreCase(tbDatosLab.getValueAt(filaselec, 3).toString())){
                    c=true;
            }
        }
               return c;
     }
    
    public boolean repitedlgExamenDiagImp(){
        int filaselec=tbDiagnosticos.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbImpDx.getRowCount(); i++){    
            if(tbImpDx.getValueAt(i, 0).toString().equalsIgnoreCase(tbDiagnosticos.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
    
    public boolean repitedlgExamenDiagAlta(){
        int filaselec=tbDiagnosticos.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbDxAlta.getRowCount(); i++){    
            if(tbDxAlta.getValueAt(i, 0).toString().equalsIgnoreCase(tbDiagnosticos.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
     //FIN DIALOGO TOPICO
    public void quitarExamenAux(){
        if(tbExamAux.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tbExamAux.getModel(); //TableProducto es el nombre de mi tabla ;) 
            m.removeRow(tbExamAux.getSelectedRow()); 
        } else 
            SelReg.setVisible(true);
    }
    
    public void quitarDiagImpr(){
        if(tbImpDx.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tbImpDx.getModel(); //TableProducto es el nombre de mi tabla ;) 
            m.removeRow(tbImpDx.getSelectedRow()); 
        } else 
            SelReg.setVisible(true);
    }
    
    public void quitarDiagFinal(){
        if(tbDxAlta.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tbDxAlta.getModel(); 
            m.removeRow(tbDxAlta.getSelectedRow()); 
        } else 
            SelReg.setVisible(true);
    }
    //
    //
    public boolean guardarDatosTopico(){
        
        boolean retorna = false;
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
        AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
                String id_topico = lbl2.getText();
                //String idhc = lblIDHCTo.getText();
                int echc = Integer.parseInt(lblCabpT.getText());
                String triaje = lblIDTriajepTop.getText();
                String apetito = txtApetito.getText();
                String sed = txtSed.getText();
                String sueno = txtSueno.getText();
                String orinas = txtOrinas.getText();
                String deposiciones = txtDeposiciones.getText();
                String motivoemer = txtaMotivo.getText();
                String relatoemer = txtaRelato.getText();
                String conciencia = txtConciencia.getText();
                String hidratacion = txtHidratacion.getText();
                String nutricion = txtNutricion.getText();
                String examen_fisico = txtaExamenFisico.getText();
                String plan_trabajo = txtaPlanTrabajo.getText();
                String eva_pac = cbxEvaluacionPaciente.getSelectedItem().toString();
                String ubicacion_egreso = cbxUbicacionEgreso.getSelectedItem().toString();
                String anot_medica = txtaAnotMed.getText();
                String anot_enf = txtaAnotacionesEnf.getText();
                String usuario = adEmerCab5.codUsuario(lblusu.getText());
                String prioridad = cbxPrioridad.getSelectedItem().toString();
                AdmisionEmergenciaTopico adEmertopico = new AdmisionEmergenciaTopico();

                    adEmertopico.setTopico_id(id_topico);
                    adEmertopico.setPreventa(echc);
                    adEmertopico.setTriaje_id(triaje);
                    adEmertopico.setFb_apetito(apetito);
                    adEmertopico.setFb_sed(sed);
                    adEmertopico.setFb_deposi(deposiciones);
                    adEmertopico.setFb_sueno(sueno);
                    adEmertopico.setFb_orina(orinas);
                    adEmertopico.setMotivo_emer(motivoemer);
                    adEmertopico.setRelato(relatoemer);
                    adEmertopico.setEg_concie(conciencia);
                    adEmertopico.setEg_hidra(hidratacion);
                    adEmertopico.setEg_nutri(nutricion);
                    adEmertopico.setEg_fisic(examen_fisico);
                    adEmertopico.setPlan_trabajo(plan_trabajo);
                    adEmertopico.setAnot_medico(anot_medica);
                    adEmertopico.setAnot_enfer(anot_enf);
                    adEmertopico.setEval_paciente(eva_pac);
                    adEmertopico.setUbic_egreso(ubicacion_egreso);
                    adEmertopico.setCod_usu(usuario);
                    adEmertopico.setPrioridad(prioridad);
                    if(adEmertopico.mantenimientoAdmisionEmergenciaTopico("I")==true){
                        AdmisionEmergenciaTopico top = new AdmisionEmergenciaTopico();
                        top.topicoID();
                        retorna = true;
                    }
                
                return retorna;
    }
    
    public boolean guardarDatos(){
        boolean retorna = false;
        if(guardarDatosTopico()==true){
            AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
            for (int i = 0; i < tbExamAux.getRowCount(); i++){      
                Caja_DetallePreventa cdp = new Caja_DetallePreventa();
                cdp.setId_topico(lbl2.getText());
                cdp.setCod_precio(tbExamAux.getValueAt(i,2).toString());
                cdp.setCod_usu(adEmer2.codUsuario(lblusu.getText()));
                cdp.setId_Preventa(Integer.parseInt(lblCabpT.getText()));
                if(cdp.cajaDetallePreventaInsertar()==true)
                    retorna = true;
                //JOptionPane.showMessageDialog(this, tbExamAux.getValueAt(i, 2).toString());
                //adEmerTop2.guardarReferencialDetalle();
            }
            for (int i = 0; i < tbDxAlta.getRowCount(); i++){      
                AdmisionEmergenciaTopicoDetalleDiagFinal adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagFinal();
                adEmerTop2.setId_topico(lbl2.getText());
                adEmerTop2.setId_cie10(Integer.parseInt(tbDxAlta.getValueAt(i, 0).toString()));
                adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblusu.getText()));
                if(adEmerTop2.insertarDetalleDiagFinal()==true)
                    retorna = true;
                //adEmerTop2.guardarReferencialDetalle();
            }
            for (int i = 0; i < tbImpDx.getRowCount(); i++){      
                AdmisionEmergenciaTopicoDetalleDiagPresun adEmerTop3=new AdmisionEmergenciaTopicoDetalleDiagPresun();
                adEmerTop3.setId_topico(lbl2.getText());
                adEmerTop3.setId_cie10(Integer.parseInt(tbImpDx.getValueAt(i, 0).toString()));
                adEmerTop3.setUsu_cod(adEmer2.codUsuario(lblusu.getText()));
                if(adEmerTop3.insertarDetalleDiagPresuntivo()==true)
                    retorna = true;
                //adEmerTop2.guardarReferencialDetalle();
            }
        } else 
            return retorna;
        return retorna;
    }
     
    public boolean modificarDatosTopico(){
        
        boolean retorna = false;

        AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
                String id_topico = lbl2.getText();
                //String idhc = lblIDHCTo.getText();
                String apetito = txtApetito.getText();
                String sed = txtSed.getText();
                String sueno = txtSueno.getText();
                String orinas = txtOrinas.getText();
                String deposiciones = txtDeposiciones.getText();
                String motivoemer = txtaMotivo.getText();
                String relatoemer = txtaRelato.getText();
                String conciencia = txtConciencia.getText();
                String hidratacion = txtHidratacion.getText();
                String nutricion = txtNutricion.getText();
                String examen_fisico = txtaExamenFisico.getText();
                String plan_trabajo = txtaPlanTrabajo.getText();
                String eva_pac = cbxEvaluacionPaciente.getSelectedItem().toString();
                String ubicacion_egreso = cbxUbicacionEgreso.getSelectedItem().toString();
                String anot_medica = txtaAnotMed.getText();
                String anot_enf = txtaAnotacionesEnf.getText();
                String prioridad = cbxPrioridad.getSelectedItem().toString();
                AdmisionEmergenciaTopico adEmertopico = new AdmisionEmergenciaTopico();
            
                    adEmertopico.setTopico_id(id_topico);
                    adEmertopico.setFb_apetito(apetito);
                    adEmertopico.setFb_sed(sed);
                    adEmertopico.setFb_deposi(deposiciones);
                    adEmertopico.setFb_sueno(sueno);
                    adEmertopico.setFb_orina(orinas);
                    adEmertopico.setMotivo_emer(motivoemer);
                    adEmertopico.setRelato(relatoemer);
                    adEmertopico.setEg_concie(conciencia);
                    adEmertopico.setEg_hidra(hidratacion);
                    adEmertopico.setEg_nutri(nutricion);
                    adEmertopico.setEg_fisic(examen_fisico);
                    adEmertopico.setPlan_trabajo(plan_trabajo);
                    adEmertopico.setAnot_medico(anot_medica);
                    adEmertopico.setAnot_enfer(anot_enf);
                    adEmertopico.setEval_paciente(eva_pac);
                    adEmertopico.setUbic_egreso(ubicacion_egreso);
                    adEmertopico.setPrioridad(prioridad);
                    if(adEmertopico.mantenimientoAdmisionEmergenciaTopico("U")==true){
                        retorna = true;
                    }
             
                return retorna;
    }
    
    // ELIMINAR DATOS TOPICOS DETALLE EXAMEN
    
    public void eliminarDetalleTopicoExamen(){
        if(tbExamAux.getSelectedRowCount()!=0){
            ElimEx.setVisible(true);
            
        } else 
            SelReg.setVisible(true);
    }
    
    public void eliminarDetalleTopicoDiagPresun(){
        if(tbImpDx.getSelectedRowCount()!=0){
            ElimDXP.setVisible(true);

        } else 
            SelReg.setVisible(true);
    }
    
    public void eliminarDetalleTopicoDiagFinal(){
        if(tbDxAlta.getSelectedRowCount()!=0){
            ElimDXF.setVisible(true);

        } else 
            SelReg.setVisible(true);
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
        ElimDXP = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jPanel69 = new javax.swing.JPanel();
        btnAlertConsulta4 = new javax.swing.JButton();
        btnEliminarNo = new javax.swing.JButton();
        ElimEx = new javax.swing.JDialog();
        jPanel19 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jPanel70 = new javax.swing.JPanel();
        btnAlertConsulta5 = new javax.swing.JButton();
        btnEliminarNo1 = new javax.swing.JButton();
        SelReg = new javax.swing.JDialog();
        jPanel18 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jPanel68 = new javax.swing.JPanel();
        btnAlertConsulta3 = new javax.swing.JButton();
        dlgListaImpDx = new javax.swing.JDialog();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbDiagnosticos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtBuscarDiagnostico = new javax.swing.JTextField();
        btnBuscarPaciente4 = new javax.swing.JButton();
        lblTipo1 = new javax.swing.JLabel();
        lblTipoDiag = new javax.swing.JLabel();
        dlgListaExamnAux = new javax.swing.JDialog();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbDatosLab = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        txtBusquedaTo = new javax.swing.JTextField();
        btnBuscarPaciente3 = new javax.swing.JButton();
        lblTipo = new javax.swing.JLabel();
        ErrorExistente = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        lblAd = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jPanel67 = new javax.swing.JPanel();
        btnAlertConsulta2 = new javax.swing.JButton();
        ErrorMas2 = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jPanel66 = new javax.swing.JPanel();
        btnAlertConsulta1 = new javax.swing.JButton();
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
                        jpmCabecera = new javax.swing.JPopupMenu();
                        jMenuItem4 = new javax.swing.JMenuItem();
                        jSeparator9 = new javax.swing.JPopupMenu.Separator();
                        jMenuItem5 = new javax.swing.JMenuItem();
                        jpmTopico = new javax.swing.JPopupMenu();
                        jMenuItem2 = new javax.swing.JMenuItem();
                        jSeparator2 = new javax.swing.JPopupMenu.Separator();
                        jMenuItem3 = new javax.swing.JMenuItem();
                        jMenuItem6 = new javax.swing.JMenuItem();
                        ElimDXF = new javax.swing.JDialog();
                        jPanel20 = new javax.swing.JPanel();
                        jLabel117 = new javax.swing.JLabel();
                        jLabel118 = new javax.swing.JLabel();
                        jPanel71 = new javax.swing.JPanel();
                        btnAlertConsulta6 = new javax.swing.JButton();
                        btnEliminarNo2 = new javax.swing.JButton();
                        ErrorExistenteDX = new javax.swing.JDialog();
                        jPanel14 = new javax.swing.JPanel();
                        lblAd1 = new javax.swing.JLabel();
                        jLabel119 = new javax.swing.JLabel();
                        jPanel72 = new javax.swing.JPanel();
                        btnAlertConsulta7 = new javax.swing.JButton();
                        jPanel8 = new javax.swing.JPanel();
                        btnNuevo = new javax.swing.JButton();
                        btnModificar = new javax.swing.JButton();
                        btnGuardar = new javax.swing.JButton();
                        btnEliminar = new javax.swing.JButton();
                        btnListado = new javax.swing.JButton();
                        lblNewMod = new javax.swing.JLabel();
                        lblCabpT = new javax.swing.JLabel();
                        txtIDTriaje = new javax.swing.JTextField();
                        lblIDHCTo = new javax.swing.JLabel();
                        lblIdFP = new javax.swing.JLabel();
                        lblusu = new javax.swing.JLabel();
                        jLabel84 = new javax.swing.JLabel();
                        lblID_PREVENTA = new javax.swing.JLabel();
                        lbl_ID_TRIAJE = new javax.swing.JLabel();
                        tbPaneles = new javax.swing.JTabbedPane();
                        pnlTopico = new javax.swing.JPanel();
                        jPanel17 = new javax.swing.JPanel();
                        txtBuscar = new javax.swing.JTextField();
                        fechaf = new com.toedter.calendar.JDateChooser();
                        fechai = new com.toedter.calendar.JDateChooser();
                        btnBuscar = new javax.swing.JButton();
                        jScrollPane4 = new javax.swing.JScrollPane();
                        tbTopico = new javax.swing.JTable();
                        jPanel9 = new javax.swing.JPanel();
                        pnlTopicoP = new javax.swing.JScrollPane();
                        pnlTopicoPrioridad = new javax.swing.JPanel();
                        jLabel38 = new javax.swing.JLabel();
                        jSeparator5 = new javax.swing.JSeparator();
                        jPanel3 = new javax.swing.JPanel();
                        jLabel48 = new javax.swing.JLabel();
                        jLabel49 = new javax.swing.JLabel();
                        txtApetito = new javax.swing.JTextField();
                        jLabel52 = new javax.swing.JLabel();
                        txtSed = new javax.swing.JTextField();
                        jLabel53 = new javax.swing.JLabel();
                        jLabel54 = new javax.swing.JLabel();
                        jLabel55 = new javax.swing.JLabel();
                        txtDeposiciones = new javax.swing.JTextField();
                        txtSueno = new javax.swing.JTextField();
                        txtOrinas = new javax.swing.JTextField();
                        jPanel5 = new javax.swing.JPanel();
                        jScrollPane7 = new javax.swing.JScrollPane();
                        txtaMotivo = new javax.swing.JTextArea();
                        jLabel56 = new javax.swing.JLabel();
                        jScrollPane6 = new javax.swing.JScrollPane();
                        txtaRelato = new javax.swing.JTextArea();
                        jLabel58 = new javax.swing.JLabel();
                        jLabel59 = new javax.swing.JLabel();
                        jLabel62 = new javax.swing.JLabel();
                        jScrollPane8 = new javax.swing.JScrollPane();
                        txtaExamenFisico = new javax.swing.JTextArea();
                        jLabel63 = new javax.swing.JLabel();
                        jScrollPane9 = new javax.swing.JScrollPane();
                        txtaPlanTrabajo = new javax.swing.JTextArea();
                        btnbuscarExamAux = new javax.swing.JButton();
                        btnBuscarImpDx = new javax.swing.JButton();
                        jScrollPane10 = new javax.swing.JScrollPane();
                        tbExamAux = new javax.swing.JTable();
                        jScrollPane11 = new javax.swing.JScrollPane();
                        tbImpDx = new javax.swing.JTable();
                        jLabel66 = new javax.swing.JLabel();
                        cbxEvaluacionPaciente = new javax.swing.JComboBox();
                        jLabel67 = new javax.swing.JLabel();
                        cbxUbicacionEgreso = new javax.swing.JComboBox();
                        jLabel68 = new javax.swing.JLabel();
                        jScrollPane12 = new javax.swing.JScrollPane();
                        txtaAnotMed = new javax.swing.JTextArea();
                        jLabel69 = new javax.swing.JLabel();
                        jScrollPane13 = new javax.swing.JScrollPane();
                        txtaAnotacionesEnf = new javax.swing.JTextArea();
                        jLabel70 = new javax.swing.JLabel();
                        jScrollPane14 = new javax.swing.JScrollPane();
                        tbDxAlta = new javax.swing.JTable();
                        jLabel72 = new javax.swing.JLabel();
                        btnQuitarExam = new javax.swing.JButton();
                        btnQuitarDiag = new javax.swing.JButton();
                        btnAgregarDiagfinal = new javax.swing.JButton();
                        btnQuitarDiagFinal = new javax.swing.JButton();
                        cbxPrioridad = new javax.swing.JComboBox();
                        lblIDTriajepTop = new javax.swing.JLabel();
                        jLabel61 = new javax.swing.JLabel();
                        txtNutricion = new javax.swing.JTextField();
                        txtHidratacion = new javax.swing.JTextField();
                        jLabel60 = new javax.swing.JLabel();
                        txtConciencia = new javax.swing.JTextField();
                        jLabel57 = new javax.swing.JLabel();
                        jLabel88 = new javax.swing.JLabel();
                        jPanel10 = new javax.swing.JPanel();
                        lblPacienteTo = new javax.swing.JLabel();
                        lblFormaP = new javax.swing.JLabel();
                        lblFP = new javax.swing.JLabel();
                        jPanel1 = new javax.swing.JPanel();
                        lblTriaje = new javax.swing.JLabel();
                        lbl1 = new javax.swing.JLabel();
                        lbl2 = new javax.swing.JTextField();
                        lblFormaP1 = new javax.swing.JLabel();
                        txtNHCTo = new javax.swing.JTextField();
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

                        ElimDXP.setAlwaysOnTop(true);
                        ElimDXP.setMinimumSize(new java.awt.Dimension(446, 240));
                        ElimDXP.setResizable(false);

                        jPanel16.setBackground(new java.awt.Color(255, 91, 70));
                        jPanel16.setMinimumSize(new java.awt.Dimension(310, 441));

                        jLabel113.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel113.setText("Eliminar el registro  ?");

                        jLabel114.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
                        jLabel114.setText("<html>Se eliminara el registro  <br>permanentemente</html> ");

                        jPanel69.setBackground(new java.awt.Color(43, 43, 43));

                        btnAlertConsulta4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnAlertConsulta4.setForeground(new java.awt.Color(240, 240, 240));
                        btnAlertConsulta4.setText("Si");
                        btnAlertConsulta4.setContentAreaFilled(false);
                        btnAlertConsulta4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAlertConsulta4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnAlertConsulta4.setIconTextGap(30);
                        btnAlertConsulta4.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlertConsulta4ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
                        jPanel69.setLayout(jPanel69Layout);
                        jPanel69Layout.setHorizontalGroup(
                            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                        );
                        jPanel69Layout.setVerticalGroup(
                            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        btnEliminarNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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

                        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                        jPanel16.setLayout(jPanel16Layout);
                        jPanel16Layout.setHorizontalGroup(
                            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel113))
                                .addContainerGap(162, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarNo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel16Layout.setVerticalGroup(
                            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel113)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEliminarNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout ElimDXPLayout = new javax.swing.GroupLayout(ElimDXP.getContentPane());
                        ElimDXP.getContentPane().setLayout(ElimDXPLayout);
                        ElimDXPLayout.setHorizontalGroup(
                            ElimDXPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        ElimDXPLayout.setVerticalGroup(
                            ElimDXPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 236, Short.MAX_VALUE)
                        );

                        ElimEx.setAlwaysOnTop(true);
                        ElimEx.setMinimumSize(new java.awt.Dimension(446, 240));

                        jPanel19.setBackground(new java.awt.Color(255, 91, 70));
                        jPanel19.setMinimumSize(new java.awt.Dimension(310, 441));

                        jLabel115.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel115.setText("Eliminar el registro  ?");

                        jLabel116.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
                        jLabel116.setText("<html>Se eliminara el registro  <br>permanentemente</html> ");

                        jPanel70.setBackground(new java.awt.Color(43, 43, 43));

                        btnAlertConsulta5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnAlertConsulta5.setForeground(new java.awt.Color(240, 240, 240));
                        btnAlertConsulta5.setText("Si");
                        btnAlertConsulta5.setContentAreaFilled(false);
                        btnAlertConsulta5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAlertConsulta5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnAlertConsulta5.setIconTextGap(30);
                        btnAlertConsulta5.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlertConsulta5ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
                        jPanel70.setLayout(jPanel70Layout);
                        jPanel70Layout.setHorizontalGroup(
                            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                        );
                        jPanel70Layout.setVerticalGroup(
                            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        btnEliminarNo1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnEliminarNo1.setForeground(new java.awt.Color(240, 240, 240));
                        btnEliminarNo1.setText("No");
                        btnEliminarNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                        btnEliminarNo1.setContentAreaFilled(false);
                        btnEliminarNo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnEliminarNo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnEliminarNo1.setIconTextGap(30);
                        btnEliminarNo1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEliminarNo1ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                        jPanel19.setLayout(jPanel19Layout);
                        jPanel19Layout.setHorizontalGroup(
                            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel115))
                                .addContainerGap(162, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel19Layout.setVerticalGroup(
                            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel115)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEliminarNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout ElimExLayout = new javax.swing.GroupLayout(ElimEx.getContentPane());
                        ElimEx.getContentPane().setLayout(ElimExLayout);
                        ElimExLayout.setHorizontalGroup(
                            ElimExLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        ElimExLayout.setVerticalGroup(
                            ElimExLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
                        );

                        SelReg.setAlwaysOnTop(true);
                        SelReg.setMinimumSize(new java.awt.Dimension(446, 240));
                        SelReg.setResizable(false);

                        jPanel18.setBackground(new java.awt.Color(241, 197, 14));
                        jPanel18.setMinimumSize(new java.awt.Dimension(310, 441));

                        jLabel110.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel110.setForeground(new java.awt.Color(51, 51, 51));
                        jLabel110.setText("Advertencia");

                        jLabel112.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel112.setForeground(new java.awt.Color(51, 51, 51));
                        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
                        jLabel112.setText("<html>Seleccione un registro</html> ");

                        jPanel68.setBackground(new java.awt.Color(43, 43, 43));

                        btnAlertConsulta3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnAlertConsulta3.setForeground(new java.awt.Color(240, 240, 240));
                        btnAlertConsulta3.setText("OK");
                        btnAlertConsulta3.setContentAreaFilled(false);
                        btnAlertConsulta3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAlertConsulta3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnAlertConsulta3.setIconTextGap(30);
                        btnAlertConsulta3.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlertConsulta3ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
                        jPanel68.setLayout(jPanel68Layout);
                        jPanel68Layout.setHorizontalGroup(
                            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel68Layout.setVerticalGroup(
                            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                        jPanel18.setLayout(jPanel18Layout);
                        jPanel18Layout.setHorizontalGroup(
                            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel110))
                                .addContainerGap(122, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel18Layout.setVerticalGroup(
                            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel110)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout SelRegLayout = new javax.swing.GroupLayout(SelReg.getContentPane());
                        SelReg.getContentPane().setLayout(SelRegLayout);
                        SelRegLayout.setHorizontalGroup(
                            SelRegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        SelRegLayout.setVerticalGroup(
                            SelRegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
                        );

                        dlgListaImpDx.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                        dlgListaImpDx.setMinimumSize(new java.awt.Dimension(681, 405));

                        jScrollPane16.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tbDiagnosticos = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false;
                            }
                        };
                        tbDiagnosticos.setForeground(new java.awt.Color(51, 51, 51));
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
                        tbDiagnosticos.setSelectionBackground(new java.awt.Color(102, 102, 102));
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

                        jPanel6.setBackground(new java.awt.Color(23, 160, 134));

                        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
                        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel13.setText("Diagnósticos");

                        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                        txtBuscarDiagnostico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        txtBuscarDiagnostico.setForeground(new java.awt.Color(98, 98, 98));
                        txtBuscarDiagnostico.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        txtBuscarDiagnostico.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtBuscarDiagnosticoCaretUpdate(evt);
                            }
                        });
                        txtBuscarDiagnostico.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                txtBuscarDiagnosticoMouseClicked(evt);
                            }
                        });
                        txtBuscarDiagnostico.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscarDiagnosticoActionPerformed(evt);
                            }
                        });
                        txtBuscarDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscarDiagnosticoKeyPressed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                        jPanel29.setLayout(jPanel29Layout);
                        jPanel29Layout.setHorizontalGroup(
                            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtBuscarDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel29Layout.setVerticalGroup(
                            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtBuscarDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        btnBuscarPaciente4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                        btnBuscarPaciente4.setContentAreaFilled(false);
                        btnBuscarPaciente4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarPaciente4.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPaciente4ActionPerformed(evt);
                            }
                        });

                        lblTipo1.setForeground(new java.awt.Color(23, 160, 134));
                        lblTipo1.setText("jLabel71");

                        lblTipoDiag.setForeground(new java.awt.Color(23, 160, 134));
                        lblTipoDiag.setText("jLabel78");

                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                        jPanel6.setLayout(jPanel6Layout);
                        jPanel6Layout.setHorizontalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(lblTipo1)
                                        .addContainerGap(64, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTipoDiag)
                                        .addGap(27, 27, 27))))
                        );
                        jPanel6Layout.setVerticalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblTipo1))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(lblTipoDiag))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(14, 14, 14))
                        );

                        javax.swing.GroupLayout dlgListaImpDxLayout = new javax.swing.GroupLayout(dlgListaImpDx.getContentPane());
                        dlgListaImpDx.getContentPane().setLayout(dlgListaImpDxLayout);
                        dlgListaImpDxLayout.setHorizontalGroup(
                            dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgListaImpDxLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        );
                        dlgListaImpDxLayout.setVerticalGroup(
                            dlgListaImpDxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgListaImpDxLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        );

                        dlgListaExamnAux.setAlwaysOnTop(true);
                        dlgListaExamnAux.setMinimumSize(new java.awt.Dimension(681, 405));

                        jScrollPane15.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tbDatosLab = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false;
                            }
                        };
                        tbDatosLab.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        tbDatosLab.setForeground(new java.awt.Color(51, 51, 51));
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
                        tbDatosLab.setSelectionBackground(new java.awt.Color(102, 102, 102));
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

                        jPanel2.setBackground(new java.awt.Color(23, 160, 134));

                        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
                        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel12.setText("Exámenes Auxiliares");

                        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

                        txtBusquedaTo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        txtBusquedaTo.setForeground(new java.awt.Color(98, 98, 98));
                        txtBusquedaTo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        txtBusquedaTo.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtBusquedaToCaretUpdate(evt);
                            }
                        });
                        txtBusquedaTo.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                txtBusquedaToMouseClicked(evt);
                            }
                        });
                        txtBusquedaTo.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBusquedaToActionPerformed(evt);
                            }
                        });
                        txtBusquedaTo.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBusquedaToKeyPressed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                        jPanel28.setLayout(jPanel28Layout);
                        jPanel28Layout.setHorizontalGroup(
                            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtBusquedaTo, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel28Layout.setVerticalGroup(
                            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtBusquedaTo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        btnBuscarPaciente3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                        btnBuscarPaciente3.setContentAreaFilled(false);
                        btnBuscarPaciente3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarPaciente3.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPaciente3ActionPerformed(evt);
                            }
                        });

                        lblTipo.setForeground(new java.awt.Color(23, 160, 134));
                        lblTipo.setText("jLabel71");

                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                        jPanel2.setLayout(jPanel2Layout);
                        jPanel2Layout.setHorizontalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(lblTipo)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblTipo))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
                        );

                        javax.swing.GroupLayout dlgListaExamnAuxLayout = new javax.swing.GroupLayout(dlgListaExamnAux.getContentPane());
                        dlgListaExamnAux.getContentPane().setLayout(dlgListaExamnAuxLayout);
                        dlgListaExamnAuxLayout.setHorizontalGroup(
                            dlgListaExamnAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                        );
                        dlgListaExamnAuxLayout.setVerticalGroup(
                            dlgListaExamnAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgListaExamnAuxLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        ErrorExistente.setAlwaysOnTop(true);
                        ErrorExistente.setMinimumSize(new java.awt.Dimension(446, 240));
                        ErrorExistente.setResizable(false);

                        jPanel13.setBackground(new java.awt.Color(241, 197, 14));
                        jPanel13.setMinimumSize(new java.awt.Dimension(310, 441));

                        lblAd.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        lblAd.setForeground(new java.awt.Color(51, 51, 51));
                        lblAd.setText("Advertencia");

                        jLabel111.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel111.setForeground(new java.awt.Color(51, 51, 51));
                        jLabel111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
                        jLabel111.setText("<html>Este examen ya se encuentra <br>registrado</html> ");

                        jPanel67.setBackground(new java.awt.Color(43, 43, 43));

                        btnAlertConsulta2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnAlertConsulta2.setForeground(new java.awt.Color(240, 240, 240));
                        btnAlertConsulta2.setText("OK");
                        btnAlertConsulta2.setContentAreaFilled(false);
                        btnAlertConsulta2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAlertConsulta2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnAlertConsulta2.setIconTextGap(30);
                        btnAlertConsulta2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlertConsulta2ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
                        jPanel67.setLayout(jPanel67Layout);
                        jPanel67Layout.setHorizontalGroup(
                            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel67Layout.setVerticalGroup(
                            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                        jPanel13.setLayout(jPanel13Layout);
                        jPanel13Layout.setHorizontalGroup(
                            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAd))
                                .addContainerGap(102, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel13Layout.setVerticalGroup(
                            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lblAd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout ErrorExistenteLayout = new javax.swing.GroupLayout(ErrorExistente.getContentPane());
                        ErrorExistente.getContentPane().setLayout(ErrorExistenteLayout);
                        ErrorExistenteLayout.setHorizontalGroup(
                            ErrorExistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        ErrorExistenteLayout.setVerticalGroup(
                            ErrorExistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
                        );

                        ErrorMas2.setAlwaysOnTop(true);
                        ErrorMas2.setMinimumSize(new java.awt.Dimension(446, 240));
                        ErrorMas2.setResizable(false);

                        jPanel12.setBackground(new java.awt.Color(241, 197, 14));
                        jPanel12.setMinimumSize(new java.awt.Dimension(310, 441));

                        jLabel108.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel108.setForeground(new java.awt.Color(51, 51, 51));
                        jLabel108.setText("Advertencia");

                        jLabel109.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel109.setForeground(new java.awt.Color(51, 51, 51));
                        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
                        jLabel109.setText("<html>Solo se pueden registrar <br>dos diagnósticos</html> ");

                        jPanel66.setBackground(new java.awt.Color(43, 43, 43));

                        btnAlertConsulta1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnAlertConsulta1.setForeground(new java.awt.Color(240, 240, 240));
                        btnAlertConsulta1.setText("OK");
                        btnAlertConsulta1.setContentAreaFilled(false);
                        btnAlertConsulta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAlertConsulta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnAlertConsulta1.setIconTextGap(30);
                        btnAlertConsulta1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlertConsulta1ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
                        jPanel66.setLayout(jPanel66Layout);
                        jPanel66Layout.setHorizontalGroup(
                            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel66Layout.setVerticalGroup(
                            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                        jPanel12.setLayout(jPanel12Layout);
                        jPanel12Layout.setHorizontalGroup(
                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel108))
                                .addContainerGap(148, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel12Layout.setVerticalGroup(
                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel108)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout ErrorMas2Layout = new javax.swing.GroupLayout(ErrorMas2.getContentPane());
                        ErrorMas2.getContentPane().setLayout(ErrorMas2Layout);
                        ErrorMas2Layout.setHorizontalGroup(
                            ErrorMas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        ErrorMas2Layout.setVerticalGroup(
                            ErrorMas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
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

                        javax.swing.GroupLayout panelSinHCLayout = new javax.swing.GroupLayout(panelSinHC);
                        panelSinHC.setLayout(panelSinHCLayout);
                        panelSinHCLayout.setHorizontalGroup(
                            panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel87)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel46)
                                .addContainerGap(255, Short.MAX_VALUE))
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
                                .addContainerGap(60, Short.MAX_VALUE))
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

                        jpmTopico.setBackground(new java.awt.Color(255, 255, 255));
                        jpmTopico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jpmTopico.setForeground(new java.awt.Color(51, 51, 51));

                        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jMenuItem2.setForeground(new java.awt.Color(51, 51, 51));
                        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones.png"))); // NOI18N
                        jMenuItem2.setText("Opciones");
                        jpmTopico.add(jMenuItem2);
                        jpmTopico.add(jSeparator2);

                        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jMenuItem3.setForeground(new java.awt.Color(51, 51, 51));
                        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/imprimir16x16.png"))); // NOI18N
                        jMenuItem3.setText("Imprimir");
                        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jMenuItem3ActionPerformed(evt);
                            }
                        });
                        jpmTopico.add(jMenuItem3);

                        jMenuItem6.setBackground(new java.awt.Color(255, 255, 255));
                        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jMenuItem6.setForeground(new java.awt.Color(51, 51, 51));
                        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/formatoEmer16x16.png"))); // NOI18N
                        jMenuItem6.setText("Imprimir Formato Completo");
                        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jMenuItem6ActionPerformed(evt);
                            }
                        });
                        jpmTopico.add(jMenuItem6);

                        ElimDXF.setAlwaysOnTop(true);
                        ElimDXF.setMinimumSize(new java.awt.Dimension(446, 240));

                        jPanel20.setBackground(new java.awt.Color(255, 91, 70));
                        jPanel20.setMinimumSize(new java.awt.Dimension(310, 441));

                        jLabel117.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel117.setText("Eliminar el registro  ?");

                        jLabel118.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
                        jLabel118.setText("<html>Se eliminara el registro  <br>permanentemente</html> ");

                        jPanel71.setBackground(new java.awt.Color(43, 43, 43));

                        btnAlertConsulta6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnAlertConsulta6.setForeground(new java.awt.Color(240, 240, 240));
                        btnAlertConsulta6.setText("Si");
                        btnAlertConsulta6.setContentAreaFilled(false);
                        btnAlertConsulta6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAlertConsulta6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnAlertConsulta6.setIconTextGap(30);
                        btnAlertConsulta6.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlertConsulta6ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
                        jPanel71.setLayout(jPanel71Layout);
                        jPanel71Layout.setHorizontalGroup(
                            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel71Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                        );
                        jPanel71Layout.setVerticalGroup(
                            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel71Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        btnEliminarNo2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnEliminarNo2.setForeground(new java.awt.Color(240, 240, 240));
                        btnEliminarNo2.setText("No");
                        btnEliminarNo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                        btnEliminarNo2.setContentAreaFilled(false);
                        btnEliminarNo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnEliminarNo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnEliminarNo2.setIconTextGap(30);
                        btnEliminarNo2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEliminarNo2ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                        jPanel20.setLayout(jPanel20Layout);
                        jPanel20Layout.setHorizontalGroup(
                            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel117))
                                .addContainerGap(162, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel20Layout.setVerticalGroup(
                            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel117)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEliminarNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout ElimDXFLayout = new javax.swing.GroupLayout(ElimDXF.getContentPane());
                        ElimDXF.getContentPane().setLayout(ElimDXFLayout);
                        ElimDXFLayout.setHorizontalGroup(
                            ElimDXFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        ElimDXFLayout.setVerticalGroup(
                            ElimDXFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
                        );

                        ErrorExistenteDX.setAlwaysOnTop(true);
                        ErrorExistenteDX.setMinimumSize(new java.awt.Dimension(446, 240));
                        ErrorExistenteDX.setResizable(false);

                        jPanel14.setBackground(new java.awt.Color(241, 197, 14));
                        jPanel14.setMinimumSize(new java.awt.Dimension(310, 441));

                        lblAd1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        lblAd1.setForeground(new java.awt.Color(51, 51, 51));
                        lblAd1.setText("Advertencia");

                        jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel119.setForeground(new java.awt.Color(51, 51, 51));
                        jLabel119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
                        jLabel119.setText("<html>Este diagnóstico ya se encuentra <br>registrado</html> ");

                        jPanel72.setBackground(new java.awt.Color(43, 43, 43));

                        btnAlertConsulta7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnAlertConsulta7.setForeground(new java.awt.Color(240, 240, 240));
                        btnAlertConsulta7.setText("OK");
                        btnAlertConsulta7.setContentAreaFilled(false);
                        btnAlertConsulta7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAlertConsulta7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnAlertConsulta7.setIconTextGap(30);
                        btnAlertConsulta7.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAlertConsulta7ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
                        jPanel72.setLayout(jPanel72Layout);
                        jPanel72Layout.setHorizontalGroup(
                            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel72Layout.setVerticalGroup(
                            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                        jPanel14.setLayout(jPanel14Layout);
                        jPanel14Layout.setHorizontalGroup(
                            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAd1))
                                .addContainerGap(70, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel14Layout.setVerticalGroup(
                            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lblAd1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout ErrorExistenteDXLayout = new javax.swing.GroupLayout(ErrorExistenteDX.getContentPane());
                        ErrorExistenteDX.getContentPane().setLayout(ErrorExistenteDXLayout);
                        ErrorExistenteDXLayout.setHorizontalGroup(
                            ErrorExistenteDXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        ErrorExistenteDXLayout.setVerticalGroup(
                            ErrorExistenteDXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
                        );

                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                        setTitle("Admisión emergencia");
                        addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                formKeyPressed(evt);
                            }
                        });

                        jPanel8.setBackground(new java.awt.Color(23, 160, 134));
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
                        btnModificar.setEnabled(false);
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

                        btnListado.setBackground(new java.awt.Color(204, 204, 204));
                        btnListado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnListado.setForeground(new java.awt.Color(255, 255, 255));
                        btnListado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Orden de compra-32.png"))); // NOI18N
                        btnListado.setText("Listado");
                        btnListado.setContentAreaFilled(false);
                        btnListado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnListado.setFocusable(false);
                        btnListado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        btnListado.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btnListado.setIconTextGap(30);
                        btnListado.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnListadoActionPerformed(evt);
                            }
                        });

                        lblNewMod.setText("jLabel39");

                        lblCabpT.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
                        lblCabpT.setText("jLabel71");

                        txtIDTriaje.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txtIDTriaje.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                        txtIDTriaje.setEnabled(false);
                        txtIDTriaje.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtIDTriajeCaretUpdate(evt);
                            }
                        });

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
                        jLabel84.setText("<html>Tópicos<span style=\"font-size:'14px'\"><br>Emergencia</br></span></html>");

                        lblID_PREVENTA.setForeground(new java.awt.Color(23, 160, 134));
                        lblID_PREVENTA.setText("jLabel1");

                        lbl_ID_TRIAJE.setForeground(new java.awt.Color(23, 160, 134));
                        lbl_ID_TRIAJE.setText("jLabel2");

                        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                        jPanel8.setLayout(jPanel8Layout);
                        jPanel8Layout.setHorizontalGroup(
                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                                    .addGap(10, 10, 10)
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnListado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(lblIdFP, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                                .addComponent(lblCabpT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                    .addGap(29, 29, 29)
                                                                    .addComponent(lblIDHCTo)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(lblID_PREVENTA))
                                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(lblNewMod)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(txtIDTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(lbl_ID_TRIAJE)))
                                                            .addGap(0, 59, Short.MAX_VALUE))))))
                                        .addGap(0, 35, Short.MAX_VALUE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
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
                                .addComponent(btnListado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCabpT)
                                    .addComponent(lblIDHCTo)
                                    .addComponent(lblID_PREVENTA))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblIdFP)
                                    .addComponent(lblNewMod)
                                    .addComponent(txtIDTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_ID_TRIAJE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
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

                        pnlTopico.setBackground(new java.awt.Color(255, 255, 255));
                        pnlTopico.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        jPanel17.setBackground(new java.awt.Color(22, 22, 22));

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

                        fechaf.setBackground(new java.awt.Color(22, 22, 22));
                        fechaf.setForeground(new java.awt.Color(102, 102, 102));
                        fechaf.setDateFormatString("dd/MM/yyyy");
                        fechaf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                        fechai.setBackground(new java.awt.Color(22, 22, 22));
                        fechai.setForeground(new java.awt.Color(102, 102, 102));
                        fechai.setDateFormatString("dd/MM/yyyy");
                        fechai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
                        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/continuar.png"))); // NOI18N
                        btnBuscar.setMnemonic('B');
                        btnBuscar.setText("Iniciar");
                        btnBuscar.setToolTipText("Buscar (Alt + B)");
                        btnBuscar.setContentAreaFilled(false);
                        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                        jPanel17.setLayout(jPanel17Layout);
                        jPanel17Layout.setHorizontalGroup(
                            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(fechai, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel17Layout.setVerticalGroup(
                            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(fechai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        );

                        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tbTopico = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false;
                            }
                        };
                        tbTopico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        tbTopico.setForeground(new java.awt.Color(51, 51, 51));
                        tbTopico.setModel(new javax.swing.table.DefaultTableModel(
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
                        tbTopico.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tbTopico.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tbTopico.getTableHeader().setReorderingAllowed(false);
                        tbTopico.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tbTopicoMouseClicked(evt);
                            }
                            public void mouseReleased(java.awt.event.MouseEvent evt) {
                                tbTopicoMouseReleased(evt);
                            }
                        });
                        tbTopico.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tbTopicoKeyPressed(evt);
                            }
                        });
                        jScrollPane4.setViewportView(tbTopico);

                        javax.swing.GroupLayout pnlTopicoLayout = new javax.swing.GroupLayout(pnlTopico);
                        pnlTopico.setLayout(pnlTopicoLayout);
                        pnlTopicoLayout.setHorizontalGroup(
                            pnlTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                        );
                        pnlTopicoLayout.setVerticalGroup(
                            pnlTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTopicoLayout.createSequentialGroup()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                        );

                        tbPaneles.addTab("Tópico", pnlTopico);

                        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                        pnlTopicoP.setBackground(new java.awt.Color(255, 255, 255));
                        pnlTopicoP.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        pnlTopicoPrioridad.setBackground(new java.awt.Color(255, 255, 255));
                        pnlTopicoPrioridad.setPreferredSize(new java.awt.Dimension(594, 950));

                        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                        jLabel38.setText("Prioridad");

                        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel48.setText("Funciones Biológicas");

                        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel49.setText("Apetito");

                        txtApetito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel52.setText("Sed");

                        txtSed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel53.setText("Deposiciones");

                        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel54.setText("Sueño");

                        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel55.setText("Orinas");

                        txtDeposiciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtDeposiciones.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtDeposicionesActionPerformed(evt);
                            }
                        });

                        txtSueno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                        txtOrinas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                        jPanel3.setLayout(jPanel3Layout);
                        jPanel3Layout.setHorizontalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel48)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel49)
                                            .addComponent(jLabel52))
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtSed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                            .addComponent(txtApetito, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel54)
                                            .addComponent(jLabel55))
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtOrinas, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                            .addComponent(txtSueno))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel53)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDeposiciones)))
                                .addContainerGap())
                        );
                        jPanel3Layout.setVerticalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel49)
                                            .addComponent(txtApetito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel52)
                                            .addComponent(txtSed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel54)
                                            .addComponent(txtSueno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel53)
                                            .addComponent(txtDeposiciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel55)
                                            .addComponent(txtOrinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 2, Short.MAX_VALUE))
                        );

                        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        txtaMotivo.setColumns(20);
                        txtaMotivo.setRows(5);
                        txtaMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtaMotivoKeyPressed(evt);
                            }
                        });
                        jScrollPane7.setViewportView(txtaMotivo);

                        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel56.setText("Motivo de Emergencia");

                        txtaRelato.setColumns(20);
                        txtaRelato.setRows(5);
                        jScrollPane6.setViewportView(txtaRelato);

                        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel58.setText("Relato");

                        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                        jPanel5.setLayout(jPanel5Layout);
                        jPanel5Layout.setHorizontalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel58)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        );
                        jPanel5Layout.setVerticalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel58))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(0, 5, Short.MAX_VALUE))
                        );

                        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        jLabel59.setText("Estado General");

                        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel62.setText("Examen Físico");

                        jScrollPane8.setPreferredSize(new java.awt.Dimension(164, 94));

                        txtaExamenFisico.setColumns(20);
                        txtaExamenFisico.setRows(5);
                        jScrollPane8.setViewportView(txtaExamenFisico);

                        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel63.setText("Plan de Trabajo");

                        txtaPlanTrabajo.setColumns(20);
                        txtaPlanTrabajo.setRows(5);
                        jScrollPane9.setViewportView(txtaPlanTrabajo);

                        btnbuscarExamAux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                        btnbuscarExamAux.setMnemonic('E');
                        btnbuscarExamAux.setToolTipText("Buscar Examen (Alt + E)");
                        btnbuscarExamAux.setContentAreaFilled(false);
                        btnbuscarExamAux.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnbuscarExamAuxActionPerformed(evt);
                            }
                        });

                        btnBuscarImpDx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                        btnBuscarImpDx.setMnemonic('D');
                        btnBuscarImpDx.setToolTipText("Buscar Diagnóstico (Alt + D)");
                        btnBuscarImpDx.setContentAreaFilled(false);
                        btnBuscarImpDx.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarImpDxActionPerformed(evt);
                            }
                        });

                        jScrollPane10.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tbExamAux.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        tbExamAux.setForeground(new java.awt.Color(51, 51, 51));
                        tbExamAux.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Código", "Exámenes", "Precio"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tbExamAux.setGridColor(new java.awt.Color(255, 255, 255));
                        tbExamAux.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tbExamAux.getTableHeader().setReorderingAllowed(false);
                        tbExamAux.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tbExamAuxKeyPressed(evt);
                            }
                        });
                        jScrollPane10.setViewportView(tbExamAux);

                        jScrollPane11.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tbImpDx.setForeground(new java.awt.Color(51, 51, 51));
                        tbImpDx.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Nro", "Código", "Diagnósico"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tbImpDx.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tbImpDx.getTableHeader().setReorderingAllowed(false);
                        tbImpDx.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tbImpDxKeyPressed(evt);
                            }
                        });
                        jScrollPane11.setViewportView(tbImpDx);

                        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel66.setText("Evaluación del Paciente");

                        cbxEvaluacionPaciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        cbxEvaluacionPaciente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MEJORADO", "NO MEJORADO", "RECUPERADO", "NO TRATADO", "FALLECIDO" }));

                        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel67.setText("Ubicación al Egreso");

                        cbxUbicacionEgreso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        cbxUbicacionEgreso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FUGADO", "ALTA", "RETIRO VOLUNTARIO", "TRASLADO", "HOSPITALIZACIÓN" }));

                        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel68.setText("Anotaciones Médicas");

                        txtaAnotMed.setColumns(20);
                        txtaAnotMed.setRows(5);
                        jScrollPane12.setViewportView(txtaAnotMed);

                        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel69.setText("Anotaciones de Enfermería");

                        txtaAnotacionesEnf.setColumns(20);
                        txtaAnotacionesEnf.setRows(5);
                        jScrollPane13.setViewportView(txtaAnotacionesEnf);

                        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel70.setText("Exámenes Auxiliares");

                        tbDxAlta.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Nro", "Código", "Diagnóstico"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tbDxAlta.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tbDxAlta.getTableHeader().setReorderingAllowed(false);
                        tbDxAlta.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tbDxAltaKeyPressed(evt);
                            }
                        });
                        jScrollPane14.setViewportView(tbDxAlta);

                        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel72.setText("Dx. de Alta");

                        btnQuitarExam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                        btnQuitarExam.setMnemonic('E');
                        btnQuitarExam.setToolTipText("Buscar Examen (Alt + E)");
                        btnQuitarExam.setContentAreaFilled(false);
                        btnQuitarExam.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnQuitarExamActionPerformed(evt);
                            }
                        });

                        btnQuitarDiag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                        btnQuitarDiag.setMnemonic('E');
                        btnQuitarDiag.setToolTipText("Buscar Examen (Alt + E)");
                        btnQuitarDiag.setContentAreaFilled(false);
                        btnQuitarDiag.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnQuitarDiagActionPerformed(evt);
                            }
                        });

                        btnAgregarDiagfinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                        btnAgregarDiagfinal.setMnemonic('D');
                        btnAgregarDiagfinal.setToolTipText("Buscar Diagnóstico (Alt + D)");
                        btnAgregarDiagfinal.setContentAreaFilled(false);
                        btnAgregarDiagfinal.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregarDiagfinalActionPerformed(evt);
                            }
                        });

                        btnQuitarDiagFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                        btnQuitarDiagFinal.setMnemonic('E');
                        btnQuitarDiagFinal.setToolTipText("Buscar Examen (Alt + E)");
                        btnQuitarDiagFinal.setContentAreaFilled(false);
                        btnQuitarDiagFinal.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnQuitarDiagFinalActionPerformed(evt);
                            }
                        });

                        cbxPrioridad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        cbxPrioridad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "I", "II", "III", "IV", "Cadáver" }));

                        lblIDTriajepTop.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
                        lblIDTriajepTop.setForeground(new java.awt.Color(255, 255, 255));
                        lblIDTriajepTop.setText("jLabel71");

                        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel61.setText("Nutrición");

                        txtNutricion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                        txtHidratacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel60.setText("Hidratación");

                        txtConciencia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel57.setText("Conciencia");

                        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel88.setText("Diagnosticos");

                        javax.swing.GroupLayout pnlTopicoPrioridadLayout = new javax.swing.GroupLayout(pnlTopicoPrioridad);
                        pnlTopicoPrioridad.setLayout(pnlTopicoPrioridadLayout);
                        pnlTopicoPrioridadLayout.setHorizontalGroup(
                            pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                        .addComponent(jLabel88)
                                        .addGap(80, 80, 80)
                                        .addComponent(btnBuscarImpDx, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnQuitarDiag, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane14)
                                            .addComponent(jScrollPane11)
                                            .addComponent(jScrollPane10)
                                            .addComponent(jSeparator5)
                                            .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel62)
                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel59)
                                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                        .addComponent(jLabel66)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(cbxEvaluacionPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane9)
                                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                                .addComponent(jLabel67)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(cbxUbicacionEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(0, 0, Short.MAX_VALUE))))
                                            .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                .addComponent(jLabel57)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtConciencia, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel60)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtHidratacion, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel61)
                                                .addGap(28, 28, 28)
                                                .addComponent(txtNutricion))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopicoPrioridadLayout.createSequentialGroup()
                                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                        .addComponent(jScrollPane12)
                                                        .addGap(10, 10, 10))
                                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel68)
                                                            .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(13, 13, 13)
                                                                .addComponent(cbxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(lblIDTriajepTop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                                .addComponent(jLabel70)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(btnbuscarExamAux, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnQuitarExam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                                .addComponent(jLabel72)
                                                                .addGap(94, 94, 94)
                                                                .addComponent(btnAgregarDiagfinal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnQuitarDiagFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(223, 223, 223)))
                                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addContainerGap())))
                        );
                        pnlTopicoPrioridadLayout.setVerticalGroup(
                            pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel38)
                                    .addComponent(cbxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIDTriajepTop, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtConciencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel60)
                                    .addComponent(txtHidratacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel61)
                                    .addComponent(txtNutricion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel63))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel66)
                                            .addComponent(cbxEvaluacionPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel67)
                                            .addComponent(cbxUbicacionEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnQuitarExam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnbuscarExamAux, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel88)
                                            .addComponent(btnQuitarDiag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel68)
                                            .addComponent(jLabel69))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addComponent(btnBuscarImpDx, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                        .addComponent(jLabel72)
                                        .addGap(5, 5, 5)
                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                                    .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                        .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnQuitarDiagFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAgregarDiagfinal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        );

                        pnlTopicoP.setViewportView(pnlTopicoPrioridad);

                        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                        jPanel9.setLayout(jPanel9Layout);
                        jPanel9Layout.setHorizontalGroup(
                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlTopicoP)
                        );
                        jPanel9Layout.setVerticalGroup(
                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlTopicoP, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        );

                        tbPaneles.addTab("tab2", jPanel9);

                        jPanel10.setBackground(new java.awt.Color(43, 43, 43));
                        jPanel10.setPreferredSize(new java.awt.Dimension(929, 115));

                        lblPacienteTo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        lblPacienteTo.setForeground(new java.awt.Color(255, 255, 255));
                        lblPacienteTo.setText("Paciente");

                        lblFormaP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        lblFormaP.setForeground(new java.awt.Color(204, 204, 204));
                        lblFormaP.setText("FORMA DE PAGO");

                        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        lblFP.setForeground(new java.awt.Color(204, 204, 204));

                        jPanel1.setBackground(new java.awt.Color(22, 22, 22));

                        lblTriaje.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        lblTriaje.setForeground(new java.awt.Color(204, 204, 204));
                        lblTriaje.setText("TRIAJE |");

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTriaje)
                                .addContainerGap())
                        );

                        lbl1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        lbl1.setText("ID Tópico:");

                        lbl2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        lbl2.setEnabled(false);
                        lbl2.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                lbl2CaretUpdate(evt);
                            }
                        });

                        lblFormaP1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        lblFormaP1.setForeground(new java.awt.Color(204, 204, 204));
                        lblFormaP1.setText("Nº H. C.");

                        txtNHCTo.setEditable(false);
                        txtNHCTo.setBackground(new java.awt.Color(43, 43, 43));
                        txtNHCTo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        txtNHCTo.setForeground(new java.awt.Color(204, 204, 204));
                        txtNHCTo.setBorder(null);
                        txtNHCTo.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtNHCToCaretUpdate(evt);
                            }
                        });
                        txtNHCTo.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtNHCToKeyPressed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                        jPanel10.setLayout(jPanel10Layout);
                        jPanel10Layout.setHorizontalGroup(
                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPacienteTo, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFormaP)
                                            .addComponent(lblFormaP1))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblFP, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                            .addComponent(txtNHCTo))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67))
                        );
                        jPanel10Layout.setVerticalGroup(
                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl1)
                                        .addComponent(lbl2))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(lblPacienteTo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblFormaP1)
                                            .addComponent(txtNHCTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblFormaP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblFP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                                    .addComponent(tbPaneles)
                                    .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tbPaneles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 

  
                if(lblNewMod.getText().equals("N")){ // NUEVO REGISTRO DE TOPICO
                            if(guardarDatos()== true){
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Guardados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=1;
                                adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, "","");
                                tbPaneles.setSelectedIndex(0);
                                adEmerTo.reporteTopico(lbl2.getText());
                                Actualizar_ESTADO_PREVENTA();
                                lbl2.setText("");
                                limpiarDatosTopico();
                                jPanel9.setVisible(false);
                                
                                
                                
 
                            }else{
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                adEmerTo.reporteTopico(lbl2.getText());
                                lbl2.setText("");
                                limpiarDatosTopico();
                                jPanel9.setVisible(false);
                 
 
                            }
                } else { // MODIFICAR TOPICO
                    
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(255,153,51)); 
                    Mensaje.setText("Desea Actualizar el Registro ?");
                    eli.setText("Si");
                    eli.setVisible(true);
                    noeli.setVisible(true);
                    tge=2;
                    
                }
      
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
            tg=1;
            btnGuardar.setEnabled(false);
            btnEliminar.setEnabled(false);
            lblNewMod.setText("N");
            habilitarDatosTopico();
            tbPaneles.setSelectedIndex(1);
            /////////////
             BuscarHC();    
            filtrarDatos();
            limpiarDatosTopico();
         
            jPanel9.setVisible(false);
        
            //JOptionPane.showMessageDialog(this, txtaMotivo.getText());
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        lblNewMod.setText("M");
        enviarDatosdeTopico();
        tbPaneles.setSelectedIndex(1);
        
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

    private void btnListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListadoActionPerformed
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, "","");
        tbPaneles.setSelectedIndex(0);
    }//GEN-LAST:event_btnListadoActionPerformed

    private void tbPanelesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPanelesMouseClicked
        
    }//GEN-LAST:event_tbPanelesMouseClicked

    private void txtNHCToCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNHCToCaretUpdate
        if(lblNewMod.getText().equals("N")){
            if(txtNHCTo.getText().length()==8){
//                AdmisionEmergenciaTopico adEmerTo = new AdmisionEmergenciaTopico();
//                adEmerCab.mostrarHCTopico(formatoNHC(txtNHCTo.getText()));
//                adEmerTo.cargarFormatTriajeparaTopico(lblIDHCTo.getText(),"",tbMostrarTriajepT);
//                if(tbMostrarTriajepT.getRowCount()!=0){
                    pnlTopicoP.setVisible(true);
//                    adEmerCab.cargarFormatEmer(lblIDHCTo.getText(),"",tbFormatosEmer);
//                    dlgMostrarDatosTriajeT.setVisible(true);
//                    dlgMostrarDatosTriajeT.setLocationRelativeTo(null);//en el centro
//                    dlgMostrarDatosTriajeT.setResizable(false);
//                    dlgMostrarDatosTriajeT.getContentPane().setBackground(Color.WHITE);
//                } else {
//                    btnGuardar.setEnabled(false);
//                    btnEliminar.setEnabled(false);
//                    JOptionPane.showMessageDialog(this, "No hay registros");
//                    txtNHCTo.setText("");
//                    lbl2.setText("");
//                }
            }else {
                lblPacienteTo.setText("Paciente");
                lbl2.setText("");
                jPanel9.setVisible(false);
                btnGuardar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        } else {//MODIFICAR TOPICO
            if(txtNHCTo.getText().length()==8){
                AdmisionEmergenciaCabecera adEmerCab6 = new AdmisionEmergenciaCabecera();
                adEmerCab6.mostrarHCTopico(formatoNHC(txtNHCTo.getText()));
//                adEmerTo.cargarDatosTopico(tbDatosTopico, lblIDHCTo.getText(), "");
//                if(tbDatosTopico.getRowCount()!=0){
//                    dlgMostrarDatosTopico.setVisible(true);
//                    dlgMostrarDatosTopico.setLocationRelativeTo(null);//en el centro
//                    dlgMostrarDatosTopico.setResizable(false);
//                    dlgMostrarDatosTopico.getContentPane().setBackground(Color.WHITE);
//                    m = (DefaultTableModel)tbDiagPresun.getModel();
//                    int filas = tbDiagPresun.getRowCount();
//                    for(int i =0;i<filas;i++){
//                        m.removeRow(0);
//                    }
//                    DefaultTableModel m2 = (DefaultTableModel)tbDiagFinal.getModel(); 
//                    m2 = (DefaultTableModel)tbDiagFinal.getModel();
//                    int filasf = tbDiagFinal.getRowCount();
//                    for(int i =0;i<filasf;i++){
//                        m2.removeRow(0);
//                    }
//                    DefaultTableModel m3 = (DefaultTableModel)tbExamenes.getModel(); 
//                    m3 = (DefaultTableModel)tbExamenes.getModel();
//                    int filase = tbExamenes.getRowCount();
//                    for(int i =0;i<filase;i++){
//                        m3.removeRow(0);
//                    }
//                    lblUsuario.setText(lblusu.getText());
//                }else{
//                    JOptionPane.showMessageDialog(this, "No hay registros");
//                    txtNHCTo.setText("");
//                    lbl2.setText("");
//                }
            }
        }
    }//GEN-LAST:event_txtNHCToCaretUpdate

    private void txtNHCToKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNHCToKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNHCToKeyPressed

    private void tbDatosLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDatosLabKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbDatosLab.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBusquedaTo.requestFocus();
            tbDatosLab.getSelectionModel().setSelectionInterval(0,0);
        }
        try {
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER){
            if(lblTipo.getText().equals("modex")){
                enviarDatosExamenes();
                formatotbExamenAux(tbExamAux);
//                tbTopico.getSelectionModel().setSelectionInterval(0, 0);
//                tbTopico.requestFocus();
            }else
                enviarDatosExamAux();
        }
        } catch (Exception e) {
            System.out.println("error_ " + e.getMessage());
        }
    }//GEN-LAST:event_tbDatosLabKeyPressed

    private void tbDatosLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosLabMouseClicked
        if(evt.getClickCount()==2){
            if(lblTipo.getText().equals("modex"))
                enviarDatosExamenes();
            else
                enviarDatosExamAux();
        }
    }//GEN-LAST:event_tbDatosLabMouseClicked

    private void btnBuscarImpDxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarImpDxActionPerformed
        if(lblNewMod.getText().equals("N")){
            if(tbImpDx.getRowCount()<2){
            adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
            dlgListaImpDx.setVisible(true);
            dlgListaImpDx.setLocationRelativeTo(null);//en el centro
            dlgListaImpDx.setResizable(false);
            dlgListaImpDx.getContentPane().setBackground(Color.WHITE);
            lblTipoDiag.setText("Diag");
        } else
            ErrorMas2.setVisible(true);   
        }else if(lblNewMod.getText().equals("M")){
      
            if(tbImpDx.getRowCount()<2){
                adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
                dlgListaImpDx.setVisible(true);
                dlgListaImpDx.setLocationRelativeTo(null);//en el centro
                dlgListaImpDx.setResizable(false);
                dlgListaImpDx.getContentPane().setBackground(Color.WHITE);
                lblTipoDiag.setText("p");
            } else {
                ErrorMas2.setVisible(true);
//                tbTopico.getSelectionModel().setSelectionInterval(0, 0);
//                tbTopico.requestFocus();
            }
        
        }
        
    }//GEN-LAST:event_btnBuscarImpDxActionPerformed

    private void btnbuscarExamAuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarExamAuxActionPerformed
        if(lblNewMod.getText().equals("N")){
            adEmerTo.cargarFormatLaboratorio(txtBusquedaTo.getText(), tbDatosLab,lblIdFP.getText());
            dlgListaExamnAux.setVisible(true);
            dlgListaExamnAux.setLocationRelativeTo(null);//en el centro
            dlgListaExamnAux.setResizable(false);
            dlgListaExamnAux.getContentPane().setBackground(Color.WHITE);
            lblTipo.setText("nuevo");  
        }else if(lblNewMod.getText().equals("M")){

            int fila = tbTopico.getSelectedRow();
            adEmerTo.cargarFormatLaboratorio(txtBusquedaTo.getText(), tbDatosLab,lblIdFP.getText());
            dlgListaExamnAux.setVisible(true);
            dlgListaExamnAux.setLocationRelativeTo(null);//en el centro
            dlgListaExamnAux.setResizable(false);
            dlgListaExamnAux.getContentPane().setBackground(Color.WHITE);
            lblTipo.setText("modex");
         
        }
  
        
    }//GEN-LAST:event_btnbuscarExamAuxActionPerformed

    private void txtaMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaMotivoKeyPressed

    }//GEN-LAST:event_txtaMotivoKeyPressed

    private void txtDeposicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeposicionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeposicionesActionPerformed

    private void btnQuitarExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarExamActionPerformed
         if(lblNewMod.getText().equals("N")){
             quitarExamenAux();
         }else if(lblNewMod.getText().equals("M")){
           
                eliminarDetalleTopicoExamen();
       
         }
        
    }//GEN-LAST:event_btnQuitarExamActionPerformed

    private void btnQuitarDiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDiagActionPerformed
        if(lblNewMod.getText().equals("N")){
          quitarDiagImpr();  
        }else if(lblNewMod.getText().equals("M")){
          eliminarDetalleTopicoDiagPresun();  
        }
        
    }//GEN-LAST:event_btnQuitarDiagActionPerformed

    private void tbDiagnosticosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosticosKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbDiagnosticos.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarDiagnostico.requestFocus();
            tbDiagnosticos.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER){
            if(lblTipoDiag.getText().equals("Diag"))
                enviarDatosDiagnostico();
            else
                if(lblTipoDiag.getText().equals("DiagF"))
                enviarDatosDiagnosticoFinal();
                else 
                    if(lblTipoDiag.getText().equals("p")){
                        enviarDatosDiagnosticoP();
                        formatotbImpdx(tbImpDx);
            
                    }
                    else 
                        if(lblTipoDiag.getText().equals("f")){
                            enviarDatosDiagnosticoF();
                            formatotbImpdx(tbDxAlta);
                        }
        }
    }//GEN-LAST:event_tbDiagnosticosKeyPressed

    private void tbDiagnosticosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticosMouseClicked
        if(evt.getClickCount()==2){
            if(lblTipoDiag.getText().equals("Diag"))
                enviarDatosDiagnostico();
            else
                if(lblTipoDiag.getText().equals("DiagF"))
                enviarDatosDiagnosticoFinal();
                else 
                    if(lblTipoDiag.getText().equals("p")){
                        enviarDatosDiagnosticoP();
                        formatotbImpdx(tbImpDx);
                    }
                    else 
                        if(lblTipoDiag.getText().equals("f")){
                            enviarDatosDiagnosticoF();
                            formatotbImpdx(tbDxAlta);
                        }
        }
    }//GEN-LAST:event_tbDiagnosticosMouseClicked

    private void btnAgregarDiagfinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiagfinalActionPerformed
        if(lblNewMod.getText().equals("N")){
           if(tbDxAlta.getRowCount()<2){
            adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
            dlgListaImpDx.setVisible(true);
            dlgListaImpDx.setLocationRelativeTo(null);//en el centro
            dlgListaImpDx.setResizable(false);
            dlgListaImpDx.getContentPane().setBackground(Color.WHITE);
            lblTipoDiag.setText("DiagF");
        } else
            ErrorMas2.setVisible(true);
        }else if(lblNewMod.getText().equals("M")){
            if(tbDxAlta.getRowCount()<2){
                adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
                dlgListaImpDx.setVisible(true);
                dlgListaImpDx.setLocationRelativeTo(null);//en el centro
                dlgListaImpDx.setResizable(false);
                dlgListaImpDx.getContentPane().setBackground(Color.WHITE);
                lblTipoDiag.setText("f");
            } else {
                ErrorMas2.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_btnAgregarDiagfinalActionPerformed

    private void btnQuitarDiagFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDiagFinalActionPerformed
        if(lblNewMod.getText().equals("N")){
            quitarDiagFinal();
        }else if(lblNewMod.getText().equals("M")){
            eliminarDetalleTopicoDiagFinal();
        }
        
    }//GEN-LAST:event_btnQuitarDiagFinalActionPerformed

    private void tbExamAuxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbExamAuxKeyPressed
//        quitarExamenAux();
    }//GEN-LAST:event_tbExamAuxKeyPressed

    private void tbImpDxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbImpDxKeyPressed
//        quitarDiagImpr();
    }//GEN-LAST:event_tbImpDxKeyPressed

    private void tbDxAltaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDxAltaKeyPressed
//        if(tbDxAlta.getSelectedRowCount()!=0){
//            m= (DefaultTableModel) tbDxAlta.getModel(); 
//            m.removeRow(tbDxAlta.getSelectedRow()); 
//        } else 
//            JOptionPane.showMessageDialog(this, "Seleccione un registro");
    }//GEN-LAST:event_tbDxAltaKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_F3){
            btnAgregarDiagfinalActionPerformed(null);
        }
    }//GEN-LAST:event_formKeyPressed

    private void lbl2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lbl2CaretUpdate
        if(lbl2.getText().equals("")){
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
        }else{ 
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
            habilitaBotonesJTable(true);
        }
    }//GEN-LAST:event_lbl2CaretUpdate

    private void txtIDTriajeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDTriajeCaretUpdate
        if(txtIDTriaje.getText().equals("")){
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);

        }else{ 
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_txtIDTriajeCaretUpdate

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
            txtBuscarPaciente.requestFocus();
            tbPacientes.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosTbPaciente();
        }

    }//GEN-LAST:event_tbPacientesKeyPressed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void txtBusquedaToCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedaToCaretUpdate
         adEmerTo.cargarFormatLaboratorio(txtBusquedaTo.getText(), tbDatosLab,lblIdFP.getText());
    }//GEN-LAST:event_txtBusquedaToCaretUpdate

    private void txtBusquedaToMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBusquedaToMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaToMouseClicked

    private void txtBusquedaToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaToActionPerformed

    private void txtBusquedaToKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaToKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbDatosLab.getSelectionModel().setSelectionInterval(0, 0);
            tbDatosLab.requestFocus();
        }
    }//GEN-LAST:event_txtBusquedaToKeyPressed

    private void btnBuscarPaciente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente3ActionPerformed

    private void txtBuscarDiagnosticoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDiagnosticoCaretUpdate
        adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
    }//GEN-LAST:event_txtBuscarDiagnosticoCaretUpdate

    private void txtBuscarDiagnosticoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarDiagnosticoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDiagnosticoMouseClicked

    private void txtBuscarDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarDiagnosticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDiagnosticoActionPerformed

    private void txtBuscarDiagnosticoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDiagnosticoKeyPressed
          if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbDiagnosticos.getSelectionModel().setSelectionInterval(0, 0);
            tbDiagnosticos.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarDiagnosticoKeyPressed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate

   
            adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, determinarFecha(fechai),determinarFecha(fechaf));
 
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
      
            if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
                tbTopico.getSelectionModel().setSelectionInterval(0, 0);
                tbTopico.requestFocus();
            }
      
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ImageIcon continuar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/continuar.png"));
        ImageIcon detener=new ImageIcon(this.getClass().getResource("/imagenes/iconos/detener.png"));
        
  
            if(btnBuscar.getText().equals("Iniciar")){
                if(fechai.getDate()==null || fechai.getDate()==null){
                    JOptionPane.showMessageDialog(this, "Ingrese un rango de fechas");
                }
                adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, determinarFecha(fechai),determinarFecha(fechaf));
                btnBuscar.setText("Detener");
                txtBuscar.setVisible(true);
                txtBuscar.requestFocus();
                btnBuscar.setIcon(detener);
                txtBuscar.setEnabled(true);
            } else {
                adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, "","");
                btnBuscar.setText("Iniciar");
                txtBuscar.setVisible(false);
                btnBuscar.setIcon(continuar);
                txtBuscar.setEnabled(false);
            }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tbTopicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTopicoMouseReleased
        int fila = tbTopico.getSelectedRow();
        if(fila == -1)
        SelReg.setVisible(true);
        else{
            if(evt.isPopupTrigger()){
                jpmTopico.show(evt.getComponent(),evt.getX(),evt.getY());
            }
        }
    }//GEN-LAST:event_tbTopicoMouseReleased

    private void tbTopicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTopicoKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbTopico.getSelectedRow()==0){
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_tbTopicoKeyPressed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        imprimirTopico();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        imprimirFormatoCompleto();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void tbTopicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTopicoMouseClicked
        int fila = tbTopico.getSelectedRow();
        if(evt.getClickCount()==1){
            AdmisionEmergenciaCabecera CAB = new AdmisionEmergenciaCabecera();
            
            adEmerTo.admisionEmergenciaTopicoDetalles(tbExamAux, String.valueOf(tbTopico.getValueAt(fila, 0)), 1);
            adEmerTo.admisionEmergenciaTopicoDetalles(tbImpDx, String.valueOf(tbTopico.getValueAt(fila, 0)), 2);
            adEmerTo.admisionEmergenciaTopicoDetalles(tbDxAlta, String.valueOf(tbTopico.getValueAt(fila, 0)), 3);
            txtNHCTo.setText(String.valueOf(tbTopico.getValueAt(fila, 2)));
            lbl2.setText(String.valueOf(tbTopico.getValueAt(fila, 0)));
            CAB.mostrarHCTopicoT(String.valueOf(tbTopico.getValueAt(fila, 27)));
            lblFormaP.setVisible(true);
            lblFP.setVisible(true);
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
        if(evt.getClickCount()==2){
             enviarDatosdeTopico();
        }
           
    }//GEN-LAST:event_tbTopicoMouseClicked

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==3 || tge==1 ||tge==9 ||tge==7||tge==9){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            tga=5;
            // MODIFICAR
                    if(modificarDatosTopico()==true){
                        cargareliminar.setBackground(new Color(0,153,102)); 
                        Mensaje.setText("Datos Actualizados de forma correcta");
                        eli.setText("OK");
                        eli.setVisible(true);
                        noeli.setVisible(false);
                         tge=9;
                        limpiarDatosTopico();
                        jPanel9.setVisible(false);
                        adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, "","");
                        tbPaneles.setSelectedIndex(0);
       
                    }else
                        cargareliminar.setBackground(new Color(0,153,102)); 
                        Mensaje.setText("Datos Actualizados de forma correcta");
                        eli.setText("OK");
                        eli.setVisible(true);
                        noeli.setVisible(false);
                        tge=9;
                                

        }
        if (tge==6){
            try { 
                AdmisionEmergenciaTopico adEmerTop = new AdmisionEmergenciaTopico();
                adEmerTop.setTopico_id(lbl2.getText());
                if(adEmerTop.mantenimientoAdmisionEmergenciaTopico("E")==true){
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro eliminado de forma correcta");
                    eli.setText("OK");
                    eli.setVisible(true);
                    noeli.setVisible(false);
                    tge=7;
                    jPanel9.setVisible(false);
                    adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, "","");
                    tbPaneles.setSelectedIndex(0);
                    txtNHCTo.setText("");
                    btnEliminar.setEnabled(false);
                    lbl2.setText("");
                }

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

    private void btnAlertConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta1ActionPerformed
        ErrorMas2.dispose();
    }//GEN-LAST:event_btnAlertConsulta1ActionPerformed

    private void btnAlertConsulta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta2ActionPerformed
        ErrorExistente.dispose();
    }//GEN-LAST:event_btnAlertConsulta2ActionPerformed

    private void btnAlertConsulta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta3ActionPerformed
        SelReg.dispose();
    }//GEN-LAST:event_btnAlertConsulta3ActionPerformed

    private void btnAlertConsulta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta4ActionPerformed
        AdmisionEmergenciaTopicoDetalleDiagPresun adEmertop = new AdmisionEmergenciaTopicoDetalleDiagPresun();
            int fila = tbImpDx.getSelectedRow();
            int filaDatosT = tbTopico.getSelectedRow();
            adEmertop.setId_topico(String.valueOf(tbTopico.getValueAt(filaDatosT, 0)));
            adEmertop.setId_cie10(Integer.parseInt(String.valueOf(tbImpDx.getValueAt(fila, 0))));

                if(adEmertop.modificarDetalleDiagPresun()==true){
                    m= (DefaultTableModel) tbImpDx.getModel(); //TableProducto es el nombre de mi tabla ;) 
                    m.removeRow(tbImpDx.getSelectedRow()); 
                    System.out.println("DX ELIMINADO");
                
                }
        ElimDXP.dispose();
    }//GEN-LAST:event_btnAlertConsulta4ActionPerformed

    private void btnEliminarNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNoActionPerformed
        ElimDXP.dispose();
    }//GEN-LAST:event_btnEliminarNoActionPerformed

    private void btnAlertConsulta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta5ActionPerformed
        Caja_DetallePreventa cp = new Caja_DetallePreventa();
            int fila = tbExamAux.getSelectedRow();
            int filaDatosT = tbTopico.getSelectedRow();
            cp.setId_topico(String.valueOf(tbTopico.getValueAt(filaDatosT, 0)));
            cp.setCod_precio(String.valueOf(tbExamAux.getValueAt(fila, 2)));

                if(cp.cajaDetallePreventaModificar()==true){
                    m= (DefaultTableModel) tbExamAux.getModel(); //TableProducto es el nombre de mi tabla ;) 
                    m.removeRow(tbExamAux.getSelectedRow()); 
                    System.out.println("EXAMEN ELIMINADO");
                }
        ElimEx.dispose();
    }//GEN-LAST:event_btnAlertConsulta5ActionPerformed

    private void btnEliminarNo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNo1ActionPerformed
         ElimEx.dispose();
    }//GEN-LAST:event_btnEliminarNo1ActionPerformed

    private void btnAlertConsulta6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta6ActionPerformed
            AdmisionEmergenciaTopicoDetalleDiagFinal adEmertop = new AdmisionEmergenciaTopicoDetalleDiagFinal();
            int fila = tbDxAlta.getSelectedRow();
            int filaDatosT = tbTopico.getSelectedRow();
            adEmertop.setId_topico(String.valueOf(tbTopico.getValueAt(filaDatosT, 0)));
            adEmertop.setId_cie10(Integer.parseInt(String.valueOf(tbDxAlta.getValueAt(fila, 0))));
            
   
                if(adEmertop.modificarDetalleDiagFinal()==true){
                    m= (DefaultTableModel) tbDxAlta.getModel(); //TableProducto es el nombre de mi tabla ;) 
                    m.removeRow(tbDxAlta.getSelectedRow()); 
                    System.out.println("DX ALTA ELIMINADO");
                }
            ElimDXF.dispose();    
    }//GEN-LAST:event_btnAlertConsulta6ActionPerformed

    private void btnEliminarNo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNo2ActionPerformed
            ElimDXF.dispose(); 
    }//GEN-LAST:event_btnEliminarNo2ActionPerformed

    private void btnAlertConsulta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlertConsulta7ActionPerformed
    
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
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaTopico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaTopico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaTopico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaTopico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmFormatoEmergenciaTopico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ABONOS;
    private javax.swing.JDialog BHC;
    private javax.swing.JDialog ElimDXF;
    private javax.swing.JDialog ElimDXP;
    private javax.swing.JDialog ElimEx;
    private javax.swing.JDialog ErrorExistente;
    private javax.swing.JDialog ErrorExistenteDX;
    private javax.swing.JDialog ErrorMas2;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog SelReg;
    private javax.swing.JButton btnAgregarDiagfinal;
    private javax.swing.JButton btnAlertConsulta1;
    private javax.swing.JButton btnAlertConsulta2;
    private javax.swing.JButton btnAlertConsulta3;
    private javax.swing.JButton btnAlertConsulta4;
    private javax.swing.JButton btnAlertConsulta5;
    private javax.swing.JButton btnAlertConsulta6;
    private javax.swing.JButton btnAlertConsulta7;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarImpDx;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBuscarPaciente3;
    private javax.swing.JButton btnBuscarPaciente4;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarNo;
    private javax.swing.JButton btnEliminarNo1;
    private javax.swing.JButton btnEliminarNo2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListado;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitarDiag;
    private javax.swing.JButton btnQuitarDiagFinal;
    private javax.swing.JButton btnQuitarExam;
    private javax.swing.JButton btnbuscarExamAux;
    private javax.swing.JLabel bus;
    private javax.swing.JLabel bus3;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxEvaluacionPaciente;
    public static javax.swing.JComboBox cbxPrioridad;
    private javax.swing.JComboBox cbxUbicacionEgreso;
    private javax.swing.JDialog dlgListaExamnAux;
    private javax.swing.JDialog dlgListaImpDx;
    private javax.swing.JButton eli;
    private com.toedter.calendar.JDateChooser fechaf;
    private com.toedter.calendar.JDateChooser fechai;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel10;
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
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JPopupMenu jpmCabecera;
    private javax.swing.JPopupMenu jpmTopico;
    private javax.swing.JLabel lbl1;
    public static javax.swing.JTextField lbl2;
    private javax.swing.JLabel lblAd;
    private javax.swing.JLabel lblAd1;
    public static javax.swing.JLabel lblCabpT;
    public static javax.swing.JLabel lblFP;
    private javax.swing.JLabel lblFormaP;
    private javax.swing.JLabel lblFormaP1;
    public static javax.swing.JLabel lblIDHCTo;
    public static javax.swing.JLabel lblIDTriajepTop;
    private javax.swing.JLabel lblID_PREVENTA;
    public static javax.swing.JLabel lblIdFP;
    public static javax.swing.JLabel lblIdPreventa;
    private javax.swing.JLabel lblNewMod;
    public static javax.swing.JLabel lblPacienteTo;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipo1;
    public static javax.swing.JLabel lblTipoDiag;
    public static javax.swing.JLabel lblTriaje;
    private javax.swing.JLabel lbl_ID_TRIAJE;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelBuscarHC;
    private javax.swing.JPanel panelSinHC;
    private javax.swing.JPanel paneltablaHC;
    private javax.swing.JPanel pnlTopico;
    private javax.swing.JScrollPane pnlTopicoP;
    private javax.swing.JPanel pnlTopicoPrioridad;
    private javax.swing.JTable tbDatosLab;
    private javax.swing.JTable tbDiagnosticos;
    private javax.swing.JTable tbDxAlta;
    private javax.swing.JTable tbExamAux;
    private javax.swing.JTable tbImpDx;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTabbedPane tbPaneles;
    public static javax.swing.JTable tbTopico;
    private javax.swing.JTable tbpreventas;
    private javax.swing.JTable tbpreventasFR;
    public static javax.swing.JTextField txtApetito;
    public static javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarDiagnostico;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtBusquedaTo;
    public static javax.swing.JTextField txtConciencia;
    public static javax.swing.JTextField txtDeposiciones;
    public static javax.swing.JTextField txtHidratacion;
    public static javax.swing.JTextField txtIDTriaje;
    public static javax.swing.JTextField txtNHCTo;
    public static javax.swing.JTextField txtNutricion;
    public static javax.swing.JTextField txtOrinas;
    public static javax.swing.JTextField txtSed;
    public static javax.swing.JTextField txtSueno;
    public static javax.swing.JTextArea txtaAnotMed;
    public static javax.swing.JTextArea txtaAnotacionesEnf;
    public static javax.swing.JTextArea txtaExamenFisico;
    public static javax.swing.JTextArea txtaMotivo;
    public static javax.swing.JTextArea txtaPlanTrabajo;
    public static javax.swing.JTextArea txtaRelato;
    // End of variables declaration//GEN-END:variables
}
