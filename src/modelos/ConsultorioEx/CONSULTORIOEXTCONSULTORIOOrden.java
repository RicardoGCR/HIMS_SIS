/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.ConsultorioEx.ConsultorioExtOrdenes;


/**
 *
 * @author MYS1
 */
public class CONSULTORIOEXTCONSULTORIOOrden implements Serializable {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    private int idOrden;
    private int idConsultorioEx;
    private int id_Preventa;
    private String fechaActu;
    private String horaActu;
    private String estado;
    private String nomPc;
    private String codUsu;
    private String id_hc;
    private String cod_nomen;
    
    public boolean mantenimientoConsultorioExtOrden(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_ORDEN ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdOrden());
            cmd.setInt(2, getIdConsultorioEx());
            cmd.setInt(3, getId_Preventa());
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
            System.out.println("Error: mantenimientoConsultorio ORDEN: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean mantenimientoConsultorioExtPREVENTA(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_ORDEN_PREVENTA ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_Preventa());
            cmd.setString(2, getId_hc());
            cmd.setString(3, getCod_nomen());
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
            System.out.println("Error: mantenimientoConsultorio PREVENTA: " + ex.getMessage());
        }
        return resp;
    }
    
    public void cargarDatosPreventa(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id_Preventa","DNI","Paciente","Modulo","fecha","hora","id_hc","estado","idcpt","CPT ","CPT"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC Caja_Mostrar_Preventas_CEX ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
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
                
                
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarPreventa(tabla);
        } catch (Exception e) {
            System.out.println("Error_cargarDatosCie10: " + e.getMessage());
        }
    }
     public void formatoTablaCargarPreventa(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);  
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(10).setPreferredWidth(500);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0); 

        
        tabla.setRowHeight(45);
    }
     
         public void ConsultoriosExtOrdenListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_ORDEN ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                ConsultorioExtOrdenes.lblIDO.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }
         /////////////////////para obtener el ultimo id 
          public void ConsultoriosExtPREVENTAListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_ORDEN_ULTIMO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                ConsultorioExtOrdenes.lblIDPREVENTA.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }
          
    public void CONSULTORIO_EXT_LISTAR_CONSULTORIO_ORDEN_RECETADEAS(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID_PREVENTA","DNI","CODIGO","PACIENTE","MODULO","FECHA","HORA","HC","ESTADO","IDCPT ","CPT","ARID","IDCONSULTORIO","ACTOM","ID_ORDEN"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CONSULTORIO_EXT_LISTAR_CONSULTORIO_ORDEN_RECETADEAS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
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
                
                
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarPreventa2(tabla);
        } catch (Exception e) {
            System.out.println("Error_cargarDatos PREVENTA: " + e.getMessage());
        }
    }
     public void formatoTablaCargarPreventa2(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);  
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(10).setPreferredWidth(500);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(12).setMinWidth(0);
        tabla.getColumnModel().getColumn(12).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getColumnModel().getColumn(11).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(13).setMinWidth(0);
        tabla.getColumnModel().getColumn(13).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(14).setMinWidth(0);
        tabla.getColumnModel().getColumn(14).setMaxWidth(0); 

        
        tabla.setRowHeight(45);
    }

    public CONSULTORIOEXTCONSULTORIOOrden() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public CONSULTORIOEXTCONSULTORIOOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
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

    public int getIdConsultorioEx() {
        return idConsultorioEx;
    }

    public void setIdConsultorioEx(int idConsultorioEx) {
        this.idConsultorioEx = idConsultorioEx;
    }

    public int getId_Preventa() {
        return id_Preventa;
    }

    public void setId_Preventa(int id_Preventa) {
        this.id_Preventa = id_Preventa;
    }

    public String getId_hc() {
        return id_hc;
    }

    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    public String getCod_nomen() {
        return cod_nomen;
    }

    public void setCod_nomen(String cod_nomen) {
        this.cod_nomen = cod_nomen;
    }

    
    
}
