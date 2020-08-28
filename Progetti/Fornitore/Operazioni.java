import java.util.*;

public class Operazioni {

	public static double totaleOrdine(Ordine ord) {
		double r=0.0;
		for (TipoLinkComposizione c : ord.getProdotti()) {
			r += + c.getProdotto().getPrezzo() * c.getQuantita();
		}
		return r;
	}
	
	public static List<Prodotto> prodottiNonDisponibili (Ordine ord, List<Prodotto> lp) {
		List<Prodotto> r = new LinkedList<Prodotto>();
		for (TipoLinkComposizione c : ord.getProdotti()) {
			if (c.getQuantita() > c.getProdotto().getDisponibili())
				r.add(c.getProdotto());
		}
		return r;
	}

}
