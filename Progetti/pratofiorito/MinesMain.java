package daFornire;

import javax.swing.*;

public class MinesMain {

    public static void main(String[] args){
        Runnable init = new Runnable() {
            @Override
            public void run() {
                new MinesFrame();
            }
        };

        SwingUtilities.invokeLater(init);
    }
}
