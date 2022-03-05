package v1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Aparat extends Panel {

    Mesto mesto=new Mesto(this);
	private Panel dugmici=new Panel();
    private Button prodaj=new Button("Prodaj");
	private Panel sladPanel=new Panel();
	private Label sladoledText=new Label();
	private ArrayList<Ukus> dostupniUkusi=new ArrayList<Ukus>();
	
	public Aparat() {
		popuni();
	}
	private void popuni() {
		prodaj.setEnabled(false);
		setLayout(new BorderLayout());
		Panel east = new Panel();
		east.setLayout(new GridLayout(2, 1));
		east.setPreferredSize(new Dimension(150, 400));
		east.add(prodaj);
		east.add(mesto);
		add(east,"East");
		dugmici.setLayout(new GridLayout(0, 2));
		add(dugmici,"Center");
		dugmici.setBackground(Color.lightGray);
		sladPanel.setBackground(Color.GRAY);
	
		Label l=new Label("Sladoled:  ");
		sladPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		l.setFont(new Font(getName(), Font.BOLD, 18));
		sladPanel.setPreferredSize(new Dimension(getWidth(),45));
		sladPanel.add(l,"West");
		sladoledText.setText("");
		sladPanel.add(sladoledText,"Center");
		//sladPanel.setBackground(Color.LIGHT_GRAY);
		add(sladPanel,"South");
		
		prodaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(mesto.dohvSladoled().toString());
				mesto.repaint();
				prodaj.setEnabled(false);
				
			}
		});

		
	}
	
	public void postaviTekstSladoleda(String s) {
		sladoledText.setText(s);
		sladoledText.revalidate();
	}
	void dodajUkus(Ukus u ) throws GreskaUkus {
		if (dostupniUkusi.contains(u)) throw new GreskaUkus();
		dostupniUkusi.add(u);
		Button b=new Button(u.dohvNaziv());
		b.setBackground(u.dohvBoju());
		dugmici.add(b);
		revalidate();
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (prodaj.isEnabled()) return;
				mesto.postaviUkus(u);
				if (mesto.tocenjeUToku()) mesto.nastaviTocenje();
				else mesto.pokreniTocenje();

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mesto.zaustaviTocenje();
			}
			
		});
	}
	
	public class GreskaUkus extends Exception{
		@Override
		public String toString() {
			return "***Greska ukus postoji!***";
		}
	}
	public void omoguciProdaju() {
		prodaj.setEnabled(true);
	}
}
