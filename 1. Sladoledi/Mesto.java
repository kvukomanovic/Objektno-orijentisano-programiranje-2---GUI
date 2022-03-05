package v1;

import java.awt.*;

public class Mesto extends Canvas implements Runnable {

	private Aparat aparat;
	private Sladoled slad;
	private Ukus tmpUkus;
	private boolean radi=false;
	private int y;
	private Thread nit;
	
	public Mesto(Aparat a) {
		aparat=a;
		
	}
	
	public Sladoled dohvSladoled() {
		return slad;
	}
	
	private void crtaj() {
		y-=getHeight()/10;
		Graphics g=getGraphics();
		g.setColor(tmpUkus.dohvBoju());
		g.fillRect(0, y,getWidth(), getHeight()/10);
	}
	@Override
	public void run() {
		y=getHeight()-1;
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(!radi) wait();
				}
				crtaj();
				Thread.sleep(500);
				slad.dodajUkus(tmpUkus, 20);
				aparat.postaviTekstSladoleda(slad.toString());
				if (y==0) {
					aparat.omoguciProdaju();
					zavrsiTocenje();
				}
				
			}
			
		} catch (InterruptedException e) {}
		
	}
	public synchronized void pokreniTocenje() {
		slad=new Sladoled(200);
		nit=new Thread(this);
		radi=true;
		nit.start();
	}
	public synchronized void nastaviTocenje() {
		radi=true;
		notifyAll();
	}
	
	public synchronized void zavrsiTocenje() {
		if (nit!=null) nit.interrupt();
		nit=null;
	}
	public synchronized  void zaustaviTocenje() {
		radi=false;
	}
	public boolean tocenjeUToku() {
		return (nit!=null)? true:false;
	}
	public void postaviUkus(Ukus u) {
		tmpUkus=u;
	}
}
