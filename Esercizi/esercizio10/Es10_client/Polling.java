import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JTextArea;

public class Polling implements Runnable {

	private JTextArea textArea;
	private PrintWriter pw;
	private Scanner in;
	private boolean running;
	
	public Polling(JTextArea textArea, PrintWriter pw, Scanner in){
		this.textArea = textArea;
		this.pw = pw;
		this.in=in;
		running = false;
	}
	@Override
	public void run() {
		running = true;
		while(running){
			pw.println("s_A_s");
			pw.flush();
			
			String text = textArea.getText();
			String line = "";
			try{
				while(!(line=in.nextLine()).equals("")){
					//Stringa vuota non può essere inviata da Client, 
					//usata come stringa di protocollo dell'invio di stringhe
					text += line+"\n\n";
				}
			} catch(NoSuchElementException e){
				//Lanciata da Scanner.nextLine() quando la comunicazione viene interrotta.
				stop();
			}
			textArea.setText(text);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		running = false;
	}
}