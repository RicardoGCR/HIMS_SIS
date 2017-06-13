/*
 * YAMILA ROCCA RUIZ
 */
package vista.admisionCentral;

import vista.admisionEmergencia.FrmFormatoEmergencia;
import tablas.FormatoTablaHC;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.management.openmbean.SimpleType;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.admisionCentral.HistoriaClinica;
import modelos.Usuario;
import vista.*;
import servicios.*;
//import static vista.RegistroUsuario.cbxPersonal;
import static vista.RegistroUsuario.txtCodigo;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.KeyStroke;
import static vista.Principal.fechaActual;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import java.text.SimpleDateFormat;
import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import java.lang.Object;
import tablas.*;
import vista.sectorizacion.FrmSector;
/*
 * @author Yamila Rocca Ruiz
 */
//ARRGLAR LA TABLA HISTORIA CLINICA
public class FrmNuevaHistoriaC extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
    Conexion c=new Conexion();
    ResultSet r;
    HistoriaClinica hC = new HistoriaClinica();
    DefaultTableModel m;
    PreparedStatement pstm;
    String estado = "A";
    /**
     * Creates new form FrmNuevaHistoriaC
     */
    public FrmNuevaHistoriaC() {
        initComponents();
        restringirCampos(8,txtDni);
        restringirCampos(10, txtFechaNac);
        restringirCampos(8, txtCodigo);
        h1 = new Thread(this);
        h1.start();
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);//en el centro
        cabecera(); // cabecera de tabla
        habilitarOpciones(false);
        habilitarBotones(true); //habilitar botones al cargar el formulario
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
        //Mostrar ID de Historia Clínica
        HistoriaClinica hC1 = new HistoriaClinica();
        txtID.setText(hC1.idHistoriaClinica());
        //Mostrar Codigo de Historia Clinica con GUION "-"
        mostrarNumHC();
        lblNumero.setVisible(false);
        lblLt.setVisible(false);
        txtNumero.setVisible(false);
        txtLote.setVisible(false);
        cbxSector.setEnabled(false);
        btnNuevo.setMnemonic(KeyEvent.VK_N);
        btnNuevo.setToolTipText("Nuevo (Alt+ N)");
        btnGuardar.setToolTipText("Guardar (Alt + G)");
        btnModificar.setToolTipText("Modificar (Alt + M)");
        btnEliminar.setToolTipText("Eliminar (Alt + E)");
        btnBuscar.setToolTipText("Buscar (Alt + B)");
        conexion = c.conectar();
        //CARGAR DEPARTAMENTOS , PROVINCIAS Y DISTRITOS - NACIMIENTO
        this.cbxDepartamentoNac.setModel(departamento());
        
        // CARGAR - ACTUAL
        this.cbxDepartamento.setModel(departamento());
        //ASIGNAR COLOR A CADA FILA SEGUN ESTADO
        tbHistoriaC.setDefaultRenderer(Object.class,new FormatoTablaHC());
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
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
        
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
    //Restringir campos al ingresar datos
    public void restringirCampos(int limite,javax.swing.JTextField campo){
        RestrictedTextField restricted = new RestrictedTextField(campo);
        restricted.setLimit(limite);
    }
    
    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    
  
    public DefaultComboBoxModel departamento(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("EXEC SP_ADMISION_HISTORIACLINICA_NOMBRE_DEPARTAMENTO"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "NOMBRE_DEPARTAMENTO" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public void limpiar(){
        txtID.setText(hC.idHistoriaClinica());
        txtDni.setText("");
        txtCodigo.setText(hC.codHistoriaClinica());
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtNombre3.setText("");
        txtApellidoPat.setText("");
        txtApellidoMat.setText("");
        txtFechaNac.setText("");
        txtEstado.setText("A");
        cbxDepartamento.setModel(departamento());
        cbxDepartamentoNac.setModel(departamento());
        cbxDistritoNac.removeAllItems();
        cbxProvinciaNac.removeAllItems();
        cbxDistritoNac.addItem("Seleccionar...");
        cbxProvinciaNac.addItem("Seleccionar...");
        txtNacionalidad.setText("");
        cbxTipoDireccion.setSelectedIndex(0);
        cbxDistrito.removeAllItems();
        cbxProvincia.removeAllItems();
        cbxTipoDireccion.removeAllItems();
        cbxDireccion.removeAllItems();
        cbxSector.removeAllItems();
        cbxDistrito.addItem("Seleccionar...");
        cbxProvincia.addItem("Seleccionar...");
        txtNumero.setText("");
        rbtMasculino.setSelected(true);
        cbxEstadoCivil.setSelectedIndex(0);
        txtReligion.setText("");
        txtGrupoSan.setText("");
        txtGradoIns.setText("");
        txtOcupacion.setText("");
        txtTelefono.setText("");
        txtCelular.setText("");
        txtEstado.enable(false);
        mostrarNumHC();
        txtCodigo.setText("");
        txtLote.setText("");
        txtDni.setBackground(new Color(255,255,255));
        txtNombre1.setBackground(new Color(255,255,255));
        txtApellidoPat.setBackground(new Color(255,255,255));
        txtApellidoMat.setBackground(new Color(255,255,255));
        txtFechaNac.setBackground(new Color(255,255,255));
        txtNumero.setBackground(new Color(255,255,255));
        txtRiesgo.setBackground(new Color(255,255,255));
        txtRiesgo.setText("");
        if(txtID.getText().equals("") && txtCodigo.getText().equals("")){
        }
    }
    
    public void habilitarOpciones(boolean opcion){
        txtDni.setEnabled(opcion);
        txtApellidoPat.setEnabled(opcion);
        txtApellidoMat.setEnabled(opcion);
        txtNombre1.setEnabled(opcion);
        txtNombre2.setEnabled(opcion);
        txtNombre3.setEnabled(opcion);
        txtFechaNac.setEnabled(opcion);
        txtEstado.setEnabled(opcion);
        txtNacionalidad.setEnabled(opcion);
        txtNumero.setEnabled(opcion);
        txtReligion.setEnabled(opcion);
        txtGrupoSan.setEnabled(opcion);
        txtGradoIns.setEnabled(opcion);
        txtCelular.setEnabled(opcion);
        txtTelefono.setEnabled(opcion);
        txtOcupacion.setEnabled(opcion);
        txtRiesgo.setEnabled(opcion);
        cbxEstadoCivil.setEnabled(opcion);
        cbxTipoDireccion.setEnabled(opcion);
        cbxDepartamentoNac.setEnabled(opcion);
        cbxDepartamento.setEnabled(opcion);
        cbxProvinciaNac.setEnabled(opcion);
        cbxProvincia.setEnabled(opcion);
        cbxDistritoNac.setEnabled(opcion);
        cbxDireccion.setEnabled(opcion);
        cbxDistrito.setEnabled(opcion);
        txtCodigo.setEnabled(opcion);
        txtNumero.setEnabled(opcion);
        txtLote.setEnabled(opcion);
        rbtMasculino.setEnabled(opcion);
        rbtFemenino.setEnabled(opcion);
    }
    
    public void habilitarBotones(boolean opcion){
        btnNuevo.setEnabled(opcion);
        btnGuardar.setEnabled(!opcion);
        btnModificar.setEnabled(!opcion);
        btnEliminar.setEnabled(!opcion);
        btnBuscar.setEnabled(opcion);
    }
    
    public void cabecera(){
        String titulos[]={"N°","ID","DNI","Apellido Pat","Apellido Mat","1er nombre",
                              "2do Nombre","3er Nombre","Fecha de Nac","S","Departamento Act","Provincia Act",
                              "Distrito Act","Codigo Distrito","Sector",
                              "Tipo Dirección","Dirección","# o Mz","Lt","Código Distrito Nac","Distrito nac",
                              "Provincia Nac","Departmento Nac","Ocupación","Estado civil","Grupo de Sangre",
                              "Religión","Teléfono","Celular","Grado de Instrucción",
                              "Nacionalidad","Fecha de creación","Hora de creación","Usuario","PC de creación",
                              "Estado","Última atención","Estado de HC","Edad"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                tbHistoriaC.setModel(m);
                TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbHistoriaC.setRowSorter(elQueOrdena);
            this.tbHistoriaC.setModel(m);
    }
    
    public void formatoHistoriaClinica(){
            tbHistoriaC.getColumnModel().getColumn(1).setPreferredWidth(160);
            tbHistoriaC.getColumnModel().getColumn(0).setPreferredWidth(65);
            tbHistoriaC.getColumnModel().getColumn(2).setPreferredWidth(70);
            tbHistoriaC.getColumnModel().getColumn(3).setPreferredWidth(80);
            tbHistoriaC.getColumnModel().getColumn(4).setPreferredWidth(80);
            tbHistoriaC.getColumnModel().getColumn(5).setPreferredWidth(80);
            tbHistoriaC.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(9).setPreferredWidth(50);
            tbHistoriaC.getColumnModel().getColumn(10).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(11).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(12).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(13).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(14).setPreferredWidth(160);
            tbHistoriaC.getColumnModel().getColumn(15).setPreferredWidth(200);
            tbHistoriaC.getColumnModel().getColumn(16).setPreferredWidth(150);
            tbHistoriaC.getColumnModel().getColumn(17).setPreferredWidth(120);
            tbHistoriaC.getColumnModel().getColumn(18).setPreferredWidth(140);
            tbHistoriaC.getColumnModel().getColumn(19).setPreferredWidth(90);
            tbHistoriaC.getColumnModel().getColumn(20).setPreferredWidth(90);
            tbHistoriaC.getColumnModel().getColumn(21).setPreferredWidth(90);
            tbHistoriaC.getColumnModel().getColumn(22).setPreferredWidth(90);
            tbHistoriaC.getColumnModel().getColumn(23).setPreferredWidth(90);
            tbHistoriaC.getColumnModel().getColumn(24).setPreferredWidth(140);
            tbHistoriaC.getColumnModel().getColumn(25).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(26).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(27).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(28).setPreferredWidth(100);
            tbHistoriaC.getColumnModel().getColumn(29).setPreferredWidth(100);
            tbHistoriaC.setRowHeight(30);
    }
    
    public void buscar_HC(int index, String opcion, String descripcion){
    String consulta="";
        try {
            tbHistoriaC.setModel(new DefaultTableModel());
            String titulos[]={"N°","ID","DNI","Apellido Pat","Apellido Mat","1er nombre",
                              "2do Nombre","3er Nombre","Fecha de Nac","S","Departamento Act","Provincia Act",
                              "Distrito Act","Codigo Distrito","Sector",
                              "Tipo Dirección","Dirección","# o Mz","Lt","Código Distrito Nac","Distrito nac",
                              "Provincia Nac","Departmento Nac","Ocupación","Estado civil","Grupo de Sangre",
                              "Religión","Teléfono","Celular","Grado de Instrucción",
                              "Nacionalidad","Fecha de creación","Hora de creación","Usuario","PC de creación",
                              "Estado","Edad","Riesgo","Ultima atención"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[39];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC SP_HC_METODO_BUSQUEDA ?,?,?";
            PreparedStatement cmd = hC.getCn().prepareStatement(consulta);
            cmd.setInt(1, index);
            cmd.setString(2, descripcion);
            cmd.setString(3, opcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(3); // id de hc
                fila[1]=r.getString(1); // codigo de hc
                fila[2]=r.getString(2); // dni
                fila[3]=r.getString(4); // apellido pat
                fila[4]=r.getString(5); // apellido mat
                fila[5]=r.getString(6); // 1er nombre
                fila[6]=r.getString(7); // 2do nombre
                fila[7]=r.getString(8); // 3er nombre
                fila[8]=r.getString(9); // fecha de nacimiento
                fila[9]=r.getString(10); // genero
                fila[10]=r.getString(11); // nombre departamento actual
                fila[11]=r.getString(12); // nombre provincia actual
                fila[12]=r.getString(13); // nombre provincia actual
                fila[13]=r.getString(14); // codigo distrito actual
                fila[14]=r.getString(35); //sector
                fila[15]=r.getString(15); // tipo dir
                fila[16]=r.getString(16); // direccion
                fila[17]=r.getString(36); // num o manzana
                fila[18]=r.getString(37); // lote
                fila[19]=r.getString(17); // codigo distrito nacimiento
                fila[20]=r.getString(18); // distrito nacimiento
                fila[21]=r.getString(19); //provincia nacimiento
                fila[22]=r.getString(20); // departamento nacimiento
                fila[23]=r.getString(21); // ocupacion
                fila[24]=r.getString(22); //estado civil
                fila[25]=r.getString(23); // grupo de sangre
                fila[26]=r.getString(24); //religion
                fila[27]=r.getString(25); //telfono
                fila[28]=r.getString(26); //celular
                fila[29]=r.getString(27); //grado de instruccion
                fila[30]=r.getString(28); // nacionalidad
                fila[31]=r.getString(29); //fecha de creacion
                fila[32]=r.getString(30); //hora de creacion
                fila[33]=r.getString(31);// usuario de creacion
                fila[34]=r.getString(32); // nombre de la pc donde se registro
                fila[35]=r.getString(33); //estado de la hc activo, pasivo, pendiente
                fila[36]=r.getString(34); //ultima atencion
                fila[37]=r.getString(38); // riesgo
                fila[38]=r.getString(39); // riesgo
                    m.addRow(fila);
                    c++;
            }
            tbHistoriaC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbHistoriaC.setRowSorter(elQueOrdena);
            this.tbHistoriaC.setModel(m);
            formatoHistoriaClinica();
        } catch (Exception e) {
            System.out.println("Error_buscar_HC: " + e.getMessage());
        }
    }
    
    public void mostrarNumHC(){
        char c1 = hC.codHistoriaClinica().charAt(0);
        char c2 = hC.codHistoriaClinica().charAt(1);
        char c3 = hC.codHistoriaClinica().charAt(2);
        char c4 = hC.codHistoriaClinica().charAt(3);
        char c5 = hC.codHistoriaClinica().charAt(4);
        char c6 = hC.codHistoriaClinica().charAt(5);
        char c7 = hC.codHistoriaClinica().charAt(6);
        txtCodigo.setText(String.valueOf(c1)+String.valueOf(c2)+String.valueOf(c3)+String.valueOf(c4)+String.valueOf(c5)
                + "-" + String.valueOf(c6)+String.valueOf(c7));
    }
    
    public void btnGuardar(){
        try {
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Guardar-32.png")); 
        String codigo = String.valueOf(txtCodigo.getText().charAt(0)) + String.valueOf(txtCodigo.getText().charAt(1)) 
                + String.valueOf(txtCodigo.getText().charAt(2)) + String.valueOf(txtCodigo.getText().charAt(3)) + String.valueOf(txtCodigo.getText().charAt(4)) + String.valueOf(txtCodigo.getText().charAt(6) + String.valueOf(txtCodigo.getText().charAt(7))) ;
//        if(txtID.getText().equalsIgnoreCase(hC.idHistoriaClinica())){
            if(txtDni.getText().equals("") || txtNombre1.getText().equals("") || txtApellidoPat.getText().equals("") ||
                   txtApellidoPat.getText().equals("") || txtFechaNac.getText().equals("") ||
                         cbxDepartamento.getSelectedIndex()==0 || cbxDepartamentoNac.getSelectedIndex() == 0 
                        || cbxProvincia.getSelectedIndex() == 0 || cbxProvinciaNac.getSelectedIndex()==0 || cbxDistrito.getSelectedIndex() == 0
                        || cbxDistritoNac.getSelectedIndex()==0 || txtRiesgo.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Debe ingresar los siguientes campos \n\t\t + lugar de nacimiento y actual");
                    txtDni.setBackground(new Color(51,204,255));
                    txtNombre1.setBackground(new Color(51,204,255));
                    txtApellidoPat.setBackground(new Color(51,204,255));
                    txtApellidoMat.setBackground(new Color(51,204,255));
                    txtFechaNac.setBackground(new Color(51,204,255));
                    txtNumero.setBackground(new Color(51,204,255));
                    txtRiesgo.setBackground(new Color(51,204,255));
                    cbxDistrito.setEnabled(false);
                    cbxDistrito.setEnabled(false);
                    cbxDistritoNac.setEnabled(false);
                    cbxDistritoNac.setEnabled(false);
                    //txtCodigo.setEnabled(true);
                } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(guardar == 0 ){
                    HistoriaClinica hC3 = new HistoriaClinica();
                    hC3.setId_hc(txtID.getText());
                    hC3.setCod_hc(codigo);
                    hC3.setDni(txtDni.getText());
                    hC3.setApe_pat(txtApellidoPat.getText().toUpperCase());
                    hC3.setApe_mat(txtApellidoMat.getText().toUpperCase());
                    hC3.setNombre1(txtNombre1.getText().toUpperCase());
                    hC3.setNombre2(txtNombre2.getText().toUpperCase());
                    hC3.setNombre3(txtNombre3.getText().toUpperCase());
                    hC3.setFec_nac(txtFechaNac.getText());
                    //GENERO
                    if(rbtMasculino.isSelected()){
                        hC3.setSexo("M");
                    } 
                    if(rbtFemenino.isSelected()){
                        hC3.setSexo("F");
                    }
                    //ACTUAL
                    hC3.setDep_act(cbxDepartamento.getSelectedItem().toString());
                    hC3.setPro_act(cbxProvincia.getSelectedItem().toString());
                    hC3.setDis_act(cbxDistrito.getSelectedItem().toString());
                    // CODIGO DEL DISTRITO 
                    hC3.setCod_dis(hC.codDistrito(cbxDistrito.getSelectedItem().toString()));
                    // CODIGO DISTRITO NAC
                    hC3.setCod_dis_nac(hC.codDistrito(cbxDistritoNac.getSelectedItem().toString()));
                    // NOMBRE DISTRITO, PROVINCIA, DEPARTAMENTO
                    hC3.setDis_nac(cbxDistritoNac.getSelectedItem().toString()); 
                    hC3.setPro_nac(cbxProvinciaNac.getSelectedItem().toString());
                    hC3.setDep_nac(cbxDepartamentoNac.getSelectedItem().toString());
                    hC3.setOcupacion(txtOcupacion.getText().toUpperCase());
                    hC3.setEstado_civil(cbxEstadoCivil.getSelectedItem().toString().toUpperCase());
                    hC3.setGrupo_sang(txtGrupoSan.getText().toUpperCase());
                    hC3.setReligion(txtReligion.getText().toUpperCase());
                    hC3.setTelefono(txtTelefono.getText());
                    hC3.setCelular(txtCelular.getText());
                    hC3.setGrado_inst(txtGradoIns.getText().toUpperCase());
                    hC3.setNacionalidad(txtNacionalidad.getText().toUpperCase());
                    hC3.setNom_usu(lblUsuUsuario.getText().toUpperCase());
                    hC3.setRiesgo(txtRiesgo.getText().toUpperCase());
                    if(!cbxProvincia.getSelectedItem().toString().equals("CHINCHA")){
                        hC3.setSe_cod("Turista");
                        hC3.setTipo_dir_nom("Turista");
                        hC3.setNom_dir("Turista");
                        hC3.setNum("");
                    } else {
                    hC3.setSe_cod(cbxSector.getSelectedItem().toString());
                    //TIPO_DIR_NOM
                    hC3.setTipo_dir_nom(cbxTipoDireccion.getSelectedItem().toString().toUpperCase());
                    hC3.setNom_dir(cbxDireccion.getSelectedItem().toString().toUpperCase());
                    hC3.setNum(txtNumero.getText().toUpperCase());
                    }
                    hC3.setLote(txtLote.getText().toUpperCase());
                    //REGISTRAR HISTORIA CLINICA
                    if(hC3.nuevaHistoriaClinica()==true){
                            JOptionPane.showMessageDialog(this, "Datos Guardados");
                            String cod_hc = txtCodigo.getText();
                            String codigo3 = String.valueOf(cod_hc.charAt(0)) + String.valueOf(cod_hc.charAt(1)) + 
                                             String.valueOf(cod_hc.charAt(2)) + String.valueOf(cod_hc.charAt(3)) +
                                             String.valueOf(cod_hc.charAt(4)) + String.valueOf(cod_hc.charAt(6)) + 
                                             String.valueOf(cod_hc.charAt(7));
                            String rutaInforme = "src\\Reportes\\admisionCentral\\historiaClinica.jasper";
                            Map parametros = new HashMap();
                            parametros.put("cod_hc", txtID.getText());
                            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, c.conectar());
                            JasperViewer ventanavisor = new JasperViewer(informe, false);
                            ventanavisor.setTitle("Historia Clínica");
                            ventanavisor.setVisible(true);
                            
                            //String datos = codigo3 + "_" + txtApellidoPat.getText() + txtApellidoMat.getText() + txtNombre1.getText(); 
                            /*JasperExportManager.exportReportToPdfFile(informe,"D:\\Historias-Clinicas\\"+datos+".pdf");
                            String file = new String("D:\\Historias-Clinicas\\"+datos+".pdf");
                            //definiendo la ruta en la propiedad file
                            //Visualizar el PDF
                            JOptionPane.showMessageDialog(this, "Exportando...");
                            Runtime.getRuntime().exec("cmd /c start "+file);    */
                            limpiar();
                            habilitarBotones(true);
                            habilitarOpciones(false);
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al modificar");
                       }
                    limpiar();
                    btnGuardar.setEnabled(false);
                    habilitarOpciones(false);
                    lblNumero.setVisible(false);
                    lblLt.setVisible(false);
                    txtNumero.setVisible(false);
                    txtLote.setVisible(false);
                    }
                }
//            }   
    }catch (Exception e) {
            System.out.println("Error_btnGuardar " + e.getMessage());
        }  
    }
    
    public void mostrarCodHC(String codigo){
    String consulta="";
        try {
            tbReasignado.setModel(new DefaultTableModel());
            String titulos[]={"N°","DNI","Apellidos y Nombres"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC SP_ADMISION_HISTORIACLINICABUSCAR_CODHC_REASIGNADO ?";
            PreparedStatement cmd = hC.getCn().prepareStatement(consulta);
            cmd.setString(1, codigo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // codigo de hc
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // apeellidos y nombres
                    m.addRow(fila);
                    c++;
            }
            tbReasignado.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbReasignado.setRowSorter(elQueOrdena);
            this.tbReasignado.setModel(m);
            tbReasignado.getColumnModel().getColumn(0).setPreferredWidth(15);
            tbReasignado.getColumnModel().getColumn(1).setPreferredWidth(30);
            tbReasignado.getColumnModel().getColumn(2).setPreferredWidth(130);
            tbReasignado.setRowHeight(25);
        } catch (Exception e) {
            System.out.println("Error_mostrarCodHC: " + e.getMessage());
        }
    }
    
    public void validaCampo() {
        String codigo;
        boolean existe = false; //variable bandera para comprobar si NO existe el expediente en la BD
        
        try{            
            pstm = hC.getCn().prepareStatement("SELECT TOP 1 COD_HC FROM ADMISION_HISTORIA_CLINICA WHERE estado_hc = 'R'");            
            r = pstm.executeQuery();                         
            while(r.next()){
                existe = true;
                if(existe == true){
                    codigo = r.getString("COD_HC");               
                    if ( txtCodigo.getText().equals(codigo) ){                
                        JOptionPane.showMessageDialog(ReasignarHC, "El código si está en la BD ", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
                }    
            }
            r.close();   
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
          }
    }
    
    public boolean validaDNI(String dni) {
        String codigo;
        boolean existe = false; //variable bandera para comprobar si NO existe el expediente en la BD
        
        try{            
            pstm = hC.getCn().prepareStatement("SELECT cod_hc, dni\n" +
                                                "FROM ADMISION_HISTORIA_CLINICA\n" +
                                                "WHERE dni = '"+dni+"'");            
            r = pstm.executeQuery();                         
            while(r.next()){
                existe = true;
                if(existe == true){
                    codigo = r.getString("COD_HC");   
                    String codigo2 = String.valueOf(codigo.charAt(0)) + String.valueOf(codigo.charAt(1)) + 
                                     String.valueOf(codigo.charAt(2)) + String.valueOf(codigo.charAt(3)) + String.valueOf(codigo.charAt(4)) + "-" + 
                                     String.valueOf(codigo.charAt(6)) + String.valueOf(codigo.charAt(7));
                    dni = r.getString("dni");
                    if ( txtDni.getText().equals(dni) ){                
                        JOptionPane.showMessageDialog(ReasignarHC, "Este número de DNI ya existe en la \n"
                                + "           Base de Datos\n "
                                + "   Historia Clínica N° " + codigo2, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
                }    
            }
            r.close();   
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
          }
        return existe;
    }
    
    public void enviarDatos(){
        int fila = tbHistoriaC.getSelectedRow();
            FrmNuevaHistoriaC nuevaHC = new FrmNuevaHistoriaC();
            nuevaHC.setVisible(true);
            BuscarHC.setVisible(false);
            dispose();
            if(String.valueOf(tbHistoriaC.getValueAt(fila, 35)).equals("D")){
                btnEliminar.setText("Restaurar");
                btnModificar.setEnabled(false);
                btnEliminar.setToolTipText("Restaurar (Alt + R)");
                btnEliminar.setMnemonic(KeyEvent.VK_R);
                ImageIcon iRestaurar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Restaurar-32.png")); 
                btnEliminar.setIcon(iRestaurar);
            } else {
                btnModificar.setEnabled(true);
            }
            FrmNuevaHistoriaC.txtID.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 1)));  
            FrmNuevaHistoriaC.txtCodigo.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 0))); 
            FrmNuevaHistoriaC.txtDni.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 2)));     
            FrmNuevaHistoriaC.txtApellidoPat.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 3)));   
            FrmNuevaHistoriaC.txtApellidoMat.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 4)));   
            FrmNuevaHistoriaC.txtNombre1.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 5)));   
            FrmNuevaHistoriaC.txtNombre2.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 6)));   
            FrmNuevaHistoriaC.txtNombre3.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 7)));   
            FrmNuevaHistoriaC.txtFechaNac.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 8)));   
            //FrmNuevaHistoriaC.txtEstado.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 37)));  
            FrmNuevaHistoriaC.cbxDepartamentoNac.setSelectedItem(tbHistoriaC.getValueAt(fila, 22));
            FrmNuevaHistoriaC.cbxProvinciaNac.setSelectedItem(String.valueOf(tbHistoriaC.getValueAt(fila, 21)));
            FrmNuevaHistoriaC.cbxDistritoNac.setSelectedItem(String.valueOf(tbHistoriaC.getValueAt(fila, 20)));
            FrmNuevaHistoriaC.cbxDepartamento.setSelectedItem(tbHistoriaC.getValueAt(fila, 10));
            FrmNuevaHistoriaC.cbxProvincia.setSelectedItem(tbHistoriaC.getValueAt(fila, 11));
            FrmNuevaHistoriaC.cbxDistrito.setSelectedItem(tbHistoriaC.getValueAt(fila, 12));
            FrmNuevaHistoriaC.txtNacionalidad.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 27))); 
            FrmNuevaHistoriaC.cbxSector.addItem(tbHistoriaC.getValueAt(fila, 14).toString()); 
            cbxTipoDireccion.removeAllItems();
            FrmNuevaHistoriaC.cbxTipoDireccion.addItem(tbHistoriaC.getValueAt(fila, 15).toString());
            cbxDireccion.removeAllItems();
            FrmNuevaHistoriaC.cbxDireccion.addItem(tbHistoriaC.getValueAt(fila, 16).toString()); 
            FrmNuevaHistoriaC.txtNumero.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 17)));   
            FrmNuevaHistoriaC.txtLote.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 18)));   
            FrmNuevaHistoriaC.txtRiesgo.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 37))); 
            if(tbHistoriaC.getValueAt(fila, 9).equals("F")){
                rbtFemenino.setSelected(true);
            } else {
                rbtMasculino.setSelected(true);
            }
            FrmNuevaHistoriaC.cbxEstadoCivil.setSelectedItem(tbHistoriaC.getValueAt(fila, 21));
            FrmNuevaHistoriaC.txtReligion.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 26))); 
            FrmNuevaHistoriaC.txtGrupoSan.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 25))); 
            FrmNuevaHistoriaC.txtGradoIns.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 26))); 
            FrmNuevaHistoriaC.txtOcupacion.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 23))); 
            FrmNuevaHistoriaC.txtTelefono.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 27))); 
            FrmNuevaHistoriaC.txtCelular.setText(String.valueOf(tbHistoriaC.getValueAt(fila, 25))); 
            
            
            habilitarOpciones(false);
            btnEliminar.setEnabled(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        BuscarHC = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        cbxTipoBusqueda = new javax.swing.JComboBox<String>();
        txtBuscar = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        chkA = new javax.swing.JRadioButton();
        chkT = new javax.swing.JRadioButton();
        chkD = new javax.swing.JRadioButton();
        btnImprimir = new javax.swing.JButton();
        btnBuscarHc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHistoriaC = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        titulo6 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lblUsuUsuario1 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        ReasignarHC = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        txtBuscarCodigoHC = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbReasignado = new javax.swing.JTable();
        btnReasignar = new javax.swing.JButton();
        btnAnadirRe = new javax.swing.JButton();
        lblCodigoR = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtApellidoPat = new javax.swing.JTextField();
        txtApellidoMat = new javax.swing.JTextField();
        txtNombre1 = new javax.swing.JTextField();
        txtNombre2 = new javax.swing.JTextField();
        txtNombre3 = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtFechaNac = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        rbtMasculino = new javax.swing.JRadioButton();
        rbtFemenino = new javax.swing.JRadioButton();
        btnAsignarDistrito = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbxDepartamentoNac = new javax.swing.JComboBox<String>();
        cbxProvinciaNac = new javax.swing.JComboBox<String>();
        cbxDistritoNac = new javax.swing.JComboBox<String>();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbxDepartamento = new javax.swing.JComboBox<String>();
        cbxProvincia = new javax.swing.JComboBox<String>();
        cbxDistrito = new javax.swing.JComboBox<String>();
        jLabel24 = new javax.swing.JLabel();
        cbxTipoDireccion = new javax.swing.JComboBox<String>();
        jLabel26 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        cbxSector = new javax.swing.JComboBox<String>();
        cbxDireccion = new javax.swing.JComboBox<String>();
        lblNumero = new javax.swing.JLabel();
        lblLt = new javax.swing.JLabel();
        txtLote = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cbxEstadoCivil = new javax.swing.JComboBox<String>();
        txtReligion = new javax.swing.JTextField();
        txtGrupoSan = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtOcupacion = new javax.swing.JTextField();
        txtGradoIns = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        txtHuella = new javax.swing.JTextField();
        txtRiesgo = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();

        BuscarHC.setMinimumSize(new java.awt.Dimension(1300, 700));
        BuscarHC.setResizable(false);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel37.setText("Búsqueda por");

        cbxTipoBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbxTipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "N° de Historia Clínica", "DNI", "Apellidos", "Nombres", "Dirección", "Sector" }));
        cbxTipoBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoBusquedaItemStateChanged(evt);
            }
        });
        cbxTipoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoBusquedaActionPerformed(evt);
            }
        });
        cbxTipoBusqueda.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cbxTipoBusquedaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));
        txtBuscar.setEnabled(false);
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

        jLabel38.setText("Mostrar");

        buttonGroup2.add(chkA);
        chkA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkA.setMnemonic('A');
        chkA.setText("Activo(A)");
        chkA.setEnabled(false);
        chkA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkAItemStateChanged(evt);
            }
        });
        chkA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAActionPerformed(evt);
            }
        });

        buttonGroup2.add(chkT);
        chkT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkT.setMnemonic('T');
        chkT.setText("Todos");
        chkT.setEnabled(false);
        chkT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTItemStateChanged(evt);
            }
        });
        chkT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTActionPerformed(evt);
            }
        });

        buttonGroup2.add(chkD);
        chkD.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkD.setMnemonic('D');
        chkD.setText("Inactivo(D)");
        chkD.setEnabled(false);
        chkD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkDItemStateChanged(evt);
            }
        });
        chkD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pdf.png"))); // NOI18N
        btnImprimir.setMnemonic('P');
        btnImprimir.setText("Imprimir");
        btnImprimir.setBorderPainted(false);
        btnImprimir.setContentAreaFilled(false);
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setEnabled(false);
        btnImprimir.setFocusPainted(false);
        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImprimir.setIconTextGap(2);
        btnImprimir.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnBuscarHc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
        btnBuscarHc.setBorderPainted(false);
        btnBuscarHc.setContentAreaFilled(false);
        btnBuscarHc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarHc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarHcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(cbxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarHc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarHc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37)
                        .addComponent(cbxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel38)
                        .addComponent(chkA)
                        .addComponent(chkT)
                        .addComponent(chkD)
                        .addComponent(btnImprimir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbHistoriaC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbHistoriaC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbHistoriaC.setForeground(new java.awt.Color(102, 102, 102));
        tbHistoriaC.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHistoriaC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbHistoriaC.setSelectionBackground(new java.awt.Color(101, 166, 136));
        tbHistoriaC.getTableHeader().setReorderingAllowed(false);
        tbHistoriaC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHistoriaCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbHistoriaCMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbHistoriaCMousePressed(evt);
            }
        });
        tbHistoriaC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbHistoriaCKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbHistoriaC);

        jPanel12.setBackground(new java.awt.Color(101, 166, 136));
        jPanel12.setPreferredSize(new java.awt.Dimension(500, 65));

        titulo6.setBackground(new java.awt.Color(0, 102, 102));
        titulo6.setFont(new java.awt.Font("Segoe UI Light", 0, 40)); // NOI18N
        titulo6.setForeground(new java.awt.Color(255, 255, 255));
        titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo6.setText("Búsqueda de Historias Clínicas");
        titulo6.setToolTipText("");
        titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(titulo6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(titulo6))
        );

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText("Todos (Alt + T)");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText("Activo (Alt + A)");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText("Inactivo (Alt + D)");

        lblUsuUsuario1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario1.setForeground(new java.awt.Color(51, 51, 51));
        lblUsuUsuario1.setText("Nombre");
        lblUsuUsuario1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel52.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Usuario");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addGap(20, 20, 20)
                .addComponent(jLabel48)
                .addGap(20, 20, 20)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel46)
                    .addComponent(jLabel48)
                    .addComponent(lblUsuUsuario1)
                    .addComponent(jLabel52)))
        );

        javax.swing.GroupLayout BuscarHCLayout = new javax.swing.GroupLayout(BuscarHC.getContentPane());
        BuscarHC.getContentPane().setLayout(BuscarHCLayout);
        BuscarHCLayout.setHorizontalGroup(
            BuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarHCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarHCLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(BuscarHCLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(BuscarHCLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35))))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        BuscarHCLayout.setVerticalGroup(
            BuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarHCLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        ReasignarHC.setMinimumSize(new java.awt.Dimension(455, 650));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Seleccione el N° de Historia Clínica a reasignar");

        jPanel11.setBackground(new java.awt.Color(101, 166, 136));

        jLabel39.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Reasignado");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        txtBuscarCodigoHC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuscarCodigoHC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarCodigoHCCaretUpdate(evt);
            }
        });

        tbReasignado = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbReasignado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbReasignado.setForeground(new java.awt.Color(102, 102, 102));
        tbReasignado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tbReasignado.setGridColor(new java.awt.Color(255, 255, 255));
        tbReasignado.setSelectionBackground(new java.awt.Color(101, 166, 136));
        tbReasignado.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbReasignado);

        btnReasignar.setBackground(new java.awt.Color(51, 204, 255));
        btnReasignar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnReasignar.setText("Reasignar");
        btnReasignar.setBorderPainted(false);
        btnReasignar.setContentAreaFilled(false);
        btnReasignar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReasignar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnReasignarItemStateChanged(evt);
            }
        });
        btnReasignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReasignarActionPerformed(evt);
            }
        });

        btnAnadirRe.setBackground(new java.awt.Color(255, 255, 255));
        btnAnadirRe.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnAnadirRe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
        btnAnadirRe.setText("Añadir");
        btnAnadirRe.setBorderPainted(false);
        btnAnadirRe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnadirRe.setFocusPainted(false);
        btnAnadirRe.setFocusable(false);
        btnAnadirRe.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnadirRe.setInheritsPopupMenu(true);
        btnAnadirRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirReActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReasignarHCLayout = new javax.swing.GroupLayout(ReasignarHC.getContentPane());
        ReasignarHC.getContentPane().setLayout(ReasignarHCLayout);
        ReasignarHCLayout.setHorizontalGroup(
            ReasignarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ReasignarHCLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(ReasignarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReasignarHCLayout.createSequentialGroup()
                        .addGroup(ReasignarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReasignarHCLayout.createSequentialGroup()
                                .addComponent(txtBuscarCodigoHC, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReasignar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAnadirRe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ReasignarHCLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCodigoR, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        ReasignarHCLayout.setVerticalGroup(
            ReasignarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReasignarHCLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ReasignarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReasignarHCLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(ReasignarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCodigoR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ReasignarHCLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(ReasignarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarCodigoHC, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnReasignar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnadirRe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admisión .::. Historia Clìnica");
        setMinimumSize(new java.awt.Dimension(855, 722));
        setResizable(false);

        jPanel8.setBackground(new java.awt.Color(101, 166, 136));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));

        titulo5.setBackground(new java.awt.Color(0, 102, 102));
        titulo5.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo5.setText("Historia Clínica");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Fecha:");

        lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("00/00/00");

        jLabel15.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Hora:");

        lblHora.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("00:00:00");

        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setText("Nuevo");
        btnNuevo.setToolTipText("");
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

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardar.setMnemonic('G');
        btnGuardar.setText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(240, 240, 240));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificar.setMnemonic('M');
        btnModificar.setText("Modificar");
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(240, 240, 240));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscar.setMnemonic('B');
        btnBuscar.setText("Buscar");
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Nombre");
        lblUsuUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHora))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(lblFecha)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lblUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(titulo5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar)
                            .addComponent(btnBuscar)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHora)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(lblUsuUsuario))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("ID Historia Clínica");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtID.setEnabled(false);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("N° de Historia Clínica");

        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(102, 102, 102));
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Paciente"));
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Apellido Paterno");

        jLabel4.setText("Apellido Materno");

        jLabel5.setText("Primer Nombre");

        jLabel6.setText("Segundo Nombre");

        jLabel7.setText("Tercer Nombre");

        txtApellidoPat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtApellidoPat.setForeground(new java.awt.Color(102, 102, 102));
        txtApellidoPat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidoPatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoPatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoPatKeyTyped(evt);
            }
        });

        txtApellidoMat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtApellidoMat.setForeground(new java.awt.Color(102, 102, 102));
        txtApellidoMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoMatActionPerformed(evt);
            }
        });
        txtApellidoMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidoMatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoMatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoMatKeyTyped(evt);
            }
        });

        txtNombre1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNombre1.setForeground(new java.awt.Color(102, 102, 102));
        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });
        txtNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombre1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombre1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre1KeyTyped(evt);
            }
        });

        txtNombre2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNombre2.setForeground(new java.awt.Color(102, 102, 102));
        txtNombre2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre2ActionPerformed(evt);
            }
        });
        txtNombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombre2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombre2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre2KeyTyped(evt);
            }
        });

        txtNombre3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNombre3.setForeground(new java.awt.Color(102, 102, 102));
        txtNombre3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombre3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombre3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre3KeyTyped(evt);
            }
        });

        txtDni.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDni.setForeground(new java.awt.Color(102, 102, 102));
        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        jLabel9.setText("DNI");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26)
                                .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(32, 32, 32)
                                .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(44, 44, 44)
                                .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(24, 24, 24)
                        .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Fecha de nac.");

        txtFechaNac.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtFechaNac.setForeground(new java.awt.Color(102, 102, 102));
        txtFechaNac.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFechaNacCaretUpdate(evt);
            }
        });
        txtFechaNac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechaNacKeyPressed(evt);
            }
        });

        jLabel11.setText("Estado");

        txtEstado.setEditable(false);
        txtEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstado.setText("A");
        txtEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtEstado.setEnabled(false);
        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 153, 153));
        jLabel35.setText("DD/MM/AAAA");

        jLabel25.setText("Nacionalidad");

        txtNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNacionalidad.setForeground(new java.awt.Color(102, 102, 102));
        txtNacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNacionalidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNacionalidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacionalidadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel35)
                                .addGap(28, 28, 28))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 79, Short.MAX_VALUE))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Género");

        buttonGroup1.add(rbtMasculino);
        rbtMasculino.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rbtMasculino.setForeground(new java.awt.Color(102, 102, 102));
        rbtMasculino.setSelected(true);
        rbtMasculino.setText("Masculino");
        rbtMasculino.setEnabled(false);
        rbtMasculino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbtMasculinoKeyPressed(evt);
            }
        });

        buttonGroup1.add(rbtFemenino);
        rbtFemenino.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rbtFemenino.setForeground(new java.awt.Color(102, 102, 102));
        rbtFemenino.setText("Femenino");
        rbtFemenino.setEnabled(false);
        rbtFemenino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbtFemeninoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rbtFemeninoKeyReleased(evt);
            }
        });

        btnAsignarDistrito.setMnemonic('A');
        btnAsignarDistrito.setContentAreaFilled(false);
        btnAsignarDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarDistritoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtFemenino)
                            .addComponent(rbtMasculino)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAsignarDistrito)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(rbtMasculino)
                .addGap(18, 18, 18)
                .addComponent(rbtFemenino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAsignarDistrito)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setText("Lugar de Nacimiento");

        jLabel16.setText("Distrito");

        jLabel17.setText("Provincia");

        jLabel18.setText("Departamento");

        cbxDepartamentoNac.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDepartamentoNac.setForeground(new java.awt.Color(102, 102, 102));
        cbxDepartamentoNac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxDepartamentoNac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentoNacItemStateChanged(evt);
            }
        });
        cbxDepartamentoNac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDepartamentoNacActionPerformed(evt);
            }
        });
        cbxDepartamentoNac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDepartamentoNacKeyPressed(evt);
            }
        });

        cbxProvinciaNac.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxProvinciaNac.setForeground(new java.awt.Color(102, 102, 102));
        cbxProvinciaNac.setEnabled(false);
        cbxProvinciaNac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciaNacItemStateChanged(evt);
            }
        });
        cbxProvinciaNac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProvinciaNacActionPerformed(evt);
            }
        });
        cbxProvinciaNac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxProvinciaNacKeyPressed(evt);
            }
        });

        cbxDistritoNac.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDistritoNac.setForeground(new java.awt.Color(102, 102, 102));
        cbxDistritoNac.setEnabled(false);
        cbxDistritoNac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDistritoNacActionPerformed(evt);
            }
        });
        cbxDistritoNac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDistritoNacKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel13))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(6, 6, 6)
                                .addComponent(cbxDepartamentoNac, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(19, 19, 19)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxDistritoNac, 0, 181, Short.MAX_VALUE)
                                    .addComponent(cbxProvinciaNac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbxDepartamentoNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbxProvinciaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbxDistritoNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setText("Lugar Actual");

        jLabel21.setText("Departamento");

        jLabel22.setText("Provincia");

        jLabel23.setText("Distrito");

        cbxDepartamento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDepartamento.setForeground(new java.awt.Color(102, 102, 102));
        cbxDepartamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentoItemStateChanged(evt);
            }
        });
        cbxDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDepartamentoActionPerformed(evt);
            }
        });
        cbxDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDepartamentoKeyPressed(evt);
            }
        });

        cbxProvincia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxProvincia.setForeground(new java.awt.Color(102, 102, 102));
        cbxProvincia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxProvincia.setEnabled(false);
        cbxProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciaItemStateChanged(evt);
            }
        });
        cbxProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProvinciaActionPerformed(evt);
            }
        });
        cbxProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxProvinciaKeyPressed(evt);
            }
        });

        cbxDistrito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDistrito.setForeground(new java.awt.Color(102, 102, 102));
        cbxDistrito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxDistrito.setEnabled(false);
        cbxDistrito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDistritoItemStateChanged(evt);
            }
        });
        cbxDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDistritoActionPerformed(evt);
            }
        });
        cbxDistrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDistritoKeyPressed(evt);
            }
        });

        jLabel24.setText("Tipo direccón");

        cbxTipoDireccion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxTipoDireccion.setForeground(new java.awt.Color(102, 102, 102));
        cbxTipoDireccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxTipoDireccion.setEnabled(false);
        cbxTipoDireccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoDireccionItemStateChanged(evt);
            }
        });
        cbxTipoDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxTipoDireccionKeyPressed(evt);
            }
        });

        jLabel26.setText("Dirección");

        txtNumero.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNumero.setForeground(new java.awt.Color(102, 102, 102));
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
        });

        jLabel36.setText("Sector");

        cbxSector.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxSector.setForeground(new java.awt.Color(102, 102, 102));
        cbxSector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSectorItemStateChanged(evt);
            }
        });

        cbxDireccion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDireccion.setForeground(new java.awt.Color(102, 102, 102));
        cbxDireccion.setEnabled(false);
        cbxDireccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDireccionItemStateChanged(evt);
            }
        });
        cbxDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDireccionKeyPressed(evt);
            }
        });

        lblNumero.setText("#");

        lblLt.setText("Lt");

        txtLote.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtLote.setForeground(new java.awt.Color(102, 102, 102));
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLoteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxDistrito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(7, 7, 7)
                        .addComponent(cbxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxSector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoDireccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxDireccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNumero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblLt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(cbxDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(cbxSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(cbxTipoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLt)
                            .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumero))
                        .addGap(10, 10, 10)))
                .addGap(1, 1, 1))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setText("Estado Civil");

        jLabel29.setText("Religión");

        jLabel30.setText("Grupo Sanguineo");

        jLabel31.setText("Grado Instrucción");

        jLabel32.setText("Ocupación");

        jLabel33.setText("Teléfono");

        cbxEstadoCivil.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxEstadoCivil.setForeground(new java.awt.Color(102, 102, 102));
        cbxEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Soltero(a)", "Casado(a)", "Viudo(a)", "Divorciado(a),", "Conviviente" }));
        cbxEstadoCivil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxEstadoCivilKeyPressed(evt);
            }
        });

        txtReligion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtReligion.setForeground(new java.awt.Color(102, 102, 102));
        txtReligion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReligionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReligionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReligionKeyTyped(evt);
            }
        });

        txtGrupoSan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtGrupoSan.setForeground(new java.awt.Color(102, 102, 102));
        txtGrupoSan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGrupoSanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoSanKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGrupoSanKeyTyped(evt);
            }
        });

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(102, 102, 102));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtOcupacion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtOcupacion.setForeground(new java.awt.Color(102, 102, 102));
        txtOcupacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOcupacionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOcupacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOcupacionKeyTyped(evt);
            }
        });

        txtGradoIns.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtGradoIns.setForeground(new java.awt.Color(102, 102, 102));
        txtGradoIns.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGradoInsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGradoInsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGradoInsKeyTyped(evt);
            }
        });

        jLabel34.setText("Celular");

        txtCelular.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCelular.setForeground(new java.awt.Color(102, 102, 102));
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCelularKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });

        txtHuella.setEditable(false);

        txtRiesgo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtRiesgo.setForeground(new java.awt.Color(102, 102, 102));
        txtRiesgo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRiesgoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRiesgoKeyReleased(evt);
            }
        });

        jLabel49.setText("Riesgo:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxEstadoCivil, 0, 114, Short.MAX_VALUE)
                    .addComponent(txtGradoIns)
                    .addComponent(txtGrupoSan)
                    .addComponent(txtReligion))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel49))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRiesgo)
                            .addComponent(txtOcupacion, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(txtHuella)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRiesgo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32)
                                    .addComponent(txtOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel33)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel34)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(txtReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGrupoSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(txtGradoIns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtHuella))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1068, 1068, 1068))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Edit-16.png"))); // NOI18N
        jLabel28.setText("Modificar (Alt + M)");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
        jLabel41.setText("Nuevo (Alt + N)");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Save-16.png"))); // NOI18N
        jLabel42.setText("Guardar (Alt + G)");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Eliminar-16.png"))); // NOI18N
        jLabel43.setText("Eliminar (Alt + E)");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
        jLabel44.setText("Buscar (Alt + B)");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir.png"))); // NOI18N
        jLabel45.setText("Salir (ESC)");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel41)
                .addGap(6, 6, 6)
                .addComponent(jLabel42)
                .addGap(6, 6, 6)
                .addComponent(jLabel28)
                .addGap(6, 6, 6)
                .addComponent(jLabel43)
                .addGap(6, 6, 6)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel28)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 844, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidoMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMatActionPerformed

    private void txtNombre2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre2ActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
            try {
            if(btnNuevo.getText().equals("Nuevo")){
                String[] opciones = {"Automático","Continuador","Reasignado"};
                ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Documento-32.png")); 
                String resp = (String) JOptionPane.showInputDialog(this,"Seleccione una opción:", "Opciones",JOptionPane.DEFAULT_OPTION, i, opciones, opciones[0]);
                if(resp.equals("Automático")){
                    habilitarOpciones(true);
                    btnGuardar.setEnabled(true);
                    btnEliminar.setEnabled(false);
                    btnModificar.setEnabled(false);
                    limpiar();
                    txtCodigo.setText(hC.codHistoriaClinica());
                    txtCodigo.setEnabled(false);
                    mostrarNumHC();
                    txtDni.requestFocus();
                } else
                if(resp.equals("Continuador")){
                    habilitarOpciones(true);
                    btnGuardar.setEnabled(true);
                    btnEliminar.setEnabled(false);
                    btnModificar.setEnabled(false);
                    txtCodigo.setText("");
                    txtCodigo.setEnabled(true);
                    txtCodigo.setEditable(true);
                    limpiar();
                    txtCodigo.requestFocus();
                } else
                    ReasignarHC.setVisible(true);
                    ReasignarHC.setLocationRelativeTo(null);//en el centro
                    ReasignarHC.setResizable(false);
                    ReasignarHC.getContentPane().setBackground(Color.WHITE);
                    mostrarCodHC("");
            } else 
            if(btnNuevo.getText().equals("Reasignar")){
                String codigo = String.valueOf(txtCodigo.getText().charAt(0)) + 
                                String.valueOf(txtCodigo.getText().charAt(1)) + 
                                String.valueOf(txtCodigo.getText().charAt(2)) + 
                                String.valueOf(txtCodigo.getText().charAt(3)) + 
                                String.valueOf(txtCodigo.getText().charAt(4)) + 
                                String.valueOf(txtCodigo.getText().charAt(6)) + 
                                String.valueOf(txtCodigo.getText().charAt(7));
                HistoriaClinica hC2 = new HistoriaClinica();
                hC2.setId_hc(txtID.getText());
                hC2.setDni(txtDni.getText());
                hC2.setCod_hc(codigo);
                hC2.setApe_pat(txtApellidoPat.getText());
                hC2.setApe_mat(txtApellidoMat.getText());
                hC2.setNombre1(txtNombre1.getText());
                hC2.setNombre2(txtNombre2.getText());
                hC2.setNombre3(txtNombre3.getText());
                hC2.setFec_nac(txtFechaNac.getText());
                //GENERO
                if(rbtMasculino.isSelected()){
                    hC2.setSexo("M");
                } 
                if(rbtFemenino.isSelected()){
                    hC2.setSexo("F");
                }
                //ACTUAL
                hC2.setDep_act(cbxDepartamento.getSelectedItem().toString());
                hC2.setPro_act(cbxProvincia.getSelectedItem().toString());
                hC2.setDis_act(cbxDistrito.getSelectedItem().toString());
                // CODIGO DEL DISTRITO 
                hC2.setCod_dis(hC.codDistrito(cbxDistrito.getSelectedItem().toString()));
                // CODIGO DISTRITO NAC
                hC2.setCod_dis_nac(hC.codDistrito(cbxDistritoNac.getSelectedItem().toString()));
                // NOMBRE DISTRITO, PROVINCIA, DEPARTAMENTO
                hC2.setDis_nac(cbxDistritoNac.getSelectedItem().toString()); 
                hC2.setPro_nac(cbxProvinciaNac.getSelectedItem().toString());
                hC2.setDep_nac(cbxDepartamentoNac.getSelectedItem().toString());
                hC2.setOcupacion(txtOcupacion.getText());
                hC2.setEstado_civil(cbxEstadoCivil.getSelectedItem().toString());
                hC2.setGrupo_sang(txtGrupoSan.getText());
                hC2.setReligion(txtReligion.getText());
                hC2.setTelefono(txtTelefono.getText());
                hC2.setCelular(txtCelular.getText());
                hC2.setGrado_inst(txtGradoIns.getText());
                hC2.setNacionalidad(txtNacionalidad.getText());
                hC2.setNom_usu(lblUsuUsuario.getText());
                if(!cbxProvincia.getSelectedItem().toString().equals("CHINCHA")){
                            hC2.setSe_cod("Turista");
                            hC2.setTipo_dir_nom("Turista");
                            hC2.setNom_dir("Turista");
                            hC2.setNum("");
                        } else {
                        hC2.setSe_cod(cbxSector.getSelectedItem().toString());
                        //TIPO_DIR_NOM
                        hC2.setTipo_dir_nom(cbxTipoDireccion.getSelectedItem().toString());
                        hC2.setNom_dir(cbxDireccion.getSelectedItem().toString());
                        hC2.setNum(txtNumero.getText());
               }
                hC2.setLote(txtLote.getText());
                hC2.setRiesgo(txtRiesgo.getText());
                if(hC2.guardarDatosReasignar()==true){
                    if(txtDni.getText().equals("") || txtNombre1.getText().equals("") || txtApellidoPat.getText().equals("") ||
                       txtApellidoPat.getText().equals("") || txtFechaNac.getText().equals("") ||
                            cbxDepartamento.getSelectedIndex()==0 || cbxDepartamentoNac.getSelectedIndex() == 0 
                            || cbxProvincia.getSelectedIndex() == 0 || cbxProvinciaNac.getSelectedIndex()==0 || cbxDistrito.getSelectedIndex() == 0
                            || cbxDistritoNac.getSelectedIndex()==0 || txtRiesgo.getText().equals("")){
                        JOptionPane.showMessageDialog(this, "Debe ingresar los siguientes campos \n\t\t + lugar de nacimiento y actual");
                        txtDni.setBackground(new Color(51,204,255));
                        txtNombre1.setBackground(new Color(51,204,255));
                        txtApellidoPat.setBackground(new Color(51,204,255));
                        txtApellidoMat.setBackground(new Color(51,204,255));
                        txtFechaNac.setBackground(new Color(51,204,255));
                        txtCodigo.setBackground(new Color(51,204,255));
                        txtRiesgo.setBackground(new Color(51,204,255));
                        cbxDistrito.setEnabled(false);
                        cbxDistrito.setEnabled(false);
                        cbxDistritoNac.setEnabled(false);
                        cbxDistritoNac.setEnabled(false);
                        txtCodigo.setEnabled(false);
                    } else {
                    ImageIcon iNuevo=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Documento-32.png")); 
                    String cod_hc = txtCodigo.getText();
                    String codigo3 = String.valueOf(cod_hc.charAt(0)) + String.valueOf(cod_hc.charAt(1)) + 
                                     String.valueOf(cod_hc.charAt(2)) + String.valueOf(cod_hc.charAt(3)) +
                                     String.valueOf(cod_hc.charAt(4)) + String.valueOf(cod_hc.charAt(6)) + String.valueOf(cod_hc.charAt(7));
                    String rutaInforme = "src\\reportes\\admisionCentral\\historiaClinica.jasper";
                    Map parametros = new HashMap();
                    parametros.put("cod_hc", codigo3);
                    JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, c.conectar());
                    String datos = codigo3 + "_" + txtApellidoPat.getText() + txtApellidoMat.getText() + txtNombre1.getText(); 
                
                            JasperViewer ventanavisor = new JasperViewer(informe, false);
                            ventanavisor.setTitle("Historia Clínica");
                            ventanavisor.setVisible(true);
//                    JasperExportManager.exportReportToPdfFile(informe,"D:\\Historias-Clinicas\\"+datos+".pdf");
//                    String file = new String("D:\\Historias-Clinicas\\"+datos+".pdf");
//                    //definiendo la ruta en la propiedad file
//                    //Visualizar el PDF
//                    Runtime.getRuntime().exec("cmd /c start "+file);   
                    JOptionPane.showMessageDialog(this, "Historia Clínica reasignada a " + 
                    txtApellidoPat.getText() + " " + txtApellidoMat.getText() + " " + 
                    txtNombre1.getText() + " " + txtNombre2.getText() + " " + txtNombre3.getText());
                    btnNuevo.setText("Nuevo");
                    btnNuevo.setIcon(iNuevo);
                    btnNuevo.setToolTipText("Nuevo (Alt + N)");
                    btnNuevo.setMnemonic(KeyEvent.VK_N);
                    habilitarOpciones(false);
                    lblNumero.setVisible(false);
                    lblLt.setVisible(false);
                    txtNumero.setVisible(false);
                    txtLote.setVisible(false);
                    btnNuevo.setIcon(iNuevo);
                    limpiar();
                    String codigoGen = String.valueOf(hC.codHistoriaClinica().charAt(0)) + 
                                       String.valueOf(hC.codHistoriaClinica().charAt(1)) + 
                                       String.valueOf(hC.codHistoriaClinica().charAt(2)) + 
                                       String.valueOf(hC.codHistoriaClinica().charAt(3)) +
                                       String.valueOf(hC.codHistoriaClinica().charAt(4)) + "-" +
                                       String.valueOf(hC.codHistoriaClinica().charAt(5)) +  
                                       String.valueOf(hC.codHistoriaClinica().charAt(6));
                    txtCodigo.setText(codigoGen);
                    }
                    cbxDistritoNac.setEnabled(false);
                    cbxDistrito.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar los datos");
                }        
            }
            } catch (Exception e) {
            }
        
    }//GEN-LAST:event_btnNuevoActionPerformed
    
    public void validaCodigo(String codigo){
        String codigoMostrar = String.valueOf(codigo.charAt(0)) + String.valueOf(codigo.charAt(1)) + 
                               String.valueOf(codigo.charAt(2)) + String.valueOf(codigo.charAt(3)) + String.valueOf(codigo.charAt(4)) + "-" + 
                               String.valueOf(codigo.charAt(5)) + String.valueOf(codigo.charAt(6));
        try {
            PreparedStatement cmd = hC.getCn().prepareStatement("SELECT * FROM ADMISION_HISTORIA_CLINICA WHERE cod_hc ='"+codigo+"'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){
                JOptionPane.showMessageDialog(null, "Esta Historia Clínica N°" + codigoMostrar + " ya existe");
            }else {
                btnGuardar();
                res.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error_validaCodigo: " + e.toString());
        }
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(validaDNI(txtDni.getText())==false){
            if(txtCodigo.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Debe ingresar un Número de HistoriaClínica");
            } else {
                String codigo = String.valueOf(txtCodigo.getText().charAt(0)) + String.valueOf(txtCodigo.getText().charAt(1)) + String.valueOf(txtCodigo.getText().charAt(2)) + String.valueOf(txtCodigo.getText().charAt(3)) + String.valueOf(txtCodigo.getText().charAt(4)) + String.valueOf(txtCodigo.getText().charAt(5)) + String.valueOf(txtCodigo.getText().charAt(6)); 
                validaCodigo(codigo);
            }
        } 
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Guardar-32.png")); 
        if(btnModificar.getText().equals("Modificar")){
            if(cbxTipoDireccion.getSelectedItem().equals("Urb")){
                txtLote.setEnabled(true);
            }
            habilitarOpciones(true);
            btnGuardar.setEnabled(true);
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnNuevo.setEnabled(false);
            btnModificar.setText("Actualizar");
            txtNumero.setEnabled(true);
            btnModificar.setIcon(i);
        } else 
        if(btnModificar.getText().equals("Actualizar")){
            if(txtID.getText() != (hC.idHistoriaClinica())){
                    int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(modificar == 0 ){
                        HistoriaClinica hC2 = new HistoriaClinica();
                        hC2.setId_hc(txtID.getText());
                        hC2.setDni(txtDni.getText());
                        hC2.setApe_pat(txtApellidoPat.getText());
                        hC2.setApe_mat(txtApellidoMat.getText());
                        hC2.setNombre1(txtNombre1.getText());
                        hC2.setNombre2(txtNombre2.getText());
                        hC2.setNombre3(txtNombre3.getText());
                        hC2.setFec_nac(txtFechaNac.getText());
                        //GENERO
                        if(rbtMasculino.isSelected()){
                            hC2.setSexo("M");
                        } 
                        if(rbtFemenino.isSelected()){
                            hC2.setSexo("F");
                        }
                        //ACTUAL
                        hC2.setDep_act(cbxDepartamento.getSelectedItem().toString());
                        hC2.setPro_act(cbxProvincia.getSelectedItem().toString());
                        hC2.setDis_act(cbxDistrito.getSelectedItem().toString());
                        // CODIGO DEL DISTRITO 
                        hC2.setCod_dis(hC.codDistrito(cbxDistrito.getSelectedItem().toString()));
                        // CODIGO DISTRITO NAC
                        hC2.setCod_dis_nac(hC.codDistrito(cbxDistritoNac.getSelectedItem().toString()));
                        // NOMBRE DISTRITO, PROVINCIA, DEPARTAMENTO
                        hC2.setDis_nac(cbxDistritoNac.getSelectedItem().toString()); 
                        hC2.setPro_nac(cbxProvinciaNac.getSelectedItem().toString());
                        hC2.setDep_nac(cbxDepartamentoNac.getSelectedItem().toString());
                        hC2.setOcupacion(txtOcupacion.getText());
                        hC2.setEstado_civil(cbxEstadoCivil.getSelectedItem().toString());
                        hC2.setGrupo_sang(txtGrupoSan.getText());
                        hC2.setReligion(txtReligion.getText());
                        hC2.setTelefono(txtTelefono.getText());
                        hC2.setCelular(txtCelular.getText());
                        hC2.setGrado_inst(txtGradoIns.getText());
                        hC2.setNacionalidad(txtNacionalidad.getText());
                        hC2.setNom_usu(lblUsuUsuario.getText());
                        hC2.setEstado_hc_uso(txtEstado.getText());
                        hC2.setRiesgo(txtRiesgo.getText());
                        if(!cbxProvincia.getSelectedItem().toString().equals("CHINCHA")){
                            hC2.setSe_cod("Turista");
                            hC2.setTipo_dir_nom("Turista");
                            hC2.setNom_dir("Turista");
                            hC2.setNum("");
                        } else {
                            hC2.setSe_cod(cbxSector.getSelectedItem().toString());
                            //TIPO_DIR_NOM
                            hC2.setTipo_dir_nom(cbxTipoDireccion.getSelectedItem().toString());
                            hC2.setNom_dir(cbxDireccion.getSelectedItem().toString());
                            hC2.setNum(txtNumero.getText());
                        }
                        hC2.setLote(txtLote.getText());
                       if(hC2.modificarHistoriaClinica()==true){
                        ImageIcon iM=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Editar-32.png")); 
                           JOptionPane.showMessageDialog(this, "Datos Actualizados");
                            limpiar();
                            habilitarBotones(true);
                            habilitarOpciones(false);
                            lblNumero.setVisible(false);
                            lblLt.setVisible(false);
                            txtNumero.setVisible(false);
                            txtLote.setVisible(false);
                            btnModificar.setIcon(iM);
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al actualizar los datos");
                       }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }
        }
            btnModificar.setText("Modificar");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        hC.estadoInactivo();
        BuscarHC.setVisible(true);
        BuscarHC.setLocationRelativeTo(null);//en el centro
        BuscarHC.setResizable(false);
        BuscarHC.getContentPane().setBackground(Color.WHITE);
        cabecera();
        cbxTipoBusqueda.setSelectedIndex(0);
        txtBuscar.setEnabled(false);
        chkA.setSelected(true);
        chkD.setSelected(false);
        chkT.setSelected(false);
        cbxTipoBusqueda.setSelectedIndex(1);
        tbHistoriaC.getSelectionModel().setSelectionInterval(0,0);
        tbHistoriaC.requestFocus();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(btnEliminar.getText().equals("Eliminar")){
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Basura-32.png")); 
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 ){
                HistoriaClinica hCEl = new HistoriaClinica();
                hCEl.setId_hc(txtID.getText());
                if(hCEl.eliminarHistoriaClinica()){
                    JOptionPane.showMessageDialog(this, "Historia Clínica \n\t\t\teliminada");
                    limpiar();
                }
                habilitarBotones(true);
                habilitarOpciones(false);
            } 
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
        }
        if(btnEliminar.getText().equals("Restaurar")){
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Restaurar-32.png")); 
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea RESTAURAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 ){
                HistoriaClinica hCRe = new HistoriaClinica();
                hCRe.setId_hc(txtID.getText());
                if(hCRe.restaurarHistoriaClinica()){
                    JOptionPane.showMessageDialog(this, "Historia Clínica \n\t\t\trestaurada");
                    limpiar();
                }
                habilitarBotones(true);
                habilitarOpciones(false);
            } 
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbxDepartamentoNacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDepartamentoNacActionPerformed
        cbxProvinciaNac.enable(true);
        cbxDistritoNac.enable(false);
        cbxDistritoNac.removeAllItems();
    }//GEN-LAST:event_cbxDepartamentoNacActionPerformed

    private void cbxProvinciaNacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProvinciaNacActionPerformed
        cbxDistritoNac.enable(true);
    }//GEN-LAST:event_cbxProvinciaNacActionPerformed

    private void cbxDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDepartamentoActionPerformed
        cbxProvincia.enable(true);
        cbxDistrito.enable(false);
        cbxDistrito.removeAllItems();
    }//GEN-LAST:event_cbxDepartamentoActionPerformed

    private void cbxProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProvinciaActionPerformed
        cbxDistrito.enable(true);
    }//GEN-LAST:event_cbxProvinciaActionPerformed

    private void cbxDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDistritoActionPerformed

    private void cbxDistritoNacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDistritoNacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDistritoNacActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void cbxTipoBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaItemStateChanged
        try{
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.cbxTipoBusqueda.getSelectedIndex()>0){
                    txtBuscar.setEnabled(true);
                    txtBuscar.setText("");
                    btnImprimir.setEnabled(true);
                    chkA.setEnabled(true);
                    chkD.setEnabled(true);
                    chkT.setEnabled(true);
                    if(this.cbxTipoBusqueda.getSelectedIndex() == 6){
                        btnImprimir.setText("Imprimir Ficha por Sector");
                    } else {
                        btnImprimir.setText("Imprimir");
                    }
                }
            }
            else{
                txtBuscar.setEnabled(false);
                //btnImprimir.setEnabled(false);
            }}
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxTipoBusquedaItemStateChanged

    private void cbxTipoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoBusquedaActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        int index = cbxTipoBusqueda.getSelectedIndex();
        if(chkA.isSelected()== false && chkD.isSelected() == false && chkT.isSelected()==false && txtBuscar.isEnabled() == true){
            JOptionPane.showMessageDialog(BuscarHC, "Debe seleccionar una opción \n ");
        } //else
