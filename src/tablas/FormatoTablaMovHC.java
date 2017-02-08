/*
    YAMILA ROCCA RUIZ
 */
package tablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaMovHC extends DefaultTableCellRenderer{
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Dar color a las HC con estado Salida
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        if(table.getValueAt(row, 12).equals("S")){
            componente.setBackground(new Color(248,151,131));
        } else
        if(table.getValueAt(row, 12).equals("R")){
            componente.setBackground(new Color(168,217,232));
        } else 
            componente.setBackground(new Color(201,213,161));
        return componente;
    }
}