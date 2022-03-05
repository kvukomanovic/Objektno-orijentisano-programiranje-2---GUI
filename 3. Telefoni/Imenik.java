package v3;

public class Imenik extends ListaStavki{

	@Override
	public void dodajStavku(Stavka s) {
		if(!(s instanceof Kontakt)) return;
		Kontakt k=(Kontakt)s;
		//lista.add(k);
		super.dodajStavku(k);
		
	}
	public String dohvIme(Broj broj) throws GNePostoji {
		for(Stavka k:lista) {
			if (k.dohvTekst().equals(broj.toString())) 
				return k.dohvNaslov();
		}
		throw new GNePostoji();
	}
	public Broj dohvBroj(String ime) throws GNePostoji {
		for(Stavka k:lista) {
			if (k.dohvNaslov().equals(ime)) 
				return new Broj(k.dohvTekst().replaceAll(" ", ""));
		}
		throw new GNePostoji();
	}
	public class GNePostoji extends Exception {

		@Override
		public String toString() {
			return "Ne postoji";
		}
	}
	
}
