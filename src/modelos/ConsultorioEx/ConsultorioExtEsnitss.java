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

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;

/**
 *
 * @author PC02
 */

public class ConsultorioExtEsnitss implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int esId;
    private String idHc;

    private String esCodigoPac;

    private String esTenofovir;

    private String esZidobudina;

    private String esAbacavir;

    private String esLamivudina;

    private String esEfavirenz;

    private String esNevirapina;

    private String esRitonavir;

    private String esAtazanavir;

    private String esRateigravir;
  
    private String esDuobir;

    private String esDuovirN;

    private String esKelatra;

    private String esCotrimoxazol;

    private String esInh;

    private String fechaActu;

    private String horaActu;

    private String nomPc;

    private Character estado;

    private String codUsu;

    public boolean mantenimientoConsultorioExtEsnitss(String tipo,String triaje)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_ESNITSS ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getEsId());
            cmd.setString(2, getIdHc());
            cmd.setString(3, getEsCodigoPac());
            cmd.setString(4, getEsTenofovir());
            cmd.setString(5, getEsZidobudina());
            cmd.setString(6, getEsAbacavir());
            cmd.setString(7, getEsLamivudina());
            cmd.setString(8, getEsEfavirenz());
            cmd.setString(9, getEsNevirapina());
            cmd.setString(10, getEsRitonavir());
            cmd.setString(11, getEsAtazanavir());
            cmd.setString(12, getEsRateigravir());
            cmd.setString(13, getEsDuobir());
            cmd.setString(14, getEsDuovirN());
            cmd.setString(15, getEsKelatra());
            cmd.setString(16, getEsCotrimoxazol());
            cmd.setString(17, getEsInh());
            cmd.setString(18, getCodUsu());
            cmd.setString(19, tipo);
            cmd.setString(20, triaje);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtEsnitss: " + ex.getMessage());
        }
        return resp;
    }
    
    public String esnitssID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 ES_ID\n" +
                        "FROM CONSULTORIO_EXT_ESNITSS \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY ES_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("esnitssID: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaConsultorioExListarC(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);//
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);//
        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);//
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(14).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(15).setPreferredWidth(0);
        tabla.setRowHeight(30);
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna13 = tabla.getColumnModel().getColumn(13);
            columna13.setMaxWidth(0);
            columna13.setMinWidth(0);
            columna13.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna14 = tabla.getColumnModel().getColumn(14);
            columna14.setMaxWidth(0);
            columna14.setMinWidth(0);
            columna14.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna15 = tabla.getColumnModel().getColumn(15);
            columna15.setMaxWidth(0);
            columna15.setMinWidth(0);
            columna15.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(45);
    }
    
    public void consultorioExListarC(String busqueda,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Acto Médico","DNI","Nº H.C.",
                "Paciente","Edad","Ocupación","FC","FR",
                "PA","Peso","T°","Talla","HC","Código","Codigo2","ID_HC","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[19];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CONSULTORIO_EXT_TRIAJE_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
//            cmd.setString(2, "");
//            cmd.setString(3, "");
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                fila[8]=r.getString(9);
                fila[9]=r.getString(10); // 
                fila[10]=r.getString(11); // 
                fila[11]=r.getString(12); // 
                fila[12]=r.getString(13); // 
                fila[13]=r.getString(14); // 
                fila[14]=r.getString(15); // 
                fila[15]=r.getString(16); // 
                fila[16]=r.getString(17); // 
                fila[17]=r.getString(18); // 
                fila[18]=r.getString(19); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaConsultorioExListarC(tabla);
        } catch (Exception e) {
            System.out.println("Error: consultorioExListarC: " + e.getMessage());
        }
    }
    
    public void formatoTablaConsultorioExtEsnitss(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);//
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);//DNI
        tabla.getColumnModel().getColumn(4).setPreferredWidth(90);//
        tabla.getColumnModel().getColumn(5).setPreferredWidth(90);//
        tabla.getColumnModel().getColumn(6).setPreferredWidth(90);//
        tabla.getColumnModel().getColumn(7).setPreferredWidth(90);//
        tabla.getColumnModel().getColumn(8).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(14).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(15).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(16).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(17).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(18).setPreferredWidth(90);
        tabla.setRowHeight(30);
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void consultorioExtEsnitssListar(String busqueda,String tipo,String fechaI,String fechaF,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Código del Paciente","Nº H.C.","DNI",
                "Tenofovir","Zidobudina","Abacavir","Lamivudina","Efavirenz",
                "Nevirapina","Ritonavir","Atazanavir","Rateigravir","Duovir",
                "Duovir-N","Kelatra","Cotrimoxazol","INH","Fecha"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[19];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CONSULTORIO_EXT_ESNITSS_LISTAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
            cmd.setString(2, tipo);
            cmd.setString(3, fechaI);
            cmd.setString(4, fechaF);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                fila[8]=r.getString(9);
                fila[9]=r.getString(10); // 
                fila[10]=r.getString(11); // 
                fila[11]=r.getString(12); // 
                fila[12]=r.getString(13); // 
                fila[13]=r.getString(14); // 
                fila[14]=r.getString(15); // 
                fila[15]=r.getString(16); // 
                fila[16]=r.getString(17); // 
                fila[17]=r.getString(18); // 
                fila[18]=r.getString(19); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaConsultorioExtEsnitss(tabla);
        } catch (Exception e) {
            System.out.println("Error: consultorioExtEsnitssListar: " + e.getMessage());
        }
    }
    
    public ConsultorioExtEsnitss() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtEsnitss(int esId) {
        this.esId = esId;
    }

    public int getEsId() {
        return esId;
    }

    public void setEsId(int esId) {
        this.esId = esId;
    }

    public String getEsCodigoPac() {
        return esCodigoPac;
    }

    public void setEsCodigoPac(String esCodigoPac) {
        this.esCodigoPac = esCodigoPac;
    }

    public String getEsTenofovir() {
        return esTenofovir;
    }

    public void setEsTenofovir(String esTenofovir) {
        this.esTenofovir = esTenofovir;
    }

    public String getEsZidobudina() {
        return esZidobudina;
    }

    public void setEsZidobudina(String esZidobudina) {
        this.esZidobudina = esZidobudina;
    }

    public String getEsAbacavir() {
        return esAbacavir;
    }

    public void setEsAbacavir(String esAbacavir) {
        this.esAbacavir = esAbacavir;
    }

    public String getEsLamivudina() {
        return esLamivudina;
    }

    public void setEsLamivudina(String esLamivudina) {
        this.esLamivudina = esLamivudina;
    }

    public String getEsEfavirenz() {
        return esEfavirenz;
    }

    public void setEsEfavirenz(String esEfavirenz) {
        this.esEfavirenz = esEfavirenz;
    }

    public String getEsNevirapina() {
        return esNevirapina;
    }

    public void setEsNevirapina(String esNevirapina) {
        this.esNevirapina = esNevirapina;
    }

    public String getEsRitonavir() {
        return esRitonavir;
    }

    public void setEsRitonavir(String esRitonavir) {
        this.esRitonavir = esRitonavir;
    }

    public String getEsAtazanavir() {
        return esAtazanavir;
    }

    public void setEsAtazanavir(String esAtazanavir) {
        this.esAtazanavir = esAtazanavir;
    }

    public String getEsRateigravir() {
        return esRateigravir;
    }

    public void setEsRateigravir(String esRateigravir) {
        this.esRateigravir = esRateigravir;
    }

    public String getEsDuobir() {
        return esDuobir;
    }

    public void setEsDuobir(String esDuobir) {
        this.esDuobir = esDuobir;
    }

    public String getEsDuovirN() {
        return esDuovirN;
    }

    public void setEsDuovirN(String esDuovirN) {
        this.esDuovirN = esDuovirN;
    }

    public String getEsKelatra() {
        return esKelatra;
    }

    public void setEsKelatra(String esKelatra) {
        this.esKelatra = esKelatra;
    }

    public String getEsCotrimoxazol() {
        return esCotrimoxazol;
    }

    public void setEsCotrimoxazol(String esCotrimoxazol) {
        this.esCotrimoxazol = esCotrimoxazol;
    }

    public String getEsInh() {
        return esInh;
    }

    public void setEsInh(String esInh) {
        this.esInh = esInh;
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
        return "modelos.ConsultorioEx.ConsultorioExtEsnitss[ esId=" + esId + " ]";
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
     * @return the idHc
     */
    public String getIdHc() {
        return idHc;
    }

    /**
     * @param idHc the idHc to set
     */
    public void setIdHc(String idHc) {
        this.idHc = idHc;
    }
    
}