//        if(chkA.isSelected()==true){
//        buscar_HC(index, "A", txtBuscar.getText());
//        } else 
//        if(chkD.isSelected()==true){
//            buscar_HC(index, "D", txtBuscar.getText());
//        } else
//        if(chkT.isSelected()==true){
//            buscar_HC(index, "", txtBuscar.getText());
//        }
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void tbHistoriaCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbHistoriaCKeyPressed
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatos();
       }
    }//GEN-LAST:event_tbHistoriaCKeyPressed

    private void cbxProvinciaNacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaNacItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxProvinciaNac.getSelectedIndex()>0){
                        this.cbxDistritoNac.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String prov=cbxProvinciaNac.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_NOMBRE_DISTRITO '"+prov+ "'");
                    this.cbxDistritoNac.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxDistritoNac.addItem(rs.getString("NOMBRE_DISTRITO"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxDistritoNac.removeAllItems();

                        this.cbxDistritoNac.addItem("Seleccionar...");
                            }
            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxProvinciaNacItemStateChanged

    private void cbxProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxProvincia.getSelectedIndex()>0){
                        this.cbxDistrito.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String prov=cbxProvincia.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_NOMBRE_DISTRITO '"+prov+ "'");
                    this.cbxDistrito.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxDistrito.addItem(rs.getString("NOMBRE_DISTRITO"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxDistrito.removeAllItems();

                        this.cbxDistrito.addItem("Seleccionar...");
                            }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxProvinciaItemStateChanged

    private void cbxDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxDepartamento.getSelectedIndex()>0){
                        this.cbxProvincia.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String dpto=cbxDepartamento.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_NOMBRE_PROVINCIA '"+dpto+"'");
                    this.cbxProvincia.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxProvincia.addItem(rs.getString("NOMBRE_PROVINCIA"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxProvincia.removeAllItems();

                        this.cbxProvincia.addItem("Seleccionar...");
                            }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxDepartamentoItemStateChanged

    private void cbxDepartamentoNacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoNacItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxDepartamentoNac.getSelectedIndex()>0){
                        this.cbxProvinciaNac.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String dpto=cbxDepartamentoNac.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_NOMBRE_PROVINCIA '"+dpto+"'");
                    this.cbxProvinciaNac.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxProvinciaNac.addItem(rs.getString("NOMBRE_PROVINCIA"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxProvinciaNac.removeAllItems();

                        this.cbxProvinciaNac.addItem("Seleccionar...");
                            }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxDepartamentoNacItemStateChanged

    private void txtFechaNacCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFechaNacCaretUpdate
        
    }//GEN-LAST:event_txtFechaNacCaretUpdate
    
    private void cbxTipoBusquedaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoBusquedaAncestorAdded

    private void chkAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAActionPerformed
        int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscar.getText();
        String opcion = "";
        estado = "A";
        txtBuscar.setText("");
        txtBuscar.setEnabled(true);
    }//GEN-LAST:event_chkAActionPerformed

    private void chkAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkAItemStateChanged
        /*if(txtBuscar.getText().equals("")){
            int index = cbxTipoBusqueda.getSelectedIndex();
            //String busqueda = txtBuscar.getText();
            String opcion = "";
                buscar_HC(0, "", "");
            
        }*/
    }//GEN-LAST:event_chkAItemStateChanged

    private void chkDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkDItemStateChanged
        /*int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscar.getText();
        String opcion = "";
        if(chkD.isSelected()){
            buscar_HC(0, "", "");
        }*/
    }//GEN-LAST:event_chkDItemStateChanged

    private void chkDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDActionPerformed
        /*int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscar.getText();
        String opcion = "";
            buscar_HC(index, "D", busqueda);
        */
        int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscar.getText();
        String opcion = "";
        estado = "D";
        txtBuscar.setText("");
        txtBuscar.setEnabled(true);
    }//GEN-LAST:event_chkDActionPerformed

    private void chkTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTItemStateChanged
        /*int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscar.getText();
        String opcion = "";
        if(chkT.isSelected()){
            buscar_HC(index, "", busqueda);
        }*/
    }//GEN-LAST:event_chkTItemStateChanged

    private void chkTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTActionPerformed
        int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscar.getText();
        String opcion = "";
        estado="";
        txtBuscar.setText("");
        txtBuscar.setEnabled(true);
    }//GEN-LAST:event_chkTActionPerformed

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void tbHistoriaCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHistoriaCMouseClicked
        if(evt.getClickCount()==2)
            enviarDatos();
    }//GEN-LAST:event_tbHistoriaCMouseClicked

    private void tbHistoriaCMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHistoriaCMousePressed
        
    }//GEN-LAST:event_tbHistoriaCMousePressed

    private void tbHistoriaCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHistoriaCMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbHistoriaCMouseEntered

    private void txtBuscarCodigoHCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCodigoHCCaretUpdate
        mostrarCodHC(txtBuscarCodigoHC.getText());
    }//GEN-LAST:event_txtBuscarCodigoHCCaretUpdate

    private void btnReasignarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnReasignarItemStateChanged
        
    }//GEN-LAST:event_btnReasignarItemStateChanged

    private void btnReasignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReasignarActionPerformed
        if(tbReasignado.getSelectedRow()>-1){
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/delete.png")); 
        int fila = tbReasignado.getSelectedRow();
        String codigoObtenido = String.valueOf(tbReasignado.getValueAt(fila, 0));
        String codigo = String.valueOf(codigoObtenido.charAt(0)) + String.valueOf(codigoObtenido.charAt(1)) 
                + String.valueOf(codigoObtenido.charAt(2)) + String.valueOf(codigoObtenido.charAt(3)) + String.valueOf(codigoObtenido.charAt(5)) + String.valueOf(codigoObtenido.charAt(6)) ;
        int reasignar = JOptionPane.showConfirmDialog(ReasignarHC, "¿Desea limpiar todos los campos de \n la Historia Clínica N°" 
        + codigo + " para ser reasignada?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        if(reasignar==0){
            HistoriaClinica hCRe = new HistoriaClinica();
            hCRe.setCod_hc(codigo);
            if(hCRe.reasignarCodHC() == true){
                JOptionPane.showMessageDialog(ReasignarHC, "La Historia Clínica N° " + codigo + " está lista para "
                        + "ser reasignada");
                mostrarCodHC("");
            }
        }
        } else {
            JOptionPane.showMessageDialog(ReasignarHC, "Debe seleccionar una Historia Clínica");
        }
    }//GEN-LAST:event_btnReasignarActionPerformed

    private void btnAnadirReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirReActionPerformed
        validaCampo();
        HistoriaClinica hisC = new HistoriaClinica();
        btnNuevo.setToolTipText("Guardar (Alt + G)");
        btnNuevo.setMnemonic(KeyEvent.VK_G);
        try{
            ReasignarHC.dispose();
            ImageIcon iGuardar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Guardar-32.png")); 
            btnNuevo.setIcon(iGuardar);
            btnNuevo.setText("Reasignar");
            String codigo = String.valueOf(hisC.obtenerCODReasignar().charAt(0))+ 
                            String.valueOf(hisC.obtenerCODReasignar().charAt(1))+
                            String.valueOf(hisC.obtenerCODReasignar().charAt(2))+
                            String.valueOf(hisC.obtenerCODReasignar().charAt(3))+
                            String.valueOf(hisC.obtenerCODReasignar().charAt(4))+ "-" +
                            String.valueOf(hisC.obtenerCODReasignar().charAt(5))+
                            String.valueOf(hisC.obtenerCODReasignar().charAt(6));
            FrmNuevaHistoriaC.txtID.setText(hisC.obtenerIDReasignar()); 
            FrmNuevaHistoriaC.txtCodigo.setText(codigo); 
            habilitarOpciones(true);
            txtCodigo.setEnabled(false);
            btnGuardar.setEnabled(false);
        } catch(Exception e){
            ImageIcon iNuevo=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Documento-32.png")); 
            JOptionPane.showMessageDialog(ReasignarHC, "No hay Historias Clínicas disponibles para reasignar", "Mensaje", JOptionPane.ERROR_MESSAGE);
            btnNuevo.setText("Nuevo");
            btnNuevo.setIcon(iNuevo);
            limpiar();
            habilitarOpciones(false);
            habilitarBotones(true);
        }
        
        /*ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/delete.png")); 
        if(tbReasignado.getSelectedRow()>-1){
            int fila = tbReasignado.getSelectedRow();
            FrmNuevaHistoriaC nuevaHC = new FrmNuevaHistoriaC();
            nuevaHC.setVisible(true);
            ReasignarHC.setVisible(false);
            dispose();
            FrmNuevaHistoriaC.txtCodigo.setText(String.valueOf(tbReasignado.getValueAt(fila,0)));
            FrmNuevaHistoriaC.txtCodigo.setText(String.valueOf(tbReasignado.getValueAt(fila,1)));
            FrmNuevaHistoriaC.txtCodigo.setText(String.valueOf(tbReasignado.getValueAt(fila,2)));
        } else {
            JOptionPane.showMessageDialog(ReasignarHC, "Debe seleccionar una Historia Clínica");
        }*/
    }//GEN-LAST:event_btnAnadirReActionPerformed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
      char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtNombre1KeyTyped

    private void txtNombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre2KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtNombre2KeyTyped

    private void txtApellidoPatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPatKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtApellidoPatKeyTyped

    private void txtApellidoMatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMatKeyReleased
        txtApellidoMat.setText(txtApellidoMat.getText().toUpperCase());
    }//GEN-LAST:event_txtApellidoMatKeyReleased

    private void txtApellidoMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMatKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtApellidoMatKeyTyped

    private void txtNombre3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre3KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtNombre3KeyTyped

    private void txtNacionalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtNacionalidadKeyTyped

    private void txtReligionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligionKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtReligionKeyTyped

    private void txtGrupoSanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoSanKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtGrupoSanKeyTyped

    private void txtGradoInsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradoInsKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtGradoInsKeyTyped

    private void txtOcupacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacionKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtOcupacionKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtCelularKeyTyped

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyTyped
        
    }//GEN-LAST:event_txtNumeroKeyTyped

    private void cbxDistritoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDistritoKeyPressed
        if(cbxProvincia.getSelectedItem().equals("CHINCHA")){
            if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                cbxTipoDireccion.requestFocus();
                cbxTipoDireccion.showPopup();
            }
        }
    }//GEN-LAST:event_cbxDistritoKeyPressed

    private void cbxDistritoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDistritoItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxDistrito.getSelectedIndex()>0){
                        this.cbxTipoDireccion.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String distrito=cbxDistrito.getSelectedItem().toString();
                    if(cbxProvincia.getSelectedItem().toString().equals("CHINCHA")){
                        cbxTipoDireccion.setEnabled(true);
                        cbxDireccion.setEnabled(true);
                    } else {
                        cbxTipoDireccion.setEnabled(false);
                        cbxDireccion.setEnabled(false);
                    }
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_TIPO_DIRECCION '"+distrito+ "'");
                    this.cbxTipoDireccion.addItem("Seleccionar...");
                    this.cbxSector.removeAllItems();
                    while(rs.next()){
                     this.cbxTipoDireccion.addItem(rs.getString("SE_TIPO_DIR"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxTipoDireccion.removeAllItems();

                        this.cbxTipoDireccion.addItem("Seleccionar...");
                            }

            }
                txtNumero.setVisible(false);
                txtLote.setVisible(false);
                lblNumero.setVisible(false);
                lblLt.setVisible(false);
        }
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxDistritoItemStateChanged

    private void cbxSectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSectorItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxSector.getSelectedIndex()>0){
                    Statement sta=conexion.createStatement();
                    String sector=cbxSector.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_TIPO_DIRECCION '"+sector+ "'");
                    this.cbxTipoDireccion.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxTipoDireccion.addItem(rs.getString("SE_TIPO_DIR"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxSectorItemStateChanged

    private void cbxTipoDireccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDireccionItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxTipoDireccion.getSelectedIndex()>0){
                        this.cbxDireccion.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String distrito=cbxDistrito.getSelectedItem().toString();
                    String tipo=cbxTipoDireccion.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_DIRECCION '"+tipo+ "', '"+distrito+"'");
                    this.cbxDireccion.addItem("Seleccionar...");
                    txtNumero.setText("");
                    txtLote.setText("");
                    while(rs.next()){
                     this.cbxDireccion.addItem(rs.getString("SE_DIR"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                        this.cbxDireccion.removeAllItems();
                        this.cbxDireccion.addItem("Seleccionar...");
                    }

                    if(cbxTipoDireccion.getSelectedItem().equals("Urb")){
                        lblNumero.setText("Mz");
                        lblNumero.setVisible(true);
                        lblLt.setVisible(true);
                        txtNumero.setVisible(true);
                        txtLote.setVisible(true);
                    } 
                    if(cbxTipoDireccion.getSelectedItem().equals("Av") || 
                       cbxTipoDireccion.getSelectedItem().equals("Calle") || 
                       cbxTipoDireccion.getSelectedItem().equals("Barrio") || 
                       cbxTipoDireccion.getSelectedItem().equals("Prol") || 
                       cbxTipoDireccion.getSelectedItem().equals("AA.HH.")){
                        lblNumero.setText("#");
                        lblNumero.setVisible(true);
                        txtNumero.setText("");
                        txtNumero.setVisible(true);
                        txtLote.setVisible(false);
                        lblLt.setVisible(false);
                    }
            }
        }
            catch(Exception ex) 
            {
                System.out.println("Error_tipodireccion: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxTipoDireccionItemStateChanged

    private void txtLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoteKeyTyped

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // IMPRIMIR, HISTORIA CLINICA Y SECTOR
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(date);
        int fila = tbHistoriaC.getSelectedRow();
        try {
            if(cbxTipoBusqueda.getSelectedIndex() != 6){
            // IMPRESION DE HISTORIA CLINICA
                if(fila == -1){
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una Historia Clínica");
                } else {
                        String cod_hc = tbHistoriaC.getValueAt(fila, 1).toString();
                        String codigo = String.valueOf(cod_hc.charAt(0)) + String.valueOf(cod_hc.charAt(1)) + 
                                        String.valueOf(cod_hc.charAt(2)) + String.valueOf(cod_hc.charAt(3)) + String.valueOf(cod_hc.charAt(4)) +
                                        String.valueOf(cod_hc.charAt(6)) + String.valueOf(cod_hc.charAt(7));
                        String rutaInforme = "src\\Reportes\\admisionCentral\\historiaClinica.jasper";
                        Map parametros = new HashMap();
                        parametros.put("cod_hc", cod_hc);
                        JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, c.conectar());
                        // VER EN EL JASPERREPORT
                        JasperViewer ventanavisor = new JasperViewer(informe, false);
                        ventanavisor.setTitle("Historia Clínica");
                        ventanavisor.setVisible(true);
                        /*String datos = String.valueOf(tbHistoriaC.getValueAt(fila, 0))+ 
                                       String.valueOf(tbHistoriaC.getValueAt(fila, 3)) + 
                                       String.valueOf(tbHistoriaC.getValueAt(fila, 4))+ 
                                       String.valueOf(tbHistoriaC.getValueAt(fila, 5))+fecha;*/
                        //EN PDF
                        /*JasperExportManager.exportReportToPdfFile(informe,"D:\\Historias-Clinicas\\"+datos+".pdf");
                        String file = new String("D:\\Historias-Clinicas\\"+datos+".pdf");
                        //definiendo la ruta en la propiedad file
                        //Visualizar el PDF
                        JOptionPane.showMessageDialog(this,"Exportando a PDF ... ");
                        Runtime.getRuntime().exec("cmd /c start "+file);*/
                }
            } else 
            // IMPRESION (SECTORIZACION)
            if(cbxTipoBusqueda.getSelectedIndex() == 6){
            // IMPRESION DE HISTORIA CLINICA
            /*int fila = tbHistoriaC.getSelectedRow();
                if(fila == -1){
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una Historia Clínica");
                } else {*/
                        String sector = txtBuscar.getText();
                        if(sector.equals("")){
                            JOptionPane.showMessageDialog(BuscarHC,"Debe ingresar el sector");
                            txtBuscar.requestFocus();
                        } else {
                            String rutaInforme = "src\\Reportes\\sectorizacion\\sectorizacion.jasper";
                            Map parametros = new HashMap();
                            parametros.put("SE_COD", sector);
                            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, c.conectar());
                            JasperViewer ventanavisor = new JasperViewer(informe, false);
                            ventanavisor.setTitle("Sectorización");
                            ventanavisor.setVisible(true);
                            /*String sector2 = String.valueOf("Sector-"+txtBuscar.getText())+fecha;
                            JasperExportManager.exportReportToPdfFile(informe,"D:\\Sectorizacion\\"+sector2+".pdf");
                            String file = new String("D:\\Sectorizacion\\"+sector2+".pdf");
                            //definiendo la ruta en la propiedad file
                            JOptionPane.showMessageDialog(this,"Exportando a PDF ... ");
                            Runtime.getRuntime().exec("cmd /c start "+file);*/
                        }
                }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: _reporteHC" + e.toString());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void cbxDireccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDireccionItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxDireccion.getSelectedIndex()>0){
                        this.cbxSector.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String distrito=cbxDistrito.getSelectedItem().toString();
                    String tipo=cbxTipoDireccion.getSelectedItem().toString();
                    String direccion=cbxDireccion.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_SECTOR '"+distrito+"','"+tipo+"','"+direccion+"'");
                    //this.cbxSector.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxSector.addItem(rs.getString("SE_COD"));
                  //  this.cbxProvincia.setModel(null);
                    }
                }
            }
        }
            catch(Exception ex) 
            {
                System.out.println("Error_direccion: " + ex.getMessage());
            }
        
        
    }//GEN-LAST:event_cbxDireccionItemStateChanged

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed

    }//GEN-LAST:event_txtDniActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbHistoriaC.getSelectionModel().setSelectionInterval(0, 0);
            tbHistoriaC.requestFocus();
        }
        if(evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            btnBuscarHc.doClick();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnAsignarDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarDistritoActionPerformed
        cbxDepartamento.setSelectedIndex(11);
        cbxProvincia.setSelectedIndex(2);
    }//GEN-LAST:event_btnAsignarDistritoActionPerformed

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void btnBuscarHcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHcActionPerformed
        int index = cbxTipoBusqueda.getSelectedIndex();
        if(chkA.isSelected()==true){
            buscar_HC(index, estado, txtBuscar.getText());
        } else 
            if(chkD.isSelected()==true){
                buscar_HC(index, estado, txtBuscar.getText());
        } else
            if(chkT.isSelected()==true){
                buscar_HC(index, estado, txtBuscar.getText());
        }
    }//GEN-LAST:event_btnBuscarHcActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtApellidoPat.requestFocus();
        }
    }//GEN-LAST:event_txtDniKeyPressed

    private void txtApellidoPatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPatKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtApellidoMat.requestFocus();
        }
    }//GEN-LAST:event_txtApellidoPatKeyPressed

    private void txtNombre1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtNombre2.requestFocus();
        }
    }//GEN-LAST:event_txtNombre1KeyPressed

    private void txtApellidoMatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMatKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtNombre1.requestFocus();
        }
    }//GEN-LAST:event_txtApellidoMatKeyPressed

    private void txtNombre2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre2KeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtNombre3.requestFocus();
        }
    }//GEN-LAST:event_txtNombre2KeyPressed

    private void txtNombre3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre3KeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtFechaNac.requestFocus();
        }
    }//GEN-LAST:event_txtNombre3KeyPressed

    private void txtFechaNacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaNacKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtNacionalidad.requestFocus();
        }
    }//GEN-LAST:event_txtFechaNacKeyPressed

    private void txtNacionalidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxDepartamentoNac.requestFocus();
            cbxDepartamentoNac.showPopup();
        }
    }//GEN-LAST:event_txtNacionalidadKeyPressed

    private void cbxDepartamentoNacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDepartamentoNacKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxProvinciaNac.requestFocus();
            cbxProvinciaNac.showPopup();
        }
    }//GEN-LAST:event_cbxDepartamentoNacKeyPressed

    private void cbxProvinciaNacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxProvinciaNacKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxDistritoNac.requestFocus();
            cbxDistritoNac.showPopup();
        }
    }//GEN-LAST:event_cbxProvinciaNacKeyPressed

    private void cbxDistritoNacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDistritoNacKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxDepartamento.requestFocus();
            cbxDepartamento.showPopup();
        }
    }//GEN-LAST:event_cbxDistritoNacKeyPressed

    private void cbxDepartamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDepartamentoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxProvincia.requestFocus();
            cbxProvincia.showPopup();
        }
    }//GEN-LAST:event_cbxDepartamentoKeyPressed

    private void cbxProvinciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxProvinciaKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxDistrito.requestFocus();
            cbxDistrito.showPopup();
        }
    }//GEN-LAST:event_cbxProvinciaKeyPressed

    private void cbxTipoDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDireccionKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxDireccion.requestFocus();
            cbxDireccion.showPopup();
        }
    }//GEN-LAST:event_cbxTipoDireccionKeyPressed

    private void cbxDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDireccionKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtNumero.requestFocus();
        }
    }//GEN-LAST:event_cbxDireccionKeyPressed

    private void txtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyPressed
        if(cbxTipoDireccion.getSelectedItem().equals("Urb")){
            if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                txtLote.requestFocus();
            }
        }else {
            if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                rbtMasculino.requestFocus();
            }
        }
    }//GEN-LAST:event_txtNumeroKeyPressed

    private void rbtMasculinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbtMasculinoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            rbtMasculino.setSelected(true);
            cbxEstadoCivil.requestFocus();
            cbxEstadoCivil.showPopup();
        }
    }//GEN-LAST:event_rbtMasculinoKeyPressed

    private void rbtFemeninoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbtFemeninoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            rbtFemenino.setSelected(true);
            cbxEstadoCivil.requestFocus();
            cbxEstadoCivil.showPopup();
        }
    }//GEN-LAST:event_rbtFemeninoKeyPressed

    private void txtLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            rbtMasculino.requestFocus();
        }
    }//GEN-LAST:event_txtLoteKeyPressed

    private void rbtFemeninoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbtFemeninoKeyReleased
      
    }//GEN-LAST:event_rbtFemeninoKeyReleased

    private void cbxEstadoCivilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxEstadoCivilKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtReligion.requestFocus();
        }
    }//GEN-LAST:event_cbxEstadoCivilKeyPressed

    private void txtReligionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligionKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtGrupoSan.requestFocus();
        }
    }//GEN-LAST:event_txtReligionKeyPressed

    private void txtGrupoSanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoSanKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtGradoIns.requestFocus();
        }
    }//GEN-LAST:event_txtGrupoSanKeyPressed

    private void txtGradoInsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradoInsKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtRiesgo.requestFocus();
        }
    }//GEN-LAST:event_txtGradoInsKeyPressed

    private void txtRiesgoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRiesgoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtOcupacion.requestFocus();
        }
    }//GEN-LAST:event_txtRiesgoKeyPressed

    private void txtOcupacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacionKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtOcupacionKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtCelular.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            btnGuardar.doClick();
        }
    }//GEN-LAST:event_txtCelularKeyPressed

    private void txtNombre1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyReleased
        txtNombre1.setText(txtNombre1.getText().toUpperCase());
    }//GEN-LAST:event_txtNombre1KeyReleased

    private void txtNombre2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre2KeyReleased
        txtNombre2.setText(txtNombre2.getText().toUpperCase());
    }//GEN-LAST:event_txtNombre2KeyReleased

    private void txtNombre3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre3KeyReleased
        txtNombre3.setText(txtNombre3.getText().toUpperCase());
    }//GEN-LAST:event_txtNombre3KeyReleased

    private void txtApellidoPatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPatKeyReleased
        txtApellidoPat.setText(txtApellidoPat.getText().toUpperCase());
    }//GEN-LAST:event_txtApellidoPatKeyReleased

    private void txtNacionalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyReleased
        txtNacionalidad.setText(txtNacionalidad.getText().toUpperCase());
    }//GEN-LAST:event_txtNacionalidadKeyReleased

    private void txtReligionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligionKeyReleased
    }//GEN-LAST:event_txtReligionKeyReleased

    private void txtGrupoSanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoSanKeyReleased
        txtGrupoSan.setText(txtGrupoSan.getText().toUpperCase());
    }//GEN-LAST:event_txtGrupoSanKeyReleased

    private void txtGradoInsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradoInsKeyReleased
        txtGradoIns.setText(txtGradoIns.getText().toUpperCase());
    }//GEN-LAST:event_txtGradoInsKeyReleased

    private void txtRiesgoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRiesgoKeyReleased
        txtRiesgo.setText(txtRiesgo.getText().toUpperCase());
    }//GEN-LAST:event_txtRiesgoKeyReleased

    private void txtOcupacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacionKeyReleased
        txtOcupacion.setText(txtOcupacion.getText().toUpperCase());
    }//GEN-LAST:event_txtOcupacionKeyReleased
    
    //HORA
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
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
            java.util.logging.Logger.getLogger(FrmNuevaHistoriaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmNuevaHistoriaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmNuevaHistoriaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNuevaHistoriaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmNuevaHistoriaC().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarHC;
    private javax.swing.JDialog ReasignarHC;
    private javax.swing.JButton btnAnadirRe;
    private javax.swing.JButton btnAsignarDistrito;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarHc;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    public static javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReasignar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public static javax.swing.JComboBox<String> cbxDepartamento;
    public static javax.swing.JComboBox<String> cbxDepartamentoNac;
    public static javax.swing.JComboBox<String> cbxDireccion;
    public static javax.swing.JComboBox<String> cbxDistrito;
    public static javax.swing.JComboBox<String> cbxDistritoNac;
    public static javax.swing.JComboBox<String> cbxEstadoCivil;
    public static javax.swing.JComboBox<String> cbxProvincia;
    public static javax.swing.JComboBox<String> cbxProvinciaNac;
    public static javax.swing.JComboBox<String> cbxSector;
    private javax.swing.JComboBox<String> cbxTipoBusqueda;
    public static javax.swing.JComboBox<String> cbxTipoDireccion;
    private javax.swing.JRadioButton chkA;
    private javax.swing.JRadioButton chkD;
    private javax.swing.JRadioButton chkT;
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
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCodigoR;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblLt;
    private javax.swing.JLabel lblNumero;
    public static javax.swing.JLabel lblUsuUsuario;
    public static javax.swing.JLabel lblUsuUsuario1;
    public static javax.swing.JRadioButton rbtFemenino;
    public static javax.swing.JRadioButton rbtMasculino;
    private javax.swing.JTable tbHistoriaC;
    private javax.swing.JTable tbReasignado;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    public static javax.swing.JTextField txtApellidoMat;
    public static javax.swing.JTextField txtApellidoPat;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarCodigoHC;
    public static javax.swing.JTextField txtCelular;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtEstado;
    public static javax.swing.JTextField txtFechaNac;
    public static javax.swing.JTextField txtGradoIns;
    public static javax.swing.JTextField txtGrupoSan;
    private javax.swing.JTextField txtHuella;
    public static javax.swing.JTextField txtID;
    public static javax.swing.JTextField txtLote;
    public static javax.swing.JTextField txtNacionalidad;
    public static javax.swing.JTextField txtNombre1;
    public static javax.swing.JTextField txtNombre2;
    public static javax.swing.JTextField txtNombre3;
    public static javax.swing.JTextField txtNumero;
    public static javax.swing.JTextField txtOcupacion;
    public static javax.swing.JTextField txtReligion;
    public static javax.swing.JTextField txtRiesgo;
    public static javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
