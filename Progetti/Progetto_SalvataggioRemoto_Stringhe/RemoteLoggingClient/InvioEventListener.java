
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.*;

public class InvioEventListener implements ActionListener {
        private JTextField textField;    //alla pressione di invio useremo lo stream per inviare
	private ObjectOutputStream oos;  //il messaggio nel campo field

	public InvioEventListener(JTextField textField, ObjectOutputStream oos) {
		this.textField = textField;
		this.oos = oos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		if (!text.equals("")) {
			try {
				oos.writeObject(text);
				oos.flush();
			} catch (IOException e1) {

				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Connessione con il server persa, applicazione dismessa");
				// simulazione dell'operazione di click sulla chiusura del frame
				// principale, chiudo il programma in maniera controllata
				Window frame = SwingUtilities.getWindowAncestor(textField);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        }
			textField.setText("");
		}
	}
}
