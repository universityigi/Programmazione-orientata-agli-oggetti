package esami.giugnodieci;

import javax.swing.*;

public class HexMain {

    public static void main(String[] args) {
        Runnable init = new Runnable() {
            @Override
            public void run() {
                HexFrame g = new HexFrame();
                g.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(init);
    }
}
