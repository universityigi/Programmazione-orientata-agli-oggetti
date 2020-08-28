package esami.giugnodieci;

import javax.swing.*;

import java.awt.*;

public class HexFrame extends JFrame {

    protected JLabel ipLabel = new JLabel("IP Address");
    protected JLabel portLabel = new JLabel("Port");
    protected JTextField ipBox = new JTextField(10);
    protected JTextField portBox = new JTextField(10);
    protected JButton connect = new JButton("Connetti");
    protected JButton disconnect = new JButton("Disconnetti");
    protected JButton start = new JButton("Start");
    protected JButton stop = new JButton("Stop");
    protected JButton converti = new JButton("Converti");
    protected JButton numeri = new JButton("0 - 9");
    protected JButton lettere = new JButton("A - F");
    protected JTextField hexBox = new JTextField(40);
    protected JTextField decBox = new JTextField(40);
    protected JTextField binBox = new JTextField(40);
    HexListener listener;

    public HexFrame() {
        super("");
        HexFrame frame = this;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        listener = new HexListener(frame);
        frame.add(top(), BorderLayout.NORTH);
        frame.add(middle(), BorderLayout.CENTER);
        frame.add(bottom(), BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel top() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        ipBox.setText("127.0.0.1");
        portBox.setText("4400");
        connect.addActionListener(listener);
        connect.setActionCommand(HexListener.CONNECT);
        disconnect.addActionListener(listener);
        disconnect.setActionCommand(HexListener.DISCONNECT);
        disconnect.setEnabled(false);
        panel.add(ipLabel);
        panel.add(ipBox);
        panel.add(portLabel);
        panel.add(portBox);
        panel.add(connect);
        panel.add(disconnect);
        return panel;
    }

    private JPanel middle() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel subpanel1 = new JPanel(new BorderLayout(5, 5));
        JPanel subpanel2 = new JPanel(new GridLayout(3, 1));
        JPanel subpanel3 = new JPanel(new GridLayout(3, 1));
        lettere.addActionListener(listener);
        lettere.setActionCommand(HexListener.LETTERE);
        lettere.setEnabled(false);
        numeri.addActionListener(listener);
        numeri.setActionCommand(HexListener.NUMERI);
        numeri.setEnabled(false);
        hexBox.setEditable(false);
        decBox.setEditable(false);
        binBox.setEditable(false);
        subpanel1.add(lettere, BorderLayout.WEST);
        subpanel1.add(numeri, BorderLayout.EAST);
        subpanel2.add(new JLabel("Hexadecimal"));
        subpanel2.add(new JLabel("Decimal"));
        subpanel2.add(new JLabel("Binary"));
        subpanel3.add(hexBox);

        subpanel3.add(decBox);
        subpanel3.add(binBox);
        panel.add(subpanel1, BorderLayout.WEST);
        panel.add(subpanel2, BorderLayout.CENTER);
        panel.add(subpanel3, BorderLayout.EAST);
        return panel;
    }

    private JPanel bottom() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        start.addActionListener(listener);
        start.setActionCommand(HexListener.START);
        start.setEnabled(false);
        stop.addActionListener(listener);
        stop.setActionCommand(HexListener.STOP);
        stop.setEnabled(false);
        converti.addActionListener(listener);
        converti.setActionCommand(HexListener.CONVERTI);
        converti.setEnabled(false);
        panel.add(start);
        panel.add(stop);
        panel.add(converti);
        return panel;
    }
}
