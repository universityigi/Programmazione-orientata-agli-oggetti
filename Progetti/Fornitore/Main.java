import java.util.*;

public class Main {

	final static String[] cognomi = { "Rossi", "Verdi", "Bianchi", "Neri", "Gialli", "Viola" };
	final static String[] nomi = { "Maria", "Giulia", "Antonio", "Carlo", "Luisa", "Anna" };

	static List<Cliente> dbClienti = new LinkedList<Cliente>();
	static List<Fornitore> dbFornitori = new LinkedList<Fornitore>();
	static List<Prodotto> dbProdotti = new LinkedList<Prodotto>();
	static List<Ordine> dbOrdini = new LinkedList<Ordine>();

	public static Cliente newRandomCliente() {
		int kc = (int) (Math.random() * cognomi.length);
		int kn = (int) (Math.random() * nomi.length);
		return new Cliente(nomi[kn], cognomi[kc], "", "" + nomi[kn].hashCode());
	}

	public static Fornitore newRandomFornitore() {
		int kc = (int) (Math.random() * cognomi.length);
		int kn = (int) (Math.random() * nomi.length);
		return new Fornitore(nomi[kn], cognomi[kc], "", "" + cognomi[kn].hashCode());
	}

	public static Cliente chooseRandomCliente() {
		int k = (int) (Math.random() * dbClienti.size());
		return dbClienti.get(k);
	}

	public static Fornitore chooseRandomFornitore() {
		int k = (int) (Math.random() * dbFornitori.size());
		return dbFornitori.get(k);
	}

	public static Prodotto newRandomProdotto(int n, int q) {
		String codice = String.format("A%03d", n);
		double pr = Math.random() * 100;
		return new Prodotto(codice, "", chooseRandomFornitore(), pr, q);
	}

	public static Prodotto chooseRandomProdotto() {
		int k = (int) (Math.random() * dbProdotti.size());
		return dbProdotti.get(k);
	}

	public static Ordine newRandomOrdine(int n)  {
		Ordine ord = new Ordine(n, chooseRandomCliente());
		int k = (int) (Math.random() * 10);
		for (int i = 0; i < k; i++) {
			Prodotto p = chooseRandomProdotto();
			int q = (int) (Math.random() * 10);
			ord.inserisciProdotto(p, q);
		}
		return ord;
	}

	public static Ordine chooseRandomOrdine() {
		int k = (int) (Math.random() * dbOrdini.size());
		return dbOrdini.get(k);
	}

	public static void creaDB() {
		
		GUIPersone gui= new GUIPersone(dbClienti, dbFornitori);
		while(gui.isLocked()){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.err.println(dbClienti.size());
		System.err.println(dbFornitori.size());
		
		for (int i = 0; i < 20; i++) {
			int q = (int) (Math.random() * 10);
			dbProdotti.add(newRandomProdotto(i, q));
		}
		for (int i = 0; i < 20; i++) {
			dbOrdini.add(newRandomOrdine(i));
		}
	}

	public static void main(String[] args) {
		creaDB();

		System.out.println("=== LISTA TUTTI PRODOTTI ===");
		Iterator<Prodotto> ip = dbProdotti.iterator();
		while (ip.hasNext()) {
			System.out.println(ip.next());
		}

		System.out.println("=== TOTALI DEGLI ORDINI ===");
		Iterator<Ordine> it = dbOrdini.iterator();
		while (it.hasNext()) {
			Ordine ord = it.next();
			double ptot = Operazioni.totaleOrdine(ord);
			System.out.println("Ordine: " + ord);
			System.out.println("Totale: " + ptot);
		}

		System.out.println("=== PRODOTTI NON DISPONIBILI NEGLI ORDINI ===");
		it = dbOrdini.iterator();
		while (it.hasNext()) {
			Ordine ord = it.next();
			List<Prodotto> pnd = Operazioni.prodottiNonDisponibili(ord, dbProdotti);
			System.out.println("Ordine: " + ord);
			System.out.println("Prodotti non disponibili: " + pnd);
		}
	}

}
