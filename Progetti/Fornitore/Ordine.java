import java.util.*;

public class Ordine {

	private final int numero;
	private Cliente cliente;
	private List<TipoLinkComposizione> prodotti;
	
	public Ordine(int numero, Cliente cliente) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.prodotti = new LinkedList<TipoLinkComposizione>();
	}

	public int getNumero() {
		return numero;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<TipoLinkComposizione> getProdotti() {
		return prodotti;
	}

	private TipoLinkComposizione prodottoPresente(Prodotto p) {
		for (TipoLinkComposizione c: prodotti) {
			if (c.getProdotto().equals(p)) return c;
		}
		return null;
	}
	
	public void inserisciProdotto(Prodotto p, int q) {
		TipoLinkComposizione c = prodottoPresente(p);
		if (c==null) 
			prodotti.add(new TipoLinkComposizione(p, this, q));
		else
			{ int newQ = c.getQuantita() + q; 
			  c.setQuantita(newQ);
			}
	}

	@Override
	public String toString() {
		return "Ordine [numero=" + numero + ", cliente=" + cliente + ", prodotti=" + prodotti + "]";
	}

}
