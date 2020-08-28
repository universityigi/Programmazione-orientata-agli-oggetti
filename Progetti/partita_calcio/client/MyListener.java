package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyListener implements ActionListener{
	private JTextField ip_address, port;
	private JTextArea lazioArea, romaArea;
        private JButton connect, disconnect, start, stop, lazio, roma;
	private Boolean connected, transmitting; 
	private Downloader download;
	private Socket socket;	
	
	public MyListener(JTextField ip_address, JTextField port, JTextArea lazioArea, JTextArea romaArea, JButton connect, JButton disconnect, JButton start, JButton stop, JButton lazio, JButton roma){
		this.ip_address = ip_address;
		this.port = port;
		this.lazioArea = lazioArea;
		this.romaArea = romaArea;
                this.connect = connect;
                this.disconnect = disconnect;
                this.start = start;
                this.stop = stop;
                this.lazio = lazio;
                this.roma = roma;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Connect")){
                        connect.setEnabled(false);
                        disconnect.setEnabled(true);
                        start.setEnabled(true);
                        stop.setEnabled(false);
                        lazio.setEnabled(false);
                        roma.setEnabled(false);
                        
			String ip = ip_address.getText();
			int p = Integer.parseInt(port.getText());
			try {
				socket = new Socket(ip,p);
				download = new Downloader(socket, lazioArea, romaArea);
			}catch (IOException e1) {
				e1.printStackTrace();
			}			
			System.out.println("Connesso");
		}
		else if(cmd.equals("Disconnect")){       
                        connect.setEnabled(true);
                        disconnect.setEnabled(false);
                        start.setEnabled(false);
                        stop.setEnabled(false);
                        lazio.setEnabled(false);
                        roma.setEnabled(false);
                        
			download.disconnect();
		}
		else if(cmd.equals("Start")){       
                        connect.setEnabled(false);
                        disconnect.setEnabled(false);
                        start.setEnabled(false);
                        stop.setEnabled(true);
                        lazio.setEnabled(true);
                        roma.setEnabled(true);
                        
			Thread t = new Thread(download);
			t.start();
		}
		else if(cmd.equals("Stop")){       
                        connect.setEnabled(false);
                        disconnect.setEnabled(true);
                        start.setEnabled(true);
                        stop.setEnabled(false);
                        lazio.setEnabled(false);
                        roma.setEnabled(false);
                        
			download.stop();
		}
		else if(cmd.equals("LAZIO")){
			download.lazio();
		}
		else if(cmd.equals("ROMA")){
			download.roma();
		}
	}
}