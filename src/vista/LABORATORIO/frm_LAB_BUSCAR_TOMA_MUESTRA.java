
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.LABORATORIO.Celda_Checkox;
import modelos.LABORATORIO.LAB_Analisis_Detalle;
import modelos.LABORATORIO.LAB_Analisis_Examen;
import modelos.LABORATORIO.LAB_Clasificacion_Examen;
import modelos.LABORATORIO.LAB_Muestra_Examen;
import modelos.LABORATORIO.LAB_PC_AREA;
import modelos.LABORATORIO.LAB_Resultado_Muestra_Cabecera;
import modelos.LABORATORIO.Render_Checkbox;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
import static vista.LABORATORIO.frm_LAB_TOMA_MUESTRA_CABECERA.lblDocumento;
import static vista.Principal.fechaActual;
import vista.frmlaboratorioClinico;
import vista.LABORATORIO.frm_LAB_RESULTADO_MUESTRA;
/**
 *
 * @author PC-SISTEMA
 */
public class frm_LAB_BUSCAR_TOMA_MUESTRA extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,resultado;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_BUSCAR_TOMA_MUESTRA() {
        initComponents();
        c.conectar();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/laboratorio.png")).getImage());
     
        h1 = new Thread(this);
        h1.start();
        buscar_HC.getContentPane().setBackground(Color.white); 
        buscar_HC.setLocationRelativeTo(null);
        personal.getContentPane().setBackground(Color.white);
        personal.setLocationRelativeTo(null);
        analisis.getContentPane().setBackground(Color.white);
        analisis.setLocationRelativeTo(null);
        VerTomaM.getContentPane().setBackground(Color.white);
        VerTomaM.setLocationRelativeTo(null);
        panelPaciente.setBackground(Color.white); 
        panelTM.setBackground(Color.white); 
        panelAnalisis.setBackground(Color.white); 
       
        panelPacientes.setBackground(Color.white);
         chPacientes.setVisible(false);
        txtPacientes.setVisible(false);
            btnPacientes.setVisible(false);
            chAnalisis.setVisible(false);
            txtAnalisis.setVisible(false);
            btnAnalisis.setVisible(false);
            chPersonal.setVisible(false);
            txtPersonal.setVisible(false);
            btnPersonal.setVisible(false);
            chActoMedico.setVisible(false);
            txtActoM.setVisible(false);
            
            btnBuscar.setVisible(false);

        lblServicio.setVisible(false);
        lblArea.setVisible(false);
        this.getContentPane().setBackground(Color.white); 
       
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        buscar_HC.setResizable(false);
        dateDesde.setBackground(Color.white);
        dateHasta.setBackground(Color.white);
        //fecha
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
//        lblFecha.setText(fechaActual());
       
