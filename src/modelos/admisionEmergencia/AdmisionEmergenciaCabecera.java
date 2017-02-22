/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.admisionEmergencia;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
import vista.admisionEmergencia.FrmFormatoEmergencia;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblApNom;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblDepartamento;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblDireccion;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblDistrito;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblDni;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblEdad;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblEstcivil;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblFechaNac;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblGenero;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblProvincia;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblSector;

/**
 *
 * @author Yamila Rocca Ruiz
 */
public class AdmisionEmergenciaCabecera {
    static DefaultTableModel m;
    private Connection cn;
    private String emercab_id;
    private String emercab_fechaing;
    private String emercab_horaing;
    private String id_hc;
    private String emercab_traidopor;
    private String emercab_parentesco;
    private String emer_forma_llegada_id;
    private String cod_jerar_forma_pago;
    private String emer_observacion;
    private String emer_estado_egreso;
    private String emer_fecha_actu;
    private String emer_hora_actu;
    private String cod_usu;
    private String emer_nom_pc;
    private String emer_estado;
    Conexion con = new Conexion();

    public boolean insertarAdmisionemergenciaCabecera()
        {
        boolean resp = false;
        try{
            String sql = "EXEC ADMISION_EMERGENCIA_CABECERA_INSERTAR ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getEmercab_id());
            cmd.setString(2, getId_hc());
            cmd.setString(3, getEmercab_traidopor());
            cmd.setString(4, getEmercab_parentesco());
            cmd.setString(5, getEmer_forma_llegada_id());
            cmd.setString(6, getCod_jerar_forma_pago());
            cmd.setString(7, getEmer_observacion());
            cmd.setString(8, getEmer_estado_egreso());
            cmd.setString(9, getCod_usu());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_insertarAdmisionemergenciaCabecera: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean admisionEmergenciaCabeceraModificar()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC ADMISION_EMERGENCIA_CABECERA_MODIFICAR ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getEmercab_id());
            cmd.setString(2, getEmercab_traidopor());
            cmd.setString(3, getEmercab_parentesco());
            cmd.setString(4, getEmer_forma_llegada_id());
            cmd.setString(5, getCod_jerar_forma_pago());
            cmd.setString(6, getEmer_observacion());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error_admisionEmergenciaCabeceraModificar: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean eliminarAdmisionemergenciaCabecera()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC ADMISION_EMERGENCIA_CABECERA_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_hc());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminarAdmisionemergenciaCabecera: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoTablaCargarFormatEmer(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(160);//cod emergencia
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//apellidos
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//nombres
        tabla.getColumnModel().getColumn(3).setPreferredWidth(180);//dni
        tabla.setRowHeight(25);
    }
    
