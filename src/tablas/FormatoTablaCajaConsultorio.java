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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import vista.ConsultorioEx.ConsultorioAsignacion;

public class FormatoTablaCajaConsultorio extends DefaultTableCellRenderer{
 

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JLabel cell =(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        
        if (value instanceof Long){
            long valor=(long)value;
            if(column==4){
                if(valor<12){
                    cell.setBackground(Color.red);
                    cell.setBackground(Color.white);
                }
            }
        }
        
        if (value instanceof String){
            
            String Valor =(String) value;
            cell.setBackground(Color.red);
            cell.setBackground(Color.white);
            
        }

        
        
        
        return cell;
    }
    
    
    }
