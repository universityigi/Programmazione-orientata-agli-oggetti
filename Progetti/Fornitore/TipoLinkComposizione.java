public class TipoLinkComposizione {
	private final Prodotto ilProdotto;
	private final Ordine lOrdine;
	private int quantita;
	
	public TipoLinkComposizione(Prodotto x, Ordine y, int a)
	{
		ilProdotto = x; 
		lOrdine = y; 
		quantita = a; 
	}

	public boolean equals(Object o) {
		if (o != null && getClass().equals(o.getClass())) {
      		TipoLinkComposizione b = (TipoLinkComposizione)o;
			return b.ilProdotto == ilProdotto && b.lOrdine == lOrdine; 
		}
    	else return false;
  	}

	public int hashCode() {
		return ilProdotto.hashCode() + lOrdine.hashCode();
	}

	public Prodotto getProdotto() { return ilProdotto; }
	public Ordine getOrdine() { return lOrdine; }
	public int getQuantita() { return quantita; }
	public void setQuantita(int q){ quantita = q; }
}