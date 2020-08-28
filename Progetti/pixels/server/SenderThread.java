package server;

import java.io.PrintWriter;
import java.util.Random;

public class SenderThread implements Runnable{
	
	private Random random = new Random();
	private Boolean running;
	private PrintWriter out;
	public SenderThread(PrintWriter out){
		running=false;
		this.out = out;
	}
	@Override
	public void run() {
		if(!running){
			running=true;
			while(true){
				int r = random.nextInt(256);
				int c = random.nextInt(9)+1;
				out.println( r + ";#"+c+"44444");
				out.flush();
			}
		}
	}
	public void stop(){
		running=false;
		out.println("-1;-1");
		out.flush();
	}
}