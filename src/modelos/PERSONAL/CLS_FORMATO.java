/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.PERSONAL;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author PC02
 */
public class CLS_FORMATO extends DefaultTableCellRenderer{
    private Component componente;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        //Dar color 
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        
        //Para dar Color-descomentar
        if(table.getValueAt(row, 1).equals("UO0000000000003")){     
            componente.setBackground(new Color(0,0,136));
//            componente.setBackground(new Color(242,136,136));
//            componente.setForeground(new Color(30,30,30));        
//            componente.setForeground(new Color(255,255,255));
        } else {
             componente.setBackground(new Color(255,255,255));
//            componente.setForeground(new Color(30,30,30));
        }
        return componente;
    }
}
