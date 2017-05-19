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
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.ConsultorioEx.ConsultorioExtDiagnostico;


/**
 *
 * @author MYS1
 */
public class ConsultorioExtConsultorioDiagnostico implements Serializable {

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
    private int idDx;
    private int idConsultorioEx;
    private int ID_CIE10;
    private String tipod;
    private String info;
    private String fechaActu;
    private String horaActu;
    private String estado;
    private String nomPc;
    private String codUsu;
    
    public boolean mantenimientoConsultorioExtDiagnostico(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_DIAGNOSTICO ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdDx());
            cmd.setInt(2, getIdConsultorioEx());
            cmd.setInt(3, getID_CIE10());
            cmd.setString(4, getTipod());
            cmd.setString(5, getInfo());
            cmd.setString(6, getCodUsu());
            cmd.setString(7, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorio DIAGNOSTICO: " + ex.getMessage());
        }
        return resp;
    }
    
    public void ConsultoriosExtDiagnosticoListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_DIAGNOSTICO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                
                ConsultorioExtDiagnostico.lblIDCIE10.setText(r.getString(6)); 
                ConsultorioExtDiagnostico.txtCIE10.setText(r.getString(3)); 
//                ConsultorioExtDiagnostico.cbxTipod.setText(r.getString(4));
                ConsultorioExtDiagnostico.txtInfo.setText(r.getString(5));

                
                ConsultorioExtDiagnostico.lblIDDX.setText(r.getString(1));

                if (!ConsultorioExtDiagnostico.lblIDDX.getText().equals("") ){
                    ConsultorioExtDiagnostico.btnGuardar.setEnabled(false);
                    ConsultorioExtDiagnostico.btneditar.setEnabled(true);
                    
                    ConsultorioExtDiagnostico.txtCIE10.setEnabled(true);
                    ConsultorioExtDiagnostico.cbxTipod.setEnabled(true);
                    ConsultorioExtDiagnostico.txtInfo.setEnabled(true);
                    
                    ConsultorioExtDiagnostico.var.setText("2");
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
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
     public void formatoTablaCargarCie10(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);//CODIGO
        tabla.setRowHeight(30);
    }
     
     public void TriajeListarReporte(String id,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","id_consulltorio","CIE10","Tipo","Información Complementaria",
                "idCIE10"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CONSULTORIO_EXT_LISTAR_CONSULTORIO_DIAGNOSTICO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);

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
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);

        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);//nhc
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);//paciente
        tabla.getColumnModel().getColumn(4).setPreferredWidth(400);//fecha de ingreso
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0); 

        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(35);
    }
    
    

    public ConsultorioExtConsultorioDiagnostico() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtConsultorioDiagnostico(int idDx) {
        this.idDx = idDx;
    }

    public int getIdDx() {
        return idDx;
    }

    public void setIdDx(int idDx) {
        this.idDx = idDx;
    }

    public String getTipod() {
        return tipod;
    }

    public void setTipod(String tipo) {
        this.tipod = tipo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public int getID_CIE10() {
        return ID_CIE10;
    }

    public void setID_CIE10(int ID_CIE10) {
        this.ID_CIE10 = ID_CIE10;
    }

    
    
}
