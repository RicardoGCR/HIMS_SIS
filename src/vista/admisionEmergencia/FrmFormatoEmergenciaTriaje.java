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
public class FrmFormatoEmergenciaTriaje extends javax.swing.JFrame implements Runnable{
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
    AdmisionEmergenciaTriaje adEmerTr1 = new AdmisionEmergenciaTriaje();
    AdmisionEmergenciaTopico adEmerTo = new AdmisionEmergenciaTopico();
    Caja_Preventa cp = new Caja_Preventa();
    byte tg;
    byte tge;
    byte tga;
    /**
     * Creates new form FrmemergenciaCabecera
     */
    public FrmFormatoEmergenciaTriaje() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);//fondo blanco
        setLocationRelativeTo(null);//en el centro
        this.setExtendedState(MAXIMIZED_BOTH);
        btnBuscar.setMnemonic(KeyEvent.VK_F3);
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
        adEmerTr1.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, "","");
        tbTriaje.getSelectionModel().setSelectionInterval(0, 0);
        tbTriaje.requestFocus();
        cerrar();
//        buscar_HC(1,"A","");
        pnlB.setEnabled(false);
        tbPaneles.setEnabledAt(0,false);
        tbPaneles.setEnabledAt(1, false);
        restringirCampos(8, txtNHCTri);
        pnlTriaje.setVisible(false);
        tbPacientes.setTableHeader(null);
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
        tbPaneles.setSelectedIndex(1);

        formatotbExamenAux(tbExamenes);

        formatotbImpdx(tbDiagFinal);
        formatotbImpdx(tbDiagPresun);
        //tbPaciente.setDefaultRenderer(Object.class,new tablas.AdmisionEmergenciaTopico());
        //limitadores
        cargareliminar.setVisible(false);
        lblNewMod.setVisible(false);
        lblCabpT.setVisible(false);
        lblIDHCTr.setVisible(false);
        txtIDTriaje.setVisible(false);
        lblIdFP.setVisible(false);
        lblIdTr.setVisible(false);
        lblIDHCTo.setVisible(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
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
     public void imprimirTriaje(){
        int fila = tbTriaje.getSelectedRow();
        adEmerCab.reporteTriaje(String.valueOf(tbTriaje.getValueAt(fila, 0)));
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

    public void limpiarDatosTriaje(){
        lblPaciente.setText("");
        txtFC.setText("");
        txtFR.setText("");
        txtPA.setText("");
        txtT.setText("");
        txtPeso.setText("");
        txtTalla.setText("");
        lblFechaIng.setText("");
        lblHoraIng.setText("");
        txtIDM.setText("");
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
    
    
//    public void mostrarHCTriaje(String nhc){
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
//                
//                
//            }
//            //
//        } catch (Exception e) {
//            System.out.println("Error_mostrar_MovHC: " + e.getMessage());
//        }
//    }
    
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
    

    public void enviarDatosTbPaciente(){
        int fila = tbPacientes.getSelectedRow();
        BHC.dispose();
            txtNHCTri.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
            lblPaciente.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));
            lblIDPreventa.setText(String.valueOf(tbPacientes.getValueAt(fila, 7)));
            txtPA.requestFocus();
            btnGuardar.setEnabled(true);

    }
    

    
    public void enviarDatosTbFormatEmergenciaTriaje(){
        int fila = tbFormatosEmer.getSelectedRow();
        dlgModemergencia.dispose();
        pnlTriaje.setVisible(true);
        FrmFormatoEmergenciaTriaje.lblTraidoporTriaje.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 1)));
        FrmFormatoEmergenciaTriaje.lblParentesco.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 2)));
//        FrmFormatoEmergenciaTriaje.lblFechaIngTriaje.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 6)));
//        FrmFormatoEmergenciaTriaje.lblHoraIngTriaje.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 7)));
        FrmFormatoEmergenciaTriaje.lblIDPreventa.setText(String.valueOf(tbFormatosEmer.getValueAt(fila, 0)));
        btnGuardar.setEnabled(true);
