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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC-SISTEMA
 */
public class Celda_Checkox  extends DefaultCellEditor implements TableCellRenderer{
   private JComponent component=new JCheckBox();
   private boolean value=false;//valor de la celda

   //constructor
    public Celda_Checkox() {
        super(new JCheckBox());
    }
    //retorna valor de celda
    @Override
    public Object getCellEditorValue(){
       return ((JCheckBox)component).isSelected();    
    }
    
    //segun el valor de la celda selecciona/deselecciona el jcheck
    
   @Override
    public Component getTableCellEditorComponent(JTable table, Object value,boolean  isSelected,int row, int column){
            //color de fondo en modo edicion
            ((JCheckBox)component).setBackground(new Color(200,200,0));
            //obtiene valor de cela y coloca en el jcheckbox
            boolean b=((Boolean)value).booleanValue();
            ((JCheckBox)component).setSelected(b);
       return ((JCheckBox)component);
            
    }
           
    //cuando termina la manipulacion de la celda
    @Override
    public  boolean stopCellEditing(){
        value=((Boolean)getCellEditorValue()).booleanValue();
        ((JCheckBox)component).setSelected(value);
       return super.stopCellEditing();
    }
    
    //retorna componente
    public Component getTableCellRendererComponent(JTable table, Object value,boolean  isSelected,boolean hasFocus,int row, int column){
        if(value==null)
    return null;
    return ((JCheckBox)component);
    }
    }
