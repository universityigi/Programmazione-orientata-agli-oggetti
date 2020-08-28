import java.util.*;

public class DBPosti {
	private Map<String,String> posti;
	
	public DBPosti() {
		posti = new HashMap<String,String>();
	}
	public String getPrenotazione(String posto) {
		return posti.get(posto);
	}
	public synchronized boolean prenota(String posto, String persona) {
		if (posti.get(posto)==null) {
			posti.put(posto, persona);
			return true;
		}
		else
			return false;
	}
	@Override
	public String toString() {
		return "DBPosti [posti=" + posti + "]";
	}
}
