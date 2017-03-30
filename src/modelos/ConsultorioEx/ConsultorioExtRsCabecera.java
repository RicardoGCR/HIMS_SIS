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
    private Long rsId;
    private String rsTipoRiesgo;
    private String rsPadre;
    private String rsAfilSis;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private String usuario;
    private Character estado;
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
    
    public ConsultorioExtRsCabecera()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsCabecera(Long rsId) {
        this.rsId = rsId;
    }

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
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

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
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
    public int hashCode() {
        int hash = 0;
        hash += (rsId != null ? rsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsCabecera)) {
            return false;
        }
        ConsultorioExtRsCabecera other = (ConsultorioExtRsCabecera) object;
        if ((this.rsId == null && other.rsId != null) || (this.rsId != null && !this.rsId.equals(other.rsId))) {
            return false;
        }
        return true;
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
    
}
