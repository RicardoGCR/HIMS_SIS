/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campos;

import java.awt.*;
import javax.swing.text.*;
import javax.swing.*;

public class LimitadorDeDocumento extends DefaultStyledDocument {
int caracteresMaximos;

public LimitadorDeDocumento( int caracteresMaximos ) {
this.caracteresMaximos = caracteresMaximos;
}

public void insertString(int offs, String str, AttributeSet a)
throws BadLocationException {
if ( (getLength() + str.length()) <= caracteresMaximos)
super.insertString(offs, str, a);
else
Toolkit.getDefaultToolkit().beep();
}

public static void main( String [] a ){
// este sera un limitador para 20 caracteres
LimitadorDeDocumento limitador = new LimitadorDeDocumento(20);
JTextField campo = new JTextField();
campo.setDocument(limitador);
// esto es para mostrarlo
JOptionPane.showMessageDialog(null,campo);
System.exit(0);
}

}