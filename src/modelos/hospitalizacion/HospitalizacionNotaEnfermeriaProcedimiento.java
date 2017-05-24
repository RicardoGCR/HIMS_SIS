/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.hospitalizacion;

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

/**
 *
 * @author PC02
 */
public class HospitalizacionNotaEnfermeriaProcedimiento implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int neId;
    private String codNomenCaja;
    private int necptId;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    private HospitalizacionNotaEnfermeria hospitalizacionNotaEnfermeria;

    public boolean mantenimientoHospitalizacionNotaEnfermeriaProcedimiento(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_MANTENIMIENTO_NOTA_ENFERMERIA_PROCEDIMIENTO ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getNecptId());
            cmd.setInt(2, getNeId());
            cmd.setString(3, getCodNomenCaja());
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
            System.out.println("Error: mantenimientoHospitalizacionNotaEnfermeriaProcedimiento: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoTablaDiagPresuntivo(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(550);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(60);
        tabla.setRowHeight(30);
    }
    
    public void inicializarTabla(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Fecha","Hora"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaDiagPresuntivo(tabla);
    }
    
    public void listarProcedimientos(String ne_id, JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código CPT","Nomenclatura","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC HOSPITALIZAR_LISTAR_NOTA_ENFERMERIA_PROCEDIMIENTOS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ne_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // nhc
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDiagPresuntivo(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarProcedimientos: " + e.getMessage());
        }
    }
    
    public HospitalizacionNotaEnfermeriaProcedimiento() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public HospitalizacionNotaEnfermeriaProcedimiento(int necptId) {
        this.necptId = necptId;
    }

    public int getNecptId() {
        return necptId;
    }

    public void setNecptId(int necptId) {
        this.necptId = necptId;
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

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public HospitalizacionNotaEnfermeria getHospitalizacionNotaEnfermeria() {
        return hospitalizacionNotaEnfermeria;
    }

    public void setHospitalizacionNotaEnfermeria(HospitalizacionNotaEnfermeria hospitalizacionNotaEnfermeria) {
        this.hospitalizacionNotaEnfermeria = hospitalizacionNotaEnfermeria;
    }

    @Override
    public String toString() {
        return "modelos.hospitalizacion.HospitalizacionNotaEnfermeriaProcedimiento[ necptId=" + necptId + " ]";
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
     * @return the neId
     */
    public int getNeId() {
        return neId;
    }

    /**
     * @param neId the neId to set
     */
    public void setNeId(int neId) {
        this.neId = neId;
    }

    /**
     * @return the codNomenCaja
     */
    public String getCodNomenCaja() {
        return codNomenCaja;
    }

    /**
     * @param codNomenCaja the codNomenCaja to set
     */
    public void setCodNomenCaja(String codNomenCaja) {
        this.codNomenCaja = codNomenCaja;
    }
    
}
