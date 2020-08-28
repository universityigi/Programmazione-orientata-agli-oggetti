import java.io.PrintWriter;

public class SenderThread implements Runnable {

	private PrintWriter pw;
	private boolean flag;
	private String sent = "";
	
	public SenderThread(PrintWriter pw) {
		flag = false;
		this.pw = pw;
	}

	// thread Sender
	public void run() {
		flag = true;
		while (flag) {
			String toSend = "";
			double check = Math.random();
			if (check > 0.5) {
				toSend = "1";
			} else {
				toSend = "0";
			}
			pw.println(toSend);
			pw.flush();
			sent += toSend;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// thread chiamante
	public void stop() {
		//chiusura del pw delegata al chiamante
		flag = false;
		pw.println("0");
		pw.flush();
		pw.println("1");
		pw.flush();
		pw.println("0");
		pw.flush();
		pw.println("*");
		pw.flush();
			
		sent+="010";
		
		String hashcode = Integer.toString(sent.hashCode());
		pw.println(hashcode);
		pw.flush();
		
		pw.println("stop");
		pw.flush();
	}
}
