package v1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import v1.Aparat.GreskaUkus;

public class Sladoledznica extends Frame {

	private Aparat aparat=new Aparat();
	private Panel dodavanjeUkusa=new Panel();
	private TextField nazivUkusa=new TextField();
	private TextField bojaUkusa=new TextField();
	private Button dodaj=new Button("Dodaj ukus");
	
	
	public Sladoledznica() {
		super("Sladoledznica");
		setSize(500,460);
		setResizable(false);
		popuniPanel();
		add(aparat,"Center");
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				aparat.mesto.zavrsiTocenje();
				dispose();
			}
		});
		
	}
	private void popuniPanel() {
		dodavanjeUkusa.setLayout(new GridLayout(1,5,5,5));
		dodavanjeUkusa.setPreferredSize(new Dimension(getWidth(),35));
		Label l=new Label("Naziv:  ");
		l.setFont(new Font(getName(),Font.BOLD,20));
		dodavanjeUkusa.add(l);
		dodavanjeUkusa.add(nazivUkusa);
		l=new Label("Boja:  ");
		l.setFont(new Font(getName(),Font.BOLD,20));
		dodavanjeUkusa.add(l);
		dodavanjeUkusa.add(bojaUkusa);
		dodavanjeUkusa.add(dodaj);
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv=nazivUkusa.getText();
				String boja=bojaUkusa.getText();
				if (!naziv.isEmpty() && !boja.isEmpty()) {
					Ukus b=new Ukus(naziv,Color.decode("#"+boja));
					try {
						aparat.dodajUkus(b);
					} catch (GreskaUkus e1) {
						System.out.println(e1);
					}
				}
			}
		});
		add(dodavanjeUkusa,"South"); 
		dodavanjeUkusa.setBackground(Color.CYAN);
		
	}
	
	public static void main(String[] args) {
		new Sladoledznica();
	}
	
	
}
