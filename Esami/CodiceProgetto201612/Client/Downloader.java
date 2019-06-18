import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Downloader implements Runnable {

	private PrintWriter filePw;
	private Scanner scan;
	private boolean running;
	private String rec;

	public Downloader(String fileName, Scanner scan) throws IOException {
		
		rec = "";
		File file = new File(fileName+".txt");
		
		filePw = new PrintWriter(new FileWriter(file));
		this.scan = scan;
		running = false;
	}

	@Override
	public void run() {

		if (!running) {
			
			running = true;
			while (running) {
				String cmd = scan.nextLine();
				if (cmd.equals("*")) {
					filePw.println(rec);
					filePw.flush();
					String code = scan.nextLine();
					filePw.println(code);
					filePw.flush();
					filePw.close();
					
					code = scan.nextLine();
					
					running = !code.equals(ClientListener.STOP);
				} else {
					rec += cmd;
				}
			}

		}
	}
	
	public boolean isRunning(){
		return running;
	}
	
}
