package server;

public class Server{
	public static void main(String[] args) {
		Serveto server = new Serveto();
		Thread t = new Thread(server);
		t.start();
	}
}
