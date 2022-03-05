package v4;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Basta extends Panel implements Runnable {
	
	private ArrayList<Rupa> slobodne=new ArrayList<>();
	private Rupa[][] rupe;
	private int povrce=100;
	private int ms;
	private int brKoraka;
	private Thread nit;
	private int n=3,m=3;
	public boolean prekinuto=false;
	
	public Basta(int n, int m) {
		rupe=new Rupa[n][m];
		this.n=n; this.m=m;
		setBackground(Color.green);
		setVisible(true);
		setLayout(new GridLayout(n,m,25,25));
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				Rupa r=new Rupa(this);
				rupe[i][j]=r;
				slobodne.add(r);
				r.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						r.zgazi();
					}
				});
				add(r);
			}
	}

	public int dohvBrKoraka() {
		return brKoraka;
	}

	public void postaviBrKoraka(int brKoraka) {
		this.brKoraka = brKoraka;
		for(Rupa[]  ru : rupe) {
			for(Rupa r: ru)
			r.postaviBrKoraka(brKoraka);
		}
	}
	public void smanjiPovrce() {
		if (povrce>0) povrce--;
	}
	public synchronized void pokreni() {
		if (nit==null) {
			nit=new Thread(this);
			nit.start();
		}
	}
	public synchronized void zaustavi() {
		if (nit!=null) nit.interrupt();
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				rupe[i][j].zaustavi();
	}
	public void postaviInterval(int ms) {
		this.ms=ms;
	}
	public/* synchronized*/ int dohvPovrce() {
		return povrce;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Rupa r;
				//synchronized (this) {
					int n=(int)(Math.random()*(slobodne.size()));
					if (!slobodne.isEmpty()) { r=slobodne.get(n);
					Krtica kr=new Krtica(r);
					r.postZivotinju(kr);
					Thread.sleep(ms);
					r.stvoriNit();
					slobodne.remove(r);
					r.pokreni();
					ms=(int) ((int)ms*0.99);}
			//	}
				
			}
		} catch (InterruptedException e) {}
		
	}

	public void javi(Rupa rupa) {
		slobodne.add(rupa);
		Igra i=Igra.dohvIgru();
		i.postaviPovrce(povrce);
	}
	
}
