import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread implements Runnable {

	private Server mainThread;
	private Socket sock;
	private boolean fired = false, running = true;
	private PrintWriter pw;
	private Scanner in;
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
			pw = new PrintWriter(new OutputStreamWriter(os));
			in = new Scanner(new InputStreamReader(is));
		} catch (IOException e) {
			fired = true;
			if (in != null) {
				in.close();
			}
			if (pw != null) {
				pw.close();
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

			String o = in.nextLine();
			System.err.println(o);
			if (o.equals("s_A_s")) {
				inviaMessaggi();
			} else if (o.equals("_ALT_")) {// chiusura client
				running = false;
			} else {
				String msg = (String) o;
				System.out.println(msg);
				mainThread.newMessage(msg);
			}

		}

		if (in != null)
			in.close();

		if (pw != null) {
			pw.close();
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
		
		for (String message : privateMessages) {
			pw.println(message);
			pw.flush();
		}
		pw.println("");
		pw.flush();
		privateMessages = new LinkedList<String>();
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