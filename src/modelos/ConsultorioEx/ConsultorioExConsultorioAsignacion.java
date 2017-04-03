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
public class ConsultorioExConsultorioAsignacion {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private int consultorio_id;
    private int nro_cita;
    private int cod_rol;
    private String fecha;
    private int id_Turno;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String usuario;
    
    
    public String codUsuario(String nombreUsuario){
        String cod="";
        try
        {
            String sql = "SELECT USU_CODIGO FROM USUARIO WHERE Usu_Usuario = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombreUsuario);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codUsuario: " + ex.getMessage());
        }
        return cod;
    }
    
    public String TurnosId(String tipo)
    {
        String cod="";
        try
        {
            String sql = "SELECT id_Turno \n" +
                        "FROM cONSULTORIO_EXT_TURNO\n" +
                        "WHERE HoraI = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, tipo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codDistrito: " + ex.getMessage());
        }
        return cod;
    }
    
    public int MismoMedicoMismoTurno(String nombre, String turno,String fecha){
        int resultado=0;
        try
        {
            String sql = "select * from CONSULTORIO_EXT_CONSULTORIO_ASIGNACION where COD_ROL = ? AND id_Turno  =? and FECHA =?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
            cmd.setString(2, turno);
            cmd.setString(3, fecha);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            //getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error verificacion de mas de un medico al mismo consultorio: " + ex.getMessage());
        }
        return resultado;
    }
    public int MiscoConsultorioMismoTurno(String nombre, String turno,String fecha){
        int resultado=0;
        try
        {
            String sql = "select * from CONSULTORIO_EXT_CONSULTORIO_ASIGNACION where CC_ID = ? AND id_Turno  =? and FECHA =?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
            cmd.setString(2, turno);
            cmd.setString(3, fecha);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            //getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error verificacion de mas de un medico al mismo consultorio: " + ex.getMessage());
        }
        return resultado;
    }
    
    public boolean mantenimientoConsultorioExConsultorioAsignacion(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_CONSULTORIO_ASIGNACION_MANTENIMIENTO ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setInt(2, getConsultorio_id());
            cmd.setInt(3, getNro_cita());
            cmd.setInt(4, getCod_rol());
            cmd.setString(5, getFecha());
            cmd.setInt(6, getId_Turno());
            cmd.setString(7, getUsuario());
            cmd.setString(8, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExConsultorioAsignacion: " + ex.getMessage());
        }
        return resp;
    }
    public void ListarMedicos(String nhc,JTable tabla,String se,String ar,String tipo){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Médico","Día","Mes","Año","Turno","Area","Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CONSULTORIO_EXT_LISTA_MEDICO_TURNO ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            cmd.setString(2, se);
            cmd.setString(3, ar);
            cmd.setString(4, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // nhc
                fila[3]=r.getString(4); //
                fila[4]=r.getString(5); // 
                fila[5]=r.getString(6); // id
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // id

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaTriajeReporte(tabla);
        } catch (Exception e) {
            System.out.println("Error: LISTAR MEDICOS DE TURNO: " + e.getMessage());
        }
    }
   public void formatoTablaTriajeReporte(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(345);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(200);
        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
       public void listarDetalle(JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Dia","Hora Inicio","Hora Termino","Consultorio","Nº de Citas","Turno","Médico","","","",""};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[12];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_LISTAR_CONSULASIGNA";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
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
     
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDetalle(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarConsultorios: " + e.getMessage());
        }
    }
   public void listarConsultorios(String busqueda, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Nº Consultorio","Descripción"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[3];
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
   
   public void formatoTablaConsultorio(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(330);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void formatoTablaDetalle(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(500);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(10).setMinWidth(0);
        tabla.getColumnModel().getColumn(10).setMaxWidth(0);
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getColumnModel().getColumn(11).setMaxWidth(0);
   

//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(45);
    }
    public ConsultorioExConsultorioAsignacion()
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
     * @return the consultorio_id
     */
    public int getConsultorio_id() {
        return consultorio_id;
    }

    /**
     * @param consultorio_id the consultorio_id to set
     */
    public void setConsultorio_id(int consultorio_id) {
        this.consultorio_id = consultorio_id;
    }

    /**
     * @return the nro_cita
     */
    public int getNro_cita() {
        return nro_cita;
    }

    /**
     * @param nro_cita the nro_cita to set
     */
    public void setNro_cita(int nro_cita) {
        this.nro_cita = nro_cita;
    }

    /**
     * @return the turno
     */

    /**
     * @return the cod_rol
     */
    public int getCod_rol() {
        return cod_rol;
    }

    /**
     * @param cod_rol the cod_rol to set
     */
    public void setCod_rol(int cod_rol) {
        this.cod_rol = cod_rol;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_Turno() {
        return id_Turno;
    }

    public void setId_Turno(int id_Turno) {
        this.id_Turno = id_Turno;
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
