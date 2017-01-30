/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author PC-SISTEMA
 */
public class Render_Checkbox extends JCheckBox implements TableCellRenderer{
    private JComponent component=new JCheckBox();
   
    public Render_Checkbox(){
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //color de fondo de la celda
            ((JCheckBox)component).setBackground(new Color(0,0,0));
            //obtiene valor boolean y coloca en el jcheckbox
            boolean b=((Boolean)value).booleanValue();
            ((JCheckBox)component).setSelected(b);
       return ((JCheckBox)component);//To change body of generated methods, choose Tools | Templates.
    }
}
