package gui;

import java.awt.*;
import javax.swing.*;
import listners.ButtonListner;


public class ServerInterface {
	
	private JFrame frame;
	private JPanel pan;
	private JButton but_A;
	private JButton but_S;
	ButtonListner l;
	
	public ServerInterface(){
		
		frame = new JFrame("BroadcastServerServer");
		pan = new JPanel(new FlowLayout());
		but_A = new JButton("Avvia");
		but_S = new JButton("Stop");
		pan.add(but_A);
		pan.add(but_S);
		frame.add(pan);
		frame.setVisible(true);
		frame.setSize(220,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ButtonListner l=new ButtonListner(but_A,but_S,frame); 
		but_A.addActionListener(l);
		but_S.addActionListener(l);
	}
}

