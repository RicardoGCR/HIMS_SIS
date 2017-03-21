/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

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
 * @author MYS1
 */
public class ConsultorioEXTriaje {
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
    private String id_doc;
    
    public String codUsuario(String nombreUsuario){
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
     
    public boolean mantenimientoCXTriaje(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CX_TRIAJE_MANTANIMIENTO ?,?,?,?,?,?,?,?,?,?,?";
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
            cmd.setString(10,getId_doc());
            cmd.setString(11, tipo);
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
     public void TriajeListarReporte(String nhc,JTable tabla,String f1, String f2,String tipo){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Acto Médico","DNI","N° H.C.","Paciente",
                "Fecha","Edad","","FC","FR","PA","Peso","Tº","Talla"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CONSULTORIO_EXT_TRIAJE_LISTAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            cmd.setString(2, f1);
            cmd.setString(3, f2);
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
            formatoTablaTriajeReporte(tabla);
        } catch (Exception e) {
            System.out.println("Error: admisionEmergenciaTriajeListarReporte: " + e.getMessage());
        }
    }
    public void TriajeListar(String idhc,String fecha,JTable tabla, String estado){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"N.","N° de Registro","Id Triaje","Fecha de ing",
                "Hora de ing","Traído por","Parentesco","FC","FR",
                "PA","Peso","T°","Talla"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
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
                fila[13]=r.getString(14); //
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
    
    public void formatoTablaTriajeReporte(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//nhc
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//nhc
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(240);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);//fecha de ingreso
        tabla.getColumnModel().getColumn(6).setPreferredWidth(30);//traido por
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(40);//parentesco
        tabla.getColumnModel().getColumn(9).setPreferredWidth(40);//fc
        tabla.getColumnModel().getColumn(10).setPreferredWidth(40);//fr  
        tabla.getColumnModel().getColumn(11).setPreferredWidth(40);//pa
        tabla.getColumnModel().getColumn(12).setPreferredWidth(40);//peso
        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    
    
    
    public ConsultorioEXTriaje()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public static DefaultTableModel getM() {
        return m;
    }

    public static void setM(DefaultTableModel m) {
        ConsultorioEXTriaje.m = m;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getTriaje_id() {
        return triaje_id;
    }

    public void setTriaje_id(String triaje_id) {
        this.triaje_id = triaje_id;
    }

    public int getPreventa_id() {
        return preventa_id;
    }

    public void setPreventa_id(int preventa_id) {
        this.preventa_id = preventa_id;
    }

    public String getTriaje_fv_pa() {
        return triaje_fv_pa;
    }

    public void setTriaje_fv_pa(String triaje_fv_pa) {
        this.triaje_fv_pa = triaje_fv_pa;
    }

    public String getTriaje_fv_fc() {
        return triaje_fv_fc;
    }

    public void setTriaje_fv_fc(String triaje_fv_fc) {
        this.triaje_fv_fc = triaje_fv_fc;
    }

    public String getTriaje_fv_fr() {
        return triaje_fv_fr;
    }

    public void setTriaje_fv_fr(String triaje_fv_fr) {
        this.triaje_fv_fr = triaje_fv_fr;
    }

    public String getTriaje_fv_t() {
        return triaje_fv_t;
    }

    public void setTriaje_fv_t(String triaje_fv_t) {
        this.triaje_fv_t = triaje_fv_t;
    }

    public String getTriaje_fv_peso() {
        return triaje_fv_peso;
    }

    public void setTriaje_fv_peso(String triaje_fv_peso) {
        this.triaje_fv_peso = triaje_fv_peso;
    }

    public String getTriaje_fecha_actu() {
        return triaje_fecha_actu;
    }

    public void setTriaje_fecha_actu(String triaje_fecha_actu) {
        this.triaje_fecha_actu = triaje_fecha_actu;
    }

    public String getTriaje_hora_actu() {
        return triaje_hora_actu;
    }

    public void setTriaje_hora_actu(String triaje_hora_actu) {
        this.triaje_hora_actu = triaje_hora_actu;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public String getTriaje_nom_pc() {
        return triaje_nom_pc;
    }

    public void setTriaje_nom_pc(String triaje_nom_pc) {
        this.triaje_nom_pc = triaje_nom_pc;
    }

    public String getTriaje_estado() {
        return triaje_estado;
    }

    public void setTriaje_estado(String triaje_estado) {
        this.triaje_estado = triaje_estado;
    }

    public String getTriaje_talla() {
        return triaje_talla;
    }

    public void setTriaje_talla(String triaje_talla) {
        this.triaje_talla = triaje_talla;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getId_doc() {
        return id_doc;
    }

    public void setId_doc(String id_doc) {
        this.id_doc = id_doc;
    }

   
    
    
}
