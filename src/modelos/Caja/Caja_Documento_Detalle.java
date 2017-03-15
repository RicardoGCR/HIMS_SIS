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
import servicios.Conexion;
/**
 *
 * @author MYS1
 */
public class Caja_Documento_Detalle {
private Connection cn;
private int id_cod_doc_det;
private String id_documento;  
private String cod_precio;
private String nom_consultorio_citas;  
private Double cantidad_detalle;
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
public boolean DetalleVenta(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_INSERTAR_DETALLE_VENTA "
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_cod_doc_det());
            cmd.setString(2, getId_documento());
            cmd.setString(3, getCod_precio());
            cmd.setString(4, getNom_consultorio_citas());
            cmd.setDouble(5, getCantidad_detalle());
            cmd.setDouble(6, getPrecio_detalle());
            cmd.setDouble(7, getTotal_detalle());
            cmd.setString(8, getFecha_aten());
            cmd.setDouble(9, getDescu_exo_detalle());
            cmd.setString(10, getPersonal_aten());
            cmd.setString(11, getNum_aten());
            cmd.setString(12, getTurno_cita());
            cmd.setString(13, getCod_usu());


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

    public Double getCantidad_detalle() {
        return cantidad_detalle;
    }

    public void setCantidad_detalle(Double cantidad_detalle) {
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
