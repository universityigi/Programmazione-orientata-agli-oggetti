package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private ServerSocket serverSocket; 
    private Socket socket;
    private Boolean flag=false;
	
    @Override
    public void run(){
	if(!flag){
            flag=true;
            try {
		serverSocket = new ServerSocket(8080);
            }catch (IOException e) {
		e.printStackTrace();
            }
            System.out.println("Server Avviato!");
            while(true){
		try {
                    socket = serverSocket.accept();
		} catch (IOException e){
                    break;
		}
		ClientThread client = new ClientThread(socket);
		Thread t = new Thread(client);
		t.start();
            }
	}
    }
}