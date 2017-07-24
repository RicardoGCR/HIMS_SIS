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
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class ConsultorioExConsultorio {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private String numero;
    private String descripcion;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String usuario;
    private int AR_ID;

    public boolean mantenimientoConsultorioExConsultorio(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_CONSULTORIO_MANTENIMIENTO ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setString(2, getNumero());
            cmd.setString(3, getDescripcion());
            cmd.setString(4, getUsuario());
            cmd.setInt(5, getAR_ID());
            cmd.setString(6, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExConsultorio: " + ex.getMessage());
        }
        return resp;
    }
    
    public String consultorioID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 CC_ID FROM CONSULTORIO_EXT_CONSULTORIO WHERE NOM_PC = HOST_NAME() ORDER BY CC_ID DESC";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: consultorioID: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaConsultorio(JTable tabla){

        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(175);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(175);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(45);
    }
    
    public void inicializarTablaConsultorios(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Nº Consultorio","Descripción","Área",""};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaConsultorio(tabla);
    }
    
    public void listarConsultorios(String busqueda, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Nº Consultorio","Descripción","Área",""};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[5];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_CONSULTORIO_LISTAR ?";
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
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaConsultorio(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarConsultorios: " + e.getMessage());
        }
    }
    
    public void formatoTablaListarConsultorioCabecera(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
//        COLUMNAS OCULTAS
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaListarConsultorioCabecera(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"Fecha","Acto Médico","Área"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaListarConsultorioCabecera(tabla);
    }
    
    public void listarConsultorioCabecera(String idHc, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"","Fecha","Acto Médico","Área"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[4];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC [CONSULTORIO_EXT_LISTAR_CONSULTORIO_CABECERA] ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, idHc);
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
            formatoTablaListarConsultorioCabecera(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarConsultorioCabecera: " + e.getMessage());
        }
    }
    
    public void listarEmergencia(String idHc, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"","Fecha","Acto Médico","Área"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[4];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC [CONSULTORIO_EXT_LISTAR_HISTORIAL_CAJA_PREVENTA_EMERGENCIA] ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, idHc);
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
            formatoTablaListarConsultorioCabecera(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarEmergencia: " + e.getMessage());
        }
    }
    
    public void listarHospitalizacion(String idHc, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"","Fecha","Acto Médico","Área"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[4];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC [CONSULTORIO_EXT_LISTAR_HISTORIAL_CAJA_PREVENTA_HOSPITALIZACION] ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, idHc);
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
            formatoTablaListarConsultorioCabecera(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarHospitalizacion: " + e.getMessage());
        }
    }
    
    public ConsultorioExConsultorio()
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
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public int getAR_ID() {
        return AR_ID;
    }

    public void setAR_ID(int AR_ID) {
        this.AR_ID = AR_ID;
    }
    
}
