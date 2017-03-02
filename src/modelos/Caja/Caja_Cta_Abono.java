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
public class Caja_Cta_Abono {
private Connection cn;
private int id_Cta_Abono;
private String id_hc;
private String cod_nomen_caja;  
private Double monto;  
private byte Huella;  
private String Documento; 
private String Usuario; 
private int id_Cta_Abonoc;
Conexion con = new Conexion();  
public boolean nuevoC(){
        boolean resp = false;
        try{
            String sql = "exec Caja_Cta_Abono_Cabecera "
                        + "?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_hc());
            cmd.setString(2, getCod_nomen_caja());
            cmd.setByte(3, getHuella());

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
public boolean nuevo(){
        boolean resp = false;
        try{
            String sql = "exec Caja_Cta_Abono_INSERTAR "
                        + "?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_Cta_Abonoc());
            cmd.setDouble(2, getMonto());
            cmd.setString(3, getDocumento());
            cmd.setString(4, getUsuario());

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

public boolean modificar(){
        boolean resp = false;
        try
        {
            String sql = "Caja_Cta_Abono_MODIFICAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_Cta_Abono());
            cmd.setInt(2, getId_Cta_Abonoc());
            cmd.setDouble(3, getMonto());
            cmd.setString(4, getDocumento());
            cmd.setString(5, getUsuario());

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

public String nomen(String tipo)
    {
        String cod="";
        try
        {

            String sql = "select nomen_caja +'  '+ descripcion_nomen_tipo \n" +
                        "FROM CAJA_NOMENCLATURA_CAJA\n" +
                        "WHERE cod_nomen_caja =?";
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

 public String cpt(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec CAJA_CTA_ABONO_Momenclatura";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error : " + ex.getMessage());
        }
        return id;
    }

 public String id(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec CAJA_CTA_ABONO_Mostrar_id";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error : " + ex.getMessage());
        }
        return id;
    }
 
  public String doc(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec CAJA_CTA_ABONO_generar_id";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error : " + ex.getMessage());
        }
        return id;
    }
  
 public Caja_Cta_Abono(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getId_Cta_Abono() {
        return id_Cta_Abono;
    }

    public void setId_Cta_Abono(int id_Cta_Abono) {
        this.id_Cta_Abono = id_Cta_Abono;
    }

    public String getId_hc() {
        return id_hc;
    }

    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    public String getCod_nomen_caja() {
        return cod_nomen_caja;
    }

    public void setCod_nomen_caja(String cod_nomen_caja) {
        this.cod_nomen_caja = cod_nomen_caja;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public byte getHuella() {
        return Huella;
    }

    public void setHuella(byte Huella) {
        this.Huella = Huella;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public int getId_Cta_Abonoc() {
        return id_Cta_Abonoc;
    }

    public void setId_Cta_Abonoc(int id_Cta_Abonoc) {
        this.id_Cta_Abonoc = id_Cta_Abonoc;
    }
    
    

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

   
 
}
   