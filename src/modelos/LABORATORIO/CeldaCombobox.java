/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;

import tablas.*;
import java.awt.Component;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Silvana
 */
public class CeldaCombobox extends AbstractCellEditor implements TableCellEditor{
    JComboBox jcbx = new JComboBox(new String[]{"Pendiente","Salida","Retorno"});
    Object valorActual;
    
    public CeldaCombobox(){
        ItemListener il = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    ItemSelectable is = e.getItemSelectable();
                    String bjt = (String)is.getSelectedObjects()[0];
                    valorActual = bjt;
                }
            }
        };
        jcbx.addItemListener(il);
    }
    
    @Override
    public Object getCellEditorValue() {
        return valorActual;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ModeloTabla mdl = (ModeloTabla)table.getModel();
        //mdl = mdl.getF
        return jcbx;
    }
    
}
