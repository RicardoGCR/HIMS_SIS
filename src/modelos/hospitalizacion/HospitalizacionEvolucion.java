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
public class HospitalizacionEvolucion {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int he_id;
    private String evolucion;
    private String indicaciones;
    private int preventa;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String usuario;
    private String estado;
    private String fecha_ev;
    private String hora_ev;
    
    public boolean mantenimientoHospitalizacionEvolucion(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_EVOLUCION_MANTENIMIENTO ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHe_id());
            cmd.setString(2, getEvolucion());
            cmd.setString(3, getIndicaciones());
            cmd.setInt(4, getPreventa());
            cmd.setString(5, getUsuario());
            cmd.setString(6, getFecha_ev());
            cmd.setString(7, getHora_ev());
            cmd.setString(8, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionEvolucion: " + ex.getMessage());
        }
        return resp;
    }
    
    public String hospitalizacionEvolucionID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 HE_ID FROM HOSPITALIZACION_EVOLUCION WHERE NOM_PC = HOST_NAME() ORDER BY HE_ID DESC";
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
    
    public void formatoTablaEvolucion(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(527);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(525);
        tabla.setRowHeight(30);
    }
    
    public void inicializarTabla(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Fecha","Hora","Indicaciones","Evolución Clínica"};
        m=new DefaultTableModel(null,titulos);
        JTable p=new JTable(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaEvolucion(tabla);
    }
    
    public void listarEvolucion(String preventa, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Fecha","Hora","Indicaciones","Evolución Clínica"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[5];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_EVOLUCION_LISTAR ?";
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
            formatoTablaEvolucion(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarEvolucion: " + e.getMessage());
        }
    }
    
    public HospitalizacionEvolucion()
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
     * @return the he_id
     */
    public int getHe_id() {
        return he_id;
    }

    /**
     * @param he_id the he_id to set
     */
    public void setHe_id(int he_id) {
        this.he_id = he_id;
    }

    /**
     * @return the evolucion
     */
    public String getEvolucion() {
        return evolucion;
    }

    /**
     * @param evolucion the evolucion to set
     */
    public void setEvolucion(String evolucion) {
        this.evolucion = evolucion;
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
     * @return the fecha_ev
     */
    public String getFecha_ev() {
        return fecha_ev;
    }

    /**
     * @param fecha_ev the fecha_ev to set
     */
    public void setFecha_ev(String fecha_ev) {
        this.fecha_ev = fecha_ev;
    }

    /**
     * @return the hora_ev
     */
    public String getHora_ev() {
        return hora_ev;
    }

    /**
     * @param hora_ev the hora_ev to set
     */
    public void setHora_ev(String hora_ev) {
        this.hora_ev = hora_ev;
    }
    
}
