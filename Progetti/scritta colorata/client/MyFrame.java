
package cliente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyFrame extends JFrame{
    private String nome;
    private Container contenitore = this.getContentPane();
    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    private JButton connect = new JButton("Connect");
    private JButton disconnect = new JButton("Disconnect");
    private JLabel ip_address = new JLabel("IP Address");
    private JTextField ip_addressT = new JTextField("127.0.0.1");
    private JLabel porta = new JLabel("Porta");
    private JTextField portaT = new JTextField("4400");
    private JPanel sopra = new JPanel();
    private JPanel centro = new JPanel();
    private JPanel sotto = new JPanel();
    private JLabel centros = new JLabel("UCCISO");
    
    public MyFrame(){
        nome = JOptionPane.showInputDialog("Inserisci il tuo nome cognome matricola: ");
        this.setTitle(nome);
        sopra.add(start);
        sopra.add(ip_address);
        ip_addressT.setPreferredSize(new Dimension(50, 15));
        sopra.add(ip_addressT);
        sopra.add(porta);
        portaT.setPreferredSize(new Dimension(50, 15));
        sopra.add(portaT);
        sopra.add(stop);
        
        centros.setHorizontalAlignment(SwingConstants.CENTER);
        Font f = centros.getFont();
        centros.setFont(new Font(f.getName(), Font.BOLD, 30));
        centro.add(centros);
        
        sotto.add(connect);
        sotto.add(disconnect);
        
        contenitore.add(sopra, BorderLayout.NORTH);
        contenitore.add(centro, BorderLayout.CENTER);
        contenitore.add(sotto, BorderLayout.SOUTH);
        
        setVisible(true);
        setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        MyListener listener = new MyListener(ip_addressT.getText(), portaT.getText(), centros, start, stop, connect, disconnect);
        start.addActionListener(listener);
        stop.addActionListener(listener);
        connect.addActionListener(listener);
        disconnect.addActionListener(listener);
        
        start.setEnabled(false);
        stop.setEnabled(false);
        connect.setEnabled(true);
        disconnect.setEnabled(false);
    }
}
