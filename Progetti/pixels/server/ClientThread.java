package server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ClientThread implements Runnable {
	public Boolean running;
	public Random random;
	private Socket socket; 
	private PrintWriter out;
	private Scanner in;
	private SenderThread send;
        
	public ClientThread(Socket socket){
		try {
			this.socket = socket;
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new Scanner(new InputStreamReader(socket.getInputStream()));
			running=false;
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		System.out.println("Qualcuno connesso");
		if(!running){      
			running = true;  
			while(running){
				String cmd = in.nextLine();
				if(cmd.equals("start")){       
                                        System.out.println("inizio trasferimento");
					send = new SenderThread(out);
					Thread t = new Thread(send);
					t.start();
				}
				else if(cmd.equals("stop")){       
                                        System.out.println("stop trasferimento");
					send.stop();
				}
				else if(cmd.equals("disconnect")){
                                        System.out.println("Qualcuno disconnesso");
					running=false;
				}
			}
			try {
				out.flush();
				out.close();
				in.close();
				socket.close();
				System.out.println("disconnected");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}