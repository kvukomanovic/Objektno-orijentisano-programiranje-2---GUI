package v3;

public class Broj {

	private int kodDrzava,pozivni,pretp;

	public Broj(int kodDrzava, int pozivni, int pretp) {
		super();
		this.kodDrzava = kodDrzava;
		this.pozivni = pozivni;
		this.pretp = pretp;
	}
	public Broj(String s) {
		kodDrzava=Integer.parseInt(s, 1, 4, 10);
		pozivni=Integer.parseInt(s, 4, 6, 10);
		pretp=Integer.parseInt(s, 6, s.length(), 10);
	}
	static boolean istaDrzava(Broj a, Broj b) {
		return a.kodDrzava==b.kodDrzava;
	}
	static boolean istaMreza(Broj a, Broj b) {
		return a.kodDrzava==b.kodDrzava && a.pozivni==b.pozivni;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Broj)) return false;
		Broj b=(Broj)obj;
		return this.kodDrzava==b.kodDrzava && this.pozivni==b.pozivni && this.pretp==b.pretp;
	}
	@Override
	public String toString() {
		return "+"+kodDrzava+" "+pozivni+" "+ pretp;
	}
}
