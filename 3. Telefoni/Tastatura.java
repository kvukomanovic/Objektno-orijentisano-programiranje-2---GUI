package v3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tastatura extends Panel implements Runnable {

	private Label natpis2=new Label("");
	private Label natpis1=new Label("");
	private Label natpis;
	private Button[][] dugmici=new Button[4][3];
	private Panel dugm=new Panel();
	private boolean rezimSlova=false;
	private Thread nit=new Thread(this);
	private ActionEvent event=null;
	private long ms=-1;
	private char c;
	private int br;
	private String s;
	
	
	
	public Tastatura(String n) {
		setLayout(new BorderLayout());
		Panel natpisi=new Panel(new GridLayout(2,1));
		//natpisi.setPreferredSize(new Dimension(getWidth(), 50));
		natpisi.add(natpis1);
		natpisi.add(natpis2);
		add(natpisi,"North");
		
		dodajDugmice();
		dodajOsluskivaceDugmicima();
	}
	
	private void dodajOsluskivaceDugmicima() {
		for(int i=0;i<4;i++) 
			for(int j=0;j<3;j++) {
				dugmici[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!rezimSlova) {
							natpis=natpis1;
							event=e;
							Button b;
							
								b=(Button)event.getSource();
							
							String s=b.getLabel();
							natpis.setText(natpis.getText()+s);
							repaint();
							event=null;
						}
						else {
							natpis=natpis2;
							if (event==null || event.getSource()!=e.getSource()) {
								ms=System.currentTimeMillis();
								event=e;
								s=((Button)e.getSource()).getLabel();
								br=0;
								
								c=s.charAt(br); c=(c!='_')? c : ' ';
								natpis.setText(natpis.getText()+c);
								
							} else {
								long mss=System.currentTimeMillis();
								if (mss-ms>1000) {
									ms=mss;
									event=e;
									s=((Button)e.getSource()).getLabel();
									br=0;
									c=s.charAt(br); c=(c!='_')? c : ' ';
									natpis.setText(natpis.getText()+c);
								} else {
									ms=mss;
									br=(br+1)%(s.length());
									c=s.charAt(br); c=(c!='_')? c : ' ';
									if (natpis.getText().length()==1) natpis.setText(""+c);
									else {
									String tmp=natpis.getText().substring(0, natpis.getText().length()-1);
									natpis.setText(tmp+c);
									}
								}
							}
													}
					}
				});
			}
		
	}

	private void dodajDugmice() {
	
		dugm.setLayout(new GridLayout(4,3));
		Font newButtonFont=new Font(getName(), Font.BOLD,20);
		for(int i=0;i<4;i++) 
			for(int j=0;j<3;j++) {
				dugmici[i][j]=new Button();
				dugmici[i][j].setFont(newButtonFont);
				dugm.add(dugmici[i][j]);
			}
		add(dugm,"Center");
		postaviBrojeve();
	}
	private void postaviBrojeve() {
		for(int i=0;i<3;i++) 
			for(int j=0;j<3;j++)
				dugmici[i][j].setLabel(""+(i*3+j+1));
		dugmici[3][0].setLabel("*");
		dugmici[3][1].setLabel("0");
		dugmici[3][2].setLabel("+");
		rezimSlova=false;
		dugmici[3][0].setEnabled(true);
		dugmici[0][0].setEnabled(true);
		dugmici[3][2].setEnabled(true);
	}
	private void postaviSlova() {
		dugmici[0][0].setLabel("");
		dugmici[0][1].setLabel("ABC");
		dugmici[0][2].setLabel("DEF");
		dugmici[1][0].setLabel("GHI");
		dugmici[1][1].setLabel("JKL");
		dugmici[1][2].setLabel("MNO");
		dugmici[2][0].setLabel("PQRS");
		dugmici[2][1].setLabel("TUV");
		dugmici[2][2].setLabel("WXYZ");
		dugmici[3][0].setLabel("");
		dugmici[3][1].setLabel("_");
		dugmici[3][2].setLabel("");
		dugmici[3][0].setEnabled(false);
		dugmici[0][0].setEnabled(false);
		dugmici[3][2].setEnabled(false);
		rezimSlova=true;
	}
	public void promeniRezim() {
		if (rezimSlova) postaviBrojeve();
		else postaviSlova();
	}
	public void postaviNatpis(String a,String b) {
		natpis1.setText(a);
		natpis2.setText(b);
	}
	@Override
	public void run() {
		
	}
	public String dohvIme() {
		return natpis2.getText();
	}
	public String dohvBroj() {
		return natpis1.getText();
	}

	
}
