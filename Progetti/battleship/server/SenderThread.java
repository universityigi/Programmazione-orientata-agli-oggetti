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
            for(int i=0; (i<50 && (running=true)); i++){
		int r = random.nextInt(144);
		out.println( Integer.toString(r) );
		out.flush();
            }
            out.println(-1);
            out.flush();
	}
    }
    public void stop(){
	running=false;
	out.println("-1");
	out.flush();
    }	
}