    dateDesde.requestFocus();
  
 
    //inicio
    txtPacientes.setEnabled(false);
            btnPacientes.setEnabled(false);
            txtAnalisis.setEnabled(false);
            btnAnalisis.setEnabled(false);
            txtPersonal.setEnabled(false);
            btnPersonal.setEnabled(false);
            txtActoM.setEnabled(false);
    //Servicio-Area
        LAB_PC_AREA pa=new LAB_PC_AREA();
        lblServicio.setText(pa.LAB_PC_AREA_SERVICIO());
        lblArea.setText("");
       LAB_BUSCAR_TM_DIA();
      formato();
   //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                dispose();
            } });
    }
    
    public void formato(){
    tb_TomasRealizadas.getColumnModel().getColumn(4).setPreferredWidth(200);
    tb_TomasRealizadas.getColumnModel().getColumn(5).setPreferredWidth(90);
    tb_TomasRealizadas.getColumnModel().getColumn(6).setPreferredWidth(250);
    tb_TomasRealizadas.getColumnModel().getColumn(7).setPreferredWidth(240);
    tb_TomasRealizadas.getColumnModel().getColumn(8).setPreferredWidth(100);
    tb_TomasRealizadas.getColumnModel().getColumn(9).setPreferredWidth(90);
    tb_TomasRealizadas.getColumnModel().getColumn(10).setPreferredWidth(250);
    tb_TomasRealizadas.getColumnModel().getColumn(11).setPreferredWidth(85);
    tb_TomasRealizadas.getColumnModel().getColumn(12).setPreferredWidth(140);
    tb_TomasRealizadas.getColumnModel().getColumn(13).setPreferredWidth(50);
    tb_TomasRealizadas.getColumnModel().getColumn(14).setPreferredWidth(50);
    tb_TomasRealizadas.getColumnModel().getColumn(15).setPreferredWidth(130);
    tb_TomasRealizadas.getColumnModel().getColumn(16).setPreferredWidth(130);
    tb_TomasRealizadas.getColumnModel().getColumn(17).setPreferredWidth(200);
    tb_TomasRealizadas.getColumnModel().getColumn(18).setPreferredWidth(90);
    tb_TomasRealizadas.getColumnModel().getColumn(19).setPreferredWidth(90);
    //Ocultar    
    tb_TomasRealizadas.getColumnModel().getColumn(0).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(1).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(2).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(3).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(20).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(20).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(21).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(21).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(22).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(22).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(23).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(23).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(24).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(24).setMaxWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(25).setMinWidth(0);
    tb_TomasRealizadas.getColumnModel().getColumn(25).setMaxWidth(0);
    tb_TomasRealizadas.getSelectionModel().setSelectionInterval(0, 0);
            tb_TomasRealizadas.requestFocus();
}
    
    public void LAB_BUSCAR_TM_DIA(){
      
        String tipo="",servicioArea="";
        if(lblArea.getText().equalsIgnoreCase("")){
        tipo="9";
        servicioArea=lblServicio.getText();
        }
         
    String consulta="";
        try {
            tb_TomasRealizadas.setModel(new DefaultTableModel());
            String titulos[]={"cod_cab_toma_mu_exa","cod_det_toma_mu_ana","cod_exa_ana","id_hc","Servicio/Área","Código CPT",
                "Nomenclatura" ,"Análisis Examen","Acto Médico","N° de H.C.","Datos del Paciente",
                "DNI","Fecha de Nacimiento","Edad","Sexo","Forma de Pago","N° de Toma de Muestra","Personal Toma de Muestra",
                "Fecha TM","Hora TM","Personal Solicita","Habitacion","Cama","Fecha Orden","Hora Orden",
                "HospiServ"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
//            m=(DefaultTableModel)tb_TomasRealizadas.getModel(); Cuando se va agregando
            String fila[]=new String[30];

            LAB_Analisis_Examen obj=new LAB_Analisis_Examen();
                    consulta="exec sp_LAB_BUSQUEDA_TOMA_MUESTRA ?,?,?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1,0);
            cmd.setInt(2, 0);
            cmd.setString(3, "");
            cmd.setString(4, servicioArea);
            cmd.setString(5, tipo);
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
                fila[20]=r.getString(21);
                fila[21]=r.getString(22);
                fila[22]=r.getString(23);
                fila[23]=r.getString(24);
                fila[24]=r.getString(25);
                fila[25]=r.getString(26);

                m.addRow(fila);
                c++;
            }
            tb_TomasRealizadas.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_TomasRealizadas.setRowSorter(elQueOrdena);
            this.tb_TomasRealizadas.setModel(m);
            
            formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
}
    
    public void LAB_HC_cargar(){
    try {
             String titulos[]={"N°","N° H.C.","Paciente","Dirección","DNI","Sexo","Fecha de Nac.","Edad","COdigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
//            String fila[]=new String[9];
//
//            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
//        String consulta="exec Caja_BuscarHC ?";
//        
//        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
//            cmd.setString(1, "");
//        ResultSet r=cmd.executeQuery();
//        int c=1;
//        while(r.next()){
//            fila[0]=String.valueOf(c)+"º";
//            fila[1]=r.getString(1);
//            fila[2]=r.getString(2);
//            fila[3]=r.getString(3);
//            fila[4]=r.getString(4);
//            fila[5]=r.getString(5);
//            fila[6]=r.getString(6);
//             fila[7]=r.getString(7);
//              fila[8]=r.getString(8);
//                m.addRow(fila);
//                c++;
//            }
            tb_HC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_HC.setRowSorter(elQueOrdena);
            this.tb_HC.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_HC_formato(){
    tb_HC.getColumnModel().getColumn(0).setPreferredWidth(40);
    tb_HC.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(2).setPreferredWidth(230);
    tb_HC.getColumnModel().getColumn(3).setPreferredWidth(180);
    tb_HC.getColumnModel().getColumn(4).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(5).setPreferredWidth(80);
    tb_HC.getColumnModel().getColumn(6).setPreferredWidth(150);
    tb_HC.getColumnModel().getColumn(7).setPreferredWidth(100);
    
            //Ocultar    
    tb_HC.getColumnModel().getColumn(8).setMinWidth(0);
    tb_HC.getColumnModel().getColumn(8).setMaxWidth(0);
    tb_HC.getSelectionModel().setSelectionInterval(0, 0);
            tb_HC.requestFocus();
}
    
 public void Personal_cargar(){

    try {
             String titulos[]={"N°","Código","Apellido Paterno","Apellido Materno","Nombres","Cargo","Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            LAB_Resultado_Muestra_Cabecera obj=new LAB_Resultado_Muestra_Cabecera();
            
        String consulta="exec sp_LAB_PERSONAL ?,?,?";
       PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, "");
            cmd.setString(2, lblServicio.getText());
            cmd.setString(3, "4");
        ResultSet r=cmd.executeQuery();
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
            fila[6]=r.getString(6);
                m.addRow(fila);
                c++;
            }
            tbPersonal.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPersonal.setRowSorter(elQueOrdena);
            this.tbPersonal.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e.getMessage());
    }
}
    public void Personal_formato(){
    tbPersonal.getColumnModel().getColumn(0).setPreferredWidth(40);
    tbPersonal.getColumnModel().getColumn(1).setPreferredWidth(100);
    tbPersonal.getColumnModel().getColumn(2).setPreferredWidth(120);
    tbPersonal.getColumnModel().getColumn(3).setPreferredWidth(120);
    tbPersonal.getColumnModel().getColumn(4).setPreferredWidth(200);
    tbPersonal.getColumnModel().getColumn(5).setPreferredWidth(250);
    tbPersonal.getColumnModel().getColumn(6).setPreferredWidth(140);
    
    tbPersonal.getSelectionModel().setSelectionInterval(0, 0);
            tbPersonal.requestFocus();
}

    
    public void Analisis_cargar(){
            String serArea="",tipo="";
            if(lblArea.getText().equalsIgnoreCase("")){
                serArea=lblServicio.getText();
                tipo="1";
            }else{
                serArea=lblArea.getText();
                tipo="4";
            }
            
            
            try {
            String titulos[]={"N°","Código","Código CPT","Análisis","Servicio","Área"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            LAB_Resultado_Muestra_Cabecera obj=new LAB_Resultado_Muestra_Cabecera ();

            String consulta="exec sp_LAB_RESULTADO_TM_ANALISIS ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, "");
            cmd.setString(2, serArea);
            cmd.setString(3, tipo);
            ResultSet r=cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                m.addRow(fila);
                c++;
            }
            tb_Analisis.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Analisis.setRowSorter(elQueOrdena);
            this.tb_Analisis.setModel(m);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }
     public void Analisis_formato(){
    tb_Analisis.getColumnModel().getColumn(0).setPreferredWidth(40);
    tb_Analisis.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Analisis.getColumnModel().getColumn(2).setPreferredWidth(100);
    tb_Analisis.getColumnModel().getColumn(3).setPreferredWidth(250);
    tb_Analisis.getColumnModel().getColumn(4).setPreferredWidth(140);
    tb_Analisis.getColumnModel().getColumn(5).setPreferredWidth(130);
  
    tb_Analisis.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Analisis.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Analisis.getSelectionModel().setSelectionInterval(0, 0);
            tb_Analisis.requestFocus();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscar_HC = new javax.swing.JDialog();
        btnBuscar1 = new javax.swing.JButton();
        txtbuscarHC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_HC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jpanel3 = new javax.swing.JPanel();
            titulo8 = new javax.swing.JLabel();
            jLabel40 = new javax.swing.JLabel();
            personal = new javax.swing.JDialog();
            txtBuscarPersonal = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            btnBuscarPersonal = new javax.swing.JButton();
            jScrollPane2 = new javax.swing.JScrollPane();
            tbPersonal = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                cbxBuscarPersonal = new javax.swing.JComboBox();
                jpanel2 = new javax.swing.JPanel();
                titulo7 = new javax.swing.JLabel();
                analisis = new javax.swing.JDialog();
                jScrollPane3 = new javax.swing.JScrollPane();
                tb_Analisis = new javax.swing.JTable();
                jpanel1 = new javax.swing.JPanel();
                titulo6 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                cbxBuscarAnalisis = new javax.swing.JComboBox();
                txtBuscarAnalisis = new javax.swing.JTextField();
                btnBuscarAnalisis = new javax.swing.JButton();
                jPopupMenu1 = new javax.swing.JPopupMenu();
                muestras = new javax.swing.JMenuItem();
                VerTomaM = new javax.swing.JDialog();
                panelTM = new javax.swing.JPanel();
                jLabel25 = new javax.swing.JLabel();
                txtActoMedico = new javax.swing.JTextField();
                txtFormaPago = new javax.swing.JTextField();
                jLabel30 = new javax.swing.JLabel();
                jLabel29 = new javax.swing.JLabel();
                txtNToma = new javax.swing.JTextField();
                jLabel26 = new javax.swing.JLabel();
                txtPersonalTomaMuestra = new javax.swing.JTextField();
                jLabel27 = new javax.swing.JLabel();
                txtFechaTM = new javax.swing.JTextField();
                jLabel28 = new javax.swing.JLabel();
                txtHoraTM = new javax.swing.JTextField();
                jLabel31 = new javax.swing.JLabel();
                txtPersonalSolicita = new javax.swing.JTextField();
                jLabel32 = new javax.swing.JLabel();
                txtPiso = new javax.swing.JTextField();
                jLabel33 = new javax.swing.JLabel();
                txtCama = new javax.swing.JTextField();
                jLabel34 = new javax.swing.JLabel();
                jLabel37 = new javax.swing.JLabel();
                txtFechaOrden = new javax.swing.JTextField();
                txtHoraOrden = new javax.swing.JTextField();
                txthospiServ = new javax.swing.JTextField();
                jLabel36 = new javax.swing.JLabel();
                jpanel4 = new javax.swing.JPanel();
                titulo9 = new javax.swing.JLabel();
                jScrollPane4 = new javax.swing.JScrollPane();
                tb_muestras = new javax.swing.JTable();
                panelPaciente = new javax.swing.JPanel();
                jLabel23 = new javax.swing.JLabel();
                txtDni = new javax.swing.JTextField();
                jLabel22 = new javax.swing.JLabel();
                txtHc = new javax.swing.JTextField();
                txtPacientes2 = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                txtFecha = new javax.swing.JTextField();
                jLabel18 = new javax.swing.JLabel();
                txtSexo = new javax.swing.JTextField();
                jLabel24 = new javax.swing.JLabel();
                txtEdad = new javax.swing.JTextField();
                panelAnalisis = new javax.swing.JPanel();
                txtNomenclatura = new javax.swing.JTextField();
                jLabel35 = new javax.swing.JLabel();
                jLabel38 = new javax.swing.JLabel();
                txtCPT = new javax.swing.JTextField();
                jLabel39 = new javax.swing.JLabel();
                txtServArea = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                jpanel = new javax.swing.JPanel();
                titulo5 = new javax.swing.JLabel();
                lblUsu = new javax.swing.JLabel();
                jScrollPane5 = new javax.swing.JScrollPane();
                tb_TomasRealizadas = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        if(colIndex==0){
                            return true;
                        }else{
                            return false; //Disallow the editing of any cell
                        }}};
                        lblServicio = new javax.swing.JLabel();
                        lblArea = new javax.swing.JLabel();
                        panelPacientes = new javax.swing.JPanel();
                        chPacientes = new javax.swing.JCheckBox();
                        txtPacientes = new javax.swing.JTextField();
                        btnPacientes = new javax.swing.JButton();
                        chAnalisis = new javax.swing.JCheckBox();
                        txtAnalisis = new javax.swing.JTextField();
                        btnAnalisis = new javax.swing.JButton();
                        chPersonal = new javax.swing.JCheckBox();
                        txtPersonal = new javax.swing.JTextField();
                        btnPersonal = new javax.swing.JButton();
                        btnBuscar = new javax.swing.JButton();
                        chActoMedico = new javax.swing.JCheckBox();
                        txtActoM = new javax.swing.JTextField();
                        dateDesde = new com.toedter.calendar.JDateChooser();
                        dateHasta = new com.toedter.calendar.JDateChooser();
                        jLabel20 = new javax.swing.JLabel();
                        jLabel21 = new javax.swing.JLabel();
                        cbx = new javax.swing.JComboBox();
                        jLabel19 = new javax.swing.JLabel();
                        lbldia = new javax.swing.JLabel();
                        jPanel1 = new javax.swing.JPanel();
                        jLabel3 = new javax.swing.JLabel();
                        jLabel10 = new javax.swing.JLabel();

                        buscar_HC.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        buscar_HC.setAlwaysOnTop(true);
                        buscar_HC.setMinimumSize(new java.awt.Dimension(876, 692));

                        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                        btnBuscar1.setBorder(null);
                        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscar1ActionPerformed(evt);
                            }
                        });

                        txtbuscarHC.setForeground(new java.awt.Color(0, 51, 51));
                        txtbuscarHC.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtbuscarHCActionPerformed(evt);
                            }
                        });
                        txtbuscarHC.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtbuscarHCKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtbuscarHCKeyTyped(evt);
                            }
                        });

                        tb_HC.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_HC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_HC.setRowHeight(25);
                        tb_HC.getTableHeader().setReorderingAllowed(false);
                        tb_HC.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_HCMouseClicked(evt);
                            }
                        });
                        tb_HC.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_HCKeyPressed(evt);
                            }
                        });
                        jScrollPane1.setViewportView(tb_HC);

                        jpanel3.setBackground(new java.awt.Color(2, 67, 115));

                        titulo8.setBackground(new java.awt.Color(0, 102, 102));
                        titulo8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                        titulo8.setForeground(new java.awt.Color(255, 255, 255));
                        titulo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        titulo8.setText("Historia Clínica");
                        titulo8.setToolTipText("");
                        titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                        javax.swing.GroupLayout jpanel3Layout = new javax.swing.GroupLayout(jpanel3);
                        jpanel3.setLayout(jpanel3Layout);
                        jpanel3Layout.setHorizontalGroup(
                            jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                        );
                        jpanel3Layout.setVerticalGroup(
                            jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );

                        jLabel40.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
                        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel40.setText("Búsqueda por H.C / Paciente / DNI");

                        javax.swing.GroupLayout buscar_HCLayout = new javax.swing.GroupLayout(buscar_HC.getContentPane());
                        buscar_HC.getContentPane().setLayout(buscar_HCLayout);
                        buscar_HCLayout.setHorizontalGroup(
                            buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buscar_HCLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1)
                                .addGap(19, 19, 19))
                            .addGroup(buscar_HCLayout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtbuscarHC, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(258, Short.MAX_VALUE))
                            .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jpanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        buscar_HCLayout.setVerticalGroup(
                            buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_HCLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jLabel40)
                                .addGap(2, 2, 2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(41, Short.MAX_VALUE))
                            .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(buscar_HCLayout.createSequentialGroup()
                                    .addComponent(jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 644, Short.MAX_VALUE)))
                        );

                        personal.setAlwaysOnTop(true);
                        personal.setMinimumSize(new java.awt.Dimension(846, 504));

                        txtBuscarPersonal.setForeground(new java.awt.Color(0, 51, 51));
                        txtBuscarPersonal.setEnabled(false);
                        txtBuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscarPersonalActionPerformed(evt);
                            }
                        });
                        txtBuscarPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscarPersonalKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscarPersonalKeyTyped(evt);
                            }
                        });

                        jLabel2.setText("Búsqueda por:");

                        btnBuscarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                        btnBuscarPersonal.setBorder(null);
                        btnBuscarPersonal.setEnabled(false);
                        btnBuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPersonalActionPerformed(evt);
                            }
                        });

                        tbPersonal.setModel(new javax.swing.table.DefaultTableModel(
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
                        tbPersonal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tbPersonal.setRowHeight(25);
                        tbPersonal.getTableHeader().setReorderingAllowed(false);
                        tbPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tbPersonalMouseClicked(evt);
                            }
                        });
                        tbPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tbPersonalKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                tbPersonalKeyTyped(evt);
                            }
                        });
                        jScrollPane2.setViewportView(tbPersonal);

                        cbxBuscarPersonal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Cargo", "Apellidos y Nombres" }));
                        cbxBuscarPersonal.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbxBuscarPersonalItemStateChanged(evt);
                            }
                        });

                        jpanel2.setBackground(new java.awt.Color(2, 67, 115));

                        titulo7.setBackground(new java.awt.Color(0, 102, 102));
                        titulo7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                        titulo7.setForeground(new java.awt.Color(255, 255, 255));
                        titulo7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        titulo7.setText("Personal");
                        titulo7.setToolTipText("");
                        titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                        javax.swing.GroupLayout jpanel2Layout = new javax.swing.GroupLayout(jpanel2);
                        jpanel2.setLayout(jpanel2Layout);
                        jpanel2Layout.setHorizontalGroup(
                            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        jpanel2Layout.setVerticalGroup(
                            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );

                        javax.swing.GroupLayout personalLayout = new javax.swing.GroupLayout(personal.getContentPane());
                        personal.getContentPane().setLayout(personalLayout);
                        personalLayout.setHorizontalGroup(
                            personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personalLayout.createSequentialGroup()
                                .addGroup(personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(personalLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(personalLayout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(158, 158, 158)
                                        .addComponent(txtBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(26, Short.MAX_VALUE))
                            .addComponent(jpanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        personalLayout.setVerticalGroup(
                            personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalLayout.createSequentialGroup()
                                .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbxBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(47, Short.MAX_VALUE))
                        );

                        analisis.setAlwaysOnTop(true);
                        analisis.setMinimumSize(new java.awt.Dimension(710, 435));

                        tb_Analisis.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_Analisis.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_Analisis.setRowHeight(21);
                        tb_Analisis.getTableHeader().setReorderingAllowed(false);
                        tb_Analisis.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_AnalisisKeyPressed(evt);
                            }
                        });
                        jScrollPane3.setViewportView(tb_Analisis);

                        jpanel1.setBackground(new java.awt.Color(2, 67, 115));

                        titulo6.setBackground(new java.awt.Color(0, 102, 102));
                        titulo6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                        titulo6.setForeground(new java.awt.Color(255, 255, 255));
                        titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        titulo6.setText("Análisis");
                        titulo6.setToolTipText("");
                        titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                        javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
                        jpanel1.setLayout(jpanel1Layout);
                        jpanel1Layout.setHorizontalGroup(
                            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        jpanel1Layout.setVerticalGroup(
                            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel1Layout.createSequentialGroup()
                                .addComponent(titulo6)
                                .addGap(0, 0, Short.MAX_VALUE))
                        );

                        jLabel6.setText("Búsqueda por:");

                        cbxBuscarAnalisis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Código CPT", "Análisis" }));
                        cbxBuscarAnalisis.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbxBuscarAnalisisItemStateChanged(evt);
                            }
                        });

                        txtBuscarAnalisis.setForeground(new java.awt.Color(0, 51, 51));
                        txtBuscarAnalisis.setEnabled(false);
                        txtBuscarAnalisis.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscarAnalisisActionPerformed(evt);
                            }
                        });
                        txtBuscarAnalisis.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscarAnalisisKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscarAnalisisKeyTyped(evt);
                            }
                        });

                        btnBuscarAnalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                        btnBuscarAnalisis.setBorder(null);
                        btnBuscarAnalisis.setEnabled(false);
                        btnBuscarAnalisis.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarAnalisisActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout analisisLayout = new javax.swing.GroupLayout(analisis.getContentPane());
                        analisis.getContentPane().setLayout(analisisLayout);
                        analisisLayout.setHorizontalGroup(
                            analisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(analisisLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(analisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(analisisLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxBuscarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(131, 131, 131)
                                        .addComponent(txtBuscarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBuscarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(27, Short.MAX_VALUE))
                        );
                        analisisLayout.setVerticalGroup(
                            analisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, analisisLayout.createSequentialGroup()
                                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(analisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, analisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbxBuscarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                        );

                        muestras.setText("Ver Toma de Muestra");
                        muestras.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                muestrasActionPerformed(evt);
                            }
                        });
                        jPopupMenu1.add(muestras);

                        VerTomaM.setAlwaysOnTop(true);
                        VerTomaM.setMinimumSize(new java.awt.Dimension(890, 572));
                        VerTomaM.setResizable(false);

                        panelTM.setBorder(javax.swing.BorderFactory.createTitledBorder("Toma de Muestra"));

                        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel25.setText("Acto Médico");

                        txtActoMedico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtActoMedico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtActoMedico.setEnabled(false);

                        txtFormaPago.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtFormaPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtFormaPago.setEnabled(false);
                        txtFormaPago.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtFormaPagoActionPerformed(evt);
                            }
                        });
                        txtFormaPago.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtFormaPagoKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtFormaPagoKeyReleased(evt);
                            }
                        });

                        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel30.setText("Forma de Pago");

                        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel29.setText("N° de Toma de Muestra");

                        txtNToma.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtNToma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtNToma.setEnabled(false);
                        txtNToma.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtNTomaKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtNTomaKeyReleased(evt);
                            }
                        });

                        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel26.setText("Personal - Toma de Muestra");

                        txtPersonalTomaMuestra.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
                        txtPersonalTomaMuestra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtPersonalTomaMuestra.setEnabled(false);
                        txtPersonalTomaMuestra.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtPersonalTomaMuestraActionPerformed(evt);
                            }
                        });
                        txtPersonalTomaMuestra.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtPersonalTomaMuestraKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtPersonalTomaMuestraKeyReleased(evt);
                            }
                        });

                        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel27.setText("Fecha Toma Muestra");

                        txtFechaTM.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtFechaTM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtFechaTM.setEnabled(false);
                        txtFechaTM.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtFechaTMActionPerformed(evt);
                            }
                        });
                        txtFechaTM.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtFechaTMKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtFechaTMKeyReleased(evt);
                            }
                        });

                        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel28.setText("Hora Toma Muestra");

                        txtHoraTM.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtHoraTM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtHoraTM.setEnabled(false);
                        txtHoraTM.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtHoraTMActionPerformed(evt);
                            }
                        });
                        txtHoraTM.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtHoraTMKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtHoraTMKeyReleased(evt);
                            }
                        });

                        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel31.setText("Personal - Solicita Muestra");

                        txtPersonalSolicita.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
                        txtPersonalSolicita.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtPersonalSolicita.setEnabled(false);
                        txtPersonalSolicita.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtPersonalSolicitaActionPerformed(evt);
                            }
                        });
                        txtPersonalSolicita.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtPersonalSolicitaKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtPersonalSolicitaKeyReleased(evt);
                            }
                        });

                        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel32.setText("Piso");

                        txtPiso.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtPiso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtPiso.setEnabled(false);
                        txtPiso.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtPisoActionPerformed(evt);
                            }
                        });
                        txtPiso.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtPisoKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtPisoKeyReleased(evt);
                            }
                        });

                        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel33.setText("Cama");

                        txtCama.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtCama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtCama.setEnabled(false);
                        txtCama.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtCamaActionPerformed(evt);
                            }
                        });
                        txtCama.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtCamaKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtCamaKeyReleased(evt);
                            }
                        });

                        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel34.setText("Fecha Orden");

                        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel37.setText("Hora Orden");

                        txtFechaOrden.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtFechaOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtFechaOrden.setEnabled(false);
                        txtFechaOrden.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtFechaOrdenActionPerformed(evt);
                            }
                        });
                        txtFechaOrden.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtFechaOrdenKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtFechaOrdenKeyReleased(evt);
                            }
                        });

                        txtHoraOrden.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtHoraOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtHoraOrden.setEnabled(false);
                        txtHoraOrden.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtHoraOrdenActionPerformed(evt);
                            }
                        });
                        txtHoraOrden.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtHoraOrdenKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtHoraOrdenKeyReleased(evt);
                            }
                        });

                        txthospiServ.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txthospiServ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txthospiServ.setEnabled(false);
                        txthospiServ.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txthospiServActionPerformed(evt);
                            }
                        });
                        txthospiServ.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txthospiServKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txthospiServKeyReleased(evt);
                            }
                        });

                        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel36.setText("Hospitalización-Servicio");

                        javax.swing.GroupLayout panelTMLayout = new javax.swing.GroupLayout(panelTM);
                        panelTM.setLayout(panelTMLayout);
                        panelTMLayout.setHorizontalGroup(
                            panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTMLayout.createSequentialGroup()
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelTMLayout.createSequentialGroup()
                                            .addGap(114, 114, 114)
                                            .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTMLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtPersonalSolicita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panelTMLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPersonalTomaMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(28, 28, 28)
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFechaTM, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                        .addComponent(txtNToma, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTMLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHoraOrden)
                                            .addComponent(txtFormaPago, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(panelTMLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtHoraTM)))
                                .addGap(24, 24, 24)
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txthospiServ)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelTMLayout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPiso)
                                    .addComponent(txtCama))
                                .addContainerGap())
                        );
                        panelTMLayout.setVerticalGroup(
                            panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTMLayout.createSequentialGroup()
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36))
                                .addGap(0, 0, 0)
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNToma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txthospiServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel32))
                                .addGap(0, 0, 0)
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPersonalTomaMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoraTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel37)))
                                .addGap(0, 0, 0)
                                .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPersonalSolicita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHoraOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0))
                        );

                        jpanel4.setBackground(new java.awt.Color(2, 67, 115));

                        titulo9.setBackground(new java.awt.Color(0, 102, 102));
                        titulo9.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                        titulo9.setForeground(new java.awt.Color(255, 255, 255));
                        titulo9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        titulo9.setText("Toma de Muestra");
                        titulo9.setToolTipText("");
                        titulo9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                        javax.swing.GroupLayout jpanel4Layout = new javax.swing.GroupLayout(jpanel4);
                        jpanel4.setLayout(jpanel4Layout);
                        jpanel4Layout.setHorizontalGroup(
                            jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(titulo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        );
                        jpanel4Layout.setVerticalGroup(
                            jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel4Layout.createSequentialGroup()
                                .addComponent(titulo9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        );

                        tb_muestras.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "cod_det_toma_mu_ana", "codMuestra", "codContenedor", "codArea", "Muestra", "Contenedor", "Area", "Cantidad", "Codigo_Barras"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, true, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tb_muestras.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_muestras.setRowHeight(28);
                        tb_muestras.getTableHeader().setReorderingAllowed(false);
                        jScrollPane4.setViewportView(tb_muestras);

                        panelPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Paciente"));

                        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel23.setText("DNI");

                        txtDni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtDni.setEnabled(false);

                        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel22.setText("Nº de H.C.");

                        txtHc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtHc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtHc.setEnabled(false);

                        txtPacientes2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtPacientes2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtPacientes2.setEnabled(false);

                        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel7.setText("Paciente");

                        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel17.setText("Fecha de Nac.");

                        txtFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtFecha.setEnabled(false);

                        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel18.setText("Sexo");

                        txtSexo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtSexo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtSexo.setEnabled(false);

                        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel24.setText("Edad");

                        txtEdad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtEdad.setEnabled(false);

                        javax.swing.GroupLayout panelPacienteLayout = new javax.swing.GroupLayout(panelPaciente);
                        panelPaciente.setLayout(panelPacienteLayout);
                        panelPacienteLayout.setHorizontalGroup(
                            panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDni)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                    .addComponent(txtHc, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPacientes2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtEdad)
                                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtSexo)
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))))))
                                .addContainerGap())
                        );
                        panelPacienteLayout.setVerticalGroup(
                            panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel7))
                                .addGap(0, 0, 0)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtHc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPacientes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18))
                                        .addGap(0, 0, 0)
                                        .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSexo))))
                                .addGap(0, 0, Short.MAX_VALUE))
                        );

                        panelAnalisis.setBorder(javax.swing.BorderFactory.createTitledBorder("Análisis"));

                        txtNomenclatura.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtNomenclatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtNomenclatura.setEnabled(false);
                        txtNomenclatura.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtNomenclaturaActionPerformed(evt);
                            }
                        });
                        txtNomenclatura.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtNomenclaturaKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtNomenclaturaKeyReleased(evt);
                            }
                        });

                        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel35.setText("Nomenclatura");

                        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel38.setText("Código CPT");

                        txtCPT.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                        txtCPT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtCPT.setEnabled(false);
                        txtCPT.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtCPTActionPerformed(evt);
                            }
                        });
                        txtCPT.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtCPTKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtCPTKeyReleased(evt);
                            }
                        });

                        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel39.setText("Servicio/Área");

                        txtServArea.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtServArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtServArea.setEnabled(false);
                        txtServArea.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtServAreaKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtServAreaKeyReleased(evt);
                            }
                        });

                        javax.swing.GroupLayout panelAnalisisLayout = new javax.swing.GroupLayout(panelAnalisis);
                        panelAnalisis.setLayout(panelAnalisisLayout);
                        panelAnalisisLayout.setHorizontalGroup(
                            panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                            .addComponent(txtNomenclatura)))
                                    .addComponent(txtServArea)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        panelAnalisisLayout.setVerticalGroup(
                            panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jLabel39)
                                .addGap(0, 0, 0)
                                .addComponent(txtServArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel38)
                                        .addGroup(panelAnalisisLayout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtNomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCPT))))
                                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21))))
                        );

                        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
                        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel1.setText("Muestra(s)");

                        javax.swing.GroupLayout VerTomaMLayout = new javax.swing.GroupLayout(VerTomaM.getContentPane());
                        VerTomaM.getContentPane().setLayout(VerTomaMLayout);
                        VerTomaMLayout.setHorizontalGroup(
                            VerTomaMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VerTomaMLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(VerTomaMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelTM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, VerTomaMLayout.createSequentialGroup()
                                        .addComponent(panelPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(panelAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(32, 32, 32))
                        );
                        VerTomaMLayout.setVerticalGroup(
                            VerTomaMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VerTomaMLayout.createSequentialGroup()
                                .addComponent(jpanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(VerTomaMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panelPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(2, 2, 2)
                                .addComponent(panelTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel1)
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122))
                        );

                        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        setMinimumSize(new java.awt.Dimension(1067, 665));
                        setPreferredSize(new java.awt.Dimension(1067, 665));

                        jpanel.setBackground(new java.awt.Color(2, 67, 115));

                        titulo5.setBackground(new java.awt.Color(0, 102, 102));
                        titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                        titulo5.setForeground(new java.awt.Color(255, 255, 255));
                        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        titulo5.setText("Tomas de Muestras Realizadas");
                        titulo5.setToolTipText("");
                        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                        lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
                        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
                        lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                        lblUsu.setText("Usuario");

                        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                        jpanel.setLayout(jpanelLayout);
                        jpanelLayout.setHorizontalGroup(
                            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(titulo5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                        );
                        jpanelLayout.setVerticalGroup(
                            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(titulo5)
                                    .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 29, Short.MAX_VALUE))
                        );

                        tb_TomasRealizadas.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "cod_cab_toma_mu_exa", "cod_det_toma_mu_ana", "cod_exa_ana", "id_hc", "Servicio/Área", "Código CPT", "Nomenclatura", "Analisis Examen", "N° de Toma de Muestra", "N° de H.C", "Datos del Paciente", "DNI", "Fecha de Nacimiento", "Edad", "Sexo", "Forma de Pago", "Acto Medico", "Nombre Per Toma Muestra", "fecha_toma_mu_de", "hora_toma_mu_de", "nom_per_solicita", "hab_nom", "cama", "fecha_orden", "hora_orden", "id_cod_doc_det", "Años", "Meses", "Dias"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                true, false, true, true, false, true, true, false, true, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, false, true, true, true
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tb_TomasRealizadas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_TomasRealizadas.setComponentPopupMenu(jPopupMenu1);
                        tb_TomasRealizadas.setRowHeight(24);
                        tb_TomasRealizadas.setSelectionBackground(new java.awt.Color(2, 67, 115));
                        tb_TomasRealizadas.getTableHeader().setReorderingAllowed(false);
                        tb_TomasRealizadas.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_TomasRealizadasMouseClicked(evt);
                            }
                            public void mouseReleased(java.awt.event.MouseEvent evt) {
                                tb_TomasRealizadasMouseReleased(evt);
                            }
                        });
                        tb_TomasRealizadas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                tb_TomasRealizadasPropertyChange(evt);
                            }
                        });
                        tb_TomasRealizadas.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_TomasRealizadasKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                tb_TomasRealizadasKeyTyped(evt);
                            }
                        });
                        jScrollPane5.setViewportView(tb_TomasRealizadas);
                        if (tb_TomasRealizadas.getColumnModel().getColumnCount() > 0) {
                            tb_TomasRealizadas.getColumnModel().getColumn(13).setResizable(false);
                            tb_TomasRealizadas.getColumnModel().getColumn(14).setResizable(false);
                        }

                        lblServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblServicio.setText("servicio");

                        lblArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblArea.setText("area");

                        chPacientes.setText("Todos los Pacientes");
                        chPacientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        chPacientes.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                chPacientesItemStateChanged(evt);
                            }
                        });

                        txtPacientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                        txtPacientes.setForeground(new java.awt.Color(0, 51, 51));
                        txtPacientes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtPacientes.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtPacientesActionPerformed(evt);
                            }
                        });
                        txtPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtPacientesKeyPressed(evt);
                            }
                        });

                        btnPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                        btnPacientes.setBorder(null);
                        btnPacientes.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnPacientesActionPerformed(evt);
                            }
                        });

                        chAnalisis.setText("Todos los Análisis");
                        chAnalisis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        chAnalisis.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                chAnalisisItemStateChanged(evt);
                            }
                        });

                        txtAnalisis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                        txtAnalisis.setForeground(new java.awt.Color(0, 51, 51));
                        txtAnalisis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtAnalisis.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtAnalisisKeyPressed(evt);
                            }
                        });

                        btnAnalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                        btnAnalisis.setBorder(null);
                        btnAnalisis.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAnalisisActionPerformed(evt);
                            }
                        });

                        chPersonal.setText("Todo el Personal");
                        chPersonal.setHideActionText(true);
                        chPersonal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        chPersonal.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                chPersonalItemStateChanged(evt);
                            }
                        });

                        txtPersonal.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                        txtPersonal.setForeground(new java.awt.Color(0, 51, 51));
                        txtPersonal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtPersonalKeyPressed(evt);
                            }
                        });

                        btnPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                        btnPersonal.setBorder(null);
                        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnPersonalActionPerformed(evt);
                            }
                        });

                        btnBuscar.setFont(new java.awt.Font("Segoe UI Light", 1, 11)); // NOI18N
                        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar16x16.png"))); // NOI18N
                        btnBuscar.setMnemonic('B');
                        btnBuscar.setText("Buscar");
                        btnBuscar.setToolTipText("Buscar (Alt-B)");
                        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarActionPerformed(evt);
                            }
                        });

                        chActoMedico.setText("Todos los Actos Médicos");
                        chActoMedico.setHideActionText(true);
                        chActoMedico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        chActoMedico.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                chActoMedicoItemStateChanged(evt);
                            }
                        });

                        txtActoM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                        txtActoM.setForeground(new java.awt.Color(0, 51, 51));
                        txtActoM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtActoM.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtActoMKeyPressed(evt);
                            }
                        });

                        javax.swing.GroupLayout panelPacientesLayout = new javax.swing.GroupLayout(panelPacientes);
                        panelPacientes.setLayout(panelPacientesLayout);
                        panelPacientesLayout.setHorizontalGroup(
                            panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPacientesLayout.createSequentialGroup()
                                .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(chAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(chPacientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(chPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(chActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtAnalisis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(txtPacientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(txtPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(txtActoM, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnPacientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAnalisis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPersonal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar)
                                .addGap(15, 15, 15))
                        );
                        panelPacientesLayout.setVerticalGroup(
                            panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPacientesLayout.createSequentialGroup()
                                .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelPacientesLayout.createSequentialGroup()
                                        .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(chPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAnalisis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(chAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, 0)
                                        .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(panelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(chActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtActoM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, 0))
                        );

                        dateDesde.setDateFormatString("dd-MM-yyyy");

                        dateHasta.setDateFormatString("dd-MM-yyyy");

                        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel20.setText("Hasta");

                        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel21.setText("Búsqueda por:");

                        cbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "N° de H.C / DNI", "Análisis ", "Personal - Toma de Muestra", "Acto Médico" }));
                        cbx.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbxItemStateChanged(evt);
                            }
                        });
                        cbx.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                cbxKeyPressed(evt);
                            }
                        });

                        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel19.setText("Desde");

                        lbldia.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
                        lbldia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lbldia.setText("Exámenes con Toma de Muestras del Día");

                        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
                        jPanel1.setPreferredSize(new java.awt.Dimension(721, 25));

                        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                        jLabel3.setText("Salir (Esc)");

                        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                        jLabel10.setText("Buscar (Alt+B)");

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(850, 850, 850)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(8, 8, 8))
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        );

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(405, 405, 405))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
                                    .addComponent(lbldia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(101, 101, 101)
                                        .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblArea, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbx, 0, 173, Short.MAX_VALUE))
                                        .addGap(84, 84, 84)
                                        .addComponent(panelPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane5))
                                .addGap(28, 28, 28))
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dateDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblArea)
                                        .addComponent(lblServicio))
                                    .addComponent(panelPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lbldia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tb_HC.setModel(new DefaultTableModel());
            String titulos[]={"N°","N° H.C.","Paciente","Dirección","DNI","Sexo","Fecha de Nac.","Edad","COdigo"};
           m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
            consulta="exec Caja_BuscarHC ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtbuscarHC.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
            fila[6]=r.getString(6);
             fila[7]=r.getString(7);
              fila[8]=r.getString(8);
                m.addRow(fila);
                c++;
            }
            tb_HC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_HC.setRowSorter(elQueOrdena);
            this.tb_HC.setModel(m);

            LAB_HC_formato();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtbuscarHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarHCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarHCActionPerformed

    private void txtbuscarHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarHCKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_HC.getSelectionModel().setSelectionInterval(0, 0);
            tb_HC.requestFocus();
        }
    }//GEN-LAST:event_txtbuscarHCKeyPressed

    private void txtbuscarHCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarHCKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscar1.doClick();
        }

    }//GEN-LAST:event_txtbuscarHCKeyTyped

    private void tb_HCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HCMouseClicked

    }//GEN-LAST:event_tb_HCMouseClicked

    private void tb_HCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_HCKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                buscar_HC.setVisible(false);
                int filaselec=tb_HC.getSelectedRow();
             
                
                txtPacientes.setText(tb_HC.getValueAt(filaselec, 1).toString());
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_HCKeyPressed

    private void tb_TomasRealizadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_TomasRealizadasMouseClicked

    }//GEN-LAST:event_tb_TomasRealizadasMouseClicked

    private void tb_TomasRealizadasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_TomasRealizadasMouseReleased
 
    }//GEN-LAST:event_tb_TomasRealizadasMouseReleased

    private void tb_TomasRealizadasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_TomasRealizadasPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_TomasRealizadasPropertyChange

    private void tb_TomasRealizadasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_TomasRealizadasKeyTyped

    }//GEN-LAST:event_tb_TomasRealizadasKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
 try{
          
            if(dateDesde.getDate()==null || dateHasta.getDate()==null){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un rango de Fechas.");
          } else if(cbx.getSelectedIndex()==0){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un tipo de búsqueda");
          }  else if(txtPacientes.getText().isEmpty()&&chPacientes.isSelected()==false&& txtAnalisis.getText().isEmpty()
                  && chAnalisis.isSelected()==false && txtPersonal.getText().isEmpty()&&chPersonal.isSelected()==false
                  &&txtActoM.getText().isEmpty()&&chActoMedico.isSelected()==false){
              JOptionPane.showMessageDialog(rootPane, "Seleccione y/o ingrese un " +cbx.getSelectedItem());
          }  else {
              buscar_examenes();
           
          }  
          }catch(Exception e) {
              JOptionPane.showMessageDialog(this, e.getMessage());
              
          }
    }//GEN-LAST:event_btnBuscarActionPerformed
