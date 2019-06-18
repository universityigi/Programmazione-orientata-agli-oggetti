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
					sock = lis.accept();
				} catch (IOException e) {
					break;
				} 
				
				ClientThread cl = new ClientThread(sock, this);
				Thread tr = new Thread(cl);
				tr.start();
				l.add(cl);	
				control();
			}
		}
	}
	
	private void control(){
		List<ClientThread> del = new LinkedList<ClientThread>();
		Iterator<ClientThread> it = l.iterator();
		while(it.hasNext()){
			ClientThread sock = it.next();
			if(sock.isClosed()){
				del.add(sock);
			}
		}
		l.removeAll(del);
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
			if(!chiudiSockets()) System.exit(1);
		}
	}
	
	private boolean chiudiSockets(){
		
		Iterator<ClientThread> t = l.iterator();
		while(t.hasNext()){
			ClientThread sock = t.next();
			if(!sock.isClosed()){
				try {
					sock.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		l = null;
		return true;
	}
	
	public synchronized boolean newMessage(String msg){
		Iterator<ClientThread> it = l.iterator();
		while(it.hasNext()){
			ClientThread client = it.next();
			client.sendMsg(msg);
		}
		return true;
	}
}
