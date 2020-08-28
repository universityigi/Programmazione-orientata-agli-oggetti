package esami.giugnodieci;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Scanner;

public class HexListener implements ActionListener {

    public static final String START = "start";
    public static final String CONNECT = "connect";
    public static final String DISCONNECT = "disconnect";
    public static final String STOP = "stop";
    public static final String LETTERE = "lettere";
    public static final String CONVERTI = "converti"; //todo public static final String LETTERE = "lettere";
    public static final String NUMERI = "cifre";
    private HexFrame frame;
    private Socket socket;
    private PrintWriter printer;
    private Scanner scanner;

    private HexWorker worker;

    public HexListener(HexFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(CONNECT)) {
            String ip;
            Integer port;
            ip = frame.ipBox.getText();
            try {
                port = Integer.parseInt(frame.portBox.getText());
                if (port < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "Inserisci un numero di porta valido.", "Errore", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                socket = new Socket(ip, port);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(frame, "Impossibile connettersi al server" + ip + ":" + port + "\nRiprova", "Errore", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                printer = new PrintWriter(socket.getOutputStream());
                scanner = new Scanner(socket.getInputStream());
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(frame, "Errore nel connettersi al server" + ip + ":" + port + "\nRiprova", "Errore", JOptionPane.WARNING_MESSAGE);
                return;
            }
            frame.connect.setEnabled(false);
            frame.disconnect.setEnabled(true);
            frame.start.setEnabled(true);
            JOptionPane.showMessageDialog(frame, "Connessione riuscita.", "Connessione riuscita",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (cmd.equals(DISCONNECT)) {
            printer.println(DISCONNECT);
            printer.flush();
            try {
                printer.close();
                scanner.close();
                socket.close();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(frame, "Errore in chiusura della connessione.", "Errore", JOptionPane.WARNING_MESSAGE);
            }
            frame.hexBox.setText("");
            frame.decBox.setText("");
            frame.binBox.setText("");
            frame.connect.setEnabled(true);
            frame.disconnect.setEnabled(false);
            frame.start.setEnabled(false);
            frame.converti.setEnabled(false);
            JOptionPane.showMessageDialog(frame, "Connessione chiusa.", "Connessione chiusa",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (cmd.equals(START)) {
            frame.hexBox.setText("");
            frame.decBox.setText("");
            frame.binBox.setText("");
            frame.start.setEnabled(false);
            frame.stop.setEnabled(true);
            frame.lettere.setEnabled(true);
            frame.numeri.setEnabled(true);
            frame.converti.setEnabled(false);
            printer.println(START);
            printer.flush();
            worker = new HexWorker(frame, printer, scanner);
            worker.execute();
            return;
        } else if (cmd.equals(STOP)) {
            if (!worker.equals(null)) {
                worker.cancel(true);
            }
            frame.start.setEnabled(true);
            frame.stop.setEnabled(false);
            frame.lettere.setEnabled(false);
            frame.numeri.setEnabled(false);
            frame.disconnect.setEnabled(true);
        } else if (cmd.equals(LETTERE) || cmd.equals(NUMERI)) {
            printer.println(cmd);
            printer.flush();
            return;
        } else if (cmd.equals(CONVERTI)) {
            Long dec = Long.parseUnsignedLong(frame.hexBox.getText(), 16);
            frame.decBox.setText(String.valueOf(dec));
            String bin = Long.toBinaryString(dec);
            frame.binBox.setText(bin);
        }

    }
}