    public void cargarFormatEmer(String id_hc,String fecha,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Traído por","Parentesco","ID FL","Forma de Llegada",
                "Observación","Fecha de ing","Hora de ing"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_PREVENTA_LISTAR_MOSTRAR_MODIF ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id_hc);
            cmd.setString(2, fecha);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // nhc
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // dni
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // dni
                fila[7]=r.getString(8);
                //fila[4]=r.getString(1); // codigo de hc
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarFormatEmer(tabla);
        } catch (Exception e) {
            System.out.println("Error_buscar_HC: " + e.getMessage());
        }
    }
    
    public void formatoTablaCabeceraListar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);//nhc
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//acto medico
        tabla.getColumnModel().getColumn(2).setPreferredWidth(70);//dni
        tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//paciente
        tabla.getColumnModel().getColumn(4).setPreferredWidth(25);//genero
        tabla.getColumnModel().getColumn(5).setPreferredWidth(30);//edad
        tabla.getColumnModel().getColumn(6).setPreferredWidth(60);//sector
        tabla.getColumnModel().getColumn(7).setPreferredWidth(200);//traido por
        tabla.getColumnModel().getColumn(8).setPreferredWidth(90);//parentesco
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);//forma de llegada
        tabla.getColumnModel().getColumn(10).setPreferredWidth(120);//observacion
        tabla.getColumnModel().getColumn(11).setPreferredWidth(90);//fecha de ingreso
        tabla.getColumnModel().getColumn(12).setPreferredWidth(90);//hora de ingreso
        tabla.getColumnModel().getColumn(13).setPreferredWidth(1);//hora de ingreso
        TableColumn columna = tabla.getColumnModel().getColumn(13);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void admisionEmergenciaCabeceraListar(String nhc,String estado,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nº H.C.","Acto Médico","DNI","Paciente","S",
                "Edad","Sector","Traído por","Parenteso","F. de llegada",
                "Observación","Fecha ingreso","Hora ingreso","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC ADMISION_EMERGENCIA_CABECERA_LISTAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            cmd.setString(2, estado);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(14);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3); // 
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                fila[6]=r.getString(6); // 
                fila[7]=r.getString(7); // 
                fila[8]=r.getString(8);
                fila[9]=r.getString(9); // 
                fila[10]=r.getString(10); // 
                fila[11]=r.getString(11); // 
                fila[12]=r.getString(12); // 
                fila[13]=r.getString(13); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCabeceraListar(tabla);
        } catch (Exception e) {
            System.out.println("Error_buscar_HC: " + e.getMessage());
        }
    }
    
    public void mostrarHCTriaje(String nhc){
        String consulta="";
        try {
            consulta="EXEC SP_ADMISION_HISTORIACLINICA_BUSXNHC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                FrmFormatoEmergencia.lblPaciente.setText(r.getString(2) + " " + r.getString(3) + " " + 
                                 r.getString(4) + " " + r.getString(5) + " " +
                                 r.getString(6)); 
                FrmFormatoEmergencia.lblIDHCTr.setText(r.getString(18));
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
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                FrmFormatoEmergencia.lblPacienteTo.setText(r.getString(2) + " " + r.getString(3) + " " + 
                                 r.getString(4) + " " + r.getString(5) + " " +
                                 r.getString(6)); 
                FrmFormatoEmergencia.lblIDHCTo.setText(r.getString(18));
            }
            //
        } catch (Exception e) {
            System.out.println("Error_mostrar_MovHC: " + e.getMessage());
        }
    }
    
    public String idAdmisionEmergenciaCabecera(){
        String id = "";
        try {
            String consulta = "EXEC ADMISION_EMERGENCIA_CABECERA_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_idAdmisionEmergenciaCabecera: " + ex.getMessage());
        }
        return id;
    }
    
    public String codFormaLlegada(String formaLlegada)
    {
        String cod="";
        try
        {
            String sql = "SELECT EMER_FORMA_LLEGADA_ID FROM ADMISION_EMERGENCIA_FORMA_LLEGADA WHERE EMER_FORMA_LLEGADA_NOMBRE = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, formaLlegada);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codFormaLlegada: " + ex.getMessage());
        }
        return cod;
    }
    
    public String codUsuario(String nombreUsuario)
    {
        String cod="";
        try
        {
            String sql = "SELECT USU_CODIGO FROM USUARIO WHERE Usu_Usuario = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombreUsuario);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codUsuario: " + ex.getMessage());
        }
        return cod;
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
    
    public void reporteCabecera(String ruta, int id) {
        try {
            Map parametros = new HashMap();
            parametros.put("id", id);
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(rutaInforme), parametros, cn);          
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Formato de Emergencia - Cabecera");
           ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error_reporteCabecera:"+e.getMessage());
        }
    }
        
    public void reporteTriaje(String ruta, String id_triaje) {
        try {
            Map parametros = new HashMap();
            parametros.put("id_triaje", id_triaje);
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(rutaInforme), parametros, cn);          
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Formato de Emergencia - Triaje");
           ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error_reporteTriaje:"+e.getMessage());
        }
    }
    
    public AdmisionEmergenciaCabecera()
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
     * @return the emercab_id
     */
    public String getEmercab_id() {
        return emercab_id;
    }

    /**
     * @param emercab_id the emercab_id to set
     */
    public void setEmercab_id(String emercab_id) {
        this.emercab_id = emercab_id;
    }

    /**
     * @return the emercab_fechaing
     */
    public String getEmercab_fechaing() {
        return emercab_fechaing;
    }

    /**
     * @param emercab_fechaing the emercab_fechaing to set
     */
    public void setEmercab_fechaing(String emercab_fechaing) {
        this.emercab_fechaing = emercab_fechaing;
    }

    /**
     * @return the emercab_horaing
     */
    public String getEmercab_horaing() {
        return emercab_horaing;
    }

    /**
     * @param emercab_horaing the emercab_horaing to set
     */
    public void setEmercab_horaing(String emercab_horaing) {
        this.emercab_horaing = emercab_horaing;
    }

    /**
     * @return the id_hc
     */
    public String getId_hc() {
        return id_hc;
    }

    /**
     * @param id_hc the id_hc to set
     */
    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    /**
     * @return the emercab_traidopor
     */
    public String getEmercab_traidopor() {
        return emercab_traidopor;
    }

    /**
     * @param emercab_traidopor the emercab_traidopor to set
     */
    public void setEmercab_traidopor(String emercab_traidopor) {
        this.emercab_traidopor = emercab_traidopor;
    }

    /**
     * @return the emercab_parentesco
     */
    public String getEmercab_parentesco() {
        return emercab_parentesco;
    }

    /**
     * @param emercab_parentesco the emercab_parentesco to set
     */
    public void setEmercab_parentesco(String emercab_parentesco) {
        this.emercab_parentesco = emercab_parentesco;
    }

    /**
     * @return the emer_forma_llegada_id
     */
    public String getEmer_forma_llegada_id() {
        return emer_forma_llegada_id;
    }

    /**
     * @param emer_forma_llegada_id the emer_forma_llegada_id to set
     */
    public void setEmer_forma_llegada_id(String emer_forma_llegada_id) {
        this.emer_forma_llegada_id = emer_forma_llegada_id;
    }

    /**
     * @return the cod_jerar_forma_pago
     */
    public String getCod_jerar_forma_pago() {
        return cod_jerar_forma_pago;
    }

    /**
     * @param cod_jerar_forma_pago the cod_jerar_forma_pago to set
     */
    public void setCod_jerar_forma_pago(String cod_jerar_forma_pago) {
        this.cod_jerar_forma_pago = cod_jerar_forma_pago;
    }

    /**
     * @return the emer_observacion
     */
    public String getEmer_observacion() {
        return emer_observacion;
    }

    /**
     * @param emer_observacion the emer_observacion to set
     */
    public void setEmer_observacion(String emer_observacion) {
        this.emer_observacion = emer_observacion;
    }

    /**
     * @return the emer_estado_egreso
     */
    public String getEmer_estado_egreso() {
        return emer_estado_egreso;
    }

    /**
     * @param emer_estado_egreso the emer_estado_egreso to set
     */
    public void setEmer_estado_egreso(String emer_estado_egreso) {
        this.emer_estado_egreso = emer_estado_egreso;
    }

    /**
     * @return the emer_fecha_actu
     */
    public String getEmer_fecha_actu() {
        return emer_fecha_actu;
    }

    /**
     * @param emer_fecha_actu the emer_fecha_actu to set
     */
    public void setEmer_fecha_actu(String emer_fecha_actu) {
        this.emer_fecha_actu = emer_fecha_actu;
    }

    /**
     * @return the emer_hora_actu
     */
    public String getEmer_hora_actu() {
        return emer_hora_actu;
    }

    /**
     * @param emer_hora_actu the emer_hora_actu to set
     */
    public void setEmer_hora_actu(String emer_hora_actu) {
        this.emer_hora_actu = emer_hora_actu;
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
     * @return the emer_nom_pc
     */
    public String getEmer_nom_pc() {
        return emer_nom_pc;
    }

    /**
     * @param emer_nom_pc the emer_nom_pc to set
     */
    public void setEmer_nom_pc(String emer_nom_pc) {
        this.emer_nom_pc = emer_nom_pc;
    }

    /**
     * @return the emer_estado
     */
    public String getEmer_estado() {
        return emer_estado;
    }

    /**
     * @param emer_estado the emer_estado to set
     */
    public void setEmer_estado(String emer_estado) {
        this.emer_estado = emer_estado;
    }

    
}
