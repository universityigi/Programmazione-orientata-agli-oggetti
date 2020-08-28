import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ListnerFornitore extends ListnerPersona {
	
	private JTextField pIva;
	private List<Fornitore> dbFornitore;

	public ListnerFornitore(List<Fornitore> dbFornitore, JTextField nomeField, JTextField cognomeField, JTextField indirizzoField, JTextField pIva) {
		super(nomeField, cognomeField, indirizzoField);
		this.pIva = pIva;
		this.dbFornitore = dbFornitore;
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		JButton source = (JButton)arg.getSource();
		if(source.getActionCommand().equals("fornitore")){
			String nome = super.getNomeField().getText();
			String cognome = super.getCognomeField().getText();
			String indirizzo = super.getIndirizzoField().getText();
			String pI = pIva.getText().toUpperCase();
			if(nome.equals("")|| cognome.equals("")||indirizzo.equals("")||pI.equals("")||!controlloPIva(pI)){
				JOptionPane.showMessageDialog(source, "Errore Inserimento", "Errore Inserimento", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				dbFornitore.add(new Fornitore(cognome, nome, indirizzo, pI));
				JOptionPane.showMessageDialog(source, "Inserimento OK", "Inserimento OK", JOptionPane.INFORMATION_MESSAGE);
				super.getNomeField().setText("");
				super.getCognomeField().setText("");
				super.getIndirizzoField().setText("");
				pIva.setText("");
			}
			
		}

	}

	private boolean controlloPIva(String pI) {
		if(pI.length() != 11)
			return false;
		try{
			Long.parseLong(pI);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}

}
