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
import static modelos.hospitalizacion.HospitalizacionPapeletas.m;
import servicios.Conexion;
import vista.hospitalizacion.FrmHospitalizacionExClinico;
import static vista.hospitalizacion.FrmHospitalizacionExClinico.txtActoMedico;

/**
 *
 * @author PC02
 */
public class HospitalizacionExamenClinico {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int hec_id;
    private int id_preventa;
    private String hec_ex_gen;
    private String hec_piel_tej;
    private String hec_cab;
    private String hec_oj;
    private String hec_dientes;
    private String hec_torax;
    private String hec_mam;
    private String hec_pulmon;
    private String hec_cora;
    private String hec_linf;
    private String hec_genit;
    private String hec_tacto_rec;
    private String hec_ex_gine;
    private String hec_extrem;
    private String hec_sis_neuro;
    private String cod_usu;
    private String cod_per;
    private int diag_prin;
    
    public int idHospitalizacionExamenClinico(){
        int id = 0;
        try {
            String consulta = "EXEC HOSPITALIZACION_EXAMEN_CLINICO_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getInt(1);
        }
        }catch(Exception ex){
            System.out.println("Error: idHospitalizacionExamenClinico: " + ex.getMessage());
        }
        return id;
    }
    
    public boolean mantenimientoHospitalizacionExClinico(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_EXAMEN_CLINICO_MANTENIMIENTO ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHec_id());
            cmd.setInt(2, getId_preventa());
            cmd.setString(3, getHec_ex_gen());
            cmd.setString(4, getHec_piel_tej());
            cmd.setString(5, getHec_cab());
            cmd.setString(6, getHec_oj());
            cmd.setString(7, getHec_dientes());
            cmd.setString(8, getHec_torax());
            cmd.setString(9, getHec_mam());
            cmd.setString(10, getHec_pulmon());
            cmd.setString(11, getHec_cora());
            cmd.setString(12, getHec_linf());
            cmd.setString(13, getHec_genit());
            cmd.setString(14, getHec_tacto_rec());
            cmd.setString(15, getHec_ex_gine());
            cmd.setString(16, getHec_extrem());
            cmd.setString(17, getHec_sis_neuro());
            cmd.setString(18, getCod_per());
            cmd.setInt(19, getDiag_prin());
            cmd.setString(20, getCod_usu());
            cmd.setString(21, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionExClinico: " + ex.getMessage());
        }
        return resp;
    }
    
    public void datosTriaje(int id_preventa){
        String consulta="";
        try {
            consulta="EXEC HOSPITALIZACION_EXAMEN_CLINICO_DATOS_TRIAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, id_preventa);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                FrmHospitalizacionExClinico.txtIDTriaje.setText(r.getString(1)); 
                FrmHospitalizacionExClinico.txtPA.setText(r.getString(3)); 
                FrmHospitalizacionExClinico.txtFC.setText(r.getString(4)); 
                FrmHospitalizacionExClinico.txtFR.setText(r.getString(5)); 
                FrmHospitalizacionExClinico.txtTemp.setText(r.getString(6)); 
                FrmHospitalizacionExClinico.txtPeso.setText(r.getString(7)); 
                FrmHospitalizacionExClinico.txtTalla.setText(r.getString(13)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: datosTriaje: " + e.getMessage());
        }
    }
    
    public void formatoTablaDiagPresuntivo(JTable tabla){
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
        String titulos[]={"ID CIE 10","Código CIE 10","Diagnósticos Presuntivos"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaDiagPresuntivo(tabla);
    }
    
    public void listarDiagPresun(String topico_id, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID CIE 10","Código CIE 10","Diagnósticos Presuntivos"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[3];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_EXAMEN_CLINICO_L_DIAG_PRESUN ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, topico_id);
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
            formatoTablaDiagPresuntivo(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDiagPresun: " + e.getMessage());
        }
    }
    
    public void formatoTablaExClinico(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);//cama
        tabla.getColumnModel().getColumn(8).setPreferredWidth(250);//medico
        tabla.getColumnModel().getColumn(9).setPreferredWidth(80);//codigo medico
        tabla.getColumnModel().getColumn(10).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(14).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(15).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(16).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(17).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(18).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(19).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(20).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(21).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(22).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(23).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(24).setPreferredWidth(300);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaExClinico(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Preventa","Acto Médico","DNI","Nº H.C.","Paciente",
                "Edad","Cama","Médico","Códido del Médico","Examen General","Piel,Tejido,celular subcutáneo",
                "Cabeza,Cuello","Ojos,oídos,nariz,garganta,boca","Dientes","Tórax","Mamas","Pulmones","Corazón",
                "Linfáticos","Genitales","Tacto rectal","Examen Ginecológico","Extremidades","Sistema Neurológico"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaExClinico(tabla);
    }
    
    public void listarExClinico(String busqueda, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Preventa","Acto Médico","DNI","Nº H.C.","Paciente",
                "Edad","Cama","Médico","Códido del Médico","Examen General","Piel,Tejido,celular subcutáneo",
                "Cabeza,Cuello","Ojos,oídos,nariz,garganta,boca","Dientes","Tórax","Mamas","Pulmones","Corazón",
                "Linfáticos","Genitales","Tacto rectal","Examen Ginecológico","Extremidades","Sistema Neurológico"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[25];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_EXAMEN_CLINICO_LISTAR ?";
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
                    fila[24]=r.getString(25); 
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaExClinico(tabla);
            //inicializarTablaExClinico(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarExClinico: " + e.getMessage());
        }
    }
    
    public void formatoTablaDiagDefinitivo(JTable tabla){
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
    
    public void listarDiagDefinitivo(String topico_id, JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID CIE 10","Código CIE 10","Diagnósticos Definitivos"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[3];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_EXAMEN_CLINICO_L_DIAG_DEFI ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, topico_id);
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
            formatoTablaDiagDefinitivo(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDiagDefinitivo: " + e.getMessage());
        }
    }
    
    public void formatoListarMedicos(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(345);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(150);
        tabla.setRowHeight(30);
    }
    
    public void listarMedicos(String busqueda, JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Médico","Día","Mes","Año","Turno",};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC HOSPITALIZACION_LISTA_MEDICO_TURNO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // nhc
                fila[3]=r.getString(4); //
                fila[4]=r.getString(5); // 
                fila[5]=r.getString(6); // id
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoListarMedicos(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarFormatoHC: " + e.getMessage());
        }
    }
    
    public void formatoTablaDiagnosticos(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(350);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void listarDiagnosticos(String id, String tipo, JTable tabla,String modo){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID CIE 10","Código CIE 10","Diagnósticos"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[3];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_EXAMEN_CLINICO_LISTAR_DIAGNOSTICOS ?,?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, id);
                cmd.setString(2, tipo);
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
            if(modo.equals("V"))
               formatoTablaDiagnosticos(tabla);
            else{
                tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(550);
                tabla.setRowHeight(30);
            }
        } catch (Exception e) {
            System.out.println("Error: listarDiagnosticos: " + e.getMessage());
        }
    }
    
    public void formatoTablaDiagnosticosEpicrisis(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(350);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void listarDiagnosticosEpicrisis(String id,JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID CIE 10","Código CIE 10","Diagnósticos"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[3];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC HOSPITALIZACION_DATOS_EXAMEN_CLINICO ?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, id);
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
            formatoTablaDiagnosticosEpicrisis(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDiagnosticosEpicrisis: " + e.getMessage());
        }
    }
    
    public HospitalizacionExamenClinico()
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
     * @return the hec_id
     */
    public int getHec_id() {
        return hec_id;
    }

    /**
     * @param hec_id the hec_id to set
     */
    public void setHec_id(int hec_id) {
        this.hec_id = hec_id;
    }

    /**
     * @return the id_preventa
     */
    public int getId_preventa() {
        return id_preventa;
    }

    /**
     * @param id_preventa the id_preventa to set
     */
    public void setId_preventa(int id_preventa) {
        this.id_preventa = id_preventa;
    }

    /**
     * @return the hec_ex_gen
     */
    public String getHec_ex_gen() {
        return hec_ex_gen;
    }

    /**
     * @param hec_ex_gen the hec_ex_gen to set
     */
    public void setHec_ex_gen(String hec_ex_gen) {
        this.hec_ex_gen = hec_ex_gen;
    }

    /**
     * @return the hec_piel_tej
     */
    public String getHec_piel_tej() {
        return hec_piel_tej;
    }

    /**
     * @param hec_piel_tej the hec_piel_tej to set
     */
    public void setHec_piel_tej(String hec_piel_tej) {
        this.hec_piel_tej = hec_piel_tej;
    }

    /**
     * @return the hec_cab
     */
    public String getHec_cab() {
        return hec_cab;
    }

    /**
     * @param hec_cab the hec_cab to set
     */
    public void setHec_cab(String hec_cab) {
        this.hec_cab = hec_cab;
    }

    /**
     * @return the hec_oj
     */
    public String getHec_oj() {
        return hec_oj;
    }

    /**
     * @param hec_oj the hec_oj to set
     */
    public void setHec_oj(String hec_oj) {
        this.hec_oj = hec_oj;
    }

    /**
     * @return the hec_dientes
     */
    public String getHec_dientes() {
        return hec_dientes;
    }

    /**
     * @param hec_dientes the hec_dientes to set
     */
    public void setHec_dientes(String hec_dientes) {
        this.hec_dientes = hec_dientes;
    }

    /**
     * @return the hec_torax
     */
    public String getHec_torax() {
        return hec_torax;
    }

    /**
     * @param hec_torax the hec_torax to set
     */
    public void setHec_torax(String hec_torax) {
        this.hec_torax = hec_torax;
    }

    /**
     * @return the hec_mam
     */
    public String getHec_mam() {
        return hec_mam;
    }

    /**
     * @param hec_mam the hec_mam to set
     */
    public void setHec_mam(String hec_mam) {
        this.hec_mam = hec_mam;
    }

    /**
     * @return the hec_pulmon
     */
    public String getHec_pulmon() {
        return hec_pulmon;
    }

    /**
     * @param hec_pulmon the hec_pulmon to set
     */
    public void setHec_pulmon(String hec_pulmon) {
        this.hec_pulmon = hec_pulmon;
    }

    /**
     * @return the hec_cora
     */
    public String getHec_cora() {
        return hec_cora;
    }

    /**
     * @param hec_cora the hec_cora to set
     */
    public void setHec_cora(String hec_cora) {
        this.hec_cora = hec_cora;
    }

    /**
     * @return the hec_linf
     */
    public String getHec_linf() {
        return hec_linf;
    }

    /**
     * @param hec_linf the hec_linf to set
     */
    public void setHec_linf(String hec_linf) {
        this.hec_linf = hec_linf;
    }

    /**
     * @return the hec_genit
     */
    public String getHec_genit() {
        return hec_genit;
    }

    /**
     * @param hec_genit the hec_genit to set
     */
    public void setHec_genit(String hec_genit) {
        this.hec_genit = hec_genit;
    }

    /**
     * @return the hec_tacto_rec
     */
    public String getHec_tacto_rec() {
        return hec_tacto_rec;
    }

    /**
     * @param hec_tacto_rec the hec_tacto_rec to set
     */
    public void setHec_tacto_rec(String hec_tacto_rec) {
        this.hec_tacto_rec = hec_tacto_rec;
    }

    /**
     * @return the hec_ex_gine
     */
    public String getHec_ex_gine() {
        return hec_ex_gine;
    }

    /**
     * @param hec_ex_gine the hec_ex_gine to set
     */
    public void setHec_ex_gine(String hec_ex_gine) {
        this.hec_ex_gine = hec_ex_gine;
    }

    /**
     * @return the hec_extrem
     */
    public String getHec_extrem() {
        return hec_extrem;
    }

    /**
     * @param hec_extrem the hec_extrem to set
     */
    public void setHec_extrem(String hec_extrem) {
        this.hec_extrem = hec_extrem;
    }

    /**
     * @return the hec_sis_neuro
     */
    public String getHec_sis_neuro() {
        return hec_sis_neuro;
    }

    /**
     * @param hec_sis_neuro the hec_sis_neuro to set
     */
    public void setHec_sis_neuro(String hec_sis_neuro) {
        this.hec_sis_neuro = hec_sis_neuro;
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
     * @return the diag_prin
     */
    public int getDiag_prin() {
        return diag_prin;
    }

    /**
     * @param diag_prin the diag_prin to set
     */
    public void setDiag_prin(int diag_prin) {
        this.diag_prin = diag_prin;
    }
    
}
