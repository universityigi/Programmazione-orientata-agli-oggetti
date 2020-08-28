/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author pablo
 */
public class ClientThread implements Runnable {
    private Socket sock;
    private boolean fired = false;
    private SenderThread st = null;
    Serveto parent;
    public ClientThread(Socket s, Serveto parent) {
        sock = s;
        this.parent = parent;
    }
    @Override
    public void run() {
        if (fired)
            return;
        fired = true;
        boolean running = true;
        Scanner in = null;
        PrintWriter pw = null;
        try {
            in = new Scanner(sock.getInputStream());
            pw = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
        e.printStackTrace();
        }
        while(running) {
            String cmd = in.nextLine();
            System.out.println("Ricevuto: "+ cmd);
            if (cmd.equals("start")) {
//Avvio nuovo thread per invio di 01
                st = new SenderThread(pw);
                Thread t = new Thread(st);
                t.start();
            } else if (cmd.equals("stop")) {
                st.stop();
            } else {
                running = false;
            }
        }
        try {
            pw.close();
            in.close();
            sock.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}