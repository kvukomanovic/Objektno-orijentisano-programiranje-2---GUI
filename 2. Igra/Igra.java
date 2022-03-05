package v2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import v2.Polje.Status;

public class Igra extends Frame {

	private Mreza mreza=new Mreza(this);
	private Panel desno;
	private Panel dole=new Panel();
	private double balans=0;
	private Label balansLabel=new Label("0");
	private double kvota;
	private Label kvotaLabel=new Label("");
	private double dobitak;
	private Label dobitakLabel=new Label("");
	private Button igraj=new Button("Igraj");
	private int ulogValue=100;
	private TextField ulog=new TextField("100");
	private Label doletext=new Label();
	
	public Mreza dohvMrezu() {
		return mreza;
	}
	
	public Igra() {
		super("Igra");
		setSize(500,500);
		dodajDesno(); 
		dodajDole();
		add(mreza,"Center");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		igraj.setEnabled(false);
		setVisible(true);
		
	}
	private void dodajDole() {
		dole.setBackground(Color.gray);
		add(dole,"South");
		dole.add(doletext);
		
	}

	private void dodajDesno() {
		desno=new Panel(new GridLayout(5,2,15,15));
		desno.add(new Label("Balans: "));
		desno.add(balansLabel);
		desno.add(new Label("Ulog: "));
		desno.add(ulog);
		desno.add(new Label("Kvota: "));
		desno.add(kvotaLabel);
		desno.add(new Label("Dobitak: "));
		desno.add(dobitakLabel);
		desno.add(new Label());
		desno.add(igraj);
		igraj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Igraj();
				
			}
		});
		ulog.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				int u=0;
				if (!ulog.getText().isEmpty()) {
					u=Integer.parseInt(ulog.getText());
					ulogValue=u;
					azurirajKvotuiDobitak();
				}
			}
		});
		
		add(desno,"East");
				
	}
	private void azurirajKvotuiDobitak() {
		int s=mreza.dohvIzabrana().size();
		if (s!=0) {
		kvota=20.0/s;
		dobitak=ulogValue*kvota;
		kvotaLabel.setText(String.format("%.2f", kvota));
		dobitakLabel.setText(String.format("%.2f", dobitak));
		}
	}
	
	public void javipromenu() {
		if (mreza.dohvIzabrana().size()==0) igraj.setEnabled(false);
		else {
			igraj.setEnabled(true);
			azurirajKvotuiDobitak();
		}
	}
	private void Igraj() {
		Generator gen=new Generator();
		int br=gen.broj(0, 19);
		if (mreza.set().contains(br)) {
			dole.setBackground(Color.green);
			balans+=dobitak;
		} else {
			dole.setBackground(Color.red);
			balans-=ulogValue;
		}
		doletext.setText(br+"");
		balansLabel.setText(String.format("%.2f",balans));
	}
	public static void main(String[] args) {
		new Igra();
	}
}
