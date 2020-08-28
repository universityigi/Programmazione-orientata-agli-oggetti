package server;

public class Server {
    public static void main(String[] args) {
        Serveto serv = new Serveto();
        Thread avv = new Thread(serv);
        avv.start();
    }   
}
