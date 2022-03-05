package v3;

import java.awt.*;

public class Stavka extends Panel{

	private Label naslov=new Label();
	private Label tekst=new Label();
	public Stavka(String n, String t) {
		super();
		naslov.setText(n);
		naslov.setAlignment(Label.LEFT);
		naslov.revalidate();
		tekst.setText(t);
		tekst.setAlignment(Label.LEFT);
		tekst.revalidate();
		naslov.setFont(new Font(getName(), Font.BOLD,12 ));
		setLayout(new GridLayout(2,1));
		add(naslov);
		add(tekst);
	}
	public void promeniNaslov(String n) {
		naslov.setText(n);
		naslov.revalidate();
	}
	public String dohvNaslov() {
		return naslov.getText();
	}
	public String dohvTekst() {
		return tekst.getText();
	}
	
}
