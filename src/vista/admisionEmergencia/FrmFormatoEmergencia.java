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
public class FrmFormatoEmergencia extends javax.swing.JFrame implements Runnable{
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
    /**
     * Creates new form FrmemergenciaCabecera
     */
    public FrmFormatoEmergencia() {
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
//        buscar_HC(1,"A","");
        pnlB.setEnabled(false);
        limpiar();
        restringirCampos(8, txtNHC);
        restringirCampos(8, txtNHCTri);
        restringirCampos(8, txtNHCTo);
        pnlDatosCabecera.setVisible(false);
        pnlTriaje.setVisible(false);
        pnlTopicoP.setVisible(false);
        cbxFormaLlegada.setModel(formaDeLlegada());
        mostrarDatosModif(false);
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
        tbPaneles.setSelectedIndex(0);
        habilitarPestanas(1,true);
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
        btnQuitarDiag.setEnabled(false);
        btnQuitarExam.setEnabled(false);
        btnQuitarDiagFinal.setEnabled(false);
        formatotbImpdx(tbImpDx);
        formatotbExamenAux(tbExamAux);
        formatotbExamenAux(tbExamenes);
        formatotbDxAlta();
        formatotbImpdx(tbDiagFinal);
        formatotbImpdx(tbDiagPresun);
        //tbPaciente.setDefaultRenderer(Object.class,new tablas.AdmisionEmergenciaTopico());
        //limitadores
        LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(100);
        pnlEObservación.setDocument(limitObservacion);
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
        lblIDHCTr.setVisible(false);
        lbl1.setVisible(false);
        lbl2.setVisible(false);
        lblFormaP.setVisible(false);
        lblFP.setVisible(false);
//        lblIDHCTo.setVisible(false);
        txtIDTriaje.setVisible(false);
        lblIdFP.setVisible(false);
        lblIdTr.setVisible(false);
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
    
    public void habilitarPestanas(int tipo,boolean opcion){
        switch(tipo){
            case 1:
                tbPaneles.setEnabledAt(1, opcion);
                tbPaneles.setEnabledAt(2, opcion);
                tbPaneles.setSelectedIndex(0); break;
            case 2:
                tbPaneles.setEnabledAt(0, opcion);
                tbPaneles.setEnabledAt(2, opcion);
                tbPaneles.setSelectedIndex(1); break;
            case 3:
                tbPaneles.setEnabledAt(1, opcion);
                tbPaneles.setEnabledAt(0, opcion);
                tbPaneles.setSelectedIndex(2); break;
            default:
                tbPaneles.setEnabledAt(0, opcion);
                tbPaneles.setEnabledAt(1, opcion);
                tbPaneles.setEnabledAt(2, opcion);
                tbPaneles.setSelectedIndex(0); break;
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
    
     public void BuscarHC(){
        String consulta="";
        try {
            tbPacientes.setModel(new DefaultTableModel());
             String titulos[]={"Nº H.C.","DNI","Paciente","Direccion","Sexo","Fecha","Edad",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            AdmisionEmergenciaCabecera obj=new AdmisionEmergenciaCabecera();
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
        cbxFormaLlegada.setSelectedIndex(0);
    }
    
    public void limpiarDatosTriaje(){
        lblPaciente.setText("");
        txtFC.setText("");
        txtFR.setText("");
        txtPA.setText("");
        txtT.setText("");
        txtPeso.setText("");
        lblFechaIngTriaje.setText("");
        lblHoraIngTriaje.setText("");
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
    
    public void mostrarHCTriaje(String nhc){
        String consulta="";
        try {
            consulta="EXEC SP_ADMISION_HISTORIACLINICA_BUSXNHC ?";
            PreparedStatement cmd = movHC.getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPaciente.setText(r.getString(2) + " " + r.getString(3) + " " + 
                                 r.getString(4) + " " + r.getString(5) + " " +
                                 r.getString(6)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error_mostrar_MovHC: " + e.getMessage());
        }
    }
    
    public void mostrarHCTopico(String nhc){
        String consulta="";
        try {
            consulta="EXEC SP_ADMISION_HISTORIACLINICA_BUSXNHC ?";
            PreparedStatement cmd = movHC.getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPaciente.setText(r.getString(2) + " " + r.getString(3) + " " + 
                                 r.getString(4) + " " + r.getString(5) + " " +
                                 r.getString(6)); 
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
        BHC.dispose();
        if(lblPestana.getText().equals("C")){
            FrmFormatoEmergencia.txtNHC.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            FrmFormatoEmergencia.txtTraidopor.requestFocus();
        }else 
        if(lblPestana.getText().equals("TR")){
            FrmFormatoEmergencia.txtNHCTri.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            FrmFormatoEmergencia.lblPaciente.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));
        } else
        if(lblPestana.getText().equals("TO")){
            FrmFormatoEmergencia.txtNHCTo.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            FrmFormatoEmergencia.lblPacienteTo.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));
            FrmFormatoEmergencia.txtNHCTo.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
        }
    }
    
    public void enviarDatosTbFormatEmergencia(){
        int fila = tbFormatosEmer.getSelectedRow();
        dlgModemergencia.dispose();
        pnlDatosCabecera.setVisible(true);
        FrmFormatoEmergencia.txtNroRegistro.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 0)));
        FrmFormatoEmergencia.txtTraidopor.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 1)));
        FrmFormatoEmergencia.txtParentesco.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 2)));
        FrmFormatoEmergencia.cbxFormaLlegada.setSelectedItem(String.valueOf(tbFormatosEmer.getValueAt(fila, 4)));
        FrmFormatoEmergencia.pnlEObservación.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 5)));
        FrmFormatoEmergencia.lblFechaIng.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 6)));
        FrmFormatoEmergencia.lblHoraIng.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 7)));
        mostrarDatosModif(true);
        FrmFormatoEmergencia.txtTraidopor.requestFocus();
        habilitarPestanas(1, false);
        btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
    }
    
    public void enviarDatosTbFormatEmergenciaTriaje(){
        int fila = tbFormatosEmer.getSelectedRow();
        dlgModemergencia.dispose();
        pnlTriaje.setVisible(true);
        FrmFormatoEmergencia.lblTraidoporTriaje.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 1)));
        FrmFormatoEmergencia.lblParentesco.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 2)));
        FrmFormatoEmergencia.lblFechaIngTriaje.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 6)));
        FrmFormatoEmergencia.lblHoraIngTriaje.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 7)));
        FrmFormatoEmergencia.lblIDPreventa.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 0)));
        btnGuardar.setEnabled(true);
        habilitarPestanas(2, false);
