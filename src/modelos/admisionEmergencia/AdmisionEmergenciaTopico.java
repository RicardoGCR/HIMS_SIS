package modelos.admisionEmergencia;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
//import static modelos.admisionEmergencia.AdmisionEmergenciaCabecera.m;
import static modelos.admisionEmergencia.AdmisionEmergenciaTriaje.m;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
import vista.ConsultorioEx.HistoriaClinica;
import vista.admisionEmergencia.FrmFormatoEmergencia;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lbl2;
import static vista.admisionEmergencia.FrmListFormatoEmergencia.tbTriaje;

/**
 *
 * @author Yamila Rocca Ruiz
 */
public class AdmisionEmergenciaTopico {
    static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private String topico_id;
    private int preventa_id;
    private String triaje_id;
    private String fb_apetito;
    private String fb_sed;
    private String fb_deposi;
    private String fb_sueno;
    private String fb_orina;
    private String motivo_emer;
    private String relato;
    private String eg_concie;
    private String eg_hidra;
    private String eg_nutri;
    private String eg_fisic;
    private String plan_trabajo;
    private String anot_medico;
    private String anot_enfer;
    private String eval_paciente;
    private String ubic_egreso;
    private String cod_usu;
    private String nom_pc;
    private String fecha_actu;
    private String hora_actu;
    private String prioridad;
    
