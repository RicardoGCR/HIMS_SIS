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
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class HospitalizacionCamas {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int ca_id;
    private int hab_id;
    private String ca_gen;
    private String ca_asign;
    private String ca_fecha_actu;
    private String ca_hora_actu;
    private String ca_nom_pc;
    private String ca_estado;
    private String cod_usu;
    private String ca_desc;
    
    public boolean mantenimientoHospitalizacionCamas(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_CAMAS_MANTENIMIENTO ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCa_id());
            cmd.setInt(2, getHab_id());
            cmd.setString(3, getCa_gen());
            cmd.setString(4, getCa_asign());
            cmd.setString(5, getCod_usu());
            cmd.setString(6, tipo);
            cmd.setString(7, getCa_desc());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionCamas: " + ex.getMessage());
        }
        return resp;
    }
    
    public String codHabitacion(String habitacion)
    {
        String cod="";
        try
        {
            String sql = "SELECT HH.HAB_ID\n" +
                            "FROM HOSPITALIZACION_HABITACION HH\n" +
                            "WHERE HH.HAB_NOM = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, habitacion);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: codHabitacion: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaHospitalizacionCamListar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//id tabla HABITACION
        tabla.getColumnModel().getColumn(1).setPreferredWidth(40);//asignacion
        tabla.getColumnModel().getColumn(2).setPreferredWidth(220);//nombre de habitacion
        tabla.getColumnModel().getColumn(3).setPreferredWidth(180);//servicio
        tabla.getColumnModel().getColumn(4).setPreferredWidth(40);//genero
        tabla.getColumnModel().getColumn(5).setPreferredWidth(160);//descripcion
        tabla.getColumnModel().getColumn(6).setPreferredWidth(120);//asignacion
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void hospitalizacionCamListar(JTable tabla,String descripcion){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Cama","Piso","Nombre Habitación","Servicio","Género","Descripción","Asignación"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC HOSPITALIZACION_CAMAS_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
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
            formatoTablaHospitalizacionCamListar(tabla);
        } catch (Exception e) {
            System.out.println("Error: hospitalizacionPisosListar: " + e.getMessage());
        }
    }
    
    public HospitalizacionCamas()
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
     * @return the ca_id
     */
    public int getCa_id() {
        return ca_id;
    }

    /**
     * @param ca_id the ca_id to set
     */
    public void setCa_id(int ca_id) {
        this.ca_id = ca_id;
    }

    /**
     * @return the hab_id
     */
    public int getHab_id() {
        return hab_id;
    }

    /**
     * @param hab_id the hab_id to set
     */
    public void setHab_id(int hab_id) {
        this.hab_id = hab_id;
    }

    /**
     * @return the ca_gen
     */
    public String getCa_gen() {
        return ca_gen;
    }

    /**
     * @param ca_gen the ca_gen to set
     */
    public void setCa_gen(String ca_gen) {
        this.ca_gen = ca_gen;
    }

    /**
     * @return the ca_asign
     */
    public String getCa_asign() {
        return ca_asign;
    }

    /**
     * @param ca_asign the ca_asign to set
     */
    public void setCa_asign(String ca_asign) {
        this.ca_asign = ca_asign;
    }

    /**
     * @return the ca_fecha_actu
     */
    public String getCa_fecha_actu() {
        return ca_fecha_actu;
    }

    /**
     * @param ca_fecha_actu the ca_fecha_actu to set
     */
    public void setCa_fecha_actu(String ca_fecha_actu) {
        this.ca_fecha_actu = ca_fecha_actu;
    }

    /**
     * @return the ca_hora_actu
     */
    public String getCa_hora_actu() {
        return ca_hora_actu;
    }

    /**
     * @param ca_hora_actu the ca_hora_actu to set
     */
    public void setCa_hora_actu(String ca_hora_actu) {
        this.ca_hora_actu = ca_hora_actu;
    }

    /**
     * @return the ca_nom_pc
     */
    public String getCa_nom_pc() {
        return ca_nom_pc;
    }

    /**
     * @param ca_nom_pc the ca_nom_pc to set
     */
    public void setCa_nom_pc(String ca_nom_pc) {
        this.ca_nom_pc = ca_nom_pc;
    }

    /**
     * @return the ca_estado
     */
    public String getCa_estado() {
        return ca_estado;
    }

    /**
     * @param ca_estado the ca_estado to set
     */
    public void setCa_estado(String ca_estado) {
        this.ca_estado = ca_estado;
    }

    /**
     * @return the cod_usu
     */
    public String getCod_usu() {
        return cod_usu;
    }

    /**
     * @param cod_usu the cod_usu to set
     */
    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    /**
     * @return the ca_desc
     */
    public String getCa_desc() {
        return ca_desc;
    }

    /**
     * @param ca_desc the ca_desc to set
     */
    public void setCa_desc(String ca_desc) {
        this.ca_desc = ca_desc;
    }
}
