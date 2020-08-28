public class Cliente extends Persona {

	private final String codicefiscale;

	public Cliente(String cognome, String nome, String indirizzo, String codicefiscale) {
		super(cognome, nome, indirizzo);
		this.codicefiscale = codicefiscale;
	}
	
	public String getCodiceFiscale() {
		return this.codicefiscale;
	}
	
	@Override
	public String toString() {
		return "Cliente ["+super.toString()+" codicefiscale=" + codicefiscale + "]";
	}

}
