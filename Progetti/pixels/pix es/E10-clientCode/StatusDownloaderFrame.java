
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class StatusDownloaderFrame extends JFrame {

	private JPanel north;
	private JTextField addressText;
	private JTextField portaText;
	private JPanel middlePanel;
	private JPanel addressPanel;
	private JPanel portPanel;
	private JPanel southpanel;
	private JButton connectBtn;
	private JButton disconnectBtn;
	private JButton startBtn;
	private JButton stopBtn;
	private JButton clearBtn;

	public StatusDownloaderFrame() {

		Container mainContainer = this.getContentPane();
				
		north = new JPanel();
		
		addressPanel = new JPanel(new FlowLayout());
		portPanel = new JPanel(new FlowLayout());
		
		addressPanel.add(new JLabel("IP Address"));
		addressText = new JTextField(10);
		addressText.setText("127.0.0.1");
		addressPanel.add(addressText);

		portPanel.add(new JLabel("Port"));
		portaText = new JTextField(10);
		portaText.setText("4400");
		portPanel.add(new JPanel().add(portaText));
		
		southpanel = new JPanel();
		
		middlePanel = new JPanel(new GridLayout(16,16));
		JPanel[] elements = new JPanel[16*16];
		
		for(int i = 0; i < elements.length ; i++){
			elements[i] = new JPanel();
			elements[i].setBackground(Color.lightGray);
			middlePanel.add(elements[i], i);
		}
		
		
		
		ActionListener list = new ClientListener(addressText, portaText, elements);

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
		clearBtn = new JButton("Clear");
		clearBtn.setActionCommand(ClientListener.CLEAR);
		clearBtn.addActionListener(list);

		southpanel.add(connectBtn);
		southpanel.add(disconnectBtn);
		southpanel.add(clearBtn);
		north.add(startBtn);
		north.add(addressPanel);
		north.add(portPanel);

		north.add(stopBtn);

		mainContainer.add(north, BorderLayout.NORTH);
		mainContainer.add(southpanel, BorderLayout.SOUTH);
		mainContainer.add(middlePanel, BorderLayout.CENTER);

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
				clearBtn.setEnabled(false);
				disconnectBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				startBtn.setEnabled(false);
			}else{
				clearBtn.setEnabled(true);
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
			clearBtn.setEnabled(true);
		}
		
	}
}
