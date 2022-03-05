package v4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Rupa extends Canvas implements Runnable {

	protected Basta basta;
	private Zivotinja zivotinja;
	private Thread nit;
	private int brKoraka=10;
	private boolean udarena=false;
	
	public Rupa(Basta basta) {
		this.basta=basta;
		//setSize(300,300);
		setVisible(true);
		setBackground(new Color(102, 51, 0));
		
	}
	public Zivotinja dohvZivotinju() {
		return zivotinja;
	}
	public void postZivotinju(Zivotinja z) {
		zivotinja=z;
		udarena=false;
	}
	public void zgazi() {
		if (zivotinja!=null) {
			udarena=true;
			zivotinja.udarenaEfekat();
		}
	}
	@Override
	public void paint(Graphics g) {
		setBackground(new Color(102, 51, 0));
		if (zivotinja!=null) zivotinja.crtajZivotinju(g);
		else {
			g.clearRect(0, 0,this.getWidth(), this.getHeight());
		
		}
	}
	@Override
	public void run() {
		try {
			 while(!nit.interrupted()) {
					for (int i=0;i<=brKoraka;i++) {
						Thread.sleep(100);
						zivotinja.trenutniKorak(i);
						repaint();
					}
				Thread.sleep(2000);
				 zaustavi();
			}
			}  catch (InterruptedException e) {}
		}
		
	
	public void stvoriNit() {
		if (nit==null) nit=new Thread(this);
	}
	public void pokreni() {
		if (!pokrenuta()) nit.start();
	}
	
	public synchronized void zaustavi() {
		if (nit!=null) {
			 nit.interrupt();
			 if (!udarena && zivotinja!=null) zivotinja.pobeglaEfekat(); 
			 zivotinja=null;
			 basta.javi(this);
			 repaint();
			 nit=null;
			 if (basta.dohvPovrce()==0) basta.zaustavi();
		}
	}
	public boolean pokrenuta() {
		return (nit!=null)?nit.isAlive():false;
	}
	public synchronized int dohvBrKoraka() {
		return brKoraka;
	}
	public synchronized void postaviBrKoraka(int brKoraka) {
		this.brKoraka = brKoraka;
	}
	
	
}
