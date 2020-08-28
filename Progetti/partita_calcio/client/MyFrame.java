package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	private Container contenuto;
	
	private JPanel northPanel = new JPanel();
	private JPanel centralPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	private JPanel areaPanel = new JPanel(new FlowLayout());
	private JPanel singleAreaPanel1 = new JPanel(new BorderLayout());
	private JPanel singleAreaPanel2 = new JPanel(new BorderLayout());
	
	private JTextField ip_address = new JTextField();
	private JTextField port = new JTextField();
	
	private JLabel ip_addressL = new JLabel("IP Address");
	private JLabel portL = new JLabel("Port"); 
	
	private JButton connect = new JButton("Connect");
	private JButton disconnect = new JButton("Disconnect");
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	
	private JButton lazio = new JButton("LAZIO");
	private JButton roma = new JButton("ROMA");
	
	private JTextArea lazioT;
	private JTextArea romaT;
	
	public MyFrame(){
		super("Downloader");
		
		contenuto = this.getContentPane();
		
		ip_address.setPreferredSize(new Dimension(70, 15));
		port.setPreferredSize(new Dimension(60, 15));
		
		ip_address.setText("127.0.0.1");
		port.setText("4400");
		
		lazioT = new JTextArea(25, 15);
		romaT = new JTextArea(25, 15);
				
		lazioT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		romaT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		lazioT.setEditable(false);
		romaT.setEditable(false);
		
		lazioT.setText("");
		romaT.setText("");
		
		lazioT.scrollRectToVisible(new Rectangle(2, 23));
		romaT.scrollRectToVisible(new Rectangle(2, 23));
		
		lazioT.setForeground(Color.CYAN);
		romaT.setForeground(Color.ORANGE);
		
		northPanel.setLayout(new FlowLayout());
		centralPanel.setLayout(new BorderLayout());
		southPanel.setLayout(new FlowLayout());
		
		northPanel.add(ip_addressL);
		northPanel.add(ip_address);
		northPanel.add(portL);
		northPanel.add(port);
		northPanel.add(connect);
		northPanel.add(disconnect);
		
		singleAreaPanel1.add(lazioT, BorderLayout.CENTER);
		singleAreaPanel2.add(romaT, BorderLayout.CENTER);
		
		areaPanel.add(singleAreaPanel1);
		areaPanel.add(singleAreaPanel2);
		
		buttonPanel.add(lazio);
		buttonPanel.add(roma);
		
		centralPanel.add(areaPanel, BorderLayout.CENTER);
		centralPanel.add(buttonPanel, BorderLayout.EAST);
		
		southPanel.add(start);
		southPanel.add(stop);
		
		contenuto.add(northPanel, BorderLayout.NORTH);
		contenuto.add(centralPanel, BorderLayout.CENTER);
		contenuto.add(southPanel, BorderLayout.SOUTH);
		
		contenuto.setVisible(true);
		
		ActionListener listener = new MyListener(ip_address, port, lazioT, romaT, connect, disconnect, start, stop, lazio, roma);
		connect.addActionListener(listener);
		disconnect.addActionListener(listener);
		start.addActionListener(listener);
		stop.addActionListener(listener);
		lazio.addActionListener(listener);
		roma.addActionListener(listener);
		
		this.setButtons(false, false);
		this.setVisible(true);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
	}
	
	public void setButtons(Boolean connected, Boolean transmitted){
		if(connected){
			connect.setEnabled(false);
			start.setEnabled(true);
			stop.setEnabled(false);
			disconnect.setEnabled(true);
		}
		if(transmitted){
			connect.setEnabled(false);
			start.setEnabled(false);
			stop.setEnabled(true);
			disconnect.setEnabled(false);
		}
	}
}
