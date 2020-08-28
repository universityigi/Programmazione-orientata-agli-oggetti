import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Downloader implements Runnable {

	private Scanner scan;
	private boolean running;
	private JPanel[] middlePanel;

	public Downloader(JPanel[] mosaic, Scanner scan) throws IOException {

		this.middlePanel = msgLabel;

		this.scan = scan;
		running = false;
	}

	@Override
	public void run() {

		if (!running) {

			running = true;
			while (running) {
				String cmd = scan.nextLine();
				String[] info = cmd.split(";");
								
				String position = info[0];
				String color = info[1];
				
				if(position.equals("-1") && color.equals("-1")){
					running = false;
					continue;
				}
				
				Color col = Color.decode(color);
				System.out.println(col.toString());
				middlePanel[Integer.valueOf(position)].setBackground(col); 
				
				
			}

		}
	}

	public boolean isRunning() {
		return running;
	}

}
