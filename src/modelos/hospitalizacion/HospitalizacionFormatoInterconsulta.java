/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.hospitalizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class HospitalizacionFormatoInterconsulta {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private int id_hec;
    private int ar_id;
    private String resumen_hc;
    private String motivo;
    private String cod_per;
    private int id_preventa;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String usuario;
    
    public boolean mantenimientoHospitalizacionFormInterconsulta(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_FORMATO_INTERCONSULTA_MANTENIMIENTO ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setInt(2, getId_hec());
            cmd.setInt(3, getAr_id());
            cmd.setString(4, getResumen_hc());
            cmd.setString(5, getMotivo());
            cmd.setString(6, getCod_per());
            cmd.setInt(7, getId_preventa());
            cmd.setString(8, getUsuario());
            cmd.setString(9, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionFormInterconsulta: " + ex.getMessage());
        }
        return resp;
    }
    
    public String hospitalizacionInterconsultaID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 HFI_ID FROM HOSPITALIZACION_FORMATO_INTERCONSULTA \n" +
"	WHERE NOM_PC = HOST_NAME() ORDER BY HFI_ID DESC";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("hospitalizacionInterconsultaID: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaDiag(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(450);
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaDiag(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID CIE 10","Código CIE 10","Diagnósticos"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaDiag(tabla);
    }
    
    public void formatoTablaDiagInterconsultaP(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(120);
        tabla.setRowHeight(30);
    }
    
    public void listarFormatoInterconsultaP(String busqueda, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Preventa","Acto Médico","DNI","Nº H.C.","Paciente",
                                   "Edad","Médico","Código","Servicio","Area"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[11];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_FORMATO_INTERCONSULTA_LISTAR ?";
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
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDiagInterconsultaP(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarFormatoInterconsulta: " + e.getMessage());
        }
    }
    
    public String codServicio(String servicio)
    {
        String cod="";
        try
        {
            String sql = "SELECT SE_ID \n" +
            "FROM SISTEMA_SERVICIO\n" +
            "WHERE SE_DESC = ? \n" +
            "AND UP_ID = 01";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, servicio);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: codServicio: " + ex.getMessage());
        }
        return cod;
    }
    
    public String codArea(String area)
    {
        String cod="";
        try
        {
            String sql = "SELECT AR_ID\n" +
                        "FROM SISTEMA_AREAS, SISTEMA_SERVICIO\n" +
                        "WHERE AR_DESC = ?\n" +
                        "AND SISTEMA_AREAS.SE_ID = SISTEMA_SERVICIO.SE_ID\n" +
                        "AND SISTEMA_SERVICIO.UP_ID = 01";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, area);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: codArea: " + ex.getMessage());
        }
        return cod;
    }
    
    public HospitalizacionFormatoInterconsulta()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_hec
     */
    public int getId_hec() {
        return id_hec;
    }

    /**
     * @param id_hec the id_hec to set
     */
    public void setId_hec(int id_hec) {
        this.id_hec = id_hec;
    }

    /**
     * @return the ar_id
     */
    public int getAr_id() {
        return ar_id;
    }

    /**
     * @param ar_id the ar_id to set
     */
    public void setAr_id(int ar_id) {
        this.ar_id = ar_id;
    }

    /**
     * @return the resumen_hc
     */
    public String getResumen_hc() {
        return resumen_hc;
    }

    /**
     * @param resumen_hc the resumen_hc to set
     */
    public void setResumen_hc(String resumen_hc) {
        this.resumen_hc = resumen_hc;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the cod_per
     */
    public String getCod_per() {
        return cod_per;
    }

    /**
     * @param cod_per the cod_per to set
     */
    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    /**
     * @return the id_preventa
     */
    public int getId_preventa() {
        return id_preventa;
    }

    /**
     * @param id_preventa the id_preventa to set
     */
    public void setId_preventa(int id_preventa) {
        this.id_preventa = id_preventa;
    }

    /**
     * @return the fecha_actu
     */
    public String getFecha_actu() {
        return fecha_actu;
    }

    /**
     * @param fecha_actu the fecha_actu to set
     */
    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    /**
     * @return the hora_actu
     */
    public String getHora_actu() {
        return hora_actu;
    }

    /**
     * @param hora_actu the hora_actu to set
     */
    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    /**
     * @return the nom_pc
     */
    public String getNom_pc() {
        return nom_pc;
    }

    /**
     * @param nom_pc the nom_pc to set
     */
    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
