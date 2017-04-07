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
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class ConsultorioExtRsMonitoreo implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    private Connection cn;
    private int rs_id;
    private Long moId;
    private String moFecha;
    private String moEdad;
    private String moPeso;
    private String moTalla;
    private Character estado;
    private String codUsu;
    private String nomPc;
    private int id_cie10;

    public boolean mantenimientoConsultorioExtRsMonitoreo(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_RS_MONITOREO ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRs_id());
            cmd.setString(2, getMoFecha());
            cmd.setString(3, getMoEdad());
            cmd.setString(4, getMoPeso());
            cmd.setString(5, getMoTalla());
            cmd.setInt(6, getId_cie10());
            cmd.setString(7, getCodUsu());
            cmd.setString(8, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtRsMonitoreo: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoTablaDiagnostico(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(250);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTabla(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Edad","Peso","Talla","Código CIE 10","Diagnóstico"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaDiagnostico(tabla);
    }
    
    public void listarDiagnostico(String id, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Edad","Peso","Talla","Código CIE 10","Diagnóstico"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[6];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_RS_MONITOREO_LISTAR ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, id);
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); // id
                    fila[1]=r.getString(2); // dni
                    fila[2]=r.getString(3); // nhc
                    fila[3]=r.getString(4); // id
                    fila[4]=r.getString(5); // dni
                    fila[5]=r.getString(6); // nhc
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDiagnostico(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDiagnostico: " + e.getMessage());
        }
    }
    
    public ConsultorioExtRsMonitoreo() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsMonitoreo(Long moId) {
        this.moId = moId;
    }

    public Long getMoId() {
        return moId;
    }

    public void setMoId(Long moId) {
        this.moId = moId;
    }

    public String getMoFecha() {
        return moFecha;
    }

    public void setMoFecha(String moFecha) {
        this.moFecha = moFecha;
    }

    public String getMoEdad() {
        return moEdad;
    }

    public void setMoEdad(String moEdad) {
        this.moEdad = moEdad;
    }

    public String getMoPeso() {
        return moPeso;
    }

    public void setMoPeso(String moPeso) {
        this.moPeso = moPeso;
    }

    public String getMoTalla() {
        return moTalla;
    }

    public void setMoTalla(String moTalla) {
        this.moTalla = moTalla;
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

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moId != null ? moId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsMonitoreo)) {
            return false;
        }
        ConsultorioExtRsMonitoreo other = (ConsultorioExtRsMonitoreo) object;
        if ((this.moId == null && other.moId != null) || (this.moId != null && !this.moId.equals(other.moId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsMonitoreo[ moId=" + moId + " ]";
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
     * @return the rs_id
     */
    public int getRs_id() {
        return rs_id;
    }

    /**
     * @param rs_id the rs_id to set
     */
    public void setRs_id(int rs_id) {
        this.rs_id = rs_id;
    }

    /**
     * @return the id_cie10
     */
    public int getId_cie10() {
        return id_cie10;
    }

    /**
     * @param id_cie10 the id_cie10 to set
     */
    public void setId_cie10(int id_cie10) {
        this.id_cie10 = id_cie10;
    }
    
}
