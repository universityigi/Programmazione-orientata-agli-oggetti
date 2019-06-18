import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;

@SuppressWarnings("serial")
public class BroadcastChatWindow extends JFrame{
	
	private PrintWriter pw;
	private Scanner scan;
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
		
		ActionListener list= new InvioEventListener(textMessage, pw);
		invio.addActionListener(list);
		
		Polling p = new Polling(messagesArea, pw, scan);
		Thread t = new Thread(p);
		t.start();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener wl = new MainWindListener(sock, pw, scan, p);
		this.addWindowListener(wl);
		
		this.setVisible(true);
	}

	private void setupConnection() throws UnknownHostException, IOException {
		sock= new Socket("127.0.0.1",3000);
		InputStream in = sock.getInputStream();
		OutputStream os = sock.getOutputStream();
		pw = new PrintWriter(new OutputStreamWriter(os));
		scan = new Scanner(new InputStreamReader(in));
	}	
}