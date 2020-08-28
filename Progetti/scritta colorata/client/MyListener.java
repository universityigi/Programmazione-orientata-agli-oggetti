
package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;


public class MyListener implements ActionListener{
    private String address;
    private int porta;
    private JLabel centro;
    private JButton start, stop, connect, disconnect;
    private Downloader download;
    private boolean running = false;
    private Socket socket = null;
    private PrintWriter out;
    private Scanner in;
    
    public MyListener(String address, String porta, JLabel centro, JButton start, JButton stop, JButton connect, JButton disconnect){
        this.start = start;
        this.stop = stop;
        this.connect = connect;
        this.disconnect = disconnect;
        this.address = address;
        this.porta = Integer.parseInt(porta);
        this.centro = centro;
    }
    
    public void actionPerformed(ActionEvent e){
        String sta="Start", sto="Stop", con="Connect", dis="Disconnect";
        String cmd = e.getActionCommand();
        
        if(cmd.equals(sta)){
            connect.setEnabled(false);
            disconnect.setEnabled(false);
            start.setEnabled(false);
            stop.setEnabled(true);
            
            out.println("start");
            out.flush();
            download = new Downloader(in, centro);
            Thread t = new Thread(download);
            t.start();
            System.out.println("startato");
        }
        else if (cmd.equals(sto)){
            connect.setEnabled(false);
            disconnect.setEnabled(true);
            start.setEnabled(true);
            stop.setEnabled(false);
            
            out.println("stop");
            out.flush();
            System.out.println("stoppato");
        }
        else if(cmd.equals(con)){
            connect.setEnabled(false);
            disconnect.setEnabled(true);
            start.setEnabled(true);
            stop.setEnabled(false);
            try{
                socket = new Socket(address, porta);
                out = new PrintWriter(socket.getOutputStream());
                in = new Scanner(socket.getInputStream());
            }catch(IOException ex){
                ex.printStackTrace();
            }
            System.out.println("connessione stabilita");
        }
        else if(cmd.equals(dis)){
            connect.setEnabled(true);
            disconnect.setEnabled(false);
            start.setEnabled(false);
            stop.setEnabled(false);
            
            out.println("disconnect");
            out.flush();
            out.close();
            in.close();
            try{
                socket.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            System.out.println("disconnessuto");
        }
    }
}
