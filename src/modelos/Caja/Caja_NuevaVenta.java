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
import servicios.Conexion;
/**
 *
 * @author MYS1
 */
public class Caja_NuevaVenta {
private Connection cn;

private String id_documento ;
private String cod_tipo_documento ;
private String cod_jerar_forma_pago ;
private String id_hc ;
private String cod_motiv_anu ;
private String serie_documento ;
private String num_documento ;
private String cliente ;
private String dependencia ;
private Double descuento ;
private Double sub_total_doc ;
private Double igv_doc ;
private Double total_doc ;
private String fecha_actu ;
private String hora_actu ;
private String estado_pago ;
private String adelanto_doc ;
private Double devolucion_doc ;
private String usu_ade ;
private String shc ;
private String usu_facturacion ;
private String nom_pc ;
private String fecha_actu_anu ;
private String hora_actu_anu ;
private String nom_pc_anu ;
private String cod_usu_anu ;
private String cod_usu ;
private String tipo_venta ;
private String id_liquidacion ;
private String estado_doc ;

Conexion con = new Conexion();

        
public boolean Nuevo()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_Cta2_INSERTAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getId_cuenta2());
//            cmd.setString(2, getId_cuenta1());
//            cmd.setString(3, getCuenta_2());
//            cmd.setString(4, getDescripcion());
//            cmd.setString(5, getNom_usu());

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

public String idanu(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_Motiv_Anu_Id";
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

public boolean modificar(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Cta2_Actualizar ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getId_cuenta2());
//            cmd.setString(2, getId_cuenta1());
//            cmd.setString(3, getCuenta_2());
//            cmd.setString(4, getDescripcion());
//            cmd.setString(5, getNom_usu());

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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getSub_total_doc() {
        return sub_total_doc;
    }

    public void setSub_total_doc(Double sub_total_doc) {
        this.sub_total_doc = sub_total_doc;
    }

    public Double getIgv_doc() {
        return igv_doc;
    }

    public void setIgv_doc(Double igv_doc) {
        this.igv_doc = igv_doc;
    }

    public Double getTotal_doc() {
        return total_doc;
    }

    public void setTotal_doc(Double total_doc) {
        this.total_doc = total_doc;
    }

    public String getFecha_actu() {
        return fecha_actu;
    }

    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    public String getHora_actu() {
        return hora_actu;
    }

    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }

    public String getAdelanto_doc() {
        return adelanto_doc;
    }

    public void setAdelanto_doc(String adelanto_doc) {
        this.adelanto_doc = adelanto_doc;
    }

    public Double getDevolucion_doc() {
        return devolucion_doc;
    }

    public void setDevolucion_doc(Double devolucion_doc) {
        this.devolucion_doc = devolucion_doc;
    }

    public String getUsu_ade() {
        return usu_ade;
    }

    public void setUsu_ade(String usu_ade) {
        this.usu_ade = usu_ade;
    }

    public String getShc() {
        return shc;
    }

    public void setShc(String shc) {
        this.shc = shc;
    }

    public String getUsu_facturacion() {
        return usu_facturacion;
    }

    public void setUsu_facturacion(String usu_facturacion) {
        this.usu_facturacion = usu_facturacion;
    }

    public String getNom_pc() {
        return nom_pc;
    }

    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    public String getFecha_actu_anu() {
        return fecha_actu_anu;
    }

    public void setFecha_actu_anu(String fecha_actu_anu) {
        this.fecha_actu_anu = fecha_actu_anu;
    }

    public String getHora_actu_anu() {
        return hora_actu_anu;
    }

    public void setHora_actu_anu(String hora_actu_anu) {
        this.hora_actu_anu = hora_actu_anu;
    }

    public String getNom_pc_anu() {
        return nom_pc_anu;
    }

    public void setNom_pc_anu(String nom_pc_anu) {
        this.nom_pc_anu = nom_pc_anu;
    }

    public String getCod_usu_anu() {
        return cod_usu_anu;
    }

    public void setCod_usu_anu(String cod_usu_anu) {
        this.cod_usu_anu = cod_usu_anu;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public String getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    public String getId_liquidacion() {
        return id_liquidacion;
    }

    public void setId_liquidacion(String id_liquidacion) {
        this.id_liquidacion = id_liquidacion;
    }

    public String getEstado_doc() {
        return estado_doc;
    }

    public void setEstado_doc(String estado_doc) {
        this.estado_doc = estado_doc;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

   
 
}
