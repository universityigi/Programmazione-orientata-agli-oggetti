
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClientListener implements ActionListener {

	public static final String START = "start", STOP = "stop", CONNECT = "connect", DISCONNECT = "disconnect";

	private JTextField ipAddressField;
	private JTextField portaField;
	private JLabel msgLabel;

	private boolean connected = false, transmitting = false;
	private Downloader downloader = null;

	private PrintWriter netPw;
	private Scanner scan;
	private Socket sock;
	private StatusDownloaderFrame frame;

	public ClientListener(JTextField ipAddr, JTextField porta, JLabel msgLabel) {
		this.ipAddressField = ipAddr;
		this.portaField = porta;
		this.msgLabel = msgLabel;

	}

	private void setupConnection() throws UnknownHostException, IOException {
		sock = new Socket(ipAddressField.getText(), Integer.parseInt(portaField.getText()));
		OutputStream os = sock.getOutputStream();
		netPw = new PrintWriter(new OutputStreamWriter(os));
		scan = new Scanner(sock.getInputStream());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (frame == null)
			frame = (StatusDownloaderFrame) SwingUtilities.getRoot((JButton) e.getSource());

		String cmd = e.getActionCommand();

		if (cmd.equals(ClientListener.CONNECT)) {
			try {
				setupConnection();
				connected = true;
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Impossibile connettersi al server: \n" + e1.getMessage());
				e1.printStackTrace();
				return;
			}

			JOptionPane.showMessageDialog(null, "Connessione stabilita");
		} else if (cmd.equals(ClientListener.START)) {
			try {
				downloader = new Downloader(msgLabel, scan);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Impossibile creare il file: \n" + e1.getMessage());
				e1.printStackTrace();
			}
			transmitting = true;
			netPw.println(cmd);
			netPw.flush();

			Thread t = new Thread(downloader);
			t.start();
			JOptionPane.showMessageDialog(null, "Download avviato");
		} else if (cmd.equals(ClientListener.STOP)) {
			netPw.println(cmd);
			netPw.flush();
			transmitting = false;
			JOptionPane.showMessageDialog(null, "Download fermato");
		} else if (cmd.equals(ClientListener.DISCONNECT)) {
			netPw.println(ClientListener.DISCONNECT);
			netPw.flush();
			netPw.close();
			scan.close();
			connected = false;
			try {
				sock.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "Connessione chiusa");
		}
		frame.setButtons(connected, transmitting);
	}
}
