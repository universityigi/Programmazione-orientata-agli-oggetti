package daFornire;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class MinesWorker  extends SwingWorker<Boolean,Object> {

    private MinesFrame frame;
    private PrintWriter printer;
    private Scanner scanner;

    public MinesWorker(MinesFrame frame, PrintWriter printer, Scanner scanner) {
        this.frame=frame;
        this.printer=printer;
        this.scanner=scanner;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        printer.println(MinesListener.START);
        printer.flush();
        System.out.println("\nInizio download");
        try {
            while (!isCancelled()){
                String line = scanner.nextLine();
                if (line.equals("done"))
                    break;
                //format riga;colonna;value
                String row,col,val;
                Integer riga,colonna,mina;
                row=line.split(":")[0];
                col=line.split(":")[1];
                val=line.split(":")[2];
                try {
                    riga=Integer.parseInt(row);
                    colonna=Integer.parseInt(col);
                    mina=Integer.parseInt(val);
                } catch (NumberFormatException e){
                    return false;
                }
                if (colonna==0)
                    System.out.println();
                System.out.print(mina+" ");
                if (mina<0)
                    frame.board.getButton(riga,colonna).setMine(true);
                else {
                    frame.board.getButton(riga,colonna).setMine(false);
                    frame.board.getButton(riga,colonna).setAdjacentMinesCount(mina);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (isCancelled()){
            printer.println(MinesListener.STOP);
            printer.flush();
            String attendere = scanner.nextLine();
            while(!attendere.equals("interrupted")&&!attendere.equals("done")){
                attendere=scanner.nextLine();
            }
        }
        return true;
    }

    @Override
    protected void done() {
        if (isCancelled()){
            frame.board.setGameActive(false);
            JOptionPane.showMessageDialog(frame,"Download interrotto.",
                    "Download interrotto",JOptionPane.INFORMATION_MESSAGE);
        } else {
            frame.board.setGameActive(true);
            JOptionPane.showMessageDialog(frame,"La partita è pronta.",
                    "Download completato",JOptionPane.INFORMATION_MESSAGE);
        }
        frame.stop.setEnabled(false);
        frame.start.setEnabled(true);
        frame.clear.setEnabled(true);
        frame.disconnect.setEnabled(true);

    }
}
