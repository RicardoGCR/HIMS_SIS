/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.ConsultorioEx.ConsultorioExtPerfilUsuario;


/**
 *
 * @author MYS1
 */
public class ConsultorioExtConsultorioCabecera implements Serializable {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    private int idConsultorioEx;
    private String id_hc;
    private int id_ActoMedico;
    private String fechaActu;
    private String horaActu;
    private String estado;
    private String nomPc;
    private String codUsu;
    private String Triaje_id;
    
    
    
    public boolean mantenimientoConsultorioExtCabecera(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_CABECERA ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdConsultorioEx());
            cmd.setString(2, getId_hc());
            cmd.setInt(3, getId_ActoMedico());
            cmd.setString(4, getCodUsu());
            cmd.setString(5, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorio CABECERA: " + ex.getMessage());
        }
        return resp;
    }
    
    
        public boolean mantenimientoCXTriaje(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_TRIAJE_ACTUALIZAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getTriaje_id());
            cmd.setString(2, tipo);
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
        
    
    public void TriajeListarReporte(String nhc,JTable tabla,String tipo){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Acto Médico","DNI","N° H.C.","Paciente",
                "Fecha","Edad","FC","FR","PA","Peso","Tº","Talla","IDM","idhc","Cod_det","Nº Atención","Mèdico","Turno","AM"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[20];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CONSULTORIO_EXT_TRIAJE_LISTAR_CONSULTORIO ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            cmd.setString(2, tipo);
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
                fila[15]=r.getString(16); // 
                fila[16]=r.getString(17); // 
                fila[17]=r.getString(18); // 
                fila[18]=r.getString(19); //
                fila[19]=r.getString(20); //

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
    
    public void formatoTablaTriajeReporte(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);//nhc
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);//nhc
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(4).setPreferredWidth(240);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);//fecha de ingreso
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(10).setMinWidth(0);
        tabla.getColumnModel().getColumn(10).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getColumnModel().getColumn(11).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(12).setMinWidth(0);
        tabla.getColumnModel().getColumn(12).setMaxWidth(0);
        tabla.getColumnModel().getColumn(13).setMinWidth(0);
        tabla.getColumnModel().getColumn(13).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(14).setMinWidth(0);
        tabla.getColumnModel().getColumn(14).setMaxWidth(0); 
        
        tabla.getColumnModel().getColumn(15).setMinWidth(0);
        tabla.getColumnModel().getColumn(15).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(16).setPreferredWidth(100);//fecha de ingreso
        tabla.getColumnModel().getColumn(17).setMinWidth(0);
        tabla.getColumnModel().getColumn(17).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(19).setMinWidth(0);
        tabla.getColumnModel().getColumn(19).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(18).setPreferredWidth(150);//fecha de ingreso

        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(40);
    }
    
    
    
    public void TriajeListarVer(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Paciente"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CONSULTORIO_EXT_TRIAJE_LISTAR_SOLO_NOMBRES_CONSULTORIO ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2); // 

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaTriajeVer(tabla);
        } catch (Exception e) {
            System.out.println("Error: admisionEmergenciaTriajeListarReporte: " + e.getMessage());
        }
    }
    
     public void ConsultoriosExtCABERCERAListar(String rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_CABECERA_ULTIMO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                ConsultorioExtPerfilUsuario.lblIdCabecera.setText(r.getString(1)); 
                }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios CABECERA LISTAR  " + e.getMessage());
        }
    }  
    
    public void formatoTablaTriajeVer(JTable tabla){;
        tabla.getColumnModel().getColumn(0).setPreferredWidth(10);//nhc
        tabla.getColumnModel().getColumn(1).setPreferredWidth(170);//nhc
        tabla.setRowHeight(30);
    }
    


    public ConsultorioExtConsultorioCabecera() {
          Conexion con = new Conexion();
          cn = con.conectar();
    }

    public ConsultorioExtConsultorioCabecera(int idConsultorioEx) {
        this.idConsultorioEx = idConsultorioEx;
    }

    public int getIdConsultorioEx() {
        return idConsultorioEx;
    }

    public void setIdConsultorioEx(int idConsultorioEx) {
        this.idConsultorioEx = idConsultorioEx;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public DefaultTableModel getM() {
        return m;
    }

    public void setM(DefaultTableModel m) {
        this.m = m;
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

    public String getId_hc() {
        return id_hc;
    }

    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    public int getId_ActoMedico() {
        return id_ActoMedico;
    }

    public void setId_ActoMedico(int id_ActoMedico) {
        this.id_ActoMedico = id_ActoMedico;
    }

    public String getTriaje_id() {
        return Triaje_id;
    }

    public void setTriaje_id(String Triaje_id) {
        this.Triaje_id = Triaje_id;
    }
    
}
