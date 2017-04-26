/*
    YAMILA ROCCA RUIZ
 */
package modelos.LABORATORIO;

import tablas.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTabla extends DefaultTableCellRenderer{
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Dar color 
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        
        
        //Para dar Color-descomentar
//        if(table.getValueAt(row, 11).equals("Completo")){
//            componente.setBackground(new Color(248,151,131));
//        } else if(table.getValueAt(row, 11).equals("R")){
//            componente.setBackground(new Color(168,217,232));
//        } else 
//            componente.setBackground(new Color(201,213,161));
        return componente;
    }
}
