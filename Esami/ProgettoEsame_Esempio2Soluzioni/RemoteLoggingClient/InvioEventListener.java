
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InvioEventListener implements ActionListener {
	/*
	 * Alla pressione del pulsante "Invio" uso lo stream di output della socket
	 * per inviare il contenuto del text field
	 */
	private JTextField textField;
	private ObjectOutputStream oos;

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
