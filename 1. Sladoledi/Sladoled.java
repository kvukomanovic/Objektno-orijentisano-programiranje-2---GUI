package v1;

import java.util.ArrayList;
import java.util.HashMap;

public class Sladoled {

	class UkusKolicina{
		Ukus ukus;
		int kolicina;
		private UkusKolicina(Ukus ukus, int kolicina) {
			this.ukus = ukus;
			this.kolicina = kolicina;
		}	
	};
    ArrayList<UkusKolicina> ukusi=new ArrayList<UkusKolicina>();
	private int velicina;
	private int popunjeno=0;
	
	public Sladoled(int v) {
		velicina=v;
	}
	public void dodajUkus(Ukus u, int kol) {
		if (popunjeno==velicina) return;
		int k= (velicina-popunjeno<kol)? velicina-popunjeno : kol;
		
		for(UkusKolicina uk: ukusi) {
			if (uk.ukus.equals(u)) {
				uk.kolicina+=k;
				popunjeno+=k;
				return;
		    }
		}
		UkusKolicina ukus=new UkusKolicina(u,k);
		ukusi.add(ukus); popunjeno+=k;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(UkusKolicina uk: ukusi) {
			sb.append(uk.kolicina+"ml"+uk.ukus+" ");
		}
		return sb.toString();
	}
}
