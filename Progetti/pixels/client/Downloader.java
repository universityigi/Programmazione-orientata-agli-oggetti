package client;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Downloader implements Runnable{
	private JPanel[] arrayPanel;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private boolean running;

	public Downloader(Socket socket, JPanel[] arrayPanel){
		this.socket = socket;
		this.arrayPanel = arrayPanel;
		try {
			in = new Scanner(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			running=false;
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "sono stato io", "here i am", JOptionPane.ERROR_MESSAGE);
		}
	}
	@Override
	public void run() {
		if(!running){
			out.println("start");
			out.flush();
                        
			clear();
                        try{
                            Thread.sleep(333);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        
			running=true;
			while(running){        
				if(in.hasNextLine()){
					String linea = in.nextLine();
					String[] arr = linea.split(";");
				
					int pos = Integer.parseInt(arr[0]);
					Color color = Color.decode(arr[1]);
				
					if(pos != -1 && arr[1] != "-1"){
						arrayPanel[pos].setBackground(color);
					}else {System.out.println("finish");}
				}
			}
		}
	}
	
	public boolean isRunning(){
		return running;
	}
	public void stop(){
		if(running == true){
			running=false;
			out.println("stop");
			out.flush();
		}
	}
	public void disconnect(){
		out.println("disconnect");
		out.flush();
		try {
			out.close();
			in.close();
			socket.close();
			Thread.sleep(500);
			System.out.println(socket);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
        public void clear(){
		for(int i=0; i<256; i++){
			arrayPanel[i].setBackground(Color.LIGHT_GRAY);
		}
	}
}