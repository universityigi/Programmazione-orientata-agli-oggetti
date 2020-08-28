
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
public class BroadcastChatWindow extends JFrame{
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket sock = null;
	
	private JScrollPane centralPanel;
	private JPanel southPanel;
	private JTextArea messagesArea;
	private JButton invio; 
	private JTextField textMessage;
	
	
	public BroadcastChatWindow(){
		
		try {
			setupConnection();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossibile stabilire una connessione con il server");
			System.exit(1);
		}
		
		this.setTitle("Messagistica");
		Container mainContainer = this.getContentPane();
		
		
		southPanel = new JPanel();
		
		messagesArea = new JTextArea(25,50);
		messagesArea.setEditable(false);
		
		centralPanel = new JScrollPane(messagesArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		textMessage = new JTextField(50);
		invio = new JButton("Invia");
		
		southPanel.add(textMessage);
		southPanel.add(invio);
		
		mainContainer.add(centralPanel, BorderLayout.CENTER);
		mainContainer.add(southPanel, BorderLayout.SOUTH);
		
		setLocation(200,100);
		
		ActionListener list= new InvioEventListener(textMessage, oos);
		invio.addActionListener(list);
		
		Polling p = new Polling(messagesArea, oos, ois);
		Thread t = new Thread(p);
		t.start();
		//Cambio il listner di finestra
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener wl = new MainWindListener(sock, oos, ois, p);
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
