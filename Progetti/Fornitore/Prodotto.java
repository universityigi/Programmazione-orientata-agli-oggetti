
public class Prodotto {
	
	private final String codice, descrizione;
	private Fornitore fornitore;
	private double prezzo;
	private int disponibili;
	
	public Prodotto(String codice, String descrizione, Fornitore fornitore, double prezzo, int disponibili) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.fornitore = fornitore;
		this.prezzo = prezzo;
		this.disponibili = disponibili;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getDisponibili() {
		return disponibili;
	}

	public void setDisponibili(int disponibili) {
		this.disponibili = disponibili;
	}

	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public String toString() {
		return "Prodotto [codice=" + codice + ", descrizione=" + descrizione + ", fornitore=" + fornitore + ", prezzo="
				+ prezzo + ", disponibili=" + disponibili + "]";
	}
	
}
