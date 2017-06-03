/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
public class FormatoTablaReporteDiarioCaja extends DefaultTableCellRenderer{
     private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Dar color a las HC con estado Salida
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        
        if(table.getValueAt(row, 2).equals("CONTADO")){
            componente.setBackground(new Color(50,151,219));
        } else

        if(!table.getValueAt(row, 2).equals("CONTADO")){
            componente.setBackground(new Color(0,153,187)); 
        }
        
         if(!table.getValueAt(row, 6).equals("SIN ANULACION")){
            componente.setBackground(new Color(230,72,86));//
        }
        
//            componente.setBackground(new Color(39,174,97));
        return componente;
    }
    
}
