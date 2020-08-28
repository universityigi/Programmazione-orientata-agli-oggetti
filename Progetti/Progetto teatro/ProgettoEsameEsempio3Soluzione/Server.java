import java.net.*;
import java.io.*;

public class Server {
	private int port = 4444;
	private DBPosti dbPosti;
	
	public Server() {
		dbPosti = new DBPosti();
	}
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				System.out.println("Listening to port "+port+" ...");
				Socket sc = ss.accept(); // bloccante
				System.out.println("   Connected...");
				ClientThread ct = new ClientThread(sc,dbPosti);
				Thread t = new Thread(ct);
				t.start(); // non-bloccante
			}
			// ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Server().run();
	}
}
