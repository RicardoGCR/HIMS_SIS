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
public class HospitalizacionHabitaciones {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int hab_id;
    private int piso_id;
    private String hab_nom;
    private String hab_fecha_actu;
    private String hab_hora_actu;
    private String hab_nom_pc;
    private String hab_estado;
    private String cod_usu;
    private int se_id;
    
    public boolean mantenimientoHospitalizacionHabitacion(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_HABITACION_MANTENIMIENTO ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHab_id());
            cmd.setInt(2, getPiso_id());
            cmd.setString(3, getHab_nom());
            cmd.setInt(4, getSe_id());
            cmd.setString(5, getCod_usu());
            cmd.setString(6, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionHabitacion: " + ex.getMessage());
        }
        return resp;
    }
    
    public String codPiso(int num_piso)
    {
        String cod="";
        try
        {
            String sql = "SELECT PISO_ID\n" +
                        "FROM HOSPITALIZACION_PISOS\n" +
                        "WHERE PISO_NUMERO = ?\n";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, num_piso);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: codPiso: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaHospitalizacionHabListar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//id tabla HABITACION
        tabla.getColumnModel().getColumn(1).setPreferredWidth(40);//nro piso
        tabla.getColumnModel().getColumn(2).setPreferredWidth(180);//nombre piso
        tabla.getColumnModel().getColumn(3).setPreferredWidth(160);//nombre HABITACION
        tabla.getColumnModel().getColumn(4).setPreferredWidth(160);//servicio desc
        tabla.getColumnModel().getColumn(5).setPreferredWidth(120);//codigo servicio
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void hospitalizacionHabListar(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Piso","Piso","Nombre de piso","Habitación","Servicio","Código de servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC HOSPITALIZACION_HABITACION_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, getHab_nom());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaHospitalizacionHabListar(tabla);
        } catch (Exception e) {
            System.out.println("Error: hospitalizacionPisosListar: " + e.getMessage());
        }
    }
    
    public HospitalizacionHabitaciones()
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
     * @return the piso_id
     */
    public int getPiso_id() {
        return piso_id;
    }

    /**
     * @param piso_id the piso_id to set
     */
    public void setPiso_id(int piso_id) {
        this.piso_id = piso_id;
    }

    /**
     * @return the hab_nom
     */
    public String getHab_nom() {
        return hab_nom;
    }

    /**
     * @param hab_nom the hab_nom to set
     */
    public void setHab_nom(String hab_nom) {
        this.hab_nom = hab_nom;
    }

    /**
     * @return the hab_fecha_actu
     */
    public String getHab_fecha_actu() {
        return hab_fecha_actu;
    }

    /**
     * @param hab_fecha_actu the hab_fecha_actu to set
     */
    public void setHab_fecha_actu(String hab_fecha_actu) {
        this.hab_fecha_actu = hab_fecha_actu;
    }

    /**
     * @return the hab_hora_actu
     */
    public String getHab_hora_actu() {
        return hab_hora_actu;
    }

    /**
     * @param hab_hora_actu the hab_hora_actu to set
     */
    public void setHab_hora_actu(String hab_hora_actu) {
        this.hab_hora_actu = hab_hora_actu;
    }

    /**
     * @return the hab_nom_pc
     */
    public String getHab_nom_pc() {
        return hab_nom_pc;
    }

    /**
     * @param hab_nom_pc the hab_nom_pc to set
     */
    public void setHab_nom_pc(String hab_nom_pc) {
        this.hab_nom_pc = hab_nom_pc;
    }

    /**
     * @return the hab_estado
     */
    public String getHab_estado() {
        return hab_estado;
    }

    /**
     * @param hab_estado the hab_estado to set
     */
    public void setHab_estado(String hab_estado) {
        this.hab_estado = hab_estado;
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
     * @return the se_id
     */
    public int getSe_id() {
        return se_id;
    }

    /**
     * @param se_id the se_id to set
     */
    public void setSe_id(int se_id) {
        this.se_id = se_id;
    }
}
