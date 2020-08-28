import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ListnerCliente extends ListnerPersona {
	private JTextField codiceFiscale;
	private List<Cliente> dbCliente;
	public ListnerCliente(List<Cliente> dbCliente, JTextField nomeField, JTextField cognomeField, JTextField indirizzoField,
			JTextField codiceFiscale) {
		super(nomeField, cognomeField, indirizzoField);
		this.codiceFiscale=codiceFiscale;
		this.dbCliente = dbCliente;
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		JButton source = (JButton)arg.getSource();
		if(source.getActionCommand().equals("cliente")){
			String nome = super.getNomeField().getText();
			String cognome = super.getCognomeField().getText();
			String indirizzo = super.getIndirizzoField().getText();
			String cf = codiceFiscale.getText().toUpperCase();
			if(nome.equals("")|| cognome.equals("")||indirizzo.equals("")||cf.equals("")||!controlloCF(cf)){
				JOptionPane.showMessageDialog(source, "Errore Inserimento", "Errore Inserimento", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				dbCliente.add(new Cliente(cognome, nome, indirizzo, cf));
				JOptionPane.showMessageDialog(source, "Inserimento OK", "Inserimento OK", JOptionPane.INFORMATION_MESSAGE);
				super.getNomeField().setText("");
				super.getCognomeField().setText("");
				super.getIndirizzoField().setText("");
				codiceFiscale.setText("");
			}
			
		}
	}

	private boolean controlloCF(String cf) {
		boolean check = true;
		if(cf.length() != 16)
			return false;
		check = check && isAlphabetical(cf.substring(0, 6));
		check = check && isNumeric(cf.substring(6, 8));
		check = check && isAlphabetical(cf.substring(8,9));
		check = check && isNumeric(cf.substring(9,11));
		check = check && isAlphabetical(cf.substring(11,12));
		check = check && isNumeric(cf.substring(12,15));
		check = check && isAlphabetical(cf.substring(15,16));
		
		return check;
	}
	
	private boolean isNumeric(String x){
		try{
			Long.parseLong(x);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	private boolean isAlphabetical(String x){
		for(int i = 0; i < x.length();i++){
			char c = x.charAt(i);
			int cValue = (int)c;
			if(cValue<65||cValue>90){
				return false;
			}
		}
		return true;
	}
		
		

}
