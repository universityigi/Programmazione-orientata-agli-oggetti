

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.JTextField;

public class InvioEventListener implements ActionListener {

	private JTextField textField;
	private PrintWriter pw;

	public InvioEventListener(JTextField textField, PrintWriter pw) {
		this.textField = textField;
		this.pw = pw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		if (!text.equals("")) {
			if(text.equals("_ALT_")||text.equals("s_A_s"))
				//controlli per evitare di mandare le stringhe di protocollo, in caso vengono modificate
				text = text.toLowerCase();
			pw.println(text);
			pw.flush();
			textField.setText("");
		}
	}
}
