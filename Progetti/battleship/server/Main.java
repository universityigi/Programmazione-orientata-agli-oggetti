package server;

public class Main {
	public static void main(String[] args) {
		Server server = new Server();
		Thread t = new Thread(server);
		t.start();
	}
}
