/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author Yamila Rocca Ruiz
 */
public class AdmisionEmergenciaTopico extends DefaultTableCellHeaderRenderer{
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Dar color a las HC con estado Salida
        //if(table.getValueAt(row, 6).equals("Salida")){
            //componente.setBackground(new Color(255,85,64));
        //}
        //if(table.getValueAt(row, 12).equals("S")){
            //componente.setBackground(new Color(248,151,131));
        //} 
        return componente;
    }
}
