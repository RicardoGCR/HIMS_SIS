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
public class Caja_Documento_Detalle {
DefaultTableModel m;
private Connection cn;
private int id_cod_doc_det;
private String id_documento;  
private String cod_precio;
private String nom_consultorio_citas;  
private int cantidad_detalle;
private Double precio_detalle;
private Double total_detalle;
private String fecha_aten;
private Double descu_exo_detalle;
private String personal_aten;
private String num_aten;
private String turno_cita;
private String cod_usu;  
private int Id_Preventa;
private int ACTO_MEDICO;

private int id_cod_det;
private String cod_produc;
private int cantidad;
private String nom_usu;

Conexion con = new Conexion();  

   public String idDet(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec CAJA_ID_DOCUMENTO_DETALLE";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_id doc det: " + ex.getMessage());
        }
        return id;
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
    public String CodPrecio(String precio ,String FP)
    {
        String cod="";
        try
        {
            String sql = "EXEC Caja_NomenclaturaVentaBUSCAR_PREVENTA ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, precio);
            cmd.setString(2, FP);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_PRECIO: " + ex.getMessage());
        }
        return cod;
    }
    
    public String CodPrecio1(String precio, String FP)
    {
        String cod="";
        try
        {
            String sql = "EXEC Caja_NomenclaturaVentaBUSCAR_PREVENTA1 ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, precio);
            cmd.setString(2, FP);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_PRECIO: " + ex.getMessage());
        }
        return cod;
    }
    
     public String CodPrecio_COD_PRECIO(String precio)
    {
        String cod="";
        try
        {
            String sql = "EXEC CAJA_HALLAR_PRECIO_DE_CODPRECIO ?";
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
            System.out.println("Error_PRECIO DEL CODIGO DEL PRECIO: " + ex.getMessage());
        }
        return cod;
    }
     
     public String CodPrecio_COD_PRECIO_FR(String precio,String FP)
    {
        String cod="";
        try
        {
            String sql = "EXEC CAJA_VERIFICAR_PRECIO_FR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, precio);
            cmd.setString(2, FP);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_PRECIO DEL CODIGO DEL PRECIO FR : " + ex.getMessage());
        }
        return cod;
    }
    public String CodPrecio_CODIGO_FR(String precio,String FP){
        String cod="";
        try
        {
            String sql = "EXEC CAJA_CODIGO_PRECIO_FR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, precio);
            cmd.setString(2, FP);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_PRECIO DEL CODIGO DEL PRECIO FR : " + ex.getMessage());
        }
        return cod;
    }
    
    public String VisibleAdmin(String codigo)
    {
        String cod="";
        try
        {
            String sql = "EXEC Caja_Listar_Visible_Admin ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, codigo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_PRECIO: " + ex.getMessage());
        }
        return cod;
    }
    
    
