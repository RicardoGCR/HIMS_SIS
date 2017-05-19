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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Entity
@Table(name = "HOSPITALIZACION_EPICRISIS_DIAGNOSTICOS_EGRESO")
@NamedQueries({
    @NamedQuery(name = "HospitalizacionEpicrisisDiagnosticosEgreso.findAll", query = "SELECT h FROM HospitalizacionEpicrisisDiagnosticosEgreso h")})
public class HospitalizacionEpicrisisDiagnosticosEgreso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HHD_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int hhId;
    private int idCie10;
    private String codUsu;
    private int hhdId;
    @Column(name = "FECHA_ACTU")
    private String fechaActu;
    @Column(name = "HORA_ACTU")
    private String horaActu;
    @Column(name = "NOM_PC")
    private String nomPc;
    @Column(name = "HC_ESTADO")
    private Character hcEstado;
    @JoinColumn(name = "HH_ID", referencedColumnName = "HH_ID")
    @ManyToOne
    private HospitalizacionEpicrisis hospitalizacionEpicrisis;

    public boolean mantenimientoHospitalizacionEpicrisisDiagnosticosEgreso(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_MANTENIMIENTO_EPICRISIS_DIAGNOSTICOS_EGRESO ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHhId());
            cmd.setInt(2, getIdCie10());
            cmd.setString(3, getCodUsu());
            cmd.setString(4, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionEpicrisisDiagnosticosEgreso: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoDiagnosticosEgreso(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void listarDiagnosticosEgreso(String id, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Código CIE 10","Diagnóstico"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[3];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_LISTAR_EPICRISIS_DIAGNOSTICOS_EGRESO ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, id);
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); 
                    fila[1]=r.getString(2); 
                    fila[2]=r.getString(3); 
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoDiagnosticosEgreso(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDiagnosticosEgreso: " + e.getMessage());
        }
    }
    
    public HospitalizacionEpicrisisDiagnosticosEgreso() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public HospitalizacionEpicrisisDiagnosticosEgreso(int hhdId) {
        this.hhdId = hhdId;
    }

    public int getHhdId() {
        return hhdId;
    }

    public void setHhdId(int hhdId) {
        this.hhdId = hhdId;
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

    public Character getHcEstado() {
        return hcEstado;
    }

    public void setHcEstado(Character hcEstado) {
        this.hcEstado = hcEstado;
    }

    public HospitalizacionEpicrisis getHospitalizacionEpicrisis() {
        return hospitalizacionEpicrisis;
    }

    public void setHospitalizacionEpicrisis(HospitalizacionEpicrisis hospitalizacionEpicrisis) {
        this.hospitalizacionEpicrisis = hospitalizacionEpicrisis;
    }
    
    @Override
    public String toString() {
        return "modelos.hospitalizacion.HospitalizacionEpicrisisDiagnosticosEgreso[ hhdId=" + hhdId + " ]";
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
     * @return the hhId
     */
    public int getHhId() {
        return hhId;
    }

    /**
     * @param hhId the hhId to set
     */
    public void setHhId(int hhId) {
        this.hhId = hhId;
    }

    /**
     * @return the idCie10
     */
    public int getIdCie10() {
        return idCie10;
    }

    /**
     * @param idCie10 the idCie10 to set
     */
    public void setIdCie10(int idCie10) {
        this.idCie10 = idCie10;
    }

    /**
     * @return the codUsu
     */
    public String getCodUsu() {
        return codUsu;
    }

    /**
     * @param codUsu the codUsu to set
     */
    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }
    
}
