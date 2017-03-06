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
import vista.hospitalizacion.FrmHospitalizacionCajaPreventa;

/**
 *
 * @author PC02
 */
public class HospitalizacionPapeletas {
    static DefaultTableModel m;
    Conexion con = new Conexion();
    private static Connection cn;
    private String hp_id;
    private String id_hc;
    private int se_id;
    private int ar_id;
    private int ca_id;
    private int id_per_uni_org;
    private String hp_indicaciones;
    private int acto_medico;
    private String fecha_registro;
    private String hora_registro;
    private String fecha_pago;
    private String hora_pago;
    private String cod_jerar_forma_pago;
    private String hp_estado_venta;
    private String hp_estado;
    private String cod_usu;
    private String nom_pc;
    
    public boolean mantenimientoHospitalizacionPapeleta()
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_PAPELETAS_MANTENIMIENTO ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getHp_id());
            cmd.setString(2, getId_hc());
            cmd.setInt(3, getAr_id());
            cmd.setInt(4, getCa_id());
            cmd.setString(5, getHp_indicaciones());
            cmd.setString(6, getCod_usu());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionPapeleta: " + ex.getMessage());
        }
        return resp;
    }
    
    
    
    public void formatoTablaBuscar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//nhc
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//apellidos
        tabla.getColumnModel().getColumn(2).setPreferredWidth(60);//nombres
        tabla.getColumnModel().getColumn(3).setPreferredWidth(250);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(50);//dni
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void buscar_HC(int index, String opcion, String descripcion, JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"N°","DNI","Nº. H.C.","Datos del Paciente","Edad"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC SP_HC_METODO_BUSQUEDA ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, index);
            cmd.setString(2, descripcion);
            cmd.setString(3, opcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // dni
                fila[2]=r.getString(3); // nhc
                fila[3]=r.getString(4) + " " + r.getString(5) + " " + r.getString(6) + " " + 
                        r.getString(7) + " " + r.getString(8); // apellidos y nombres
                fila[4]=r.getString(36); // Edad
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaBuscar(tabla);
        } catch (Exception e) {
            System.out.println("Error_buscar_HC: " + e.getMessage());
        }
    }
