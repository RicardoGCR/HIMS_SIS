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
    public String CodPrecio(String precio)
    {
        String cod="";
        try
        {
            String sql = "EXEC Caja_NomenclaturaVentaBUSCAR_PREVENTA ?";
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
            System.out.println("Error_PRECIO: " + ex.getMessage());
        }
        return cod;
    }
    
    public String CodPrecio1(String precio)
    {
        String cod="";
        try
        {
            String sql = "EXEC Caja_NomenclaturaVentaBUSCAR_PREVENTA1 ?";
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
            System.out.println("Error_PRECIO: " + ex.getMessage());
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
                        + "?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
  
            cmd.setString(1, getId_documento());
            cmd.setString(2, getCod_precio());
            cmd.setString(3, getNom_consultorio_citas());
            cmd.setInt(4, getCantidad_detalle());
            cmd.setDouble(5, getPrecio_detalle());
            cmd.setDouble(6, getTotal_detalle());
            cmd.setDouble(7, getDescu_exo_detalle());
            cmd.setString(8, getPersonal_aten());
            cmd.setString(9, getNum_aten());
            cmd.setString(10, getTurno_cita());
            cmd.setString(11, getCod_usu());


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
            System.out.println("Error: LISTAR ID DETALLE  " + e.getMessage());
        }
    }
public void Detalle(String codigo,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"CPT","Cantidad","Precio","Dsct.","SubTotal","Departamento / Área","Atencion","Medico/Personal","Nº Atencion","Turno","cpt","idd","id"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
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


        
        tabla.setRowHeight(40);
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
 
 
}
