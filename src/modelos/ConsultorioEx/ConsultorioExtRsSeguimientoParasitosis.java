/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;
public class ConsultorioExtRsSeguimientoParasitosis implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    private Connection cn;
    private int rs_id;
    private int spId;
    private String spFecha;
    private String spEdad;
    private String spRecomendaciones;
    private Character estado;
    private String codUsu;
    private String nomPc;
    private int id_cie10;

    public boolean mantenimientoConsultorioExtRsSeguimientoParasitosis(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_RS_SEGUIMIENTO_PARASITOSIS ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRs_id());
            cmd.setString(2, getSpFecha());
            cmd.setString(3, getSpEdad());
            cmd.setInt(4, getId_cie10());
            cmd.setString(5, getSpRecomendaciones());
            cmd.setString(6, getCodUsu());
            cmd.setString(7, tipo);
            cmd.setInt(8, getSpId());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtRsSeguimientoParasitosis: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoTablaDiagnostico(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(650);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(0);
//        COLUMNAS OCULTAS
        TableColumn columna0 = tabla.getColumnModel().getColumn(0);
            columna0.setMaxWidth(0);
            columna0.setMinWidth(0);
            columna0.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna6 = tabla.getColumnModel().getColumn(0);
        columna6.setMaxWidth(0);
        columna6.setMinWidth(0);
        columna6.setPreferredWidth(0);
        tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTabla(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Fecha","Edad","Res Hb.","Código CIE 10","Diagnóstico","ID"};
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
        String titulos[]={"ID","Fecha","Edad","Res Hb.","Código CIE 10","Diagnóstico","ID"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[7];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_RS_SEGUIMIENTO_PARASITOSIS_LISTAR ?";
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
                    fila[6]=r.getString(7); // dni
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
    
    public ConsultorioExtRsSeguimientoParasitosis() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsSeguimientoParasitosis(int spId) {
        this.spId = spId;
    }

    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public String getSpFecha() {
        return spFecha;
    }

    public void setSpFecha(String spFecha) {
        this.spFecha = spFecha;
    }

    public String getSpEdad() {
        return spEdad;
    }

    public void setSpEdad(String spEdad) {
        this.spEdad = spEdad;
    }

    public String getSpRecomendaciones() {
        return spRecomendaciones;
    }

    public void setSpRecomendaciones(String spRecomendaciones) {
        this.spRecomendaciones = spRecomendaciones;
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
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSeguimientoParasitosis[ spId=" + spId + " ]";
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
