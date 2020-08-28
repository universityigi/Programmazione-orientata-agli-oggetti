package listners;

import java.awt.event.*;
import javax.swing.*;
import thread.Server;

public class ButtonListner implements ActionListener{
	
	private JButton a;
	private JButton s;
	private Server serv;
	private JFrame fr;
	
	public ButtonListner(JButton bott, JButton bot2,JFrame frame){
		a=bott;
		s=bot2;
		s.setEnabled(false);
		serv = new Server();
		fr=frame;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String x = e.getActionCommand();
		if(x.equals("Avvia")){
			
			a.setEnabled(false);
			Thread avv = new Thread(serv);
			avv.start();
			fr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			s.setEnabled(true);
		}
		else if(x.equals("Stop")){
			s.setEnabled(false);
			serv.ferma();
			fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			a.setEnabled(true);
		}
		else System.exit(1);
	}
}