//        txtIDTriaje.setText(adEmerTr.idAdmisionEmergenciaTriaje());
//        if(txtIDTriaje.getText().equalsIgnoreCase("")){
//            txtIDTriaje.setText("TR000000000000000001");
//        }
    }   
    
 
    
    public void enviarDatosPnlTriaje(){
        int fila = tbTriaje.getSelectedRow();
        jLabel36.setText("Registro");
        tbPaneles.setSelectedIndex(0);
        pnlTriaje.setVisible(true);
        FrmFormatoEmergenciaTriaje.txtNHCTri.setText(String.valueOf(tbTriaje.getValueAt(fila, 1)));
        FrmFormatoEmergenciaTriaje.txtIDTriaje.setText(String.valueOf(tbTriaje.getValueAt(fila, 0)));
        
        FrmFormatoEmergenciaTriaje.txtFC.setText(String.valueOf(tbTriaje.getValueAt(fila, 6)));
        FrmFormatoEmergenciaTriaje.txtFR.setText(String.valueOf(tbTriaje.getValueAt(fila, 7)));
        FrmFormatoEmergenciaTriaje.txtPA.setText(String.valueOf(tbTriaje.getValueAt(fila, 8)));
        FrmFormatoEmergenciaTriaje.txtPeso.setText(String.valueOf(tbTriaje.getValueAt(fila, 9)));
        FrmFormatoEmergenciaTriaje.txtT.setText(String.valueOf(tbTriaje.getValueAt(fila, 10)));
        FrmFormatoEmergenciaTriaje.txtTalla.setText(String.valueOf(tbTriaje.getValueAt(fila, 11)));
        FrmFormatoEmergenciaTriaje.lblHoraIng.setText(String.valueOf(tbTriaje.getValueAt(fila, 5)));
        FrmFormatoEmergenciaTriaje.lblFechaIng.setText(String.valueOf(tbTriaje.getValueAt(fila, 4)));
        txtIDM.setText(String.valueOf(tbTriaje.getValueAt(fila, 13)));
        
        FrmFormatoEmergenciaTriaje.lblIDPreventa.setText(String.valueOf(tbTriaje.getValueAt(fila, 12)));
        //Mostrar ID
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }
    
  
    
 
    

 
    
    public void habilitarDatosTriaje(){
        txtNHCTri.requestFocus();
        txtNHCTri.setEnabled(true);
        btnFiltrarTri.setEnabled(true);
        txtNHCTri.setText("");
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
                            jpmTriaje = new javax.swing.JPopupMenu();
                            jMenuItem1 = new javax.swing.JMenuItem();
                            jSeparator1 = new javax.swing.JPopupMenu.Separator();
                            mnuVisualizar = new javax.swing.JMenuItem();
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
                            plTriaje = new javax.swing.JPanel();
                            lblIdTr = new javax.swing.JLabel();
                            jLabel22 = new javax.swing.JLabel();
                            jPanel9 = new javax.swing.JPanel();
                            txtNHCTri = new javax.swing.JTextField();
                            btnFiltrarTri = new javax.swing.JButton();
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
                            txtIDM = new javax.swing.JTextField();
                            jLabel3 = new javax.swing.JLabel();
                            jSeparator4 = new javax.swing.JSeparator();
                            lblPaciente = new javax.swing.JLabel();
                            jLabel24 = new javax.swing.JLabel();
                            lblIDHCTr = new javax.swing.JLabel();
                            jLabel33 = new javax.swing.JLabel();
                            lblTraidoporTriaje = new javax.swing.JLabel();
                            jLabel34 = new javax.swing.JLabel();
                            lblParentesco = new javax.swing.JLabel();
                            lblIDPreventa = new javax.swing.JLabel();
                            lblFechaIM = new javax.swing.JLabel();
                            lblFechaIng = new javax.swing.JLabel();
                            lblHoraIM = new javax.swing.JLabel();
                            lblHoraIng = new javax.swing.JLabel();
                            jPanel1 = new javax.swing.JPanel();
                            jPanel3 = new javax.swing.JPanel();
                            fechaf = new com.toedter.calendar.JDateChooser();
                            fechai = new com.toedter.calendar.JDateChooser();
                            txtBuscar = new javax.swing.JTextField();
                            btnBuscar1 = new javax.swing.JButton();
                            jScrollPane4 = new javax.swing.JScrollPane();
                            tbTriaje = new javax.swing.JTable();
                            jPanel10 = new javax.swing.JPanel();
                            jLabel36 = new javax.swing.JLabel();
                            jLabel2 = new javax.swing.JLabel();
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

                            jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones.png"))); // NOI18N
                            jMenuItem1.setText("Opciones");
                            jpmTriaje.add(jMenuItem1);
                            jpmTriaje.add(jSeparator1);

                            mnuVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/imprimir16x16.png"))); // NOI18N
                            mnuVisualizar.setText("Imprimir");
                            mnuVisualizar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    mnuVisualizarActionPerformed(evt);
                                }
                            });
                            mnuVisualizar.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    mnuVisualizarKeyPressed(evt);
                                }
                            });
                            jpmTriaje.add(mnuVisualizar);

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
                            jLabel84.setText("<html>Triaje<span style=\"font-size:'14px'\"><br>Emergencia</br></span></html>");

                            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                            jPanel8.setLayout(jPanel8Layout);
                            jPanel8Layout.setHorizontalGroup(
                                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblusu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblCabpT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblIdFP)
                                                        .addComponent(lblIDHCTo)
                                                        .addComponent(lblNewMod)
                                                        .addComponent(txtIDTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(0, 35, Short.MAX_VALUE)))
                                    .addContainerGap())
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addGap(116, 116, 116)
                                    .addComponent(lblCabpT)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblIdFP)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblIDHCTo)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblNewMod)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIDTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                            plTriaje.setBackground(new java.awt.Color(255, 255, 255));
                            plTriaje.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                            lblIdTr.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            lblIdTr.setText("ID Triaje");

                            jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel22.setText("Nº H.C.");

                            jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                            txtNHCTri.setEditable(false);
                            txtNHCTri.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            txtNHCTri.setBorder(null);
                            txtNHCTri.addCaretListener(new javax.swing.event.CaretListener() {
                                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                    txtNHCTriCaretUpdate(evt);
                                }
                            });

                            btnFiltrarTri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
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
                                    .addGap(3, 3, 3)
                                    .addComponent(txtNHCTri, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnFiltrarTri, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0))
                            );
                            jPanel9Layout.setVerticalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNHCTri, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                        .addComponent(btnFiltrarTri, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jLabel37.setForeground(new java.awt.Color(153, 153, 153));
                            jLabel37.setText("Alt + B");

                            pnlTriaje.setBackground(new java.awt.Color(255, 255, 255));

                            jPanel11.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel11.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                            jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                            jLabel25.setForeground(new java.awt.Color(51, 51, 51));
                            jLabel25.setText("Funciones Vitales");

                            jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel26.setText("Presión Arterial");

                            txtPA.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtPAKeyPressed(evt);
                                }
                            });

                            txtFC.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtFCKeyPressed(evt);
                                }
                            });

                            jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel27.setText("Frecuencia Cardiaca");

                            jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel28.setText("Frecuencia Respiratoria");

                            jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel29.setText("Temperatura:");

                            jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel30.setText("Peso");

                            txtFR.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtFRKeyPressed(evt);
                                }
                            });

                            txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtPesoKeyPressed(evt);
                                }
                            });

                            txtT.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    txtTMouseClicked(evt);
                                }
                            });
                            txtT.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtTKeyPressed(evt);
                                }
                            });

                            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel1.setText("Talla");

                            txtTalla.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtTallaKeyPressed(evt);
                                }
                            });

                            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel3.setText("Índice de Masa Corporal");

                            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                            jPanel11.setLayout(jPanel11Layout);
                            jPanel11Layout.setHorizontalGroup(
                                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29)
                                        .addComponent(jLabel28)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel27)
                                        .addComponent(jLabel26)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3))
                                    .addGap(86, 86, 86)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtIDM)
                                        .addComponent(txtTalla, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                        .addComponent(txtFR, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPeso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                        .addComponent(txtFC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                        .addComponent(txtPA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                                    .addGap(303, 303, 303))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                            );
                            jPanel11Layout.setVerticalGroup(
                                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel26)
                                        .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel27)
                                        .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel30))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel28)
                                        .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel29)
                                        .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtIDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap())
                            );

                            lblPaciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            lblPaciente.setText("jLabel25");

                            jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel24.setText("Paciente");

                            lblIDHCTr.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            lblIDHCTr.setText("jLabel13");

                            jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel33.setText("Edad");

                            lblTraidoporTriaje.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            lblTraidoporTriaje.setText("jLabel34");

                            jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            jLabel34.setText("Género");

                            lblParentesco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            lblParentesco.setText("jLabel35");

                            lblIDPreventa.setForeground(new java.awt.Color(255, 255, 255));
                            lblIDPreventa.setText("jLabel35");

                            lblFechaIM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            lblFechaIM.setText("Fecha de Ingreso");

                            lblFechaIng.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            lblFechaIng.setText("jLabel43");

                            lblHoraIM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            lblHoraIM.setText("Hora de Ingreso");

                            lblHoraIng.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                            lblHoraIng.setText("jLabel43");

                            javax.swing.GroupLayout pnlTriajeLayout = new javax.swing.GroupLayout(pnlTriaje);
                            pnlTriaje.setLayout(pnlTriajeLayout);
                            pnlTriajeLayout.setHorizontalGroup(
                                pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlTriajeLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                            .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel33)
                                                .addComponent(jLabel24))
                                            .addGap(302, 553, Short.MAX_VALUE))
                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                            .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator4)
                                                .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                    .addComponent(lblFechaIM, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                            .addComponent(lblPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(33, 33, 33)
                                                            .addComponent(lblIDPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblIDHCTr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblTraidoporTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                            .addComponent(lblFechaIng, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(125, 125, 125)
                                                            .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblHoraIM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel34))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblHoraIng))))))
                                            .addContainerGap())))
                            );
                            pnlTriajeLayout.setVerticalGroup(
                                pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTriajeLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel24)
                                        .addComponent(lblPaciente)
                                        .addComponent(lblIDPreventa)
                                        .addComponent(lblIDHCTr))
                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlTriajeLayout.createSequentialGroup()
                                            .addGap(22, 22, 22)
                                            .addComponent(jLabel33))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTriajeLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblTraidoporTriaje)
                                                .addComponent(jLabel34)
                                                .addComponent(lblParentesco))))
                                    .addGap(15, 15, 15)
                                    .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFechaIM)
                                        .addComponent(lblFechaIng)
                                        .addComponent(lblHoraIM)
                                        .addComponent(lblHoraIng))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(76, 76, 76))
                            );

                            javax.swing.GroupLayout plTriajeLayout = new javax.swing.GroupLayout(plTriaje);
                            plTriaje.setLayout(plTriajeLayout);
                            plTriajeLayout.setHorizontalGroup(
                                plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(plTriajeLayout.createSequentialGroup()
                                    .addGroup(plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(pnlTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(plTriajeLayout.createSequentialGroup()
                                            .addGap(16, 16, 16)
                                            .addComponent(jLabel22)
                                            .addGap(88, 88, 88)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel37)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblIdTr, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
                            );
                            plTriajeLayout.setVerticalGroup(
                                plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(plTriajeLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(plTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel22)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblIdTr)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addComponent(pnlTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
                            );

                            tbPaneles.addTab("Triaje", plTriaje);

                            jPanel3.setBackground(new java.awt.Color(22, 22, 22));

                            fechaf.setBackground(new java.awt.Color(22, 22, 22));
                            fechaf.setForeground(new java.awt.Color(102, 102, 102));
                            fechaf.setDateFormatString("dd/MM/yyyy");
                            fechaf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                            fechai.setBackground(new java.awt.Color(22, 22, 22));
                            fechai.setForeground(new java.awt.Color(102, 102, 102));
                            fechai.setDateFormatString("dd/MM/yyyy");
                            fechai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

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

                            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                            jPanel3.setLayout(jPanel3Layout);
                            jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
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
                            jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(fechai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
                            );

                            jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                            tbTriaje = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false;
                                }
                            };
                            tbTriaje.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            tbTriaje.setForeground(new java.awt.Color(51, 51, 51));
                            tbTriaje.setModel(new javax.swing.table.DefaultTableModel(
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
                            tbTriaje.setSelectionBackground(new java.awt.Color(102, 102, 102));
                            tbTriaje.getTableHeader().setReorderingAllowed(false);
                            tbTriaje.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tbTriajeMouseClicked(evt);
                                }
                                public void mouseReleased(java.awt.event.MouseEvent evt) {
                                    tbTriajeMouseReleased(evt);
                                }
                            });
                            tbTriaje.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tbTriajeKeyPressed(evt);
                                }
                            });
                            jScrollPane4.setViewportView(tbTriaje);

                            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                            jPanel1.setLayout(jPanel1Layout);
                            jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
                            );
                            jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
                            );

                            tbPaneles.addTab("tab2", jPanel1);

                            jPanel10.setBackground(new java.awt.Color(43, 43, 43));
                            jPanel10.setPreferredSize(new java.awt.Dimension(929, 115));

                            jLabel36.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                            jLabel36.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel36.setText("Registro");

                            jLabel2.setForeground(new java.awt.Color(43, 43, 43));
                            jLabel2.setText("jLabel2");

                            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                            jPanel10.setLayout(jPanel10Layout);
                            jPanel10Layout.setHorizontalGroup(
                                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addGap(139, 139, 139)
                                            .addComponent(jLabel2)))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );
                            jPanel10Layout.setVerticalGroup(
                                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
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
                                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 1185, Short.MAX_VALUE)
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
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                            );

                            pack();
                        }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarTriActionPerformed
        filtrarDatos();
        cbxTipoBusqueda.requestFocus();
        lblPestana.setText("TR");
        tbPaciente.getSelectionModel().setSelectionInterval(0,0);
    }//GEN-LAST:event_btnFiltrarTriActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {

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
                        
                            AdmisionEmergenciaTriaje adEmerTr1 = new AdmisionEmergenciaTriaje();
                            AdmisionEmergenciaCabecera adEmerCab3 = new AdmisionEmergenciaCabecera();
                            
                            adEmerTr1.setTriaje_id("");
                            adEmerTr1.setTriaje_fv_pa(txtPA.getText());
                            adEmerTr1.setTriaje_fv_fc(txtFC.getText());
                            adEmerTr1.setTriaje_fv_t(txtT.getText());
                            adEmerTr1.setTriaje_fv_peso(txtPeso.getText());
                            adEmerTr1.setTriaje_fv_fr(txtFR.getText());
                            adEmerTr1.setTriaje_talla(txtTalla.getText());
                            adEmerTr1.setCod_usu(adEmerCab3.codUsuario(lblusu.getText()));
                            adEmerTr1.setModulo("EME");
                            adEmerTr1.setId_hc(lblIDPreventa.getText());
                            adEmerTr1.setIDM(txtIDM.getText());
                            if(adEmerTr1.mantenimientoAdmisionemergenciaTriaje("I")==true){
                                AdmisionEmergenciaTriaje adEmer = new AdmisionEmergenciaTriaje();
                                adEmer.triajeID();
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Guardados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=1;
                                //VISUALIZAR REPORTE !!!!!
                                
                                
                                AdmisionEmergenciaTriaje L1A = new AdmisionEmergenciaTriaje();
                                L1A.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, "","");
                                tbTriaje.getSelectionModel().setSelectionInterval(0, 0);
                                tbTriaje.requestFocus();
                                tbPaneles.setSelectedIndex(1);
                                adEmerCab.reporteTriaje(txtIDTriaje.getText());
                                pnlTriaje.setVisible(false);
                                btnFiltrarTri.setEnabled(false);
                                txtIDTriaje.setText("");
                                txtNHCTri.setEnabled(false);
                                txtNHCTri.setText("");
                                limpiarDatosTriaje();
                                btnGuardar.setEnabled(false);
                            }else{
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                            }
                                
                    
                    //}
                } else { // MODIFICAR REGISTRO DE TRIAJE
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(255,153,51)); 
                    Mensaje.setText("Desea Actualizar el Registro ?");
                    eli.setText("Si");
                    eli.setVisible(true);
                    noeli.setVisible(true);
                    tge=2;
