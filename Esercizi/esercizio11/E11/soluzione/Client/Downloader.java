import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JLabel;

public class Downloader implements Runnable {

	private Scanner scan;
	private boolean running;
	private JLabel msgLabel;

	public Downloader(JLabel msgLabel, Scanner scan) throws IOException {

		this.msgLabel = msgLabel;

		this.scan = scan;
		running = false;
	}

	@Override
	public void run() {

		if (!running) {

			running = true;
			while (running) {
				String cmd = scan.nextLine();
				Font f = msgLabel.getFont();
				String[] info = cmd.split(";");
				String color = info[0], s = info[1], text = info[2];
				Color col = null;
				if (color.equals("3")) {
					col = Color.GREEN;
				} else if (color.equals("2")) {
					col = Color.ORANGE;
				} else if (color.equals("1")) {
					col = Color.RED;
				} else if (color.equals("0")) {
					col = Color.BLACK;
					running = false;
				}
				int size = Integer.parseInt(s);
				msgLabel.setFont(new Font(f.getName(), Font.BOLD, size));
				msgLabel.setText(text);
				msgLabel.setForeground(col);
			}

		}
	}

	public boolean isRunning() {
		return running;
	}

}