public void buscar_examenes(){
    DecimalFormat df = new DecimalFormat("00");
        int dia,mes,anio,diah,mesh,anioh;
        dia = dateDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dateDesde.getCalendar().get(Calendar.MONTH) + 1;
        anio = dateDesde.getCalendar().get(Calendar.YEAR);
        diah = dateHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
        mesh = dateHasta.getCalendar().get(Calendar.MONTH) + 1;
        anioh = dateHasta.getCalendar().get(Calendar.YEAR);
        lbldia.setText("Exámenes con Toma de Muestras del "+df.format(dia)+"/"+df.format(mes)+"/"+anio+" al " 
                +df.format(diah)+"/"+df.format(mesh)+"/"+anioh);
        int desde=Integer.parseInt(anio+df.format(mes)+df.format(dia));
        int hasta=Integer.parseInt(anioh+df.format(mesh)+df.format(diah));
        String buscar="",tipo="1",servicioArea="";
        
        
        if((chPacientes.isSelected()==true ||chAnalisis.isSelected()==true ||chPersonal.isSelected()==true
                ||chActoMedico.isSelected()==true ) 
                &&lblArea.getText().equalsIgnoreCase("")){
        tipo="1";
        servicioArea=lblServicio.getText();
        }
        else if(txtPacientes.getText().length()>0 &&lblArea.getText().equalsIgnoreCase("")){
        tipo="2";
         buscar=txtPacientes.getText();
        servicioArea=lblServicio.getText();
        }
        else if(txtAnalisis.getText().length()>0 &&lblArea.getText().equalsIgnoreCase("")){
        tipo="3";
         buscar=txtAnalisis.getText();
        servicioArea=lblServicio.getText();
        }
         else if(txtPersonal.getText().length()>0 &&lblArea.getText().equalsIgnoreCase("")){
        tipo="4";
         buscar=txtPersonal.getText();
        servicioArea=lblServicio.getText();
        }else if(txtActoM.getText().length()>0 &&lblArea.getText().equalsIgnoreCase("")){
        tipo="5";
         buscar=txtActoM.getText();
        servicioArea=lblServicio.getText();
        }
    String consulta="";
        try {
            tb_TomasRealizadas.setModel(new DefaultTableModel());
            String titulos[]={"cod_cab_toma_mu_exa","cod_det_toma_mu_ana","cod_exa_ana","id_hc","Servicio/Área","Código CPT",
                "Nomenclatura" ,"Análisis Examen","Acto Médico","N° de H.C.","Datos del Paciente",
                "DNI","Fecha de Nacimiento","Edad","Sexo","Forma de Pago","N° de Toma de Muestra","Personal Toma de Muestra",
                "Fecha TM","Hora TM","Personal Solicita","Habitacion","Cama","Fecha Orden","Hora Orden",
                "HospiServ"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
//            m=(DefaultTableModel)tb_TomasRealizadas.getModel(); Cuando se va agregando
            String fila[]=new String[30];

            LAB_Analisis_Examen obj=new LAB_Analisis_Examen();
                    consulta="exec sp_LAB_BUSQUEDA_TOMA_MUESTRA ?,?,?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1,desde);
            cmd.setInt(2, hasta);
            cmd.setString(3, buscar);
            cmd.setString(4, servicioArea);
            cmd.setString(5, tipo);
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
                fila[20]=r.getString(21);
                fila[21]=r.getString(22);
                fila[22]=r.getString(23);
                fila[23]=r.getString(24);
                fila[24]=r.getString(25);
                fila[25]=r.getString(26);

                m.addRow(fila);
                c++;
            }
            tb_TomasRealizadas.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_TomasRealizadas.setRowSorter(elQueOrdena);
            this.tb_TomasRealizadas.setModel(m);
            
            formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
}
    private void tb_TomasRealizadasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_TomasRealizadasKeyPressed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_tb_TomasRealizadasKeyPressed

    
    private void chPacientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chPacientesItemStateChanged
        if(chPacientes.isSelected()){
            txtPacientes.setEnabled(false);
            btnPacientes.setEnabled(false);
            txtPacientes.setText("");
            txtAnalisis.setText("");
        }else{
            txtPacientes.setEnabled(true);
            btnPacientes.setEnabled(true);
            txtPacientes.setText("");
            txtAnalisis.setText("");
        }
    }//GEN-LAST:event_chPacientesItemStateChanged
            
    private void btnPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacientesActionPerformed
        buscar_HC.setVisible(true);
        txtbuscarHC.setText("");
        LAB_HC_cargar();
        LAB_HC_formato();
    }//GEN-LAST:event_btnPacientesActionPerformed

    private void chAnalisisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chAnalisisItemStateChanged
        if(chAnalisis.isSelected()){
            txtAnalisis.setEnabled(false);
            btnAnalisis.setEnabled(false);
            txtAnalisis.setText("");
        }else{
            txtAnalisis.setEnabled(true);
            btnAnalisis.setEnabled(true);
        }
    }//GEN-LAST:event_chAnalisisItemStateChanged

    private void btnAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisActionPerformed
                 analisis.setVisible(true);
                    txtBuscarAnalisis.setText("");
                    Analisis_cargar();
                    Analisis_formato();
               
    }//GEN-LAST:event_btnAnalisisActionPerformed

    private void cbxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxItemStateChanged
        if(cbx.getSelectedIndex()==0){
            chPacientes.setSelected(false);
            chAnalisis.setSelected(false);
            chPersonal.setSelected(false);
            chPacientes.setVisible(false);
            txtPacientes.setVisible(false);
            btnPacientes.setVisible(false);
            chAnalisis.setVisible(false);
            txtAnalisis.setVisible(false);
            btnAnalisis.setVisible(false);
            chPersonal.setVisible(false);
            txtPersonal.setVisible(false);
            btnPersonal.setVisible(false);
            chActoMedico.setVisible(false);
            txtActoM.setVisible(false);
            txtPacientes.setText("");
            txtAnalisis.setText("");
            txtPersonal.setText("");
            btnBuscar.setVisible(false);
        }
        else if(cbx.getSelectedIndex()==1){
            chPacientes.setSelected(true);
            chAnalisis.setSelected(false);
            chPersonal.setSelected(false);
            chActoMedico.setSelected(false);
            chPacientes.setVisible(true);
            txtPacientes.setVisible(true);
            btnPacientes.setVisible(true);
            chAnalisis.setVisible(false);
            txtAnalisis.setVisible(false);
            btnAnalisis.setVisible(false);
            chPersonal.setVisible(false);
            txtPersonal.setVisible(false);
            btnPersonal.setVisible(false);
            chActoMedico.setVisible(false);
            txtActoM.setVisible(false);
            txtPacientes.setText("");
            txtAnalisis.setText("");
            txtPersonal.setText("");
            txtActoM.setText("");
            btnBuscar.setVisible(true);
        }else if(cbx.getSelectedIndex()==2){
            chPacientes.setSelected(false);
            chAnalisis.setSelected(true);
            chPersonal.setSelected(false);
            chActoMedico.setSelected(false);
            chPacientes.setVisible(false);
            txtPacientes.setVisible(false);
            btnPacientes.setVisible(false);
            chAnalisis.setVisible(true);
            txtAnalisis.setVisible(true);
            btnAnalisis.setVisible(true);
            chPersonal.setVisible(false);
            txtPersonal.setVisible(false);
            btnPersonal.setVisible(false);
            chActoMedico.setVisible(false);
            txtActoM.setVisible(false);
            txtPacientes.setText("");
            txtAnalisis.setText("");
            txtPersonal.setText("");
            txtActoM.setText("");
            btnBuscar.setVisible(true);
        }else if(cbx.getSelectedIndex()==3){
            chPacientes.setSelected(false);
            chAnalisis.setSelected(false);
            chPersonal.setSelected(true);
            chActoMedico.setSelected(false);
            chPacientes.setVisible(false);
            txtPacientes.setVisible(false);
            btnPacientes.setVisible(false);
            chAnalisis.setVisible(false);
            txtAnalisis.setVisible(false);
            btnAnalisis.setVisible(false);
            chPersonal.setVisible(true);
            txtPersonal.setVisible(true);
            btnPersonal.setVisible(true);
            chActoMedico.setVisible(false);
            txtActoM.setVisible(false);
            txtPacientes.setText("");
            txtAnalisis.setText("");
            txtPersonal.setText("");
            txtActoM.setText("");
            btnBuscar.setVisible(true);
        }else if(cbx.getSelectedIndex()==4){
            chPacientes.setSelected(false);
            chAnalisis.setSelected(false);
            chPersonal.setSelected(false);
            chActoMedico.setSelected(true);
            
            chPacientes.setVisible(false);
            txtPacientes.setVisible(false);
            btnPacientes.setVisible(false);
            chAnalisis.setVisible(false);
            txtAnalisis.setVisible(false);
            btnAnalisis.setVisible(false);
            chPersonal.setVisible(false);
            txtPersonal.setVisible(false);
            btnPersonal.setVisible(false);
            chActoMedico.setVisible(true);
            txtActoM.setVisible(true);
            txtPacientes.setText("");
            txtAnalisis.setText("");
            txtPersonal.setText("");
            txtActoM.setText("");
            btnBuscar.setVisible(true);
        }
    }//GEN-LAST:event_cbxItemStateChanged

    private void chPersonalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chPersonalItemStateChanged
       if(chPersonal.isSelected()){
            txtPersonal.setEnabled(false);
            btnPersonal.setEnabled(false);
            txtPersonal.setText("");
        }else{
            txtPersonal.setEnabled(true);
            btnPersonal.setEnabled(true);
        }
    }//GEN-LAST:event_chPersonalItemStateChanged

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed
       
                    personal.setVisible(true);
                    txtBuscarPersonal.setText("");
                    Personal_cargar();
                    Personal_formato();
               
    }//GEN-LAST:event_btnPersonalActionPerformed

    private void cbxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxKeyPressed

    private void txtPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacientesKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    btnPacientes.doClick();
                }
    }//GEN-LAST:event_txtPacientesKeyPressed

    private void txtAnalisisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnalisisKeyPressed
         char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    btnAnalisis.doClick();
                }
    }//GEN-LAST:event_txtAnalisisKeyPressed

    private void txtPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    btnPersonal.doClick();
                }
    }//GEN-LAST:event_txtPersonalKeyPressed

    private void txtBuscarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPersonalActionPerformed

    private void txtBuscarPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPersonalKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarPersonal.doClick();
        }
    }//GEN-LAST:event_txtBuscarPersonalKeyPressed

    private void txtBuscarPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPersonalKeyTyped

    }//GEN-LAST:event_txtBuscarPersonalKeyTyped

    private void btnBuscarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonalActionPerformed
        // TODO add your handling code here:

        try {
            String tipo="",buscar="";
            

            if(cbxBuscarPersonal.getSelectedIndex()==1){
                tipo="5";
            }else if(cbxBuscarPersonal.getSelectedIndex()==2){
                tipo="6";
            }
            buscar=txtBuscarPersonal.getText();
            String titulos[]={"N°","Código","Apellido Paterno","Apellido Materno","Nombres","Cargo","Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            LAB_Resultado_Muestra_Cabecera obj=new LAB_Resultado_Muestra_Cabecera ();

            String consulta="exec sp_LAB_PERSONAL ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
            cmd.setString(2, lblServicio.getText());
            cmd.setString(3, tipo);
            ResultSet r=cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                fila[6]=r.getString(6);
                m.addRow(fila);
                c++;
            }
            tbPersonal.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPersonal.setRowSorter(elQueOrdena);
            this.tbPersonal.setModel(m);
            Personal_formato();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarPersonalActionPerformed

    private void tbPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPersonalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPersonalMouseClicked

    private void tbPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{

                personal.setVisible(false);
                int filaselec=tbPersonal.getSelectedRow();
                String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
                tbPersonal.getValueAt(filaselec, 3).toString()
                +" "+tbPersonal.getValueAt(filaselec, 4).toString();
                txtPersonal.setText(nombreCompleto);
                

            }catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_tbPersonalKeyPressed

    private void tbPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPersonalKeyTyped

    private void cbxBuscarPersonalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarPersonalItemStateChanged
        // TODO add your handling code here:
        try
        {
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(cbxBuscarPersonal.getSelectedIndex()>0){
                    txtBuscarPersonal.setEnabled(true);
                    btnBuscarPersonal.setEnabled(true);
                }
            }
            else{
                txtBuscarPersonal.setEnabled(false);
                btnBuscarPersonal.setEnabled(false);
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxBuscarPersonalItemStateChanged

    private void tb_AnalisisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_AnalisisKeyPressed
      char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{

                analisis.setVisible(false);
                int filaselec=tb_Analisis.getSelectedRow();
                txtAnalisis.setText(tb_Analisis.getValueAt(filaselec, 3).toString());
                

            }catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_tb_AnalisisKeyPressed

    private void cbxBuscarAnalisisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarAnalisisItemStateChanged
        try
        {
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(cbxBuscarAnalisis.getSelectedIndex()>0){
                    txtBuscarAnalisis.setEnabled(true);
                    btnBuscarAnalisis.setEnabled(true);
                }
            }
            else{
                txtBuscarAnalisis.setEnabled(false);
                btnBuscarAnalisis.setEnabled(false);
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxBuscarAnalisisItemStateChanged

    private void txtBuscarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarAnalisisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarAnalisisActionPerformed

    private void txtBuscarAnalisisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAnalisisKeyPressed
          char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarAnalisis.doClick();
        }
    }//GEN-LAST:event_txtBuscarAnalisisKeyPressed

    private void txtBuscarAnalisisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAnalisisKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarAnalisisKeyTyped

    private void btnBuscarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAnalisisActionPerformed
        try {
            String tipo="",buscar="",servArea="";
            

            if(cbxBuscarAnalisis.getSelectedIndex()==1 && lblArea.getText().isEmpty()){
                tipo="2";
                servArea=lblServicio.getText();
            }else if(cbxBuscarAnalisis.getSelectedIndex()==2 && lblArea.getText().isEmpty()){
                tipo="3";
                servArea=lblServicio.getText();
            }else if(cbxBuscarAnalisis.getSelectedIndex()==1 && lblArea.getText().length()>0){
                tipo="5";
                servArea=lblArea.getText();
            }else if(cbxBuscarAnalisis.getSelectedIndex()==2 && lblArea.getText().length()>0){
                tipo="6";
                servArea=lblArea.getText();
            }
            buscar=txtBuscarAnalisis.getText();
            String titulos[]={"N°","Código","Código CPT","Análisis","Servicio","Área"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            LAB_Resultado_Muestra_Cabecera obj=new LAB_Resultado_Muestra_Cabecera ();

            String consulta="exec sp_LAB_RESULTADO_TM_ANALISIS ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
            cmd.setString(2, servArea);
            cmd.setString(3, tipo);
            ResultSet r=cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                m.addRow(fila);
                c++;
            }
            tb_Analisis.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Analisis.setRowSorter(elQueOrdena);
            this.tb_Analisis.setModel(m);
            Analisis_formato();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarAnalisisActionPerformed

    private void txtPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPacientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacientesActionPerformed

    private void txtFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormaPagoActionPerformed

    private void txtFormaPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFormaPagoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormaPagoKeyPressed

    private void txtFormaPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFormaPagoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormaPagoKeyReleased

    private void txtNTomaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNTomaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNTomaKeyPressed

    private void txtNTomaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNTomaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNTomaKeyReleased

    private void txtPersonalTomaMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalTomaMuestraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalTomaMuestraActionPerformed

    private void txtPersonalTomaMuestraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalTomaMuestraKeyPressed

    }//GEN-LAST:event_txtPersonalTomaMuestraKeyPressed

    private void txtPersonalTomaMuestraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalTomaMuestraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalTomaMuestraKeyReleased

    private void txtFechaTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaTMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaTMActionPerformed

    private void txtFechaTMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaTMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaTMKeyPressed

    private void txtFechaTMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaTMKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaTMKeyReleased

    private void txtHoraTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraTMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraTMActionPerformed

    private void txtHoraTMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraTMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraTMKeyPressed

    private void txtHoraTMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraTMKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraTMKeyReleased

    private void txtPersonalSolicitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicitaActionPerformed

    private void txtPersonalSolicitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicitaKeyPressed

    private void txtPersonalSolicitaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicitaKeyReleased

    private void txtPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPisoActionPerformed

    private void txtPisoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPisoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPisoKeyPressed

    private void txtPisoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPisoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPisoKeyReleased

    private void txtCamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamaActionPerformed

    private void txtCamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamaKeyPressed

    private void txtCamaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCamaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamaKeyReleased

    private void txtFechaOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaOrdenActionPerformed

    private void txtFechaOrdenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaOrdenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaOrdenKeyPressed

    private void txtFechaOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaOrdenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaOrdenKeyReleased

    private void txtHoraOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraOrdenActionPerformed

    private void txtHoraOrdenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraOrdenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraOrdenKeyPressed

    private void txtHoraOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraOrdenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraOrdenKeyReleased

    private void muestrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestrasActionPerformed
       
       
                    try{
                        if( tb_TomasRealizadas.getRowCount()>0){
                        int filaselec=tb_TomasRealizadas.getSelectedRow();
                        
                       VerTomaM.setVisible(true);
                    //Datos del Paciente
                    
                    txtHc.setText(tb_TomasRealizadas.getValueAt(filaselec, 9).toString());
                    txtPacientes2.setText(tb_TomasRealizadas.getValueAt(filaselec, 10).toString());
                    txtDni.setText(tb_TomasRealizadas.getValueAt(filaselec, 11).toString());
                    txtFecha.setText(tb_TomasRealizadas.getValueAt(filaselec, 12).toString());
                    txtEdad.setText(tb_TomasRealizadas.getValueAt(filaselec, 13).toString());
                    txtSexo.setText(tb_TomasRealizadas.getValueAt(filaselec, 14).toString());
                    //Toma de Muestra
                    txtActoMedico.setText(tb_TomasRealizadas.getValueAt(filaselec, 8).toString());
                    txtFormaPago.setText(tb_TomasRealizadas.getValueAt(filaselec, 15).toString());
                    txtNToma.setText(tb_TomasRealizadas.getValueAt(filaselec, 16).toString());
                    txtPersonalTomaMuestra.setText(tb_TomasRealizadas.getValueAt(filaselec, 17).toString());
                    txtFechaTM.setText(tb_TomasRealizadas.getValueAt(filaselec, 18).toString());
                    txtHoraTM.setText(tb_TomasRealizadas.getValueAt(filaselec, 19).toString());
                    if(tb_TomasRealizadas.getValueAt(filaselec,20).toString().isEmpty()){
                    txtPersonalSolicita.setText("--");
                    }else{
                    txtPersonalSolicita.setText(tb_TomasRealizadas.getValueAt(filaselec, 20).toString());
                        
                    }
                    if(tb_TomasRealizadas.getValueAt(filaselec,21).toString().isEmpty()){
                    txtPiso.setText("--");
                    txtCama.setText("--");
                    txthospiServ.setText("--");
                    }else{
                    txtPiso.setText(tb_TomasRealizadas.getValueAt(filaselec, 21).toString());
                    txtCama.setText(tb_TomasRealizadas.getValueAt(filaselec, 22).toString()); 
                    txthospiServ.setText(tb_TomasRealizadas.getValueAt(filaselec, 25).toString());
                    }
                    
                    txtFechaOrden.setText(tb_TomasRealizadas.getValueAt(filaselec, 23).toString());
                    txtHoraOrden.setText(tb_TomasRealizadas.getValueAt(filaselec, 24).toString());
                    
                    //Servicio
                    txtServArea.setText(tb_TomasRealizadas.getValueAt(filaselec, 4).toString());
                    txtCPT.setText(tb_TomasRealizadas.getValueAt(filaselec, 5).toString());
                    txtNomenclatura.setText(tb_TomasRealizadas.getValueAt(filaselec, 7).toString());
                            
                    LAB_mostrar_muestras(tb_TomasRealizadas.getValueAt(filaselec, 1).toString());
                    
                    
                   
                       }        
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ingreso "+e.getMessage());
        }
    }//GEN-LAST:event_muestrasActionPerformed

    public void LAB_mostrar_muestras(String cod_det_toma_mu_ana){
        try {
            String consulta="";
            tb_muestras.setModel(new DefaultTableModel());
            String titulos[]={"Nº","cod_det_toma_mu_ana","cod_muestra","cod_conten","ar_id",
                "Muestra","Contenedor","Área","Cantidad","Código de Barras"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
//            m=(DefaultTableModel)tb_TomasRealizadas.getModel(); Cuando se va agregando
            String fila[]=new String[10];

            LAB_Analisis_Examen obj=new LAB_Analisis_Examen();
                    consulta="exec sp_LAB_BUSQUEDA_MUESTRAS ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,cod_det_toma_mu_ana);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                fila[6]=r.getString(6);
                fila[7]=r.getString(7);
                fila[8]=r.getString(8);
                fila[9]=r.getString(9);

                m.addRow(fila);
                c++;
            }
            tb_muestras.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_muestras.setRowSorter(elQueOrdena);
            this.tb_muestras.setModel(m);
            
            LAB_Formato_muestras();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void LAB_Formato_muestras(){
    tb_muestras.getColumnModel().getColumn(0).setPreferredWidth(70);
    tb_muestras.getColumnModel().getColumn(5).setPreferredWidth(190);
    tb_muestras.getColumnModel().getColumn(6).setPreferredWidth(170);
    tb_muestras.getColumnModel().getColumn(7).setPreferredWidth(170);
    tb_muestras.getColumnModel().getColumn(8).setPreferredWidth(70);
    tb_muestras.getColumnModel().getColumn(9).setPreferredWidth(180);
    //Ocultar    
    tb_muestras.getColumnModel().getColumn(1).setMinWidth(0);
    tb_muestras.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_muestras.getColumnModel().getColumn(2).setMinWidth(0);
    tb_muestras.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_muestras.getColumnModel().getColumn(3).setMinWidth(0);
    tb_muestras.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_muestras.getColumnModel().getColumn(4).setMinWidth(0);
    tb_muestras.getColumnModel().getColumn(4).setMaxWidth(0);
    tb_muestras.getSelectionModel().setSelectionInterval(0, 0);
            tb_muestras.requestFocus();
    }
    private void txtNomenclaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomenclaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomenclaturaActionPerformed

    private void txtNomenclaturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomenclaturaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomenclaturaKeyPressed

    private void txtNomenclaturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomenclaturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomenclaturaKeyReleased

    private void txtCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPTActionPerformed

    private void txtCPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPTKeyPressed

    private void txtCPTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPTKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPTKeyReleased

    private void txtServAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServAreaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServAreaKeyPressed

    private void txtServAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServAreaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServAreaKeyReleased

    private void chActoMedicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chActoMedicoItemStateChanged
        if(chActoMedico.isSelected()){
            txtActoM.setEnabled(false);
            txtActoM.setText("");
        }else{
            txtActoM.setEnabled(true);
        }
    }//GEN-LAST:event_chActoMedicoItemStateChanged

    private void txtActoMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtActoMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActoMKeyPressed

    private void txthospiServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthospiServActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthospiServActionPerformed

    private void txthospiServKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthospiServKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthospiServKeyPressed

    private void txthospiServKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthospiServKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txthospiServKeyReleased
    public void enableDatos(){
    tb_TomasRealizadas.setEnabled(true);
    tb_TomasRealizadas.setBackground(Color.white);
}
    public void limpiar(){
  
   DefaultTableModel modelo = (DefaultTableModel)tb_TomasRealizadas.getModel(); 
   int filas=tb_TomasRealizadas.getRowCount();
   for(int i=0;i<filas;i++){
                    modelo.removeRow(0);
   }
}
   
  
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
//            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
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
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_TOMA_MUESTRA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_TOMA_MUESTRA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_TOMA_MUESTRA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_TOMA_MUESTRA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frm_LAB_BUSCAR_TOMA_MUESTRA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog VerTomaM;
    private javax.swing.JDialog analisis;
    public static javax.swing.JButton btnAnalisis;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscarAnalisis;
    private javax.swing.JButton btnBuscarPersonal;
    public static javax.swing.JButton btnPacientes;
    public static javax.swing.JButton btnPersonal;
    private javax.swing.JDialog buscar_HC;
    private javax.swing.JComboBox cbx;
    private javax.swing.JComboBox cbxBuscarAnalisis;
    private javax.swing.JComboBox cbxBuscarPersonal;
    private javax.swing.JCheckBox chActoMedico;
    private javax.swing.JCheckBox chAnalisis;
    private javax.swing.JCheckBox chPacientes;
    private javax.swing.JCheckBox chPersonal;
    private com.toedter.calendar.JDateChooser dateDesde;
    private com.toedter.calendar.JDateChooser dateHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JPanel jpanel3;
    private javax.swing.JPanel jpanel4;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lbldia;
    private javax.swing.JMenuItem muestras;
    private javax.swing.JPanel panelAnalisis;
    private javax.swing.JPanel panelPaciente;
    private javax.swing.JPanel panelPacientes;
    private javax.swing.JPanel panelTM;
    private javax.swing.JDialog personal;
    public static javax.swing.JTable tbPersonal;
    private javax.swing.JTable tb_Analisis;
    public static javax.swing.JTable tb_HC;
    public static javax.swing.JTable tb_TomasRealizadas;
    public static javax.swing.JTable tb_muestras;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo8;
    private javax.swing.JLabel titulo9;
    public static javax.swing.JTextField txtActoM;
    public static javax.swing.JTextField txtActoMedico;
    public static javax.swing.JTextField txtAnalisis;
    private javax.swing.JTextField txtBuscarAnalisis;
    private javax.swing.JTextField txtBuscarPersonal;
    public static javax.swing.JTextField txtCPT;
    public static javax.swing.JTextField txtCama;
    public static javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtEdad;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtFechaOrden;
    public static javax.swing.JTextField txtFechaTM;
    public static javax.swing.JTextField txtFormaPago;
    public static javax.swing.JTextField txtHc;
    public static javax.swing.JTextField txtHoraOrden;
    public static javax.swing.JTextField txtHoraTM;
    public static javax.swing.JTextField txtNToma;
    public static javax.swing.JTextField txtNomenclatura;
    public static javax.swing.JTextField txtPacientes;
    public static javax.swing.JTextField txtPacientes2;
    public static javax.swing.JTextField txtPersonal;
    public static javax.swing.JTextField txtPersonalSolicita;
    public static javax.swing.JTextField txtPersonalTomaMuestra;
    public static javax.swing.JTextField txtPiso;
    public static javax.swing.JTextField txtServArea;
    public static javax.swing.JTextField txtSexo;
    private javax.swing.JTextField txtbuscarHC;
    public static javax.swing.JTextField txthospiServ;
    // End of variables declaration//GEN-END:variables
}
