
package modelos.admisionEmergencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static modelos.admisionEmergencia.AdmisionEmergenciaCabecera.m;
import servicios.Conexion;
import vista.ConsultorioEx.HistoriaClinica;
import vista.admisionEmergencia.FrmFormatoEmergencia;
import vista.admisionEmergencia.FrmFormatoEmergenciaTriaje;

/**
 *
 * @author Yamila Rocca Ruiz
 */
public class AdmisionEmergenciaTriaje {
    static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private String triaje_id;
    private int preventa_id;
    private String triaje_fv_pa;
    private String triaje_fv_fc;
    private String triaje_fv_fr;
    private String triaje_fv_t;
    private String triaje_fv_peso;
    private String triaje_fecha_actu;
    private String triaje_hora_actu;
    private String cod_usu;
    private String triaje_nom_pc;
    private String triaje_estado;
    private String triaje_talla;
    private String modulo;
    private String id_hc;
    private String IDM;

    
    public boolean mantenimientoAdmisionemergenciaTriaje(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC ADMISION_EMERGENCIA_TRIAJE_MANTANIMIENTO ?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getTriaje_id());
            cmd.setString(2, getTriaje_fv_pa());
            cmd.setString(3, getTriaje_fv_fc());
            cmd.setString(4, getTriaje_fv_fr());
            cmd.setString(5, getTriaje_fv_t());
            cmd.setString(6, getTriaje_fv_peso());
            cmd.setString(7, getCod_usu());
            cmd.setString(8, getTriaje_talla());
            cmd.setString(9, getModulo());
            cmd.setString(10, getId_hc());
            cmd.setString(11, getIDM());
            cmd.setString(12, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoAdmisionemergenciaTriaje: " + ex.getMessage());
        }
        return resp;
    }
    
    public String idAdmisionEmergenciaTriaje(){
        String id = "";
        try {
            String consulta = "EXEC ADMISION_EMERGENCIA_TRIAJE_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_idAdmisionEmergenciaTriaje: " + ex.getMessage());
        }
        return id;
    }
    
    public void formatoTablaTriajeListar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//
        tabla.getColumnModel().getColumn(1).setPreferredWidth(170);//
        tabla.getColumnModel().getColumn(2).setPreferredWidth(170);//
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(50);
        tabla.setRowHeight(25);
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.setRowHeight(0);
            tabla.doLayout();
    }
    
    public void admisionEmergenciaTriajeListar(String idhc,String fecha,JTable tabla, String estado){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"N.","N° de Registro","Id Triaje","Fecha de ing",
                "Hora de ing","Traído por","Parentesco","FC","FR",
                "PA","Peso","T°","Talla"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC ADMISION_EMERGENCIA_TRIAJE_MODIF_MOSTRAR_LISTASEMER ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, idhc);
            cmd.setString(2, fecha);
            cmd.setString(3, estado);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaTriajeListar(tabla);
        } catch (Exception e) {
            System.out.println("Error_admisionEmergenciaTriajeListar: " + e.getMessage());
        }
    }
    
    public void formatoTablaConsultorioExListar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);//
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);//
        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);//
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(50);
        tabla.setRowHeight(30);
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.setRowHeight(0);
            tabla.doLayout();
    }
    
    public void consultorioExListar(String busqueda,String tipo,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Acto Médico","DNI","Nº H.C.",
                "Paciente","Edad","Ocupación","FC","FR",
                "PA","Peso","T°","Talla","HC"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CONSULTORIO_EXT_TRIAJE_LISTAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
            cmd.setString(2, "");
            cmd.setString(3, "");
            cmd.setString(4, tipo);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaConsultorioExListar(tabla);
        } catch (Exception e) {
            System.out.println("Error: consultorioExListar: " + e.getMessage());
        }
    }
    
    public void formatoTablaConsultorioExListarC(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);//
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);//
        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);//
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(14).setPreferredWidth(50);
        tabla.setRowHeight(30);
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.setRowHeight(0);
            tabla.doLayout();
            
        TableColumn columna14 = tabla.getColumnModel().getColumn(14);
            columna14.setMaxWidth(0);
            columna14.setMinWidth(0);
            columna14.setPreferredWidth(0);
            tabla.setRowHeight(0);
            tabla.doLayout();
    }
    
    public void consultorioExListarC(String busqueda,String tipo,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Acto Médico","DNI","Nº H.C.",
                "Paciente","Edad","Ocupación","FC","FR",
                "PA","Peso","T°","Talla","HC","Código"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CONSULTORIO_EXT_TRIAJE_LISTAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
            cmd.setString(2, "");
            cmd.setString(3, "");
            cmd.setString(4, tipo);
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
                fila[14]=r.getString(15); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaConsultorioExListarC(tabla);
        } catch (Exception e) {
            System.out.println("Error: consultorioExListarC: " + e.getMessage());
        }
    }
    
    public void formatoTablaTriajeReporte(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//id triaje
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);//nhc
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//nhc
        tabla.getColumnModel().getColumn(3).setPreferredWidth(240);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(60);//fecha de ingreso
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);//hora de ingreso 
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//traido por
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);//parentesco
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);//fc
        tabla.getColumnModel().getColumn(10).setPreferredWidth(50);//fr  
        tabla.getColumnModel().getColumn(11).setPreferredWidth(50);//pa
        tabla.getColumnModel().getColumn(12).setMinWidth(0);
        tabla.getColumnModel().getColumn(12).setMaxWidth(0);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(50);//pa

        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(38);
    }
    
    public void admisionEmergenciaTriajeListarReporte(String nhc,JTable tabla, String fechai,String fechaf){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","N° H.C.","DNI","Paciente",
                "Fecha de ingreso","Hora de ingreso",
                "FC","FR","PA","Peso","T°","Talla","id","IDM"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec ADMISION_EMERGENCIA_TRIAJE_LISTAR ?,?,?";
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaTriajeReporte(tabla);
        } catch (Exception e) {
            System.out.println("Error: admisionEmergenciaTriajeListarReporte: " + e.getMessage());
        }
    }
    
    public void historiaClinicaTriaje(String id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_HISTORIAL_EMERGENCIA_TRIAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                HistoriaClinica.txtPA.setText(r.getString(2)); 
                HistoriaClinica.txtFC.setText(r.getString(3)); 
                HistoriaClinica.txtFR.setText(r.getString(4)); 
                HistoriaClinica.txtT.setText(r.getString(5)); 
                HistoriaClinica.txtPeso.setText(r.getString(6)); 
                HistoriaClinica.txtTalla.setText(r.getString(7)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: historiaClinicaTratamiento  " + e.getMessage());
        }
    } 
    
    public String triajeID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 TRIAJE_ID FROM ADMISION_EMERGENCIA_TRIAJE WHERE TRIAJE_NOM_PC = HOST_NAME()"
                    + "ORDER BY TRIAJE_ID DESC";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               FrmFormatoEmergenciaTriaje.txtIDTriaje.setText(rs.getString(1));
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: triajeID: " + ex.getMessage());
        }
        return cod;
    }
    
    public AdmisionEmergenciaTriaje()
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
     * @return the emercab_id
     */
    public int getPreventa_id() {
        return preventa_id;
    }

    /**
     * @param emercab_id the emercab_id to set
     */
    public void setPreventa_id(int preventa_id) {
        this.preventa_id = preventa_id;
    }

    /**
     * @return the triaje_fv_pa
     */
    public String getTriaje_fv_pa() {
        return triaje_fv_pa;
    }

    /**
     * @param triaje_fv_pa the triaje_fv_pa to set
     */
    public void setTriaje_fv_pa(String triaje_fv_pa) {
        this.triaje_fv_pa = triaje_fv_pa;
    }

    /**
     * @return the triaje_fv_fc
     */
    public String getTriaje_fv_fc() {
        return triaje_fv_fc;
    }

    /**
     * @param triaje_fv_fc the triaje_fv_fc to set
     */
    public void setTriaje_fv_fc(String triaje_fv_fc) {
        this.triaje_fv_fc = triaje_fv_fc;
    }

    /**
     * @return the triaje_fv_fr
     */
    public String getTriaje_fv_fr() {
        return triaje_fv_fr;
    }

    /**
     * @param triaje_fv_fr the triaje_fv_fr to set
     */
    public void setTriaje_fv_fr(String triaje_fv_fr) {
        this.triaje_fv_fr = triaje_fv_fr;
    }

    /**
     * @return the triaje_fv_t
     */
    public String getTriaje_fv_t() {
        return triaje_fv_t;
    }

    /**
     * @param triaje_fv_t the triaje_fv_t to set
     */
    public void setTriaje_fv_t(String triaje_fv_t) {
        this.triaje_fv_t = triaje_fv_t;
    }

    /**
     * @return the triaje_fv_peso
     */
    public String getTriaje_fv_peso() {
        return triaje_fv_peso;
    }

    /**
     * @param triaje_fv_peso the triaje_fv_peso to set
     */
    public void setTriaje_fv_peso(String triaje_fv_peso) {
        this.triaje_fv_peso = triaje_fv_peso;
    }

    /**
     * @return the triaje_fecha_actu
     */
    public String getTriaje_fecha_actu() {
        return triaje_fecha_actu;
    }

    /**
     * @param triaje_fecha_actu the triaje_fecha_actu to set
     */
    public void setTriaje_fecha_actu(String triaje_fecha_actu) {
        this.triaje_fecha_actu = triaje_fecha_actu;
    }

    /**
     * @return the triaje_hora_actu
     */
    public String getTriaje_hora_actu() {
        return triaje_hora_actu;
    }

    /**
     * @param triaje_hora_actu the triaje_hora_actu to set
     */
    public void setTriaje_hora_actu(String triaje_hora_actu) {
        this.triaje_hora_actu = triaje_hora_actu;
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
     * @return the triaje_nom_pc
     */
    public String getTriaje_nom_pc() {
        return triaje_nom_pc;
    }

    /**
     * @param triaje_nom_pc the triaje_nom_pc to set
     */
    public void setTriaje_nom_pc(String triaje_nom_pc) {
        this.triaje_nom_pc = triaje_nom_pc;
    }

    /**
     * @return the triaje_estado
     */
    public String getTriaje_estado() {
        return triaje_estado;
    }

    /**
     * @param triaje_estado the triaje_estado to set
     */
    public void setTriaje_estado(String triaje_estado) {
        this.triaje_estado = triaje_estado;
    }

    /**
     * @return the triaje_talla
     */
    public String getTriaje_talla() {
        return triaje_talla;
    }

    /**
     * @param triaje_talla the triaje_talla to set
     */
    public void setTriaje_talla(String triaje_talla) {
        this.triaje_talla = triaje_talla;
    }

    /**
     * @return the modulo
     */
    public String getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getId_hc() {
        return id_hc;
    }

    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    public String getIDM() {
        return IDM;
    }

    public void setIDM(String IDM) {
        this.IDM = IDM;
    }
    
    
}