//                    if(tga == 5){ // SELECCION SI
//                        
//                    }
                }
             
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
            tg=1;
            btnGuardar.setEnabled(false);
            btnEliminar.setEnabled(false);
            habilitarDatosTriaje();
            lblNewMod.setText("N");
            limpiarDatosTriaje();
            jLabel36.setText("Registro");
            tbPaneles.setSelectedIndex(0);
      
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

            lblNewMod.setText("M");
            enviarDatosPnlTriaje();
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
        jLabel36.setText("Listado");
        tbPaneles.setSelectedIndex(1);

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

    private void txtNHCTriCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNHCTriCaretUpdate
        if(lblNewMod.getText().equals("N")){
            if(txtNHCTri.getText().length()==8){
                adEmerCab.mostrarHCTriaje(formatoNHC(txtNHCTri.getText()));
  
                pnlTriaje.setVisible(true);
//                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(),"",tbFormatosEmer);
//                AdmisionEmergenciaTriaje ad = new AdmisionEmergenciaTriaje();
//                if(tbFormatosEmer.getRowCount()!=0){
//                    if(lblPestana.getText().equals("TR") || tbPaneles.getSelectedIndex()==1)
//                        lblPestanaMod.setText("TR");
//                    dlgModemergencia.setVisible(true);
//                    dlgModemergencia.setLocationRelativeTo(null);//en el centro
//                    dlgModemergencia.setResizable(false);
//                    dlgModemergencia.getContentPane().setBackground(Color.WHITE);
//                } else {
//                    JOptionPane.showMessageDialog(this,"No hay registros");
//                }
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
                pnlTriaje.setVisible(true);
//                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(),"",tbFormatosEmer);
//                adEmerTr.admisionEmergenciaTriajeListar(lblIDHCTr.getText(),"" , tbModifTriaje,"A");
//                if(tbModifTriaje.getRowCount()!=0){
//                    dlgModTriaje.setVisible(true);
//                    dlgModTriaje.setLocationRelativeTo(null);//en el centro
//                    dlgModTriaje.setResizable(false);
//                    dlgModTriaje.getContentPane().setBackground(Color.WHITE);
//                } else {
//                    dlgModTriaje.setVisible(false);
//                    JOptionPane.showMessageDialog(this,"No hay registros");
//                    txtNHCTri.setText("");
//                }
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
        
            if(jdcBusquedaFecha.getCalendar()!= null){
                String fecha = adEmerCab.determinarFecha(jdcBusquedaFecha);
                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(), fecha, tbFormatosEmer);
            } else {
                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(), "", tbFormatosEmer);
            }
        
        tbFormatosEmer.getSelectionModel().setSelectionInterval(0, 0);
        tbFormatosEmer.requestFocus();
    }//GEN-LAST:event_BTNBActionPerformed

    private void tbFormatosEmerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFormatosEmerKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
  
           if(teclaPresionada==KeyEvent.VK_ENTER){ 
            enviarDatosTbFormatEmergenciaTriaje();
        
        }
    }//GEN-LAST:event_tbFormatosEmerKeyPressed

    private void tbPanelesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPanelesMouseClicked
        
    }//GEN-LAST:event_tbPanelesMouseClicked

    private void tbPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacienteMouseClicked
      if(evt.getClickCount()==2){
         enviarDatosTbPaciente();
       }
    }//GEN-LAST:event_tbPacienteMouseClicked

    private void tbFormatosEmerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFormatosEmerMouseClicked
   
            if(evt.getClickCount()==2){
             enviarDatosTbFormatEmergenciaTriaje();
            }
        
    }//GEN-LAST:event_tbFormatosEmerMouseClicked

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

    private void txtIDTriajeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDTriajeCaretUpdate
        if(txtIDTriaje.getText().equals("")){
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);

        }else{ 
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_txtIDTriajeCaretUpdate

    private void tbDatosTopicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDatosTopicoKeyPressed
    
    }//GEN-LAST:event_tbDatosTopicoKeyPressed

    private void tbDatosTopicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosTopicoMouseClicked
    
    }//GEN-LAST:event_tbDatosTopicoMouseClicked

    private void btnAddExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddExamActionPerformed
 
    }//GEN-LAST:event_btnAddExamActionPerformed

    private void btnAddDiagPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiagPActionPerformed
  
    }//GEN-LAST:event_btnAddDiagPActionPerformed

    private void btnAddDiagFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiagFActionPerformed

    }//GEN-LAST:event_btnAddDiagFActionPerformed

    private void btnDelExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelExamActionPerformed

    }//GEN-LAST:event_btnDelExamActionPerformed

    private void btnDelExamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDelExamKeyPressed
        
    }//GEN-LAST:event_btnDelExamKeyPressed

    private void btnDelDiagPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDiagPActionPerformed
 
    }//GEN-LAST:event_btnDelDiagPActionPerformed

    private void btnDelDiagPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDelDiagPKeyPressed
        
    }//GEN-LAST:event_btnDelDiagPKeyPressed

    private void tbDiagPresunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagPresunKeyPressed
 
    }//GEN-LAST:event_tbDiagPresunKeyPressed

    private void tbExamenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbExamenesKeyPressed

    }//GEN-LAST:event_tbExamenesKeyPressed

    private void btnDelDiagFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDiagFActionPerformed
 
    }//GEN-LAST:event_btnDelDiagFActionPerformed

    private void tbDiagFinalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagFinalKeyPressed
 
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

    }//GEN-LAST:event_tb_Grupo4MouseClicked

    private void tb_Grupo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo4KeyPressed
 
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

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate


         adEmerTr1.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, determinarFecha(fechai),determinarFecha(fechaf));
     
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed

            if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
                tbTriaje.getSelectionModel().setSelectionInterval(0, 0);
                tbTriaje.requestFocus();
            }
        
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        ImageIcon continuar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/continuar.png"));
        ImageIcon detener=new ImageIcon(this.getClass().getResource("/imagenes/iconos/detener.png"));
        
     
            if(btnBuscar1.getText().equals("Iniciar")){
                if(fechai.getDate()==null || fechai.getDate()==null){
                    JOptionPane.showMessageDialog(this, "Ingrese un rango de fechas");
                }
                adEmerTr1.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, determinarFecha(fechai),determinarFecha(fechaf));
                btnBuscar1.setText("Detener");
                txtBuscar.setVisible(true);
                txtBuscar.requestFocus();
                btnBuscar1.setIcon(detener);
                txtBuscar.setEnabled(true);
            } else {
                adEmerTr1.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, "","");
                btnBuscar1.setText("Iniciar");
                txtBuscar.setVisible(false);
                btnBuscar1.setIcon(continuar);
                txtBuscar.setEnabled(false);
            }
     
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void tbTriajeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMouseReleased
        int fila = tbTriaje.getSelectedRow();
        if(fila == -1)
        JOptionPane.showMessageDialog(this, "Seleccione una fila");
        else{
            if(evt.isPopupTrigger()){
                jpmTriaje.show(evt.getComponent(),evt.getX(),evt.getY());
            }
        }
    }//GEN-LAST:event_tbTriajeMouseReleased

    private void tbTriajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriajeKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbTriaje.getSelectedRow()==0){
            txtBuscar.requestFocus();
        }
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER)
            enviarDatosPnlTriaje();
        
    }//GEN-LAST:event_tbTriajeKeyPressed

    private void mnuVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVisualizarActionPerformed
        //VISUALIZAR REPORTE DE TRIAJE!!!!!
        imprimirTriaje();
    }//GEN-LAST:event_mnuVisualizarActionPerformed

    private void mnuVisualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnuVisualizarKeyPressed

    }//GEN-LAST:event_mnuVisualizarKeyPressed

    private void txtPAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPAKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtFC.requestFocus();
        }
    }//GEN-LAST:event_txtPAKeyPressed

    private void txtFCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFCKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtPeso.requestFocus();
        }
    }//GEN-LAST:event_txtFCKeyPressed

    private void txtPesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtFR.requestFocus();
        }
    }//GEN-LAST:event_txtPesoKeyPressed

    private void txtFRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFRKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtT.requestFocus();
        }
    }//GEN-LAST:event_txtFRKeyPressed

    private void txtTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTMouseClicked
        
    }//GEN-LAST:event_txtTMouseClicked

    private void txtTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtTalla.requestFocus();
        }
    }//GEN-LAST:event_txtTKeyPressed

    private void tbTriajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMouseClicked
         if(evt.getClickCount()==2){
            enviarDatosPnlTriaje();
         }
         if(evt.getClickCount()==1){
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                int fila = tbTriaje.getSelectedRow();
                jLabel2.setText(String.valueOf(tbTriaje.getValueAt(fila, 0)));
            }
    }//GEN-LAST:event_tbTriajeMouseClicked

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==3 || tge==1 ||tge==9 ||tge==7||tge==9){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            tga=5;
            try {
            
            AdmisionEmergenciaTriaje adEmerTr1 = new AdmisionEmergenciaTriaje();
                        adEmerTr1.setTriaje_id(txtIDTriaje.getText());
                        adEmerTr1.setTriaje_fv_pa(txtPA.getText());
                        adEmerTr1.setTriaje_fv_fc(txtFC.getText());
                        adEmerTr1.setTriaje_fv_t(txtT.getText());
                        adEmerTr1.setTriaje_fv_peso(txtPeso.getText());
                        adEmerTr1.setTriaje_fv_fr(txtFR.getText());
                        adEmerTr1.setTriaje_talla(txtTalla.getText());
                        adEmerTr1.setIDM(txtIDM.getText());
                        if(adEmerTr1.mantenimientoAdmisionemergenciaTriaje("U")==true){
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Actualizados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=9;
                                pnlTriaje.setVisible(false);
                                btnFiltrarTri.setEnabled(false);
                                txtIDTriaje.setText("");
                                txtNHCTri.setEnabled(false);
                                txtNHCTri.setText("");
                                limpiarDatosTriaje();
                                btnGuardar.setEnabled(false);
                                AdmisionEmergenciaTriaje L1A = new AdmisionEmergenciaTriaje();
                                L1A.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, "","");
                                tbTriaje.getSelectionModel().setSelectionInterval(0, 0);
                                tbTriaje.requestFocus();
                                tbPaneles.setSelectedIndex(1);
                            }else{
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                        
                    }
                        } catch (Exception e) {
            }

        }
        if (tge==6){
            try { 
                AdmisionEmergenciaTriaje adEmerTrE = new AdmisionEmergenciaTriaje();
                adEmerTrE.setTriaje_id(jLabel2.getText());
                if(adEmerTrE.mantenimientoAdmisionemergenciaTriaje("E")){
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro eliminado de forma correcta");
                    eli.setText("OK");
                    eli.setVisible(true);
                    noeli.setVisible(false);
                    tge=7;
                    AdmisionEmergenciaTriaje L1A = new AdmisionEmergenciaTriaje();
                                L1A.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, "","");
                                tbTriaje.getSelectionModel().setSelectionInterval(0, 0);
                                tbTriaje.requestFocus();
                    pnlTriaje.setVisible(false);
                    txtNHCTri.setEnabled(false);
                    txtNHCTri.setText("");
                    btnEliminar.setEnabled(false);
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

    private void txtTallaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtIDM.requestFocus();
        }
    }//GEN-LAST:event_txtTallaKeyPressed
    
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
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaTriaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaTriaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaTriaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFormatoEmergenciaTriaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmFormatoEmergenciaTriaje().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ABONOS;
    private javax.swing.JDialog BHC;
    private javax.swing.JButton BTNB;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JButton btnAddDiagF;
    private javax.swing.JButton btnAddDiagP;
    private javax.swing.JButton btnAddExam;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscarDiagnostico;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBuscarTo;
    private javax.swing.JButton btnBuscarTriTop;
    private javax.swing.JButton btnDelDiagF;
    private javax.swing.JButton btnDelDiagP;
    private javax.swing.JButton btnDelExam;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrarTri;
    private javax.swing.JButton btnFiltrarTri1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnbuscar3;
    private javax.swing.JLabel bus;
    private javax.swing.JLabel bus3;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxTipoBusqueda;
    private javax.swing.JDialog dlgBuscarCPT;
    private javax.swing.JDialog dlgBuscarPac;
    private javax.swing.JDialog dlgListaExamnAux;
    private javax.swing.JDialog dlgListaImpDx;
    private javax.swing.JDialog dlgModemergencia;
    private javax.swing.JDialog dlgMostrarDatosTopico;
    private javax.swing.JDialog dlgMostrarDatosTriajeT;
    private javax.swing.JButton eli;
    private com.toedter.calendar.JDateChooser fechaf;
    private com.toedter.calendar.JDateChooser fechai;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JDateChooser jdcBusquedaFecha;
    private com.toedter.calendar.JDateChooser jdtBuscarTriTop;
    private com.toedter.calendar.JDateChooser jdtFechaTop;
    private javax.swing.JPopupMenu jpmTriaje;
    public static javax.swing.JLabel lblCabpT;
    private javax.swing.JLabel lblFechaIM;
    public static javax.swing.JLabel lblFechaIng;
    private javax.swing.JLabel lblHoraIM;
    public static javax.swing.JLabel lblHoraIng;
    public static javax.swing.JLabel lblIDHCTo;
    public static javax.swing.JLabel lblIDHCTr;
    public static javax.swing.JLabel lblIDPreventa;
    public static javax.swing.JLabel lblIdFP;
    public static javax.swing.JLabel lblIdPreventa;
    private javax.swing.JLabel lblIdTr;
    private javax.swing.JLabel lblNewMod;
    public static javax.swing.JLabel lblPaciente;
    public static javax.swing.JLabel lblParentesco;
    public static final javax.swing.JLabel lblPestana = new javax.swing.JLabel();
    public static javax.swing.JLabel lblPestanaMod;
    private javax.swing.JLabel lblTipo;
    public static javax.swing.JLabel lblTipoDiag;
    public static javax.swing.JLabel lblTraidoporTriaje;
    public static javax.swing.JLabel lblUsuario;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JMenuItem mnuVisualizar;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelBuscarHC;
    private javax.swing.JPanel panelSinHC;
    private javax.swing.JPanel paneltablaHC;
    private javax.swing.JPanel plTriaje;
    private javax.swing.JPanel pnlB;
    private javax.swing.JPanel pnlTriaje;
    private javax.swing.JTable tbDatosLab;
    private javax.swing.JTable tbDatosTopico;
    private javax.swing.JTable tbDiagFinal;
    private javax.swing.JTable tbDiagPresun;
    private javax.swing.JTable tbDiagnosticos;
    private javax.swing.JTable tbExamenes;
    private javax.swing.JTable tbFormatosEmer;
    private javax.swing.JTable tbMostrarTriajepT;
    private static javax.swing.JTable tbPaciente;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTabbedPane tbPaneles;
    public static javax.swing.JTable tbTriaje;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JTable tbpreventas;
    private javax.swing.JTable tbpreventasFR;
    public static javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtBuscarDiagnostico;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtBusquedaTo;
    public static javax.swing.JTextField txtFC;
    public static javax.swing.JTextField txtFR;
    public static javax.swing.JTextField txtIDM;
    public static javax.swing.JTextField txtIDTriaje;
    public static javax.swing.JTextField txtNHCTri;
    public static javax.swing.JTextField txtPA;
    public static javax.swing.JTextField txtPeso;
    public static javax.swing.JTextField txtT;
    public static javax.swing.JTextField txtTalla;
    // End of variables declaration//GEN-END:variables
}