public boolean DetalleVenta(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_INSERTAR_DETALLE_VENTA "
                        + "?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
  
            cmd.setString(1, getId_documento());
            cmd.setString(2, getCod_precio());
//            cmd.setString(3, getNom_consultorio_citas());
            cmd.setInt(3, getCantidad_detalle());
            cmd.setDouble(4, getPrecio_detalle());
            cmd.setDouble(5, getTotal_detalle());
            cmd.setDouble(6, getDescu_exo_detalle());
            cmd.setString(7, getPersonal_aten());
            cmd.setString(8, getNum_aten());
            cmd.setString(9, getTurno_cita());
            cmd.setString(10, getCod_usu());


            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
    public boolean CAJA_DESCUENTO_INICIO(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_INICIAR_TEMPRAL ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean CAJA_DESCUENTO_EC_LA(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_DESCUENTO_LA_EC ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
  
            cmd.setInt(1, getId_cod_det());
            cmd.setString(2, getCod_produc());
            cmd.setInt(3, getCantidad());
            cmd.setString(4, getNom_usu());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  DESCONTAR " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean CAJA_DESCUENTO_FIN(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CONFIRMA_TEMPORAL ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }


    public boolean MovimientoHC(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_Mantenimiento_Movimiento_HC ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
  
            cmd.setString(1, getCod_usu());
            cmd.setString(2, getId_documento());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }


public void DetalleID(String ap_id){
        String consulta="";
        try {
            consulta="Listar_Detalle_preventa ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
            /////////Referida
                
                Caja_Pagos.lblIdDetalle.setText(r.getString(13));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR ID DETALLE PREVENTA " + e.getMessage());
        }
    }

    public void UltimoID_LA_EC(String ap_id){
        String consulta="";
        try {
            consulta="CAJA_BUSCAR_ULTIMA_VENTA_LA_EC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                
                Caja_Pagos.lblID_DETALLE_VENTA.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR ID DETALLE  " + e.getMessage());
        }
    }
    
    public void CONSULTAR_DESCUENTOLA_EC_RX(String ap_id,String ap_id2){
        String consulta="";
        try {
            consulta="CAJA_CONSULTAR_EXISTENCIAS_LA_EC_RX ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            cmd.setString(2, ap_id2);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){  
            /////////Referida
                Caja_Pagos.lblDescuento.setText(r.getString(1));    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: CONSULTA DE DESCUENTO EXISTENCIAS " + e.getMessage());
        }
    }
    
    public void CONSULTAR_COD_FP_FR(String ap_id){
        String consulta="";
        try {
            consulta="CAJA_CAMBIAR_FP_FR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){  
            /////////Referida
                Caja_Pagos.lblFP.setText(r.getString(1));   
                System.out.println("NUEVA FORMA DE PAGO " +r.getString(1));
            }
            //
        } catch (Exception e) {
            System.out.println("Error: CONSULTA ID FP " + e.getMessage());
        }
    }
    
public void Detalle(String codigo,JTable tabla){
//    tabla.getTableHeader().setVisible(false);
//    tabla.setTableHeader(null);
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"CPT","Cantidad","Precio","Dsct.","SubTotal","Departamento / Área","Fechs Atencion","Medico/Personal","Nº Atencion","Turno","cpt","idd","id_detalle","id_doc"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="Listar_Detalle_preventa ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, codigo);
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

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCPT(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar detalle de venta: " + e.getMessage());
        }
    }
    
    public void formatoTablaCPT(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(350);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);  
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(10).setMinWidth(0);
        tabla.getColumnModel().getColumn(10).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(7).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getColumnModel().getColumn(11).setMaxWidth(0);
        tabla.getColumnModel().getColumn(12).setMinWidth(0);
        tabla.getColumnModel().getColumn(12).setMaxWidth(0);
        tabla.getColumnModel().getColumn(13).setMinWidth(0);
        tabla.getColumnModel().getColumn(13).setMaxWidth(0);



        
        tabla.setRowHeight(40);
    }

    
    public void DESCUENTO_LA_EC(String codigo,String Grupo,JTable tabla){
//    tabla.getTableHeader().setVisible(false);
//    tabla.setTableHeader(null);
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"PRODUC","PRODUC REF","COD","CANTIDAD"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="CAJA_CONSULTAR_INSUMOS_LA ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, codigo);
            cmd.setString(2, Grupo);
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
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR INSUMOS ----: " + e.getMessage());
        }
    }







 public Caja_Documento_Detalle(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getId_cod_doc_det() {
        return id_cod_doc_det;
    }

    public void setId_cod_doc_det(int id_cod_doc_det) {
        this.id_cod_doc_det = id_cod_doc_det;
    }

    public String getId_documento() {
        return id_documento;
    }

    public void setId_documento(String id_documento) {
        this.id_documento = id_documento;
    }

    public String getCod_precio() {
        return cod_precio;
    }

    public void setCod_precio(String cod_precio) {
        this.cod_precio = cod_precio;
    }

    public String getNom_consultorio_citas() {
        return nom_consultorio_citas;
    }

    public void setNom_consultorio_citas(String nom_consultorio_citas) {
        this.nom_consultorio_citas = nom_consultorio_citas;
    }

    public int getCantidad_detalle() {
        return cantidad_detalle;
    }

    public void setCantidad_detalle(int cantidad_detalle) {
        this.cantidad_detalle = cantidad_detalle;
    }

    public Double getPrecio_detalle() {
        return precio_detalle;
    }

    public void setPrecio_detalle(Double precio_detalle) {
        this.precio_detalle = precio_detalle;
    }

    public Double getTotal_detalle() {
        return total_detalle;
    }

    public void setTotal_detalle(Double total_detalle) {
        this.total_detalle = total_detalle;
    }

    public String getFecha_aten() {
        return fecha_aten;
    }

    public void setFecha_aten(String fecha_aten) {
        this.fecha_aten = fecha_aten;
    }

    public Double getDescu_exo_detalle() {
        return descu_exo_detalle;
    }

    public void setDescu_exo_detalle(Double descu_exo_detalle) {
        this.descu_exo_detalle = descu_exo_detalle;
    }

    public String getPersonal_aten() {
        return personal_aten;
    }

    public void setPersonal_aten(String personal_aten) {
        this.personal_aten = personal_aten;
    }

    public String getNum_aten() {
        return num_aten;
    }

    public void setNum_aten(String num_aten) {
        this.num_aten = num_aten;
    }

    public String getTurno_cita() {
        return turno_cita;
    }

    public void setTurno_cita(String turno_cita) {
        this.turno_cita = turno_cita;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public int getId_Preventa() {
        return Id_Preventa;
    }

    public void setId_Preventa(int Id_Preventa) {
        this.Id_Preventa = Id_Preventa;
    }

    public int getACTO_MEDICO() {
        return ACTO_MEDICO;
    }

    public void setACTO_MEDICO(int ACTO_MEDICO) {
        this.ACTO_MEDICO = ACTO_MEDICO;
    }
    
    

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public int getId_cod_det() {
        return id_cod_det;
    }

    public void setId_cod_det(int id_cod_det) {
        this.id_cod_det = id_cod_det;
    }

    public String getCod_produc() {
        return cod_produc;
    }

    public void setCod_produc(String cod_produc) {
        this.cod_produc = cod_produc;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }
    
    
 
 
}
