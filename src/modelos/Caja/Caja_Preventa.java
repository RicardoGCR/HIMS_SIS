/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Caja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static modelos.hospitalizacion.HospitalizacionPapeletas.getCn;
import servicios.Conexion;
import vista.admisionEmergencia.FrmFormatoEmergencia;
import vista.hospitalizacion.FrmHospitalizacionCajaPreventa;

/**
 *
 * @author PC02
 */
public class Caja_Preventa {
    DefaultTableModel m;
    private Connection cn;
    private int id_preventa;
    private int Id_Detalle_p;
    private String id_hc;
    private int CA_ID;
    private int id_per_uni_org;
    private String hOS_Indicaciones;
    private String Modulo;
    private int UP_ID;
    private String fecha_actu;
    private String hora_actu;
    private String cod_usu;
    private String estado;
    private String Pc_Registro;
    private String cod_per;
    private String cod_medico;
    private int lt_minuto;
    private int AR_ID;
    private String COD_TIPO_TURNO;
    private String OBSERVACION;
    private int ACTO_MEDICO;
    private int HKD_ID;
    private String EMER_FORMA_LLEGADA_ID;
    private String EMER_TRAIDOPOR;
    private String EMER_PARENTESCO;
    private String EMER_OBSERVACION;
    private String ESTADO_CAJAP;
    private String cod_nomen;
    private String paq_globular;
    private String plasma;
    private String plaquetas;
    private int cantidad;
    private int donantes;
    private String hematocrito;
    private String grupo_sang;
    private String rh;
    private String hemoglobina;
    private String cod_jerar_forma_pago;
    private String elisa_vih;
    private String elisa_prueba_ra;
    private String elisa_prueba_config;
    private String elisa_consejero;

    private String procedencia;
    
    Conexion con = new Conexion();

