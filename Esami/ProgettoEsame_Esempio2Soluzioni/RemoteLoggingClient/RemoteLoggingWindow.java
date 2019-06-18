
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

@SuppressWarnings("serial")
public class RemoteLoggingWindow extends JFrame{
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket sock = null;
	
	private JPanel southPanel;
	private JButton invio; 
	private JTextField textMessage;
	
	private String name;
	
	
	public RemoteLoggingWindow(){
		
		name = JOptionPane.showInputDialog("Username:");
		if(name == null){
			JOptionPane.showMessageDialog(null, "Necessario indicare un username.");
			System.exit(0);
		}
		try {
			setupConnection();
			
			oos.writeObject(name);
			oos.flush();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossibile stabilire una connessione con il server");
			System.exit(1);
		}	
		
		this.setTitle(name);
		Container mainContainer = this.getContentPane();
		
		
		southPanel = new JPanel();
				
		textMessage = new JTextField(50);
		invio = new JButton("Invia");
		
		southPanel.add(textMessage);
		southPanel.add(invio);
		
		mainContainer.add(southPanel, BorderLayout.CENTER);
		
		setLocation(200,100);
		
		ActionListener list= new InvioEventListener(textMessage, oos);
		invio.addActionListener(list);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener wl = new MainWindListener(sock, oos, ois);
		this.addWindowListener(wl);
		
		this.setVisible(true);
		
		
	}


	private void setupConnection() throws UnknownHostException, IOException {
		sock= new Socket("127.0.0.1",3000);
		InputStream in = sock.getInputStream();
		OutputStream os = sock.getOutputStream();
		oos = new ObjectOutputStream(os);
		ois = new ObjectInputStream(in);
	}	
}
