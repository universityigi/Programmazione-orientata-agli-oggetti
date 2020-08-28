
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
	private JPanel central22;
	private JPanel southpanel;
	private JButton connectBtn;
	private JButton disconnectBtn;
	private JButton startBtn;
	private JButton stopBtn;

	public BinaryDownloaderFrame() {

		Container mainContainer = this.getContentPane();

		centralPanel = new JPanel(new GridLayout(2, 2));

		central11 = new JPanel(new BorderLayout());
		central12 = new JPanel(new BorderLayout());
		central21 = new JPanel(new BorderLayout());
		central22 = new JPanel(new BorderLayout());

		central11.add(new JLabel("Matricola"), BorderLayout.NORTH);
		matricolaText = new JTextField(20);
		central11.add(matricolaText, BorderLayout.SOUTH);

		central12.add(new JLabel("IP Address"), BorderLayout.NORTH);
		addressText = new JTextField(20);
		central12.add(addressText, BorderLayout.SOUTH);

		central21.add(new JLabel("Porta"), BorderLayout.NORTH);
		portaText = new JTextField(20);
		central21.add(new JPanel().add(portaText), BorderLayout.SOUTH);

		centralPanel.add(central11);
		centralPanel.add(central12);
		centralPanel.add(central21);
		centralPanel.add(central22);

		southpanel = new JPanel();

		ActionListener list = new ClientListener(addressText, portaText, matricolaText);

		connectBtn = new JButton("Connect");
		connectBtn.setActionCommand(ClientListener.CONNECT);
		connectBtn.addActionListener(list);
		disconnectBtn = new JButton("Disconnect");
		disconnectBtn.setActionCommand(ClientListener.DISCONNECT);
		disconnectBtn.addActionListener(list);
		startBtn = new JButton("Start");
		startBtn.setActionCommand(ClientListener.START);
		startBtn.addActionListener(list);
		stopBtn = new JButton("Stop");
		stopBtn.setActionCommand(ClientListener.STOP);
		stopBtn.addActionListener(list);

		southpanel.add(connectBtn);
		southpanel.add(disconnectBtn);
		southpanel.add(startBtn);
		southpanel.add(stopBtn);

		mainContainer.add(southpanel, BorderLayout.SOUTH);
		mainContainer.add(centralPanel, BorderLayout.CENTER);

		setLocation(200, 100);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
