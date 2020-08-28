
package cliente;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import javax.swing.JLabel;

/**
 *
 * @author pablo
 */
public class Downloader implements Runnable{
    private Scanner in;
    private JLabel centro;
    private boolean running;
    
    
    public Downloader(Scanner in, JLabel centro){
        this.in = in;
        this.centro = centro;
        running = false;
    }
    
    
    @Override
    public void run(){
        if(!running){
            running = true;
            while(running){
                String s = in.nextLine();
                Font font = centro.getFont();
                String[] ss = s.split(";");
                String c = ss[0], f = ss[1], m = ss[2];
                Color color = null;
                if(c.equals("0")){
                    color = Color.black;
                    running = false;
                }
                else if(c.equals("1")){
                    color = Color.red;
                }
                else if(c.equals("2")){
                    color = Color.orange;
                }
                else if(c.equals("3")){
                    color = Color.green;
                }
                int size = Integer.parseInt(f);
                centro.setFont(new Font(font.getName(), Font.BOLD, size));
                centro.setText(m);
                centro.setForeground(color);
            }
        }
    }
    public boolean isRunning() {
        return running;
    }  
}
