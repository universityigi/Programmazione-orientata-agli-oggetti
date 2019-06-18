import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientThread implements Runnable {

	private Socket sock;
	private boolean fired = false;
	private SenderThread st = null;
	private Scanner in = null;
	private PrintWriter pw = null;

	Server parent;

	public ClientThread(Socket s, Server parent) {
		sock = s;

		this.parent = parent;
	}

	@Override
	public void run() {
		if (fired)
			return;
		fired = true;
		boolean running = true;
		
		try {
			in = new Scanner(sock.getInputStream());
			pw = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (running) {
			String cmd = in.nextLine();
			System.out.println(cmd);
			if (cmd.equals("start")) {
				//Avvio nuovo thread per invio di 01
				st = new SenderThread(pw);
				Thread t = new Thread(st);
				t.start();
			} else if (cmd.equals("stop")) {
				st.stop();
			} else {
				running = false;
			}
		}
		try {
			pw.close();
			in.close();
			sock.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public boolean isClosed() {
		
		return sock.isClosed();
	}

	public void close() throws IOException {
		pw.close();
		in.close();
		sock.close();
	}
}