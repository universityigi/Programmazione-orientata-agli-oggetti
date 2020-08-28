/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.PrintWriter;

public class SenderThread implements Runnable {
    private PrintWriter pw;
    private boolean flag;
    private String[] msgs0 = { "OK", "Running", "Funzionante", "Online"};
    private String[] msgs1 = { "Warning", "Manutenzione consigliata", "Attenzione", "Sovraccarico" };
    private String[] msgs2 = { "Error", "Manutenzione necessaria", "Irraggiungibile", "Out of order" };
    public SenderThread(PrintWriter pw) {
        flag = false;
        this.pw = pw;
    }
    @Override
    public void run() {
        flag = true;
        while (flag) {
            String toSend = "";
            double status = Math.random(), severity = 0, msg = Math.random() * msgs0.length;
            while(severity < 0.4){
                severity = Math.random();
            }
            if (status < 0.33) {
                toSend += "3;";
                toSend += (int)Math.ceil(severity * 50) + ";";
                toSend += msgs0[(int) msg];
            } else if (status < 0.66) {
                toSend += "2;";
                toSend += (int)Math.ceil(severity * 50) + ";";
                toSend += msgs1[(int) msg];
            } else {
                toSend += "1;";
                toSend += (int)Math.ceil(severity * 50) + ";";
                toSend += msgs2[(int) msg];
            }
            pw.println(toSend);
            pw.flush();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop() {
// chiusura del pw delegata al chiamante
        flag = false;
        pw.println("0;12;STOP");
        pw.flush();
    }
}