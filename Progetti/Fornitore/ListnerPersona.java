import java.awt.event.ActionListener;

import javax.swing.JTextField;

public abstract class ListnerPersona implements ActionListener {

	private JTextField nomeField, cognomeField, indirizzoField;

	public ListnerPersona( JTextField nomeField, JTextField cognomeField, JTextField indirizzoField) {
		this.nomeField = nomeField;
		this.cognomeField = cognomeField;
		this.indirizzoField = indirizzoField;
	}

	protected JTextField getNomeField() {
		return nomeField;
	}

	protected JTextField getCognomeField() {
		return cognomeField;
	}

	protected JTextField getIndirizzoField() {
		return indirizzoField;
	}

}
