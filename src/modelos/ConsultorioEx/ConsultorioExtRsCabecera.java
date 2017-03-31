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
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import servicios.Conexion;

public class ConsultorioExtRsCabecera implements Serializable {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private static final long serialVersionUID = 1L;
    private int rsId;
    private String rsTipoRiesgo;
    private String rsPadre;
    private String rsAfilSis;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private String usuario;
    private String estado;
    private String hc_nino;
    private String hc_madre;
    private String triaje_id;
    private String tipo_seguro;
    private Collection<ConsultorioExtRsCcd> consultorioExtRsCcdCollection;

    private Collection<ConsultorioExtRsVacunas> consultorioExtRsVacunasCollection;

    public void formatoTablaNinos(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(00);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
        //COLUMNAS OCULTAS
        TableColumn columna0 = tabla.getColumnModel().getColumn(0);
            columna0.setMaxWidth(0);
            columna0.setMinWidth(0);
            columna0.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna8 = tabla.getColumnModel().getColumn(8);
            columna8.setMaxWidth(0);
            columna8.setMinWidth(0);
            columna8.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna9 = tabla.getColumnModel().getColumn(9);
        columna9.setMaxWidth(0);
        columna9.setMinWidth(0);
        columna9.setPreferredWidth(0);
        tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaNinos(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Acto Médico","DNI","Nº H.C.","Paciente","Genero",
        "Fecha de Nacimiento","Edad","Establecimiento","Triaje"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaNinos(tabla);
    }
    
    public void listarNinos(JTable tabla,String busqueda){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Acto Médico","DNI","Nº H.C.","Paciente","Genero",
                "Fecha de Nacimiento","Edad","Establecimiento","Triaje"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[10];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_LISTAR_NINOS ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
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
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaNinos(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarNinos: " + e.getMessage());
        }
    }
    
    public void formatoTablaMadres(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        //COLUMNAS OCULTAS
        TableColumn columna5 = tabla.getColumnModel().getColumn(5);
            columna5.setMaxWidth(0);
            columna5.setMinWidth(0);
            columna5.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna6 = tabla.getColumnModel().getColumn(6);
            columna6.setMaxWidth(0);
            columna6.setMinWidth(0);
            columna6.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaMadres(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"DNI","Nº H.C.","Paciente","Teléfono","Dirección","Id","Sector"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaNinos(tabla);
    }
    
    public void listarMadres(JTable tabla,String busqueda){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"DNI","Nº H.C.","Paciente","Teléfono","Dirección","Id","Sector"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[7];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_BUSCARHC_rs ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
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
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaMadres(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarMadres: " + e.getMessage());
        }
    }
    
    public boolean mantenimientoConsultorioRsCabecera(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_RS_CABECERA ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsId());
            cmd.setString(2, getHc_nino());
            cmd.setString(3, getHc_madre());
            cmd.setString(4, getRsTipoRiesgo());
            cmd.setString(5, getRsPadre());
            cmd.setString(6, getRsAfilSis());
            cmd.setString(7, getTriaje_id());
            cmd.setString(8, getUsuario());
            cmd.setString(9, getTipo_seguro());
            cmd.setString(10, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioRsCabecera: " + ex.getMessage());
        }
        return resp;
    }
    
    public ConsultorioExtRsCabecera()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsCabecera(int rsId) {
        this.rsId = rsId;
    }

    public int getRsId() {
        return rsId;
    }

    public void setRsId(int rsId) {
        this.rsId = rsId;
    }

    public String getRsTipoRiesgo() {
        return rsTipoRiesgo;
    }

    public void setRsTipoRiesgo(String rsTipoRiesgo) {
        this.rsTipoRiesgo = rsTipoRiesgo;
    }

    public String getRsPadre() {
        return rsPadre;
    }

    public void setRsPadre(String rsPadre) {
        this.rsPadre = rsPadre;
    }

    public String getRsAfilSis() {
        return rsAfilSis;
    }

    public void setRsAfilSis(String rsAfilSis) {
        this.rsAfilSis = rsAfilSis;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<ConsultorioExtRsCcd> getConsultorioExtRsCcdCollection() {
        return consultorioExtRsCcdCollection;
    }

    public void setConsultorioExtRsCcdCollection(Collection<ConsultorioExtRsCcd> consultorioExtRsCcdCollection) {
        this.consultorioExtRsCcdCollection = consultorioExtRsCcdCollection;
    }

    @XmlTransient
    public Collection<ConsultorioExtRsVacunas> getConsultorioExtRsVacunasCollection() {
        return consultorioExtRsVacunasCollection;
    }

    public void setConsultorioExtRsVacunasCollection(Collection<ConsultorioExtRsVacunas> consultorioExtRsVacunasCollection) {
        this.consultorioExtRsVacunasCollection = consultorioExtRsVacunasCollection;
    }


    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsCabecera[ rsId=" + rsId + " ]";
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
     * @return the hc_nino
     */
    public String getHc_nino() {
        return hc_nino;
    }

    /**
     * @param hc_nino the hc_nino to set
     */
    public void setHc_nino(String hc_nino) {
        this.hc_nino = hc_nino;
    }

    /**
     * @return the hc_madre
     */
    public String getHc_madre() {
        return hc_madre;
    }

    /**
     * @param hc_madre the hc_madre to set
     */
    public void setHc_madre(String hc_madre) {
        this.hc_madre = hc_madre;
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
     * @return the tipo_seguro
     */
    public String getTipo_seguro() {
        return tipo_seguro;
    }

    /**
     * @param tipo_seguro the tipo_seguro to set
     */
    public void setTipo_seguro(String tipo_seguro) {
        this.tipo_seguro = tipo_seguro;
    }
    
}
