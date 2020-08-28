package client;

public class Calciatore implements Comparable<Calciatore>{
	private String nome, ruolo, squadra;
	public Calciatore(String nome, String ruolo, String squadra){
		this.nome = nome;
		this.ruolo = ruolo;
		this.squadra = squadra;
	}
        public String getNome() {
		return nome;
	}
        public String getRuolo() {
		return ruolo;
	}
        public String getSquadra() {
		return squadra;
	}
        @Override
	public boolean equals(Object o){
		if(o != null && this.getClass().equals(o.getClass())){
			Calciatore c = (Calciatore)o;
			return nome.equals(c.getNome()) &&
					ruolo.equals(c.getRuolo())&&
					squadra.equals(c.getSquadra());
		}
		return false;
	}
	@Override
	public int hashCode(){
		return nome.hashCode() + ruolo.hashCode() + squadra.hashCode();
	}
	@Override
	public int compareTo(Calciatore c) {
		if(ruolo.compareTo(c.getRuolo()) != 0 ) 
			return -ruolo.compareTo(c.getRuolo());
		return nome.compareTo(c.getNome());
	}	
	@Override
	public String toString(){
		return nome + " - " + ruolo + " - " + squadra;
	}
}
