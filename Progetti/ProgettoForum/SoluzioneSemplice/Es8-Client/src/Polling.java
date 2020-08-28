

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class Polling implements Runnable {

	private JTextArea textArea;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private boolean running;
	
	public Polling(JTextArea textArea, ObjectOutputStream oos, ObjectInputStream ois){
		this.textArea = textArea;
		this.oos = oos;
		this.ois=ois;
		running = false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		running = true;
		while(running){
			Object o = null;
			try {
				oos.writeObject(new Integer(0));
				oos.flush();
				o = ois.readObject();
				
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(null, "Connessione con il server persa, applicazione dismessa");
				//simulazione dell'operazione di click sulla chiusura del frame principale
				Window frame = SwingUtilities.getWindowAncestor(textArea);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Connessione con il server persa, applicazione dismessa");
				//simulazione dell'operazione di click sulla chiusura del frame principale
				Window frame = SwingUtilities.getWindowAncestor(textArea);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
			List<String> l = null;
			if(List.class.isInstance(o)){
				
				l = (List<String>)o;
			}
			String text = textArea.getText();
			Iterator<String> it = l.iterator();
			while(it.hasNext()){
				text += it.next()+"\n\n";
			}
			textArea.setText(text);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		running = false;
	}

}
