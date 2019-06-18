
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class StatusDownloaderFrame extends JFrame {

	private JPanel north;
	private JTextField addressText;
	private JTextField portaText;
	private JLabel msgLabel;
	private JPanel addressPanel;
	private JPanel portPanel;
	private JPanel southpanel;
	private JButton connectBtn;
	private JButton disconnectBtn;
	private JButton startBtn;
	private JButton stopBtn;

	public StatusDownloaderFrame() {

		Container mainContainer = this.getContentPane();
				
		north = new JPanel();
		
		addressPanel = new JPanel(new FlowLayout());
		portPanel = new JPanel(new FlowLayout());

		msgLabel = new JLabel("STOPPED");
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Font f =  msgLabel.getFont();
		msgLabel.setFont(new Font(f.getName(), Font.BOLD, 30));
		
		addressPanel.add(new JLabel("IP Address"), BorderLayout.CENTER);
		addressText = new JTextField(10);
		addressText.setText("127.0.0.1");
		addressPanel.add(addressText, BorderLayout.SOUTH);

		portPanel.add(new JLabel("Port"), BorderLayout.CENTER);
		portaText = new JTextField(10);
		portaText.setText("4400");
		portPanel.add(new JPanel().add(portaText), BorderLayout.SOUTH);
		
		southpanel = new JPanel();

		ActionListener list = new ClientListener(addressText, portaText, msgLabel);

		connectBtn = new JButton("Connect");
		connectBtn.setActionCommand(ClientListener.CONNECT);
		connectBtn.addActionListener(list);
		disconnectBtn = new JButton("Disconnect");
		disconnectBtn.setActionCommand(ClientListener.DISCONNECT);
		disconnectBtn.addActionListener(list);
		startBtn = new JButton("Start");
		startBtn.setActionCommand(ClientListener.START);
		startBtn.addActionListener(list);
		stopBtn = new JButton("Stop ");
		stopBtn.setActionCommand(ClientListener.STOP);
		stopBtn.addActionListener(list);

		southpanel.add(connectBtn);
		southpanel.add(disconnectBtn);
		north.add(startBtn);
		north.add(addressPanel);
		north.add(portPanel);

		north.add(stopBtn);

		mainContainer.add(north, BorderLayout.NORTH);
		mainContainer.add(southpanel, BorderLayout.SOUTH);
		mainContainer.add(msgLabel, BorderLayout.CENTER);

		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setButtons(false, false);
		
		setTitle("Nome Cognome 1234567");
		
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
