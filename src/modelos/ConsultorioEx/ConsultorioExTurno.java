/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

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
public class ConsultorioExTurno {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private String nombre;
    private String horai;
    private String horaf;
    
    public boolean mantenimientoConsultorioExTurno(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_TURNO_MANTENIMIENTO ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setString(2, getNombre());
            cmd.setString(3, getHorai());
            cmd.setString(4, getHoraf());
            cmd.setString(5, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExTurno: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoTablaTurnos(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaTurnos(JTable tabla){
        tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Turno","Hora de Inicio","Hora de Término"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaTurnos(tabla);
    }
    
    public void listarTurnos(JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Turno","Hora de Inicio","Hora de Término"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[4];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_TURNO_LISTAR";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); 
                    fila[1]=r.getString(2); 
                    fila[2]=r.getString(3); 
                    fila[3]=r.getString(4); 
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaTurnos(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarTurnos: " + e.getMessage());
        }
    }
    
    public ConsultorioExTurno()
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the horai
     */
    public String getHorai() {
        return horai;
    }

    /**
     * @param horai the horai to set
     */
    public void setHorai(String horai) {
        this.horai = horai;
    }

    /**
     * @return the horaf
     */
    public String getHoraf() {
        return horaf;
    }

    /**
     * @param horaf the horaf to set
     */
    public void setHoraf(String horaf) {
        this.horaf = horaf;
    }
}
