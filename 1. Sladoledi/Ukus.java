package v1;

import java.awt.*;

public class Ukus {

	private String naziv;
	private Color boja;
	
	public Ukus(String n, Color b) {
		naziv=n; boja=b;
	}
	
	public String dohvNaziv() {
		return naziv;
	}
	public Color dohvBoju() {
		return boja;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ukus)) return false;
		Ukus u=(Ukus) obj;
		return naziv.equals(u.dohvNaziv());
	}
	
	@Override
	public String toString() {
		return "["+naziv+"]";
	}
}
