import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

public class Server implements Runnable {

	private ServerSocket lis = null;
	private int port;
	private boolean running;
	private Set<ClientThread> clients = null;

	public Server(String text) throws NumberFormatException {
		port = Integer.parseInt(text);
		running = false;
		clients = new HashSet<ClientThread>();
	}

	public void run() {

		try {
			// attendo nuove connessioni
			lis = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore nella creazione del ServerSocket, applicazione dismessa", null,
					0);
			System.exit(1);
		}
		System.out.println("Server Avviato");
		Socket sock = null;
		running = true;

		while (running) {
			try {
				// accetto nuove connessioni
				sock = lis.accept();
			} catch (IOException e) {
				break;
			}
			ClientThread cl = new ClientThread(sock, this);
			Thread tr = new Thread(cl);
			clients.add(cl);
			tr.start();
		}
	}

	public void stop() {
		if (running) {
			running = false;
			try {
				lis.close();
			} catch (IOException e) {
				return;
			}
			if (!chiudiSockets())
				System.exit(1);
		}
	}

	private boolean chiudiSockets() {

		Iterator<ClientThread> t = clients.iterator();
		while (t.hasNext()) {
			ClientThread clientThread = t.next();
			if (!clientThread.isClosed()) {
				try {
					clientThread.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		clients = new HashSet<ClientThread>();
		return true;
	}
}
