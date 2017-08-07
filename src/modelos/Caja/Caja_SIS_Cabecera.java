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
public class Caja_SIS_Cabecera {
private Connection cn;

private String ID_FUA;  
private String ID_DOCUMENTO;  
private int ID_ESTABLECIMIENTO; 
private String ATENCION; 
Conexion con = new Conexion();

public boolean nuevoSIS(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CABECERA_SIS_INSERTAR "
                        + "?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getID_FUA());
            cmd.setString(1, getID_DOCUMENTO());
            cmd.setInt(2, getID_ESTABLECIMIENTO());
            cmd.setString(3, getATENCION());
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
   public String idSIS(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec SIS_FUA_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_SIS: " + ex.getMessage());
        }
        return id;
    }


public Caja_SIS_Cabecera(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getID_FUA() {
        return ID_FUA;
    }

    public void setID_FUA(String ID_FUA) {
        this.ID_FUA = ID_FUA;
    }

    public String getID_DOCUMENTO() {
        return ID_DOCUMENTO;
    }

    public void setID_DOCUMENTO(String ID_DOCUMENTO) {
        this.ID_DOCUMENTO = ID_DOCUMENTO;
    }

    public int getID_ESTABLECIMIENTO() {
        return ID_ESTABLECIMIENTO;
    }

    public void setID_ESTABLECIMIENTO(int ID_ESTABLECIMIENTO) {
        this.ID_ESTABLECIMIENTO = ID_ESTABLECIMIENTO;
    }

    public String getATENCION() {
        return ATENCION;
    }

    public void setATENCION(String ATENCION) {
        this.ATENCION = ATENCION;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }


    
}
