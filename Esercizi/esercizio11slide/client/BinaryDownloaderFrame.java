
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class BinaryDownloaderFrame extends JFrame {

	private JPanel centralPanel;
	private JTextField addressText;
	private JTextField portaText;
	private JTextField matricolaText;
	private JPanel central11;
	private JPanel central12;
	private JPanel central21;
	private JPanel southpanel;
	private JButton connectBtn;
	private JButton disconnectBtn;
	private JButton startBtn;
	private JButton stopBtn;

	public BinaryDownloaderFrame() {

		// Widgets
		matricolaText = new JTextField(20);
		addressText = new JTextField(20);
		portaText = new JTextField(20);
		connectBtn = new JButton("Connect");		
		disconnectBtn = new JButton("Disconnect");
		startBtn = new JButton("Start");
		stopBtn = new JButton("Stop");
		
		// Layout
		central11 = new JPanel(new BorderLayout());
		central11.add(new JLabel("Matricola"), BorderLayout.NORTH);
		central11.add(matricolaText, BorderLayout.SOUTH);
				
		central12 = new JPanel(new BorderLayout());
		central12.add(new JLabel("IP Address"), BorderLayout.NORTH);
		central12.add(addressText, BorderLayout.SOUTH);
		
		central21 = new JPanel(new BorderLayout());
		central21.add(new JLabel("Porta"), BorderLayout.NORTH);
		central21.add(new JPanel().add(portaText), BorderLayout.SOUTH);

		centralPanel = new JPanel(new GridLayout(2, 2));
		centralPanel.add(central11);
		centralPanel.add(central12);
		centralPanel.add(central21);
		
		southpanel = new JPanel();
		southpanel.add(connectBtn);
		southpanel.add(disconnectBtn);
		southpanel.add(startBtn);
		southpanel.add(stopBtn);
		
		Container mainContainer = this.getContentPane();
		mainContainer.add(southpanel, BorderLayout.SOUTH);
		mainContainer.add(centralPanel, BorderLayout.CENTER);

		// Listeners
		ActionListener list = new ClientListener(this, addressText, portaText, matricolaText);

		connectBtn.setActionCommand(ClientListener.CONNECT);
		connectBtn.addActionListener(list);
		disconnectBtn.setActionCommand(ClientListener.DISCONNECT);
		disconnectBtn.addActionListener(list);
		startBtn.setActionCommand(ClientListener.START);
		startBtn.addActionListener(list);
		stopBtn.setActionCommand(ClientListener.STOP);
		stopBtn.addActionListener(list);		
		
		// Initial state
		setLocation(200, 100);
		setButtons(false, false);

		this.setVisible(true);
	}

	public void setButtons(boolean connected, boolean transmitting) {
		if(connected){
			connectBtn.setEnabled(false);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			
			if(transmitting){
				disconnectBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				startBtn.setEnabled(false);
			}else{
				stopBtn.setEnabled(false);
				startBtn.setEnabled(true);
				disconnectBtn.setEnabled(true);
			}
			
		}else{
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			connectBtn.setEnabled(true);
			disconnectBtn.setEnabled(false);
			startBtn.setEnabled(false);
			stopBtn.setEnabled(false);
		}
		
	}
}
