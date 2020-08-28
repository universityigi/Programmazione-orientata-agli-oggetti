
public abstract class Persona {
	
	private final String cognome, nome;
	private String indirizzo;
	
	public Persona(String cognome, String nome, String indirizzo) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.indirizzo = indirizzo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return cognome + " " + nome + ", " + indirizzo;
	}

}
