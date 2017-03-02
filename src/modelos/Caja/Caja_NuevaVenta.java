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

////////////////////////////////////////////////////
Conexion con = new Conexion();

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

public boolean Nuevo()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_VENTA_NUEVA_CABEZERA ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_documento());
            cmd.setString(2, getCod_tipo_documento());
            cmd.setString(3, getCod_jerar_forma_pago());
            cmd.setString(4, getId_hc());
            cmd.setString(5, getCod_motiv_anu());
            cmd.setString(6, getSerie_documento());
            cmd.setString(7, getNum_documento());
            cmd.setString(8, getDependencia());
            cmd.setString(9, getCod_usu());
            cmd.setString(10, getId_liquidacion());
            cmd.setInt(11, getCorrelativo());
            cmd.setInt(12, getId_ActoMedico());
            cmd.setInt(13, getId_Cta_Abono());

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

}
