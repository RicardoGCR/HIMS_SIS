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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V;

public class ConsultorioExtCarnetPerinatalHo implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int hoId;
    private int id_cie10;
    private String hoHosp;
    private String hoFecha;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    private int idActoMedico;

    public boolean mantenimientoConsultorioExtCarnetPerinatalFu(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_HO ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHoId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getHoHosp());
            cmd.setString(4, getHoFecha());
            cmd.setInt(5, getId_cie10());
            cmd.setString(6, getCodUsu());
            cmd.setString(7, tipo);
            cmd.setInt(8, getIdActoMedico());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalHo: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalHoID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 HO_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_HO \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY HO_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalHoID: " + ex.getMessage());
        }
        return cod;
    }   
        public void cargarDatosCie10(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nro","Código","Diagnóstico"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CIE10_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // clasificacion
                fila[1]=r.getString(2); //codigo
                fila[2]=r.getString(3); //codigo
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarCie10(tabla);
        } catch (Exception e) {
            System.out.println("Error_cargarDatosCie10: " + e.getMessage());
        }
    }
     public void formatoTablaCargarCie10(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);//CODIGO
        tabla.setRowHeight(30);
    } 
     
     public void ConsultoriosExtHoListar(String cp_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_HO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdHos.setText(r.getString(1)); 
                try { // llenar el campo fecha FUM
                  if(r.getString(4).equals("")){
                  RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.fechaf3.setDate(null);
                } else {
                    String fechaFUM = (String)(r.getString(4));
                    DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = dfo.parse(fechaFUM);
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.fechaf3.setDate(fecha);
                }
                } catch (Exception e) {
                }
                if(r.getString(3).equals("SI"))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkHsi.setText("X");
                if(r.getString(3).equals("NO"))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkHno.setText("X");
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtCIE10.setText(r.getString(5)); 
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtdes1.setText(r.getString(6));
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCie10.setText(r.getString(5));
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdCie10.setText(r.getString(7));
                
                
                    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtFuListar: " + e.getMessage());
        }
    }
     
    
    public ConsultorioExtCarnetPerinatalHo() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalHo(int hoId) {
        this.hoId = hoId;
    }

    public int getHoId() {
        return hoId;
    }

    public void setHoId(int hoId) {
        this.hoId = hoId;
    }

    public String getHoHosp() {
        return hoHosp;
    }

    public void setHoHosp(String hoHosp) {
        this.hoHosp = hoHosp;
    }

    public String getHoFecha() {
        return hoFecha;
    }

    public void setHoFecha(String hoFecha) {
        this.hoFecha = hoFecha;
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

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalHo[ hoId=" + hoId + " ]";
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
     * @return the cpId
     */
    public int getCpId() {
        return cpId;
    }

    /**
     * @param cpId the cpId to set
     */
    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    /**
     * @return the id_cie10
     */
    public int getId_cie10() {
        return id_cie10;
    }

    /**
     * @param id_cie10 the id_cie10 to set
     */
    public void setId_cie10(int id_cie10) {
        this.id_cie10 = id_cie10;
    }

    /**
     * @return the idActoMedico
     */
    public int getIdActoMedico() {
        return idActoMedico;
    }

    /**
     * @param idActoMedico the idActoMedico to set
     */
    public void setIdActoMedico(int idActoMedico) {
        this.idActoMedico = idActoMedico;
    }
    
}
