import java.net.*;
import java.io.*;

public class ClientThread implements Runnable {
	private Socket socket;
	private DBPosti dbPosti;
	public ClientThread(Socket socket, DBPosti dbPosti) {
		super();
		this.socket = socket;
		this.dbPosti = dbPosti;
	}
	
	private boolean verifica(String posto) {
		char c = posto.charAt(0);
		int n = Integer.parseInt(posto.substring(1));
		return (c>='A' && c<='F' && n>=1 && n<=20);
	}

	public void run() {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			PrintWriter pw = new PrintWriter(os);
			
			System.out.println("Receiving... ");
			
			String nome = br.readLine();
			String cognome = br.readLine();
			String posto = br.readLine();
			
			System.out.println("Received: "+nome+" "+cognome+" "+posto);
			
			if (nome==null || cognome==null || posto==null) {
				pw.println("Errore! Dati insufficienti.");
			}
			else if (!verifica(posto)) {
				pw.println("Errore! Posto non esistente.");
			}
			else {
				String persona = nome+" "+cognome;
				boolean r = dbPosti.prenota(posto, persona);
				if (r) {
					pw.println("OK! Posto prenotato.");
				}
				else {
					pw.println("Posto non disponibile.");
				}
			}
			pw.flush();
			is.close();
			os.close();
			socket.close();
			System.out.println(dbPosti);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
}
