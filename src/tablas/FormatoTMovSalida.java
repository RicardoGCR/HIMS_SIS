package tablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTMovSalida extends DefaultTableCellRenderer {
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(table.getValueAt(row, 8).equals("S")){
            componente.setBackground(new Color(201,197,80));
        } /*else {
            componente.setBackground(new Color(201,0,80));
        }*/
        return componente;
    }
    
}
