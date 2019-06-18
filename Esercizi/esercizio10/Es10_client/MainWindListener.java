import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MainWindListener implements WindowListener {

	private Socket sock;
	private PrintWriter pw;
	private Scanner in;
	private Polling p;

	public MainWindListener(Socket s, PrintWriter pw, Scanner in, Polling p) {
		this.sock = s;
		this.in = in;
		this.pw = pw;
		this.p = p;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {

		p.stop();

		pw.println("_ALT_");
		pw.flush();
		pw.close();

		if (in != null)
			in.close();

		if (sock != null)
			try {
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		System.exit(0);

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}
}
