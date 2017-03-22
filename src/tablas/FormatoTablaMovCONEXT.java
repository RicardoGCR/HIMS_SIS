/*
    YAMILA ROCCA RUIZ
 */
package tablas;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import vista.ConsultorioEx.ConsultorioAsignacion;

public class FormatoTablaMovCONEXT extends DefaultTableCellRenderer{
 
    private Component componente;

    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Dar color a las HC con estado Salida
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        try {
         if(table.getValueAt(row, 1).equals(fechaActual())){
            componente.setBackground(new Color(67,115,45));
        
   
        } else
       
            componente.setBackground(new Color(138,123,64)); 
         
   
        } catch (Exception e) {
        }
        
        
        return componente;
    }
    
    public static String fechaActual(){
        java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(now);
    }
   
   
    
    
}
