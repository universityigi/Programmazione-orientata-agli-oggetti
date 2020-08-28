
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread implements Runnable {

	private Server mainThread;
	private Socket sock;
	private boolean fired = false, running = true;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	// Buffer per mantenere tutti i nuovi messaggi ancora non inviati al client,
	// viene inviata ad ogni richiesta esplicita del client (comando 0)
	private List<String> privateMessages;

	public ClientThread(Socket s, Server mainThread) {
		sock = s;
		privateMessages = new LinkedList<String>();
		this.mainThread = mainThread;
		try {

			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());

		} catch (IOException e) {
			fired = true;
			try {
				ois.close();
				oos.close();
				sock.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				fired = true;
			}
		}
	}

	@Override
	public void run() {
		running = true;
		if (fired)
			return;
		fired = true;

		while (running) {
			try {
				// appena avviato il server mi pongo in attesa di una richiesta
				// dal client
				Object o = ois.readObject();
				if (Integer.class.isInstance(o)) {

					Integer cmd = (Integer) o;
					// System.out.println(cmd);

					// Richiesta "0" --> il client vuole tutti i messaggi
					// "nuovi" rispetto all'ultima richiesta
					if (cmd == 0) // polling
						inviaMessaggi();

					// Richiesta "1" --> il client sta chiudendo la connessione,
					// esco dal ciclo
					else // chiusura client
						running = false;
				} else if (String.class.isInstance(o)) {
					// Il client sta inviando una stringa, che viene comunicata
					// al thread principale per inviarla a tutti i thread figli
					String msg = (String) o;
					System.out.println(msg);
					mainThread.newMessage(msg);
				}
			} catch (IOException e) {
				running = false;
			} catch (ClassNotFoundException e) {
				running = false;
			}
		}
		try {
			ois.close();
			oos.close();
			sock.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void inviaMessaggi() {
		try {
			// Invio l'intera lista di nuovi messaggi
			oos.writeObject(privateMessages);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// reset della lista per non inviare duplicati
		privateMessages = new LinkedList<String>();
	}

	public boolean isClosed() {

		return sock.isClosed();
	}

	public void close() throws IOException {
		sock.close();
		ois.close();
		oos.close();
	}

	public void sendMsg(String msg) {
		// un nuovo messaggio non viene subito inviato ma viene salvato nella
		// Lista privateMessages in attesa che il client lo richieda
		privateMessages.add(msg);
	}
}