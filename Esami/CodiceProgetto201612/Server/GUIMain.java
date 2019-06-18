import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUIMain extends JFrame implements ActionListener {

	private JPanel centralPanel;
	private JPanel topPanel;
	private JButton startBtn;
	private JButton stopBtn;
	private JTextField portTxt;
	private JLabel portLbl;

	private Server serv;

	public GUIMain() {

		// Widgets
		startBtn = new JButton("Start");
		stopBtn = new JButton("Stop");
		
		portTxt = new JTextField(10);
		portLbl = new JLabel("Port");
		
		// Layout
		topPanel = new JPanel();
		topPanel.add(portLbl);
		topPanel.add(portTxt);

		centralPanel = new JPanel();
		centralPanel.add(startBtn);
		centralPanel.add(stopBtn);

		Container mainCont = this.getContentPane();
		mainCont.add(centralPanel, BorderLayout.CENTER);
		mainCont.add(topPanel, BorderLayout.NORTH);
		
		// Listeners
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startBtn.setActionCommand("start");
		startBtn.addActionListener(this);
		stopBtn.setActionCommand("stop");
		stopBtn.addActionListener(this);
		
		// Initial state
		stopBtn.setEnabled(false);
		
		setSize(220,120);
	}

	public static void main(String[] args) {
		GUIMain gui = new GUIMain();
		gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("start")) {
			try {
				serv = new Server(portTxt.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Formato porta errato", "Errore", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Thread avv = new Thread(serv);
			avv.start();

			startBtn.setEnabled(false);
			stopBtn.setEnabled(true);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		else if(cmd.equals("stop")){
			serv.stop();
			startBtn.setEnabled(true);
			stopBtn.setEnabled(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
