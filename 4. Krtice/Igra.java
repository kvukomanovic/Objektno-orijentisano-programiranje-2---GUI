package v4;

import java.awt.*;
import java.awt.event.*;


public class Igra extends Frame {

	private static Igra temp=null;
	private Basta basta;
	private CheckboxGroup gr=new CheckboxGroup();
	private Checkbox lako=new Checkbox("Lako",gr,true);
	private Checkbox srednje=new Checkbox("Srednje",gr,false);
	private Checkbox tesko=new Checkbox("Tesko",gr,false);
	private Button dugme=new Button("Kreni");
	private int povrceInt=100;
	private Label povrce=new Label("Povrce: "+povrceInt);
	
	public static Igra novaIgra() {
		if (temp==null) temp=new Igra();
		return temp;
	}
	private Igra() {
		super("Igra");
		setSize(600,500);
		basta=new Basta(4,4);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				basta.zaustavi();
				dispose();
			}
		});
		dodajDesno();
		add(basta,"Center");
		setVisible(true);
		
	}

	private void dodajDesno() {
		Panel desno=new Panel(new GridLayout(2,1));
		Panel gore=new Panel(new GridLayout(5,1));
		Label t=new Label("Tezina:");
		t.setFont(new Font(getName(), Font.BOLD, 20));
		gore.add(t);
		gore.add(lako);
		gore.add(srednje);
		gore.add(tesko);
		gore.add(dugme);
		desno.add(gore);
		desno.add(povrce);
		add(desno,"East");
		dugme.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dugme.getLabel()=="Kreni") {
					lako.setEnabled(false);
					srednje.setEnabled(false);
					tesko.setEnabled(false);
					dugme.setLabel("Stani");
					Checkbox box=gr.getSelectedCheckbox();
					switch (box.getLabel()) {
						case "Lako": basta.postaviBrKoraka(10);
									 basta.postaviInterval(1000);
									break;
						case "Srednje": basta.postaviBrKoraka(8);
										basta.postaviInterval(750);
										break;
						case "Tesko": basta.postaviBrKoraka(6);
									  basta.postaviInterval(500);
					}
					basta.pokreni();
				}
				else {
					dugme.setLabel("Kreni");
					basta.zaustavi();
				}
			}
		});
		
		
		
	
	}
	public void postaviPovrce(int i) {
		povrceInt=i;
		povrce.setText("Povrce: "+povrceInt);
	}
	public static Igra dohvIgru() {
		return temp;
	}
	
	public static void main(String[] args) {
		Igra i=Igra.novaIgra();
	}
}
