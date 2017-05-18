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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.hospitalizacion.FrmHospitalizacionEpicrisis;
import vista.hospitalizacion.FrmHospitalizacionNotaEnfermeria;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "HOSPITALIZACION_EPICRISIS")
@NamedQueries({
    @NamedQuery(name = "HospitalizacionEpicrisis.findAll", query = "SELECT h FROM HospitalizacionEpicrisis h")})
public class HospitalizacionEpicrisis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HH_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int idPreventa;
    private String codUsu;
    private int hhId;
    @Column(name = "HH_COMPLICACIONES")
    private String hhComplicaciones;
    @Column(name = "HH_TIPO_ALTA")
    private String hhTipoAlta;
    @Column(name = "HH_CONDICION_EGRESO")
    private String hhCondicionEgreso;
    @Column(name = "HH_PRONOSTICO_ALTA")
    private String hhPronosticoAlta;
    @Column(name = "HH_INF_MORTALIDAD")
    private String hhInfMortalidad;
    @Column(name = "FECHA_ACTU")
    private String fechaActu;
    @Column(name = "HORA_ACTU")
    private String horaActu;
    @Column(name = "NOM_PC")
    private String nomPc;
    @Column(name = "HC_ESTADO")
    private Character hcEstado;
    @OneToMany(mappedBy = "hospitalizacionEpicrisis")
    private Collection<HospitalizacionEpicrisisDiagnosticosEgreso> hospitalizacionEpicrisisDiagnosticosEgresoCollection;

    public boolean mantenimientoHospitalizacionEpicrisis(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_MANTENIMIENTO_EPICRISIS ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHhId());
            cmd.setInt(2, getIdPreventa());
            cmd.setString(3, getHhComplicaciones());
            cmd.setString(4, getHhTipoAlta());
            cmd.setString(5, getHhCondicionEgreso());
            cmd.setString(6, getHhPronosticoAlta());
            cmd.setString(7, getHhInfMortalidad());
            cmd.setString(8, getCodUsu());
            cmd.setString(9, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionEpicrisis: " + ex.getMessage());
        }
        return resp;
    }
    
    public String diasHospitalizados(String idPreventa){
        String cod="";
        try
        {
            String sql = "HOSPITALIZACION_EPICRISIS_DIAS_HOSPITALIZADOS ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idPreventa);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1) + " DIAS";
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error diasHospitalizados: " + ex.getMessage());
        }
        return cod;
    }
    
    public String epicrisisID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 HH_ID\n" +
                        "FROM HOSPITALIZACION_EPICRISIS \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY HH_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               FrmHospitalizacionEpicrisis.lblId.setText(rs.getString(1));
            }
        }
        catch(Exception ex)
        {
            System.out.println("epicrisisID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public void formatoTablaEpicrisis(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);//cama
        tabla.getColumnModel().getColumn(8).setPreferredWidth(70);//medico
        tabla.getColumnModel().getColumn(9).setPreferredWidth(80);//codigo medico
        tabla.getColumnModel().getColumn(10).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(14).setPreferredWidth(7);
//        COLUMNAS OCULTAS
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna1 = tabla.getColumnModel().getColumn(1);
            columna1.setMaxWidth(0);
            columna1.setMinWidth(0);
            columna1.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna11 = tabla.getColumnModel().getColumn(11);
            columna11.setMaxWidth(0);
            columna11.setMinWidth(0);
            columna11.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna14 = tabla.getColumnModel().getColumn(14);
            columna14.setMaxWidth(0);
            columna14.setMinWidth(0);
            columna14.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaExClinico(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","ID","Acto Médico","DNI","Nº H.C.","Paciente",
                "Cama","Estado","Forma de Pago","Fecha de Ingreso",
                "Hora de ingreso","ID","Fecha de Egreso","Hora de Egreso","SE"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaEpicrisis(tabla);
    }
    
    public void listarEpicrisis(String busqueda, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","ID","Acto Médico","DNI","Nº H.C.","Paciente",
                "Cama","Estado","Forma de Pago","Fecha de Ingreso",
                "Hora de ingreso","ID","Fecha de Egreso","Hora de Egreso","SE"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[15];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_LISTAR_EPICRISIS_DEL_DIA ?";
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
            formatoTablaEpicrisis(tabla);
            //inicializarTablaExClinico(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarExClinico: " + e.getMessage());
        }
    }
    
    public void hospitalizacionListarEpicrisis(String id){
        String consulta="";
        try {
            consulta="HOSPITALIZACION_LISTAR_EPICRISIS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                FrmHospitalizacionEpicrisis.txtComplicaciones.setText(r.getString(1)); 
                FrmHospitalizacionEpicrisis.txtEstadia.setText(r.getString(2)); 
                if(r.getString(3).equalsIgnoreCase("TERAPEUTICA"))
                    FrmHospitalizacionEpicrisis.cbxTipoAlta.setSelectedIndex(1);
                else if(r.getString(3).equalsIgnoreCase("COMUN"))
                    FrmHospitalizacionEpicrisis.cbxTipoAlta.setSelectedIndex(2);
                else if(r.getString(3).equalsIgnoreCase("VOLUNTARIA"))
                    FrmHospitalizacionEpicrisis.cbxTipoAlta.setSelectedIndex(3);
                else if(r.getString(3).equalsIgnoreCase("DECESO"))
                    FrmHospitalizacionEpicrisis.cbxTipoAlta.setSelectedIndex(4);
                FrmHospitalizacionEpicrisis.txtCondicionEgreso.setText(r.getString(4));
                FrmHospitalizacionEpicrisis.txtPronosticoAlta.setText(r.getString(5));
                FrmHospitalizacionEpicrisis.txtInfo.setText(r.getString(6));

            }
            //
        } catch (Exception e) {
            System.out.println("Error: hospitalizacionListarEpicrisis  " + e.getMessage());
        }
    }
    
    public HospitalizacionEpicrisis() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public HospitalizacionEpicrisis(int hhId) {
        this.hhId = hhId;
    }

    public int getHhId() {
        return hhId;
    }

    public void setHhId(int hhId) {
        this.hhId = hhId;
    }

    public String getHhComplicaciones() {
        return hhComplicaciones;
    }

    public void setHhComplicaciones(String hhComplicaciones) {
        this.hhComplicaciones = hhComplicaciones;
    }

    public String getHhTipoAlta() {
        return hhTipoAlta;
    }

    public void setHhTipoAlta(String hhTipoAlta) {
        this.hhTipoAlta = hhTipoAlta;
    }

    public String getHhCondicionEgreso() {
        return hhCondicionEgreso;
    }

    public void setHhCondicionEgreso(String hhCondicionEgreso) {
        this.hhCondicionEgreso = hhCondicionEgreso;
    }

    public String getHhPronosticoAlta() {
        return hhPronosticoAlta;
    }

    public void setHhPronosticoAlta(String hhPronosticoAlta) {
        this.hhPronosticoAlta = hhPronosticoAlta;
    }

    public String getHhInfMortalidad() {
        return hhInfMortalidad;
    }

    public void setHhInfMortalidad(String hhInfMortalidad) {
        this.hhInfMortalidad = hhInfMortalidad;
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

    public Collection<HospitalizacionEpicrisisDiagnosticosEgreso> getHospitalizacionEpicrisisDiagnosticosEgresoCollection() {
        return hospitalizacionEpicrisisDiagnosticosEgresoCollection;
    }

    public void setHospitalizacionEpicrisisDiagnosticosEgresoCollection(Collection<HospitalizacionEpicrisisDiagnosticosEgreso> hospitalizacionEpicrisisDiagnosticosEgresoCollection) {
        this.hospitalizacionEpicrisisDiagnosticosEgresoCollection = hospitalizacionEpicrisisDiagnosticosEgresoCollection;
    }

    @Override
    public String toString() {
        return "modelos.hospitalizacion.HospitalizacionEpicrisis[ hhId=" + hhId + " ]";
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
     * @return the idPreventa
     */
    public int getIdPreventa() {
        return idPreventa;
    }

    /**
     * @param idPreventa the idPreventa to set
     */
    public void setIdPreventa(int idPreventa) {
        this.idPreventa = idPreventa;
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
