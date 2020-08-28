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
        
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton connect = new JButton("Connect");
	private JButton disconnect = new JButton("Disconnect");
	private JButton clear = new JButton("Clear");
	
	private JTextField ip_address = new JTextField();
	private JTextField port = new JTextField();
	
	private JLabel ip_addressL = new JLabel("Ip Address");
	private JLabel portL = new JLabel("Port");
	
	private JPanel northPanel = new JPanel();
	private JPanel centralPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	
	private JPanel[] arrayPanel;
	
	private Container content = this.getContentPane();
	
	public MyFrame(){
		super("Giulio Cesare 892892");
		//adding stuff to northPanel
		northPanel.setLayout(new FlowLayout());
		northPanel.add(start);
		northPanel.add(ip_addressL);
		ip_address.setPreferredSize(new Dimension(75,15));
		northPanel.add(ip_address);
		northPanel.add(portL);
		port.setPreferredSize(new Dimension(60,15));
		northPanel.add(port);
		northPanel.add(stop);
		//adding stuff to centralPanel
		centralPanel.setLayout(new GridLayout(16,16));
		arrayPanel = new JPanel[256];
		for(int i=0; i<256; i++){
			arrayPanel[i] = new JPanel();
			arrayPanel[i].setBackground(Color.LIGHT_GRAY);
			centralPanel.add(arrayPanel[i]);
                }
		//adding stuff to southPanel
		southPanel.setLayout(new FlowLayout());
		southPanel.add(connect);
		southPanel.add(disconnect);
		southPanel.add(clear);
		//addListener
		ActionListener listener = new MyListener(connect, disconnect, start, stop, clear,  ip_address, port, arrayPanel);
		connect.addActionListener(listener);
		start.addActionListener(listener);
		stop.addActionListener(listener);
		disconnect.addActionListener(listener);
		clear.addActionListener(listener);
		this.setButtons();
		//adding stuff to content
		content.setVisible(true);
		content.add(northPanel, BorderLayout.NORTH);
		content.add(centralPanel, BorderLayout.CENTER);
		content.add(southPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(500, 300);
	}	
	public void setButtons(){
		connect.setEnabled(true);
		start.setEnabled(false);
		stop.setEnabled(false);
		disconnect.setEnabled(false);
		clear.setEnabled(false);		
	}
}