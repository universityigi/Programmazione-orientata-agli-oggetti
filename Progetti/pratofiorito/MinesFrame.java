package daFornire;

import javax.swing.*;
import java.awt.*;

public class MinesFrame extends JFrame {

    protected JLabel ipLabel = new JLabel("IP Address");
    protected JLabel portLabel = new JLabel("Port");
    protected JTextField ipBox = new JTextField(10);
    protected JTextField portBox = new JTextField(10);
    protected JButton connect = new JButton("Connetti");
    protected JButton disconnect = new JButton("Disconnetti");
    protected JButton start = new JButton("Start");
    protected JButton stop = new JButton("Stop");
    protected JButton clear = new JButton("Rivela");
    protected Board board;
    private MinesListener listener;


    public MinesFrame(){
        super("Dario Pietrosanto 1711031");
        MinesFrame frame = this;
        frame.setLayout(new BorderLayout(10,10));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        listener = new MinesListener(frame);
        frame.add(top(), BorderLayout.NORTH);
        frame.add(middle(), BorderLayout.CENTER);
        frame.add(bottom(), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel top(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));

        panel.add(ipLabel);
        ipBox.setText("80.211.232.219");
        panel.add(ipBox);
        panel.add(portLabel);
        portBox.setText("4400");
        panel.add(portBox);
        connect.addActionListener(listener);
        connect.setActionCommand(MinesListener.CONNECT);
        connect.setEnabled(true);
        panel.add(connect);
        disconnect.addActionListener(listener);
        disconnect.setActionCommand(MinesListener.DISCONNECT);
        disconnect.setEnabled(false);
        panel.add(disconnect);

        return panel;
    }

    private JPanel bottom(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));

        start.addActionListener(listener);
        start.setActionCommand(MinesListener.START);
        start.setEnabled(false);
        panel.add(start);
        stop.addActionListener(listener);
        stop.setActionCommand(MinesListener.STOP);
        stop.setEnabled(false);
        panel.add(stop);
        clear.addActionListener(listener);
        clear.setActionCommand(MinesListener.CLEAR);
        clear.setEnabled(false);
        panel.add(clear);

        return panel;
    }

    private JPanel middle(){
        BoardButton[][] grid = new BoardButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new BoardButton();
                grid[i][j].setPreferredSize(new Dimension(20,40));
                grid[i][j].setEnabled(false);
            }
        }

        board = new Board(grid);
        board.setPreferredSize(new Dimension(200,400));
        return board;
    }

}
