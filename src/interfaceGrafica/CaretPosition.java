package interfaceGrafica;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.text.JTextComponent;

class CaretPosition extends FocusAdapter{

    @Override
    public void focusGained(FocusEvent e) {

        JTextComponent comp = (JTextComponent) e.getSource();           
        comp.setCaretPosition(0);
    }       
} 
