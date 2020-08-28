import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIPersone  extends JFrame implements Lockable{
	private boolean locked = true;
	private JButton buttonSubmit, buttonAddCliente, buttonAddFornitore;
	private JPanel centralPanel, cSxPanel, cDxPanel, ccSxPanel, ccDxPanel;
	private JLabel nomeLab1, nomeLab2, cognomeLab1, cognomeLab2, indirizzoLab1, indirizzoLab2, codFiscLab, pIvaLab;
	private JTextField nomeField1, nomeField2, cognomeField1, cognomeField2, indirizzoField1, indirizzoField2,
			codFiscField, pIvaField;

	public GUIPersone(List<Cliente> dbCliente, List<Fornitore> dbFornitore) {
		super();
		// pannello sinistro
		cSxPanel = new JPanel(new BorderLayout());
		ccSxPanel = new JPanel(new GridLayout(4, 2));

		nomeLab1 = new JLabel("Nome Cliente");
		cognomeLab1 = new JLabel("Cognome Cliente");
		indirizzoLab1 = new JLabel("Indirizzo Cliente");
		codFiscLab = new JLabel("Codice Fiscale Cliente");

		nomeField1 = new JTextField(10);
		cognomeField1 = new JTextField(10);
		indirizzoField1 = new JTextField(10);
		codFiscField = new JTextField(10);

		ccSxPanel.add(nomeLab1);
		ccSxPanel.add(nomeField1);
		ccSxPanel.add(cognomeLab1);
		ccSxPanel.add(cognomeField1);
		ccSxPanel.add(indirizzoLab1);
		ccSxPanel.add(indirizzoField1);
		ccSxPanel.add(codFiscLab);
		ccSxPanel.add(codFiscField);

		buttonAddCliente = new JButton("Aggiungi Cliente");
		JPanel butCliContainer = new JPanel();
		butCliContainer.add(buttonAddCliente);
		
		cSxPanel.add(ccSxPanel, BorderLayout.CENTER);
		cSxPanel.add(butCliContainer, BorderLayout.SOUTH);
		cSxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK) );
		
		// pannello Destro
		cDxPanel = new JPanel(new BorderLayout());
		ccDxPanel = new JPanel(new GridLayout(4, 2));
		
		nomeLab2 = new JLabel("Nome Fornitore");
		cognomeLab2 = new JLabel("Cognome Fornitore");
		indirizzoLab2 = new JLabel("Indirizzo Fornitore");
		pIvaLab = new JLabel("Partita Iva Fornitore");

		nomeField2 = new JTextField(10);
		cognomeField2 = new JTextField(10);
		indirizzoField2 = new JTextField(10);
		pIvaField = new JTextField(10);

		ccDxPanel.add(nomeLab2);
		ccDxPanel.add(nomeField2);
		ccDxPanel.add(cognomeLab2);
		ccDxPanel.add(cognomeField2);
		ccDxPanel.add(indirizzoLab2);
		ccDxPanel.add(indirizzoField2);
		ccDxPanel.add(pIvaLab);
		ccDxPanel.add(pIvaField);

		
		buttonAddFornitore = new JButton("Aggiungi Fornitore");
		JPanel butFornContainer = new JPanel();
		butFornContainer.add(buttonAddFornitore);
		
		cDxPanel.add(ccDxPanel, BorderLayout.CENTER);
		cDxPanel.add(butFornContainer, BorderLayout.SOUTH);
		cDxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK) );

		centralPanel = new JPanel(new GridLayout(1, 2));
		centralPanel.add(cSxPanel);
		centralPanel.add(cDxPanel);
		centralPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK) );
		
		buttonSubmit = new JButton("Aggiunta Terminata");
		Container mainWindow = getContentPane();
		mainWindow.add(centralPanel, BorderLayout.CENTER);
		
		JPanel subContainer = new JPanel();
		subContainer.add(buttonSubmit);
		mainWindow.add(subContainer, BorderLayout.SOUTH);
		
		setSize(800, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		buttonAddCliente.setActionCommand("cliente");
		buttonAddFornitore.setActionCommand("fornitore");
		buttonSubmit.setActionCommand("submit");
		
		ListnerCliente listenerCliente = new ListnerCliente(dbCliente, nomeField1, cognomeField1, indirizzoField1, codFiscField);
		buttonAddCliente.addActionListener(listenerCliente);
		
		ListnerFornitore lf = new ListnerFornitore(dbFornitore, nomeField2, cognomeField2, indirizzoField2, pIvaField);
		buttonAddFornitore.addActionListener(lf);
		
		ListnerChangeStatus lcs = new ListnerChangeStatus(this);
		buttonSubmit.addActionListener(lcs);

	}
	
	@Override
	public boolean isLocked() {
		return locked;
	}
	
	@Override
	public void unlock(){
		this.locked = false;
	}
}

class ListnerChangeStatus implements ActionListener{
	private GUIPersone gui;
	public ListnerChangeStatus(GUIPersone gui) {
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton source = (JButton)e.getSource();
		if(source.getActionCommand().equals("submit")){
			gui.unlock();
//			gui.setVisible(false);
			gui.dispose();
		}
		
	}
	
}