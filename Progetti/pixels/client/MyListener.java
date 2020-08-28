package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyListener implements ActionListener{
	private JButton connect, disconnect, start, stop, clear; 
	private JTextField ip_address, port;
	private Socket socket;
	private JPanel[] arrayPanel;
	private Downloader download;
        
	public MyListener(JButton connect, JButton disconnect, JButton start, JButton stop, JButton clear, JTextField ip_address, JTextField port, JPanel[] arrayPanel){
		this.connect = connect;
		this.disconnect = disconnect;
		this.start = start;
		this.stop = stop;
		this.clear = clear;
		this.ip_address = ip_address;
		this.port = port;
		this.arrayPanel= arrayPanel;	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String co = "Connect", d = "Disconnect", cl = "Clear", s = "Start", sto = "Stop" ;
                String cmd = e.getActionCommand();
                
		if(co.equals(cmd)){
			connect.setEnabled(false);
			start.setEnabled(true);
			stop.setEnabled(false);
			disconnect.setEnabled(true);
			clear.setEnabled(false);

			String ip = ip_address.getText();
			int p = Integer.parseInt(port.getText());
			try {
				socket = new Socket(ip,p);
			}catch (IOException e1) {
				e1.printStackTrace();
			}			
		}
		else if(d.equals(cmd)){
			connect.setEnabled(true);
			start.setEnabled(false);
			stop.setEnabled(false);
			disconnect.setEnabled(false);
			clear.setEnabled(true);				
			download.disconnect();
		}
		else if(s.equals(cmd)){
			connect.setEnabled(false);
			start.setEnabled(false);
			stop.setEnabled(true);
			disconnect.setEnabled(false);
			clear.setEnabled(false);
			download = new Downloader(socket, arrayPanel); 
			Thread t = new Thread(download);
			t.start();
		}
		else if(sto.equals(cmd)){
			connect.setEnabled(false);
			start.setEnabled(true);
			stop.setEnabled(false);
			disconnect.setEnabled(true);
			clear.setEnabled(true);
			download.stop();
		}
		else if(cl.equals(cmd)){
			connect.setEnabled(true);
			start.setEnabled(false);
			stop.setEnabled(false);
			disconnect.setEnabled(false);
			clear.setEnabled(false);
                        download.disconnect();
			download.clear();
		}
	}
}