//        txtIDTriaje.setText(adEmerTr.idAdmisionEmergenciaTriaje());
//        if(txtIDTriaje.getText().equalsIgnoreCase("")){
//            txtIDTriaje.setText("TR000000000000000001");
//        }
    }   
    
    public void enviarNomenclatura(){
        int fila = tb_Grupo4.getSelectedRow();
        dlgBuscarCPT.dispose();
        FrmFormatoEmergencia.txtCPT.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 2)));
    }   
    
    public void enviarDatosPnlTriaje(){
        int fila = tbModifTriaje.getSelectedRow();
        dlgModTriaje.dispose();
        pnlTriaje.setVisible(true);
        FrmFormatoEmergencia.txtIDTriaje.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 2)));
        FrmFormatoEmergencia.txtFC.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 7)));
        FrmFormatoEmergencia.txtFR.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 8)));
        FrmFormatoEmergencia.txtPA.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 9)));
        FrmFormatoEmergencia.txtPeso.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 10)));
        FrmFormatoEmergencia.txtT.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 11)));
        FrmFormatoEmergencia.txtTalla.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 12)));
        FrmFormatoEmergencia.lblTraidoporTriaje.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 5)));
        FrmFormatoEmergencia.lblParentesco.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 6)));
        FrmFormatoEmergencia.lblFechaIngTriaje.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 3)));
        FrmFormatoEmergencia.lblHoraIngTriaje.setText(String.valueOf(tbModifTriaje.getValueAt(fila, 4)));
        //Mostrar ID
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(true);
        habilitarPestanas(2, false);
    }
    
    public void enviarDatosaPnlTopico(){
        int fila = tbMostrarTriajepT.getSelectedRow();
        dlgMostrarDatosTriajeT.dispose();
        pnlTopicoP.setVisible(true);
        FrmFormatoEmergencia.lblCabpT.setText(String.valueOf(tbMostrarTriajepT.getValueAt(fila, 4)));
        FrmFormatoEmergencia.lblIDTriajepTop.setText(String.valueOf(tbMostrarTriajepT.getValueAt(fila, 5)));
//        txtIDTopico.setText(adEmerTo.idAdmisionEmergenciaTopico());
//        if(txtIDTopico.getText().equalsIgnoreCase("")){
//            txtIDTopico.setText("TO000000000000000001");
//        }
        adEmerTo.formadePago(String.valueOf(tbMostrarTriajepT.getValueAt(fila, 5)));
        lblFormaP.setVisible(true);
        lblFP.setVisible(true);
        btnGuardar.setEnabled(true);
    }
    
    public void enviarDatosdeTopico(){
        int fila = tbDatosTopico.getSelectedRow();
        dlgMostrarDatosTopico.dispose();
        pnlTopicoP.setVisible(true);
        FrmFormatoEmergencia.lbl2.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 0)));
        FrmFormatoEmergencia.txtApetito.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 6)));
        FrmFormatoEmergencia.txtSed.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 7)));
        FrmFormatoEmergencia.txtDeposiciones.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 8)));
        FrmFormatoEmergencia.txtSueno.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 9)));
        FrmFormatoEmergencia.txtOrinas.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 10)));
        FrmFormatoEmergencia.txtaMotivo.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 11)));
        FrmFormatoEmergencia.txtaRelato.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 12)));
        FrmFormatoEmergencia.txtConciencia.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 13)));
        FrmFormatoEmergencia.txtHidratacion.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 14)));
        FrmFormatoEmergencia.txtNutricion.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 15)));
        FrmFormatoEmergencia.txtaExamenFisico.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 16)));
        FrmFormatoEmergencia.txtaPlanTrabajo.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 17)));
        FrmFormatoEmergencia.txtaAnotMed.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 18)));
        FrmFormatoEmergencia.txtaAnotacionesEnf.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 19)));
        FrmFormatoEmergencia.cbxPrioridad.setSelectedItem(String.valueOf(tbDatosTopico.getValueAt(fila, 22)));
        /*FrmFormatoEmergencia.lblIDTriajepTop.setText(String.valueOf(tbMostrarTriajepT.getValueAt(fila, 5)));
        txtIDTopico.setText(adEmerTo.idAdmisionEmergenciaTopico());*/
        btnGuardar.setEnabled(true);
        //inhabilitar botones
        habilitaBotonesJTable(false);
        tbExamAux.setEnabled(false);
    }
    
    public void habilitaBotonesJTable(boolean opcion){
        btnbuscarExamAux.setEnabled(opcion);
        btnBuscarImpDx.setEnabled(opcion);
        btnAgregarDiagfinal.setEnabled(opcion);
    }
    
    public void habilitarDatos(){
        txtNHC.requestFocus();
        btnFiltrar.setEnabled(true);
        txtNHC.setEnabled(true);
        pnlDatosCabecera.setVisible(false);
        txtNHC.setText("");
        limpiar();
    }
    
    public void habilitarDatosTriaje(){
        txtNHCTri.requestFocus();
        txtNHCTri.setEnabled(true);
        btnFiltrarTri.setEnabled(true);
        pnlDatosCabecera.setVisible(false);
        txtNHCTri.setText("");
    }
    
    public void habilitarDatosTopico(){
        txtNHCTo.requestFocus();
        txtNHCTo.setEnabled(true);
        btnFiltrarTo.setEnabled(true);
        txtNHCTo.setText("");
    }
    
    public void formatotbExamenAux(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(200);//nombre
        tabla.getColumnModel().getColumn(1).setPreferredWidth(140);//CODIGO
        tabla.getColumnModel().getColumn(2).setPreferredWidth(140);//CODIGO
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
            JOptionPane.showMessageDialog(dlgListaExamnAux, "Este examen ya se encuentra registrado");
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
        m = (DefaultTableModel) tbExamenes.getModel();
        int fila = tbDatosLab.getSelectedRow();
        if(tbExamenes.getColumnCount()==0){
            dlgListaExamnAux.dispose();
            //PASAR DATOS A TABLA
            String examenes[] = {
            String.valueOf(tbDatosLab.getValueAt(fila, 0)),
            String.valueOf(tbDatosLab.getValueAt(fila, 2)),
            String.valueOf(tbDatosLab.getValueAt(fila, 3))};
            m.addRow(examenes);
            //formatotbExamenAux();
            btnQuitarExam.setEnabled(true);
            int filas = tbDatosTopico.getSelectedRow();
            for (int i = 0; i < tbExamenes.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    Caja_DetallePreventa cp = new Caja_DetallePreventa();
                    cp.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                    cp.setCod_precio(tbExamenes.getValueAt(i, 2).toString());
                    cp.setCod_usu(adEmer2.codUsuario(lblUsuario.getText()));
                    cp.setId_Preventa(Integer.parseInt(String.valueOf(tbDatosTopico.getValueAt(filas, 23))));
                    cp.cajaDetallePreventaInsertar();
            }
        } else 
        if(repitedlgExamen()==true)
            JOptionPane.showMessageDialog(dlgListaExamnAux, "Este examen ya se encuentra registrado");
        else{
            dlgListaExamnAux.dispose();
            //PASAR DATOS A TABLA
            String examenes[] = {
            String.valueOf(tbDatosLab.getValueAt(fila, 0)),
            String.valueOf(tbDatosLab.getValueAt(fila, 2)),
            String.valueOf(tbDatosLab.getValueAt(fila, 3))};
            m.addRow(examenes);
            btnQuitarExam.setEnabled(true);
            int filas = tbDatosTopico.getSelectedRow();
            boolean eliminar = false;
            boolean insertar = false;
            for (int i = 0; i < tbExamenes.getRowCount(); i++){      
                AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                Caja_DetallePreventa cp = new Caja_DetallePreventa();
                cp.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                cp.cajaDetallePreventaModificar();//{
                eliminar = true;
            }
            if(eliminar == true){
                for (int i = 0; i < tbExamenes.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    Caja_DetallePreventa cp = new Caja_DetallePreventa();
                    cp.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                    cp.setCod_precio(tbExamenes.getValueAt(i, 2).toString());
                    cp.setCod_usu(adEmer2.codUsuario(lblUsuario.getText()));
                    cp.setId_Preventa(Integer.parseInt(String.valueOf(tbDatosTopico.getValueAt(filas, 23))));
                    cp.cajaDetallePreventaInsertar();
                    insertar = true;
                }
            }
            if(insertar == true && eliminar == true){
                JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Examen guardo con éxito");
            }
        }
    }
    
    public void formatotbImpdx(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);//clasificacion
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
            JOptionPane.showMessageDialog(dlgListaImpDx, "Diagnóstico ya registrado");
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
        m = (DefaultTableModel) tbDiagPresun.getModel();
        int fila = tbDiagnosticos.getSelectedRow();
        if(tbDiagPresun.getColumnCount()==0){
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbDiagPresun);
            btnQuitarDiag.setEnabled(true);
            int filas = tbDatosTopico.getSelectedRow();
            for (int i = 0; i < tbDiagPresun.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    AdmisionEmergenciaTopicoDetalleDiagPresun adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagPresun();
                    adEmerTop2.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                    adEmerTop2.setId_cie10(Integer.parseInt(String.valueOf(tbDiagPresun.getValueAt(i, 0))));
                    adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblUsuario.getText()));
                    adEmerTop2.modificarDetalleDiagPresun();//{
            }
        } else
        if(repitedlgExamenDiagImp()==true)
            JOptionPane.showMessageDialog(dlgListaImpDx, "Diagnóstico ya registrado");
        else {
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbDiagPresun);
            btnQuitarDiag.setEnabled(true);
            boolean eliminar = false;
            boolean insertar = false;
            int filas = tbDatosTopico.getSelectedRow();
            for (int i = 0; i < tbDiagPresun.getRowCount(); i++){      
                AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                AdmisionEmergenciaTopicoDetalleDiagPresun adEmerTop1=new AdmisionEmergenciaTopicoDetalleDiagPresun();
                adEmerTop1.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                adEmerTop1.setId_cie10(0);
                adEmerTop1.modificarDetalleDiagPresun();//{
                eliminar = true;
            }
            if(eliminar == true){
                for (int i = 0; i < tbDiagPresun.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    AdmisionEmergenciaTopicoDetalleDiagPresun adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagPresun();
                    adEmerTop2.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                    adEmerTop2.setId_cie10(Integer.parseInt(String.valueOf(tbDiagPresun.getValueAt(i, 0))));
                    adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblUsuario.getText()));
                    adEmerTop2.insertarDetalleDiagPresuntivo();
                    insertar = true;
            }
            }
            if(insertar == true && eliminar == true){
                JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Diagnóstico guardo con éxito");
            }
        }
    }
    
    public void formatotbDxAlta(){
        tbDxAlta.getColumnModel().getColumn(0).setPreferredWidth(50);//CODIGO
        tbDxAlta.getColumnModel().getColumn(1).setPreferredWidth(50);//clasificacion
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
            JOptionPane.showMessageDialog(dlgListaImpDx, "Diagnóstico ya registrado");
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
        m = (DefaultTableModel) tbDiagFinal.getModel();
        int fila = tbDiagnosticos.getSelectedRow();
        if(tbDiagFinal.getColumnCount()==0){
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbDiagFinal);
            btnQuitarDiag.setEnabled(true);
            int filas = tbDatosTopico.getSelectedRow();
                for (int i = 0; i < tbDiagFinal.getRowCount(); i++){      
                        AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                        AdmisionEmergenciaTopicoDetalleDiagFinal adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagFinal();
                        adEmerTop2.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                        adEmerTop2.setId_cie10(Integer.parseInt(String.valueOf(tbDiagFinal.getValueAt(i, 0))));
                        adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblUsuario.getText()));
                        adEmerTop2.modificarDetalleDiagFinal();//{
                }
        } else
        if(repitedlgExamenDiagAlta()==true)
            JOptionPane.showMessageDialog(dlgListaImpDx, "Diagnóstico ya registrado");
        else {
            dlgListaImpDx.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tbDiagnosticos.getValueAt(fila, 0)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 1)),
            String.valueOf(tbDiagnosticos.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbImpdx(tbDiagFinal);
            btnQuitarDiag.setEnabled(true);
            boolean eliminar = false;
            boolean insertar = false;
            int filas = tbDatosTopico.getSelectedRow();
                for (int i = 0; i < tbDiagFinal.getRowCount(); i++){      
                    AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                    AdmisionEmergenciaTopicoDetalleDiagFinal adEmerTop1=new AdmisionEmergenciaTopicoDetalleDiagFinal();
                    adEmerTop1.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                    adEmerTop1.setId_cie10(0);
                    adEmerTop1.modificarDetalleDiagFinal();//{
                    eliminar = true;
                }
                if(eliminar == true){
                    for (int i = 0; i < tbDiagFinal.getRowCount(); i++){      
                        AdmisionEmergenciaCabecera adEmer2 =  new AdmisionEmergenciaCabecera();
                        AdmisionEmergenciaTopicoDetalleDiagFinal adEmerTop2=new AdmisionEmergenciaTopicoDetalleDiagFinal();
                        adEmerTop2.setId_topico(String.valueOf(tbDatosTopico.getValueAt(filas, 0)));
                        adEmerTop2.setId_cie10(Integer.parseInt(String.valueOf(tbDiagFinal.getValueAt(i, 0))));
                        adEmerTop2.setUsu_cod(adEmer2.codUsuario(lblUsuario.getText()));
                        adEmerTop2.insertarDetalleDiagFinal();
                        insertar = true;
                }
                }
                if(insertar == true && eliminar == true){
                    JOptionPane.showMessageDialog(dlgMostrarDatosTopico, "Diagnóstico guardo con éxito");
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
    public void quitarExamenAux(){
        if(tbExamAux.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tbExamAux.getModel(); //TableProducto es el nombre de mi tabla ;) 
            m.removeRow(tbExamAux.getSelectedRow()); 
        } else 
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
    }
    
    public void quitarDiagImpr(){
        if(tbImpDx.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tbImpDx.getModel(); //TableProducto es el nombre de mi tabla ;) 
            m.removeRow(tbImpDx.getSelectedRow()); 
        } else 
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
    }
    
    public void quitarDiagFinal(){
        if(tbDxAlta.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tbDxAlta.getModel(); 
            m.removeRow(tbDxAlta.getSelectedRow()); 
        } else 
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
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
                habilitarPestanas(3, true);
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(guardar == 0){
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
                }else{
                    JOptionPane.showMessageDialog(this, "No se realizó ningun movimiento");
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
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
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
                habilitarPestanas(3, true);
                int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(modificar == 0){
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
                }
                return retorna;
    }
    
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbFormatosEmer = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jdcBusquedaFecha = new com.toedter.calendar.JDateChooser();
        BTNB = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        lblPestanaMod = new javax.swing.JLabel();
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
        btnbuscar3 = new javax.swing.JButton();
        txtBuscar2 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tb_Grupo4 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel24 = new javax.swing.JPanel();
            jLabel81 = new javax.swing.JLabel();
            jLabel82 = new javax.swing.JLabel();
            jPanel25 = new javax.swing.JPanel();
            btnNuevo2 = new javax.swing.JButton();
            jLabel83 = new javax.swing.JLabel();
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
                            jPanel8 = new javax.swing.JPanel();
                            btnNuevo = new javax.swing.JButton();
                            btnModificar = new javax.swing.JButton();
                            btnGuardar = new javax.swing.JButton();
                            btnEliminar = new javax.swing.JButton();
                            btnBuscar = new javax.swing.JButton();
                            lblNewMod = new javax.swing.JLabel();
                            lblCabpT = new javax.swing.JLabel();
                            txtIDTriaje = new javax.swing.JTextField();
                            lblIDHCTo = new javax.swing.JLabel();
                            lblIdFP = new javax.swing.JLabel();
                            lblusu = new javax.swing.JLabel();
                            jLabel84 = new javax.swing.JLabel();
                            tbPaneles = new javax.swing.JTabbedPane();
                            jPanel1 = new javax.swing.JPanel();
                            jLabel4 = new javax.swing.JLabel();
                            jPanel6 = new javax.swing.JPanel();
                            txtNHC = new javax.swing.JTextField();
                            btnFiltrar = new javax.swing.JButton();
                            jSeparator1 = new javax.swing.JSeparator();
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
                            jLabel16 = new javax.swing.JLabel();
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
                            txtTraidopor = new javax.swing.JTextField();
                            jLabel32 = new javax.swing.JLabel();
                            txtParentesco = new javax.swing.JTextField();
                            jLabel41 = new javax.swing.JLabel();
                            cbxFormaLlegada = new javax.swing.JComboBox();
                            jScrollPane4 = new javax.swing.JScrollPane();
                            pnlEObservación = new javax.swing.JEditorPane();
                            jLabel3 = new javax.swing.JLabel();
                            lblFechaIM = new javax.swing.JLabel();
                            panelFormaPago = new javax.swing.JPanel();
                            txtCPT = new javax.swing.JTextField();
                            btnBuscarCPT = new javax.swing.JButton();
                            jLabel2 = new javax.swing.JLabel();
                            jLabel21 = new javax.swing.JLabel();
                            txtNroRegistro = new javax.swing.JLabel();
                            plTriaje = new javax.swing.JPanel();
                            lblIdTr = new javax.swing.JLabel();
                            jLabel22 = new javax.swing.JLabel();
                            jPanel9 = new javax.swing.JPanel();
                            txtNHCTri = new javax.swing.JTextField();
                            btnFiltrarTri = new javax.swing.JButton();
                            jSeparator3 = new javax.swing.JSeparator();
                            jLabel37 = new javax.swing.JLabel();
                            pnlTriaje = new javax.swing.JPanel();
                            jPanel11 = new javax.swing.JPanel();
                            jLabel25 = new javax.swing.JLabel();
                            jLabel26 = new javax.swing.JLabel();
                            txtPA = new javax.swing.JTextField();
                            txtFC = new javax.swing.JTextField();
                            jLabel27 = new javax.swing.JLabel();
                            jLabel28 = new javax.swing.JLabel();
                            jLabel29 = new javax.swing.JLabel();
                            jLabel30 = new javax.swing.JLabel();
                            txtFR = new javax.swing.JTextField();
                            txtPeso = new javax.swing.JTextField();
                            txtT = new javax.swing.JTextField();
                            jLabel1 = new javax.swing.JLabel();
                            txtTalla = new javax.swing.JTextField();
                            jSeparator4 = new javax.swing.JSeparator();
                            lblPaciente = new javax.swing.JLabel();
                            jLabel24 = new javax.swing.JLabel();
                            lblIDHCTr = new javax.swing.JLabel();
                            jLabel13 = new javax.swing.JLabel();
                            lblFechaIngTriaje = new javax.swing.JLabel();
                            jLabel23 = new javax.swing.JLabel();
                            lblHoraIngTriaje = new javax.swing.JLabel();
                            jLabel33 = new javax.swing.JLabel();
                            lblTraidoporTriaje = new javax.swing.JLabel();
                            jLabel34 = new javax.swing.JLabel();
                            lblParentesco = new javax.swing.JLabel();
                            lblIDPreventa = new javax.swing.JLabel();
                            pnlTopico = new javax.swing.JPanel();
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
                            jSeparator6 = new javax.swing.JSeparator();
                            jScrollPane6 = new javax.swing.JScrollPane();
                            txtaRelato = new javax.swing.JTextArea();
                            jLabel58 = new javax.swing.JLabel();
                            jLabel59 = new javax.swing.JLabel();
                            jLabel57 = new javax.swing.JLabel();
                            txtConciencia = new javax.swing.JTextField();
                            jLabel60 = new javax.swing.JLabel();
                            txtHidratacion = new javax.swing.JTextField();
                            jLabel61 = new javax.swing.JLabel();
                            txtNutricion = new javax.swing.JTextField();
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
                            jLabel64 = new javax.swing.JLabel();
                            lblPacienteTo = new javax.swing.JLabel();
                            cbxPrioridad = new javax.swing.JComboBox();
                            lblIDTriajepTop = new javax.swing.JLabel();
                            jPanel7 = new javax.swing.JPanel();
                            txtNHCTo = new javax.swing.JTextField();
                            btnFiltrarTo = new javax.swing.JButton();
                            jLabel50 = new javax.swing.JLabel();
                            lbl1 = new javax.swing.JLabel();
                            lbl2 = new javax.swing.JTextField();
                            jSeparator7 = new javax.swing.JSeparator();
                            jLabel85 = new javax.swing.JLabel();
                            lblFormaP = new javax.swing.JLabel();
                            lblFP = new javax.swing.JLabel();
                            jPanel29 = new javax.swing.JPanel();
                            jScrollPane25 = new javax.swing.JScrollPane();
                            tbCabecera = new javax.swing.JTable();
                            fechai = new com.toedter.calendar.JDateChooser();
                            fechaf = new com.toedter.calendar.JDateChooser();
                            txtBuscar = new javax.swing.JTextField();
                            btnBuscar1 = new javax.swing.JButton();
                            jPanel10 = new javax.swing.JPanel();
                            jLabel36 = new javax.swing.JLabel();
                            jLabel42 = new javax.swing.JLabel();
                            jLabel43 = new javax.swing.JLabel();
                            jLabel44 = new javax.swing.JLabel();

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

                            tbFormatosEmer = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false;
                                }
                            };
                            tbFormatosEmer.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {
                                    {null, null, null, null},
                                    {null, null, null, null},
                                    {null, null, null, null},
                                    {null, null, null, null}
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
                            tbFormatosEmer.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                            tbFormatosEmer.setSelectionBackground(new java.awt.Color(0, 118, 168));
                            tbFormatosEmer.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tbFormatosEmerMouseClicked(evt);
                                }
                            });
                            tbFormatosEmer.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tbFormatosEmerKeyPressed(evt);
                                }
                            });
                            jScrollPane2.setViewportView(tbFormatosEmer);

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

                            javax.swing.GroupLayout dlgModemergenciaLayout = new javax.swing.GroupLayout(dlgModemergencia.getContentPane());
                            dlgModemergencia.getContentPane().setLayout(dlgModemergenciaLayout);
                            dlgModemergenciaLayout.setHorizontalGroup(
                                dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(dlgModemergenciaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(dlgModemergenciaLayout.createSequentialGroup()
                                            .addGroup(dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(dlgModemergenciaLayout.createSequentialGroup()
                                                    .addComponent(jLabel39)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jdcBusquedaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(BTNB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lblPestanaMod))
                                            .addGap(0, 107, Short.MAX_VALUE))
                                        .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
                            );
                            dlgModemergenciaLayout.setVerticalGroup(
                                dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgModemergenciaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lblPestanaMod)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                    .addGroup(dlgModemergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jdcBusquedaFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BTNB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            dlgBuscarCPT.getContentPane().setLayout(null);

                            jPanel12.setBackground(new java.awt.Color(0, 153, 153));
                            jPanel12.setMinimumSize(new java.awt.Dimension(310, 441));

                            jLabel74.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                            jLabel74.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel74.setText("Nomenclaturas");

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

                            javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                            jPanel12.setLayout(jPanel12Layout);
                            jPanel12Layout.setHorizontalGroup(
                                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel74)
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(490, Short.MAX_VALUE))
                            );
                            jPanel12Layout.setVerticalGroup(
                                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel74)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(btnbuscar3)))
                                    .addGap(408, 408, 408))
                            );

                            dlgBuscarCPT.getContentPane().add(jPanel12);
                            jPanel12.setBounds(0, 0, 770, 104);

                            jPanel13.setBackground(new java.awt.Color(255, 255, 255));

                            javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                            jPanel13.setLayout(jPanel13Layout);
                            jPanel13Layout.setHorizontalGroup(
                                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 750, Short.MAX_VALUE)
                            );
                            jPanel13Layout.setVerticalGroup(
                                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 50, Short.MAX_VALUE)
                            );

                            dlgBuscarCPT.getContentPane().add(jPanel13);
                            jPanel13.setBounds(0, 312, 750, 50);

                            jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                            jPanel18.setBackground(new java.awt.Color(255, 255, 255));

                            jLabel76.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                            jLabel76.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Nueva ventana-100.png"))); // NOI18N
                            jLabel76.setText("Busqueda de Nomenclaturas ");

                            javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                            jPanel18.setLayout(jPanel18Layout);
                            jPanel18Layout.setHorizontalGroup(
                                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addGap(95, 95, 95)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(61, Short.MAX_VALUE))
                            );
                            jPanel18Layout.setVerticalGroup(
                                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addGap(55, 55, 55)
                                    .addComponent(jLabel76)
                                    .addContainerGap(47, Short.MAX_VALUE))
                            );

                            jTabbedPane3.addTab("tab2", jPanel18);

                            jPanel23.setBackground(new java.awt.Color(255, 255, 255));

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
                            tb_Grupo4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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
                            jScrollPane21.setViewportView(tb_Grupo4);

                            javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                            jPanel23.setLayout(jPanel23Layout);
                            jPanel23Layout.setHorizontalGroup(
                                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 754, Short.MAX_VALUE)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel23Layout.createSequentialGroup()
                                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                            );
                            jPanel23Layout.setVerticalGroup(
                                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 202, Short.MAX_VALUE)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                            );

                            jTabbedPane3.addTab("tab2", jPanel23);

                            jPanel24.setBackground(new java.awt.Color(255, 255, 255));

                            jLabel81.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                            jLabel81.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel81.setText("No se hallaron coincidencias");

                            jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                            jLabel82.setForeground(new java.awt.Color(0, 153, 153));
                            jLabel82.setText(":(");

                            jPanel25.setBackground(new java.awt.Color(153, 153, 153));

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

                            jLabel83.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jLabel83.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel83.setText("Nueva Nomenclatura");

                            javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
                            jPanel25.setLayout(jPanel25Layout);
                            jPanel25Layout.setHorizontalGroup(
                                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGap(67, 67, 67)
                                    .addComponent(btnNuevo2)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                                    .addContainerGap(45, Short.MAX_VALUE)
                                    .addComponent(jLabel83)
                                    .addGap(44, 44, 44))
                            );
                            jPanel25Layout.setVerticalGroup(
                                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(btnNuevo2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(40, Short.MAX_VALUE))
                            );

                            javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
                            jPanel24.setLayout(jPanel24Layout);
                            jPanel24Layout.setHorizontalGroup(
                                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel24Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel82)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel81)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            );
                            jPanel24Layout.setVerticalGroup(
                                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel24Layout.createSequentialGroup()
                                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel24Layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel82))
                                        .addGroup(jPanel24Layout.createSequentialGroup()
                                            .addGap(77, 77, 77)
                                            .addComponent(jLabel81))
                                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jTabbedPane3.addTab("tab3", jPanel24);

                            dlgBuscarCPT.getContentPane().add(jTabbedPane3);
                            jTabbedPane3.setBounds(0, 108, 749, 230);

                            BHC.setAlwaysOnTop(true);
                            BHC.setMinimumSize(new java.awt.Dimension(749, 350));
                            BHC.setResizable(false);

                            jPanel15.setBackground(new java.awt.Color(126, 5, 46));
                            jPanel15.setMinimumSize(new java.awt.Dimension(310, 441));

                            jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                            jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel19.setText("Paciente");

                            jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel14.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel14.setText("Busqueda por DNI, H.C. y Apellidos");

                            bus.setForeground(new java.awt.Color(126, 5, 46));
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

                            bus3.setForeground(new java.awt.Color(126, 5, 46));
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
                            jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Find User Male-80.png"))); // NOI18N
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

                            jPanel35.setBackground(new java.awt.Color(126, 5, 46));
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
                            jLabel87.setForeground(new java.awt.Color(126, 5, 46));
                            jLabel87.setText(":(");

                            jPanel28.setBackground(new java.awt.Color(180, 17, 72));

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

                            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                            setTitle("Admisión emergencia");
                            addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    formKeyPressed(evt);
                                }
                            });

                            jPanel8.setBackground(new java.awt.Color(126, 5, 46));
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
                            jLabel84.setText("<html>Admisión<span style=\"font-size:'14px'\"><br>Emergencia</br></span></html>");

                            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                            jPanel8.setLayout(jPanel8Layout);
                            jPanel8Layout.setHorizontalGroup(
                                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addGap(181, 181, 181)
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblCabpT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblIdFP)
                                                        .addComponent(lblIDHCTo)
                                                        .addComponent(lblNewMod)
                                                        .addComponent(txtIDTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addGap(21, 21, 21)
                                                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                            .addGap(0, 35, Short.MAX_VALUE)))
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
                                    .addComponent(btnBuscar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCabpT)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblIdFP)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblIDHCTo)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblNewMod)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIDTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
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

                            jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            jLabel4.setText("Nº H.C.");

                            jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                            txtNHC.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                            txtNHC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                            txtNHC.setBorder(null);
                            txtNHC.setEnabled(false);
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

                            btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                            btnFiltrar.setMnemonic('B');
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
                                    .addComponent(txtNHC, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0))
                            );
                            jPanel6Layout.setVerticalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            pnlDatosCabecera.setBackground(new java.awt.Color(255, 255, 255));

                            jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel5.setText("Apellidos y Nombres");

                            lblApNom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblApNom.setText("jLabel6");

                            jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel6.setText("Género");

                            lblGenero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblGenero.setText("jLabel7");

                            jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel7.setText("Edad");

                            lblEdad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblEdad.setText("jLabel8");

                            jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel8.setText("Fecha de Nacimiento");

                            lblFechaNac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblFechaNac.setText("jLabel9");

                            jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel9.setText("Estado Civil");

                            lblEstcivil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblEstcivil.setText("jLabel10");

                            jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel16.setText("DNI");

                            lblDni.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblDni.setText("jLabel17");

                            jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel17.setText("Distrito");

                            lblDistrito.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblDistrito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                            lblDistrito.setText("jLabel18");

                            jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel18.setText("Provincia");

                            lblProvincia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblProvincia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                            lblProvincia.setText("jLabel20");

                            jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel20.setText("Departamento");

                            lblDepartamento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblDepartamento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                            lblDepartamento.setText("jLabel21");

                            jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel10.setText("Dirección");

                            lblDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblDireccion.setText("jLabel11");

                            jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel11.setText("Sector");

                            lblSector.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblSector.setText("W");

                            lblEstado.setForeground(new java.awt.Color(255, 255, 255));
                            lblEstado.setText("A");

                            lblIDHC.setForeground(new java.awt.Color(255, 255, 255));
                            lblIDHC.setText("jLabel12");

                            lblHoraIM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblHoraIM.setForeground(new java.awt.Color(51, 51, 51));
                            lblHoraIM.setText("Hora de Ingreso");

                            lblHoraIng.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblHoraIng.setForeground(new java.awt.Color(51, 51, 51));
                            lblHoraIng.setText("jLabel43");

                            lblFechaIng.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblFechaIng.setForeground(new java.awt.Color(51, 51, 51));
                            lblFechaIng.setText("jLabel43");

                            jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel31.setText("Traído por");

                            txtTraidopor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            txtTraidopor.setForeground(new java.awt.Color(51, 51, 51));
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

                            jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel32.setText("Parentesco");

                            txtParentesco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            txtParentesco.setForeground(new java.awt.Color(51, 51, 51));
                            txtParentesco.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    txtParentescoKeyReleased(evt);
                                }
                            });

                            jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel41.setText("Forma de LLegada");

                            cbxFormaLlegada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Caminando" }));

                            pnlEObservación.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            pnlEObservación.setForeground(new java.awt.Color(51, 51, 51));
                            pnlEObservación.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    pnlEObservaciónKeyReleased(evt);
                                }
                            });
                            jScrollPane4.setViewportView(pnlEObservación);

                            jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            jLabel3.setText("Observación");

                            lblFechaIM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblFechaIM.setForeground(new java.awt.Color(51, 51, 51));
                            lblFechaIM.setText("Fecha de Ingreso");

                            panelFormaPago.setBackground(new java.awt.Color(255, 255, 255));
                            panelFormaPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                            txtCPT.setEditable(false);
                            txtCPT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            txtCPT.setForeground(new java.awt.Color(51, 51, 51));
                            txtCPT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                            txtCPT.setBorder(null);
                            txtCPT.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                            txtCPT.setSelectionColor(new java.awt.Color(255, 255, 255));
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

                            javax.swing.GroupLayout panelFormaPagoLayout = new javax.swing.GroupLayout(panelFormaPago);
                            panelFormaPago.setLayout(panelFormaPagoLayout);
                            panelFormaPagoLayout.setHorizontalGroup(
                                panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(txtCPT)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3))
                            );
                            panelFormaPagoLayout.setVerticalGroup(
                                panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelFormaPagoLayout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addGroup(panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                        .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel2.setText("Prioridad");

                            javax.swing.GroupLayout pnlDatosCabeceraLayout = new javax.swing.GroupLayout(pnlDatosCabecera);
                            pnlDatosCabecera.setLayout(pnlDatosCabeceraLayout);
                            pnlDatosCabeceraLayout.setHorizontalGroup(
                                pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                    .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGap(16, 16, 16)
                                    .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addComponent(lblFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(60, 60, 60)
                                            .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblIDHC))
                                        .addComponent(lblApNom, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                    .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(38, 38, 38)
                                            .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel7))
                                            .addGap(18, 18, 18)
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblEstcivil, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(121, 121, 121))
                                .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                    .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel18)
                                                .addComponent(jLabel20)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel17))
                                            .addGap(50, 50, 50)
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                    .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel11)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblSector, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(jLabel2)
                                                .addComponent(lblFechaIM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(29, 29, 29)
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                    .addComponent(lblFechaIng)
                                                    .addGap(26, 26, 26)
                                                    .addComponent(lblHoraIM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(lblHoraIng)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(panelFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                                    .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(cbxFormaLlegada, javax.swing.GroupLayout.Alignment.LEADING, 0, 329, Short.MAX_VALUE)
                                                        .addComponent(txtTraidopor, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE)))))
                                    .addContainerGap())
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
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addGap(0, 1, Short.MAX_VALUE)
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblIDHC, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel16)
                                                    .addComponent(lblDni)))
                                            .addGap(63, 63, 63))
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6)
                                                .addComponent(lblGenero))
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
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel31)
                                                .addComponent(txtTraidopor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel32)
                                                .addComponent(txtParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cbxFormaLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel41))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                                            .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(panelFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlDatosCabeceraLayout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(jLabel2)))
                                    .addGap(15, 15, 15)
                                    .addGroup(pnlDatosCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFechaIM)
                                        .addComponent(lblFechaIng)
                                        .addComponent(lblHoraIM)
                                        .addComponent(lblHoraIng))
                                    .addGap(21, 21, 21))
                            );

                            jLabel21.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel21.setText("Alt + B");

                            txtNroRegistro.setText("21");

                            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                            jPanel1.setLayout(jPanel1Layout);
                            jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1)
                                        .addComponent(pnlDatosCabecera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(30, 30, 30)
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
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pnlDatosCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(61, Short.MAX_VALUE))
                            );

                            tbPaneles.addTab("Cabecera", jPanel1);

                            plTriaje.setBackground(new java.awt.Color(255, 255, 255));
                            plTriaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                            lblIdTr.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            lblIdTr.setText("ID Triaje");

                            jLabel22.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            jLabel22.setText("Nº H.C.");

                            jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                            txtNHCTri.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                            txtNHCTri.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                            txtNHCTri.setBorder(null);
                            txtNHCTri.setEnabled(false);
                            txtNHCTri.addCaretListener(new javax.swing.event.CaretListener() {
                                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                    txtNHCTriCaretUpdate(evt);
                                }
                            });

                            btnFiltrarTri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                            btnFiltrarTri.setMnemonic('B');
                            btnFiltrarTri.setToolTipText("Buscar Nª H.C. (Alt + B)");
                            btnFiltrarTri.setContentAreaFilled(false);
                            btnFiltrarTri.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnFiltrarTri.setEnabled(false);
                            btnFiltrarTri.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnFiltrarTriActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                            jPanel9.setLayout(jPanel9Layout);
                            jPanel9Layout.setHorizontalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addComponent(txtNHCTri, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 6, Short.MAX_VALUE)
                                    .addComponent(btnFiltrarTri, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0))
                            );
                            jPanel9Layout.setVerticalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnFiltrarTri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNHCTri, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jLabel37.setText("Alt + B");

                            pnlTriaje.setBackground(new java.awt.Color(255, 255, 255));

                            jPanel11.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

                            jLabel25.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
                            jLabel25.setText("Funciones Vitales");

                            jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                            jLabel26.setText("PA:");

                            jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                            jLabel27.setText("FC:");

                            jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                            jLabel28.setText("FR:");

                            jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                            jLabel29.setText("Tº:");

                            jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                            jLabel30.setText("Peso:");

                            jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                            jLabel1.setText("Talla:");

                            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                            jPanel11.setLayout(jPanel11Layout);
                            jPanel11Layout.setHorizontalGroup(
                                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addGap(35, 35, 35)
                                            .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                        .addComponent(jLabel26)
                                                        .addGap(108, 108, 108))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                        .addComponent(jLabel30)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(34, 34, 34)))
                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                    .addComponent(jLabel27)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(35, 35, 35)))
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                        .addComponent(jLabel29)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                        .addComponent(jLabel28)
                                                        .addGap(39, 39, 39)
                                                        .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addGap(28, 28, 28)
                                                    .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addContainerGap(21, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(76, 76, 76))
                            );
                            jPanel11Layout.setVerticalGroup(
                                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel25)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel26)
                                                .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel28)
                                        .addComponent(txtFR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel27)
                                            .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel29)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel30)
                                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(15, Short.MAX_VALUE))
                            );

                            lblPaciente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            lblPaciente.setText("jLabel25");

                            jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            jLabel24.setText("Paciente:");

                            lblIDHCTr.setText("jLabel13");

                            jLabel13.setForeground(new java.awt.Color(153, 153, 153));
                            jLabel13.setText("Fecha de ingreso:");

                            lblFechaIngTriaje.setForeground(new java.awt.Color(153, 153, 153));
                            lblFechaIngTriaje.setText("jLabel23");

                            jLabel23.setForeground(new java.awt.Color(153, 153, 153));
                            jLabel23.setText("Hora de ingreso:");

                            lblHoraIngTriaje.setForeground(new java.awt.Color(153, 153, 153));
                            lblHoraIngTriaje.setText("jLabel33");

                            jLabel33.setText("Traído por:");

                            lblTraidoporTriaje.setText("jLabel34");

                            jLabel34.setText("Parentesco:");

                            lblParentesco.setText("jLabel35");

                            lblIDPreventa.setForeground(new java.awt.Color(255, 255, 255));
                            lblIDPreventa.setText("jLabel35");

                            javax.swing.GroupLayout pnlTriajeLayout = new javax.swing.GroupLayout(pnlTriaje);
                            pnlTriaje.setLayout(pnlTriajeLayout);
                            pnlTriajeLayout.setHorizontalGroup(
                                pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlTriajeLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblFechaIngTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel23)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblHoraIngTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                            .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator4)
                                                .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                            .addComponent(jLabel24)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(29, 29, 29)
                                                            .addComponent(lblIDPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                            .addComponent(jLabel33)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblTraidoporTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                            .addContainerGap())))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTriajeLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel34)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTriajeLayout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblIDHCTr, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                                .addGroup(pnlTriajeLayout.createSequentialGroup()
                                    .addGap(152, 152, 152)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );
                            pnlTriajeLayout.setVerticalGroup(
                                pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTriajeLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel24)
                                        .addComponent(lblPaciente)
                                        .addComponent(lblIDPreventa))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel33)
                                        .addComponent(lblTraidoporTriaje)
                                        .addComponent(jLabel34)
                                        .addComponent(lblParentesco))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblIDHCTr)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel23)
                                            .addComponent(lblHoraIngTriaje))
                                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(lblFechaIngTriaje)))
                                    .addContainerGap())
                            );

                            javax.swing.GroupLayout plTriajeLayout = new javax.swing.GroupLayout(plTriaje);
                            plTriaje.setLayout(plTriajeLayout);
                            plTriajeLayout.setHorizontalGroup(
                                plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(plTriajeLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plTriajeLayout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel37)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblIdTr, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator3)
                                        .addComponent(pnlTriaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
                            );
                            plTriajeLayout.setVerticalGroup(
                                plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(plTriajeLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel22)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel37)
                                            .addComponent(lblIdTr)))
                                    .addGap(5, 5, 5)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pnlTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
                            );

                            tbPaneles.addTab("Triaje", plTriaje);

                            pnlTopico.setBackground(new java.awt.Color(255, 255, 255));
                            pnlTopico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                            pnlTopicoP.setBackground(new java.awt.Color(255, 255, 255));

                            pnlTopicoPrioridad.setBackground(new java.awt.Color(255, 255, 255));
                            pnlTopicoPrioridad.setPreferredSize(new java.awt.Dimension(594, 950));

                            jLabel38.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
                            jLabel38.setText("*PRIORIDAD");

                            jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

                            jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel48.setText("FUNCIONES BIOLÓGICAS");

                            jLabel49.setText("Apetito:");

                            jLabel52.setText("Sed:");

                            jLabel53.setText("Deposiciones:");

                            jLabel54.setText("Sueño:");

                            jLabel55.setText("Orinas:");

                            txtDeposiciones.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    txtDeposicionesActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                            jPanel3.setLayout(jPanel3Layout);
                            jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel53)
                                        .addComponent(jLabel49)
                                        .addComponent(jLabel52)
                                        .addComponent(jLabel54)
                                        .addComponent(jLabel55))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSed)
                                        .addComponent(txtSueno)
                                        .addComponent(txtOrinas)
                                        .addComponent(txtDeposiciones)
                                        .addComponent(txtApetito))
                                    .addContainerGap())
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel48)
                                    .addContainerGap(91, Short.MAX_VALUE))
                            );
                            jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel48)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel49)
                                        .addComponent(txtApetito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel52)
                                        .addComponent(txtSed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel54)
                                        .addComponent(txtSueno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel55)
                                        .addComponent(txtOrinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel53)
                                        .addComponent(txtDeposiciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jPanel5.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

                            txtaMotivo.setColumns(20);
                            txtaMotivo.setRows(5);
                            txtaMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtaMotivoKeyPressed(evt);
                                }
                            });
                            jScrollPane7.setViewportView(txtaMotivo);

                            jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel56.setText("MOTIVO DE EMERGENCIA:");

                            jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

                            txtaRelato.setColumns(20);
                            txtaRelato.setRows(5);
                            jScrollPane6.setViewportView(txtaRelato);

                            jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel58.setText("RELATO:");

                            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                            jPanel5.setLayout(jPanel5Layout);
                            jPanel5Layout.setHorizontalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel56)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel58)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
                            );
                            jPanel5Layout.setVerticalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator6)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel56)
                                                .addComponent(jLabel58))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane6)
                                                .addComponent(jScrollPane7)))))
                            );

                            jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel59.setText("Estado General:");

                            jLabel57.setText("Conciencia:");

                            jLabel60.setText("Hidratación:");

                            jLabel61.setText("Nutrición:");

                            jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel62.setText("Examen Físico:");

                            txtaExamenFisico.setColumns(20);
                            txtaExamenFisico.setRows(5);
                            jScrollPane8.setViewportView(txtaExamenFisico);

                            jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel63.setText("Plan de Trabajo:");

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
                            tbExamAux.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                            tbExamAux.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tbExamAuxKeyPressed(evt);
                                }
                            });
                            jScrollPane10.setViewportView(tbExamAux);

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
                            tbImpDx.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                            tbImpDx.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tbImpDxKeyPressed(evt);
                                }
                            });
                            jScrollPane11.setViewportView(tbImpDx);

                            jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel66.setText("Evaluación del paciente:");

                            cbxEvaluacionPaciente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mejorado", "No Mejorado", "Recuperado", "No tratado", "Fallecido" }));

                            jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel67.setText("Ubicación al egreso:");

                            cbxUbicacionEgreso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fugado", "alta", "Retiro Voluntario", "Traslado", "Hospitalización" }));

                            jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel68.setText("Anotaciones médicas:");

                            txtaAnotMed.setColumns(20);
                            txtaAnotMed.setRows(5);
                            jScrollPane12.setViewportView(txtaAnotMed);

                            jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel69.setText("Anotaciones de enfermería:");

                            txtaAnotacionesEnf.setColumns(20);
                            txtaAnotacionesEnf.setRows(5);
                            jScrollPane13.setViewportView(txtaAnotacionesEnf);

                            jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel70.setText("Exámenes Auxiliares:");

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
                            tbDxAlta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                            tbDxAlta.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tbDxAltaKeyPressed(evt);
                                }
                            });
                            jScrollPane14.setViewportView(tbDxAlta);

                            jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                            jLabel72.setText("Dx. de Alta:");

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

                            jLabel64.setText("Paciente:");

                            lblPacienteTo.setText("jLabel65");

                            cbxPrioridad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            cbxPrioridad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "I", "II", "III", "IV", "Cadáver" }));

                            lblIDTriajepTop.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
                            lblIDTriajepTop.setForeground(new java.awt.Color(255, 255, 255));
                            lblIDTriajepTop.setText("jLabel71");

                            javax.swing.GroupLayout pnlTopicoPrioridadLayout = new javax.swing.GroupLayout(pnlTopicoPrioridad);
                            pnlTopicoPrioridad.setLayout(pnlTopicoPrioridadLayout);
                            pnlTopicoPrioridadLayout.setHorizontalGroup(
                                pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                            .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addComponent(jLabel64)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(lblPacienteTo, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(28, 28, 28)
                                                    .addComponent(lblIDTriajepTop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel38)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cbxPrioridad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGap(11, 11, 11))
                                                .addComponent(jLabel70)
                                                .addComponent(jLabel59)
                                                .addComponent(jLabel62)
                                                .addComponent(jLabel72)
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnAgregarDiagfinal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnQuitarDiagFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addComponent(jLabel57)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtConciencia, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel60)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtHidratacion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel61)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtNutricion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel67)
                                                        .addComponent(cbxEvaluacionPaciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cbxUbicacionEgreso, 0, 126, Short.MAX_VALUE)))
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addComponent(jLabel63)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel66))
                                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(16, 16, 16))
                                        .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                            .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator5)
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel68)
                                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane13)
                                                        .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                            .addComponent(jLabel69)
                                                            .addGap(0, 0, Short.MAX_VALUE))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnbuscarExamAux, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnQuitarExam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnBuscarImpDx, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnQuitarDiag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addContainerGap())))
                            );
                            pnlTopicoPrioridadLayout.setVerticalGroup(
                                pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel38)
                                        .addComponent(jLabel64)
                                        .addComponent(lblPacienteTo)
                                        .addComponent(cbxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblIDTriajepTop, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel59)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel57)
                                        .addComponent(txtConciencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel60)
                                        .addComponent(txtHidratacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel61)
                                        .addComponent(txtNutricion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel62)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel63)
                                        .addComponent(jLabel66))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                            .addComponent(cbxEvaluacionPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel67)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxUbicacionEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel70)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                            .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addComponent(btnBuscarImpDx, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(26, 26, 26)
                                                    .addComponent(btnQuitarDiag, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel68)
                                                .addComponent(jLabel69))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                                .addComponent(jScrollPane12))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel72)
                                            .addGroup(pnlTopicoPrioridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addGap(11, 11, 11)
                                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopicoPrioridadLayout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnAgregarDiagfinal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(26, 26, 26)
                                                    .addComponent(btnQuitarDiagFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(29, 29, 29))))
                                        .addGroup(pnlTopicoPrioridadLayout.createSequentialGroup()
                                            .addComponent(btnbuscarExamAux, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(btnQuitarExam, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
                            );

                            pnlTopicoP.setViewportView(pnlTopicoPrioridad);

                            jPanel7.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                            txtNHCTo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                            txtNHCTo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                            txtNHCTo.setBorder(null);
                            txtNHCTo.setEnabled(false);
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

                            btnFiltrarTo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
                            btnFiltrarTo.setMnemonic('B');
                            btnFiltrarTo.setToolTipText("Buscar Nº H.C. (Alt + B)");
                            btnFiltrarTo.setContentAreaFilled(false);
                            btnFiltrarTo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnFiltrarTo.setEnabled(false);
                            btnFiltrarTo.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnFiltrarToActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                            jPanel7.setLayout(jPanel7Layout);
                            jPanel7Layout.setHorizontalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addComponent(txtNHCTo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 6, Short.MAX_VALUE)
                                    .addComponent(btnFiltrarTo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0))
                            );
                            jPanel7Layout.setVerticalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnFiltrarTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNHCTo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jLabel50.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            jLabel50.setText("Nº H.C.");

                            lbl1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            lbl1.setText("ID Tópico:");

                            lbl2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            lbl2.setEnabled(false);
                            lbl2.addCaretListener(new javax.swing.event.CaretListener() {
                                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                    lbl2CaretUpdate(evt);
                                }
                            });

                            jLabel85.setText("Alt + B");

                            lblFormaP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            lblFormaP.setText("Forma de pago:");

                            lblFP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            lblFP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

                            javax.swing.GroupLayout pnlTopicoLayout = new javax.swing.GroupLayout(pnlTopico);
                            pnlTopico.setLayout(pnlTopicoLayout);
                            pnlTopicoLayout.setHorizontalGroup(
                                pnlTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pnlTopicoP)
                                .addGroup(pnlTopicoLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(pnlTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator7)
                                        .addGroup(pnlTopicoLayout.createSequentialGroup()
                                            .addComponent(jLabel50)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel85)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(115, 115, 115)
                                            .addComponent(lblFormaP)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblFP, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
                            );
                            pnlTopicoLayout.setVerticalGroup(
                                pnlTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlTopicoLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(pnlTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel50)
                                        .addGroup(pnlTopicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbl1)
                                            .addComponent(jLabel85)
                                            .addComponent(lbl2)
                                            .addComponent(lblFormaP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblFP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pnlTopicoP, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            );

                            tbPaneles.addTab("Tópico", pnlTopico);

                            jPanel29.setBackground(new java.awt.Color(22, 22, 22));

                            jScrollPane25.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                            tbCabecera = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false;
                                }
                            };
                            tbCabecera.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            tbCabecera.setForeground(new java.awt.Color(102, 102, 102));
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

                            fechai.setBackground(new java.awt.Color(22, 22, 22));
                            fechai.setForeground(new java.awt.Color(102, 102, 102));
                            fechai.setDateFormatString("dd/MM/yyyy");
                            fechai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                            fechaf.setBackground(new java.awt.Color(22, 22, 22));
                            fechaf.setForeground(new java.awt.Color(102, 102, 102));
                            fechaf.setDateFormatString("dd/MM/yyyy");
                            fechaf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

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

                            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                            jPanel29.setLayout(jPanel29Layout);
                            jPanel29Layout.setHorizontalGroup(
                                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                                .addGroup(jPanel29Layout.createSequentialGroup()
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
                            jPanel29Layout.setVerticalGroup(
                                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(fechai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                    .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                            );

                            tbPaneles.addTab("tab4", jPanel29);

                            jPanel10.setBackground(new java.awt.Color(43, 43, 43));
                            jPanel10.setPreferredSize(new java.awt.Dimension(929, 115));

                            jLabel36.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                            jLabel36.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel36.setText("Nuevo Registro");

                            jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                            jLabel42.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            jLabel42.setText("Cabecera");
                            jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                            jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                            jLabel43.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            jLabel43.setText("Triaje");
                            jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                            jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                            jLabel44.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            jLabel44.setText("Tópico");
                            jLabel44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                            jPanel10.setLayout(jPanel10Layout);
                            jPanel10Layout.setHorizontalGroup(
                                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap())
                            );

                            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                            getContentPane().setLayout(layout);
                            layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                                        .addComponent(tbPaneles)))
                            );
                            layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(tbPaneles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
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

    private void btnFiltrarTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarTriActionPerformed
        filtrarDatos();
        cbxTipoBusqueda.requestFocus();
        lblPestana.setText("TR");
        tbPaciente.getSelectionModel().setSelectionInterval(0,0);
    }//GEN-LAST:event_btnFiltrarTriActionPerformed

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
                        habilitarPestanas(1, false);
                    } else {//INSERTAR CAJA_PREVENTA COMO EMERGENCIA
                        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                        if(guardar == 0){
                            Caja_Preventa caja1 = new Caja_Preventa();
                            caja1.setId_hc(lblIDHC.getText());
                            caja1.setEMER_OBSERVACION(pnlEObservación.getText());
                            caja1.setEMER_TRAIDOPOR(txtTraidopor.getText());
                            caja1.setEMER_PARENTESCO(txtParentesco.getText());
                            caja1.setEMER_FORMA_LLEGADA_ID(caja1.codFormaLlegada(cbxFormaLlegada.getSelectedItem().toString()));
                            caja1.setCod_usu(caja1.codUsuario(lblusu.getText()));
//                            caja1.setId_preventa(Integer.parseInt(txtNroRegistro.getText()));
                            caja1.setCod_nomen(txtCPT.getText());
                            if(caja1.mantanimientoCajaPreventaEmergencia("I")==true){
                                //VISUALIZAR EL REPORTE
//                                String ruta = "/reportes/admisionEmergencia/formatoEmergencia-Cabecera.jasper";
                                JOptionPane.showMessageDialog(this, "Datos guardados");
//                                adEmerCab.reporteCabecera(ruta, Integer.parseInt(txtNroRegistro.getText()));
                                pnlDatosCabecera.setVisible(false);
                                limpiar();
                                txtNHC.setText("");
                                txtNHC.setEnabled(false);
                                txtNroRegistro.setText("");
                                habilitarPestanas(4, true);
                            } else {
                                JOptionPane.showMessageDialog(this, "No se guardó ningún registro");
                            }
                        }
                    }
                } else { //MODIFICAR CAJA_PREVENTA COMO EMERGENCIA
                    if(txtTraidopor.getText().equals("") || txtParentesco.getText().equals("")){ //VALIDAR CAJAS VACIAS
                        JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
                        txtTraidopor.setBackground(new Color(153,204,205));
                        txtParentesco.setBackground(new Color(153,204,205));
                    } else {
                        int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                        if(modificar == 0){
                            Caja_Preventa caja1 = new Caja_Preventa();
                            caja1.setId_preventa(Integer.parseInt(txtNroRegistro.getText()));
                            caja1.setEMER_OBSERVACION(pnlEObservación.getText());
                            caja1.setEMER_TRAIDOPOR(txtTraidopor.getText());
                            caja1.setEMER_PARENTESCO(txtParentesco.getText());
                            caja1.setEMER_FORMA_LLEGADA_ID(caja1.codFormaLlegada(cbxFormaLlegada.getSelectedItem().toString()));
                            if(caja1.mantanimientoCajaPreventaEmergencia("U")==true){
                                JOptionPane.showMessageDialog(this, "Datos modificados");
                                pnlDatosCabecera.setVisible(false);
                                limpiar();
                                txtNHC.setText("");
                                txtNHC.setEnabled(false);
                                mostrarDatosModif(false);
                                txtNroRegistro.setText("");
                                btnGuardar.setEnabled(false);
                                btnEliminar.setEnabled(false);
                                cbxFormaLlegada.setSelectedIndex(0);
                                habilitarPestanas(1, true);
                            } else {
                                JOptionPane.showMessageDialog(this, "No se realizaron modificaciones");
                                habilitarPestanas(1, false);
                            }
                        }
                    }
                }
            } else
            if(tbPaneles.getSelectedIndex()==1){ //TRIAJE
                if(lblNewMod.getText().equals("N")/*txtIDTriaje.getText().equals(adEmerTr.idAdmisionEmergenciaTriaje()) ||
                    txtIDTriaje.getText().equals("TR000000000000000001")*/){ // NUEVO REGISTRO DE TRIAJE
                    /*if(txtFC.getText().equals("") || txtPA.getText().equals("") 
                            || txtPeso.getText().equals("") || txtFR.getText().equals("") 
                            || txtT.getText().equals("")){ // VALIDAR CAMPOS VACIOS
                        JOptionPane.showMessageDialog(this, "Ingrese los campos");
                        txtFC.setBackground(new Color(153,204,205));
                        txtPA.setBackground(new Color(153,204,205));
                        txtT.setBackground(new Color(153,204,205));
                        txtPeso.setBackground(new Color(153,204,205));
                        txtFR.setBackground(new Color(153,204,205));
                        habilitarPestanas(2, false);
                    } else { // INSERTAR */
                        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                        if(guardar == 0){ // SELECCION SI
                            AdmisionEmergenciaTriaje adEmerTr1 = new AdmisionEmergenciaTriaje();
                            AdmisionEmergenciaCabecera adEmerCab3 = new AdmisionEmergenciaCabecera();
                            adEmerTr1.setPreventa_id(Integer.parseInt(lblIDPreventa.getText()));
                            adEmerTr1.setTriaje_id("");
                            adEmerTr1.setTriaje_fv_pa(txtPA.getText());
                            adEmerTr1.setTriaje_fv_fc(txtFC.getText());
                            adEmerTr1.setTriaje_fv_t(txtT.getText());
                            adEmerTr1.setTriaje_fv_peso(txtPeso.getText());
                            adEmerTr1.setTriaje_fv_fr(txtFR.getText());
                            adEmerTr1.setTriaje_talla(txtTalla.getText());
                            adEmerTr1.setCod_usu(adEmerCab3.codUsuario(lblusu.getText()));
                            adEmerTr1.setModulo("EME");
                            if(adEmerTr1.mantenimientoAdmisionemergenciaTriaje("I")==true){
                                AdmisionEmergenciaTriaje adEmer = new AdmisionEmergenciaTriaje();
                                adEmer.triajeID();
                                JOptionPane.showMessageDialog(this, "Datos guardados");
                                //VISUALIZAR REPORTE !!!!!
                                adEmerCab.reporteTriaje(txtIDTriaje.getText());
                                pnlTriaje.setVisible(false);
                                btnFiltrarTri.setEnabled(false);
                                txtIDTriaje.setText("");
                                txtNHCTri.setEnabled(false);
                                txtNHCTri.setText("");
                                limpiarDatosTriaje();
                                habilitarPestanas(4, true);
                                btnGuardar.setEnabled(false);
                            }
                        }
                    //}
                } else { // MODIFICAR REGISTRO DE TRIAJE
                    int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(modificar == 0){ // SELECCION SI
                        AdmisionEmergenciaTriaje adEmerTr1 = new AdmisionEmergenciaTriaje();
                        adEmerTr1.setTriaje_id(txtIDTriaje.getText());
                        adEmerTr1.setTriaje_fv_pa(txtPA.getText());
                        adEmerTr1.setTriaje_fv_fc(txtFC.getText());
                        adEmerTr1.setTriaje_fv_t(txtT.getText());
                        adEmerTr1.setTriaje_fv_peso(txtPeso.getText());
                        adEmerTr1.setTriaje_fv_fr(txtFR.getText());
                        adEmerTr1.setTriaje_talla(txtTalla.getText());
                        if(adEmerTr1.mantenimientoAdmisionemergenciaTriaje("U")==true){
                                JOptionPane.showMessageDialog(this, "Datos modificados");
                                pnlTriaje.setVisible(false);
                                btnFiltrarTri.setEnabled(false);
                                txtIDTriaje.setText("");
                                txtNHCTri.setEnabled(false);
                                txtNHCTri.setText("");
                                limpiarDatosTriaje();
                                btnGuardar.setEnabled(false);
                                habilitarPestanas(4, true);
                            }
                    }
                }
            } else 
            if(tbPaneles.getSelectedIndex()==2){ // TOPICO
                if(lblNewMod.getText().equals("N")){ // NUEVO REGISTRO DE TOPICO
                            if(guardarDatos()== true){
                                JOptionPane.showMessageDialog(this, "Datos guardados");
                                adEmerTo.reporteTopico(lbl2.getText());
                                pnlTriaje.setVisible(false);
                                lbl2.setText("");
                                limpiarDatosTopico();
                                pnlTopicoP.setVisible(false);
                                txtNHCTo.setEnabled(false);
                                btnFiltrarTo.setEnabled(false);
                                habilitarPestanas(4, true);
                            }else{
                                JOptionPane.showMessageDialog(this, "Datos guardados");
                                adEmerTo.reporteTopico(lbl2.getText());
                                pnlTriaje.setVisible(false);
                                lbl2.setText("");
                                limpiarDatosTopico();
                                pnlTopicoP.setVisible(false);
                                txtNHCTo.setEnabled(false);
                                btnFiltrarTo.setEnabled(false);
                                habilitarPestanas(4, true);
                            }
                } else { // MODIFICAR TOPICO
                    // MODIFICAR
                    if(modificarDatosTopico()==true){
                        JOptionPane.showMessageDialog(this, "Datos modificados");
                        limpiarDatosTopico();
                        pnlTopicoP.setVisible(false);
                        txtNHCTo.setEnabled(false);
                        btnFiltrarTo.setEnabled(false);
                        habilitarPestanas(4, true);
                    }else
                        JOptionPane.showMessageDialog(this, "Error al modificar registro");
                }
            }//CIERRE TOPICO
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if(tbPaneles.getSelectedIndex()==0){
            btnGuardar.setEnabled(false);
            btnEliminar.setEnabled(false);
            habilitarDatos();
            lblNewMod.setText("N");
            mostrarDatosModif(false);
        } else
        if(tbPaneles.getSelectedIndex()==1){
            btnGuardar.setEnabled(false);
            btnEliminar.setEnabled(false);
            habilitarDatosTriaje();
            lblNewMod.setText("N");
            limpiarDatosTriaje();
        } else 
        if(tbPaneles.getSelectedIndex()==2){
            btnGuardar.setEnabled(false);
            btnEliminar.setEnabled(false);
            lblNewMod.setText("N");
            habilitarDatosTopico();
        }
            //JOptionPane.showMessageDialog(this, txtaMotivo.getText());
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(tbPaneles.getSelectedIndex()==0){
            habilitarDatos();
        }
        if(tbPaneles.getSelectedIndex()==1){
            txtNHCTri.setEnabled(true);
            txtNHCTri.setText("");
            btnFiltrarTri.setEnabled(true);
            txtNHCTri.requestFocus();
        }
        if(tbPaneles.getSelectedIndex()==2){
            txtNHCTo.setEnabled(true);
            txtNHCTo.setText("");
            btnFiltrarTo.setEnabled(true);
            txtNHCTo.requestFocus();
            pnlTopicoP.setVisible(false);
        }
        lblNewMod.setText("M");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Basura-32.png")); 
        try { 
        if(tbPaneles.getSelectedIndex()==0){ //ELIMINAR REGISTRO DE CAJA_PREVENTA (EMERGENCIA)
            int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
            if(eliminar == 0){
                Caja_Preventa cp = new Caja_Preventa();
                cp.setId_preventa(Integer.parseInt(txtNroRegistro.getText()));
                if(cp.mantanimientoCajaPreventaEmergencia("E")){
                    JOptionPane.showMessageDialog(this, "Registro eliminado");
                    limpiar();
                    pnlDatosCabecera.setVisible(false);
                    txtNHC.setEnabled(false);
                    txtNHC.setText("");
                    btnGuardar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                } else 
                    JOptionPane.showMessageDialog(this, "No se registró ningún movimiento");
            }
        habilitarPestanas(1, true);
        } else // ELIMINAR REGISTRO TRIAJE
        if(tbPaneles.getSelectedIndex() == 1){
            int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
            if(eliminar == 0){
                AdmisionEmergenciaTriaje adEmerTr = new AdmisionEmergenciaTriaje();
                adEmerTr.setTriaje_id(txtIDTriaje.getText());
                if(adEmerTr.mantenimientoAdmisionemergenciaTriaje("E")){
                    JOptionPane.showMessageDialog(this, "Registro eliminado");
                    pnlTriaje.setVisible(false);
                    txtNHCTri.setEnabled(false);
                    txtNHCTri.setText("");
                    btnFiltrar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                }
            }
        habilitarPestanas(2, true);
        } else 
        if(tbPaneles.getSelectedIndex() == 2){
            int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
            if(eliminar == 0){
                AdmisionEmergenciaTopico adEmerTop = new AdmisionEmergenciaTopico();
                adEmerTop.setTopico_id(lbl2.getText());
                if(adEmerTop.mantenimientoAdmisionEmergenciaTopico("E")==true){
                    JOptionPane.showMessageDialog(this, "Registro eliminado");
                    pnlTopicoP.setVisible(false);
                    txtNHCTo.setEnabled(false);
                    txtNHCTo.setText("");
                    btnEliminar.setEnabled(false);
                    btnFiltrarTo.setEnabled(false);
                    lbl2.setText("");
                }
            }
        habilitarPestanas(3, true);
        }
        btnGuardar.setEnabled(false);
        } catch (Exception e) {
            System.out.println("Error_btnEliminar" + e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(tbPaneles.getSelectedIndex()==0){
            FrmListFormatoEmergencia frmEmerList = new FrmListFormatoEmergencia();
            frmEmerList.setVisible(true);
//            this.dispose();
            String u=FrmFormatoEmergencia.lblusu.getText();
            frmEmerList.lblUsuUsuario.setText(u);
        } else
        if(tbPaneles.getSelectedIndex()==1){
            FrmListFormatoEmergencia frmEmerList = new FrmListFormatoEmergencia();
            frmEmerList.setVisible(true);
            this.dispose();
            String u=PrincipalMDI.lblUsu.getText();
            frmEmerList.lblUsuUsuario.setText(u);
            FrmListFormatoEmergencia.tbpReporteEmergencia.setSelectedIndex(1);
            AdmisionEmergenciaTriaje a = new AdmisionEmergenciaTriaje();
            a.admisionEmergenciaTriajeListarReporte(FrmListFormatoEmergencia.txtBuscar.getText(), FrmListFormatoEmergencia.tbTriaje, "","");
        } else
        if(tbPaneles.getSelectedIndex()==2){
            FrmListFormatoEmergencia frmEmerList = new FrmListFormatoEmergencia();
            frmEmerList.setVisible(true);
            this.dispose();
            String u=PrincipalMDI.lblUsu.getText();
            frmEmerList.lblUsuUsuario.setText(u);
            FrmListFormatoEmergencia.tbpReporteEmergencia.setSelectedIndex(2);
            adEmerTo.admisionEmergenciaTopicoReporteFinal(FrmListFormatoEmergencia.txtBuscar.getText(), FrmListFormatoEmergencia.tbTopico, "","");
        }
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
                habilitarPestanas(1, true);
            }
        } else {
            if(txtNHC.getText().length()==8){ //MODIFICAR
                habilitarPestanas(1, false);
                mostrarHCCabecera(formatoNHC(txtNHC.getText()));
                pnlDatosCabecera.setVisible(false);
                adEmerCab.cargarFormatEmer(lblIDHC.getText(),"",tbFormatosEmer);
                if(tbFormatosEmer.getRowCount()!=0){
                    if(lblPestana.getText().equals("C"))
                        lblPestanaMod.setText("C");
                    dlgModemergencia.setVisible(true);
                    dlgModemergencia.setLocationRelativeTo(null);//en el centro
                    dlgModemergencia.setResizable(false);
                    dlgModemergencia.getContentPane().setBackground(Color.WHITE);
                } else {
                    habilitarPestanas(1, true);
                    JOptionPane.showMessageDialog(this,"No hay registros");
                    //campo en blanco, para verificar si existe registro o no
                    txtTraidopor.setText("");
                }
            }
        }
        
        if(txtTraidopor.getText().equals("")){
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
            habilitarPestanas(4, true);
        }else{ 
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
            habilitarPestanas(1, false);
        }
    }//GEN-LAST:event_txtNHCCaretUpdate

    private void txtNHCTriCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNHCTriCaretUpdate
        if(lblNewMod.getText().equals("N")){
            if(txtNHCTri.getText().length()==8){
                adEmerCab.mostrarHCTriaje(formatoNHC(txtNHCTri.getText()));
                pnlTriaje.setVisible(false);
                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(),"",tbFormatosEmer);
                AdmisionEmergenciaTriaje ad = new AdmisionEmergenciaTriaje();
                if(tbFormatosEmer.getRowCount()!=0){
                    if(lblPestana.getText().equals("TR") || tbPaneles.getSelectedIndex()==1)
                        lblPestanaMod.setText("TR");
                    dlgModemergencia.setVisible(true);
                    dlgModemergencia.setLocationRelativeTo(null);//en el centro
                    dlgModemergencia.setResizable(false);
                    dlgModemergencia.getContentPane().setBackground(Color.WHITE);
                } else {
                    JOptionPane.showMessageDialog(this,"No hay registros");
                }
            }
            else{
                lblIDHCTr.setText("");
                lblPaciente.setText("");
                txtIDTriaje.setText("");
                pnlTriaje.setVisible(false);
            }
        } else { //MODIFICAR
            if(txtNHCTri.getText().length()==8){
                adEmerCab.mostrarHCTriaje(formatoNHC(txtNHCTri.getText()));
                //pnlTriaje.setVisible(true);
                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(),"",tbFormatosEmer);
                adEmerTr.admisionEmergenciaTriajeListar(lblIDHCTr.getText(),"" , tbModifTriaje,"A");
                if(tbModifTriaje.getRowCount()!=0){
                    dlgModTriaje.setVisible(true);
                    dlgModTriaje.setLocationRelativeTo(null);//en el centro
                    dlgModTriaje.setResizable(false);
                    dlgModTriaje.getContentPane().setBackground(Color.WHITE);
                } else {
                    dlgModTriaje.setVisible(false);
                    JOptionPane.showMessageDialog(this,"No hay registros");
                    txtNHCTri.setText("");
                }
            }
            else{
                lblIDHCTr.setText("");
                lblPaciente.setText("");
                txtIDTriaje.setText("");
                pnlTriaje.setVisible(false);
            }
        }
    }//GEN-LAST:event_txtNHCTriCaretUpdate

    private void BTNBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBActionPerformed
        if(lblPestanaMod.getText().equals("C")){
            if(jdcBusquedaFecha.getCalendar()!= null){
                String fecha = adEmerCab.determinarFecha(jdcBusquedaFecha);
                adEmerCab.cargarFormatEmer(lblIDHC.getText(), fecha, tbFormatosEmer);
            } else {
                adEmerCab.cargarFormatEmer(lblIDHC.getText(), "", tbFormatosEmer);
            }
        } else {
            if(jdcBusquedaFecha.getCalendar()!= null){
                String fecha = adEmerCab.determinarFecha(jdcBusquedaFecha);
                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(), fecha, tbFormatosEmer);
            } else {
                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(), "", tbFormatosEmer);
            }
        }
        tbFormatosEmer.getSelectionModel().setSelectionInterval(0, 0);
        tbFormatosEmer.requestFocus();
    }//GEN-LAST:event_BTNBActionPerformed

    private void tbFormatosEmerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFormatosEmerKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(lblPestanaMod.getText().equals("C")){
            if(teclaPresionada==KeyEvent.VK_ENTER){
                enviarDatosTbFormatEmergencia();
            }
        } else if(lblPestanaMod.getText().equals("TR")){
           if(teclaPresionada==KeyEvent.VK_ENTER){ 
            enviarDatosTbFormatEmergenciaTriaje();
        }
        }
    }//GEN-LAST:event_tbFormatosEmerKeyPressed

    private void tbPanelesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPanelesMouseClicked
        
    }//GEN-LAST:event_tbPanelesMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        
    }//GEN-LAST:event_jPanel1MouseClicked

    private void tbPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacienteMouseClicked
      if(evt.getClickCount()==2){
         enviarDatosTbPaciente();
       }
    }//GEN-LAST:event_tbPacienteMouseClicked

    private void tbFormatosEmerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFormatosEmerMouseClicked
        if(tbPaneles.getSelectedIndex()==0){
            if(evt.getClickCount()==2){
             enviarDatosTbFormatEmergencia();
            }
        } else if(tbPaneles.getSelectedIndex()==1){
            if(evt.getClickCount()==2){
             enviarDatosTbFormatEmergenciaTriaje();
            }
        }
    }//GEN-LAST:event_tbFormatosEmerMouseClicked

    private void btnBuscarMTriajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMTriajeActionPerformed
        if(jdtBuscarTriaje.getCalendar()!=null)
            adEmerTr.admisionEmergenciaTriajeListar(lblIDHCTr.getText(),adEmerCab.determinarFecha(jdtBuscarTriaje), tbModifTriaje,"A");
        else
            adEmerTr.admisionEmergenciaTriajeListar(lblIDHCTr.getText(),"", tbModifTriaje,"A");  
    }//GEN-LAST:event_btnBuscarMTriajeActionPerformed

    private void tbModifTriajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbModifTriajeKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER)
            enviarDatosPnlTriaje();
    }//GEN-LAST:event_tbModifTriajeKeyPressed

    private void tbModifTriajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbModifTriajeMouseClicked
        if(evt.getClickCount()==2)
            enviarDatosPnlTriaje();
    }//GEN-LAST:event_tbModifTriajeMouseClicked

    private void txtNHCToCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNHCToCaretUpdate
        if(lblNewMod.getText().equals("N")){
            if(txtNHCTo.getText().length()==8){
                AdmisionEmergenciaTopico adEmerTo = new AdmisionEmergenciaTopico();
                adEmerCab.mostrarHCTopico(formatoNHC(txtNHCTo.getText()));
                adEmerTo.cargarFormatTriajeparaTopico(lblIDHCTo.getText(),"",tbMostrarTriajepT);
                if(tbMostrarTriajepT.getRowCount()!=0){
                    //pnlTopicoP.setVisible(true);
                    adEmerCab.cargarFormatEmer(lblIDHCTo.getText(),"",tbFormatosEmer);
                    dlgMostrarDatosTriajeT.setVisible(true);
                    dlgMostrarDatosTriajeT.setLocationRelativeTo(null);//en el centro
                    dlgMostrarDatosTriajeT.setResizable(false);
                    dlgMostrarDatosTriajeT.getContentPane().setBackground(Color.WHITE);
                } else {
                    btnGuardar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "No hay registros");
                    txtNHCTo.setText("");
                    lbl2.setText("");
                }
            }else {
                lblPacienteTo.setText("");
                lbl2.setText("");
                pnlTopicoP.setVisible(false);
                btnGuardar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        } else {//MODIFICAR TOPICO
            if(txtNHCTo.getText().length()==8){
                AdmisionEmergenciaCabecera adEmerCab6 = new AdmisionEmergenciaCabecera();
                adEmerCab6.mostrarHCTopico(formatoNHC(txtNHCTo.getText()));
                adEmerTo.cargarDatosTopico(tbDatosTopico, lblIDHCTo.getText(), "");
                if(tbDatosTopico.getRowCount()!=0){
                    dlgMostrarDatosTopico.setVisible(true);
                    dlgMostrarDatosTopico.setLocationRelativeTo(null);//en el centro
                    dlgMostrarDatosTopico.setResizable(false);
                    dlgMostrarDatosTopico.getContentPane().setBackground(Color.WHITE);
                    m = (DefaultTableModel)tbDiagPresun.getModel();
                    int filas = tbDiagPresun.getRowCount();
                    for(int i =0;i<filas;i++){
                        m.removeRow(0);
                    }
                    DefaultTableModel m2 = (DefaultTableModel)tbDiagFinal.getModel(); 
                    m2 = (DefaultTableModel)tbDiagFinal.getModel();
                    int filasf = tbDiagFinal.getRowCount();
                    for(int i =0;i<filasf;i++){
                        m2.removeRow(0);
                    }
                    DefaultTableModel m3 = (DefaultTableModel)tbExamenes.getModel(); 
                    m3 = (DefaultTableModel)tbExamenes.getModel();
                    int filase = tbExamenes.getRowCount();
                    for(int i =0;i<filase;i++){
                        m3.removeRow(0);
                    }
                    lblUsuario.setText(lblusu.getText());
                }else{
                    JOptionPane.showMessageDialog(this, "No hay registros");
                    txtNHCTo.setText("");
                    lbl2.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtNHCToCaretUpdate

    private void txtNHCToKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNHCToKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNHCToKeyPressed

    private void btnFiltrarToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarToActionPerformed
        
            /*lblPestana.setText("MTop");
            adEmerTo.cargarDatosTopico(tbDatosTopico, lblIDHCTo.getText(), "");
            adEmerCab.cargarFormatEmer(lblIDHCTo.getText(),"",tbFormatosEmer);
            dlgMostrarDatosTopico.setVisible(true);
            dlgMostrarDatosTopico.setLocationRelativeTo(null);//en el centro
            dlgMostrarDatosTopico.setResizable(false);
            dlgMostrarDatosTopico.getContentPane().setBackground(Color.WHITE);*/
            lblPestana.setText("TO");
            filtrarDatos();
            cbxTipoBusqueda.requestFocus();
            tbPaciente.getSelectionModel().setSelectionInterval(0,0);
            pnlTopicoP.setVisible(false);
    }//GEN-LAST:event_btnFiltrarToActionPerformed

    private void txtBusquedaToCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedaToCaretUpdate
        adEmerTo.cargarFormatLaboratorio(txtBusquedaTo.getText(), tbDatosLab,lblIdFP.getText());
    }//GEN-LAST:event_txtBusquedaToCaretUpdate

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
                formatotbExamenAux(tbExamenes);
                tbDatosTopico.getSelectionModel().setSelectionInterval(0, 0);
                tbDatosTopico.requestFocus();
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

    private void txtBuscarDiagnosticoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDiagnosticoCaretUpdate
        adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
    }//GEN-LAST:event_txtBuscarDiagnosticoCaretUpdate

    private void btnBuscarImpDxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarImpDxActionPerformed
        if(tbImpDx.getRowCount()<2){
            adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
            dlgListaImpDx.setVisible(true);
            dlgListaImpDx.setLocationRelativeTo(null);//en el centro
            dlgListaImpDx.setResizable(false);
            dlgListaImpDx.getContentPane().setBackground(Color.WHITE);
            lblTipoDiag.setText("Diag");
        } else
            JOptionPane.showMessageDialog(this, "Sólo se permiten dos tipos de diagnósticos");
    }//GEN-LAST:event_btnBuscarImpDxActionPerformed

    private void btnbuscarExamAuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarExamAuxActionPerformed
        adEmerTo.cargarFormatLaboratorio(txtBusquedaTo.getText(), tbDatosLab,lblIdFP.getText());
        dlgListaExamnAux.setVisible(true);
        dlgListaExamnAux.setLocationRelativeTo(null);//en el centro
        dlgListaExamnAux.setResizable(false);
        dlgListaExamnAux.getContentPane().setBackground(Color.WHITE);
        lblTipo.setText("nuevo");
    }//GEN-LAST:event_btnbuscarExamAuxActionPerformed

    private void txtaMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaMotivoKeyPressed

    }//GEN-LAST:event_txtaMotivoKeyPressed

    private void txtDeposicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeposicionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeposicionesActionPerformed

    private void btnQuitarExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarExamActionPerformed
        quitarExamenAux();
    }//GEN-LAST:event_btnQuitarExamActionPerformed

    private void btnQuitarDiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDiagActionPerformed
        quitarDiagImpr();
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
                        formatotbImpdx(tbDiagPresun);
                        tbDatosTopico.getSelectionModel().setSelectionInterval(0, 0);
                        tbDatosTopico.requestFocus();
                    }
                    else 
                        if(lblTipoDiag.getText().equals("f")){
                            enviarDatosDiagnosticoF();
                            formatotbImpdx(tbDiagFinal);
                            tbDatosTopico.getSelectionModel().setSelectionInterval(0, 0);
                            tbDatosTopico.requestFocus();
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
                        formatotbImpdx(tbDiagPresun);
                    }
                    else 
                        if(lblTipoDiag.getText().equals("f")){
                            enviarDatosDiagnosticoF();
                            formatotbImpdx(tbDiagFinal);
                        }
        }
    }//GEN-LAST:event_tbDiagnosticosMouseClicked

    private void btnAgregarDiagfinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiagfinalActionPerformed
        if(tbDxAlta.getRowCount()<2){
            adEmerTo.cargarDatosCie10(txtBuscarDiagnostico.getText(), tbDiagnosticos);
            dlgListaImpDx.setVisible(true);
            dlgListaImpDx.setLocationRelativeTo(null);//en el centro
            dlgListaImpDx.setResizable(false);
            dlgListaImpDx.getContentPane().setBackground(Color.WHITE);
            lblTipoDiag.setText("DiagF");
        } else
            JOptionPane.showMessageDialog(this, "Sólo se permiten dos tipos de diagnósticos");
    }//GEN-LAST:event_btnAgregarDiagfinalActionPerformed

    private void btnQuitarDiagFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDiagFinalActionPerformed
        quitarDiagFinal();
    }//GEN-LAST:event_btnQuitarDiagFinalActionPerformed

    private void tbExamAuxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbExamAuxKeyPressed
        quitarExamenAux();
    }//GEN-LAST:event_tbExamAuxKeyPressed

    private void tbImpDxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbImpDxKeyPressed
        quitarDiagImpr();
    }//GEN-LAST:event_tbImpDxKeyPressed

    private void tbDxAltaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDxAltaKeyPressed
        if(tbDxAlta.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tbDxAlta.getModel(); 
            m.removeRow(tbDxAlta.getSelectedRow()); 
        } else 
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
    }//GEN-LAST:event_tbDxAltaKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_F3){
            btnAgregarDiagfinalActionPerformed(null);
        }
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
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER)
            enviarDatosaPnlTopico();
    }//GEN-LAST:event_tbMostrarTriajepTKeyPressed

    private void tbMostrarTriajepTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMostrarTriajepTMouseClicked
        if(evt.getClickCount()==2){
            enviarDatosaPnlTopico();
        }
    }//GEN-LAST:event_tbMostrarTriajepTMouseClicked

    private void lbl2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lbl2CaretUpdate
        if(lbl2.getText().equals("")){
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
        }else{ 
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
            habilitaBotonesJTable(true);
        }
        habilitarPestanas(3, false);
    }//GEN-LAST:event_lbl2CaretUpdate

    private void txtIDTriajeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDTriajeCaretUpdate
        if(txtIDTriaje.getText().equals("")){
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
            habilitarPestanas(4, true);
        }else{ 
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
            habilitarPestanas(2, false);
        }
    }//GEN-LAST:event_txtIDTriajeCaretUpdate

    private void tbDatosTopicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDatosTopicoKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        int fila = tbDatosTopico.getSelectedRow();
        if(teclaPresionada==KeyEvent.VK_ENTER)
            enviarDatosdeTopico();
        if(evt.isShiftDown()){
            adEmerTo.admisionEmergenciaTopicoDetalles(tbExamenes, String.valueOf(tbDatosTopico.getValueAt(fila, 0)), 1);
            adEmerTo.admisionEmergenciaTopicoDetalles(tbDiagPresun, String.valueOf(tbDatosTopico.getValueAt(fila, 0)), 2);
            adEmerTo.admisionEmergenciaTopicoDetalles(tbDiagFinal, String.valueOf(tbDatosTopico.getValueAt(fila, 0)), 3);
        }
    }//GEN-LAST:event_tbDatosTopicoKeyPressed

    private void tbDatosTopicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosTopicoMouseClicked
        int fila = tbDatosTopico.getSelectedRow();
        if(evt.getClickCount()==1){
            adEmerTo.admisionEmergenciaTopicoDetalles(tbExamenes, String.valueOf(tbDatosTopico.getValueAt(fila, 0)), 1);
            adEmerTo.admisionEmergenciaTopicoDetalles(tbDiagPresun, String.valueOf(tbDatosTopico.getValueAt(fila, 0)), 2);
            adEmerTo.admisionEmergenciaTopicoDetalles(tbDiagFinal, String.valueOf(tbDatosTopico.getValueAt(fila, 0)), 3);
            lblIdFP.setText(String.valueOf(tbDatosTopico.getValueAt(fila, 24)));
        } else 
        if(evt.getClickCount()==2)
            enviarDatosdeTopico();
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

    private void txtTraidoporCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTraidoporCaretUpdate
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(true);
        habilitarPestanas(1, false);
    }//GEN-LAST:event_txtTraidoporCaretUpdate

    private void jdtFechaTopKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdtFechaTopKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbDatosTopico.getSelectionModel().setSelectionInterval(0, 0);
            tbDatosTopico.requestFocus();
        }
    }//GEN-LAST:event_jdtFechaTopKeyPressed

    private void btnbuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar3ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
        Caja_Nomenclatura cajaNomen = new Caja_Nomenclatura();
        jTabbedPane3.setSelectedIndex(1);
        cajaNomen.cajaNomenclaturaListarEmergencia(txtBuscar2.getText(), tb_Grupo4);
        if (tb_Grupo4.getRowCount() == 0){
            jTabbedPane3.setSelectedIndex(2);
        }
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

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

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Grupo4.getSelectionModel().setSelectionInterval(0, 0);
            tb_Grupo4.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar2KeyPressed

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

    private void txtTraidoporKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTraidoporKeyReleased
        txtTraidopor.setText(txtTraidopor.getText().toUpperCase());
    }//GEN-LAST:event_txtTraidoporKeyReleased

    private void txtParentescoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParentescoKeyReleased
        txtParentesco.setText(txtParentesco.getText().toUpperCase());
    }//GEN-LAST:event_txtParentescoKeyReleased

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
       
            if(btnBuscar.getText().equals("Iniciar")){
                if(fechai.getDate()==null || fechai.getDate()==null){
                    JOptionPane.showMessageDialog(this, "Ingrese un rango de fechas");
                }
                cp.listarDatosEmergencia(txtBuscar.getText(), determinarFecha(fechai),determinarFecha(fechaf), tbCabecera);
                btnBuscar.setText("Detener");
                txtBuscar.setVisible(true);
                txtBuscar.requestFocus();
                btnBuscar.setIcon(detener);
                txtBuscar.setEnabled(true);
               
            } else {
                cp.listarDatosEmergencia(txtBuscar.getText(), "","", tbCabecera);
                btnBuscar.setText("Iniciar");
                txtBuscar.setVisible(false);
                btnBuscar.setIcon(continuar);
                txtBuscar.setEnabled(false);
  
            }
        
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        imprimirCabecera();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void txtCPTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPTCaretUpdate

    }//GEN-LAST:event_txtCPTCaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
        dlgBuscarCPT.setLocationRelativeTo(null);//en el centro
        dlgBuscarCPT.setResizable(false);
        dlgBuscarCPT.getContentPane().setBackground(Color.WHITE);
        dlgBuscarCPT.setVisible(true);
        Caja_Nomenclatura cajaNomen = new Caja_Nomenclatura();
        cajaNomen.cajaNomenclaturaListarEmergencia(txtBuscar2.getText(), tb_Grupo4);
    }//GEN-LAST:event_btnBuscarCPTActionPerformed
    
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
            java.util.logging.Logger.getLogger(FrmFormatoEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new FrmFormatoEmergencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ABONOS;
    private javax.swing.JDialog BHC;
    private javax.swing.JButton BTNB;
    private javax.swing.JButton btnAddDiagF;
    private javax.swing.JButton btnAddDiagP;
    private javax.swing.JButton btnAddExam;
    private javax.swing.JButton btnAgregarDiagfinal;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarDiagnostico;
    private javax.swing.JButton btnBuscarImpDx;
    private javax.swing.JButton btnBuscarMTriaje;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBuscarTo;
    private javax.swing.JButton btnBuscarTriTop;
    private javax.swing.JButton btnDelDiagF;
    private javax.swing.JButton btnDelDiagP;
    private javax.swing.JButton btnDelExam;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnFiltrarTo;
    private javax.swing.JButton btnFiltrarTri;
    private javax.swing.JButton btnFiltrarTri1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnQuitarDiag;
    private javax.swing.JButton btnQuitarDiagFinal;
    private javax.swing.JButton btnQuitarExam;
    private javax.swing.JButton btnbuscar3;
    private javax.swing.JButton btnbuscarExamAux;
    private javax.swing.JLabel bus;
    private javax.swing.JLabel bus3;
    private javax.swing.JComboBox cbxEvaluacionPaciente;
    public static javax.swing.JComboBox cbxFormaLlegada;
    public static javax.swing.JComboBox cbxPrioridad;
    private javax.swing.JComboBox cbxTipoBusqueda;
    private javax.swing.JComboBox cbxUbicacionEgreso;
    private javax.swing.JDialog dlgBuscarCPT;
    private javax.swing.JDialog dlgBuscarPac;
    private javax.swing.JDialog dlgListaExamnAux;
    private javax.swing.JDialog dlgListaImpDx;
    private javax.swing.JDialog dlgModTriaje;
    private javax.swing.JDialog dlgModemergencia;
    private javax.swing.JDialog dlgMostrarDatosTopico;
    private javax.swing.JDialog dlgMostrarDatosTriajeT;
    private com.toedter.calendar.JDateChooser fechaf;
    private com.toedter.calendar.JDateChooser fechai;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel10;
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
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
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
    public static javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JDateChooser jdcBusquedaFecha;
    private com.toedter.calendar.JDateChooser jdtBuscarTriTop;
    private com.toedter.calendar.JDateChooser jdtBuscarTriaje;
    private com.toedter.calendar.JDateChooser jdtFechaTop;
    private javax.swing.JPopupMenu jpmCabecera;
    private javax.swing.JLabel lbl1;
    public static javax.swing.JTextField lbl2;
    public static javax.swing.JLabel lblApNom;
    public static javax.swing.JLabel lblCabpT;
    public static javax.swing.JLabel lblDepartamento;
    public static javax.swing.JLabel lblDireccion;
    public static javax.swing.JLabel lblDistrito;
    public static javax.swing.JLabel lblDni;
    public static javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEstado;
    public static javax.swing.JLabel lblEstcivil;
    public static javax.swing.JLabel lblFP;
    private javax.swing.JLabel lblFechaIM;
    public static javax.swing.JLabel lblFechaIng;
    public static javax.swing.JLabel lblFechaIngTriaje;
    public static javax.swing.JLabel lblFechaNac;
    private javax.swing.JLabel lblFormaP;
    public static javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblHoraIM;
    public static javax.swing.JLabel lblHoraIng;
    public static javax.swing.JLabel lblHoraIngTriaje;
    private javax.swing.JLabel lblIDHC;
    public static javax.swing.JLabel lblIDHCTo;
    public static javax.swing.JLabel lblIDHCTr;
    public static javax.swing.JLabel lblIDPreventa;
    public static javax.swing.JLabel lblIDTriajepTop;
    public static javax.swing.JLabel lblIdFP;
    public static javax.swing.JLabel lblIdPreventa;
    private javax.swing.JLabel lblIdTr;
    private javax.swing.JLabel lblNewMod;
    public static javax.swing.JLabel lblPaciente;
    public static javax.swing.JLabel lblPacienteTo;
    public static javax.swing.JLabel lblParentesco;
    public static final javax.swing.JLabel lblPestana = new javax.swing.JLabel();
    public static javax.swing.JLabel lblPestanaMod;
    public static javax.swing.JLabel lblProvincia;
    public static javax.swing.JLabel lblSector;
    private javax.swing.JLabel lblTipo;
    public static javax.swing.JLabel lblTipoDiag;
    public static javax.swing.JLabel lblTraidoporTriaje;
    public static javax.swing.JLabel lblUsuario;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel panelBuscarHC;
    private javax.swing.JPanel panelFormaPago;
    private javax.swing.JPanel panelSinHC;
    private javax.swing.JPanel paneltablaHC;
    private javax.swing.JPanel plTriaje;
    private javax.swing.JPanel pnlB;
    private javax.swing.JPanel pnlDatosCabecera;
    public static javax.swing.JEditorPane pnlEObservación;
    private javax.swing.JPanel pnlTopico;
    private javax.swing.JScrollPane pnlTopicoP;
    private javax.swing.JPanel pnlTopicoPrioridad;
    private javax.swing.JPanel pnlTriaje;
    private javax.swing.JTable tbCabecera;
    private javax.swing.JTable tbDatosLab;
    private javax.swing.JTable tbDatosTopico;
    private javax.swing.JTable tbDiagFinal;
    private javax.swing.JTable tbDiagPresun;
    private javax.swing.JTable tbDiagnosticos;
    private javax.swing.JTable tbDxAlta;
    private javax.swing.JTable tbExamAux;
    private javax.swing.JTable tbExamenes;
    private javax.swing.JTable tbFormatosEmer;
    private javax.swing.JTable tbImpDx;
    private javax.swing.JTable tbModifTriaje;
    private javax.swing.JTable tbMostrarTriajepT;
    private static javax.swing.JTable tbPaciente;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTabbedPane tbPaneles;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JTable tbpreventas;
    private javax.swing.JTable tbpreventasFR;
    public static javax.swing.JTextField txtApetito;
    public static javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtBuscarDiagnostico;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtBusquedaTo;
    public static javax.swing.JTextField txtCPT;
    public static javax.swing.JTextField txtConciencia;
    public static javax.swing.JTextField txtDeposiciones;
    public static javax.swing.JTextField txtFC;
    public static javax.swing.JTextField txtFR;
    public static javax.swing.JTextField txtHidratacion;
    public static javax.swing.JTextField txtIDTriaje;
    public static javax.swing.JTextField txtNHC;
    public static javax.swing.JTextField txtNHCTo;
    public static javax.swing.JTextField txtNHCTri;
    public static javax.swing.JLabel txtNroRegistro;
    public static javax.swing.JTextField txtNutricion;
    public static javax.swing.JTextField txtOrinas;
    public static javax.swing.JTextField txtPA;
    public static javax.swing.JTextField txtParentesco;
    public static javax.swing.JTextField txtPeso;
    public static javax.swing.JTextField txtSed;
    public static javax.swing.JTextField txtSueno;
    public static javax.swing.JTextField txtT;
    public static javax.swing.JTextField txtTalla;
    public static javax.swing.JTextField txtTraidopor;
    public static javax.swing.JTextArea txtaAnotMed;
    public static javax.swing.JTextArea txtaAnotacionesEnf;
    public static javax.swing.JTextArea txtaExamenFisico;
    public static javax.swing.JTextArea txtaMotivo;
    public static javax.swing.JTextArea txtaPlanTrabajo;
    public static javax.swing.JTextArea txtaRelato;
    // End of variables declaration//GEN-END:variables
}
