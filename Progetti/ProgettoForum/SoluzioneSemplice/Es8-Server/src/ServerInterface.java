

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ServerInterface implements ActionListener{
	
	private JFrame frame;
	private JPanel pan;
	private JButton but_A;
	private JButton but_S;
	private Server serv;
	
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
		but_A.addActionListener(this);
		but_S.addActionListener(this);
		but_S.setEnabled(false);
		serv = new Server();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String x = e.getActionCommand();
		if(x.equals("Avvia")){
			
			but_A.setEnabled(false);
			Thread avv = new Thread(serv);
			avv.start();
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			but_S.setEnabled(true);
		}
		else if(x.equals("Stop")){
			but_S.setEnabled(false);
			serv.ferma();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			but_A.setEnabled(true);
		}
		else System.exit(1);
	}
}

