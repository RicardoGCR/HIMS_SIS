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
public class ConsultorioExRiesgoQuirurgico {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private int ar_id;
    private String id_hc;
    private int procedencia;
    private String hta;
    private String dm;
    private String renal;
    private String alergia;
    private String qx;
    private String otros;
    private String disnea;
    private String palpit;
    private String tos;
    private String sint_otros;
    private String ex_fisico;
    private String desc;
    private String sugerencia;
    private String cod_per;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String usuario;
    private int preventa;
    private String id_triaje;
          
    public boolean mantenimientoConsultorioExRQ(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_RIESGO_QUIRURGICO_MANTENIMIENTO ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setInt(2, getAr_id());
            cmd.setInt(3, getProcedencia());
            cmd.setString(4, getHta());
            cmd.setString(5, getDm());
            cmd.setString(6, getRenal());
            cmd.setString(7, getAlergia());
            cmd.setString(8, getQx());
            cmd.setString(9, getOtros());
            cmd.setString(10, getDisnea());
            cmd.setString(11, getPalpit());
            cmd.setString(12, getTos());
            cmd.setString(13, getSint_otros());
            cmd.setString(14, getEx_fisico());
            cmd.setString(15, getDesc());
            cmd.setString(16, getSugerencia());
            cmd.setString(17, getCod_per());
            cmd.setString(18, getUsuario());
            cmd.setInt(19, getPreventa());
            cmd.setString(20, getId_triaje());
            cmd.setString(21, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExRQ: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean mantenimientoPreventa(String tipo,String id_hc)
        {
        boolean resp = false;
        try{
            String sql = "CAJA_PREVENTA_MANTENIMIENTO_CONSULTORIO_EXT ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setString(2, id_hc);
            cmd.setString(3, getUsuario());
            cmd.setString(4, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoPreventa: " + ex.getMessage());
        }
        return resp;
    }
    
    public String preventaID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 Id_Preventa\n" +
                        "FROM CAJA_PREVENTA \n" +
                        "WHERE Pc_Registro = HOST_NAME()\n" +
                        "ORDER BY Id_Preventa DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("preventaID: " + ex.getMessage());
        }
        return cod;
    }
    
    public String rqID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 RQ_ID\n" +
                        "FROM CONSULTORIO_EXT_RIESGO_QUIRURGICO \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY RQ_ID DESC";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("rqID: " + ex.getMessage());
        }
        return cod;
    }
    
    public String areaID(String servicio)
    {
        String cod="";
        try
        {
            String sql = "SELECT AR.AR_ID,ar.AR_DESC\n" +
                        "FROM SISTEMA_AREAS AR, SISTEMA_SERVICIO SS\n" +
                        "WHERE SS.SE_ID = AR.SE_ID\n" +
                        "AND AR.AR_DESC = ?\n" +
                        "AND UP_ID IN (01)";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, servicio);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: servicioID " + ex.getMessage());
        }
        return cod;
    }
    
    public String medicoID(String medico)
    {
        String cod="";
        try
        {
            String sql = "SELECT COD_PER\n" +
                        "FROM PERSONAL_PERSONAL\n" +
                        "WHERE ape_pat_per + ' ' + ape_mat_per + ' ' + "
                        + "nombres_per = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, medico);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: servicioID " + ex.getMessage());
        }
        return cod;
    }
   
    
    public void formatoTablaDiagnostico(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(550);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTabla(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID CIE 10","Código CIE 10","Diagnóstico"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaDiagnostico(tabla);
    }
    
    public void listarDiagnostico(String busqueda, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID CIE 10","Código CIE 10","Diagnósticos"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[3];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CIE10_LISTAR ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); // id
                    fila[1]=r.getString(2); // dni
                    fila[2]=r.getString(3); // nhc
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDiagnostico(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDiagnostico: " + e.getMessage());
        }
    }
    
    
    public void inicializarTablaNomenclatura(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Código CPT","Nomenclatura"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaNomenclatura(tabla);
    }
    
    public void formatoTablaNomenclatura(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(600);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void listarDiagnosticoNomenclatura(String busqueda, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Código CPT","Nomenclatura"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[3];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CAJA_NOMENCLATURA_CAJA_LISTAR ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); // id
                    fila[1]=r.getString(2); // dni
                    fila[2]=r.getString(3); // nhc
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaNomenclatura(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDiagnosticoNomenclatura: " + e.getMessage());
        }
    }
    
    public void formatoTablaRQ(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(14).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(15).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(16).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(17).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(18).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(19).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(20).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(21).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(22).setPreferredWidth(100);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablRQ(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Acto Médico","DNI","Nº H.C.","Paciente","Edad","Ocupación","Area",
                "HTA","DM","ENF RENAL","ALERGIA A MEDICAMENTOS","Qx Anteriores","Otros","DISNEA",
                "PALPITACIONES","TOS","Otros sintomas","Examen fisico preferencial","RQ","Sugerencia",
                "Médico","Fecha"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaRQ(tabla);
    }
    
    public void listarRQ(String busqueda, String fechaInicio, String fechaFin,JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Acto Médico","DNI","Nº H.C.","Paciente","Edad","Ocupación","Area",
                "HTA","DM","ENF RENAL","ALERGIA A MEDICAMENTOS","Qx Anteriores","Otros","DISNEA",
                "PALPITACIONES","TOS","Otros sintomas","Examen fisico preferencial","RQ","Sugerencia",
                "Médico","Fecha","Preventa"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[24];
                consulta="EXEC CONSULTORIO_EXT_RIESGO_QUIRURGICO_LISTAR ?,?,?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
                cmd.setString(2, fechaInicio);
                cmd.setString(3, fechaFin);
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
                    fila[12]=r.getString(13); 
                    fila[13]=r.getString(14); 
                    fila[14]=r.getString(15); 
                    fila[15]=r.getString(16); 
                    fila[16]=r.getString(17); 
                    fila[17]=r.getString(18); 
                    fila[18]=r.getString(19); 
                    fila[19]=r.getString(20); 
                    fila[20]=r.getString(21); 
                    fila[21]=r.getString(22); 
                    fila[22]=r.getString(23); 
                    fila[23]=r.getString(24); 
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaRQ(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarConsultorios: " + e.getMessage());
        }
    }
    
    public ConsultorioExRiesgoQuirurgico()
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
     * @return the ar_id
     */
    public int getAr_id() {
        return ar_id;
    }

    /**
     * @param ar_id the ar_id to set
     */
    public void setAr_id(int ar_id) {
        this.ar_id = ar_id;
    }

    /**
     * @return the id_hc
     */
    public String getId_hc() {
        return id_hc;
    }

    /**
     * @param id_hc the id_hc to set
     */
    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    /**
     * @return the procedencia
     */
    public int getProcedencia() {
        return procedencia;
    }

    /**
     * @param procedencia the procedencia to set
     */
    public void setProcedencia(int procedencia) {
        this.procedencia = procedencia;
    }

    /**
     * @return the hta
     */
    public String getHta() {
        return hta;
    }

    /**
     * @param hta the hta to set
     */
    public void setHta(String hta) {
        this.hta = hta;
    }

    /**
     * @return the dm
     */
    public String getDm() {
        return dm;
    }

    /**
     * @param dm the hm to set
     */
    public void setDm(String dm) {
        this.dm = dm;
    }

    /**
     * @return the renal
     */
    public String getRenal() {
        return renal;
    }

    /**
     * @param renal the renal to set
     */
    public void setRenal(String renal) {
        this.renal = renal;
    }

    /**
     * @return the alergia
     */
    public String getAlergia() {
        return alergia;
    }

    /**
     * @param alergia the alergia to set
     */
    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    /**
     * @return the qx
     */
    public String getQx() {
        return qx;
    }

    /**
     * @param qx the qx to set
     */
    public void setQx(String qx) {
        this.qx = qx;
    }

    /**
     * @return the otros
     */
    public String getOtros() {
        return otros;
    }

    /**
     * @param otros the otros to set
     */
    public void setOtros(String otros) {
        this.otros = otros;
    }

    /**
     * @return the disnea
     */
    public String getDisnea() {
        return disnea;
    }

    /**
     * @param disnea the disnea to set
     */
    public void setDisnea(String disnea) {
        this.disnea = disnea;
    }

    /**
     * @return the palpit
     */
    public String getPalpit() {
        return palpit;
    }

    /**
     * @param palpit the palpit to set
     */
    public void setPalpit(String palpit) {
        this.palpit = palpit;
    }

    /**
     * @return the tos
     */
    public String getTos() {
        return tos;
    }

    /**
     * @param tos the tos to set
     */
    public void setTos(String tos) {
        this.tos = tos;
    }

    /**
     * @return the sint_otros
     */
    public String getSint_otros() {
        return sint_otros;
    }

    /**
     * @param sint_otros the sint_otros to set
     */
    public void setSint_otros(String sint_otros) {
        this.sint_otros = sint_otros;
    }

    /**
     * @return the ex_fisico
     */
    public String getEx_fisico() {
        return ex_fisico;
    }

    /**
     * @param ex_fisico the ex_fisico to set
     */
    public void setEx_fisico(String ex_fisico) {
        this.ex_fisico = ex_fisico;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the sugerencia
     */
    public String getSugerencia() {
        return sugerencia;
    }

    /**
     * @param sugerencia the sugerencia to set
     */
    public void setSugerencia(String sugerencia) {
        this.sugerencia = sugerencia;
    }

    /**
     * @return the cod_per
     */
    public String getCod_per() {
        return cod_per;
    }

    /**
     * @param cod_per the cod_per to set
     */
    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
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
     * @return the id_triaje
     */
    public String getId_triaje() {
        return id_triaje;
    }

    /**
     * @param id_triaje the id_triaje to set
     */
    public void setId_triaje(String id_triaje) {
        this.id_triaje = id_triaje;
    }
}
