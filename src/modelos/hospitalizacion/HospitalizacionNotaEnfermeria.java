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
public class HospitalizacionNotaEnfermeria {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private String nota_enfermeria;
    private String indicaciones;
    private int preventa;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String usuario;
    private String fecha_not;
    private String hora_not;

    public boolean mantenimientoHospitalizacionNotEnfermeria(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_NOT_ENFERMERIA_MANTENIMIENTO ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setString(2, getNota_enfermeria());
            cmd.setString(3, getIndicaciones());
            cmd.setInt(4, getPreventa());
            cmd.setString(5, getUsuario());
            cmd.setString(6, getFecha_not());
            cmd.setString(7, getHora_not());
            cmd.setString(8, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionNotEnfermeria: " + ex.getMessage());
        }
        return resp;
    }
    
    public String hospitalizacionNotaEnfID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 HN_ID FROM HOSPITALIZACION_NOT_ENFERMERIA WHERE NOM_PC = HOST_NAME() ORDER BY HN_ID DESC";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("hospitalizacionEvolucionID: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaNotaEnf(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(527);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(525);
        tabla.setRowHeight(30);
    }
    
    public void inicializarTabla(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Fecha","Hora","Nota de Enfermería","Indicaciones"};
        m=new DefaultTableModel(null,titulos);
        JTable p=new JTable(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaNotaEnf(tabla);
    }
    
    public void listarNotaEnf(String preventa, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Fecha","Hora","Nota de Enfermería","Indicaciones"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[5];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_NOT_ENFERMERIA_LISTAR ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, preventa);
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); // id
                    fila[1]=r.getString(2); // id
                    fila[2]=r.getString(3); // dni
                    fila[3]=r.getString(4); // nhc
                    fila[4]=r.getString(5); // nhc
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaNotaEnf(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarNotaEnf: " + e.getMessage());
        }
    }
    
    public HospitalizacionNotaEnfermeria()
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
     * @return the nota_enfermeria
     */
    public String getNota_enfermeria() {
        return nota_enfermeria;
    }

    /**
     * @param nota_enfermeria the nota_enfermeria to set
     */
    public void setNota_enfermeria(String nota_enfermeria) {
        this.nota_enfermeria = nota_enfermeria;
    }

    /**
     * @return the indicaciones
     */
    public String getIndicaciones() {
        return indicaciones;
    }

    /**
     * @param indicaciones the indicaciones to set
     */
    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    /**
     * @return the preventa
     */
    public int getPreventa() {
        return preventa;
    }

    /**
     * @param preventa the preventa to set
     */
    public void setPreventa(int preventa) {
        this.preventa = preventa;
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

    /**
     * @return the fecha_not
     */
    public String getFecha_not() {
        return fecha_not;
    }

    /**
     * @param fecha_not the fecha_not to set
     */
    public void setFecha_not(String fecha_not) {
        this.fecha_not = fecha_not;
    }

    /**
     * @return the hora_not
     */
    public String getHora_not() {
        return hora_not;
    }

    /**
     * @param hora_not the hora_not to set
     */
    public void setHora_not(String hora_not) {
        this.hora_not = hora_not;
    }
}
