import java.io.*;
import java.net.*;

public class ClientThread implements Runnable {

	private Socket sock;
	private boolean fired = false, running = true;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private PrintWriter pw; //per scrittura su file
	private String nome;
	
	public ClientThread(Socket s) {
		sock = s;
		nome = null;
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
				Object o = ois.readObject();
				if (Integer.class.isInstance(o)) {
					running = false;
					
				} else if (String.class.isInstance(o)) {
					String s = (String)o;
					if(nome == null){
						nome = s;
						File f = new File(s+".txt");
						if(!f.exists())
							f.createNewFile();
						pw = new PrintWriter(new FileOutputStream(f,true));
						
					}else{
						pw.println(s);
						pw.flush();
						System.out.println(s);
					}
				}
			} catch (IOException e) {
				running = false;
			} catch (ClassNotFoundException e) {
				running = false;
			}
		}
		pw.close();
		try {
			ois.close();
			oos.close();
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
		sock.close();
		ois.close();
		oos.close();
	}

}