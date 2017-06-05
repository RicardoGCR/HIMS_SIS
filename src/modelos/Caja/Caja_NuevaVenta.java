/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Caja;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.Caja.Caja_Pagos;
/**
 *
 * @author MYS1
 */
public class Caja_NuevaVenta {
DefaultTableModel m;
private Connection cn;
///////////////////////////////////////////////////////
private String id_documento ;
private String cod_tipo_documento ;
private String cod_jerar_forma_pago ;
private String id_hc ;
private String cod_motiv_anu ;
private String serie_documento ;
private String num_documento ;//////////////VENTA NUEVA
private String dependencia ;
private String cod_usu ;
private String id_liquidacion ;
private int Correlativo ;
private int Id_ActoMedico ;
private int id_Cta_Abono ;
private Double devolucion_doc;
private String cod_usu_anu;
//////////////////////////////////////////////////////
private int ID_ACTOMEDICO1 ;
private int NUM_ACTOMEDICO ;//////////////ACTO MEDICO
private String FECHA_TERMINO ;
private String DURACION ;
private int CA_ID;
private int CONTADOR_CITAS; 
private int CITAS_CAJA ;
private String ESTADOP;
private String cod_empre_jerar;
private String Usu_Exoneracion ;
private String porcentaje_Exoneracion ;
private double DESCUENTO;
private double TOTAL_DOCUUMENTO;


        
////////////////////////////////////////////////////
Conexion con = new Conexion();

public void Caja_Correlativo(){
        try {
            String consulta = "exec CAJA_SERIE_CORRELATIVO";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               Caja_Pagos.lblSerie.setText(r.getString(1));
               Caja_Pagos.lblNumeroDoc.setText(r.getString(1)+"-"+r.getString(2));
        }
        }catch(Exception ex){
            System.out.println("Error al generar serie y numero " + ex.getMessage());
        }
    }
    

public void SumaTotalReporte(String total){
        String consulta="";
        try {
            consulta="CAJA_RESUMEN_DIARIO_TOTAL ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, total);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblTotalDiario.setText("S/.  "+r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: SUMA TOTAL  " + e.getMessage());
        }
    }

public void SumaCONTADOReporte(String contado){
        String consulta="";
        try {
            consulta="CAJA_RESUMEN_DIARIO_TOTAL_CONTADO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, contado);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblTotalContado.setText("S/.  "+r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: SUMA CONTADO  " + e.getMessage());
        }
    }

public void SumaPENDIENTEReporte(String pendiente){
        String consulta="";
        try {
            consulta="CAJA_RESUMEN_DIARIO_TOTAL_PENDIENTE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, pendiente);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblTotalPendiente.setText("S/.  "+r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: SUMA PENDIENTE  " + e.getMessage());
        }
    }
public void SumaANULADOReporte(String anulado){
        String consulta="";
        try {
            consulta="CAJA_RESUMEN_DIARIO_TOTAL_ANULADO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, anulado);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblTotalAnulado.setText("S/.  "+r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: SUMA ANULADO  " + e.getMessage());
        }
    }

public void ConsultoriosExtPREVENTAListar(String ap_id){
        String consulta="";
        try {
            consulta="CAJA_ACTOMEDICO_EXISTENTE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblActoMedico.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR ActoMedico Existente  " + e.getMessage());
        }
    }

public void ConsultoriosACTOMEDICO_EMERGENCIA(String ap_id){
        String consulta="";
        try {
            consulta="Caja_AM_EMERGENCIA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblActoMedico.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: AM DE EMERGENCIA  " + e.getMessage());
        }
    }

    public void VerificarPreventaHOS_OJO_IMPORTANTE_DE_MOMENTO(String ap_id){
        String consulta="";
        try {
            consulta="CAJA_VERIFICAR_PREVENTA_HOS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblActoMedico.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Erro Preventa provicional  " + e.getMessage());
        }
    }

