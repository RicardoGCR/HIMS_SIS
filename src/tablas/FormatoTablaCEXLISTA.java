/*
    YAMILA ROCCA RUIZ
 */
package tablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaCEXLISTA extends DefaultTableCellRenderer{
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Dar color a las HC con estado Salida
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        
        if(table.getValueAt(row, 16).equals(1)){
            componente.setBackground(new Color(232,76,61));
        } else
        if(table.getValueAt(row, 16).equals(2)){
            componente.setBackground(new Color(255,153,51));
        } else 
        if(table.getValueAt(row, 16).equals(3)){
            componente.setBackground(new Color(39,174,97));
        }
            componente.setBackground(new Color(39,174,97));
        return componente;
    }
}
