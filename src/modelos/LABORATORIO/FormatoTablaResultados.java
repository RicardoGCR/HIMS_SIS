/*
 */
package modelos.LABORATORIO;

import modelos.*;
import modelos.LABORATORIO.*;
import tablas.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaResultados extends DefaultTableCellRenderer{
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Dar color 
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        isCellEditable(1, 2);
        //Para dar Color-descomentar
        if(table.getValueAt(row, 2).equals("T")){     
            componente.setBackground(new Color(255,255,255));
//            componente.setBackground(new Color(242,136,136)); --Rojo
            componente.setForeground(new Color(30,30,30));        
            componente.setFont(new Font("Segoe UI Semilight",1, 11));
//            componente.setForeground(new Color(255,255,255));
            
        } else {
             componente.setBackground(new Color(255,255,255));
            componente.setForeground(new Color(30,30,30));
            componente.setEnabled(true);
        }
        return componente;
    }
    
     public boolean isCellEditable (int row, int column)
   {
       // Aquí devolvemos true o false según queramos que una celda
       // identificada por fila,columna (row,column), sea o no editable
       if (column == 2)
          return false;
       return false;
   }
}
