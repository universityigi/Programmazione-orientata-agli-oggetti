import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ColoredButton extends JButton{
	
	//stringa che mantiene l'informazione sulla posizione della casella nella griglia
	private String position;
	//colore della casella
	private Color color;
	//cifra visualizzata all'interno della casella
	private String digit;
	
	public ColoredButton(String position, Color color) {
		this.position = position;
		this.color = color;
		this.digit = "";
		
		DigitListener listener = new DigitListener();
		this.addActionListener(listener);

		changeColor(color);
	}
	
	public void setTextDigit(String digit) {
		// metodo per gestire l'informazione riguardo la cifra visualizzata all'interno della casella
		// passare come parametro la stringa vuota "" per ripristinare il contenuto di una casella
		this.digit=digit;
		super.setText(digit);
	}

	public void changeColor(Color c) {
		// metodo per gestire l'informazione riguardo ai cambi di colore della casella
		this.color = c;
		super.setBackground(c);
	}

	public boolean isGreen() {
		// metodo che indica se la casella in questione contiene un numero vincente
		return color.equals(Color.GREEN);
	}
	
	public String getDigit() {
		//metodo utile per verificare a-posteriori se la casella in questione contiene una cifra o una stringa vuota ""
		return digit;
	}
	
	@Override
	public void setBackground(Color bg) {
		// NON UTILIZZARE setBackground per cambiare colore ad un'istanza di ColoredButton. 
		// Usare invece il metodo changeColor(Color color) 
		return;
	}
	
	@Override
	public void setText(String str) {
		// NON UTILIZZARE setText per cambiare il testo visualizzato ad un'istanza di ColoredButton. 
		// Usare invece il metodo setTextDigit(String n) 
		return;
	}

}

final class DigitListener implements ActionListener {
	
	//listener per cambio cifra con il click
	protected DigitListener(){
		super();
	}
	
	public void actionPerformed(ActionEvent e) {
		ColoredButton cb = (ColoredButton) e.getSource();
		Reader r = new NumberInput();
		String digit = r.read();
		cb.setTextDigit(""+digit);
		cb.changeColor(Color.LIGHT_GRAY);
	}

}