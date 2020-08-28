package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame{
    private static final long serialVersionUID = 1L;

    private Container contenuto;
	
    private JPanel northPanel = new JPanel();
    private JPanel centralPanel = new JPanel();
    private JPanel southPanel = new JPanel();
	
    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    private JButton connect = new JButton("Connect");
    private JButton disconnect = new JButton("Disconnect");
    private JButton clear = new JButton("Clear");

    private JLabel ip_addressL = new JLabel("Ip Address");
    private JLabel portL = new JLabel("Port");
	
    private JTextField ip_address = new JTextField();
    private JTextField port = new JTextField();
	
    public MyFrame(){
	super("Nicola Todaro 1737673");
	ip_address.setPreferredSize(new Dimension(75,15));
	port.setPreferredSize(new Dimension(60,15));
	
	northPanel.setLayout(new FlowLayout());
	centralPanel.setLayout(new GridLayout(12,12));
	southPanel.setLayout(new FlowLayout());
		
	contenuto = this.getContentPane();
		
	northPanel.add(start);
	northPanel.add(ip_addressL);
	northPanel.add(ip_address);
	northPanel.add(portL);
	northPanel.add(port);
	northPanel.add(stop);
		
	ColoredButton[] buttons = new ColoredButton[144];
	for(int i=0; i<144; i++){
            buttons[i] = new ColoredButton();
            buttons[i].addActionListener(new ColoredButton());
            buttons[i].changeColor(Color.LIGHT_GRAY);
            centralPanel.add(buttons[i]);
	}
		
	southPanel.add(connect);
        southPanel.add(disconnect);
	southPanel.add(clear);
		
        contenuto.add(northPanel, BorderLayout.NORTH);
	contenuto.add(centralPanel, BorderLayout.CENTER);
	contenuto.add(southPanel, BorderLayout.SOUTH);
		
	contenuto.setVisible(true);
		
	ActionListener listener = new MyListener(start, stop, connect, disconnect, clear, ip_address, port, buttons);
	start.addActionListener(listener);
	stop.addActionListener(listener);
	connect.addActionListener(listener);
	disconnect.addActionListener(listener);
	clear.addActionListener(listener);
	this.setButtons();
	this.setVisible(true);
	this.setLocation(500, 200);
	this.setSize(500, 450);
    }
    public void setButtons(){
		connect.setEnabled(true);
		start.setEnabled(false);
		stop.setEnabled(false);
		disconnect.setEnabled(false);
		clear.setEnabled(false);		
	}
}
