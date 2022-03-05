package v3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Telefon extends Panel{

	private Panel imenikPanel=new Panel();
	private Broj broj;
	private Color boja;
	private Imenik imenik;
	private Tastatura tast;
	private Button dodaj=new Button("Dodaj kontakt");
	private Button onOff=new Button("Iskljuci telefon");
	private Label mojBroj;
	private int p=0;
	
	public Telefon(Broj b,Color c) {
		broj=b; boja=c;
		setBackground(boja);
		mojBroj=new Label(broj.toString());
		this.setLayout(new BorderLayout());
		onOff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onOff.getLabel()=="Iskljuci telefon") {
					onOff.setLabel("Ukljuci telefon");
					onOff.setBackground(Color.red);
				} else {
					onOff.setBackground(Color.LIGHT_GRAY);
					onOff.setLabel("Iskljuci telefon");
				}
				
			}
		});
		//dodaj centar - tastatura i imenik
		tast=new Tastatura("");
		imenik=new Imenik();
		Panel centar = new Panel();
		centar.setLayout(new GridLayout(2,1));
		centar.add(tast);
		centar.add(imenikPanel);
		add(centar,"Center");
		//dodaj dole
		Panel south=new Panel(new GridLayout(2,1));
		Panel dvadugmeta=new Panel();
		dvadugmeta.add(dodaj); dvadugmeta.add(onOff);
		south.add(dvadugmeta);
		south.add(mojBroj);
		mojBroj.setAlignment(1);
		mojBroj.setFont(new Font(getName(), Font.BOLD,12 ));
		add(south,"South");
		
		
		setVisible(true);
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tast.promeniRezim();
				if (p==0) p=1;
				else {
					Kontakt k=new Kontakt(tast.dohvIme(), new Broj(tast.dohvBroj()));
					imenik.dodajStavku(k);
					imenikPanel.add(imenik);
					imenikPanel.revalidate();
					tast.postaviNatpis("","");
					p=0;
				}
			}
		});
		
	}
	public Broj dohvBroj() {
		return broj;
	}
	public static void main(String[] args) {
		Frame okvir=new Frame("Telefoni");
		okvir.setSize(500,500);
		okvir.setLayout(new GridLayout(1,2));
		okvir.add(new Telefon(new Broj("+381644425"),Color.green));
		okvir.add(new Telefon(new Broj("+381608787"),Color.yellow));
		okvir.setVisible(true);
		okvir.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				okvir.dispose();
			}
		});
		
		
	}
}
