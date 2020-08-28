
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MainWindListener implements WindowListener {
	/*
	 * Esempio di implementazione di listner di finestra custom, in questo caso
	 * alla chiusura della finestra prima di interrompere il programma si avvisa
	 * il server della chiusura imminente e si chiudono socket e streams e si
	 * interrompe il thread di polling
	 */
	private Socket sock;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Polling p;

	public MainWindListener(Socket s, ObjectOutputStream oos, ObjectInputStream ois, Polling p) {
		this.sock = s;
		this.ois = ois;
		this.oos = oos;
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

		try {
			oos.writeObject(new Integer(1));
			oos.flush();
			oos.close();
			ois.close();
			sock.close();
		} catch (IOException e) {
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