public void ConsultoriosExtPREVENTAListarCEX(String ap_id){
        String consulta="";
        try {
            consulta="CAJA_ACTOMEDICO_EXISTENTE_CEX ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblActoMedico.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR ActoMedico Existente  " + e.getMessage());
        }
    }


        public void Caja_Id_Preventa(String ap_id){
        String consulta="";
        try {
            consulta="Caja_Verificar_PreVenta ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                    
                Caja_Pagos.lblIdPreventa.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }

public boolean NuevoActoMedico()
        {
        boolean resp = false;
        try
        {
            String sql = "exec CAJA_ACTO_MEDICO_NUEVO ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_ACTOMEDICO1());
            cmd.setInt(2, getNUM_ACTOMEDICO());
            cmd.setString(3, getFECHA_TERMINO());
            cmd.setString(4, getDURACION());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }

public boolean ActualizarVenta()
        {
        boolean resp = false;
        try
        {
            String sql = "exec CAJA_ACTUALIZAR_VENTA_CABECERA ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_documento());
            cmd.setDouble(2, getDESCUENTO());
            cmd.setDouble(3, getTOTAL_DOCUUMENTO());
            cmd.setString(4, getUsu_Exoneracion());
            cmd.setString(5, getPorcentaje_Exoneracion());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }

public boolean Nuevo()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_VENTA_NUEVA_CABEZERA ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_documento());
            cmd.setString(2, getCod_tipo_documento());
            cmd.setString(3, getCod_jerar_forma_pago());
            cmd.setString(4, getId_hc());
            cmd.setString(5, getCod_motiv_anu());
            cmd.setString(6, getSerie_documento());
            cmd.setString(7, getNum_documento());
            cmd.setString(8, getDependencia());
            cmd.setString(9, getESTADOP());
            cmd.setString(10, getCod_usu());
            cmd.setString(11, getId_liquidacion());
            cmd.setInt(12, getCorrelativo());
            cmd.setInt(13, getId_ActoMedico());
            cmd.setInt(14, getId_Cta_Abono());
            cmd.setString(15, getCod_empre_jerar());
            cmd.setString(16, getUsu_Exoneracion());
            cmd.setString(17, getPorcentaje_Exoneracion());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }

public boolean eliminar(){
        boolean resp = false;
        try
        {
            String sql = "EXEC CAJA_ELIMINAR_CABECERA ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_documento());
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
 
 

 public String ActoMedico(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec CAJA_ID_ACTOMEDICO";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
        return id;
    }
public String id(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_DocumentoCabecera_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
        return id;
    }



public String sinanulacion(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_NuevaVentaSinAnulacion";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
        return id;
    }

public String codTipo(String tipo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_tipo_documento \n" +
                        "FROM CAJA_TIPO_DOCUMENTO\n" +
                        "WHERE tipo_documento = ?";
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

public String anular(String tipo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_motiv_anu \n" +
                        "FROM CAJA_MOTIVO_ANULACION\n" +
                        "WHERE descripcion_anulacion = ?";
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

public boolean modificarAnulacion(){
        boolean resp = false;
        try
        {
            String sql = "Exec CAJA_VENTA_CABECERA_ANULACION ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_documento());
            cmd.setString(2, getCod_motiv_anu());
            cmd.setDouble(3, getDevolucion_doc());
            cmd.setString(4, getCod_usu_anu());

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

public void listarMedicos(String medico,String Servicio,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Médico","Día","Mes","Año","Turno"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_LISTA_MEDICO_TURNO ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, medico);
            cmd.setString(2, Servicio);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaMedico(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }

public void listarMedicos1(String Servicio,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Médico","Día","Mes","Año","Turno",};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_LISTA_MEDICO_TURNO_TODO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Servicio);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaMedico(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }
    
    public void formatoTablaMedico(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(1).setPreferredWidth(350);  
        
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0); 
        
//        tabla.getColumnModel().getColumn(2).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(3).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(4).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(5).setPreferredWidth(150); 
//        
  
        tabla.setRowHeight(40);
    }
    
    public void Preventamostrar(String parametro,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nº Preventa","ID_H.C.","Modulo","CodNomen","Nomenclatura","Descripcion","Medico","Fecha","Hora"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec Caja_Verificar_PreVenta ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, parametro);;
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

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaMedico(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }
    
    
    public void ListarAsistentaSocial(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Apellidos y Nombres"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[1];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC Caja_Asiste_Social_Listar";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaAsistente(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar ASISTENTES: " + e.getMessage());
        }
    }
    
     public void formatoTablaAsistente(JTable tabla){

        tabla.setRowHeight(37);
    }
     
    public void BuscarCPT(String Texto,String FormaPago,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nomeclatura","Descripcion","Precio","Forma de Pago","Decripcion Forma Pago","","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec Caja_NomenclaturaVentaBUSCAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Texto);
            cmd.setString(2, FormaPago);
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

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCPT(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }
     public void formatoTablaCPT(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);

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
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.setRowHeight(37);
        
    }
     
    public void ListarConsultorios(String Texto,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Dia","Hora Inicio","Hora Termino","Consultorio","Nº de Citas","Adicionales","Futuras","Turno","Médico","","","","","","","","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[21];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec Caja_ConsultoriosExternosListar ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Texto);
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

                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDetalle(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarConsultorios CAJA: " + e.getMessage());
        }
    }
     public void formatoTablaDetalle(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(40);
       
        
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
        tabla.getColumnModel().getColumn(12).setMinWidth(0);
        tabla.getColumnModel().getColumn(12).setMaxWidth(0);
        tabla.getColumnModel().getColumn(13).setMinWidth(0);
        tabla.getColumnModel().getColumn(13).setMaxWidth(0);
        tabla.getColumnModel().getColumn(14).setMinWidth(0);
        tabla.getColumnModel().getColumn(14).setMaxWidth(0);
        tabla.getColumnModel().getColumn(15).setMinWidth(0);
        tabla.getColumnModel().getColumn(15).setMaxWidth(0);
        tabla.getColumnModel().getColumn(16).setMinWidth(0);
        tabla.getColumnModel().getColumn(16).setMaxWidth(0);
        tabla.getColumnModel().getColumn(17).setMinWidth(0);
        tabla.getColumnModel().getColumn(17).setMaxWidth(0);
        tabla.getColumnModel().getColumn(18).setMinWidth(0);
        tabla.getColumnModel().getColumn(18).setMaxWidth(0);
        tabla.getColumnModel().getColumn(19).setMinWidth(0);
        tabla.getColumnModel().getColumn(19).setMaxWidth(0);
        tabla.getColumnModel().getColumn(20).setMinWidth(0);
        tabla.getColumnModel().getColumn(20).setMaxWidth(0);

   

//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(45);
    }
     
     public boolean ActualizarCitas()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_ACTUALIZAR_CITAS ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCA_ID());
            cmd.setInt(2, getCONTADOR_CITAS());
            cmd.setInt(3, getCITAS_CAJA());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }
     
     public boolean ActualizarCitasFuturas()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_ACTUALIZAR_CITAS_FUTURA ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCA_ID());
            cmd.setInt(2, getCONTADOR_CITAS());
            cmd.setInt(3, getCITAS_CAJA());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }
     public boolean ActualizarCitasAdicionales()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_ACTUALIZAR_CITAS_ADICIONAL ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCA_ID());
            cmd.setInt(2, getCONTADOR_CITAS());
            cmd.setInt(3, getCITAS_CAJA());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }
     
     public void listarEmpresa(String Servicio ,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Codigo","Forma de Pago","Distrito","Representante","RUC","Direccion","Telefono","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC Caja_EmpresaJerarquia_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Servicio);
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

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaEmpresa(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar EMPRESAS : " + e.getMessage());
        }
    }
      public void formatoTablaEmpresa(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(1).setPreferredWidth(220);  
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(3).setPreferredWidth(170); 
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100); 
        tabla.getColumnModel().getColumn(5).setPreferredWidth(160); 
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100); 
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0); 
//        
  
        tabla.setRowHeight(40);
    }
      
      public void ReporteDiariocajaCabecera(String Usuario,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Documento","Nº Documento","Forma de Pago","DNI","HC","C","Estado","Descuento","Total","Fecha","Hora","Am","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_CONSULTAR_REPORTE_DIA_PC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Usuario);
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

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteCabecera(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar REPORTE CABECERA" + e.getMessage());
        }
    }
      
        public void ReporteDiariocajaCabeceraCC(String Servicio,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Documento","Nº Documento","Forma de Pago","DNI","HC","C","Estado","Descuento","Total","Fecha","Hora","Am","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_CONSULTAR_ACTOMEDICODNI ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Servicio);
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

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteCabecera(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar CABECERA REPORTE" + e.getMessage());
        }
    }
      
      public void formatoTablaReporteCabecera(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setMinWidth(0);
            tabla.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla.getColumnModel().getColumn(4).setMinWidth(0);
            tabla.getColumnModel().getColumn(4).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(11).setMinWidth(0);
            tabla.getColumnModel().getColumn(11).setMaxWidth(0);
            tabla.getColumnModel().getColumn(12).setMinWidth(0);
            tabla.getColumnModel().getColumn(12).setMaxWidth(0);
        
//        tabla.getColumnModel().getColumn(2).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(3).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(4).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(5).setPreferredWidth(150); 
//        
  
        tabla.setRowHeight(40);
        
    }
      
      public void ReporteDiariocajaDetalleCC(String Servicio,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"CPT","Precio","Departamento / Área","Precio","Descuento","Total","Médico/Personal","Nº Atencion","Turno","doc"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_CONSULTAR_ACTOMEDICODNI_DET ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Servicio); ///bus1 esto se busca
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
//                fila[10]=r.getString(11);
//                fila[11]=r.getString(12);
//                fila[12]=r.getString(13);
//                fila[13]=r.getString(14);

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaDetalleReporte(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar detalle REPORTE " + e.getMessage());
        }
    }
      
      public void formatoTablaDetalleReporte(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(430);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(220);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        
//        tabla.getColumnModel().getColumn(2).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(3).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(4).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(5).setPreferredWidth(150); 
//        
  
        tabla.setRowHeight(40);
    }

     


 public Caja_NuevaVenta(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getId_documento() {
        return id_documento;
    }

    public void setId_documento(String id_documento) {
        this.id_documento = id_documento;
    }

    public String getCod_tipo_documento() {
        return cod_tipo_documento;
    }

    public void setCod_tipo_documento(String cod_tipo_documento) {
        this.cod_tipo_documento = cod_tipo_documento;
    }

    public String getCod_jerar_forma_pago() {
        return cod_jerar_forma_pago;
    }

    public void setCod_jerar_forma_pago(String cod_jerar_forma_pago) {
        this.cod_jerar_forma_pago = cod_jerar_forma_pago;
    }

    public String getId_hc() {
        return id_hc;
    }

    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    public String getCod_motiv_anu() {
        return cod_motiv_anu;
    }

    public void setCod_motiv_anu(String cod_motiv_anu) {
        this.cod_motiv_anu = cod_motiv_anu;
    }

    public String getSerie_documento() {
        return serie_documento;
    }

    public void setSerie_documento(String serie_documento) {
        this.serie_documento = serie_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public String getId_liquidacion() {
        return id_liquidacion;
    }

    public void setId_liquidacion(String id_liquidacion) {
        this.id_liquidacion = id_liquidacion;
    }

    public int getCorrelativo() {
        return Correlativo;
    }

    public void setCorrelativo(int Correlativo) {
        this.Correlativo = Correlativo;
    }

    public int getId_ActoMedico() {
        return Id_ActoMedico;
    }

    public void setId_ActoMedico(int Id_ActoMedico) {
        this.Id_ActoMedico = Id_ActoMedico;
    }

    public int getId_Cta_Abono() {
        return id_Cta_Abono;
    }

    public void setId_Cta_Abono(int id_Cta_Abono) {
        this.id_Cta_Abono = id_Cta_Abono;
    }

    public Double getDevolucion_doc() {
        return devolucion_doc;
    }

    public void setDevolucion_doc(Double devolucion_doc) {
        this.devolucion_doc = devolucion_doc;
    }

    public String getCod_usu_anu() {
        return cod_usu_anu;
    }

    public void setCod_usu_anu(String cod_usu_anu) {
        this.cod_usu_anu = cod_usu_anu;
    }
    
    
///////////////////////////////////////////////ACTO MEDICO

    public int getID_ACTOMEDICO1() {
        return ID_ACTOMEDICO1;
    }

    public void setID_ACTOMEDICO1(int ID_ACTOMEDICO1) {
        this.ID_ACTOMEDICO1 = ID_ACTOMEDICO1;
    }

    public int getNUM_ACTOMEDICO() {
        return NUM_ACTOMEDICO;
    }

    public void setNUM_ACTOMEDICO(int NUM_ACTOMEDICO) {
        this.NUM_ACTOMEDICO = NUM_ACTOMEDICO;
    }

    public String getFECHA_TERMINO() {
        return FECHA_TERMINO;
    }

    public void setFECHA_TERMINO(String FECHA_TERMINO) {
        this.FECHA_TERMINO = FECHA_TERMINO;
    }

    public String getDURACION() {
        return DURACION;
    }

    public void setDURACION(String DURACION) {
        this.DURACION = DURACION;
    }
    
    
    
    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public int getCA_ID() {
        return CA_ID;
    }

    public void setCA_ID(int CA_ID) {
        this.CA_ID = CA_ID;
    }

    public int getCONTADOR_CITAS() {
        return CONTADOR_CITAS;
    }

    public void setCONTADOR_CITAS(int CONTADOR_CITAS) {
        this.CONTADOR_CITAS = CONTADOR_CITAS;
    }

    public int getCITAS_CAJA() {
        return CITAS_CAJA;
    }

    public void setCITAS_CAJA(int CITAS_CAJA) {
        this.CITAS_CAJA = CITAS_CAJA;
    }

    public String getESTADOP() {
        return ESTADOP;
    }

    public void setESTADOP(String ESTADOP) {
        this.ESTADOP = ESTADOP;
    }

    public String getCod_empre_jerar() {
        return cod_empre_jerar;
    }

    public void setCod_empre_jerar(String cod_empre_jerar) {
        this.cod_empre_jerar = cod_empre_jerar;
    }

    public String getUsu_Exoneracion() {
        return Usu_Exoneracion;
    }

    public void setUsu_Exoneracion(String Usu_Exoneracion) {
        this.Usu_Exoneracion = Usu_Exoneracion;
    }

    public String getPorcentaje_Exoneracion() {
        return porcentaje_Exoneracion;
    }

    public void setPorcentaje_Exoneracion(String porcentaje_Exoneracion) {
        this.porcentaje_Exoneracion = porcentaje_Exoneracion;
    }

    public double getDESCUENTO() {
        return DESCUENTO;
    }

    public void setDESCUENTO(double DESCUENTO) {
        this.DESCUENTO = DESCUENTO;
    }

    public double getTOTAL_DOCUUMENTO() {
        return TOTAL_DOCUUMENTO;
    }

    public void setTOTAL_DOCUUMENTO(double TOTAL_DOCUUMENTO) {
        this.TOTAL_DOCUUMENTO = TOTAL_DOCUUMENTO;
    }
    
    
    
    
    
    
    

}
