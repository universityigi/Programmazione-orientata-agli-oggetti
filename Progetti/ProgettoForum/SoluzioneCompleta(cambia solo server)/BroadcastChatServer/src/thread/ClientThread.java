package thread;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread implements Runnable {

	private Server mainThread;
	private Socket sock;
	private boolean fired = false, running = true;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private volatile List<String> privateMessages;

	public ClientThread(Socket s, Server mainThread) {
		sock = s;
		privateMessages = new LinkedList<String>();
		this.mainThread = mainThread;
		try {
			InputStream is;
			OutputStream os;
			is = sock.getInputStream();
			os = sock.getOutputStream();
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			fired = true;
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if (sock != null) {
					sock.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
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
				Object o = ois.readObject();
				if (Integer.class.isInstance(o)) {
					Integer cmd = (Integer) o;
					System.out.println(cmd);
					if (cmd == 0) // polling
						inviaMessaggi();
					else // chiusura client
						running = false;
				} else if (String.class.isInstance(o)) {
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
			if (ois != null) {
				ois.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			if (oos != null) {
				oos.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			if (sock != null) {
				sock.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void inviaMessaggi() {
		try {
			sendObject(privateMessages);
		} catch (IOException e) {
		}
		privateMessages = new LinkedList<String>();
	}

	private void sendObject(Object o) throws IOException {

		oos.writeObject(o);
		oos.flush();
	}

	public boolean isClosed() {

		return sock.isClosed();
	}

	public void close() throws IOException {
		sock.close();
	}

	public void sendMsg(String msg) {
		privateMessages.add(msg);
	}
}