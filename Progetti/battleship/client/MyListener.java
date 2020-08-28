package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MyListener implements ActionListener{
	private JButton start, stop, connect, disconnect, clear;
	private JTextField ip_address, port;
	private Socket socket;
	private ColoredButton[] buttons;
	private Downloader download;
	public MyListener(JButton start, JButton stop, JButton connect, JButton disconnect, JButton clear, JTextField ip_address, JTextField port, ColoredButton[] buttons){
		this.start = start;
		this.stop = stop;
		this.connect = connect;
		this.disconnect = disconnect;
		this.clear = clear;
		this.ip_address = ip_address;
		this.port = port;
		this.buttons = buttons;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		String sta="Start", sto="Stop", dis="Disconnect", con="Connect", cle="Clear";
		String cmd = e.getActionCommand();
		
		if(cmd.equals(sta)){
			connect.setEnabled(false);
			start.setEnabled(false);
			stop.setEnabled(true);
			disconnect.setEnabled(false);
			clear.setEnabled(false);
			
			Thread t = new Thread(download);
			t.start();
		}
		else if (cmd.equals(sto)){
			connect.setEnabled(false);
			start.setEnabled(true);
			stop.setEnabled(false);
			disconnect.setEnabled(true);
			clear.setEnabled(true);
			
			download.stop();
		}
		else if(cmd.equals(con)) {
			connect.setEnabled(false);
			start.setEnabled(true);
			stop.setEnabled(false);
			disconnect.setEnabled(true);
			clear.setEnabled(false);

			String ip = ip_address.getText();
			int p = Integer.parseInt(port.getText());
			try {
				socket = new Socket(ip,p);
				download = new Downloader(socket, buttons);
			}catch (IOException e1) {
				e1.printStackTrace();
			}			
		}	
		else if(cmd.equals(dis)){
			connect.setEnabled(true);
			start.setEnabled(false);
			stop.setEnabled(false);
			disconnect.setEnabled(false);
			clear.setEnabled(true);				
			
			download.disconnect();
		}
		else if(cmd.equals(cle)){
			connect.setEnabled(true);
			start.setEnabled(true);
			stop.setEnabled(false);
			disconnect.setEnabled(true);
			clear.setEnabled(false);
			
			this.clear();
		}
	}
	
	public void clear(){
		for(int i=0; i<144; i++)
			buttons[i].changeColor(Color.LIGHT_GRAY);
	}
}