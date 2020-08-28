package client;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Downloader implements Runnable{
    private ColoredButton[] buttons;
    private Boolean running, celeste, stopFlag;
    private Socket socket;
    private PrintWriter out;
    private Scanner in;
	
    public Downloader(Socket socket, ColoredButton[] buttons){
	this.socket= socket;
	this.buttons = buttons;
	try {
            out = new PrintWriter(this.socket.getOutputStream());
            in = new Scanner(this.socket.getInputStream());
            running = false;
            celeste = false;
            stopFlag = false;
	}catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unused")
    @Override
    public void run() {
	if(!running){
            out.println("start");
            out.flush();
            running = true;
            while(running) {
                if(in.hasNextLine()){
                    String s[] = in.nextLine().split("\n");
                    int posizione_in_griglia = Integer.parseInt(s[0]);
                    if(posizione_in_griglia!=-1){
                        if(buttons[posizione_in_griglia].getBackground().equals(Color.LIGHT_GRAY))
                            buttons[posizione_in_griglia].changeColor(Color.YELLOW);
                    else if(buttons[posizione_in_griglia].getBackground().equals(Color.CYAN))
			buttons[posizione_in_griglia].changeColor(Color.GREEN);
                    }
                    else{			
			celeste = false;
			for(int i=0; i<144; i++){
                            if(buttons[i].getBackground().equals(Color.CYAN)){
				celeste=true;
				break;
                            }
                        }
			if(celeste && !stopFlag)
                            JOptionPane.showMessageDialog(null, "User Win!", "You Win", JOptionPane.INFORMATION_MESSAGE);
			else 
                            JOptionPane.showMessageDialog(null, "Server Wins! Not be sad RETRY!", "You Lose", JOptionPane.INFORMATION_MESSAGE);
			stopFlag = false;
                    }
		}
            }
	}
    }
    public void stop(){
	running = false;
	stopFlag = true;
	out.println("stop");
	out.flush();
	for(int i=0; i<144; i++)
            if(!buttons[i].getBackground().equals(Color.CYAN))
                buttons[i].changeColor(Color.LIGHT_GRAY);
    }
    public void disconnect(){
	out.println("disconnect");
	out.flush();
	try {
            socket.close();
            out.close();
            in.close();
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
}	