//   PROCEDIMIENTO PARRA LISTAR LOS PENDIENTES
    public void formatoTablaListarPapeletas(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//nhc
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);//apellidos
//        COLUMNAS OCULTAS
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna1 = tabla.getColumnModel().getColumn(1);
            columna1.setMaxWidth(0);
            columna1.setMinWidth(0);
            columna1.setPreferredWidth(0);
            tabla.doLayout();
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);//ACTO MEDICO
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);//NHC
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);//PACIENTE
        tabla.getColumnModel().getColumn(5).setPreferredWidth(250);//PACIENTE
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//RIESGO
        tabla.getColumnModel().getColumn(7).setPreferredWidth(250);//MEDICO
        tabla.getColumnModel().getColumn(8).setPreferredWidth(200);//SERVICIO
        tabla.getColumnModel().getColumn(9).setPreferredWidth(130);//CODIGO AREA
        tabla.getColumnModel().getColumn(10).setPreferredWidth(150);//AREA DESC
        tabla.getColumnModel().getColumn(11).setPreferredWidth(100);//CAMA
        tabla.getColumnModel().getColumn(12).setPreferredWidth(150);//INDICACIONES
        tabla.getColumnModel().getColumn(13).setPreferredWidth(90);//ESTADO DE VENTA
        tabla.getColumnModel().getColumn(14).setPreferredWidth(80);//FECHA REGISTRO
        tabla.getColumnModel().getColumn(15).setPreferredWidth(80);//HORA REGISTRO
        tabla.getColumnModel().getColumn(16).setPreferredWidth(200);//FORMA DE PAGO
        tabla.getColumnModel().getColumn(17).setPreferredWidth(90);//FECHA PAGO
        tabla.getColumnModel().getColumn(18).setPreferredWidth(90);//HORA PAGO
        tabla.setRowHeight(30);
    }
    
    public void formatoTablaListarPapeletasF(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//nhc
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);//apellidos
//        COLUMNAS OCULTAS
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna1 = tabla.getColumnModel().getColumn(1);
            columna1.setMaxWidth(0);
            columna1.setMinWidth(0);
            columna1.setPreferredWidth(0);
            tabla.doLayout();
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);//ACTO MEDICO
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);//NHC
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);//NHC
        tabla.getColumnModel().getColumn(5).setPreferredWidth(250);//PACIENTE
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//RIESGO
        tabla.getColumnModel().getColumn(7).setPreferredWidth(250);//MEDICO
        tabla.getColumnModel().getColumn(8).setPreferredWidth(200);//SERVICIO
        tabla.getColumnModel().getColumn(9).setPreferredWidth(130);//CODIGO AREA
        tabla.getColumnModel().getColumn(10).setPreferredWidth(150);//AREA DESC
        tabla.setRowHeight(30);
    }
    
    public void listarPapeleta(String tipo, String busqueda, JTable tabla, String frm){
    String consulta="";
        try {
            if(frm.equals("C")){
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID CP","ID HC","Acto Médico","N° H.C.","DNI","Datos del Paciente","Riesgo","Médico",
                "Servicio","Código de Área","Área","Cama","Indicaciones","Estado","Fecha de registro","Hora de registro",
                "Forma de pago","Fecha de pago","Hora de pago"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[19];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CAJA_PREVENTA_LISTAR_HOSPITALIZACION ?,?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
                cmd.setString(2, tipo);
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); // id
                    fila[1]=r.getString(2); // dni
                    fila[2]=r.getString(3); // nhc
                    fila[3]=r.getString(4); // apellidos y nombres
                    fila[4]=r.getString(5); // Edad
                    fila[5]=r.getString(6); // id
                    fila[6]=r.getString(7); // dni
                    fila[7]=r.getString(8); // nhc
                    fila[8]=r.getString(9); // apellidos y nombres
                    fila[9]=r.getString(10); // Edad
                    fila[10]=r.getString(11); // id
                    fila[11]=r.getString(12); // dni
                    fila[12]=r.getString(13); // nhc
                    fila[13]=r.getString(14); // apellidos y nombres
                    fila[14]=r.getString(15); // Edad
                    fila[15]=r.getString(16); // apellidos y nombres
                    fila[16]=r.getString(17); // Edad
                    fila[17]=r.getString(18); // Edad
                    fila[18]=r.getString(19); // Edad
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
                formatoTablaListarPapeletas(tabla);
            } else {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID CP","ID HC","Acto Médico","N° H.C.","DNI","Datos del Paciente","Riesgo",
                    "Cama","Indicaciones","Fecha de ingreso","Hora de ingreso"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[11];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CAJA_PREVENTA_LISTAR_HOSPITALIZACION ?,?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
                cmd.setString(2, tipo);
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); // id
                    fila[1]=r.getString(2); // dni
                    fila[2]=r.getString(3); // nhc
                    fila[3]=r.getString(4); // apellidos y nombres
                    fila[4]=r.getString(5); // Edad
                    fila[5]=r.getString(6); // id
                    fila[6]=r.getString(7); // cama
                    fila[7]=r.getString(12); // indicaciones
                    fila[8]=r.getString(13); // fecha ingreso
                    fila[9]=r.getString(18); // hora ingreso
                    fila[10]=r.getString(19); // hora ingreso
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
                formatoTablaListarPapeletasF(tabla);
            }
        } catch (Exception e) {
            System.out.println("Error_buscar_HC: " + e.getMessage());
        }
    }
    
    public String idHospitalizacionPapeleta(){
        String id = "";
        try {
            String consulta = "EXEC HOSPITALIZACION_PAPELETA_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error: idHospitalizacionPapeleta: " + ex.getMessage());
        }
        return id;
    }
    
    public void mostrarDatosPaciente(String nhc){
        String consulta="";
        try {
            consulta="EXEC SP_ADMISION_HISTORIACLINICA_BUSXNHC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, nhc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                FrmHospitalizacionCajaPreventa.lblIDHC.setText(r.getString(18)); 
                FrmHospitalizacionCajaPreventa.txtDni.setText(r.getString(1)); 
                FrmHospitalizacionCajaPreventa.txtPaciente.setText(r.getString(2) + " " + r.getString(3) + " " + 
                                 r.getString(4) + " " + r.getString(5) + " " +
                                 r.getString(6)); 
                FrmHospitalizacionCajaPreventa.txtEdad.setText(r.getString(19)); 
                FrmHospitalizacionCajaPreventa.lblEstado.setText(r.getString(17)); 
                FrmHospitalizacionCajaPreventa.lblGenero.setText(r.getString(8)); 
                
            }
            //
        } catch (Exception e) {
            System.out.println("mostrarDatosPaciente: " + e.getMessage());
        }
    }
    
    public void formatoTablaHospitalizacionArticulosListar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//id tabla HABITACION
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);//nro piso
        tabla.setRowHeight(30);
    }
    
    public void hospitalizacionArticulosListar(JTable tabla,String articulo){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Artículo","Descripción"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC HOSPITALIZACION_ARTICULOS_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, articulo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(3);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaHospitalizacionArticulosListar(tabla);
        } catch (Exception e) {
            System.out.println("Error: hospitalizacionArticulosListar: " + e.getMessage());
        }
    }
    
    public String codServicio(int num_piso)
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
    
    public String codArea(String area)
    {
        String cod="";
        try
        {
            String sql = "select AR_ID from SISTEMA_AREAS where ar_desc = ? \n" +
                        "and se_id not in(01)";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, area);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: codArea: " + ex.getMessage());
        }
        return cod;
    }
    
    public String codCama(String genero, String servicio,String cama)
    {
        String cod="";
        try
        {
            String sql = "SELECT HC.CA_ID\n" +
                        "FROM HOSPITALIZACION_CAMAS HC, HOSPITALIZACION_HABITACION HB, SISTEMA_SERVICIO SE\n" +
                        "WHERE HC.CA_ESTADO_USO = 'A' -- CAMAS LIBRES\n" +
                        "AND HB.HAB_ID = HC.HAB_ID\n" +
                        "AND SE.SE_ID = HB.SE_ID -- SERVICIO\n" +
                        "AND HC.CA_GEN = ?\n" +
                        "AND SE.SE_DESC = ?\n" +
                        "AND HC.CA_DESC = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, genero);
            cmd.setString(2, servicio);
            cmd.setString(3, cama);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: codArea: " + ex.getMessage());
        }
        return cod;
    }
    
    public HospitalizacionPapeletas()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    /**
     * @return the cn
     */
    public static Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the hp_id
     */
    public String getHp_id() {
        return hp_id;
    }

    /**
     * @param hp_id the hp_id to set
     */
    public void setHp_id(String hp_id) {
        this.hp_id = hp_id;
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
     * @return the id_per_uni_org
     */
    public int getId_per_uni_org() {
        return id_per_uni_org;
    }

    /**
     * @param id_per_uni_org the id_per_uni_org to set
     */
    public void setId_per_uni_org(int id_per_uni_org) {
        this.id_per_uni_org = id_per_uni_org;
    }

    /**
     * @return the hp_indicaciones
     */
    public String getHp_indicaciones() {
        return hp_indicaciones;
    }

    /**
     * @param hp_indicaciones the hp_indicaciones to set
     */
    public void setHp_indicaciones(String hp_indicaciones) {
        this.hp_indicaciones = hp_indicaciones;
    }

    /**
     * @return the acto_medico
     */
    public int getActo_medico() {
        return acto_medico;
    }

    /**
     * @param acto_medico the acto_medico to set
     */
    public void setActo_medico(int acto_medico) {
        this.acto_medico = acto_medico;
    }

    /**
     * @return the fecha_registro
     */
    public String getFecha_registro() {
        return fecha_registro;
    }

    /**
     * @param fecha_registro the fecha_registro to set
     */
    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    /**
     * @return the hora_registro
     */
    public String getHora_registro() {
        return hora_registro;
    }

    /**
     * @param hora_registro the hora_registro to set
     */
    public void setHora_registro(String hora_registro) {
        this.hora_registro = hora_registro;
    }

    /**
     * @return the fecha_pago
     */
    public String getFecha_pago() {
        return fecha_pago;
    }

    /**
     * @param fecha_pago the fecha_pago to set
     */
    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    /**
     * @return the hora_pago
     */
    public String getHora_pago() {
        return hora_pago;
    }

    /**
     * @param hora_pago the hora_pago to set
     */
    public void setHora_pago(String hora_pago) {
        this.hora_pago = hora_pago;
    }

    /**
     * @return the cod_jerar_forma_pago
     */
    public String getCod_jerar_forma_pago() {
        return cod_jerar_forma_pago;
    }

    /**
     * @param cod_jerar_forma_pago the cod_jerar_forma_pago to set
     */
    public void setCod_jerar_forma_pago(String cod_jerar_forma_pago) {
        this.cod_jerar_forma_pago = cod_jerar_forma_pago;
    }

    /**
     * @return the hp_estado_venta
     */
    public String getHp_estado_venta() {
        return hp_estado_venta;
    }

    /**
     * @param hp_estado_venta the hp_estado_venta to set
     */
    public void setHp_estado_venta(String hp_estado_venta) {
        this.hp_estado_venta = hp_estado_venta;
    }

    /**
     * @return the hp_estado
     */
    public String getHp_estado() {
        return hp_estado;
    }

    /**
     * @param hp_estado the hp_estado to set
     */
    public void setHp_estado(String hp_estado) {
        this.hp_estado = hp_estado;
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
}
