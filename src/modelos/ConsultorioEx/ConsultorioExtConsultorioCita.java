/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.ConsultorioEx.ConsultorioExtProxima;

/**
 *
 * @author MYS1
 */
public class ConsultorioExtConsultorioCita implements Serializable {
    
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int idCita;
    private int idConsultorioEx;
    private int id_Preventa;
    private String fecha;
    private String numero;
    private String turno;
    private String medico;
    private String fechaActu;
    private String horaActu;
    private String estado;
    private String nomPc;
    private String codUsu;
    private String id_hc;
    private String cod_nomen;
    private String CITA_NRO ;
    private String TURNO_CITA;
    private String MEDICO_CITA;
    
    public boolean mantenimientoConsultorioExtCita(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_CITA ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdCita());
            cmd.setInt(2, getIdConsultorioEx());
            cmd.setString(3, getFecha());
            cmd.setString(4, getNumero());
            cmd.setString(5, getTurno());
            cmd.setString(6, getMedico());
            cmd.setInt(7, getId_Preventa());
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
            System.out.println("Error: mantenimientoConsultorio CITA: " + ex.getMessage());
        }
        return resp;
    }
    
    public void ListarTurnos(JTable tabla,String fecha){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Fecha","Consultoria","Atenciones Disponibles","Turno","Horario","Medico","CC_ID","COD_ROL","ID_TURNO","FECHA","AR_ID"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[12];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_LISTAR_CONSULTORIO_CITA ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, fecha);
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
            formatoTablaDiagnostico(tabla);
        } catch (Exception e) {
            System.out.println("Error: LISTAR TURNOS: " + e.getMessage());
        }
    }
    
        public void formatoTablaDiagnostico(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(150);

        
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(10).setMinWidth(0);
        tabla.getColumnModel().getColumn(10).setMaxWidth(0);
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getColumnModel().getColumn(11).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
//        COLUMNAS OCULTAS
        TableColumn columna0 = tabla.getColumnModel().getColumn(0);
            columna0.setMaxWidth(0);
            columna0.setMinWidth(0);
            columna0.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna7 = tabla.getColumnModel().getColumn(7);
            columna7.setMaxWidth(0);
            columna7.setMinWidth(0);
            columna7.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
        
        
        
      public void ListarNumeros(String fecha,String turno){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_CITA_cantidad ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, fecha);
            cmd.setString(2, turno);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
  
                ConsultorioExtProxima.lblNumeros.setText(r.getString(2)); 
    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR NUMEROS  " + e.getMessage());
        }
    }
      
      
    public boolean mantenimientoConsultorioExtPREVENTA(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_CITA_PREVENTA_CITAS ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_Preventa());
            cmd.setString(2, getId_hc());
            cmd.setString(3, getCod_nomen());
            
            cmd.setString(4, getCITA_NRO());
            cmd.setString(5, getTURNO_CITA());
            cmd.setString(6, getMEDICO_CITA());
            
            cmd.setString(7, getCodUsu());
            cmd.setString(8, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorio  CITA PREVENTA: " + ex.getMessage());
        }
        return resp;
    }  
    
     public void ConsultoriosExtPREVENTAListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_CITA_ULTIMO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                ConsultorioExtProxima.lblIDPREVENTA.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }
    
   

    public ConsultorioExtConsultorioCita() {
        Conexion con = new Conexion();
          cn = con.conectar();
    }

    public ConsultorioExtConsultorioCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public int getIdConsultorioEx() {
        return idConsultorioEx;
    }

    public int getId_Preventa() {
        return id_Preventa;
    }

    public void setId_Preventa(int id_Preventa) {
        this.id_Preventa = id_Preventa;
    }

    public String getId_hc() {
        return id_hc;
    }

    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    public String getCod_nomen() {
        return cod_nomen;
    }

    public void setCod_nomen(String cod_nomen) {
        this.cod_nomen = cod_nomen;
    }

    public String getCITA_NRO() {
        return CITA_NRO;
    }

    public void setCITA_NRO(String CITA_NRO) {
        this.CITA_NRO = CITA_NRO;
    }

    public String getTURNO_CITA() {
        return TURNO_CITA;
    }

    public void setTURNO_CITA(String TURNO_CITA) {
        this.TURNO_CITA = TURNO_CITA;
    }

    public String getMEDICO_CITA() {
        return MEDICO_CITA;
    }

    public void setMEDICO_CITA(String MEDICO_CITA) {
        this.MEDICO_CITA = MEDICO_CITA;
    }
    
    
    
    

    public void setIdConsultorioEx(int idConsultorioEx) {
        this.idConsultorioEx = idConsultorioEx;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    

   
    
}
