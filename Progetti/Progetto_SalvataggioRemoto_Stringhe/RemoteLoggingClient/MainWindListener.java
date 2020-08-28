
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MainWindListener implements WindowListener {
	private Socket sock;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public MainWindListener(Socket s, ObjectOutputStream oos, ObjectInputStream ois) {
		this.sock = s;
		this.ois = ois;
		this.oos = oos;
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
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
        
        public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
        public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}
