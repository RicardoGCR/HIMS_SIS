/*
    YAMILA ROCCA RUIZ
 */
package modelos;

import modelos.LABORATORIO.*;
import tablas.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaSIGA extends DefaultTableCellRenderer{
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Dar color 
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        
        //Para dar Color-descomentar
        if(table.getValueAt(row, 3).equals("0")||table.getValueAt(row, 3).equals("")){     
            componente.setBackground(new Color(242,136,136));
//            componente.setBackground(new Color(242,136,136));
            componente.setForeground(new Color(30,30,30));        
//            componente.setForeground(new Color(255,255,255));
        } else {
             componente.setBackground(new Color(255,255,255));
            componente.setForeground(new Color(30,30,30));
        }
        return componente;
    }
}