    public boolean mantenimientoAdmisionEmergenciaTopico(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC ADMISION_EMERGENCIA_TOPICO_MANTENIMIENTO ?,?,?,\n" +
                         "?,?,?,?,?,?,?,?,?,\n" +
                         "?,?,?,?,?,?,?,?,\n" +
                         "?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getTopico_id());
            cmd.setInt(2, getPreventa_id());
            cmd.setString(3, getFb_apetito());
            cmd.setString(4, getFb_sed());
            cmd.setString(5, getFb_deposi());
            cmd.setString(6, getFb_sueno());
            cmd.setString(7, getFb_orina());
            cmd.setString(8, getMotivo_emer());
            cmd.setString(9, getRelato());
            cmd.setString(10, getEg_concie());
            cmd.setString(11, getEg_hidra());
            cmd.setString(12, getEg_nutri());
            cmd.setString(13, getEg_fisic());
            cmd.setString(14, getPlan_trabajo());
            cmd.setString(15, getAnot_medico());
            cmd.setString(16, getAnot_enfer());
            cmd.setString(17, getEval_paciente());
            cmd.setString(18, getUbic_egreso());
            cmd.setString(19, getCod_usu());
            cmd.setString(20, tipo);
            cmd.setString(21, getTriaje_id());
            cmd.setString(22, getPrioridad());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoAdmisionEmergenciaTopico: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoTablaCargarLaboratorio(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);//CODIGO
        TableColumn columna = tabla.getColumnModel().getColumn(3);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void cargarFormatLaboratorio(String nombre,JTable tabla,String formaPago){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Nomenclatura","Examen","Precio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_NOMENCLATURA_CAJA_LISTAR_EXAMENES ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nombre);
            cmd.setString(2, formaPago);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // codigo
                fila[1]=r.getString(2); //Clasificación
                fila[2]=r.getString(3); //nombre del examen
                fila[3]=r.getString(4); //nombre del examen
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarLaboratorio(tabla);
        } catch (Exception e) {
            System.out.println("Error_cargarFormatLaboratorio: " + e.getMessage());
        }
    }
    
    public void formatoTablaCargarCie10(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);//CODIGO
        tabla.setRowHeight(30);
    }
    
    public void cargarDatosCie10(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nro","Código","Diagnóstico"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CIE10_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // clasificacion
                fila[1]=r.getString(2); //codigo
                fila[2]=r.getString(3); //codigo
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarCie10(tabla);
        } catch (Exception e) {
            System.out.println("Error_cargarDatosCie10: " + e.getMessage());
        }
    }
    
    public void formatoTablaCargarTriajepT(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(80);//fecha d ing
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//hora d ing
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//traido por
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);//parentesco
        tabla.getColumnModel().getColumn(4).setPreferredWidth(160);//id cabecera
        tabla.getColumnModel().getColumn(5).setPreferredWidth(160);//id triaje
        tabla.setRowHeight(25);
    }
    
    public void cargarFormatTriajeparaTopico(String id_hc,String fecha,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Fecha de ing","Hora de ing","Traído por",
                "Parentesco","ID Preventa","ID Triaje"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC ADMISION_EMERGENCIA_TOPICO_MOSTRAR_LISTASEMER_Y_REPORTE ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id_hc);
            cmd.setString(2, fecha);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(9); // FECHA ING
                fila[1]=r.getString(10);//HORA ing
                fila[2]=r.getString(11);//TRAIPOD POR
                fila[3]=r.getString(12); //PARENTESCO
                fila[4]=r.getString(2); //ID CABECERA
                fila[5]=r.getString(3); //ID TRIAJE
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarTriajepT(tabla);
        } catch (Exception e) {
            System.out.println("Error_buscar_HC: " + e.getMessage());
        }
    }
    
    public void formatoTablaCargarDatosTopico(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(160);//codigo
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);//fecha d ing
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//fecha ingreso
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);//hora ingreso
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);//fecha ingreso
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);//hora ingreso
        tabla.getColumnModel().getColumn(6).setPreferredWidth(80);//apetito
        tabla.getColumnModel().getColumn(7).setPreferredWidth(80);//sed
        tabla.getColumnModel().getColumn(8).setPreferredWidth(80);//deposicion
        tabla.getColumnModel().getColumn(9).setPreferredWidth(80);//sueño
        tabla.getColumnModel().getColumn(10).setPreferredWidth(80);//orina
        tabla.getColumnModel().getColumn(11).setPreferredWidth(500);//motivo 
        tabla.getColumnModel().getColumn(12).setPreferredWidth(500);//relato
        tabla.getColumnModel().getColumn(13).setPreferredWidth(80);//conciencia
        tabla.getColumnModel().getColumn(14).setPreferredWidth(80);//hidratacion
        tabla.getColumnModel().getColumn(15).setPreferredWidth(80);//nutricion
        tabla.getColumnModel().getColumn(16).setPreferredWidth(500);//examen fisco
        tabla.getColumnModel().getColumn(17).setPreferredWidth(500);//plan de trabajo
        tabla.getColumnModel().getColumn(18).setPreferredWidth(500);//anot medica
        tabla.getColumnModel().getColumn(19).setPreferredWidth(500);//anot enf
        tabla.getColumnModel().getColumn(20).setPreferredWidth(100);//evaluacion
        tabla.getColumnModel().getColumn(21).setPreferredWidth(100);//ubicacion de egreso
        TableColumn columna = tabla.getColumnModel().getColumn(24);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(25);
    }
    
    public void cargarDatosTopico(JTable tabla,String topico_id, String fecha){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Tópico","Traído por","Fecha de ing","Hora de ingreso",
                              "Fecha de registro","Hora de registro","Apetito","Sed",
                              "Deposiciones","Sueño","Orina","Motivo de Emergencia",
                              "Relato","Conciencia","Hidratación","Nutrición",
                              "Examen Físico","Plan de Trabajo","Anotaciones Médicas",
                              "Anotaciones de Enfermería","Evaluación del Paciente",
                              "Ubicación al egreso","Prioridad","ID","","FORMA DE PAGO"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[30];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC ADMISION_EMERGENCIA_TOPICO_LISTAR_Y_REPORTE ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, topico_id);
            cmd.setString(2, fecha);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);//TOPIDO_ID 
                fila[1]=r.getString(25);//TRAIDO POR
                fila[2]=r.getString(21);//FECHA ING
                fila[3]=r.getString(22);//HORA ING
                fila[4]=r.getString(24);// FECHA REGISTRO
                fila[5]=r.getString(23);//HORA REGISTRO
                fila[6]=r.getString(5);//APETITO
                fila[7]=r.getString(6);//SED
                fila[8]=r.getString(7);//DEPOSI
                fila[9]=r.getString(8);//SUENO
                fila[10]=r.getString(9);// ORINA
                fila[11]=r.getString(10);//MOTIVO EMER
                fila[12]=r.getString(11);//RELATO
                fila[13]=r.getString(12);//CONCIE
                fila[14]=r.getString(13);//HIDRA
                fila[15]=r.getString(14);// NUTRI
                fila[16]=r.getString(15);//FISIC
                fila[17]=r.getString(16);//Plan trabajo
                fila[18]=r.getString(17);//anot medica
                fila[19]=r.getString(18);//anot enf
                fila[20]=r.getString(19);//eval paciente
                fila[21]=r.getString(20);//ubic egreso
                fila[22]=r.getString(26);//ubic egreso
                fila[23]=r.getString(27);//ubic egreso
                fila[24]=r.getString(28);//ubic egreso
                fila[25]=r.getString(29);//ubic egreso
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarDatosTopico(tabla);
        } catch (Exception e) {
            System.out.println("Error: cargarDatosTopico: " + e.getMessage());
        }
    }
    
    
    public void formatoAdmisionEmergenciaTopicoDetalles(JTable tabla,int tipo){
        if(tipo==1){
            tabla.getColumnModel().getColumn(0).setPreferredWidth(1);//codigo
            tabla.getColumnModel().getColumn(1).setPreferredWidth(250);//fecha d ing
            tabla.getColumnModel().getColumn(2).setPreferredWidth(100);//fecha ingreso
//            TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.setRowHeight(0);
//            tabla.doLayout();
        } else {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(40);//codigo
            tabla.getColumnModel().getColumn(1).setPreferredWidth(60);//fecha d ing
            tabla.getColumnModel().getColumn(2).setPreferredWidth(600);//fecha ingreso
        }
        tabla.setRowHeight(25);
    }
    
    public void admisionEmergenciaTopicoDetalles(JTable tabla,String topico_id, int tipo){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            if(tipo==1){
                String titulos[]={"Nro","Exámenes","Precio"};
                m=new DefaultTableModel(null,titulos);
            } else {
                String titulos[]={"Nro","Código","Diagnósticos"};
                m=new DefaultTableModel(null,titulos);
            }
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC ADMISION_EMERGENCIA_TOPICO_DETALLES ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, topico_id);
            cmd.setInt(2, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);//TOPIDO_ID 
                fila[1]=r.getString(2);//TRAIDO POR
                fila[2]=r.getString(3);//FECHA ING
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoAdmisionEmergenciaTopicoDetalles(tabla,tipo);
        } catch (Exception e) {
            System.out.println("Error: admisionEmergenciaTopicoDetalles: " + e.getMessage());
        }
    }
    
    public String idAdmisionEmergenciaTopico(){
        String id = "";
        try {
            String consulta = "EXEC ADMISION_EMERGENCIA_TOPICO_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_idAdmisionEmergenciaTopico: " + ex.getMessage());
        }
        return id;
    }
    
    public void reporteTopico(String topico_id) {
        try {
            Map parametros = new HashMap();
            parametros.put("topico_id", topico_id);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/admisionEmergencia/formatoEmergencia-Topico.jasper"), parametros, con.conectar());          
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Formato de Emergencia - Tópico");
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "reporteTopico:"+e.getMessage());
        }
    }
    
    public void reporteTopicoCompleto(String topico_id) {
        try {
            Map parametros = new HashMap();
            parametros.put("topico_id", topico_id);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/admisionEmergencia/formatoEmergencia.jasper"), parametros, con.conectar());          
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Formato de Emergencia - Tópico");
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "reporteTopico:"+e.getMessage());
        }
    }
    
    public void formatoTablaTopicoReporteFinal(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//id topico
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);//nhc
        tabla.getColumnModel().getColumn(2).setPreferredWidth(70);//nhc
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);//apetito
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//sed
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);//deposicion
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);//sueño
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);//orinas
        tabla.getColumnModel().getColumn(10).setPreferredWidth(300);//motivo de emergencia
        tabla.getColumnModel().getColumn(11).setPreferredWidth(300);//relato de emergencia
        tabla.getColumnModel().getColumn(12).setPreferredWidth(100);//conciencia
        tabla.getColumnModel().getColumn(13).setPreferredWidth(100);//hidratacion
        tabla.getColumnModel().getColumn(14).setPreferredWidth(100);//nutricion
        tabla.getColumnModel().getColumn(15).setPreferredWidth(300);//examen fisico
        tabla.getColumnModel().getColumn(16).setPreferredWidth(300);//plan de trabajo
        tabla.getColumnModel().getColumn(17).setPreferredWidth(300);//anotaciones medicas
        tabla.getColumnModel().getColumn(18).setPreferredWidth(300);//anotaciones de enfermeria
        tabla.getColumnModel().getColumn(19).setPreferredWidth(100);//evaluacion del paciente
        tabla.getColumnModel().getColumn(20).setPreferredWidth(100);//ubicacion al egreso
        tabla.getColumnModel().getColumn(21).setPreferredWidth(100);//fecha de ingreso
        tabla.getColumnModel().getColumn(22).setPreferredWidth(100);//hora de ingreso
        tabla.getColumnModel().getColumn(23).setPreferredWidth(100);//fecha de registro
        tabla.getColumnModel().getColumn(24).setPreferredWidth(100);//hora de registro
        tabla.getColumnModel().getColumn(25).setPreferredWidth(200);//fecha de registro
        tabla.getColumnModel().getColumn(26).setPreferredWidth(50);//hora de registro
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void admisionEmergenciaTopicoReporteFinal(String nhc,JTable tabla, String fechai,String fechaf){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Acto Médico","Nº H.C.","DNI","Paciente",
                "Apetito","Sed","Deposición","Sueño","Orinas",
                "Motivo de Emergencia","Relato","Conciencia",
                "Hidratación","Nutrición","Examen físico",
                "Plan de trabajo","Anotaciones Médicas",
                "Anotaciones de enfermería","Evaluación del paciente",
                "Ubicación al egreso","Fecha de ingreso",
                "Hora de ingreso","Fecha de registro","Hora de registro","Traido por","Prioridad"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[27];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec ADMISION_EMERGENCIA_TOPICO_REPORTE_FINAL ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            cmd.setString(2, fechai);
            cmd.setString(3, fechaf);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                fila[8]=r.getString(9);
                fila[9]=r.getString(10); // 
                fila[10]=r.getString(11); // 
                fila[11]=r.getString(12); // 
                fila[12]=r.getString(13); // 
                fila[13]=r.getString(14); // 
                fila[14]=r.getString(15);
                fila[15]=r.getString(16);
                fila[16]=r.getString(17); // 
                fila[17]=r.getString(18);
                fila[18]=r.getString(19);
                fila[19]=r.getString(20); // 
                fila[20]=r.getString(21); // 
                fila[21]=r.getString(22);
                fila[22]=r.getString(23); // 
                fila[23]=r.getString(24); // 
                fila[24]=r.getString(25); // 
                fila[25]=r.getString(26); // 
                fila[26]=r.getString(27); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaTopicoReporteFinal(tabla);
        } catch (Exception e) {
            System.out.println("Error: admisionEmergenciaTopicoReporteFinal: " + e.getMessage());
        }
    }
    
    public void historiaClinicaTopico(String id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_HISTORIAL_TOPICO_EMERGENCIA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                HistoriaClinica.txtTopico.setText(r.getString(1)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: historiaClinicaTopico  " + e.getMessage());
        }
    } 
    
    public void formatoTablaHistorialTopicoDetalles(JTable tabla,String tipo){
        if(tipo.equals("P") || tipo.equals("D")){
            tabla.getColumnModel().getColumn(0).setPreferredWidth(40);//
            tabla.getColumnModel().getColumn(1).setPreferredWidth(300);//
            TableColumn columna2 = tabla.getColumnModel().getColumn(2);
            columna2.setMaxWidth(0);
            columna2.setMinWidth(0);
            columna2.setPreferredWidth(0);
            tabla.doLayout();
        } else {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(40);//
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);//
            tabla.getColumnModel().getColumn(2).setPreferredWidth(70);//
        }
        tabla.setRowHeight(30);
    }
    
    public void historiaClinicaTopicoDetalles(String preventa,JTable tabla, String tipo){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Descripción","Estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CONSULTORIO_EXT_LISTAR_HISTORIAL_CONSULTORIO_EMERGENCIA_TOPICO_DETALLES ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, preventa);
            cmd.setString(2, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaHistorialTopicoDetalles(tabla,tipo);
        } catch (Exception e) {
            System.out.println("Error: historiaClinicaTopicoDetalles: " + e.getMessage());
        }
    }
    
    public void formadePago(String nhc){
        String consulta="";
        try {
            consulta="EXEC ADMISION_EMERGENCIA_CARGAR_FORMA_PAGO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                FrmFormatoEmergencia.lblIdFP.setText(r.getString(1));
                FrmFormatoEmergencia.lblFP.setText(r.getString(2));
            }
            //
        } catch (Exception e) {
            System.out.println("Error: formadePago" + e.getMessage());
        }
    }
    
    public String topicoID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 TOPICO_ID FROM ADMISION_EMERGENCIA_TOPICO WHERE PC_NOMBRE = HOST_NAME()"
                    + "ORDER BY TRIAJE_ID DESC";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               FrmFormatoEmergencia.lbl2.setText(rs.getString(1));
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: topicoID: " + ex.getMessage());
        }
        return cod;
    }
    //Constructor
    
    public AdmisionEmergenciaTopico()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the topico_id
     */
    public String getTopico_id() {
        return topico_id;
    }

    /**
     * @param topico_id the topico_id to set
     */
    public void setTopico_id(String topico_id) {
        this.topico_id = topico_id;
    }

    /**
     * @return the emercab_id
     */
    public int getPreventa_id() {
        return preventa_id;
    }

    /**
     * @param emercab_id the emercab_id to set
     */
    public void setPreventa(int preventa_id) {
        this.preventa_id = preventa_id;
    }

    /**
     * @return the fb_apetito
     */
    public String getFb_apetito() {
        return fb_apetito;
    }

    /**
     * @param fb_apetito the fb_apetito to set
     */
    public void setFb_apetito(String fb_apetito) {
        this.fb_apetito = fb_apetito;
    }

    /**
     * @return the fb_sed
     */
    public String getFb_sed() {
        return fb_sed;
    }

    /**
     * @param fb_sed the fb_sed to set
     */
    public void setFb_sed(String fb_sed) {
        this.fb_sed = fb_sed;
    }

    /**
     * @return the fb_deposi
     */
    public String getFb_deposi() {
        return fb_deposi;
    }

    /**
     * @param fb_deposi the fb_deposi to set
     */
    public void setFb_deposi(String fb_deposi) {
        this.fb_deposi = fb_deposi;
    }

    /**
     * @return the fb_sueno
     */
    public String getFb_sueno() {
        return fb_sueno;
    }

    /**
     * @param fb_sueno the fb_sueno to set
     */
    public void setFb_sueno(String fb_sueno) {
        this.fb_sueno = fb_sueno;
    }

    /**
     * @return the fb_orina
     */
    public String getFb_orina() {
        return fb_orina;
    }

    /**
     * @param fb_orina the fb_orina to set
     */
    public void setFb_orina(String fb_orina) {
        this.fb_orina = fb_orina;
    }

    /**
     * @return the motivo_emer
     */
    public String getMotivo_emer() {
        return motivo_emer;
    }

    /**
     * @param motivo_emer the motivo_emer to set
     */
    public void setMotivo_emer(String motivo_emer) {
        this.motivo_emer = motivo_emer;
    }

    /**
     * @return the relato
     */
    public String getRelato() {
        return relato;
    }

    /**
     * @param relato the relato to set
     */
    public void setRelato(String relato) {
        this.relato = relato;
    }

    /**
     * @return the eg_concie
     */
    public String getEg_concie() {
        return eg_concie;
    }

    /**
     * @param eg_concie the eg_concie to set
     */
    public void setEg_concie(String eg_concie) {
        this.eg_concie = eg_concie;
    }

    /**
     * @return the eg_hidra
     */
    public String getEg_hidra() {
        return eg_hidra;
    }

    /**
     * @param eg_hidra the eg_hidra to set
     */
    public void setEg_hidra(String eg_hidra) {
        this.eg_hidra = eg_hidra;
    }

    /**
     * @return the eg_nutri
     */
    public String getEg_nutri() {
        return eg_nutri;
    }

    /**
     * @param eg_nutri the eg_nutri to set
     */
    public void setEg_nutri(String eg_nutri) {
        this.eg_nutri = eg_nutri;
    }

    /**
     * @return the eg_fisic
     */
    public String getEg_fisic() {
        return eg_fisic;
    }

    /**
     * @param eg_fisic the eg_fisic to set
     */
    public void setEg_fisic(String eg_fisic) {
        this.eg_fisic = eg_fisic;
    }

    /**
     * @return the plan_trabajo
     */
    public String getPlan_trabajo() {
        return plan_trabajo;
    }

    /**
     * @param plan_trabajo the plan_trabajo to set
     */
    public void setPlan_trabajo(String plan_trabajo) {
        this.plan_trabajo = plan_trabajo;
    }

    /**
     * @return the anot_medico
     */
    public String getAnot_medico() {
        return anot_medico;
    }

    /**
     * @param anot_medico the anot_medico to set
     */
    public void setAnot_medico(String anot_medico) {
        this.anot_medico = anot_medico;
    }

    /**
     * @return the anot_enfer
     */
    public String getAnot_enfer() {
        return anot_enfer;
    }

    /**
     * @param anot_enfer the anot_enfer to set
     */
    public void setAnot_enfer(String anot_enfer) {
        this.anot_enfer = anot_enfer;
    }

    /**
     * @return the eval_paciente
     */
    public String getEval_paciente() {
        return eval_paciente;
    }

    /**
     * @param eval_paciente the eval_paciente to set
     */
    public void setEval_paciente(String eval_paciente) {
        this.eval_paciente = eval_paciente;
    }

    /**
     * @return the ubic_egreso
     */
    public String getUbic_egreso() {
        return ubic_egreso;
    }

    /**
     * @param ubic_egreso the ubic_egreso to set
     */
    public void setUbic_egreso(String ubic_egreso) {
        this.ubic_egreso = ubic_egreso;
    }

    /**
     * @return the cod_usu
     */
    public String getCod_usu() {
        return cod_usu;
    }

    /**
     * @param cod_usu the cod_usu to set
     */
    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    /**
     * @return the nom_pc
     */
    public String getNom_pc() {
        return nom_pc;
    }

    /**
     * @param nom_pc the nom_pc to set
     */
    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    /**
     * @return the fecha_actu
     */
    public String getFecha_actu() {
        return fecha_actu;
    }

    /**
     * @param fecha_actu the fecha_actu to set
     */
    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    /**
     * @return the hora_actu
     */
    public String getHora_actu() {
        return hora_actu;
    }

    /**
     * @param hora_actu the hora_actu to set
     */
    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    /**
     * @return the triaje_id
     */
    public String getTriaje_id() {
        return triaje_id;
    }

    /**
     * @param triaje_id the triaje_id to set
     */
    public void setTriaje_id(String triaje_id) {
        this.triaje_id = triaje_id;
    }

    /**
     * @return the prioridad
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    
}
