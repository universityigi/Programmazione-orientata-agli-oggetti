public class Fornitore extends Persona {

	private final String partitaIVA;

	public Fornitore(String cognome, String nome, String indirizzo, String partitaIVA) {
		super(cognome, nome, indirizzo);
		this.partitaIVA = partitaIVA;
	}

	public String getPartitaIVA() {
		return this.partitaIVA;
	}

	@Override
	public String toString() {
		return "Fornitore ["+super.toString()+" partitaIVA=" + partitaIVA + "]";
	}

	
}
