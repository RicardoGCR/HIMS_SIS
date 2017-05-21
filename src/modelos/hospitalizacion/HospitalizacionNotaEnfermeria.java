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
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.hospitalizacion.FrmHospitalizacionEpicrisis;
import vista.hospitalizacion.FrmHospitalizacionNotaEnfermeria;

public class HospitalizacionNotaEnfermeria implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int idPreventa;
    private int actoMedico;
    private int neId;
    private String nePa;
    private String neFr;
    private String neFc;
    private String neT;
    private String nePeso;
    private String neTalla;
    private String neIdm;
    private String neAnotaciones;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    private Collection<HospitalizacionNotaEnfermeriaProcedimiento> hospitalizacionNotaEnfermeriaProcedimientoCollection;

    public boolean mantenimientoHospitalizacionNotaEnfermeria(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_MANTENIMIENTO_NOTA_ENFERMERIA ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getNeId());
            cmd.setInt(2, getIdPreventa());
            cmd.setInt(3, getActoMedico());
            cmd.setString(4, getNePa());
            cmd.setString(5, getNeFr());
            cmd.setString(6, getNeFc());
            cmd.setString(7, getNeT());
            cmd.setString(8, getNePeso());
            cmd.setString(9, getNeTalla());
            cmd.setString(10, getNeIdm());
            cmd.setString(11, getNeAnotaciones());
            cmd.setString(12, getCodUsu());
            cmd.setString(13, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionNotaEnfermeria: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoTablaDiagPresuntivo(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(0);
//        COLUMNAS OCULTAS
        TableColumn columna0 = tabla.getColumnModel().getColumn(0);
            columna0.setMaxWidth(0);
            columna0.setMinWidth(0);
            columna0.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna3 = tabla.getColumnModel().getColumn(3);
            columna3.setMaxWidth(0);
            columna3.setMinWidth(0);
            columna3.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna4 = tabla.getColumnModel().getColumn(4);
            columna4.setMaxWidth(0);
            columna4.setMinWidth(0);
            columna4.setPreferredWidth(0);
            tabla.doLayout();
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
        TableColumn columna7 = tabla.getColumnModel().getColumn(7);
            columna7.setMaxWidth(0);
            columna7.setMinWidth(0);
            columna7.setPreferredWidth(0);
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
        TableColumn columna10 = tabla.getColumnModel().getColumn(10);
            columna10.setMaxWidth(0);
            columna10.setMinWidth(0);
            columna10.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna11 = tabla.getColumnModel().getColumn(11);
            columna11.setMaxWidth(0);
            columna11.setMinWidth(0);
            columna11.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTabla(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Fecha","Hora","","","","","","","","","",""};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaDiagPresuntivo(tabla);
    }
    
    public void listarProcedimientos(String preventa, JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Fecha","Hora","","","","","","","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC HOSPITALIZACION_LISTAR_NOTA_ENFERMERIA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, preventa);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // nhc
                fila[3]=r.getString(4); // id
                fila[4]=r.getString(5); // dni
                fila[5]=r.getString(6); // nhc
                fila[6]=r.getString(7); // id
                fila[7]=r.getString(8); // dni
                fila[8]=r.getString(9); // nhc
                fila[9]=r.getString(10); // id
                fila[10]=r.getString(11); // dni
                fila[11]=r.getString(12); // nhc
                fila[12]=("Ver más..."); // nhc
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDiagPresuntivo(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDiagPresun: " + e.getMessage());
        }
    }
    
//    public void epi(){
//    String consulta="";
//        try {
//            String fila[]=new String[1];
//            //int index = cbxTipoBusqueda.getSelectedIndex();
//            consulta="EXEC YAMILA ";
//            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            ResultSet r= cmd.executeQuery();
//            int c=1;
//            while(r.next()){
//              fila[0]=r.getString(1); 
//                    m.addRow(fila);
//                    c++;
//            }
//        } catch (Exception e) {
//            System.out.println("Error: notas de enfermeria para epicris : " + e.getMessage());
//        }
//    }
    
    public String notaEnfermeriaID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 NE_ID\n" +
                        "FROM HOSPITALIZACION_NOTA_ENFERMERIA \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY NE_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               FrmHospitalizacionNotaEnfermeria.lblId.setText(rs.getString(1));
            }
        }
        catch(Exception ex)
        {
            System.out.println("notaEnfermeriaID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public String notaEnfermeriaEpicrisis(String id_preventa)
    {
        String cod="";
        try
        {
            String sql = "SELECT FECHA_ACTU + ' - ' + HORA_ACTU  + CHAR(10) +  NE_ANOTACIONES + CHAR(10) + CHAR(10)  AS [text()]\n" +
"                FROM HOSPITALIZACION_NOTA_ENFERMERIA AS tt\n" +
"                WHERE ID_PREVENTA = "+id_preventa+"\n" +
"                ORDER BY FECHA_ACTU DESC ,HORA_ACTU DESC\n" +
"            FOR XML PATH('')";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
               FrmHospitalizacionEpicrisis.txtProcedTerapeuticos.setText(rs.getString(1));
               
            }
        }
        catch(Exception ex)
        {
            System.out.println("notaEnfermeriaEpicrisis: " + ex.getMessage());
        }
        return cod;
    }   
    
    public HospitalizacionNotaEnfermeria() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public HospitalizacionNotaEnfermeria(int neId) {
        this.neId = neId;
    }

    public int getNeId() {
        return neId;
    }

    public void setNeId(int neId) {
        this.neId = neId;
    }

    public String getNePa() {
        return nePa;
    }

    public void setNePa(String nePa) {
        this.nePa = nePa;
    }

    public String getNeFr() {
        return neFr;
    }

    public void setNeFr(String neFr) {
        this.neFr = neFr;
    }

    public String getNeFc() {
        return neFc;
    }

    public void setNeFc(String neFc) {
        this.neFc = neFc;
    }

    public String getNeT() {
        return neT;
    }

    public void setNeT(String neT) {
        this.neT = neT;
    }

    public String getNePeso() {
        return nePeso;
    }

    public void setNePeso(String nePeso) {
        this.nePeso = nePeso;
    }

    public String getNeTalla() {
        return neTalla;
    }

    public void setNeTalla(String neTalla) {
        this.neTalla = neTalla;
    }

    public String getNeIdm() {
        return neIdm;
    }

    public void setNeIdm(String neIdm) {
        this.neIdm = neIdm;
    }

    public String getNeAnotaciones() {
        return neAnotaciones;
    }

    public void setNeAnotaciones(String neAnotaciones) {
        this.neAnotaciones = neAnotaciones;
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

    public Collection<HospitalizacionNotaEnfermeriaProcedimiento> getHospitalizacionNotaEnfermeriaProcedimientoCollection() {
        return hospitalizacionNotaEnfermeriaProcedimientoCollection;
    }

    public void setHospitalizacionNotaEnfermeriaProcedimientoCollection(Collection<HospitalizacionNotaEnfermeriaProcedimiento> hospitalizacionNotaEnfermeriaProcedimientoCollection) {
        this.hospitalizacionNotaEnfermeriaProcedimientoCollection = hospitalizacionNotaEnfermeriaProcedimientoCollection;
    }

    @Override
    public String toString() {
        return "modelos.hospitalizacion.HospitalizacionNotaEnfermeria[ neId=" + neId + " ]";
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
     * @return the actoMedico
     */
    public int getActoMedico() {
        return actoMedico;
    }

    /**
     * @param actoMedico the actoMedico to set
     */
    public void setActoMedico(int actoMedico) {
        this.actoMedico = actoMedico;
    }
    
}
