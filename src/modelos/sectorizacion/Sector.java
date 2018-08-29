package modelos.sectorizacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;

public class Sector {

    private Connection cn;
    private String se_id;
    private String cod_dis;
    private String se_cod;
    private String se_tipo_dir;
    private String se_dir;
    private String se_alias;

    
    private String se_usu;
    private String se_fech;
    private String se_hora;
    private String se_estado;
    private String se_usuario;
    private String cod_dep;
    private String cod_prov;
    Conexion con = new Conexion();

    public boolean nuevaDireccion()
        {
        boolean resp = false;
        try{
            String sql = "EXEC SP_SISTEMASECTOR_NUEVO_DIRECCION ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getSe_id());
            cmd.setString(2, getCod_dis());
            cmd.setString(3, getSe_cod());
            cmd.setString(4, getSe_tipo_dir());
            cmd.setString(5, getSe_dir());  
            cmd.setString(6, getSe_alias());
            cmd.setString(7, getSe_usuario());
            cmd.setString(8, getCod_prov());
            cmd.setString(9, getCod_dep());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_nuevaDireccion: " + ex.getMessage());
        }
        return resp;
    }
    public boolean nuevoSectorPersonal()
        {
        boolean resp = false;
        try{
            String sql = "EXEC [SP_NUEVO_SECTOR_PERSONAL] ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getSe_cod()); 
            cmd.setString(2, getSe_id());
            cmd.setString(3, getSe_alias());
            cmd.setString(4, getSe_fech());
            cmd.setString(5, getSe_hora());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_nuevaDireccion: " + ex.getMessage());
        }
        return resp;
    }
    public boolean modificarSectorPersonal()
        {
        boolean resp = false;
        try{
            String sql = "EXEC [SP_MODIFICAR_SECTOR_PERSONAL] ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
             cmd.setString(1, getSe_usu());  
             cmd.setString(2, getSe_cod()); 
            cmd.setString(3, getSe_id());
            cmd.setString(4, getSe_alias());
            cmd.setString(5, getSe_fech());
            cmd.setString(6, getSe_hora());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_nuevaDireccion: " + ex.getMessage());
        }
        return resp;
    }
    public boolean eliminarSectorPersonal()
        {
        boolean resp = false;
        try{
            String sql = "EXEC [SP_ELIMINAR_SECTOR_PERSONAL] ? ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getSe_cod());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_nuevaDireccion: " + ex.getMessage());
        }
        return resp;
    }
     public boolean nuevoSEctor(String Se_id,String Cod_dist, String Cod_prov,String Cod_dep)
        {
        boolean resp = false;
        try{
            String sql = "EXEC SP_NUEVO_SECTOR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, Se_id);
            cmd.setString(2, Cod_dist);
            cmd.setString(3, Cod_prov);
            cmd.setString(4, Cod_dep);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_nuevaDireccion: " + ex.getMessage());
        }
        return resp;
    }
    public boolean modificarDireccion()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_SISTEMASECTOR_MODIFICAR_DIRECCION ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getSe_id());
            cmd.setString(2, getCod_dis());
            cmd.setString(3, getSe_cod());
            cmd.setString(4, getSe_tipo_dir());
            cmd.setString(5, getSe_dir());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error_modificarDireccion: " + ex.getMessage());
        }
        return resp;
    }
    public void Sector() {
        try {
            Map parametros = new HashMap();
            parametros.put("AS", "AS");
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/sectorizacion/report2.jasper"), parametros, con.conectar()); 
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("SECTOTIZACIÓN");
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error_reporteDiario:"+e.getMessage());
        } 
        } 
     public void Sector_Paciente(String PACIENTE,String opc1,String txt1,String txt2) {
        try {
            Map parametros = new HashMap();    
            parametros.put("PACIENTE", PACIENTE);
            parametros.put("opc1", opc1);
            parametros.put("txt1", txt1);
            parametros.put("txt2", txt2);
            System.out.println(PACIENTE);     
            System.out.println(opc1);
            System.out.println(txt1);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/sectorizacion/sector_paciente.jasper"), parametros, con.conectar()); 
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("SECTOTIZACIÓN");
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error_reporte Sector:"+e.getMessage());
        } 
        } 
    public boolean eliminarDireccion()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_SISTEMASECTOR_ELIMINAR_DIRECCION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getSe_id());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminarDireccion: " + ex.getMessage());
        }
        return resp;
    }
    
    public String codDepartamento(String departamento)
    {
        String cod="";
        try
        {
            String sql = "SELECT COD_DEP \n" +
                        "FROM SISTEMA_DEPARTAMENTO\n" +
                        "WHERE NOMBRE_DEPARTAMENTO = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, departamento);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codDepartamento: " + ex.getMessage());
        }
        return cod;
    }
    
    public String codProvincia(String provincia)
    {
        String cod="";
        try
        {
            String sql = "SELECT COD_PROV \n" +
                        "FROM SISTEMA_PROVINCIA\n" +
                        "WHERE NOMBRE_PROVINCIA = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, provincia);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codDepartamento: " + ex.getMessage());
        }
        return cod;
    }
    
    public String codDistrito(String distrito)
    {
        String cod="";
        try
        {
            String sql = "SELECT COD_DIS \n" +
                        "FROM SISTEMA_DISTRITO\n" +
                        "WHERE NOMBRE_DISTRITO = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, distrito);
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
    
    public boolean restaurarDireccion()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_SISTEMASECTOR_RESTAURAR_DIRECCION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getSe_id());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_restaurarDireccion: " + ex.getMessage());
        }
        return resp;
    }
    
    public String idSector(){
        String id = "";
        try {
            String consulta = "EXEC SP_SISTEMASECTOR_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_idSector: " + ex.getMessage());
        }
        return id;
    }
    
    public Sector()
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
     * @return the se_id
     */
    public String getSe_id() {
        return se_id;
    }

    /**
     * @param se_id the se_id to set
     */
    public void setSe_id(String se_id) {
        this.se_id = se_id;
    }

    /**
     * @return the cod_dis
     */
    public String getCod_dis() {
        return cod_dis;
    }

    /**
     * @param cod_dis the cod_dis to set
     */
    public void setCod_dis(String cod_dis) {
        this.cod_dis = cod_dis;
    }

    /**
     * @return the se_cod
     */
    public String getSe_cod() {
        return se_cod;
    }

    /**
     * @param se_cod the se_cod to set
     */
    public void setSe_cod(String se_cod) {
        this.se_cod = se_cod;
    }

    /**
     * @return the se_tipo_dir
     */
    public String getSe_tipo_dir() {
        return se_tipo_dir;
    }

    /**
     * @param se_tipo_dir the se_tipo_dir to set
     */
    public void setSe_tipo_dir(String se_tipo_dir) {
        this.se_tipo_dir = se_tipo_dir;
    }

    /**
     * @return the se_dir
     */
    public String getSe_dir() {
        return se_dir;
    }

    /**
     * @param se_dir the se_dir to set
     */
    public void setSe_dir(String se_dir) {
        this.se_dir = se_dir;
    }
    
    /**
     * @return the se_usu
     */
    public String getSe_usu() {
        return se_usu;
    }

    /**
     * @param se_usu the se_usu to set
     */
    public void setSe_usu(String se_usu) {
        this.se_usu = se_usu;
    }

    /**
     * @return the se_fech
     */
    public String getSe_fech() {
        return se_fech;
    }

    /**
     * @param se_fech the se_fech to set
     */
    public void setSe_fech(String se_fech) {
        this.se_fech = se_fech;
    }

    /**
     * @return the se_hora
     */
    public String getSe_hora() {
        return se_hora;
    }

    /**
     * @param se_hora the se_hora to set
     */
    public void setSe_hora(String se_hora) {
        this.se_hora = se_hora;
    }

    /**
     * @return the se_estado
     */
    public String getSe_estado() {
        return se_estado;
    }

    /**
     * @param se_estado the se_estado to set
     */
    public void setSe_estado(String se_estado) {
        this.se_estado = se_estado;
    }

    /**
     * @return the se_usuario
     */
    public String getSe_usuario() {
        return se_usuario;
    }
    
    /**
     * @return the cod_dep
     */
    public String getCod_dep() {
        return cod_dep;
    }

    /**
     * @param cod_dep the cod_dep to set
     */
    public void setCod_dep(String cod_dep) {
        this.cod_dep = cod_dep;
    }

    /**
     * @return the cod_prov
     */
    public String getCod_prov() {
        return cod_prov;
    }

    /**
     * @param cod_prov the cod_prov to set
     */
    public void setCod_prov(String cod_prov) {
        this.cod_prov = cod_prov;
    }

    public void setSe_usuario(String se_usuario) {
        this.se_usuario = se_usuario;
    }
    public String getSe_alias() {
        return se_alias;
    }

    public void setSe_alias(String se_alias) {
        this.se_alias = se_alias;
    }
}
