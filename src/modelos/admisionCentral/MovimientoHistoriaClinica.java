/*
    YAMILA ROCCA RUIZ
 */
package modelos.admisionCentral;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;

public class MovimientoHistoriaClinica {
    private Connection cn;
    Conexion c = new Conexion();
    private int id_mov;
    private String id_hc;
    private String cod_uni_organica_jerar;
    private String cod_per;
    private String id_cod_det;
    private String fecha_actu;
    private String hora_actu;
    private String fecha_aten;
    private String hora_aten;
    private String tipo_edad_consulta;
    private String estado_movimiento;
    private int num_aten;
    private String fecha_pen;
    private String hora_pen;
    private String nom_pc_pendiente;
    private String fecha_salida;
    private String hora_salida;
    private String nom_pc_salida;
    private String fecha_ret;
    private String hora_ret;
    private String nom_pc_retorno;
    private String estado_movi;
    private String cod_usu;
    private String usu_usuario_pen;
    private String usu_usuario_salida;
    private String usu_usuario_ret;
    private String usu_codigo;
    private String est_abrev;
    private static JasperReport report;
    private static JasperPrint informe;
    private static JasperViewer ventana;
    DefaultTableModel m;
    
    public void reportesPDFlista(String ruta, String archi, int tipo, String estadoM, String fechaI, String fechaF,
            String area, String estado) {
        try {
            Map parametros = new HashMap();
            parametros.put("tipo", tipo);
            parametros.put("estadoM", estadoM);
            parametros.put("fechaI", fechaI);
            parametros.put("fechaF", fechaF);
            parametros.put("area", area);
            parametros.put("estado", estado);
            if(tipo == 1)
                parametros.put("estadot", "Todos");
            else
                parametros.put("estadot", "");
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(rutaInforme), parametros, cn);
            JasperExportManager.exportReportToPdfFile(informe, archi);
//            JasperViewer ventanavisor = new JasperViewer(informe, false);
//            ventanavisor.setTitle("INFORME");
//            ventanavisor.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO"+e.getMessage());
        }
    }
    
    public void reportesPDFDetalle(String ruta, String archi, int acto_medico) {
        try {
            Map parametros = new HashMap();
            parametros.put("acto_medico", acto_medico);
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(rutaInforme), parametros, cn);
            JasperExportManager.exportReportToPdfFile(informe, archi);
//            JasperViewer ventanavisor = new JasperViewer(informe, false);
//            ventanavisor.setTitle("INFORME");
//            ventanavisor.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO"+e.getMessage());
        }
    }
    
    //Combobox JTable
    String [] datos = {"Pendiente","Salida","Retorno"};
    JComboBox jcbx = new JComboBox(datos);

    
    public void cabecera(JTable tabla){
        String titulos[]={"Acto Médico","Fecha","Hora","N° H.C","Datos del Paciente","Servicio/Area/Departamento",
                              "Consultorio","Turno","Médico","Num A","Tipo Edad","Estado","   E", "Mayor/Menor"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                tabla.setModel(m);
                TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoMovHistoriaClinica(tabla);
    }
    
    public void formatoMovHistoriaClinica(JTable tabla){
            tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(210);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(280);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(210);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(14).setPreferredWidth(100);
            //Ocultar columna 11 Pendiente (P) (S) (R)
            TableColumn columna = tabla.getColumnModel().getColumn(13);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            TableColumn columna2 = tabla.getColumnModel().getColumn(0);
            columna2.setMaxWidth(0);
            columna2.setMinWidth(0);
            columna2.setPreferredWidth(0);
            tabla.setRowHeight(28);
            tabla.doLayout();
    }
    
    public void mostrar_MovHC(int tipo, JTable tabla, String estadoM, String fechI, String fechF, String area,
            String estadoT, String consultorio, String turno){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","Acto Médico","Fecha","Hora","N° H.C","Datos del Paciente","Area/Servicio/UPSS",
                              "Consultorio","Turno","Médico","Nº At","Edad","Estado","   E", "Mayor/Menor","DNI","ba"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[17];
            consulta="EXEC SP_ADMISION_MOVIMIENTO_HC_LISTADO_MOVIMIENTO ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, tipo);
            cmd.setString(2, estadoM);
            cmd.setString(3, fechI);
            cmd.setString(4, fechF);
            cmd.setString(5, area);
            cmd.setString(6, estadoT);
            cmd.setString(7, consultorio);
            cmd.setString(8, turno);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); //ID
                fila[1]=r.getString(2); //acto médico
                fila[2]=r.getString(3); // fecha creacion
                fila[3]=r.getString(4); // hora creacion
                fila[4]=r.getString(5); // num historia clinica
                fila[5]=r.getString(6); // datos del paciente
                fila[6]=r.getString(7); // descripcion unidad organica jerarquica (Servicio/area/dpto)
                fila[7]=r.getString(8); // codigo consultorio
                fila[8]=r.getString(9); // turno
                fila[9]=r.getString(10); // medico
                fila[10]=r.getString(11); // numero de atencion
                fila[11]=r.getString(12); // edad mayor menor
                
                int mm = Integer.parseInt(r.getString(12));
                if(mm<18){
                    fila[14]="MENOR";
                }else{
                    if(mm>=18){
                        fila[14]="MAYOR";
                    }
                }
                
                if (r.getString(14).trim().equals("S")){
                    fila[12]="Salida";
                }else if (r.getString(14).trim().equals("P")){
                    fila[12]="Pendiente";
                }else if (r.getString(14).trim().equals("T")){
                    fila[12]="Salida";
                }else if (r.getString(14).trim().equals("C")){
                    fila[12]="Salida";
                }else if (r.getString(14).trim().equals("R")){
                    fila[12]="Retorno";
                }                   
                fila[13]=r.getString(14); //estado oculto   
                fila[15]=r.getString(16); //estado oculto    
                fila[16]=r.getString(17); //estado oculto
                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoMovHistoriaClinica(tabla);
            //combobox en jTable
            TableColumn tc = tabla.getColumnModel().getColumn(12);
            TableCellEditor tce = new DefaultCellEditor(jcbx);
            tc.setCellEditor(tce);
            //
        } catch (Exception e) {
            System.out.println("Error_mostrar_MovHC: " + e.getMessage());
        }
    }
    
    public boolean actualizarEstadoMovHC(String estadoM,int id,String usuario)
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_ADMISION_MOVIMIENTO_HC_ACTUALIZAR_ESTADO ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            System.out.println(estadoM);
            System.out.println(id);
            System.out.println(usuario);
            cmd.setString(1, estadoM);
            cmd.setInt(2, id);
            cmd.setString(3, usuario);
            if(!cmd.execute())
            {
                resp = true;
            }else{
                resp = false;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          //OCULTOESystem.out.println("Error_actualizarEstadoMovHC: " + ex.getMessage());
        }
        return resp;
    }
    Conexion con = new Conexion();
     public void reporteCODHIST(String id_documento) {
        try {
            Map parametros = new HashMap();
            parametros.put("CODHIS",id_documento);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/cajaCentral/CODHIST.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("error i "+e);
                
            }
    } 
    public MovimientoHistoriaClinica()
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
     * @return the id_mov
     */
    public int getId_mov() {
        return id_mov;
    }

    /**
     * @param id_mov the id_mov to set
     */
    public void setId_mov(int id_mov) {
        this.id_mov = id_mov;
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
     * @return the cod_uni_organica_jerar
     */
    public String getCod_uni_organica_jerar() {
        return cod_uni_organica_jerar;
    }

    /**
     * @param cod_uni_organica_jerar the cod_uni_organica_jerar to set
     */
    public void setCod_uni_organica_jerar(String cod_uni_organica_jerar) {
        this.cod_uni_organica_jerar = cod_uni_organica_jerar;
    }

    /**
     * @return the cod_per
     */
    public String getCod_per() {
        return cod_per;
    }

    /**
     * @param cod_per the cod_per to set
     */
    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    /**
     * @return the id_cod_det
     */
    public String getId_cod_det() {
        return id_cod_det;
    }

    /**
     * @param id_cod_det the id_cod_det to set
     */
    public void setId_cod_det(String id_cod_det) {
        this.id_cod_det = id_cod_det;
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
     * @return the fecha_aten
     */
    public String getFecha_aten() {
        return fecha_aten;
    }

    /**
     * @param fecha_aten the fecha_aten to set
     */
    public void setFecha_aten(String fecha_aten) {
        this.fecha_aten = fecha_aten;
    }

    /**
     * @return the hora_aten
     */
    public String getHora_aten() {
        return hora_aten;
    }

    /**
     * @param hora_aten the hora_aten to set
     */
    public void setHora_aten(String hora_aten) {
        this.hora_aten = hora_aten;
    }

    /**
     * @return the tipo_edad_consulta
     */
    public String getTipo_edad_consulta() {
        return tipo_edad_consulta;
    }

    /**
     * @param tipo_edad_consulta the tipo_edad_consulta to set
     */
    public void setTipo_edad_consulta(String tipo_edad_consulta) {
        this.tipo_edad_consulta = tipo_edad_consulta;
    }

    /**
     * @return the estado_movimiento
     */
    public String getEstado_movimiento() {
        return estado_movimiento;
    }

    /**
     * @param estado_movimiento the estado_movimiento to set
     */
    public void setEstado_movimiento(String estado_movimiento) {
        this.estado_movimiento = estado_movimiento;
    }

    /**
     * @return the num_aten
     */
    public int getNum_aten() {
        return num_aten;
    }

    /**
     * @param num_aten the num_aten to set
     */
    public void setNum_aten(int num_aten) {
        this.num_aten = num_aten;
    }

    /**
     * @return the fecha_pen
     */
    public String getFecha_pen() {
        return fecha_pen;
    }

    /**
     * @param fecha_pen the fecha_pen to set
     */
    public void setFecha_pen(String fecha_pen) {
        this.fecha_pen = fecha_pen;
    }

    /**
     * @return the hora_pen
     */
    public String getHora_pen() {
        return hora_pen;
    }

    /**
     * @param hora_pen the hora_pen to set
     */
    public void setHora_pen(String hora_pen) {
        this.hora_pen = hora_pen;
    }

    /**
     * @return the nom_pc_pendiente
     */
    public String getNom_pc_pendiente() {
        return nom_pc_pendiente;
    }

    /**
     * @param nom_pc_pendiente the nom_pc_pendiente to set
     */
    public void setNom_pc_pendiente(String nom_pc_pendiente) {
        this.nom_pc_pendiente = nom_pc_pendiente;
    }

    /**
     * @return the fecha_salida
     */
    public String getFecha_salida() {
        return fecha_salida;
    }

    /**
     * @param fecha_salida the fecha_salida to set
     */
    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    /**
     * @return the hora_salida
     */
    public String getHora_salida() {
        return hora_salida;
    }

    /**
     * @param hora_salida the hora_salida to set
     */
    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    /**
     * @return the nom_pc_salida
     */
    public String getNom_pc_salida() {
        return nom_pc_salida;
    }

    /**
     * @param nom_pc_salida the nom_pc_salida to set
     */
    public void setNom_pc_salida(String nom_pc_salida) {
        this.nom_pc_salida = nom_pc_salida;
    }

    /**
     * @return the fecha_ret
     */
    public String getFecha_ret() {
        return fecha_ret;
    }

    /**
     * @param fecha_ret the fecha_ret to set
     */
    public void setFecha_ret(String fecha_ret) {
        this.fecha_ret = fecha_ret;
    }

    /**
     * @return the hora_ret
     */
    public String getHora_ret() {
        return hora_ret;
    }

    /**
     * @param hora_ret the hora_ret to set
     */
    public void setHora_ret(String hora_ret) {
        this.hora_ret = hora_ret;
    }

    /**
     * @return the nom_pc_retorno
     */
    public String getNom_pc_retorno() {
        return nom_pc_retorno;
    }

    /**
     * @param nom_pc_retorno the nom_pc_retorno to set
     */
    public void setNom_pc_retorno(String nom_pc_retorno) {
        this.nom_pc_retorno = nom_pc_retorno;
    }

    /**
     * @return the estado_movi
     */
    public String getEstado_movi() {
        return estado_movi;
    }

    /**
     * @param estado_movi the estado_movi to set
     */
    public void setEstado_movi(String estado_movi) {
        this.estado_movi = estado_movi;
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
     * @return the usu_usuario_pen
     */
    public String getUsu_usuario_pen() {
        return usu_usuario_pen;
    }

    /**
     * @param usu_usuario_pen the usu_usuario_pen to set
     */
    public void setUsu_usuario_pen(String usu_usuario_pen) {
        this.usu_usuario_pen = usu_usuario_pen;
    }

    /**
     * @return the usu_usuario_salida
     */
    public String getUsu_usuario_salida() {
        return usu_usuario_salida;
    }

    /**
     * @param usu_usuario_salida the usu_usuario_salida to set
     */
    public void setUsu_usuario_salida(String usu_usuario_salida) {
        this.usu_usuario_salida = usu_usuario_salida;
    }

    /**
     * @return the usu_usuario_ret
     */
    public String getUsu_usuario_ret() {
        return usu_usuario_ret;
    }

    /**
     * @param usu_usuario_ret the usu_usuario_ret to set
     */
    public void setUsu_usuario_ret(String usu_usuario_ret) {
        this.usu_usuario_ret = usu_usuario_ret;
    }

    /**
     * @return the usu_codigo
     */
    public String getUsu_codigo() {
        return usu_codigo;
    }

    /**
     * @param usu_codigo the usu_codigo to set
     */
    public void setUsu_codigo(String usu_codigo) {
        this.usu_codigo = usu_codigo;
    }

    /**
     * @return the est_abrev
     */
    public String getEst_abrev() {
        return est_abrev;
    }

    /**
     * @param est_abrev the est_abrev to set
     */
    public void setEst_abrev(String est_abrev) {
        this.est_abrev = est_abrev;
    }
    
}
