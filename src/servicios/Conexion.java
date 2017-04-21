//OFICIAL
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Profe
 */
public class Conexion {
    
    static String servidor="192.168.1.38";
    static String puerto="1433";
    static String user="sa";
    static String password="sistemas";
    static String baseDatos="SISGESH_NET";
    Connection conexion=null;
    
     public Connection conectar()
    {
        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://"+servidor+":"+puerto+";"+"databaseName="+baseDatos
                    +";user="+user+";password="+password;
            conexion=DriverManager.getConnection(url);
             //System.out.println("Conexión exitosa");
        }
        catch(Exception ex)
        {   
            JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor");
            System.out.println("Error de conexion: " + ex.getMessage());
        }
        return conexion;
    }
     
     public ResultSet Listar(String cad){
        try {
            String url="jdbc:sqlserver://"+servidor+":"+puerto+";"+"databaseName="+baseDatos
                    +";user="+user+";password="+password;
            conexion=DriverManager.getConnection(url);
            
            PreparedStatement da=conexion.prepareStatement(cad);
            ResultSet tbl=da.executeQuery();
            return tbl;
        } catch (Exception e) {
            return null;
        }
    }

     
        /*public static void main(String[] args) throws SQLException {
        Conexion conexion = new Conexion();
        Connection con = conexion.conectar();
        }*/

     }
