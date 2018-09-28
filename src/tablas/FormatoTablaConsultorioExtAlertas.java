/*
    YAMILA ROCCA RUIZ
 */
package tablas;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import vista.ConsultorioEx.ConsultorioAsignacion;

public class FormatoTablaConsultorioExtAlertas extends DefaultTableCellRenderer{
 
    private Component componente;

    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        //Dar color a las HC con estado Salida
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        try {
            System.out.println(table.getValueAt(row, 15));
         if(table.getValueAt(row, 15).equals("1")){/*falta ATENDIDO*/
            componente.setBackground(new Color(243,156,17));
             System.out.println("fecha menor");
        } 
          if(table.getValueAt(row, 15).equals("3")){/*NO SE HA ATENDIDO*/
            componente.setBackground(new Color(232,76,61));
             System.out.println("fecha mayor");
        }
              if(table.getValueAt(row, 15).equals("2")){//debe atender hoy
           componente.setBackground(new Color(39,174,97));
             System.out.println("fecha igual");
        }  
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        
        
        return componente;
    }
     
}
