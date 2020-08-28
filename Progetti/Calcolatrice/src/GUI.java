import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class GUI implements ActionListener{
    private String currentPass;
    private JFrame frame;
    private JPanel leftPanel, rightPanel, numbers;
    private JPasswordField passField;
    private JLabel label;
    private JTextArea textArea;
    private JButton zero, one, two, three, four, five, six, seven, eight, nine, cancel, show;

    public GUI(){
        initGUI();
    }

    public void initGUI(){
        /*zero = new JButton("0");
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");*/


        passField = new JPasswordField(30);
        passField.setEditable(false);
        label = new JLabel("Codice digitato");
        textArea = new JTextArea(4,35);
        textArea.setEditable(false);

        numbers = new JPanel();
        numbers.setLayout(new GridLayout(4,3));
        numbers.add(seven = new JButton("7"));
        seven.addActionListener(this);
        numbers.add(eight = new JButton("8"));
        eight.addActionListener(this);
        numbers.add(nine = new JButton("9"));
        nine.addActionListener(this);
        numbers.add(four = new JButton("4"));
        four.addActionListener(this);
        numbers.add(five = new JButton("5"));
        five.addActionListener(this);
        numbers.add(six = new JButton("6"));
        six.addActionListener(this);
        numbers.add(one = new JButton("1"));
        one.addActionListener(this);
        numbers.add(two = new JButton("2"));
        two.addActionListener(this);
        numbers.add(three = new JButton("3"));
        three.addActionListener(this);
        numbers.add(cancel = new JButton("C"));
        cancel.addActionListener(this);
        numbers.add(zero = new JButton("0"));
        zero.addActionListener(this);
        numbers.add(show = new JButton("Show"));
        show.addActionListener(this);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(BorderLayout.NORTH, passField);
        leftPanel.add(BorderLayout.SOUTH, numbers);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(BorderLayout.NORTH, label);
        rightPanel.add(BorderLayout.CENTER,textArea);

        frame = new JFrame("Pannello Digitale");
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setSize(800,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();

        if(cmd.equals("1")){
            passField.setText(String.copyValueOf(passField.getPassword())+one.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("2")){
            passField.setText(String.copyValueOf(passField.getPassword())+two.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("3")){
            passField.setText(String.copyValueOf(passField.getPassword())+three.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("4")){
            passField.setText(String.copyValueOf(passField.getPassword())+four.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("5")){
            passField.setText(String.copyValueOf(passField.getPassword())+five.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("6")){
            passField.setText(String.copyValueOf(passField.getPassword())+six.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("7")){
            passField.setText(String.copyValueOf(passField.getPassword())+seven.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("8")){
            passField.setText(String.copyValueOf(passField.getPassword())+eight.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("9")){
            passField.setText(String.copyValueOf(passField.getPassword())+nine.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("0")){
            passField.setText(String.copyValueOf(passField.getPassword())+zero.getText());
            currentPass = String.valueOf(passField.getPassword());
            System.out.println(currentPass);
        }
        if(cmd.equals("C")){
            int decision = JOptionPane.showConfirmDialog(null,"Cancellare il codice inserito?", "Pannello digitale", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(decision == JOptionPane.YES_OPTION){
                passField.setText("");
                textArea.setText("");
            }
        }
        if(cmd.equals("Show")){
            textArea.setText(currentPass);
        }
    }
}