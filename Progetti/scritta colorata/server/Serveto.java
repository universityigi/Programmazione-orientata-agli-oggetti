/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author pablo
 */
public class Serveto implements Runnable {
    private ServerSocket lis = null;
    
    @Override
    public void run() {
        try {
// attendo nuove connessioni
            lis = new ServerSocket(4400);
        } catch (IOException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nella creazione del ServerSocket, applicazione dismessa", null, 0);
            System.exit(1);
        }
        System.out.println("Server Avviato");
        Socket sock = null;
        while (true) {
            try {
// accetto nuove connessioni
                sock = lis.accept();
            } catch (IOException e) {
                break;
            }
            System.out.println("Socket creata, connessione accettata");
            ClientThread cl = new ClientThread(sock, this);
            Thread tr = new Thread(cl);
            tr.start();
        }
    }
}
