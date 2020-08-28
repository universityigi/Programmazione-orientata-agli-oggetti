package esami.giugnodieci;


import com.sun.org.apache.xpath.internal.operations.Bool;
import javax.swing.*; import java.io.PrintWriter; 

import java.util.Scanner;
public class HexWorker extends SwingWorker<Boolean, Object> {

    private HexFrame frame;
    private PrintWriter printer;
    private Scanner scanner;

    public HexWorker(HexFrame frame, PrintWriter printer, Scanner scanner) {
        this.frame = frame;
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
// preferred A-F
        if (!isCancelled()) {
            String res = "";
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("+")) {
                    break;
                }
                res += line;
                frame.hexBox.setText(res);
            }
            frame.hexBox.setText(res);
        }
        return true;
    }

    @Override
    protected void done() {
        printer.println(HexListener.STOP);
        printer.flush();
        frame.start.setEnabled(true);
        frame.stop.setEnabled(false);
        frame.lettere.setEnabled(false);
        frame.numeri.setEnabled(false);
        frame.disconnect.setEnabled(true);
        frame.converti.setEnabled(true);

        JOptionPane.showMessageDialog(frame, "Trasmissione conclusa.", "Trasmissione conclusa", JOptionPane.INFORMATION_MESSAGE);
    }
}