package v4;

import java.awt.Canvas;
import java.awt.Graphics;

public abstract class Zivotinja {
	
	protected Rupa rupa;
	private int korak;

	public Zivotinja(Rupa rupa) {
		this.rupa=rupa;
	}
	
	public void crtajZivotinju(Graphics g){
		int w= rupa.getWidth()*korak/rupa.dohvBrKoraka();
		int h=rupa.getHeight()*korak/rupa.dohvBrKoraka();
		int x=(rupa.getWidth()-w)/2;
		int y=(rupa.getHeight()-h)/2;
		postaviDimenzije(x,y,w,h);
		crtajZapravo(g);
	}
	
	protected abstract void udarenaEfekat();
	protected abstract void pobeglaEfekat();
	protected abstract void postaviDimenzije(int x, int y,int w,int h);
	protected abstract void crtajZapravo(Graphics g);
	public void trenutniKorak(int i) {
		korak=i;		
	}
}
