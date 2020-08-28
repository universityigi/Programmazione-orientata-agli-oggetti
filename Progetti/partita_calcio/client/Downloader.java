package client;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Downloader implements Runnable{
	private int i,j;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private Boolean running;
	private JTextArea lazio, roma;
	private Calciatore c;
	private LinkedList<Calciatore> calciatoriLazio, calciatoriRoma;
	
	public Downloader(Socket socket, JTextArea lazio, JTextArea roma){
		this.calciatoriLazio = new LinkedList<Calciatore>();
		this.calciatoriRoma = new LinkedList<Calciatore>();
		this.socket = socket;
		this.lazio = lazio;
		this.roma = roma;
		try{
			in = new Scanner(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			running = false;
			i = 0;
			j = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if(!running){
			running = true;
			out.println("start");
			out.flush();
			System.out.println("running");
			while(running){
				if(in.hasNextLine()){
					String cmd = in.nextLine();
					if(!(cmd.equals("+"))){
						String[] linea = cmd.split(",");
						if(linea.length == 3){
							String nome = linea[0];
							String ruolo = linea[1];
							String squadra = linea[2];	
							
							c = new Calciatore(nome, ruolo, squadra);
							if(c.getSquadra().equals("Roma") && c!=null){
								printRoma();
							}
							else if(c.getSquadra().equals("lazio") && c!=null){
								printLazio();
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
        public void lazio(){
		out.println("lazio");
		out.flush();	
		System.out.println("lazio");
	}
	public void roma(){
		out.println("roma");
		out.flush();
		System.out.println("roma");
	}
	private void printLazio(){
		if(!(calciatoriLazio.contains(c))){
			calciatoriLazio.add(c);
		}else{j++;}
		Collections.sort(calciatoriLazio);
		Iterator<Calciatore> it = calciatoriLazio.iterator();
	
		while(it.hasNext()){                       //forse mejo come fatto sotto
			lazio.setText(lazio.getText() + it.next() + "\n");
			for(int k=0; k<j; k++){  //stampa tutti duplicati trovati di tutti però
				lazio.append("DUPLICATO RICEVUTO!\n");
			}
		}
	}
	private void printRoma(){	
		if(!(calciatoriRoma.contains(c))){//quando ricevo duplicato devo stampare duplicato e
			calciatoriRoma.add(c);   //se ne ricevo più di seguito devo stamparli tutti; ma
                        i=0;                    //una volta ricevuto uno nuovo, non devo piu stampare la scritta!
		}else {i++;}
		Collections.sort(calciatoriRoma);
		Iterator<Calciatore> it = calciatoriRoma.iterator();
		roma.setText("");
		while(it.hasNext()){
			roma.setText(roma.getText() + it.next() + "\n");
		}
		for(int k=0; k<i; k++){
			roma.append("DUPLICATO RICEVUTO!\n");
		}
	}
        
	public void stop(){
		running = false;
		out.println("stop");
		out.flush();
	}
	public void disconnect(){
		out.println("disconnect");
		out.flush();
		
		try {
			out.close();
			in.close();
			socket.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
