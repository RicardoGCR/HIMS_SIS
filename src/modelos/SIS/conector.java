/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SIS;


import java.sql.*;
import javax.swing.JOptionPane;
import vista.SIS.principal;

/**
 *
 * @author PC-SISTEMA
 */
public class conector {
    public static Connection conectame(){
            Connection con1;
            

    try { 
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
//        String cod = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + 
//                System.getProperty("user.dir") + "\\BD\\prueba.accdb"; 
        String ruta=principal.txtRuta.getText();
        String cod = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+ruta; 
         con1 = DriverManager.getConnection(cod); 

        System.out.println("Conectado"); 
        return con1; 
    } catch (ClassNotFoundException | SQLException ex) {
          JOptionPane.showMessageDialog(null, "NO CONECTADO" + ex); 
          System.out.println("NO CONECTADO" + ex); 
    } 
    return null;
     
        

}
}
