/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.ConsultorioEx.AgendaCita;
/**
 *
 * @author MYS1
 */
public class ConsultorioEXTConsultorioAgenda {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;   
    
  
     public String mostrarDatosPaciente()
    {
        String cod="";
        try
        {
            String sql = "CONSULTORIO_EXT_LISTAR_CONSUULTORIO_AGENDA_CONTAR";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            //cmd.setString(1, nombreUsuario);
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
    
        
    public ConsultorioEXTConsultorioAgenda()
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
}
