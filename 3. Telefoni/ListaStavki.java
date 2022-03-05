package v3;

import java.awt.*;
import java.util.ArrayList;

public abstract class ListaStavki extends Panel{

	protected ArrayList<Stavka> lista=new ArrayList<>();
	
	public ListaStavki() {
		setLayout(new GridLayout(0,1));
	}
	public void dodajStavku(Stavka s) {
		lista.add(s);
		add(s);
		revalidate();
	}
	
}
