package modelos.hospitalizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

/**
 *
 * @author MYS - INGENIEROS
 */
public class HospitalizacionHCHospitalaria {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int hhh_id;
    private int id_preventa;
    private String tipo_atencion;
    private String anamnesis;
    private String enf_infancia;
    private String enf_adulto;
    private String trauma_acc;
    private String habitos;
    private String tiempo_enf;
    private String motivo_enf;
    private String sintoma_prin;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String cod_usu;

    public boolean mantenimientoHospitalizacionHCHospitalaria(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_HC_HOSPITALARIA_MANTENIMIENTO ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHhh_id());
            cmd.setInt(2, getId_preventa());
            cmd.setString(3, getTipo_atencion());
            cmd.setString(4, getAnamnesis());
            cmd.setString(5, getEnf_infancia());
            cmd.setString(6, getEnf_adulto());
            cmd.setString(7, getTrauma_acc());
            cmd.setString(8, getHabitos());
            cmd.setString(9, getTiempo_enf());
            cmd.setString(10, getMotivo_enf());
            cmd.setString(11, getSintoma_prin());
            cmd.setString(12, getCod_usu());
            cmd.setString(13, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error - modelos.HospitalizacionHCHospitalaria: \nmantenimientoHospitalizacionHCHospitalaria: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoTablaBuscar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(14).setPreferredWidth(100);
        TableColumn columna1 = tabla.getColumnModel().getColumn(0);
            columna1.setMaxWidth(0);
            columna1.setMinWidth(0);
            columna1.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void listarFormatoHC(int hhh_id, String busqueda, String tipo, JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Acto Médico","Nº H.C.","DNI","Datos del Paciente","Tipo de Atención",
            "Anamnesis","Enfermedades de la infancia","Enfermedades del adulto","Trauma accidentes",
            "Hábitos","Tiempo de enfermedad","Motivo de enfermedad","Síntomas principales","Cama"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC HOSPITALIZACION_HC_HOSPITALARIA_LISTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, hhh_id);
            cmd.setString(2, busqueda);
            cmd.setString(3, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // nhc
                fila[3]=r.getString(4); //
                fila[4]=r.getString(5); // 
                fila[5]=r.getString(6); // id
                fila[6]=r.getString(7); // dni
                fila[7]=r.getString(8); // nhc
                fila[8]=r.getString(9); //
                fila[9]=r.getString(10); // 
                fila[10]=r.getString(11); // nhc
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
            formatoTablaBuscar(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarFormatoHC: " + e.getMessage());
        }
    }
    
    public int idHospitalizacionHCHospitalaria(){
        int id = 0;
        try {
            String consulta = "EXEC HOSPITALIZACION_HC_HOSPITALARIA_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getInt(1);
        }
        }catch(Exception ex){
            System.out.println("Error: idHospitalizacionHCHospitalaria: " + ex.getMessage());
        }
        return id;
    }
    
    public HospitalizacionHCHospitalaria()
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
     * @return the hhh_id
     */
    public int getHhh_id() {
        return hhh_id;
    }

    /**
     * @param hhh_id the hhh_id to set
     */
    public void setHhh_id(int hhh_id) {
        this.hhh_id = hhh_id;
    }

    /**
     * @return the id_preventa
     */
    public int getId_preventa() {
        return id_preventa;
    }

    /**
     * @param id_preventa the id_preventa to set
     */
    public void setId_preventa(int id_preventa) {
        this.id_preventa = id_preventa;
    }

    /**
     * @return the tipo_atencion
     */
    public String getTipo_atencion() {
        return tipo_atencion;
    }

    /**
     * @param tipo_atencion the tipo_atencion to set
     */
    public void setTipo_atencion(String tipo_atencion) {
        this.tipo_atencion = tipo_atencion;
    }

    /**
     * @return the anamnesis
     */
    public String getAnamnesis() {
        return anamnesis;
    }

    /**
     * @param anamnesis the anamnesis to set
     */
    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    /**
     * @return the enf_infancia
     */
    public String getEnf_infancia() {
        return enf_infancia;
    }

    /**
     * @param enf_infancia the enf_infancia to set
     */
    public void setEnf_infancia(String enf_infancia) {
        this.enf_infancia = enf_infancia;
    }

    /**
     * @return the enf_adulto
     */
    public String getEnf_adulto() {
        return enf_adulto;
    }

    /**
     * @param enf_adulto the enf_adulto to set
     */
    public void setEnf_adulto(String enf_adulto) {
        this.enf_adulto = enf_adulto;
    }

    /**
     * @return the trauma_acc
     */
    public String getTrauma_acc() {
        return trauma_acc;
    }

    /**
     * @param trauma_acc the trauma_acc to set
     */
    public void setTrauma_acc(String trauma_acc) {
        this.trauma_acc = trauma_acc;
    }

    /**
     * @return the habitos
     */
    public String getHabitos() {
        return habitos;
    }

    /**
     * @param habitos the habitos to set
     */
    public void setHabitos(String habitos) {
        this.habitos = habitos;
    }

    /**
     * @return the tiempo_enf
     */
    public String getTiempo_enf() {
        return tiempo_enf;
    }

    /**
     * @param tiempo_enf the tiempo_enf to set
     */
    public void setTiempo_enf(String tiempo_enf) {
        this.tiempo_enf = tiempo_enf;
    }

    /**
     * @return the motivo_enf
     */
    public String getMotivo_enf() {
        return motivo_enf;
    }

    /**
     * @param motivo_enf the motivo_enf to set
     */
    public void setMotivo_enf(String motivo_enf) {
        this.motivo_enf = motivo_enf;
    }

    /**
     * @return the sintoma_prin
     */
    public String getSintoma_prin() {
        return sintoma_prin;
    }

    /**
     * @param sintoma_prin the sintoma_prin to set
     */
    public void setSintoma_prin(String sintoma_prin) {
        this.sintoma_prin = sintoma_prin;
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
    
}
