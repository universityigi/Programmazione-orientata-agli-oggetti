package daFornire;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MinesListener implements ActionListener {
    public static final String CONNECT = "connect";
    public static final String DISCONNECT = "disconnect";
    public static final String START = "start";
    public static final String STOP = "stop";
    public static final String CLEAR = "clear";
    private MinesFrame frame;
    private Socket socket;
    private PrintWriter printer;
    private Scanner scanner;
    private MinesWorker worker;

    public MinesListener(MinesFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        
        if (cmd.equals(CONNECT)){
            String ip=frame.ipBox.getText();
            Integer port;
            try {
                port=Integer.parseInt(frame.portBox.getText());
                if (port<0)
                    throw new NumberFormatException();
            } catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(frame, "Inserisci una porta valida",
                        "Errore",JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                socket = new Socket(ip,port);
            } catch ( IOException e1){
                JOptionPane.showMessageDialog(frame,"Impossibile contattare il server\n"+
                        ip+":"+port+"\nRiprova.","Errore",JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                printer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                scanner = new Scanner(socket.getInputStream());
            } catch (IOException e1){
                JOptionPane.showMessageDialog(frame, "Errore nella connessione al server.",
                        "Errore",JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(frame, "Connessione riuscita","Connessione riuscita",
                    JOptionPane.INFORMATION_MESSAGE);

            frame.connect.setEnabled(false);
            frame.disconnect.setEnabled(true);
            frame.start.setEnabled(true);
            frame.clear.setEnabled(true);
            return;
        } else if (cmd.equals(DISCONNECT)){
            printer.println(DISCONNECT);
            printer.flush();
            try {
                printer.close();
                scanner.close();
                socket.close();
            } catch (IOException e1){
                JOptionPane.showMessageDialog(frame, "Errore in chiusura della connessione "+
                        "al server.","Errore",JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(frame,"Connessione chiusa","Connessione chiusa",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.disconnect.setEnabled(false);
            frame.connect.setEnabled(true);
            frame.start.setEnabled(false);
            frame.clear.setEnabled(false);
            frame.board.setGameActive(false);
            return;
        } else if (cmd.equals(START)){
            // todo init?
            frame.start.setEnabled(false);
            frame.stop.setEnabled(true);
            frame.clear.setEnabled(false);
            frame.disconnect.setEnabled(false);
            clear();

            worker = new MinesWorker(frame, printer, scanner);
            worker.execute();
            return;
        } else if (cmd.equals(STOP)){
            if(!worker.equals(null))
                worker.cancel(false);
            return;
        } else if (cmd.equals(CLEAR)){
            // QUESTO E' RILEVA NON CLEAR!!
            boolean vittoria=true;
            frame.board.setGameActive(false);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (!frame.board.getButton(i,j).isSelected() && !frame.board.getButton(i,j).hasMine())
                        vittoria=false;
                    frame.board.getButton(i,j).setSelected(true);
                }
            }
            if (!vittoria)
                JOptionPane.showMessageDialog(frame, "Hai perso.\nRiprova.",
                        "Hai perso",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clear(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                frame.board.resetGame();
            }
        }
        return;
    }
}
