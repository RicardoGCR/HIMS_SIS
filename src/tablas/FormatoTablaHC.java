package tablas;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaHC extends DefaultTableCellRenderer {
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        /*if(table.getValueAt(row, 37).equals("A")){
            componente.setBackground(new Color(164,194,137));
        } else 
        if(table.getValueAt(row, 37).equals("D")){
            componente.setBackground(new Color(255,157,120));
        }*/
        return componente;
    }
    
}