    public boolean mantanimientoCajaPreventaEmergencia(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_MANTENIMIENTO_EMERGENCIA "
                        + "?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            cmd.setString(2, getId_hc());
            cmd.setString(3, getEMER_TRAIDOPOR());
            cmd.setString(4, getEMER_PARENTESCO());
            cmd.setString(5, getEMER_OBSERVACION());
            cmd.setString(6, getEMER_FORMA_LLEGADA_ID());
            cmd.setString(7, getCod_usu());
            cmd.setString(8, tipo);
            cmd.setString(9, getCod_nomen());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("mantanimientoCajaPreventaEmergencia: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean mantenimientoCajaPreventaHospitalizacion(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_MANTENIMIENTO_HOSPITALIZACION "
                        + "?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            cmd.setString(2, getId_hc());
            cmd.setInt(3, getCA_ID());
            cmd.setString(4, gethOS_Indicaciones());
            cmd.setString(5, getCod_usu());
            cmd.setInt(6, getAR_ID());
            cmd.setString(7, getProcedencia());
            cmd.setString(8, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("mantenimientoCajaPreventaHospitalizacion: " + ex.getMessage());
        }
        return resp;
    }
    
        public boolean CAJA_mantenimientoPreventaHospitalizacion()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_MANTENIMIENTO_HOSPITALIZACION_NUEVO "
                        + "?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_hc());
            cmd.setInt(2, getCA_ID());
            cmd.setString(3, gethOS_Indicaciones());
            cmd.setString(4, getCod_usu());
            cmd.setInt(5, getAR_ID());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("mantenimientoCajaPreventaHospitalizacion caja: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean mantanimientoCajaPreventaCExDepSangre(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_MANTENIMIENTO_CONSULT_EXT "
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            cmd.setString(2, getId_hc());
            cmd.setInt(3, getAR_ID());
            cmd.setString(4, getPaq_globular());
            cmd.setString(5, getPlasma());
            cmd.setString(6, getPlaquetas());
            cmd.setInt(7, getCantidad());
            cmd.setInt(8, getDonantes());
            cmd.setString(9, getHematocrito());
            cmd.setString(10, getGrupo_sang());
            cmd.setString(11, getRh());
            cmd.setString(12, getHemoglobina());
            cmd.setString(13, getCod_usu());
            cmd.setString(14, getCod_medico());
            cmd.setString(15, tipo);
            cmd.setInt(16, getACTO_MEDICO());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("mantanimientoCajaPreventaCExDepSangre: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean mantanimientoCajaPreventaPruebaElisa(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_MANTENIMIENTO_CONSULT_EXT_ELISA "
                        + "?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            cmd.setString(2, getModulo());
            cmd.setInt(3, getACTO_MEDICO());
            cmd.setString(4, getId_hc());
            cmd.setString(5, getElisa_consejero());
            cmd.setString(6, getElisa_vih());
            cmd.setString(7, getElisa_prueba_ra());
            cmd.setString(8, getElisa_prueba_config());
            cmd.setString(9, getCod_usu());
            cmd.setString(10, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("mantanimientoCajaPreventaPruebaElisa: " + ex.getMessage());
        }
        return resp;
    }
    
    public String codFormaLlegada(String formaLlegada)
    {
        String cod="";
        try
        {
            String sql = "SELECT EMER_FORMA_LLEGADA_ID FROM ADMISION_EMERGENCIA_FORMA_LLEGADA WHERE EMER_FORMA_LLEGADA_NOMBRE = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, formaLlegada);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codFormaLlegada: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean eliminarP(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_Preventa_Eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminar: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarPreventa(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Actualizar_Preventa ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            cmd.setInt(2, getACTO_MEDICO());
            cmd.setString(3, getCod_jerar_forma_pago());
            cmd.setString(4, getCod_medico());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarDetallePreventa(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Actualizar_Detalle_Preventa ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_Detalle_p());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }
    public boolean modificarPreventaRetorno(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Actualizar_Preventa_RETORNO ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }
    
        public boolean modificarPreventaCEX(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Actualizar_Preventa_CEX ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            cmd.setInt(2, getACTO_MEDICO());
            cmd.setString(3, getCod_jerar_forma_pago());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error Modificar CEX  PREVENTA MODELO" + ex.getMessage());
        }
        return resp;
    }


 
    public String Ultima_Emergencia(String ActoMedico){
        String cod="";
        try
        {
            String sql = "CAJA_PREVENTA_ULTIMO_EME ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, ActoMedico);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error Acto medico de emergencia: " + ex.getMessage());
        }
        return cod;
    }
    public String Ultima_CEX(String ActoMedico){
        String cod="";
        try
        {
            String sql = "CAJA_PREVENTA_ULTIMO_CEX ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, ActoMedico);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error Acto medico de emergencia: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean camas(){
        boolean resp = false;
        try
        {
            String sql = "Caja_Actualizar_Camas ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCA_ID());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }
    public String codUsuario(String nombreUsuario)
    {
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

    public String codArea(String area)
    {
        String cod="";
        try
        {
            String sql = " select SA.AR_ID\n" +
                        " from SISTEMA_SERVICIO SS, SISTEMA_AREAS SA, SISTEMA_CONFIGURACION_PC_AREA SP\n" +
                        " WHERE SS.UP_ID in (02)\n" +
                        " AND SP.SE_ID = SS.SE_ID\n" +
                        " AND SA.AR_DESC = ?\n" +
                        " AND SA.SE_ID = SS.SE_ID";
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
    
    public String codCama(String cama)
    {
        String cod="";
        try
        {
            String sql = "select ca_id\n" +
                        "from HOSPITALIZACION_CAMAS\n" +
                        "where CA_DESC = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, cama);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: codCama: " + ex.getMessage());
        }
        return cod;
    }

    
    public void formatoTablaCargarFormatEmer(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);//id
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//acto medico
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//nhc
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200);//PACIENTE
        tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//TRAIDO POR
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//PARENTESCO
        tabla.getColumnModel().getColumn(7).setPreferredWidth(150);//SERVICIO
        tabla.getColumnModel().getColumn(8).setPreferredWidth(150);//AREA
        tabla.getColumnModel().getColumn(9).setPreferredWidth(200);//MEDICO
        tabla.getColumnModel().getColumn(10).setPreferredWidth(80);//ID FL
        tabla.getColumnModel().getColumn(11).setPreferredWidth(120);//NOMBRE FL 
        tabla.getColumnModel().getColumn(12).setPreferredWidth(160);//OBSERVACION
        tabla.getColumnModel().getColumn(13).setPreferredWidth(100);//FECHA DE INGRESO
        tabla.getColumnModel().getColumn(14).setPreferredWidth(100);//HORA DE INGRESO
        tabla.setRowHeight(25);
    }
    
    public void listarDatosEmergencia(String paciente,String fechai, String fechaf,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Prev","Acto Médico","Nº H.C","DNI","Paciente",
                "Traído por","Parentesco","Servicio","Area","Médico"," ID FL",
                "Forma de llegada", "Observación","Fecha de ingreso","Hora de ingreso"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_PREVENTA_LISTAR_EMERGENCIA ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, paciente);
            cmd.setString(2, fechai);
            cmd.setString(3, fechaf);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // nhc
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // dni
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // dni
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
                fila[9]=r.getString(10);
                fila[10]=r.getString(11); // dni
                fila[11]=r.getString(12);
                fila[12]=r.getString(13);
                fila[13]=r.getString(14); // dni
                fila[14]=r.getString(15); // dni
                //fila[4]=r.getString(1); // codigo de hc
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarFormatEmer(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDatosEmergencia: " + e.getMessage());
        }
    }
    
    public int idCajaPreventa(){
        int id = 0;
        try {
            String consulta = "EXEC CAJA_PREVENTA_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getInt(1);
               FrmFormatoEmergencia.txtNroRegistro.setText(String.valueOf(id));
        }
        }catch(Exception ex){
            System.out.println("Error: idCajaPreventa: " + ex.getMessage());
        }
        return id;
    }
    
    public int CajaPreventaID(){
        int id = 0;
        try {
            String consulta = "EXEC [CAJA_PREVENTA_HOSPITALIZACION_ID]";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getInt(1);
        }
        }catch(Exception ex){
            System.out.println("Error: CajaPreventaID: " + ex.getMessage());
        }
        return id;
    }
    
    ///////////////////////////ANULAR VENTA,PREVENTA CAMA Y ASIGNACION CAMA
    public boolean anularcamas(){
        boolean resp = false;
        try
        {
            String sql = "Caja_Actualizar_Camas_ANULAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCA_ID());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean anularpreventa(){
        boolean resp = false;
        try
        {
            String sql = "Caja_Actualizar_Preventa_ANULAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean anularasigcamas(){
        boolean resp = false;
        try
        {
            String sql = "Caja_ANULAR_ASIGNACION_CAMA ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }
    
    public String CodSE(String precio)
    {
        String cod="";
        try
        {
            String sql = "EXEC CAJA_BUSCAR_SERVICIO_PREVENTA ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, precio);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_SE: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaDepositoSangre(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(14).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(15).setPreferredWidth(250);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaDepositoSangre(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Acto Médico","DNI","Nº H.C.","Paciente","Estado","Paquete Globular",
                "Plasma","Plaquetas","Cantidad","Donantes","Hematocrito","Grupo Sanguíneo","RH",
                "Hemoglobina","Médico"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaDepositoSangre(tabla);
    }
    
    public void listarDepositoSangre(String busqueda, String tipo, String tiempo,JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Acto Médico","DNI","Nº H.C.","Paciente","Estado","Paquete Globular",
                "Plasma","Plaquetas","Cantidad","Donantes","Hematocrito","Grupo Sanguíneo","RH",
                "Hemoglobina","Médico"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[24];
                consulta="EXEC CONSULTORIO_EXT_DEP_SANGRE_LISTAR ?,?,?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
                cmd.setString(2, tipo);
                cmd.setString(3, tiempo);
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
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDepositoSangre(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDepositoSangre: " + e.getMessage());
        }
    }
    
    public void formatoTablaElisa(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(250);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void inicializarTablaElisa(JTable tabla){
        tabla.setModel(new DefaultTableModel());
        String titulos[]={"ID","Acto Médico","DNI","Nº H.C.","Paciente","Estado"," Elisa VIH",
                "Prueba Rápida VIH","Prueba Confirmatoria VIH","Código","Médico"};
        m=new DefaultTableModel(null,titulos);
        tabla.setModel(m);
        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
        tabla.setRowSorter(elQueOrdena);
        tabla.setModel(m);
        formatoTablaElisa(tabla);
    }
    
    public void listarElisa(String busqueda,String tiempo,JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"ID","Acto Médico","DNI","Nº H.C.","Paciente","Estado"," Elisa VIH",
                "Prueba Rápida VIH","Prueba Confirmatoria VIH","Código","Médico"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[11];
                consulta="EXEC CONSULTORIO_EXT_ELISA_LISTAR ?,?";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
                cmd.setString(1, busqueda);
                cmd.setString(2, tiempo);
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
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaElisa(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarElisa: " + e.getMessage());
        }
    }
    
    public void ListarPreventasCEX(String paciente,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Prev","DNI","Nº H.C","Paciente",
                "Modulo", "Fecha","Hora","Id_Hc","estado","ID_CPT","CPT","AREA"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="Caja_Mostrar_Preventas_CEX ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, paciente);
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
            formatoTablaCargarPreventaCEX(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }
    
    public void formatoTablaCargarPreventaCEX(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);  
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(10).setPreferredWidth(500);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getColumnModel().getColumn(11).setMaxWidth(0); 

        
        tabla.setRowHeight(45);
    }
    
    public Caja_Preventa()
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
     * @return the CA_ID
     */
    public int getCA_ID() {
        return CA_ID;
    }

    /**
     * @param CA_ID the CA_ID to set
     */
    public void setCA_ID(int CA_ID) {
        this.CA_ID = CA_ID;
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
     * @return the hOS_Indicaciones
     */
    public String gethOS_Indicaciones() {
        return hOS_Indicaciones;
    }

    /**
     * @param hOS_Indicaciones the hOS_Indicaciones to set
     */
    public void sethOS_Indicaciones(String hOS_Indicaciones) {
        this.hOS_Indicaciones = hOS_Indicaciones;
    }

    /**
     * @return the Modulo
     */
    public String getModulo() {
        return Modulo;
    }

    /**
     * @param Modulo the Modulo to set
     */
    public void setModulo(String Modulo) {
        this.Modulo = Modulo;
    }

    /**
     * @return the UP_ID
     */
    public int getUP_ID() {
        return UP_ID;
    }

    /**
     * @param UP_ID the UP_ID to set
     */
    public void setUP_ID(int UP_ID) {
        this.UP_ID = UP_ID;
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
     * @return the Pc_Registro
     */
    public String getPc_Registro() {
        return Pc_Registro;
    }

    /**
     * @param Pc_Registro the Pc_Registro to set
     */
    public void setPc_Registro(String Pc_Registro) {
        this.Pc_Registro = Pc_Registro;
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
     * @return the cod_medico
     */
    public String getCod_medico() {
        return cod_medico;
    }

    /**
     * @param cod_medico the cod_medico to set
     */
    public void setCod_medico(String cod_medico) {
        this.cod_medico = cod_medico;
    }

    /**
     * @return the lt_minuto
     */
    public int getLt_minuto() {
        return lt_minuto;
    }

    /**
     * @param lt_minuto the lt_minuto to set
     */
    public void setLt_minuto(int lt_minuto) {
        this.lt_minuto = lt_minuto;
    }

    /**
     * @return the AR_ID
     */
    public int getAR_ID() {
        return AR_ID;
    }

    /**
     * @param AR_ID the AR_ID to set
     */
    public void setAR_ID(int AR_ID) {
        this.AR_ID = AR_ID;
    }

    /**
     * @return the COD_TIPO_TURNO
     */
    public String getCOD_TIPO_TURNO() {
        return COD_TIPO_TURNO;
    }

    /**
     * @param COD_TIPO_TURNO the COD_TIPO_TURNO to set
     */
    public void setCOD_TIPO_TURNO(String COD_TIPO_TURNO) {
        this.COD_TIPO_TURNO = COD_TIPO_TURNO;
    }

    /**
     * @return the OBSERVACION
     */
    public String getOBSERVACION() {
        return OBSERVACION;
    }

    /**
     * @param OBSERVACION the OBSERVACION to set
     */
    public void setOBSERVACION(String OBSERVACION) {
        this.OBSERVACION = OBSERVACION;
    }

    /**
     * @return the ACTO_MEDICO
     */
    public int getACTO_MEDICO() {
        return ACTO_MEDICO;
    }

    /**
     * @param ACTO_MEDICO the ACTO_MEDICO to set
     */
    public void setACTO_MEDICO(int ACTO_MEDICO) {
        this.ACTO_MEDICO = ACTO_MEDICO;
    }

    /**
     * @return the HKD_ID
     */
    public int getHKD_ID() {
        return HKD_ID;
    }

    /**
     * @param HKD_ID the HKD_ID to set
     */
    public void setHKD_ID(int HKD_ID) {
        this.HKD_ID = HKD_ID;
    }
    
    /**
     * @return the EMER_FORMA_LLEGADA_ID
     */
    public String getEMER_FORMA_LLEGADA_ID() {
        return EMER_FORMA_LLEGADA_ID;
    }

    /**
     * @param EMER_FORMA_LLEGADA_ID the EMER_FORMA_LLEGADA_ID to set
     */
    public void setEMER_FORMA_LLEGADA_ID(String EMER_FORMA_LLEGADA_ID) {
        this.EMER_FORMA_LLEGADA_ID = EMER_FORMA_LLEGADA_ID;
    }

    /**
     * @return the EMER_TRAIDOPOR
     */
    public String getEMER_TRAIDOPOR() {
        return EMER_TRAIDOPOR;
    }

    /**
     * @param EMER_TRAIDOPOR the EMER_TRAIDOPOR to set
     */
    public void setEMER_TRAIDOPOR(String EMER_TRAIDOPOR) {
        this.EMER_TRAIDOPOR = EMER_TRAIDOPOR;
    }

    /**
     * @return the EMER_PARENTESCO
     */
    public String getEMER_PARENTESCO() {
        return EMER_PARENTESCO;
    }

    /**
     * @param EMER_PARENTESCO the EMER_PARENTESCO to set
     */
    public void setEMER_PARENTESCO(String EMER_PARENTESCO) {
        this.EMER_PARENTESCO = EMER_PARENTESCO;
    }

    /**
     * @return the EMER_OBSERVACION
     */
    public String getEMER_OBSERVACION() {
        return EMER_OBSERVACION;
    }

    /**
     * @param EMER_OBSERVACION the EMER_OBSERVACION to set
     */
    public void setEMER_OBSERVACION(String EMER_OBSERVACION) {
        this.EMER_OBSERVACION = EMER_OBSERVACION;
    }

    /**
     * @return the ESTADO_CAJAP
     */
    public String getESTADO_CAJAP() {
        return ESTADO_CAJAP;
    }

    /**
     * @param ESTADO_CAJAP the ESTADO_CAJAP to set
     */
    public void setESTADO_CAJAP(String ESTADO_CAJAP) {
        this.ESTADO_CAJAP = ESTADO_CAJAP;
    }

    /**
     * @return the cod_nomen
     */
    public String getCod_nomen() {
        return cod_nomen;
    }

    /**
     * @param cod_nomen the cod_nomen to set
     */
    public void setCod_nomen(String cod_nomen) {
        this.cod_nomen = cod_nomen;
    }


    public String getCod_jerar_forma_pago() {
        return cod_jerar_forma_pago;
    }

    public void setCod_jerar_forma_pago(String cod_jerar_forma_pago) {
        this.cod_jerar_forma_pago = cod_jerar_forma_pago;
    }
    

    /**
     * @return the procedencia
     */
    public String getProcedencia() {
        return procedencia;
    }

    /**
     * @param procedencia the procedencia to set
     */
    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    /**
     * @return the paq_globular
     */
    public String getPaq_globular() {
        return paq_globular;
    }

    /**
     * @param paq_globular the paq_globular to set
     */
    public void setPaq_globular(String paq_globular) {
        this.paq_globular = paq_globular;
    }

    /**
     * @return the plasma
     */
    public String getPlasma() {
        return plasma;
    }

    /**
     * @param plasma the plasma to set
     */
    public void setPlasma(String plasma) {
        this.plasma = plasma;
    }

    /**
     * @return the plaquetas
     */
    public String getPlaquetas() {
        return plaquetas;
    }

    /**
     * @param plaquetas the plaquetas to set
     */
    public void setPlaquetas(String plaquetas) {
        this.plaquetas = plaquetas;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the donantes
     */
    public int getDonantes() {
        return donantes;
    }

    /**
     * @param donantes the donantes to set
     */
    public void setDonantes(int donantes) {
        this.donantes = donantes;
    }

    /**
     * @return the hematocrito
     */
    public String getHematocrito() {
        return hematocrito;
    }

    /**
     * @param hematocrito the hematocrito to set
     */
    public void setHematocrito(String hematocrito) {
        this.hematocrito = hematocrito;
    }

    /**
     * @return the grupo_sang
     */
    public String getGrupo_sang() {
        return grupo_sang;
    }

    /**
     * @param grupo_sang the grupo_sang to set
     */
    public void setGrupo_sang(String grupo_sang) {
        this.grupo_sang = grupo_sang;
    }

    /**
     * @return the rh
     */
    public String getRh() {
        return rh;
    }

    /**
     * @param rh the rh to set
     */
    public void setRh(String rh) {
        this.rh = rh;
    }

    /**
     * @return the hemoglobina
     */
    public String getHemoglobina() {
        return hemoglobina;
    }

    /**
     * @param hemoglobina the hemoglobina to set
     */
    public void setHemoglobina(String hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    /**
     * @return the elisa_vih
     */
    public String getElisa_vih() {
        return elisa_vih;
    }

    /**
     * @param elisa_vih the elisa_vih to set
     */
    public void setElisa_vih(String elisa_vih) {
        this.elisa_vih = elisa_vih;
    }

    /**
     * @return the elisa_prueba_ra
     */
    public String getElisa_prueba_ra() {
        return elisa_prueba_ra;
    }

    /**
     * @param elisa_prueba_ra the elisa_prueba_ra to set
     */
    public void setElisa_prueba_ra(String elisa_prueba_ra) {
        this.elisa_prueba_ra = elisa_prueba_ra;
    }

    /**
     * @return the elisa_prueba_config
     */
    public String getElisa_prueba_config() {
        return elisa_prueba_config;
    }

    /**
     * @param elisa_prueba_config the elisa_prueba_config to set
     */
    public void setElisa_prueba_config(String elisa_prueba_config) {
        this.elisa_prueba_config = elisa_prueba_config;
    }

    /**
     * @return the elisa_consejero
     */
    public String getElisa_consejero() {
        return elisa_consejero;
    }

    /**
     * @param elisa_consejero the elisa_consejero to set
     */
    public void setElisa_consejero(String elisa_consejero) {
        this.elisa_consejero = elisa_consejero;
    }

    public int getId_Detalle_p() {
        return Id_Detalle_p;
    }

    public void setId_Detalle_p(int Id_Detalle_p) {
        this.Id_Detalle_p = Id_Detalle_p;
    }
    
    

}
