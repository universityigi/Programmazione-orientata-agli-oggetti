package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ColoredButton extends JButton implements ActionListener{
    private static final long serialVersionUID = 1L;
    public ColoredButton(){
	super();
    }
    public void changeColor(Color c){
	this.setBackground(c);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
	ColoredButton button = (ColoredButton)e.getSource();
	if(!(button.getModel().isPressed()) && button.getBackground().equals(Color.LIGHT_GRAY))
            button.changeColor(Color.CYAN);
	else if(button.getBackground().equals(Color.CYAN))
            button.changeColor(Color.LIGHT_GRAY);
    }
}