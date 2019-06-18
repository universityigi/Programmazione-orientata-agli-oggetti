import java.io.IOException;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Server implements Runnable{

	private ServerSocket lis = null;
	private boolean flag = false;
	private List<ClientThread> l = null;
	
	public void run(){
		
		if(!flag){
			flag = true;
			l=new LinkedList<ClientThread>();
			try {
				//attendo nuove connessioni
				lis = new ServerSocket(3000);
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore nella creazione del ServerSocket, applicazione dismessa",null,0);
				System.exit(1);
			}
			System.out.println("Server Avviato");
			Socket sock = null;
			
			while(true){
				try{
					//accetto nuove connessioni
					sock = lis.accept();
				} catch (IOException e) {
					break;
				} 
				//Avvio il thread che gestisce la comunicazione effettiva con il client
				ClientThread cl = new ClientThread(sock);
				Thread tr = new Thread(cl);
				tr.start();
				l.add(cl);	
			}
		}
	}
		
	public void ferma(){
		if(flag){
			flag=false;
			try {
				lis.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			if(!chiudiConnessioni()) System.exit(1);
		}
	}
	
	private boolean chiudiConnessioni(){
		
		Iterator<ClientThread> t = l.iterator();
		while(t.hasNext()){
			ClientThread ct = t.next();
			if(!ct.isClosed()){
				try {
					ct.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		l = null;
		return true;
	}
}
